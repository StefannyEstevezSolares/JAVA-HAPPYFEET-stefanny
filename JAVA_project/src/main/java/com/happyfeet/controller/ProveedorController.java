
package com.happyfeet.controller;

import com.happyfeet.model.Proveedor;
import com.happyfeet.service.ProveedorService;
import java.util.List;

public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController() {
        this.proveedorService = new ProveedorService();
    }

    public boolean registrar(Proveedor proveedor) {
        return proveedorService.registrar(proveedor);
    }

    public Proveedor buscarPorId(int id) {
        return proveedorService.buscarPorId(id);
    }

    public List<Proveedor> listarTodos() {
        return proveedorService.listarTodos();
    }

    public boolean actualizar(Proveedor proveedor) {
        return proveedorService.actualizar(proveedor);
    }

    public boolean eliminar(int id) {
        return proveedorService.eliminar(id);
    }
}
