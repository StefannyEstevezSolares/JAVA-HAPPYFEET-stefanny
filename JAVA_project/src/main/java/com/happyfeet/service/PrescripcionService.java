
package com.happyfeet.service;

import com.happyfeet.dao.PrescripcionDAO;
import com.happyfeet.dao.InventarioDAO;
import com.happyfeet.model.Prescripcion;
import com.happyfeet.model.Inventario;
import java.time.LocalDate;
import java.util.List;

public class PrescripcionService {

    private final PrescripcionDAO prescripcionDAO;
    private final InventarioDAO inventarioDAO;

    public PrescripcionService() {
        this.prescripcionDAO = new PrescripcionDAO();
        this.inventarioDAO = new InventarioDAO();
    }

    // Registrar prescripción
    public boolean registrar(Prescripcion prescripcion) {

        if (prescripcion == null) {
            throw new IllegalArgumentException(
                    "La prescripción no puede estar vacía."
            );
        }

        // INICIO: VALIDACIÓN DEL PRODUCTO
        Inventario producto = inventarioDAO.buscarPorId(
                prescripcion.getProductoId()
        );

        if (producto == null) {
            System.out.println("Error: el producto no existe.");
            return false;
        }

        if (!Boolean.TRUE.equals(producto.getActivo())) {
            System.out.println("Error: el producto está inactivo.");
            return false;
        }

        if (producto.getFechaVencimiento() != null
                && producto.getFechaVencimiento().isBefore(LocalDate.now())) {

            System.out.println("Error: no se puede prescribir un producto vencido.");
            return false;
        }

        if (producto.getCantidadStock() < prescripcion.getCantidad()) {
            System.out.println("Error: no hay suficientes existencias.");
            return false;
        }
        // FIN: VALIDACIÓN DEL PRODUCTO

        return prescripcionDAO.registrar(prescripcion);
    }

    // Buscar prescripción por ID
    public Prescripcion buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser válido.");
        }

        return prescripcionDAO.buscarPorId(id);
    }

    // Listar todas las prescripciones
    public List<Prescripcion> listarTodos() {
        return prescripcionDAO.listarTodos();
    }

    // Actualizar prescripción
    public boolean actualizar(Prescripcion prescripcion) {

        if (prescripcion == null) {
            throw new IllegalArgumentException(
                    "La prescripción no puede estar vacía."
            );
        }

        return prescripcionDAO.actualizar(prescripcion);
    }

    // Eliminar prescripción
    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser válido.");
        }

        return prescripcionDAO.eliminar(id);
    }
}
