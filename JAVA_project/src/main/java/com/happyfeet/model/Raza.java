
package com.happyfeet.model;

public class Raza {

    private int id;
    private int especieId;
    private String nombre;
    private String caracteristicas;

    public Raza() {
    }

    public Raza(int id, int especieId, String nombre, String caracteristicas) {
        this.id = id;
        this.especieId = especieId;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEspecieId() {
        return especieId;
    }

    public void setEspecieId(int especieId) {
        this.especieId = especieId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
