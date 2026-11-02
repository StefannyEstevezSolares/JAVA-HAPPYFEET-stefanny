
package com.happyfeet.service;

import com.happyfeet.dao.ClubMascotasDAO;
import com.happyfeet.model.ClubMascotas;
import java.util.List;

public class ClubMascotasService {

    private final ClubMascotasDAO clubDAO;

    public ClubMascotasService() {
        this.clubDAO = new ClubMascotasDAO();
    }

    public boolean registrar(ClubMascotas club) {
        if (club == null) {
            throw new IllegalArgumentException(
                    "La información del club no puede estar vacía."
            );
        }

        return clubDAO.registrar(club);
    }

    public ClubMascotas buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return clubDAO.buscarPorId(id);
    }

    public List<ClubMascotas> listarTodos() {
        return clubDAO.listarTodos();
    }

    public boolean actualizar(ClubMascotas club) {
        if (club == null) {
            throw new IllegalArgumentException(
                    "La información del club no puede estar vacía."
            );
        }

        if (club.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID del club debe ser válido."
            );
        }

        return clubDAO.actualizar(club);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return clubDAO.eliminar(id);
    }
}
