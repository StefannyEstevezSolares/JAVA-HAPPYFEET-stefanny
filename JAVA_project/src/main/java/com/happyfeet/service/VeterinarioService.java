
package com.happyfeet.service;

import com.happyfeet.dao.VeterinarioDAO;
import com.happyfeet.model.Veterinario;
import java.sql.SQLException;
import java.util.List;

public class VeterinarioService {

    private final VeterinarioDAO veterinarioDAO;

    public VeterinarioService() {
        this.veterinarioDAO = new VeterinarioDAO();
    }

    public int registrar(Veterinario veterinario) throws SQLException {

        if (veterinario == null) {
            throw new IllegalArgumentException(
                "El veterinario no puede estar vacío."
            );
        }

        return veterinarioDAO.registrar(veterinario);
    }

    public List<Veterinario> listarTodos() throws SQLException {
        return veterinarioDAO.listarTodos();
    }

    public Veterinario buscarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return veterinarioDAO.buscarPorId(id);
    }

    public boolean actualizar(Veterinario veterinario) throws SQLException {

        if (veterinario == null) {
            throw new IllegalArgumentException(
                "El veterinario no puede estar vacío."
            );
        }

        return veterinarioDAO.actualizar(veterinario);
    }

    public boolean desactivar(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return veterinarioDAO.desactivar(id);
    }
}

