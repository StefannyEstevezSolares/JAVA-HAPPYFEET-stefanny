
package com.happyfeet.service;

import com.happyfeet.dao.CitaDAO;
import com.happyfeet.model.Cita;
import java.sql.SQLException;
import java.util.List;

public class CitaService {

    private final CitaDAO citaDAO;

    public CitaService() {
        this.citaDAO = new CitaDAO();
    }

    public int registrar(Cita cita) throws SQLException {

        if (cita == null) {
            throw new IllegalArgumentException(
                "La cita no puede estar vacía."
            );
        }

        return citaDAO.registrar(cita);
    }

    public List<Cita> listarTodos() throws SQLException {
        return citaDAO.listarTodos();
    }

    public Cita buscarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return citaDAO.buscarPorId(id);
    }

    public boolean actualizar(Cita cita) throws SQLException {

        if (cita == null) {
            throw new IllegalArgumentException(
                "La cita no puede estar vacía."
            );
        }

        return citaDAO.actualizar(cita);
    }

    public boolean eliminar(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return citaDAO.eliminar(id);
    }
}

