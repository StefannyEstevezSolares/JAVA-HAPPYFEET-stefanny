
package com.happyfeet.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistroJornadaVacunacion {

    private int id;
    private int jornadaId;
    private int mascotaId;
    private int duenoId;
    private int vacunaId;
    private Integer veterinarioId;
    private LocalDateTime fechaHora;
    private String loteVacuna;
    private LocalDate proximaDosis;
    private String observaciones;

    public RegistroJornadaVacunacion() {
    }

    public RegistroJornadaVacunacion(int id, int jornadaId,
            int mascotaId, int duenoId, int vacunaId,
            Integer veterinarioId, LocalDateTime fechaHora,
            String loteVacuna, LocalDate proximaDosis,
            String observaciones) {

        this.id = id;
        this.jornadaId = jornadaId;
        this.mascotaId = mascotaId;
        this.duenoId = duenoId;
        this.vacunaId = vacunaId;
        this.veterinarioId = veterinarioId;
        this.fechaHora = fechaHora;
        this.loteVacuna = loteVacuna;
        this.proximaDosis = proximaDosis;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJornadaId() {
        return jornadaId;
    }

    public void setJornadaId(int jornadaId) {
        this.jornadaId = jornadaId;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public int getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }

    public int getVacunaId() {
        return vacunaId;
    }

    public void setVacunaId(int vacunaId) {
        this.vacunaId = vacunaId;
    }

    public Integer getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Integer veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getLoteVacuna() {
        return loteVacuna;
    }

    public void setLoteVacuna(String loteVacuna) {
        this.loteVacuna = loteVacuna;
    }

    public LocalDate getProximaDosis() {
        return proximaDosis;
    }

    public void setProximaDosis(LocalDate proximaDosis) {
        this.proximaDosis = proximaDosis;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
