
package com.happyfeet.service;

import com.happyfeet.dao.MascotaDAO;
import com.happyfeet.model.Mascota;
import java.sql.SQLException;
import java.util.List;

public class MascotaService {

    private final MascotaDAO mascotaDAO;

    public MascotaService() {
        this.mascotaDAO = new MascotaDAO();
    }

    public int registrar(Mascota mascota) throws SQLException {

        if (mascota == null) {
            throw new IllegalArgumentException(
                "La mascota no puede estar vacía."
            );
        }

        return mascotaDAO.registrar(mascota);
    }

    public List<Mascota> listarTodos() throws SQLException {
        return mascotaDAO.listarTodos();
    }

    public Mascota buscarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return mascotaDAO.buscarPorId(id);
    }

    public boolean actualizar(Mascota mascota) throws SQLException {

        if (mascota == null) {
            throw new IllegalArgumentException(
                "La mascota no puede estar vacía."
            );
        }

        return mascotaDAO.actualizar(mascota);
    }

    public boolean desactivar(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return mascotaDAO.desactivar(id);
    }
}
