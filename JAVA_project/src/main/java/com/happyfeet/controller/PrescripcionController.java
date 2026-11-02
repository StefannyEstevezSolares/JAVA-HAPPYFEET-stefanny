
package com.happyfeet.controller;

import com.happyfeet.model.Prescripcion;
import com.happyfeet.service.PrescripcionService;
import java.util.List;

public class PrescripcionController {

    private final PrescripcionService prescripcionService;

    public PrescripcionController() {
        this.prescripcionService = new PrescripcionService();
    }

    public boolean registrar(Prescripcion prescripcion) {
        return prescripcionService.registrar(prescripcion);
    }

    public Prescripcion buscarPorId(int id) {
        return prescripcionService.buscarPorId(id);
    }

    public List<Prescripcion> listarTodos() {
        return prescripcionService.listarTodos();
    }

    public boolean actualizar(Prescripcion prescripcion) {
        return prescripcionService.actualizar(prescripcion);
    }

    public boolean eliminar(int id) {
        return prescripcionService.eliminar(id);
    }
}
