
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportesDAO {

    // RF33: Servicios más solicitados
    public List<Map<String, Object>> serviciosMasSolicitados() {

        String sql = "SELECT "
                + "i.servicio_id, "
                + "SUM(i.cantidad) AS veces_solicitado "
                + "FROM items_factura i "
                + "INNER JOIN facturas f ON i.factura_id = f.id "
                + "WHERE i.tipo_item = 'Servicio' "
                + "AND f.estado <> 'Anulada' "
                + "GROUP BY i.servicio_id "
                + "ORDER BY veces_solicitado DESC";

        return ejecutarConsulta(sql);
    }

    // RF34: Desempeño de veterinarios
    public List<Map<String, Object>> desempenoVeterinarios() {

        String sql = "SELECT "
                + "v.id AS veterinario_id, "
                + "(SELECT COUNT(c.id) "
                + "FROM consultas_medicas c "
                + "WHERE c.veterinario_id = v.id) AS total_consultas, "
                + "(SELECT COUNT(p.id) "
                + "FROM procedimientos_especiales p "
                + "WHERE p.veterinario_id = v.id "
                + "AND p.estado = 'Finalizado') "
                + "AS procedimientos_finalizados "
                + "FROM veterinarios v "
                + "ORDER BY total_consultas DESC";

        return ejecutarConsulta(sql);
    }

    // RF35: Productos que necesitan reabastecimiento
    public List<Map<String, Object>> productosStockBajo() {

        String sql = "SELECT "
                + "id AS producto_id, "
                + "nombre_producto, "
                + "cantidad_stock, "
                + "stock_minimo "
                + "FROM inventario "
                + "WHERE cantidad_stock < stock_minimo "
                + "ORDER BY cantidad_stock ASC";

        return ejecutarConsulta(sql);
    }

    // RF35: Productos próximos a vencer
    public List<Map<String, Object>> productosProximosVencer() {

        String sql = "SELECT "
                + "id AS producto_id, "
                + "nombre_producto, "
                + "cantidad_stock, "
                + "fecha_vencimiento "
                + "FROM inventario "
                + "WHERE fecha_vencimiento BETWEEN CURDATE() "
                + "AND DATE_ADD(CURDATE(), INTERVAL 30 DAY) "
                + "ORDER BY fecha_vencimiento ASC";

        return ejecutarConsulta(sql);
    }

    // RF36: Facturación por período
    public List<Map<String, Object>> facturacionPorPeriodo(
            LocalDate fechaInicio, LocalDate fechaFin) {

        String sql = "SELECT "
                + "id AS factura_id, "
                + "fecha_emision, "
                + "total, "
                + "estado "
                + "FROM facturas "
                + "WHERE fecha_emision >= ? "
                + "AND fecha_emision < DATE_ADD(?, INTERVAL 1 DAY) "
                + "ORDER BY id";

        return ejecutarConsulta(
                sql,
                Date.valueOf(fechaInicio),
                Date.valueOf(fechaFin)
        );
    }

    // Método general para ejecutar consultas
    private List<Map<String, Object>> ejecutarConsulta(
            String sql, Object... parametros) {

        List<Map<String, Object>> resultados = new ArrayList<>();

        Connection con = Conexion.getConnection();

        if (con == null) {
            System.out.println("No hay conexión con la base de datos.");
            return resultados;
        }

        try (Connection conexion = con;
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i + 1, parametros[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {

                ResultSetMetaData metadata = rs.getMetaData();
                int columnas = metadata.getColumnCount();

                while (rs.next()) {

                    Map<String, Object> fila = new LinkedHashMap<>();

                    for (int i = 1; i <= columnas; i++) {

                        String nombreColumna =
                                metadata.getColumnLabel(i);

                        Object valor = rs.getObject(i);

                        fila.put(nombreColumna, valor);
                    }

                    resultados.add(fila);
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al generar reporte: " + e.getMessage()
            );
        }

        return resultados;
    }
}
