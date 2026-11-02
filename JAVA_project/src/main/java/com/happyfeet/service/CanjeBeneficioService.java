
package com.happyfeet.service;

import com.happyfeet.dao.CanjeBeneficioDAO;
import com.happyfeet.model.CanjeBeneficio;
import java.util.List;

public class CanjeBeneficioService {

    private final CanjeBeneficioDAO canjeDAO;

    public CanjeBeneficioService() {
        this.canjeDAO = new CanjeBeneficioDAO();
    }

    public boolean registrar(CanjeBeneficio canje) {
        if (canje == null) {
            throw new IllegalArgumentException(
                    "La información del canje no puede estar vacía."
            );
        }

        return canjeDAO.registrar(canje);
    }

    public CanjeBeneficio buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return canjeDAO.buscarPorId(id);
    }

    public List<CanjeBeneficio> listarTodos() {
        return canjeDAO.listarTodos();
    }

    public boolean actualizar(CanjeBeneficio canje) {
        if (canje == null) {
            throw new IllegalArgumentException(
                    "La información del canje no puede estar vacía."
            );
        }

        if (canje.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID del canje debe ser válido."
            );
        }

        return canjeDAO.actualizar(canje);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return canjeDAO.eliminar(id);
    }
}
