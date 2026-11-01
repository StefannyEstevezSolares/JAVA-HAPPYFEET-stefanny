
package com.happyfeet.model;

import java.time.LocalDateTime;

public class TransaccionPuntos {

    private int id;
    private int clubMascotasId;
    private Integer facturaId;
    private int puntos;
    private String tipo;
    private LocalDateTime fecha;
    private String descripcion;
    private int saldoAnterior;
    private int saldoNuevo;

    public TransaccionPuntos() {
    }

    public TransaccionPuntos(int id, int clubMascotasId,
            Integer facturaId, int puntos, String tipo,
            LocalDateTime fecha, String descripcion,
            int saldoAnterior, int saldoNuevo) {

        this.id = id;
        this.clubMascotasId = clubMascotasId;
        this.facturaId = facturaId;
        this.puntos = puntos;
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.saldoAnterior = saldoAnterior;
        this.saldoNuevo = saldoNuevo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClubMascotasId() {
        return clubMascotasId;
    }

    public void setClubMascotasId(int clubMascotasId) {
        this.clubMascotasId = clubMascotasId;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(int saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public int getSaldoNuevo() {
        return saldoNuevo;
    }

    public void setSaldoNuevo(int saldoNuevo) {
        this.saldoNuevo = saldoNuevo;
    }
}
