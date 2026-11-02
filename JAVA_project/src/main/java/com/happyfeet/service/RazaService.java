
package com.happyfeet.service;

import com.happyfeet.dao.RazaDAO;
import com.happyfeet.model.Raza;
import java.util.List;

public class RazaService {

    private final RazaDAO razaDAO;

    public RazaService() {
        this.razaDAO = new RazaDAO();
    }

    public boolean registrar(Raza raza) {
        if (raza == null) {
            throw new IllegalArgumentException(
                    "La raza no puede estar vacía."
            );
        }

        return razaDAO.registrar(raza);
    }

    public Raza buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return razaDAO.buscarPorId(id);
    }

    public List<Raza> listarTodos() {
        return razaDAO.listarTodos();
    }

    public boolean actualizar(Raza raza) {
        if (raza == null) {
            throw new IllegalArgumentException(
                    "La raza no puede estar vacía."
            );
        }

        if (raza.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID de la raza debe ser válido."
            );
        }

        return razaDAO.actualizar(raza);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return razaDAO.eliminar(id);
    }
}
