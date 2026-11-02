
package com.happyfeet.controller;

import com.happyfeet.model.JornadaVacunacion;
import com.happyfeet.service.JornadaVacunacionService;
import java.util.List;

public class JornadaVacunacionController {

    private final JornadaVacunacionService jornadaService;

    public JornadaVacunacionController() {
        this.jornadaService = new JornadaVacunacionService();
    }

    public boolean registrar(JornadaVacunacion jornada) {
        return jornadaService.registrar(jornada);
    }

    public JornadaVacunacion buscarPorId(int id) {
        return jornadaService.buscarPorId(id);
    }

    public List<JornadaVacunacion> listarTodos() {
        return jornadaService.listarTodos();
    }

    public boolean actualizar(JornadaVacunacion jornada) {
        return jornadaService.actualizar(jornada);
    }

    public boolean eliminar(int id) {
        return jornadaService.eliminar(id);
    }
}
