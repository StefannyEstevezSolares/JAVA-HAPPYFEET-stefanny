
package com.happyfeet.service;

import com.happyfeet.dao.TransaccionPuntosDAO;
import com.happyfeet.model.TransaccionPuntos;
import java.util.List;

public class TransaccionPuntosService {

    private final TransaccionPuntosDAO transaccionDAO;

    public TransaccionPuntosService() {
        this.transaccionDAO = new TransaccionPuntosDAO();
    }

    public boolean registrar(TransaccionPuntos transaccion) {
        if (transaccion == null) {
            throw new IllegalArgumentException(
                    "La transacción no puede estar vacía."
            );
        }

        return transaccionDAO.registrar(transaccion);
    }

    public TransaccionPuntos buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return transaccionDAO.buscarPorId(id);
    }

    public List<TransaccionPuntos> listarTodos() {
        return transaccionDAO.listarTodos();
    }

    public boolean actualizar(TransaccionPuntos transaccion) {
        if (transaccion == null) {
            throw new IllegalArgumentException(
                    "La transacción no puede estar vacía."
            );
        }

        if (transaccion.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID de la transacción debe ser válido."
            );
        }

        return transaccionDAO.actualizar(transaccion);
    }

    public boolean eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return transaccionDAO.eliminar(id);
    }
}
