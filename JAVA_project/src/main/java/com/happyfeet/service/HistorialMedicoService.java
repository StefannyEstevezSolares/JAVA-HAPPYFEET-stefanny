
package com.happyfeet.service;

import com.happyfeet.dao.HistorialMedicoDAO;
import com.happyfeet.model.HistorialMedico;
import java.sql.SQLException;
import java.util.List;

public class HistorialMedicoService {

    private final HistorialMedicoDAO historialMedicoDAO;

    public HistorialMedicoService() {
        this.historialMedicoDAO = new HistorialMedicoDAO();
    }

    public boolean registrar(HistorialMedico historial) throws SQLException {

        if (historial == null) {
            throw new IllegalArgumentException(
                "El historial médico no puede estar vacío."
            );
        }

        return historialMedicoDAO.registrar(historial);
    }

    public HistorialMedico buscarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return historialMedicoDAO.buscarPorId(id);
    }

    public List<HistorialMedico> listarTodos() throws SQLException {
        return historialMedicoDAO.listarTodos();
    }

    public boolean actualizar(HistorialMedico historial) throws SQLException {

        if (historial == null) {
            throw new IllegalArgumentException(
                "El historial médico no puede estar vacío."
            );
        }

        return historialMedicoDAO.actualizar(historial);
    }

    public boolean eliminar(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return historialMedicoDAO.eliminar(id);
    }
}
