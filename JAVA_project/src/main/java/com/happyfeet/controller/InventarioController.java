
package com.happyfeet.controller;

import com.happyfeet.model.Inventario;
import com.happyfeet.service.InventarioService;
import java.util.List;

public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController() {
        this.inventarioService = new InventarioService();
    }

    public boolean registrar(Inventario producto) {
        return inventarioService.registrar(producto);
    }

    public Inventario buscarPorId(int id) {
        return inventarioService.buscarPorId(id);
    }

    public List<Inventario> listarTodos() {
        return inventarioService.listarTodos();
    }

    public boolean actualizar(Inventario producto) {
        return inventarioService.actualizar(producto);
    }

    public boolean eliminar(int id) {
        return inventarioService.eliminar(id);
    }
}
