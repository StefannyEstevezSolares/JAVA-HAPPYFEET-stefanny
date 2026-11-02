
package com.happyfeet.service;

import com.happyfeet.dao.InventarioDAO;
import com.happyfeet.model.Inventario;
import java.util.List;

public class InventarioService {

    private final InventarioDAO inventarioDAO;

    public InventarioService() {
        this.inventarioDAO = new InventarioDAO();
    }

    public boolean registrar(Inventario producto) {

        if (producto == null) {
            throw new IllegalArgumentException(
                "El producto no puede estar vacío."
            );
        }

        return inventarioDAO.registrar(producto);
    }

    public Inventario buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return inventarioDAO.buscarPorId(id);
    }

    public List<Inventario> listarTodos() {
        return inventarioDAO.listarTodos();
    }

    public boolean actualizar(Inventario producto) {

        if (producto == null) {
            throw new IllegalArgumentException(
                "El producto no puede estar vacío."
            );
        }

        return inventarioDAO.actualizar(producto);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return inventarioDAO.eliminar(id);
    }
}

