
package com.happyfeet.service;

import com.happyfeet.dao.ProcedimientoEspecialDAO;
import com.happyfeet.model.ProcedimientoEspecial;
import java.util.List;

public class ProcedimientoEspecialService {

    private final ProcedimientoEspecialDAO procedimientoDAO;

    public ProcedimientoEspecialService() {
        this.procedimientoDAO = new ProcedimientoEspecialDAO();
    }

    public boolean registrar(ProcedimientoEspecial procedimiento) {
        if (procedimiento == null) {
            throw new IllegalArgumentException(
                    "El procedimiento no puede estar vacío."
            );
        }

        return procedimientoDAO.registrar(procedimiento);
    }

    public ProcedimientoEspecial buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return procedimientoDAO.buscarPorId(id);
    }

    public List<ProcedimientoEspecial> listarTodos() {
        return procedimientoDAO.listarTodos();
    }

    public boolean actualizar(ProcedimientoEspecial procedimiento) {
        if (procedimiento == null) {
            throw new IllegalArgumentException(
                    "El procedimiento no puede estar vacío."
            );
        }

        if (procedimiento.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID del procedimiento debe ser válido."
            );
        }

        return procedimientoDAO.actualizar(procedimiento);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return procedimientoDAO.eliminar(id);
    }
}
