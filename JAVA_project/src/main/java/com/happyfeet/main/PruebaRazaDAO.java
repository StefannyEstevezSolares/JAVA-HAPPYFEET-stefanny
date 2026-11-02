
package com.happyfeet.main;

import com.happyfeet.dao.RazaDAO;
import com.happyfeet.model.Raza;
import com.happyfeet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PruebaRazaDAO {

    public static void main(String[] args) {

        RazaDAO dao = new RazaDAO();

        try {
            // Buscar una especie existente
            int especieId = 0;

            try (Connection con = Conexion.getConnection()) {

                String sql = "SELECT id FROM especies LIMIT 1";

                try (PreparedStatement ps = con.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        especieId = rs.getInt("id");
                    }
                }
            }

            if (especieId == 0) {
                System.out.println("No hay especies registradas.");
                return;
            }

            // Crear raza de prueba
            Raza raza = new Raza();

            raza.setEspecieId(especieId);
            raza.setNombre("Raza de prueba");
            raza.setCaracteristicas("Registro para probar el DAO");

            // Registrar raza
            if (dao.registrar(raza)) {

                System.out.println("Raza registrada correctamente.");
                System.out.println("ID generado: " + raza.getId());

                // Buscar raza
                Raza encontrada = dao.buscarPorId(raza.getId());

                if (encontrada != null) {
                    System.out.println("Raza encontrada: "
                            + encontrada.getNombre());
                }

                // Actualizar raza
                raza.setNombre("Raza actualizada");
                raza.setCaracteristicas("Descripción actualizada");

                if (dao.actualizar(raza)) {
                    System.out.println("Raza actualizada correctamente.");
                }

                // Listar razas
                System.out.println("Total de razas: "
                        + dao.listarTodos().size());

                // Eliminar raza de prueba
                if (dao.eliminar(raza.getId())) {
                    System.out.println("Raza eliminada correctamente.");
                }

            } else {
                System.out.println("No se pudo registrar la raza.");
            }

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}

