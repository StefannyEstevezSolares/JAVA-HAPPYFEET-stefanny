
package com.happyfeet.service;

import com.happyfeet.dao.EventoTipoDAO;
import com.happyfeet.model.EventoTipo;
import java.util.List;

public class EventoTipoService {

    private final EventoTipoDAO eventoDAO;

    public EventoTipoService() {
        this.eventoDAO = new EventoTipoDAO();
    }

    public boolean registrar(EventoTipo evento) {
        if (evento == null) {
            throw new IllegalArgumentException(
                    "El tipo de evento no puede estar vacío."
            );
        }

        return eventoDAO.registrar(evento);
    }

    public EventoTipo buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return eventoDAO.buscarPorId(id);
    }

    public List<EventoTipo> listarTodos() {
        return eventoDAO.listarTodos();
    }

    public boolean actualizar(EventoTipo evento) {
        if (evento == null) {
            throw new IllegalArgumentException(
                    "El tipo de evento no puede estar vacío."
            );
        }

        if (evento.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID del tipo de evento debe ser válido."
            );
        }

        return eventoDAO.actualizar(evento);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return eventoDAO.eliminar(id);
    }
}
