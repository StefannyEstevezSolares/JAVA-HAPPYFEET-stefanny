
package com.happyfeet.model;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class ConsultaMedica {

    private int id;
    private int mascotaId;
    private int veterinarioId;
    private Integer citaId;
    private LocalDateTime fechaHora;
    private String motivo;
    private String sintomas;
    private String diagnostico;
    private String recomendaciones;
    private String observaciones;
    private BigDecimal pesoRegistrado;
    private BigDecimal temperatura;

    // Constructor vacio
    public ConsultaMedica() {
    }

    // Constructor con parametros
    public ConsultaMedica(int id, int mascotaId, int veterinarioId,
            Integer citaId, LocalDateTime fechaHora, String motivo,
            String sintomas, String diagnostico, String recomendaciones,
            String observaciones, BigDecimal pesoRegistrado,
            BigDecimal temperatura) {

        this.id = id;
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.citaId = citaId;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.recomendaciones = recomendaciones;
        this.observaciones = observaciones;
        this.pesoRegistrado = pesoRegistrado;
        this.temperatura = temperatura;
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

    public int getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(int veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public Integer getCitaId() {
        return citaId;
    }

    public void setCitaId(Integer citaId) {
        this.citaId = citaId;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getPesoRegistrado() {
        return pesoRegistrado;
    }

    public void setPesoRegistrado(BigDecimal pesoRegistrado) {
        this.pesoRegistrado = pesoRegistrado;
    }

    public BigDecimal getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(BigDecimal temperatura) {
        this.temperatura = temperatura;
    }
}
