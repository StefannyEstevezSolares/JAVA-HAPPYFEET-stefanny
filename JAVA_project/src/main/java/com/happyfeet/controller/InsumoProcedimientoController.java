
package com.happyfeet.controller;

import com.happyfeet.model.InsumoProcedimiento;
import com.happyfeet.service.InsumoProcedimientoService;
import java.util.List;

public class InsumoProcedimientoController {

    private final InsumoProcedimientoService insumoService;

    public InsumoProcedimientoController() {
        this.insumoService = new InsumoProcedimientoService();
    }

    public boolean registrar(InsumoProcedimiento insumo) {
        return insumoService.registrar(insumo);
    }

    public InsumoProcedimiento buscarPorId(int id) {
        return insumoService.buscarPorId(id);
    }

    public List<InsumoProcedimiento> listarTodos() {
        return insumoService.listarTodos();
    }

    public boolean actualizar(InsumoProcedimiento insumo) {
        return insumoService.actualizar(insumo);
    }

    public boolean eliminar(int id) {
        return insumoService.eliminar(id);
    }
}
