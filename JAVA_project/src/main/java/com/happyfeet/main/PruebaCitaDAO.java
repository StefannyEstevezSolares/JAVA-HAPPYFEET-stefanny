
package com.happyfeet.main;

import com.happyfeet.dao.CitaDAO;
import com.happyfeet.model.Cita;

import java.time.LocalDateTime;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class PruebaCitaDAO {

    public static void main(String[] args) {

        CitaDAO dao = new CitaDAO();

        try {

            // 1. REGISTRAR UNA CITA

            Cita cita = new Cita();

            cita.setMascotaId(1);
            cita.setVeterinarioId(null);
            cita.setFechaHora(LocalDateTime.now().plusDays(1));
            cita.setMotivo("Consulta general de prueba");
            cita.setEstadoId(1);
            cita.setObservaciones("Cita registrada para probar el DAO.");

            int idGenerado = dao.registrar(cita);

            JOptionPane.showMessageDialog(null,
                    "CITA REGISTRADA CORRECTAMENTE\n"
                    + "ID generado: " + idGenerado);

            // 2. BUSCAR POR ID

            Cita encontrada = dao.buscarPorId(idGenerado);

            if (encontrada != null) {

                JOptionPane.showMessageDialog(null,
                        "CITA ENCONTRADA\n\n"
                        + "ID: " + encontrada.getId()
                        + "\nMascota ID: " + encontrada.getMascotaId()
                        + "\nVeterinario ID: " + encontrada.getVeterinarioId()
                        + "\nFecha y hora: " + encontrada.getFechaHora()
                        + "\nMotivo: " + encontrada.getMotivo()
                        + "\nEstado ID: " + encontrada.getEstadoId());
            }

            // 3. ACTUALIZAR

            encontrada.setMotivo("Consulta general y revisión de peso");
            encontrada.setObservaciones("Cita actualizada correctamente.");

            boolean actualizado = dao.actualizar(encontrada);

            JOptionPane.showMessageDialog(null,
                    actualizado
                    ? "CITA ACTUALIZADA CORRECTAMENTE"
                    : "NO SE PUDO ACTUALIZAR");

            // 4. LISTAR TODAS LAS CITAS

            List<Cita> citas = dao.listarTodos();

            String listado = "";

            for (Cita c : citas) {

                listado += "\nID: " + c.getId()
                        + " | Mascota: " + c.getMascotaId()
                        + " | Fecha: " + c.getFechaHora()
                        + " | Motivo: " + c.getMotivo();
            }

            JOptionPane.showMessageDialog(null,
                    "LISTADO DE CITAS\n"
                    + (listado.isEmpty()
                    ? "No hay citas registradas."
                    : listado));

            // 5. ELIMINAR LA CITA DE PRUEBA

            int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Deseas eliminar la cita de prueba?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                boolean eliminado = dao.eliminar(idGenerado);

                JOptionPane.showMessageDialog(null,
                        eliminado
                        ? "CITA ELIMINADA CORRECTAMENTE"
                        : "NO SE PUDO ELIMINAR");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "ERROR EN LA BASE DE DATOS:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

