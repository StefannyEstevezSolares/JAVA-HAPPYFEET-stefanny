
package com.happyfeet.main;

import com.happyfeet.config.Conexion;
import com.happyfeet.dao.ClubMascotasDAO;
import com.happyfeet.model.ClubMascotas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PruebaClubMascotasDAO {

    public static void main(String[] args) {

        ClubMascotasDAO dao = new ClubMascotasDAO();

        int duenoId = 0;
        int clubId = 0;

        try (Connection con = Conexion.getConnection()) {

            // BUSCAR UN DUENO EXISTENTE
            String sqlDueno = "SELECT id FROM duenos LIMIT 1";

            try (PreparedStatement ps = con.prepareStatement(sqlDueno);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    duenoId = rs.getInt("id");
                }
            }

            if (duenoId == 0) {
                System.out.println("No hay duenos registrados para realizar la prueba.");
                return;
            }

            // CREAR REGISTRO DE PRUEBA
            ClubMascotas club = new ClubMascotas(
                    0,
                    duenoId,
                    100,
                    20,
                    80,
                    "Bronce",
                    LocalDate.now(),
                    LocalDateTime.now(),
                    true
            );

            // REGISTRAR
            if (dao.registrar(club)) {
                System.out.println("Club registrado correctamente.");
            } else {
                System.out.println("Error al registrar club.");
                return;
            }

            // OBTENER ID DEL REGISTRO
            String sqlId = "SELECT id FROM club_mascotas WHERE dueno_id = ? "
                    + "ORDER BY id DESC LIMIT 1";

            try (PreparedStatement ps = con.prepareStatement(sqlId)) {
                ps.setInt(1, duenoId);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        clubId = rs.getInt("id");
                    }
                }
            }

            if (clubId == 0) {
                System.out.println("No se pudo recuperar el ID del club.");
                return;
            }

            // BUSCAR
            ClubMascotas encontrado = dao.buscarPorId(clubId);

            System.out.println(encontrado != null
                    ? "Club encontrado correctamente."
                    : "No se encontro el club.");

            // ACTUALIZAR
            encontrado.setPuntosAcumulados(150);
            encontrado.setPuntosDisponibles(130);
            encontrado.setFechaUltimaActualizacion(LocalDateTime.now());

            System.out.println(dao.actualizar(encontrado)
                    ? "Club actualizado correctamente."
                    : "Error al actualizar club.");

            // LISTAR
            System.out.println("Total de registros: " + dao.listarTodos().size());

            // ELIMINAR
            System.out.println(dao.eliminar(clubId)
                    ? "Club eliminado correctamente."
                    : "Error al eliminar club.");

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}
