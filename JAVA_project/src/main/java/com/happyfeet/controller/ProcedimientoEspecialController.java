
package com.happyfeet.controller;

import com.happyfeet.model.ProcedimientoEspecial;
import com.happyfeet.service.ProcedimientoEspecialService;
import java.util.List;

public class ProcedimientoEspecialController {

    private final ProcedimientoEspecialService procedimientoService;

    public ProcedimientoEspecialController() {
        this.procedimientoService = new ProcedimientoEspecialService();
    }

    public boolean registrar(ProcedimientoEspecial procedimiento) {
        return procedimientoService.registrar(procedimiento);
    }

    public ProcedimientoEspecial buscarPorId(int id) {
        return procedimientoService.buscarPorId(id);
    }

    public List<ProcedimientoEspecial> listarTodos() {
        return procedimientoService.listarTodos();
    }

    public boolean actualizar(ProcedimientoEspecial procedimiento) {
        return procedimientoService.actualizar(procedimiento);
    }

    public boolean eliminar(int id) {
        return procedimientoService.eliminar(id);
    }
}
