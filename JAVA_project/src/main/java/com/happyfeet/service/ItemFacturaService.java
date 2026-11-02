
package com.happyfeet.service;

import com.happyfeet.dao.ItemFacturaDAO;
import com.happyfeet.model.ItemFactura;
import java.util.List;

public class ItemFacturaService {

    private final ItemFacturaDAO itemDAO;

    public ItemFacturaService() {
        this.itemDAO = new ItemFacturaDAO();
    }

    public boolean registrar(ItemFactura item) {
        if (item == null) {
            throw new IllegalArgumentException(
                    "El item de factura no puede estar vacío."
            );
        }

        return itemDAO.registrar(item);
    }

    public ItemFactura buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return itemDAO.buscarPorId(id);
    }

    public List<ItemFactura> listarTodos() {
        return itemDAO.listarTodos();
    }

    public List<ItemFactura> listarPorFactura(int facturaId) {
        if (facturaId <= 0) {
            throw new IllegalArgumentException(
                    "El ID de la factura debe ser válido."
            );
        }

        return itemDAO.listarPorFactura(facturaId);
    }

    public boolean actualizar(ItemFactura item) {
        if (item == null) {
            throw new IllegalArgumentException(
                    "El item de factura no puede estar vacío."
            );
        }

        if (item.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID del item debe ser válido."
            );
        }

        return itemDAO.actualizar(item);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return itemDAO.eliminar(id);
    }
}
