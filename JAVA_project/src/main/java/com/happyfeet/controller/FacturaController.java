
package com.happyfeet.controller;

import com.happyfeet.model.Factura;
import com.happyfeet.service.FacturaService;
import java.util.List;

public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController() {
        this.facturaService = new FacturaService();
    }

    public boolean registrar(Factura factura) {
        return facturaService.registrar(factura);
    }

    public Factura buscarPorId(int id) {
        return facturaService.buscarPorId(id);
    }

    public List<Factura> listarTodos() {
        return facturaService.listarTodos();
    }

    public boolean actualizar(Factura factura) {
        return facturaService.actualizar(factura);
    }

    public boolean eliminar(int id) {
        return facturaService.eliminar(id);
    }
}
