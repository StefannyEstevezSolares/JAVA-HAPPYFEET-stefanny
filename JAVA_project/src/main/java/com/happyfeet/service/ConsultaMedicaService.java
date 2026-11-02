
package com.happyfeet.service;

import com.happyfeet.dao.ConsultaMedicaDAO;
import com.happyfeet.model.ConsultaMedica;
import java.sql.SQLException;
import java.util.List;

public class ConsultaMedicaService {

    private final ConsultaMedicaDAO consultaDAO;

    public ConsultaMedicaService() {
        this.consultaDAO = new ConsultaMedicaDAO();
    }

    public int registrar(ConsultaMedica consulta) throws SQLException {

        if (consulta == null) {
            throw new IllegalArgumentException(
                "La consulta médica no puede estar vacía."
            );
        }

        return consultaDAO.registrar(consulta);
    }

    public ConsultaMedica buscarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return consultaDAO.buscarPorId(id);
    }

    public List<ConsultaMedica> listarTodos() throws SQLException {
        return consultaDAO.listarTodos();
    }

    public boolean actualizar(ConsultaMedica consulta) throws SQLException {

        if (consulta == null) {
            throw new IllegalArgumentException(
                "La consulta médica no puede estar vacía."
            );
        }

        return consultaDAO.actualizar(consulta);
    }

    public boolean eliminar(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return consultaDAO.eliminar(id);
    }
}
