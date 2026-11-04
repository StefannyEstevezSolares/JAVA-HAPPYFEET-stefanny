
package com.happyfeet.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MascotaBuilder {

    private int duenoId;
    private String nombre;
    private int razaId;
    private LocalDate fechaNacimiento;
    private String sexo = "No especificado";
    private BigDecimal pesoActual;
    private String microchip;
    private String tatuaje;
    private String urlFoto;
    private String alergias;
    private String condicionesPreexistentes;
    private boolean activo = true;

    // INICIO: PATRÓN BUILDER

    public MascotaBuilder(int duenoId, String nombre, int razaId) {
        this.duenoId = duenoId;
        this.nombre = nombre;
        this.razaId = razaId;
    }

    public MascotaBuilder fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public MascotaBuilder sexo(String sexo) {
        if (sexo != null && !sexo.isBlank()) {
            this.sexo = sexo;
        }
        return this;
    }

    public MascotaBuilder pesoActual(BigDecimal pesoActual) {
        this.pesoActual = pesoActual;
        return this;
    }

    public MascotaBuilder microchip(String microchip) {
        this.microchip = microchip;
        return this;
    }

    public MascotaBuilder tatuaje(String tatuaje) {
        this.tatuaje = tatuaje;
        return this;
    }

    public MascotaBuilder urlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
        return this;
    }

    public MascotaBuilder alergias(String alergias) {
        this.alergias = alergias;
        return this;
    }

    public MascotaBuilder condicionesPreexistentes(String condicionesPreexistentes) {
        this.condicionesPreexistentes = condicionesPreexistentes;
        return this;
    }

    public MascotaBuilder activo(boolean activo) {
        this.activo = activo;
        return this;
    }

    public Mascota build() {

        if (duenoId <= 0) {
            throw new IllegalArgumentException(
                    "El ID del dueño debe ser válido."
            );
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre de la mascota es obligatorio."
            );
        }

        if (razaId <= 0) {
            throw new IllegalArgumentException(
                    "El ID de la raza debe ser válido."
            );
        }

        if (pesoActual != null && pesoActual.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "El peso no puede ser negativo."
            );
        }

        Mascota mascota = new Mascota();

        mascota.setDuenoId(duenoId);
        mascota.setNombre(nombre);
        mascota.setRazaId(razaId);
        mascota.setFechaNacimiento(fechaNacimiento);
        mascota.setSexo(sexo);
        mascota.setPesoActual(pesoActual);
        mascota.setMicrochip(microchip);
        mascota.setTatuaje(tatuaje);
        mascota.setUrlFoto(urlFoto);
        mascota.setAlergias(alergias);
        mascota.setCondicionesPreexistentes(condicionesPreexistentes);
        mascota.setActivo(activo);

        return mascota;
    }

}
