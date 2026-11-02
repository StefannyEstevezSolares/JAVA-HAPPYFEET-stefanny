
package com.happyfeet.controller;

import com.happyfeet.model.EventoTipo;
import com.happyfeet.service.EventoTipoService;
import java.util.List;

public class EventoTipoController {

    private final EventoTipoService eventoService;

    public EventoTipoController() {
        this.eventoService = new EventoTipoService();
    }

    public boolean registrar(EventoTipo evento) {
        return eventoService.registrar(evento);
    }

    public EventoTipo buscarPorId(int id) {
        return eventoService.buscarPorId(id);
    }

    public List<EventoTipo> listarTodos() {
        return eventoService.listarTodos();
    }

    public boolean actualizar(EventoTipo evento) {
        return eventoService.actualizar(evento);
    }

    public boolean eliminar(int id) {
        return eventoService.eliminar(id);
    }
}
