
package com.happyfeet.controller;

import com.happyfeet.model.Dueno;
import com.happyfeet.service.DuenoService;
import java.sql.SQLException;
import java.util.List;

public class DuenoController {

    private final DuenoService duenoService;

    public DuenoController() {
        this.duenoService = new DuenoService();
    }

    public int registrar(Dueno dueno) throws SQLException {
        return duenoService.registrar(dueno);
    }

    public Dueno buscarPorId(int id) throws SQLException {
        return duenoService.buscarPorId(id);
    }

    public List<Dueno> listarTodos() throws SQLException {
        return duenoService.listarTodos();
    }

    public boolean actualizar(Dueno dueno) throws SQLException {
        return duenoService.actualizar(dueno);
    }

    public boolean desactivar(int id) throws SQLException {
        return duenoService.desactivar(id);
    }
}
