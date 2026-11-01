
package com.happyfeet.model;

import java.math.BigDecimal;

public class BeneficioClub {

    private int id;
    private String nombre;
    private String descripcion;
    private String nivelRequerido;
    private int puntosNecesarios;
    private String tipoBeneficio;
    private BigDecimal valorBeneficio;
    private Boolean activo;

    public BeneficioClub() {
    }

    public BeneficioClub(int id, String nombre, String descripcion,
            String nivelRequerido, int puntosNecesarios,
            String tipoBeneficio, BigDecimal valorBeneficio,
            Boolean activo) {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelRequerido = nivelRequerido;
        this.puntosNecesarios = puntosNecesarios;
        this.tipoBeneficio = tipoBeneficio;
        this.valorBeneficio = valorBeneficio;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivelRequerido() {
        return nivelRequerido;
    }

    public void setNivelRequerido(String nivelRequerido) {
        this.nivelRequerido = nivelRequerido;
    }

    public int getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public void setPuntosNecesarios(int puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }

    public String getTipoBeneficio() {
        return tipoBeneficio;
    }

    public void setTipoBeneficio(String tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }

    public BigDecimal getValorBeneficio() {
        return valorBeneficio;
    }

    public void setValorBeneficio(BigDecimal valorBeneficio) {
        this.valorBeneficio = valorBeneficio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
