
package com.happyfeet.main;

import com.happyfeet.config.Conexion;
import com.happyfeet.dao.EventoTipoDAO;
import com.happyfeet.model.EventoTipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PruebaEventoTipoDAO {

    public static void main(String[] args) {

        EventoTipoDAO dao = new EventoTipoDAO();

        int eventoId = 0;
        String descripcion = "PRUEBA_EVENTO_" + System.currentTimeMillis();

        try {

            // REGISTRAR
            EventoTipo evento = new EventoTipo(
                    0,
                    "Evento de prueba",
                    descripcion
            );

            if (dao.registrar(evento)) {
                System.out.println("Tipo de evento registrado correctamente.");
            } else {
                System.out.println("Error al registrar tipo de evento.");
                return;
            }

            // OBTENER ID
            try (Connection con = Conexion.getConnection()) {

                String sql = "SELECT id FROM evento_tipos WHERE descripcion = ?";

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, descripcion);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            eventoId = rs.getInt("id");
                        }
                    }
                }
            }

            if (eventoId == 0) {
                System.out.println("No se pudo recuperar el ID.");
                return;
            }

            // BUSCAR
            EventoTipo encontrado = dao.buscarPorId(eventoId);

            System.out.println(encontrado != null
                    ? "Tipo de evento encontrado correctamente."
                    : "No se encontro el tipo de evento.");

            // ACTUALIZAR
            encontrado.setNombre("Evento actualizado");
            encontrado.setDescripcion(descripcion + "_ACTUALIZADO");

            System.out.println(dao.actualizar(encontrado)
                    ? "Tipo de evento actualizado correctamente."
                    : "Error al actualizar tipo de evento.");

            // LISTAR
            System.out.println("Total de tipos de eventos: "
                    + dao.listarTodos().size());

            // ELIMINAR
            System.out.println(dao.eliminar(eventoId)
                    ? "Tipo de evento eliminado correctamente."
                    : "Error al eliminar tipo de evento.");

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}
