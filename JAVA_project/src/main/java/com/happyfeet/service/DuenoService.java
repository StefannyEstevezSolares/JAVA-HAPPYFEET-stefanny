
package com.happyfeet.service;

import com.happyfeet.dao.DuenoDAO;
import com.happyfeet.model.Dueno;
import java.sql.SQLException;
import java.util.List;

public class DuenoService {

    private final DuenoDAO duenoDAO;

    public DuenoService() {
        this.duenoDAO = new DuenoDAO();
    }

    public int registrar(Dueno dueno) throws SQLException {

        if (dueno == null) {
            throw new IllegalArgumentException(
                "El dueño no puede estar vacío."
            );
        }

        return duenoDAO.registrar(dueno);
    }

    public List<Dueno> listarTodos() throws SQLException {
        return duenoDAO.listarTodos();
    }

    public Dueno buscarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return duenoDAO.buscarPorId(id);
    }

    public boolean actualizar(Dueno dueno) throws SQLException {

        if (dueno == null) {
            throw new IllegalArgumentException(
                "El dueño no puede estar vacío."
            );
        }

        return duenoDAO.actualizar(dueno);
    }

    public boolean desactivar(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return duenoDAO.desactivar(id);
    }
}
