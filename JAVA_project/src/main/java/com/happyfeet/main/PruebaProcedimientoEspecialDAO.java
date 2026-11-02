
package com.happyfeet.main;

import com.happyfeet.dao.ProcedimientoEspecialDAO;
import com.happyfeet.model.ProcedimientoEspecial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;

public class PruebaProcedimientoEspecialDAO {

    public static void main(String[] args) {

        ProcedimientoEspecialDAO dao = new ProcedimientoEspecialDAO();

        // Crear procedimiento de prueba
        ProcedimientoEspecial procedimiento = new ProcedimientoEspecial();

        procedimiento.setMascotaId(1);
        procedimiento.setVeterinarioId(1);
        procedimiento.setTipoProcedimiento("Revision");
        procedimiento.setNombreProcedimiento("Revision veterinaria de prueba");
        procedimiento.setFechaHora(LocalDateTime.now().plusDays(1));
        procedimiento.setDuracionEstimadaMinutos(30);
        procedimiento.setInformacionPreoperatoria("Ayuno no requerido");
        procedimiento.setDetalleProcedimiento("Revision general de prueba");
        procedimiento.setComplicaciones("Ninguna");
        procedimiento.setSeguimientoPostoperatorio("Control segun evolucion");
        procedimiento.setProximoControl(LocalDate.now().plusDays(15));
        procedimiento.setEstado("Programado");
        procedimiento.setCostoProcedimiento(new BigDecimal("250.00"));

        // Registrar
        System.out.println("REGISTRAR PROCEDIMIENTO");

        if (dao.registrar(procedimiento)) {
            System.out.println("Procedimiento registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el procedimiento.");
            return;
        }

        // Obtener el registro más reciente
        List<ProcedimientoEspecial> procedimientos = dao.listarTodos();

        if (procedimientos.isEmpty()) {
            System.out.println("No hay procedimientos registrados.");
            return;
        }

        ProcedimientoEspecial registrado = procedimientos.get(0);
        int id = registrado.getId();

        System.out.println("ID registrado: " + id);

        // Buscar
        System.out.println("\nBUSCAR PROCEDIMIENTO");

        ProcedimientoEspecial encontrado = dao.buscarPorId(id);

        if (encontrado != null) {
            System.out.println("Nombre: " + encontrado.getNombreProcedimiento());
            System.out.println("Estado: " + encontrado.getEstado());
            System.out.println("Costo: " + encontrado.getCostoProcedimiento());
        }

        // Actualizar
        System.out.println("\nACTUALIZAR PROCEDIMIENTO");

        registrado.setEstado("Finalizado");
        registrado.setDetalleProcedimiento(
                "Revision general completada correctamente"
        );

        if (dao.actualizar(registrado)) {
            System.out.println("Procedimiento actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el procedimiento.");
        }

        // Listar
        System.out.println("\nLISTA DE PROCEDIMIENTOS");

        procedimientos = dao.listarTodos();

        for (ProcedimientoEspecial p : procedimientos) {
            System.out.println(
                    "ID: " + p.getId()
                    + " | Nombre: " + p.getNombreProcedimiento()
                    + " | Estado: " + p.getEstado()
            );
        }

        // Eliminar opcionalmente
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Deseas eliminar el procedimiento de prueba?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            if (dao.eliminar(id)) {
                System.out.println("Procedimiento eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el procedimiento.");
            }

        } else {
            System.out.println("El procedimiento de prueba se conservó.");
        }
    }
}
