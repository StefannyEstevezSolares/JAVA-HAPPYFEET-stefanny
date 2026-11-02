
package com.happyfeet.controller;

import com.happyfeet.model.HistorialMedico;
import com.happyfeet.service.HistorialMedicoService;
import java.sql.SQLException;
import java.util.List;

public class HistorialMedicoController {

    private final HistorialMedicoService historialMedicoService;

    public HistorialMedicoController() {
        this.historialMedicoService = new HistorialMedicoService();
    }

    public boolean registrar(HistorialMedico historial) throws SQLException {
        return historialMedicoService.registrar(historial);
    }

    public HistorialMedico buscarPorId(int id) throws SQLException {
        return historialMedicoService.buscarPorId(id);
    }

    public List<HistorialMedico> listarTodos() throws SQLException {
        return historialMedicoService.listarTodos();
    }

    public boolean actualizar(HistorialMedico historial) throws SQLException {
        return historialMedicoService.actualizar(historial);
    }

    public boolean eliminar(int id) throws SQLException {
        return historialMedicoService.eliminar(id);
    }
}
