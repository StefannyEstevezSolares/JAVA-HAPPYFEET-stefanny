
package com.happyfeet.main;

import com.happyfeet.dao.HistorialMedicoDAO;
import com.happyfeet.model.HistorialMedico;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

public class PruebaHisotrialMedicoDAO {

    public static void main(String[] args) {

        HistorialMedicoDAO dao = new HistorialMedicoDAO();

        // Crear historial de prueba
        HistorialMedico historial = new HistorialMedico();

        historial.setMascotaId(1);
        historial.setFechaEvento(LocalDate.now());
        historial.setEventoTipoId(1);
        historial.setDescripcion("Registro de prueba del historial medico");
        historial.setDiagnostico("Mascota en buen estado general");
        historial.setTratamientoRecomendado("Mantener controles veterinarios");
        historial.setVeterinarioId(1);
        historial.setConsultaId(null);
        historial.setProcedimientoId(null);

        // Registrar
        System.out.println("REGISTRAR HISTORIAL");

        if (!dao.registrar(historial)) {
            System.out.println("No se pudo registrar el historial.");
            return;
        }

        System.out.println("Historial registrado correctamente.");

        // Obtener el registro más reciente
        List<HistorialMedico> historiales = dao.listarTodos();

        if (historiales.isEmpty()) {
            System.out.println("No hay historiales registrados.");
            return;
        }

        HistorialMedico registrado = historiales.get(0);
        int id = registrado.getId();

        System.out.println("ID registrado: " + id);

        // Buscar
        System.out.println("\nBUSCAR HISTORIAL");

        HistorialMedico encontrado = dao.buscarPorId(id);

        if (encontrado != null) {
            System.out.println("Descripcion: " + encontrado.getDescripcion());
            System.out.println("Diagnostico: " + encontrado.getDiagnostico());
        }

        // Actualizar
        System.out.println("\nACTUALIZAR HISTORIAL");

        registrado.setDiagnostico("Revision completada: mascota estable");
        registrado.setTratamientoRecomendado(
                "Continuar con los cuidados habituales"
        );

        if (dao.actualizar(registrado)) {
            System.out.println("Historial actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el historial.");
        }

        // Listar
        System.out.println("\nLISTA DE HISTORIALES");

        historiales = dao.listarTodos();

        for (HistorialMedico h : historiales) {
            System.out.println(
                    "ID: " + h.getId()
                    + " | Mascota: " + h.getMascotaId()
                    + " | Fecha: " + h.getFechaEvento()
                    + " | Diagnostico: " + h.getDiagnostico()
            );
        }

        // Eliminar opcionalmente
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Deseas eliminar el historial de prueba?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            if (dao.eliminar(id)) {
                System.out.println("Historial eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el historial.");
            }

        } else {
            System.out.println("El historial de prueba se conservó.");
        }
    }
}
