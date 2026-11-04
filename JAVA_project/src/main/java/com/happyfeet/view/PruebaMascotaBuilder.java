
package com.happyfeet.view;

import com.happyfeet.controller.MascotaController;
import com.happyfeet.model.Mascota;
import com.happyfeet.model.MascotaBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PruebaMascotaBuilder {

    public static void main(String[] args) {

        MascotaController controller = new MascotaController();

        // PRUEBA 1: MASCOTA CON TODOS LOS DATOS

        System.out.println("===== PRUEBA 1: MASCOTA COMPLETA =====");

        try {

            Mascota mascotaCompleta = new MascotaBuilder(
                    1,
                    "Max Builder 2",
                    3
            )
                    .fechaNacimiento(LocalDate.of(2022, 5, 10))
                    .sexo("Macho")
                    .pesoActual(new BigDecimal("8.50"))
                    .microchip("BUILDER-002")
                    .tatuaje("MAX-002")
                    .urlFoto("https://example.com/max.jpg")
                    .alergias("Polen")
                    .condicionesPreexistentes("Ninguna")
                    .build();

            int id = controller.registrar(mascotaCompleta);

            System.out.println("Mascota registrada correctamente.");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + mascotaCompleta.getNombre());
            System.out.println("Sexo: " + mascotaCompleta.getSexo());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        // PRUEBA 2: MASCOTA SIN SEXO ESPECIFICADO

        System.out.println("\n===== PRUEBA 2: MASCOTA SIN SEXO =====");

        try {

            Mascota mascotaBasica = new MascotaBuilder(
                    1,
                    "Luna Builder 2",
                    2
            )
                    .microchip("BUILDER-003")
                    .build();

            int id = controller.registrar(mascotaBasica);

            System.out.println("Mascota registrada correctamente.");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + mascotaBasica.getNombre());
            System.out.println("Sexo: " + mascotaBasica.getSexo());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        // PRUEBA 3: MASCOTA CON SEXO ESPECIFICADO

        System.out.println("\n===== PRUEBA 3: MASCOTA CON SEXO =====");

        try {

            Mascota mascotaHembra = new MascotaBuilder(
                    1,
                    "Bella Builder",
                    2
            )
                    .sexo("Hembra")
                    .microchip("BUILDER-004")
                    .build();

            int id = controller.registrar(mascotaHembra);

            System.out.println("Mascota registrada correctamente.");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + mascotaHembra.getNombre());
            System.out.println("Sexo: " + mascotaHembra.getSexo());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n===== PRUEBAS BUILDER FINALIZADAS =====");
    }
}
