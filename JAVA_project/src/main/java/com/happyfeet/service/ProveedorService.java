
package com.happyfeet.service;

import com.happyfeet.dao.ProveedorDAO;
import com.happyfeet.model.Proveedor;
import java.util.List;

public class ProveedorService {

    private final ProveedorDAO proveedorDAO;

    public ProveedorService() {
        this.proveedorDAO = new ProveedorDAO();
    }

    public boolean registrar(Proveedor proveedor) {

        if (proveedor == null) {
            throw new IllegalArgumentException(
                "El proveedor no puede estar vacío."
            );
        }

        return proveedorDAO.registrar(proveedor);
    }

    public Proveedor buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return proveedorDAO.buscarPorId(id);
    }

    public List<Proveedor> listarTodos() {
        return proveedorDAO.listarTodos();
    }

    public boolean actualizar(Proveedor proveedor) {

        if (proveedor == null) {
            throw new IllegalArgumentException(
                "El proveedor no puede estar vacío."
            );
        }

        return proveedorDAO.actualizar(proveedor);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return proveedorDAO.eliminar(id);
    }
}
