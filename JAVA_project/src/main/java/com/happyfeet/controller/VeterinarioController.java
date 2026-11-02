
package com.happyfeet.controller;

import com.happyfeet.model.Veterinario;
import com.happyfeet.service.VeterinarioService;
import java.sql.SQLException;
import java.util.List;

public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController() {
        this.veterinarioService = new VeterinarioService();
    }

    public int registrar(Veterinario veterinario) throws SQLException {
        return veterinarioService.registrar(veterinario);
    }

    public Veterinario buscarPorId(int id) throws SQLException {
        return veterinarioService.buscarPorId(id);
    }

    public List<Veterinario> listarTodos() throws SQLException {
        return veterinarioService.listarTodos();
    }

    public boolean actualizar(Veterinario veterinario) throws SQLException {
        return veterinarioService.actualizar(veterinario);
    }

    public boolean desactivar(int id) throws SQLException {
        return veterinarioService.desactivar(id);
    }
}
