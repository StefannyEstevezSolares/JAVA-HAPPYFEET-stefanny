
package com.happyfeet.notificaciones;

import com.happyfeet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlertaInventario implements ObservadorInventario {

    @Override
    public void actualizar(String mensaje, int productoId, String tipoAlerta) {

        String sql = "INSERT INTO alertas_inventario "
                + "(producto_id, tipo_alerta, mensaje, estado) "
                + "VALUES (?, ?, ?, 'Pendiente')";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productoId);
            ps.setString(2, tipoAlerta);
            ps.setString(3, mensaje);

            ps.executeUpdate();

            System.out.println("Alerta de inventario registrada: " + mensaje);

        } catch (SQLException e) {
            System.out.println("Error al registrar alerta: " + e.getMessage());
        }
    }
}
