
package com.happyfeet.model;

import java.time.LocalDate;

public class HistorialMedico {

    private int id;
    private int mascotaId;
    private LocalDate fechaEvento;
    private int eventoTipoId;
    private String descripcion;
    private String diagnostico;
    private String tratamientoRecomendado;
    private Integer veterinarioId;
    private Integer consultaId;
    private Integer procedimientoId;

    // Constructor vacío
    public HistorialMedico() {
    }

    // Constructor completo
    public HistorialMedico(int id, int mascotaId, LocalDate fechaEvento,
            int eventoTipoId, String descripcion, String diagnostico,
            String tratamientoRecomendado, Integer veterinarioId,
            Integer consultaId, Integer procedimientoId) {

        this.id = id;
        this.mascotaId = mascotaId;
        this.fechaEvento = fechaEvento;
        this.eventoTipoId = eventoTipoId;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamientoRecomendado = tratamientoRecomendado;
        this.veterinarioId = veterinarioId;
        this.consultaId = consultaId;
        this.procedimientoId = procedimientoId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public int getEventoTipoId() {
        return eventoTipoId;
    }

    public void setEventoTipoId(int eventoTipoId) {
        this.eventoTipoId = eventoTipoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamientoRecomendado() {
        return tratamientoRecomendado;
    }

    public void setTratamientoRecomendado(String tratamientoRecomendado) {
        this.tratamientoRecomendado = tratamientoRecomendado;
    }

    public Integer getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Integer veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public Integer getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Integer consultaId) {
        this.consultaId = consultaId;
    }

    public Integer getProcedimientoId() {
        return procedimientoId;
    }

    public void setProcedimientoId(Integer procedimientoId) {
        this.procedimientoId = procedimientoId;
    }
}
