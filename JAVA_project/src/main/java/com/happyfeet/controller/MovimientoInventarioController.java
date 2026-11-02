
package com.happyfeet.controller;

import com.happyfeet.model.MovimientoInventario;
import com.happyfeet.service.MovimientoInventarioService;
import java.util.List;

public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoService;

    public MovimientoInventarioController() {
        this.movimientoService = new MovimientoInventarioService();
    }

    public boolean registrar(MovimientoInventario movimiento) {
        return movimientoService.registrar(movimiento);
    }

    public MovimientoInventario buscarPorId(int id) {
        return movimientoService.buscarPorId(id);
    }

    public List<MovimientoInventario> listarTodos() {
        return movimientoService.listarTodos();
    }

    public boolean actualizar(MovimientoInventario movimiento) {
        return movimientoService.actualizar(movimiento);
    }

    public boolean eliminar(int id) {
        return movimientoService.eliminar(id);
    }
}
