
package com.happyfeet.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CanjeBeneficio {

    private int id;
    private int clubMascotasId;
    private int beneficioId;
    private LocalDateTime fechaCanje;
    private int puntosCanjeados;
    private String estado;
    private LocalDate fechaExpiracion;
    private Integer facturaId;

    public CanjeBeneficio() {
    }

    public CanjeBeneficio(int id, int clubMascotasId, int beneficioId,
            LocalDateTime fechaCanje, int puntosCanjeados,
            String estado, LocalDate fechaExpiracion, Integer facturaId) {

        this.id = id;
        this.clubMascotasId = clubMascotasId;
        this.beneficioId = beneficioId;
        this.fechaCanje = fechaCanje;
        this.puntosCanjeados = puntosCanjeados;
        this.estado = estado;
        this.fechaExpiracion = fechaExpiracion;
        this.facturaId = facturaId;
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

    public int getBeneficioId() {
        return beneficioId;
    }

    public void setBeneficioId(int beneficioId) {
        this.beneficioId = beneficioId;
    }

    public LocalDateTime getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(LocalDateTime fechaCanje) {
        this.fechaCanje = fechaCanje;
    }

    public int getPuntosCanjeados() {
        return puntosCanjeados;
    }

    public void setPuntosCanjeados(int puntosCanjeados) {
        this.puntosCanjeados = puntosCanjeados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }
}
