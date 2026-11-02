
package com.happyfeet.controller;

import com.happyfeet.model.ItemFactura;
import com.happyfeet.service.ItemFacturaService;
import java.util.List;

public class ItemFacturaController {

    private final ItemFacturaService itemService;

    public ItemFacturaController() {
        this.itemService = new ItemFacturaService();
    }

    public boolean registrar(ItemFactura item) {
        return itemService.registrar(item);
    }

    public ItemFactura buscarPorId(int id) {
        return itemService.buscarPorId(id);
    }

    public List<ItemFactura> listarTodos() {
        return itemService.listarTodos();
    }

    public List<ItemFactura> listarPorFactura(int facturaId) {
        return itemService.listarPorFactura(facturaId);
    }

    public boolean actualizar(ItemFactura item) {
        return itemService.actualizar(item);
    }

    public boolean eliminar(int id) {
        return itemService.eliminar(id);
    }
}
