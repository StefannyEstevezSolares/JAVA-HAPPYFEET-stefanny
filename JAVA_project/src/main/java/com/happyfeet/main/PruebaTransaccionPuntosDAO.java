
package com.happyfeet.main;

import com.happyfeet.config.Conexion;
import com.happyfeet.dao.TransaccionPuntosDAO;
import com.happyfeet.model.TransaccionPuntos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class PruebaTransaccionPuntosDAO {

    public static void main(String[] args) {

        TransaccionPuntosDAO dao = new TransaccionPuntosDAO();

        int clubMascotasId = 0;
        int transaccionId = 0;

        String descripcion = "PRUEBA_TRANSACCION_" + System.currentTimeMillis();

        try (Connection con = Conexion.getConnection()) {

            // BUSCAR UN CLUB EXISTENTE
            String sqlClub = "SELECT id FROM club_mascotas LIMIT 1";

            try (PreparedStatement ps = con.prepareStatement(sqlClub);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    clubMascotasId = rs.getInt("id");
                }
            }

            if (clubMascotasId == 0) {
                System.out.println("No hay registros en el Club de Mascotas.");
                return;
            }

            // CREAR TRANSACCION DE PRUEBA
            TransaccionPuntos transaccion = new TransaccionPuntos(
                    0,
                    clubMascotasId,
                    null,
                    50,
                    "Acumulacion",
                    LocalDateTime.now(),
                    descripcion,
                    100,
                    150
            );

            // REGISTRAR
            if (dao.registrar(transaccion)) {
                System.out.println("Transaccion registrada correctamente.");
            } else {
                System.out.println("Error al registrar transaccion.");
                return;
            }

            // OBTENER ID
            String sqlId = "SELECT id FROM transacciones_puntos "
                    + "WHERE descripcion = ? ORDER BY id DESC LIMIT 1";

            try (PreparedStatement ps = con.prepareStatement(sqlId)) {
                ps.setString(1, descripcion);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        transaccionId = rs.getInt("id");
                    }
                }
            }

            if (transaccionId == 0) {
                System.out.println("No se pudo recuperar el ID.");
                return;
            }

            // BUSCAR
            TransaccionPuntos encontrada = dao.buscarPorId(transaccionId);

            System.out.println(encontrada != null
                    ? "Transaccion encontrada correctamente."
                    : "No se encontro la transaccion.");

            // ACTUALIZAR
            encontrada.setPuntos(75);
            encontrada.setSaldoNuevo(175);
            encontrada.setDescripcion(descripcion + "_ACTUALIZADA");

            System.out.println(dao.actualizar(encontrada)
                    ? "Transaccion actualizada correctamente."
                    : "Error al actualizar transaccion.");

            // LISTAR
            System.out.println("Total de transacciones: " + dao.listarTodos().size());

            // ELIMINAR
            System.out.println(dao.eliminar(transaccionId)
                    ? "Transaccion eliminada correctamente."
                    : "Error al eliminar transaccion.");

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}
