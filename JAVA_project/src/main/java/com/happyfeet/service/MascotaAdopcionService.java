
package com.happyfeet.service;

import com.happyfeet.dao.MascotaAdopcionDAO;
import com.happyfeet.model.MascotaAdopcion;
import java.util.List;

public class MascotaAdopcionService {

    private final MascotaAdopcionDAO mascotaAdopcionDAO;

    public MascotaAdopcionService() {
        this.mascotaAdopcionDAO = new MascotaAdopcionDAO();
    }

    public boolean registrar(MascotaAdopcion mascota) {

        if (mascota == null) {
            throw new IllegalArgumentException(
                "La mascota para adopción no puede estar vacía."
            );
        }

        return mascotaAdopcionDAO.registrar(mascota);
    }

    public MascotaAdopcion buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return mascotaAdopcionDAO.buscarPorId(id);
    }

    public List<MascotaAdopcion> listarTodos() {
        return mascotaAdopcionDAO.listarTodos();
    }

    public boolean actualizar(MascotaAdopcion mascota) {

        if (mascota == null) {
            throw new IllegalArgumentException(
                "La mascota para adopción no puede estar vacía."
            );
        }

        return mascotaAdopcionDAO.actualizar(mascota);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return mascotaAdopcionDAO.eliminar(id);
    }
}

