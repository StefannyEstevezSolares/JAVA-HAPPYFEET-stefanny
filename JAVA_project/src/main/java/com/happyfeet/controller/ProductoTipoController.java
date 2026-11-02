
package com.happyfeet.controller;

import com.happyfeet.model.ProductoTipo;
import com.happyfeet.service.ProductoTipoService;
import java.util.List;

public class ProductoTipoController {

    private final ProductoTipoService productoTipoService;

    public ProductoTipoController() {
        this.productoTipoService = new ProductoTipoService();
    }

    public boolean registrar(ProductoTipo productoTipo) {
        return productoTipoService.registrar(productoTipo);
    }

    public ProductoTipo buscarPorId(int id) {
        return productoTipoService.buscarPorId(id);
    }

    public List<ProductoTipo> listarTodos() {
        return productoTipoService.listarTodos();
    }

    public boolean actualizar(ProductoTipo productoTipo) {
        return productoTipoService.actualizar(productoTipo);
    }

    public boolean eliminar(int id) {
        return productoTipoService.eliminar(id);
    }
}
