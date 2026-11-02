
package com.happyfeet.controller;

import com.happyfeet.model.CanjeBeneficio;
import com.happyfeet.service.CanjeBeneficioService;
import java.util.List;

public class CanjeBeneficioController {

    private final CanjeBeneficioService canjeService;

    public CanjeBeneficioController() {
        this.canjeService = new CanjeBeneficioService();
    }

    public boolean registrar(CanjeBeneficio canje) {
        return canjeService.registrar(canje);
    }

    public CanjeBeneficio buscarPorId(int id) {
        return canjeService.buscarPorId(id);
    }

    public List<CanjeBeneficio> listarTodos() {
        return canjeService.listarTodos();
    }

    public boolean actualizar(CanjeBeneficio canje) {
        return canjeService.actualizar(canje);
    }

    public boolean eliminar(int id) {
        return canjeService.eliminar(id);
    }
}
