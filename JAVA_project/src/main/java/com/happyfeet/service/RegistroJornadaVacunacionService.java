
package com.happyfeet.service;

import com.happyfeet.dao.RegistroJornadaVacunacionDAO;
import com.happyfeet.model.RegistroJornadaVacunacion;
import java.util.List;

public class RegistroJornadaVacunacionService {

    private final RegistroJornadaVacunacionDAO registroDAO;

    public RegistroJornadaVacunacionService() {
        this.registroDAO = new RegistroJornadaVacunacionDAO();
    }

    public boolean registrar(RegistroJornadaVacunacion registro) {

        if (registro == null) {
            throw new IllegalArgumentException(
                "El registro de vacunación no puede estar vacío."
            );
        }

        return registroDAO.registrar(registro);
    }

    public RegistroJornadaVacunacion buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return registroDAO.buscarPorId(id);
    }

    public List<RegistroJornadaVacunacion> listarTodos() {
        return registroDAO.listarTodos();
    }

    public boolean actualizar(RegistroJornadaVacunacion registro) {

        if (registro == null) {
            throw new IllegalArgumentException(
                "El registro de vacunación no puede estar vacío."
            );
        }

        return registroDAO.actualizar(registro);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return registroDAO.eliminar(id);
    }
}
