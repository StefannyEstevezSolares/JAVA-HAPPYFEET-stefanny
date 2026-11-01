
package com.happyfeet.model;

import java.time.LocalDate;

public class Adopcion {

    private int id;
    private int mascotaAdopcionId;
    private int duenoId;
    private LocalDate fechaAdopcion;
    private String contratoTexto;
    private String condicionesEspeciales;
    private Boolean seguimientoRequerido;
    private LocalDate fechaPrimerSeguimiento;

    public Adopcion() {
    }

    public Adopcion(int id, int mascotaAdopcionId, int duenoId,
            LocalDate fechaAdopcion, String contratoTexto,
            String condicionesEspeciales, Boolean seguimientoRequerido,
            LocalDate fechaPrimerSeguimiento) {

        this.id = id;
        this.mascotaAdopcionId = mascotaAdopcionId;
        this.duenoId = duenoId;
        this.fechaAdopcion = fechaAdopcion;
        this.contratoTexto = contratoTexto;
        this.condicionesEspeciales = condicionesEspeciales;
        this.seguimientoRequerido = seguimientoRequerido;
        this.fechaPrimerSeguimiento = fechaPrimerSeguimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMascotaAdopcionId() {
        return mascotaAdopcionId;
    }

    public void setMascotaAdopcionId(int mascotaAdopcionId) {
        this.mascotaAdopcionId = mascotaAdopcionId;
    }

    public int getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }

    public LocalDate getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(LocalDate fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    public String getContratoTexto() {
        return contratoTexto;
    }

    public void setContratoTexto(String contratoTexto) {
        this.contratoTexto = contratoTexto;
    }

    public String getCondicionesEspeciales() {
        return condicionesEspeciales;
    }

    public void setCondicionesEspeciales(String condicionesEspeciales) {
        this.condicionesEspeciales = condicionesEspeciales;
    }

    public Boolean getSeguimientoRequerido() {
        return seguimientoRequerido;
    }

    public void setSeguimientoRequerido(Boolean seguimientoRequerido) {
        this.seguimientoRequerido = seguimientoRequerido;
    }

    public LocalDate getFechaPrimerSeguimiento() {
        return fechaPrimerSeguimiento;
    }

    public void setFechaPrimerSeguimiento(LocalDate fechaPrimerSeguimiento) {
        this.fechaPrimerSeguimiento = fechaPrimerSeguimiento;
    }
}
