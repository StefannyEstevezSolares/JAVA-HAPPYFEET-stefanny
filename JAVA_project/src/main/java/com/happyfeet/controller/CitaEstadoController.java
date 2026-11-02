
package com.happyfeet.controller;

import com.happyfeet.model.CitaEstado;
import com.happyfeet.service.CitaEstadoService;
import java.util.List;

public class CitaEstadoController {

    private final CitaEstadoService estadoService;

    public CitaEstadoController() {
        this.estadoService = new CitaEstadoService();
    }

    public boolean registrar(CitaEstado estado) {
        return estadoService.registrar(estado);
    }

    public CitaEstado buscarPorId(int id) {
        return estadoService.buscarPorId(id);
    }

    public List<CitaEstado> listarTodos() {
        return estadoService.listarTodos();
    }

    public boolean actualizar(CitaEstado estado) {
        return estadoService.actualizar(estado);
    }

    public boolean eliminar(int id) {
        return estadoService.eliminar(id);
    }
}
