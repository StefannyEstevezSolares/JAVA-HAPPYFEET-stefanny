
package com.happyfeet.test;

import com.happyfeet.dao.PrescripcionDAO;
import com.happyfeet.model.Prescripcion;
import java.util.List;

public class PruebaPrescripcionDAO {

    public static void main(String[] args) {

        PrescripcionDAO dao = new PrescripcionDAO();

        // 1. Crear prescripción de prueba
        Prescripcion prescripcion = new Prescripcion();

        prescripcion.setConsultaId(null);
        prescripcion.setProcedimientoId(1);
        prescripcion.setProductoId(1);
        prescripcion.setCantidad(2);
        prescripcion.setDosis("2 unidades");
        prescripcion.setFrecuencia("Cada 12 horas");
        prescripcion.setDuracionDias(5);
        prescripcion.setInstrucciones("Administrar después de los alimentos.");

        // 2. Registrar
        System.out.println("===== REGISTRAR PRESCRIPCIÓN =====");

        if (dao.registrar(prescripcion)) {

            int id = prescripcion.getId();

            System.out.println("Prescripción registrada correctamente.");
            System.out.println("ID generado: " + id);

            // 3. Buscar por ID
            System.out.println("\n===== BUSCAR PRESCRIPCIÓN =====");

            Prescripcion encontrada = dao.buscarPorId(id);

            if (encontrada != null) {
                System.out.println("ID: " + encontrada.getId());
                System.out.println("Producto ID: " + encontrada.getProductoId());
                System.out.println("Cantidad: " + encontrada.getCantidad());
                System.out.println("Dosis: " + encontrada.getDosis());
                System.out.println("Frecuencia: " + encontrada.getFrecuencia());
            }

            // 4. Actualizar
            System.out.println("\n===== ACTUALIZAR PRESCRIPCIÓN =====");

            prescripcion.setFrecuencia("Cada 8 horas");
            prescripcion.setInstrucciones(
                    "Administrar después de los alimentos y mantener hidratación."
            );

            if (dao.actualizar(prescripcion)) {
                System.out.println("Prescripción actualizada correctamente.");
            } else {
                System.out.println("No se pudo actualizar.");
            }

            // 5. Listar todas
            System.out.println("\n===== LISTAR PRESCRIPCIONES =====");

            List<Prescripcion> lista = dao.listarTodos();

            for (Prescripcion p : lista) {
                System.out.println(
                        "ID: " + p.getId()
                        + " | Producto: " + p.getProductoId()
                        + " | Dosis: " + p.getDosis()
                        + " | Frecuencia: " + p.getFrecuencia()
                );
            }

            // 6. Eliminar registro de prueba
            System.out.println("\n===== ELIMINAR PRESCRIPCIÓN =====");

            if (dao.eliminar(id)) {
                System.out.println("Prescripción eliminada correctamente.");
            } else {
                System.out.println("No se pudo eliminar.");
            }

        } else {
            System.out.println("No se pudo registrar la prescripción.");
        }
    }
}
