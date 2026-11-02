
package com.happyfeet.controller;

import com.happyfeet.model.ClubMascotas;
import com.happyfeet.service.ClubMascotasService;
import java.util.List;

public class ClubMascotasController {

    private final ClubMascotasService clubService;

    public ClubMascotasController() {
        this.clubService = new ClubMascotasService();
    }

    public boolean registrar(ClubMascotas club) {
        return clubService.registrar(club);
    }

    public ClubMascotas buscarPorId(int id) {
        return clubService.buscarPorId(id);
    }

    public List<ClubMascotas> listarTodos() {
        return clubService.listarTodos();
    }

    public boolean actualizar(ClubMascotas club) {
        return clubService.actualizar(club);
    }

    public boolean eliminar(int id) {
        return clubService.eliminar(id);
    }
}
