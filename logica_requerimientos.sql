USE happy_feet_veterinaria;

DROP TRIGGER IF EXISTS trg_stock_bajo;
DROP TRIGGER IF EXISTS trg_stock_bajo_update;

DROP TRIGGER IF EXISTS trg_validar_prescripcion;
DROP TRIGGER IF EXISTS trg_validar_insumo;
DROP TRIGGER IF EXISTS trg_validar_vacuna;

DROP PROCEDURE IF EXISTS sp_alertas_vencimiento;
DROP PROCEDURE IF EXISTS sp_servicios_mas_solicitados;
DROP PROCEDURE IF EXISTS sp_desempeno_veterinarios;
DROP PROCEDURE IF EXISTS sp_estado_inventario;
DROP PROCEDURE IF EXISTS sp_facturacion_periodo;

DROP EVENT IF EXISTS ev_alertas_vencimiento;


-- RF24: ALERTAS DE STOCK BAJO


DELIMITER //

CREATE TRIGGER trg_stock_bajo
AFTER INSERT ON inventario
FOR EACH ROW
BEGIN
    IF NEW.cantidad_stock < NEW.stock_minimo THEN

        INSERT INTO alertas_inventario
            (producto_id, tipo_alerta, mensaje)
        VALUES
            (NEW.id, 'STOCK_BAJO', 'Producto con stock bajo');

    END IF;
END //

CREATE TRIGGER trg_stock_bajo_update
AFTER UPDATE ON inventario
FOR EACH ROW
BEGIN
    IF NEW.cantidad_stock < NEW.stock_minimo
       AND OLD.cantidad_stock >= OLD.stock_minimo THEN

        INSERT INTO alertas_inventario
            (producto_id, tipo_alerta, mensaje)
        VALUES
            (NEW.id, 'STOCK_BAJO', 'Producto con stock bajo');

    END IF;
END //

DELIMITER ;


-- RF 25 procedimiento y evento de vencimiento: 

DELIMITER //

CREATE PROCEDURE sp_alertas_vencimiento()
BEGIN
    INSERT INTO alertas_inventario
        (producto_id, tipo_alerta, mensaje)

    SELECT
        i.id,
        'VENCIMIENTO_PROXIMO',
        'Producto próximo a vencer'

    FROM inventario i

    WHERE i.fecha_vencimiento BETWEEN CURDATE()
        AND DATE_ADD(CURDATE(), INTERVAL 30 DAY)

    AND NOT EXISTS (
        SELECT a.id
        FROM alertas_inventario a
        WHERE a.producto_id = i.id
          AND a.tipo_alerta = 'VENCIMIENTO_PROXIMO'
          AND a.estado = 'Pendiente'
    );
END //

DELIMITER ;

-- evento de RF25 

CREATE EVENT ev_alertas_vencimiento
ON SCHEDULE EVERY 1 DAY
DO
    CALL sp_alertas_vencimiento();


-- RF26 Bloquear productos vencidos

-- PRESCRIPCIONES

DELIMITER //

CREATE TRIGGER trg_validar_prescripcion
BEFORE INSERT ON prescripciones
FOR EACH ROW
BEGIN
    DECLARE producto_vencido INT DEFAULT 0;

    SELECT COUNT(*)
    INTO producto_vencido
    FROM inventario
    WHERE id = NEW.producto_id
      AND fecha_vencimiento < CURDATE();

    IF producto_vencido > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede usar un medicamento vencido';
    END IF;

END //

DELIMITER ;

-- INSUMOS DE PROCEDUMIENTOS

DELIMITER //

CREATE TRIGGER trg_validar_insumo
BEFORE INSERT ON insumos_procedimientos
FOR EACH ROW
BEGIN
    DECLARE producto_vencido INT DEFAULT 0;

    SELECT COUNT(*)
    INTO producto_vencido
    FROM inventario
    WHERE id = NEW.producto_id
      AND fecha_vencimiento < CURDATE();

    IF producto_vencido > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede utilizar un insumo vencido';
    END IF;

END //

DELIMITER ;

-- VACUNAS DE JORNADAS


DELIMITER //

CREATE TRIGGER trg_validar_vacuna
BEFORE INSERT ON registro_jornada_vacunacion
FOR EACH ROW
BEGIN
    DECLARE producto_vencido INT DEFAULT 0;

    SELECT COUNT(*)
    INTO producto_vencido
    FROM inventario
    WHERE id = NEW.vacuna_id
      AND fecha_vencimiento < CURDATE();

    IF producto_vencido > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede aplicar una vacuna vencida';
    END IF;

END //

DELIMITER ;

-- RF 33 SERVICIOS MÁS SOLICITADOS

DELIMITER //

CREATE PROCEDURE sp_servicios_mas_solicitados()
BEGIN
    SELECT
        i.servicio_id,
        SUM(i.cantidad) AS veces_solicitado

    FROM items_factura i

    INNER JOIN facturas f
        ON i.factura_id = f.id

    WHERE i.tipo_item = 'Servicio'
      AND f.estado <> 'Anulada'

    GROUP BY i.servicio_id
    ORDER BY veces_solicitado DESC;

END //

DELIMITER ;


-- rf 34: DESEMPEÑO DEL EQUIPO VETERINARIO

DELIMITER //

CREATE PROCEDURE sp_desempeno_veterinarios()
BEGIN
    SELECT
        v.id AS veterinario_id,

        (SELECT COUNT(c.id)
         FROM consultas_medicas c
         WHERE c.veterinario_id = v.id) AS total_consultas,

        (SELECT COUNT(p.id)
         FROM procedimientos_especiales p
         WHERE p.veterinario_id = v.id
           AND p.estado = 'Finalizado') AS procedimientos_finalizados

    FROM veterinarios v
    ORDER BY total_consultas DESC;

END //

DELIMITER ;


-- RF 35 estado del inventario

DELIMITER //

CREATE PROCEDURE sp_estado_inventario()
BEGIN

    -- Productos que necesitan reabastecimiento
    SELECT
        id AS producto_id,
        cantidad_stock,
        stock_minimo
    FROM inventario
    WHERE cantidad_stock < stock_minimo;

    -- Productos próximos a vencer
    SELECT
        id AS producto_id,
        cantidad_stock,
        fecha_vencimiento
    FROM inventario
    WHERE fecha_vencimiento BETWEEN CURDATE()
        AND DATE_ADD(CURDATE(), INTERVAL 30 DAY);

END //

DELIMITER ;

-- RF 36 FACTURACIÓN POR PERÍODO

DELIMITER //

CREATE PROCEDURE sp_facturacion_periodo(
    IN fecha_inicio DATE,
    IN fecha_fin DATE
)
BEGIN
    SELECT
        id AS factura_id,
        fecha_emision,
        total,
        estado

    FROM facturas

    WHERE fecha_emision >= fecha_inicio
      AND fecha_emision < DATE_ADD(fecha_fin, INTERVAL 1 DAY)

    ORDER BY id;

END //

DELIMITER ;



