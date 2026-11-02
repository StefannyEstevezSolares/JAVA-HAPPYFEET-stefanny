
package com.happyfeet.controller;

import com.happyfeet.model.RegistroJornadaVacunacion;
import com.happyfeet.service.RegistroJornadaVacunacionService;
import java.util.List;

public class RegistroJornadaVacunacionController {

    private final RegistroJornadaVacunacionService registroService;

    public RegistroJornadaVacunacionController() {
        this.registroService = new RegistroJornadaVacunacionService();
    }

    public boolean registrar(RegistroJornadaVacunacion registro) {
        return registroService.registrar(registro);
    }

    public RegistroJornadaVacunacion buscarPorId(int id) {
        return registroService.buscarPorId(id);
    }

    public List<RegistroJornadaVacunacion> listarTodos() {
        return registroService.listarTodos();
    }

    public boolean actualizar(RegistroJornadaVacunacion registro) {
        return registroService.actualizar(registro);
    }

    public boolean eliminar(int id) {
        return registroService.eliminar(id);
    }
}
