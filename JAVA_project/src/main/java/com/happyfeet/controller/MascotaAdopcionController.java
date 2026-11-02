
package com.happyfeet.controller;

import com.happyfeet.model.MascotaAdopcion;
import com.happyfeet.service.MascotaAdopcionService;
import java.util.List;

public class MascotaAdopcionController {

    private final MascotaAdopcionService mascotaAdopcionService;

    public MascotaAdopcionController() {
        this.mascotaAdopcionService = new MascotaAdopcionService();
    }

    public boolean registrar(MascotaAdopcion mascota) {
        return mascotaAdopcionService.registrar(mascota);
    }

    public MascotaAdopcion buscarPorId(int id) {
        return mascotaAdopcionService.buscarPorId(id);
    }

    public List<MascotaAdopcion> listarTodos() {
        return mascotaAdopcionService.listarTodos();
    }

    public boolean actualizar(MascotaAdopcion mascota) {
        return mascotaAdopcionService.actualizar(mascota);
    }

    public boolean eliminar(int id) {
        return mascotaAdopcionService.eliminar(id);
    }
}
