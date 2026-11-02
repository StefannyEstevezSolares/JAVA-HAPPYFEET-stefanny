
package com.happyfeet.main;

import com.happyfeet.config.Conexion;
import com.happyfeet.dao.CanjeBeneficioDAO;
import com.happyfeet.model.CanjeBeneficio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PruebaCanjeBeneficioDAO {

    public static void main(String[] args) {

        CanjeBeneficioDAO dao = new CanjeBeneficioDAO();

        try {
            int clubMascotasId = 0;
            int beneficioId = 0;

            // Buscar un registro existente del club
            try (Connection con = Conexion.getConnection()) {

                String sqlClub = "SELECT id FROM club_mascotas LIMIT 1";

                try (PreparedStatement ps = con.prepareStatement(sqlClub);
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        clubMascotasId = rs.getInt("id");
                    }
                }

                // Buscar un beneficio existente
                String sqlBeneficio =
                        "SELECT id FROM beneficios_club LIMIT 1";

                try (PreparedStatement ps = con.prepareStatement(sqlBeneficio);
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        beneficioId = rs.getInt("id");
                    }
                }
            }

            if (clubMascotasId == 0 || beneficioId == 0) {
                System.out.println(
                        "No hay registros del club o beneficios disponibles.");
                return;
            }

            // Crear canje de prueba
            CanjeBeneficio canje = new CanjeBeneficio();

            canje.setClubMascotasId(clubMascotasId);
            canje.setBeneficioId(beneficioId);
            canje.setFechaCanje(LocalDateTime.now());
            canje.setPuntosCanjeados(100);
            canje.setEstado("Pendiente");
            canje.setFechaExpiracion(LocalDate.now().plusDays(30));
            canje.setFacturaId(null);

            // Registrar canje
            if (dao.registrar(canje)) {

                System.out.println("Canje registrado correctamente.");
                System.out.println("ID generado: " + canje.getId());

                // Buscar canje
                CanjeBeneficio encontrado =
                        dao.buscarPorId(canje.getId());

                if (encontrado != null) {
                    System.out.println("Canje encontrado: "
                            + encontrado.getEstado());
                }

                // Actualizar canje
                canje.setEstado("Utilizado");

                if (dao.actualizar(canje)) {
                    System.out.println("Canje actualizado correctamente.");
                }

                // Listar canjes
                System.out.println("Total de canjes: "
                        + dao.listarTodos().size());

                // Eliminar canje de prueba
                if (dao.eliminar(canje.getId())) {
                    System.out.println("Canje eliminado correctamente.");
                }

            } else {
                System.out.println("No se pudo registrar el canje.");
            }

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}
