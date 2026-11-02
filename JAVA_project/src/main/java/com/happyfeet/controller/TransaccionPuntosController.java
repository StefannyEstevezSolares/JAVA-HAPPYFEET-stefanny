
package com.happyfeet.controller;

import com.happyfeet.model.TransaccionPuntos;
import com.happyfeet.service.TransaccionPuntosService;
import java.util.List;

public class TransaccionPuntosController {

    private final TransaccionPuntosService transaccionService;

    public TransaccionPuntosController() {
        this.transaccionService = new TransaccionPuntosService();
    }

    public boolean registrar(TransaccionPuntos transaccion) {
        return transaccionService.registrar(transaccion);
    }

    public TransaccionPuntos buscarPorId(int id) {
        return transaccionService.buscarPorId(id);
    }

    public List<TransaccionPuntos> listarTodos() {
        return transaccionService.listarTodos();
    }

    public boolean actualizar(TransaccionPuntos transaccion) {
        return transaccionService.actualizar(transaccion);
    }

    public boolean eliminar(int id) {
        return transaccionService.eliminar(id);
    }
}
