
package com.happyfeet.controller;

import com.happyfeet.model.Raza;
import com.happyfeet.service.RazaService;
import java.util.List;

public class RazaController {

    private final RazaService razaService;

    public RazaController() {
        this.razaService = new RazaService();
    }

    public boolean registrar(Raza raza) {
        return razaService.registrar(raza);
    }

    public Raza buscarPorId(int id) {
        return razaService.buscarPorId(id);
    }

    public List<Raza> listarTodos() {
        return razaService.listarTodos();
    }

    public boolean actualizar(Raza raza) {
        return razaService.actualizar(raza);
    }

    public boolean eliminar(int id) {
        return razaService.eliminar(id);
    }
}
