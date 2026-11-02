
package com.happyfeet.service;

import com.happyfeet.dao.AdopcionDAO;
import com.happyfeet.model.Adopcion;
import java.util.List;

public class AdopcionService {

    private final AdopcionDAO adopcionDAO;

    public AdopcionService() {
        this.adopcionDAO = new AdopcionDAO();
    }

    public boolean registrar(Adopcion adopcion) {

        if (adopcion == null) {
            throw new IllegalArgumentException(
                "La adopción no puede estar vacía."
            );
        }

        return adopcionDAO.registrar(adopcion);
    }

    public Adopcion buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return adopcionDAO.buscarPorId(id);
    }

    public List<Adopcion> listarTodos() {
        return adopcionDAO.listarTodos();
    }

    public boolean actualizar(Adopcion adopcion) {

        if (adopcion == null) {
            throw new IllegalArgumentException(
                "La adopción no puede estar vacía."
            );
        }

        return adopcionDAO.actualizar(adopcion);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return adopcionDAO.eliminar(id);
    }
}
