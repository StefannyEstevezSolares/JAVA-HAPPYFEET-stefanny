
package com.happyfeet.main;

import com.happyfeet.config.Conexion;
import com.happyfeet.dao.MascotaAdopcionDAO;
import com.happyfeet.model.MascotaAdopcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class PruebaMascotaAdopcionDAO {

    public static void main(String[] args) {

        MascotaAdopcionDAO dao = new MascotaAdopcionDAO();

        try {
            // Buscar una mascota existente
            int mascotaId = 0;

            try (Connection con = Conexion.getConnection()) {

                String sql = "SELECT id FROM mascotas LIMIT 1";

                try (PreparedStatement ps = con.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        mascotaId = rs.getInt("id");
                    }
                }
            }

            if (mascotaId == 0) {
                System.out.println("No hay mascotas registradas.");
                return;
            }

            // Crear registro de adopción
            MascotaAdopcion mascota = new MascotaAdopcion();

            mascota.setMascotaId(mascotaId);
            mascota.setFechaIngreso(LocalDate.now());
            mascota.setMotivoIngreso("Ingreso de prueba");
            mascota.setEstado("Disponible");
            mascota.setHistoria("Registro para probar el DAO");
            mascota.setTemperamento("Tranquilo");
            mascota.setNecesidadesEspeciales("Ninguna");
            mascota.setFotoAdicionalUrl(null);

            // Registrar
            if (dao.registrar(mascota)) {

                System.out.println("Mascota registrada para adopción.");
                System.out.println("ID generado: " + mascota.getId());

                // Buscar
                MascotaAdopcion encontrada =
                        dao.buscarPorId(mascota.getId());

                if (encontrada != null) {
                    System.out.println("Mascota encontrada.");
                    System.out.println("Estado: " + encontrada.getEstado());
                }

                // Actualizar
                mascota.setHistoria("Historia actualizada");

                if (dao.actualizar(mascota)) {
                    System.out.println("Mascota actualizada correctamente.");
                }

                // Listar
                System.out.println("Total de registros: "
                        + dao.listarTodos().size());

                // Eliminar
                if (dao.eliminar(mascota.getId())) {
                    System.out.println("Registro de prueba eliminado.");
                }

            } else {
                System.out.println("No se pudo registrar la mascota.");
            }

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}
