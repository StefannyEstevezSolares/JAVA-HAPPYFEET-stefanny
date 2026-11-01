
package com.happyfeet.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClubMascotas {

    private int id;
    private int duenoId;
    private int puntosAcumulados;
    private int puntosCanjeados;
    private int puntosDisponibles;
    private String nivel;
    private LocalDate fechaInscripcion;
    private LocalDateTime fechaUltimaActualizacion;
    private Boolean activo;

    public ClubMascotas() {
    }

    public ClubMascotas(int id, int duenoId, int puntosAcumulados,
            int puntosCanjeados, int puntosDisponibles, String nivel,
            LocalDate fechaInscripcion,
            LocalDateTime fechaUltimaActualizacion, Boolean activo) {

        this.id = id;
        this.duenoId = duenoId;
        this.puntosAcumulados = puntosAcumulados;
        this.puntosCanjeados = puntosCanjeados;
        this.puntosDisponibles = puntosDisponibles;
        this.nivel = nivel;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public int getPuntosCanjeados() {
        return puntosCanjeados;
    }

    public void setPuntosCanjeados(int puntosCanjeados) {
        this.puntosCanjeados = puntosCanjeados;
    }

    public int getPuntosDisponibles() {
        return puntosDisponibles;
    }

    public void setPuntosDisponibles(int puntosDisponibles) {
        this.puntosDisponibles = puntosDisponibles;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(LocalDateTime fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
