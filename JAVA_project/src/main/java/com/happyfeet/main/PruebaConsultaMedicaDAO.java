
package com.happyfeet.main;

import com.happyfeet.dao.ConsultaMedicaDAO;
import com.happyfeet.model.ConsultaMedica;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class PruebaConsultaMedicaDAO {

    public static void main(String[] args) {

        ConsultaMedicaDAO dao = new ConsultaMedicaDAO();

        try {

            // 1. REGISTRAR UNA CONSULTA MEDICA

            ConsultaMedica consulta = new ConsultaMedica();

            consulta.setMascotaId(1);
            consulta.setVeterinarioId(1);
            consulta.setCitaId(null);
            consulta.setFechaHora(LocalDateTime.now());
            consulta.setMotivo("Revision general de salud");
            consulta.setSintomas("No presenta sintomas graves.");
            consulta.setDiagnostico("Mascota en buen estado general.");
            consulta.setRecomendaciones("Mantener controles veterinarios.");
            consulta.setObservaciones("Consulta de prueba del sistema.");
            consulta.setPesoRegistrado(new BigDecimal("13.00"));
            consulta.setTemperatura(new BigDecimal("38.50"));

            int idGenerado = dao.registrar(consulta);

            JOptionPane.showMessageDialog(null,
                    "CONSULTA REGISTRADA CORRECTAMENTE\n"
                    + "ID generado: " + idGenerado);

            // 2. BUSCAR POR ID

            ConsultaMedica encontrada = dao.buscarPorId(idGenerado);

            if (encontrada != null) {

                JOptionPane.showMessageDialog(null,
                        "CONSULTA ENCONTRADA\n\n"
                        + "ID: " + encontrada.getId()
                        + "\nMascota ID: " + encontrada.getMascotaId()
                        + "\nVeterinario ID: " + encontrada.getVeterinarioId()
                        + "\nMotivo: " + encontrada.getMotivo()
                        + "\nDiagnostico: " + encontrada.getDiagnostico()
                        + "\nPeso: " + encontrada.getPesoRegistrado()
                        + "\nTemperatura: " + encontrada.getTemperatura());

                // 3. ACTUALIZAR

                encontrada.setDiagnostico(
                        "Estado estable. Sin complicaciones.");
                encontrada.setRecomendaciones(
                        "Continuar con alimentacion balanceada.");

                boolean actualizado = dao.actualizar(encontrada);

                JOptionPane.showMessageDialog(null,
                        actualizado
                        ? "CONSULTA ACTUALIZADA CORRECTAMENTE"
                        : "NO SE PUDO ACTUALIZAR");

            } else {

                JOptionPane.showMessageDialog(null,
                        "No se encontro la consulta.");
            }

            // 4. LISTAR TODAS LAS CONSULTAS

            List<ConsultaMedica> consultas = dao.listarTodos();

            String listado = "";

            for (ConsultaMedica c : consultas) {

                listado += "\nID: " + c.getId()
                        + " | Mascota: " + c.getMascotaId()
                        + " | Motivo: " + c.getMotivo()
                        + " | Fecha: " + c.getFechaHora();
            }

            JOptionPane.showMessageDialog(null,
                    "LISTADO DE CONSULTAS\n"
                    + (listado.isEmpty()
                    ? "No hay consultas registradas."
                    : listado));

            // 5. ELIMINAR CONSULTA DE PRUEBA

            int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Deseas eliminar la consulta de prueba?",
                    "Confirmar eliminacion",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                boolean eliminado = dao.eliminar(idGenerado);

                JOptionPane.showMessageDialog(null,
                        eliminado
                        ? "CONSULTA ELIMINADA CORRECTAMENTE"
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
