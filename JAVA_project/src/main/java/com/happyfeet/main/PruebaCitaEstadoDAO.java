
package com.happyfeet.main;

import com.happyfeet.config.Conexion;
import com.happyfeet.dao.CitaEstadoDAO;
import com.happyfeet.model.CitaEstado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PruebaCitaEstadoDAO {

    public static void main(String[] args) {

        CitaEstadoDAO dao = new CitaEstadoDAO();

        int estadoId = 0;
        String descripcion = "PRUEBA_ESTADO_" + System.currentTimeMillis();

        // REGISTRAR
        CitaEstado estado = new CitaEstado(
                0,
                "Estado de prueba",
                descripcion
        );

        if (dao.registrar(estado)) {
            System.out.println("Estado registrado correctamente.");
        } else {
            System.out.println("Error al registrar estado.");
            return;
        }

        try (Connection con = Conexion.getConnection()) {

            // OBTENER ID DEL REGISTRO
            String sql = "SELECT id FROM cita_estados WHERE descripcion = ?";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, descripcion);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        estadoId = rs.getInt("id");
                    }
                }
            }

            if (estadoId == 0) {
                System.out.println("No se pudo recuperar el ID.");
                return;
            }

            // BUSCAR
            CitaEstado encontrado = dao.buscarPorId(estadoId);

            System.out.println(encontrado != null
                    ? "Estado encontrado correctamente."
                    : "No se encontro el estado.");

            // ACTUALIZAR
            encontrado.setNombre("Estado actualizado");
            encontrado.setDescripcion(descripcion + "_ACTUALIZADO");

            System.out.println(dao.actualizar(encontrado)
                    ? "Estado actualizado correctamente."
                    : "Error al actualizar estado.");

            // LISTAR
            System.out.println("Total de estados: " + dao.listarTodos().size());

            // ELIMINAR
            System.out.println(dao.eliminar(estadoId)
                    ? "Estado eliminado correctamente."
                    : "Error al eliminar estado.");

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}

