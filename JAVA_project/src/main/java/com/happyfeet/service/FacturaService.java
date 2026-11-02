
package com.happyfeet.service;

import com.happyfeet.dao.FacturaDAO;
import com.happyfeet.model.Factura;
import java.util.List;

public class FacturaService {

    private final FacturaDAO facturaDAO;

    public FacturaService() {
        this.facturaDAO = new FacturaDAO();
    }

    public boolean registrar(Factura factura) {

        if (factura == null) {
            throw new IllegalArgumentException(
                "La factura no puede estar vacía."
            );
        }

        return facturaDAO.registrar(factura);
    }

    public Factura buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return facturaDAO.buscarPorId(id);
    }

    public List<Factura> listarTodos() {
        return facturaDAO.listarTodos();
    }

    public boolean actualizar(Factura factura) {

        if (factura == null) {
            throw new IllegalArgumentException(
                "La factura no puede estar vacía."
            );
        }

        return facturaDAO.actualizar(factura);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return facturaDAO.eliminar(id);
    }
}
