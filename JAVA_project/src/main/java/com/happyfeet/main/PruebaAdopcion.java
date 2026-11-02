
package com.happyfeet.main;

import com.happyfeet.config.Conexion;
import com.happyfeet.dao.AdopcionDAO;
import com.happyfeet.model.Adopcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class PruebaAdopcion {

    public static void main(String[] args) {

        AdopcionDAO dao = new AdopcionDAO();
        int mascotaAdopcionId = 0;
        int duenoId = 0;
        int adopcionId = 0;

        String contrato = "PRUEBA_ADOPCION_" + System.currentTimeMillis();

        try (Connection con = Conexion.getConnection()) {

            String sqlMascota = "SELECT id FROM mascotas_adopcion LIMIT 1";

            try (PreparedStatement ps = con.prepareStatement(sqlMascota);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    mascotaAdopcionId = rs.getInt("id");
                }
            }

            String sqlDueno = "SELECT id FROM duenos LIMIT 1";

            try (PreparedStatement ps = con.prepareStatement(sqlDueno);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    duenoId = rs.getInt("id");
                }
            }

            if (mascotaAdopcionId == 0 || duenoId == 0) {
                System.out.println("No hay mascotas en adopcion o duenos registrados.");
                return;
            }

            Adopcion adopcion = new Adopcion(
                    0,
                    mascotaAdopcionId,
                    duenoId,
                    LocalDate.now(),
                    contrato,
                    "Condiciones de prueba",
                    true,
                    LocalDate.now().plusDays(30)
            );

            // REGISTRAR
            if (dao.registrar(adopcion)) {
                System.out.println("Adopcion registrada correctamente.");
            } else {
                System.out.println("Error al registrar adopcion.");
                return;
            }

            // BUSCAR EL ID DEL REGISTRO
            String sqlId = "SELECT id FROM adopciones WHERE contrato_texto = ?";

            try (PreparedStatement ps = con.prepareStatement(sqlId)) {
                ps.setString(1, contrato);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        adopcionId = rs.getInt("id");
                    }
                }
            }

            // BUSCAR POR ID
            Adopcion encontrada = dao.buscarPorId(adopcionId);

            System.out.println(encontrada != null
                    ? "Adopcion encontrada correctamente."
                    : "No se encontro la adopcion.");

            // ACTUALIZAR
            encontrada.setCondicionesEspeciales("Condiciones actualizadas");

            System.out.println(dao.actualizar(encontrada)
                    ? "Adopcion actualizada correctamente."
                    : "Error al actualizar adopcion.");

            // LISTAR
            System.out.println("Total de adopciones: " + dao.listarTodos().size());

            // ELIMINAR
            System.out.println(dao.eliminar(adopcionId)
                    ? "Adopcion eliminada correctamente."
                    : "Error al eliminar adopcion.");

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}
