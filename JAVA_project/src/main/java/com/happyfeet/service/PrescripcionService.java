
package com.happyfeet.service;

import com.happyfeet.dao.PrescripcionDAO;
import com.happyfeet.model.Prescripcion;
import java.util.List;

public class PrescripcionService {

    private final PrescripcionDAO prescripcionDAO;

    public PrescripcionService() {
        this.prescripcionDAO = new PrescripcionDAO();
    }

    public boolean registrar(Prescripcion prescripcion) {

        if (prescripcion == null) {
            throw new IllegalArgumentException(
                "La prescripción no puede estar vacía."
            );
        }

        return prescripcionDAO.registrar(prescripcion);
    }

    public Prescripcion buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return prescripcionDAO.buscarPorId(id);
    }

    public List<Prescripcion> listarTodos() {
        return prescripcionDAO.listarTodos();
    }

    public boolean actualizar(Prescripcion prescripcion) {

        if (prescripcion == null) {
            throw new IllegalArgumentException(
                "La prescripción no puede estar vacía."
            );
        }

        return prescripcionDAO.actualizar(prescripcion);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return prescripcionDAO.eliminar(id);
    }
}
