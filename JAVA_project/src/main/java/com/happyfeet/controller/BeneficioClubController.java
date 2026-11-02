
package com.happyfeet.controller;

import com.happyfeet.model.BeneficioClub;
import com.happyfeet.service.BeneficioClubService;
import java.util.List;

public class BeneficioClubController {

    private final BeneficioClubService beneficioService;

    public BeneficioClubController() {
        this.beneficioService = new BeneficioClubService();
    }

    public boolean registrar(BeneficioClub beneficio) {
        return beneficioService.registrar(beneficio);
    }

    public BeneficioClub buscarPorId(int id) {
        return beneficioService.buscarPorId(id);
    }

    public List<BeneficioClub> listarTodos() {
        return beneficioService.listarTodos();
    }

    public boolean actualizar(BeneficioClub beneficio) {
        return beneficioService.actualizar(beneficio);
    }

    public boolean eliminar(int id) {
        return beneficioService.eliminar(id);
    }
}
