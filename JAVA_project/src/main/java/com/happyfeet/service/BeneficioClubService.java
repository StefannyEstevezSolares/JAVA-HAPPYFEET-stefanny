
package com.happyfeet.service;

import com.happyfeet.dao.BeneficioClubDAO;
import com.happyfeet.model.BeneficioClub;
import java.util.List;

public class BeneficioClubService {

    private final BeneficioClubDAO beneficioDAO;

    public BeneficioClubService() {
        this.beneficioDAO = new BeneficioClubDAO();
    }

    public boolean registrar(BeneficioClub beneficio) {
        if (beneficio == null) {
            throw new IllegalArgumentException(
                    "El beneficio no puede estar vacío."
            );
        }

        return beneficioDAO.registrar(beneficio);
    }

    public BeneficioClub buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return beneficioDAO.buscarPorId(id);
    }

    public List<BeneficioClub> listarTodos() {
        return beneficioDAO.listarTodos();
    }

    public boolean actualizar(BeneficioClub beneficio) {
        if (beneficio == null) {
            throw new IllegalArgumentException(
                    "El beneficio no puede estar vacío."
            );
        }

        if (beneficio.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID del beneficio debe ser válido."
            );
        }

        return beneficioDAO.actualizar(beneficio);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return beneficioDAO.eliminar(id);
    }
}
