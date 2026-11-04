package com.happyfeet.notificaciones;

public interface ObservadorInventario {

    void actualizar(String mensaje, int productoId, String tipoAlerta);
}