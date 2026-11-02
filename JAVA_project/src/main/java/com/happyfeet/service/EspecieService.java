
package com.happyfeet.service;

import com.happyfeet.dao.EspecieDAO;
import com.happyfeet.model.Especie;
import java.util.List;

public class EspecieService {

    private final EspecieDAO especieDAO;

    public EspecieService() {
        this.especieDAO = new EspecieDAO();
    }

    public boolean registrar(Especie especie) {
        if (especie == null) {
            throw new IllegalArgumentException(
                    "La especie no puede estar vacía."
            );
        }

        return especieDAO.registrar(especie);
    }

    public Especie buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return especieDAO.buscarPorId(id);
    }

    public List<Especie> listarTodos() {
        return especieDAO.listarTodos();
    }

    public boolean actualizar(Especie especie) {
        if (especie == null) {
            throw new IllegalArgumentException(
                    "La especie no puede estar vacía."
            );
        }

        if (especie.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID de la especie debe ser válido."
            );
        }

        return especieDAO.actualizar(especie);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return especieDAO.eliminar(id);
    }
}
