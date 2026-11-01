
package com.happyfeet.model;

import java.time.LocalDate;

public class MascotaAdopcion {

    private int id;
    private int mascotaId;
    private LocalDate fechaIngreso;
    private String motivoIngreso;
    private String estado;
    private String historia;
    private String temperamento;
    private String necesidadesEspeciales;
    private String fotoAdicionalUrl;

    public MascotaAdopcion() {
    }

    public MascotaAdopcion(int id, int mascotaId,
            LocalDate fechaIngreso, String motivoIngreso,
            String estado, String historia, String temperamento,
            String necesidadesEspeciales, String fotoAdicionalUrl) {

        this.id = id;
        this.mascotaId = mascotaId;
        this.fechaIngreso = fechaIngreso;
        this.motivoIngreso = motivoIngreso;
        this.estado = estado;
        this.historia = historia;
        this.temperamento = temperamento;
        this.necesidadesEspeciales = necesidadesEspeciales;
        this.fotoAdicionalUrl = fotoAdicionalUrl;
    }

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

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getMotivoIngreso() {
        return motivoIngreso;
    }

    public void setMotivoIngreso(String motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(String temperamento) {
        this.temperamento = temperamento;
    }

    public String getNecesidadesEspeciales() {
        return necesidadesEspeciales;
    }

    public void setNecesidadesEspeciales(String necesidadesEspeciales) {
        this.necesidadesEspeciales = necesidadesEspeciales;
    }

    public String getFotoAdicionalUrl() {
        return fotoAdicionalUrl;
    }

    public void setFotoAdicionalUrl(String fotoAdicionalUrl) {
        this.fotoAdicionalUrl = fotoAdicionalUrl;
    }
}
