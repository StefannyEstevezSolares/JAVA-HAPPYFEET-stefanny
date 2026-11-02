
package com.happyfeet.service;

import com.happyfeet.dao.ServicioDAO;
import com.happyfeet.model.Servicio;
import java.util.List;

public class ServicioService {

    private final ServicioDAO servicioDAO;

    public ServicioService() {
        this.servicioDAO = new ServicioDAO();
    }

    public boolean registrar(Servicio servicio) {

        if (servicio == null) {
            throw new IllegalArgumentException(
                "El servicio no puede estar vacío."
            );
        }

        return servicioDAO.registrar(servicio);
    }

    public Servicio buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return servicioDAO.buscarPorId(id);
    }

    public List<Servicio> listarTodos() {
        return servicioDAO.listarTodos();
    }

    public boolean actualizar(Servicio servicio) {

        if (servicio == null) {
            throw new IllegalArgumentException(
                "El servicio no puede estar vacío."
            );
        }

        return servicioDAO.actualizar(servicio);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return servicioDAO.eliminar(id);
    }
}
