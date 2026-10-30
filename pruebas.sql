USE happy_feet_veterinaria;

-- Triggers
SHOW TRIGGERS;

-- Procedimientos
SHOW PROCEDURE STATUS
WHERE Db = 'happy_feet_veterinaria';

-- Eventos
SHOW EVENTS;

-- Alertas generadas
SELECT *
FROM alertas_inventario
ORDER BY id DESC;



-- RF25
CALL sp_alertas_vencimiento();

-- RF33
CALL sp_servicios_mas_solicitados();

-- RF34
CALL sp_desempeno_veterinarios();

-- RF35
CALL sp_estado_inventario();

-- RF36
CALL sp_facturacion_periodo('2026-01-01', '2026-12-31');

