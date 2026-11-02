
package com.happyfeet.service;

import com.happyfeet.dao.MovimientoInventarioDAO;
import com.happyfeet.model.MovimientoInventario;
import java.util.List;

public class MovimientoInventarioService {

    private final MovimientoInventarioDAO movimientoDAO;

    public MovimientoInventarioService() {
        this.movimientoDAO = new MovimientoInventarioDAO();
    }

    public boolean registrar(MovimientoInventario movimiento) {

        if (movimiento == null) {
            throw new IllegalArgumentException(
                "El movimiento no puede estar vacío."
            );
        }

        return movimientoDAO.registrar(movimiento);
    }

    public MovimientoInventario buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return movimientoDAO.buscarPorId(id);
    }

    public List<MovimientoInventario> listarTodos() {
        return movimientoDAO.listarTodos();
    }

    public boolean actualizar(MovimientoInventario movimiento) {

        if (movimiento == null) {
            throw new IllegalArgumentException(
                "El movimiento no puede estar vacío."
            );
        }

        return movimientoDAO.actualizar(movimiento);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return movimientoDAO.eliminar(id);
    }
}
