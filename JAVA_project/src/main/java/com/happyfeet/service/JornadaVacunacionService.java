
package com.happyfeet.service;

import com.happyfeet.dao.JornadaVacunacionDAO;
import com.happyfeet.model.JornadaVacunacion;
import java.util.List;

public class JornadaVacunacionService {

    private final JornadaVacunacionDAO jornadaDAO;

    public JornadaVacunacionService() {
        this.jornadaDAO = new JornadaVacunacionDAO();
    }

    public boolean registrar(JornadaVacunacion jornada) {

        if (jornada == null) {
            throw new IllegalArgumentException(
                "La jornada no puede estar vacía."
            );
        }

        return jornadaDAO.registrar(jornada);
    }

    public JornadaVacunacion buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return jornadaDAO.buscarPorId(id);
    }

    public List<JornadaVacunacion> listarTodos() {
        return jornadaDAO.listarTodos();
    }

    public boolean actualizar(JornadaVacunacion jornada) {

        if (jornada == null) {
            throw new IllegalArgumentException(
                "La jornada no puede estar vacía."
            );
        }

        return jornadaDAO.actualizar(jornada);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return jornadaDAO.eliminar(id);
    }
}
