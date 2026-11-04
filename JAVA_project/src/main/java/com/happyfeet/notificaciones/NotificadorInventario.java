
package com.happyfeet.notificaciones;

import java.util.ArrayList;
import java.util.List;

public class NotificadorInventario {

    private final List<ObservadorInventario> observadores;

    public NotificadorInventario() {
        this.observadores = new ArrayList<>();
    }

    public void agregarObservador(ObservadorInventario observador) {

        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void eliminarObservador(ObservadorInventario observador) {
        observadores.remove(observador);
    }

    public void notificar(String mensaje, int productoId, String tipoAlerta) {

        for (ObservadorInventario observador : observadores) {
            observador.actualizar(mensaje, productoId, tipoAlerta);
        }
    }
}
