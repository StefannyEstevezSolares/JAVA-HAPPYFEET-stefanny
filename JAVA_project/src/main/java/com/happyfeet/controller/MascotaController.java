
package com.happyfeet.controller;

import com.happyfeet.model.Mascota;
import com.happyfeet.service.MascotaService;
import java.sql.SQLException;
import java.util.List;

public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController() {
        this.mascotaService = new MascotaService();
    }

    public int registrar(Mascota mascota) throws SQLException {
        return mascotaService.registrar(mascota);
    }

    public Mascota buscarPorId(int id) throws SQLException {
        return mascotaService.buscarPorId(id);
    }

    public List<Mascota> listarTodos() throws SQLException {
        return mascotaService.listarTodos();
    }

    public boolean actualizar(Mascota mascota) throws SQLException {
        return mascotaService.actualizar(mascota);
    }

    public boolean desactivar(int id) throws SQLException {
        return mascotaService.desactivar(id);
    }
}
