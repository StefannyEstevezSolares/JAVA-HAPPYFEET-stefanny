
package com.happyfeet.controller;

import com.happyfeet.model.Servicio;
import com.happyfeet.service.ServicioService;
import java.util.List;

public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController() {
        this.servicioService = new ServicioService();
    }

    public boolean registrar(Servicio servicio) {
        return servicioService.registrar(servicio);
    }

    public Servicio buscarPorId(int id) {
        return servicioService.buscarPorId(id);
    }

    public List<Servicio> listarTodos() {
        return servicioService.listarTodos();
    }

    public boolean actualizar(Servicio servicio) {
        return servicioService.actualizar(servicio);
    }

    public boolean eliminar(int id) {
        return servicioService.eliminar(id);
    }
}
