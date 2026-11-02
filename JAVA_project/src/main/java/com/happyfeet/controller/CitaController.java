
package com.happyfeet.controller;

import com.happyfeet.model.Cita;
import com.happyfeet.service.CitaService;
import java.sql.SQLException;
import java.util.List;

public class CitaController {

    private final CitaService citaService;

    public CitaController() {
        this.citaService = new CitaService();
    }

    public int registrar(Cita cita) throws SQLException {
        return citaService.registrar(cita);
    }

    public Cita buscarPorId(int id) throws SQLException {
        return citaService.buscarPorId(id);
    }

    public List<Cita> listarTodos() throws SQLException {
        return citaService.listarTodos();
    }

    public boolean actualizar(Cita cita) throws SQLException {
        return citaService.actualizar(cita);
    }

    public boolean eliminar(int id) throws SQLException {
        return citaService.eliminar(id);
    }
}
