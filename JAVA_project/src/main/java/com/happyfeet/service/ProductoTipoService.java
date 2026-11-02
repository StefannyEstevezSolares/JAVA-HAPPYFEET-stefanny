
package com.happyfeet.service;

import com.happyfeet.dao.ProductoTipoDAO;
import com.happyfeet.model.ProductoTipo;
import java.util.List;

public class ProductoTipoService {

    private final ProductoTipoDAO productoTipoDAO;

    public ProductoTipoService() {
        this.productoTipoDAO = new ProductoTipoDAO();
    }

    public boolean registrar(ProductoTipo productoTipo) {
        if (productoTipo == null) {
            throw new IllegalArgumentException(
                    "El tipo de producto no puede estar vacío."
            );
        }

        return productoTipoDAO.registrar(productoTipo);
    }

    public ProductoTipo buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return productoTipoDAO.buscarPorId(id);
    }

    public List<ProductoTipo> listarTodos() {
        return productoTipoDAO.listarTodos();
    }

    public boolean actualizar(ProductoTipo productoTipo) {
        if (productoTipo == null) {
            throw new IllegalArgumentException(
                    "El tipo de producto no puede estar vacío."
            );
        }

        if (productoTipo.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID del tipo de producto debe ser válido."
            );
        }

        return productoTipoDAO.actualizar(productoTipo);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return productoTipoDAO.eliminar(id);
    }
}
