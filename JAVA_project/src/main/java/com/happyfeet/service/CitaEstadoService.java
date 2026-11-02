
package com.happyfeet.service;

import com.happyfeet.dao.CitaEstadoDAO;
import com.happyfeet.model.CitaEstado;
import java.util.List;

public class CitaEstadoService {

    private final CitaEstadoDAO estadoDAO;

    public CitaEstadoService() {
        this.estadoDAO = new CitaEstadoDAO();
    }

    public boolean registrar(CitaEstado estado) {
        if (estado == null) {
            throw new IllegalArgumentException(
                    "El estado de la cita no puede estar vacío."
            );
        }

        return estadoDAO.registrar(estado);
    }

    public CitaEstado buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return estadoDAO.buscarPorId(id);
    }

    public List<CitaEstado> listarTodos() {
        return estadoDAO.listarTodos();
    }

    public boolean actualizar(CitaEstado estado) {
        if (estado == null) {
            throw new IllegalArgumentException(
                    "El estado de la cita no puede estar vacío."
            );
        }

        if (estado.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID del estado debe ser válido."
            );
        }

        return estadoDAO.actualizar(estado);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return estadoDAO.eliminar(id);
    }
}
