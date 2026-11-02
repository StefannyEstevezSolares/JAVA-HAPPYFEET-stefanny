
package com.happyfeet.controller;

import com.happyfeet.model.Especie;
import com.happyfeet.service.EspecieService;
import java.util.List;

public class EspecieController {

    private final EspecieService especieService;

    public EspecieController() {
        this.especieService = new EspecieService();
    }

    public boolean registrar(Especie especie) {
        return especieService.registrar(especie);
    }

    public Especie buscarPorId(int id) {
        return especieService.buscarPorId(id);
    }

    public List<Especie> listarTodos() {
        return especieService.listarTodos();
    }

    public boolean actualizar(Especie especie) {
        return especieService.actualizar(especie);
    }

    public boolean eliminar(int id) {
        return especieService.eliminar(id);
    }
}
