
package com.happyfeet.controller;

import com.happyfeet.model.Adopcion;
import com.happyfeet.service.AdopcionService;
import java.util.List;

public class AdopcionController {

    private final AdopcionService adopcionService;

    public AdopcionController() {
        this.adopcionService = new AdopcionService();
    }

    public boolean registrar(Adopcion adopcion) {
        return adopcionService.registrar(adopcion);
    }

    public Adopcion buscarPorId(int id) {
        return adopcionService.buscarPorId(id);
    }

    public List<Adopcion> listarTodos() {
        return adopcionService.listarTodos();
    }

    public boolean actualizar(Adopcion adopcion) {
        return adopcionService.actualizar(adopcion);
    }

    public boolean eliminar(int id) {
        return adopcionService.eliminar(id);
    }
}
