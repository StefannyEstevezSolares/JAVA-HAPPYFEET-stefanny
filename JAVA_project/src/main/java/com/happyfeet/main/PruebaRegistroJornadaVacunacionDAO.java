
package com.happyfeet.main;

import com.happyfeet.config.Conexion;
import com.happyfeet.dao.RegistroJornadaVacunacionDAO;
import com.happyfeet.model.RegistroJornadaVacunacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PruebaRegistroJornadaVacunacionDAO {

    public static void main(String[] args) {

        RegistroJornadaVacunacionDAO dao =
                new RegistroJornadaVacunacionDAO();

        try {
            int jornadaId = 0;
            int mascotaId = 0;
            int duenoId = 0;
            int vacunaId = 0;

            // Buscar IDs existentes
            try (Connection con = Conexion.getConnection()) {

                String sqlJornada =
                        "SELECT id FROM jornadas_vacunacion LIMIT 1";

                try (PreparedStatement ps = con.prepareStatement(sqlJornada);
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        jornadaId = rs.getInt("id");
                    }
                }

                String sqlMascota =
                        "SELECT id, dueno_id FROM mascotas LIMIT 1";

                try (PreparedStatement ps = con.prepareStatement(sqlMascota);
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        mascotaId = rs.getInt("id");
                        duenoId = rs.getInt("dueno_id");
                    }
                }

                String sqlVacuna =
                        "SELECT id FROM inventario LIMIT 1";

                try (PreparedStatement ps = con.prepareStatement(sqlVacuna);
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        vacunaId = rs.getInt("id");
                    }
                }
            }

            if (jornadaId == 0 || mascotaId == 0
                    || duenoId == 0 || vacunaId == 0) {

                System.out.println(
                        "Faltan jornadas, mascotas o productos en inventario.");
                return;
            }

            // Crear registro de prueba
            RegistroJornadaVacunacion registro =
                    new RegistroJornadaVacunacion();

            registro.setJornadaId(jornadaId);
            registro.setMascotaId(mascotaId);
            registro.setDuenoId(duenoId);
            registro.setVacunaId(vacunaId);
            registro.setVeterinarioId(null);
            registro.setFechaHora(LocalDateTime.now());
            registro.setLoteVacuna("LOTE-PRUEBA-001");
            registro.setProximaDosis(LocalDate.now().plusMonths(1));
            registro.setObservaciones("Registro de prueba del DAO");

            // Registrar
            if (dao.registrar(registro)) {

                System.out.println("Registro creado correctamente.");
                System.out.println("ID generado: " + registro.getId());

                // Buscar
                RegistroJornadaVacunacion encontrado =
                        dao.buscarPorId(registro.getId());

                if (encontrado != null) {
                    System.out.println("Registro encontrado.");
                    System.out.println("Lote: "
                            + encontrado.getLoteVacuna());
                }

                // Actualizar
                registro.setObservaciones("Registro actualizado");

                if (dao.actualizar(registro)) {
                    System.out.println("Registro actualizado correctamente.");
                }

                // Listar
                System.out.println("Total de registros: "
                        + dao.listarTodos().size());

                // Eliminar
                if (dao.eliminar(registro.getId())) {
                    System.out.println("Registro eliminado correctamente.");
                }

            } else {
                System.out.println("No se pudo registrar el registro.");
            }

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}
