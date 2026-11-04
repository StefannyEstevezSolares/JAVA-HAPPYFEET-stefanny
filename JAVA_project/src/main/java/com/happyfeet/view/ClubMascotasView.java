
package com.happyfeet.view;

import com.happyfeet.util.ControladorErrores;
import com.happyfeet.controller.ClubMascotasController;
import com.happyfeet.model.ClubMascotas;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ClubMascotasView {

    private final ClubMascotasController controller;
    private final Scanner scanner;

    public ClubMascotasView() {
        this.controller = new ClubMascotasController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n===== CLUB DE MASCOTAS =====");
            System.out.println("1. Registrar membresía");
            System.out.println("2. Buscar membresía");
            System.out.println("3. Listar membresías");
            System.out.println("4. Actualizar membresía");
            System.out.println("5. Eliminar membresía");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    buscar();
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    actualizar();
                    break;
                case 5:
                    eliminar();
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    // Registrar membresía
    private void registrar() {

        System.out.println("\n--- REGISTRAR MEMBRESÍA ---");

        ClubMascotas club = new ClubMascotas();

        System.out.print("ID del dueño: ");
        club.setDuenoId(leerEntero());

        System.out.print("Puntos acumulados: ");
        club.setPuntosAcumulados(leerEntero());

        System.out.print("Puntos canjeados: ");
        club.setPuntosCanjeados(leerEntero());

        System.out.print("Puntos disponibles: ");
        club.setPuntosDisponibles(leerEntero());

        System.out.print("Nivel de membresía: ");
        club.setNivel(scanner.nextLine());

        club.setFechaInscripcion(LocalDate.now());
        club.setFechaUltimaActualizacion(LocalDateTime.now());

        club.setActivo(leerBooleano("¿Membresía activa? (S/N): "));

        if (controller.registrar(club)) {
            System.out.println("Membresía registrada correctamente.");
        } else {
            System.out.println("No se pudo registrar la membresía.");
        }
    }

    // Buscar membresía
    private void buscar() {

        System.out.println("\n--- BUSCAR MEMBRESÍA ---");
        System.out.print("Ingrese el ID: ");

        int id = leerEntero();

        ClubMascotas club = controller.buscarPorId(id);

        if (club != null) {
            mostrarClub(club);
        } else {
            System.out.println("No se encontró la membresía.");
        }
    }

    // Listar membresías
    private void listar() {

        System.out.println("\n--- LISTA DE MEMBRESÍAS ---");

        List<ClubMascotas> clubes = controller.listarTodos();

        if (clubes.isEmpty()) {
            System.out.println("No hay membresías registradas.");
            return;
        }

        for (ClubMascotas club : clubes) {
            mostrarClub(club);
            System.out.println("------------------------------");
        }
    }

    // Actualizar membresía
    private void actualizar() {

        System.out.println("\n--- ACTUALIZAR MEMBRESÍA ---");
        System.out.print("Ingrese el ID de la membresía: ");

        int id = leerEntero();

        ClubMascotas club = controller.buscarPorId(id);

        if (club == null) {
            System.out.println("No se encontró la membresía.");
            return;
        }

        System.out.println("Membresía encontrada. Ingrese los nuevos datos:");

        System.out.print("ID del dueño: ");
        club.setDuenoId(leerEntero());

        System.out.print("Puntos acumulados: ");
        club.setPuntosAcumulados(leerEntero());

        System.out.print("Puntos canjeados: ");
        club.setPuntosCanjeados(leerEntero());

        System.out.print("Puntos disponibles: ");
        club.setPuntosDisponibles(leerEntero());

        System.out.print("Nivel de membresía: ");
        club.setNivel(scanner.nextLine());

        club.setActivo(leerBooleano("¿Membresía activa? (S/N): "));

        club.setFechaUltimaActualizacion(LocalDateTime.now());

        if (controller.actualizar(club)) {
            System.out.println("Membresía actualizada correctamente.");
        } else {
            System.out.println("No se pudo actualizar la membresía.");
        }
    }

    // Eliminar membresía
    private void eliminar() {

        System.out.println("\n--- ELIMINAR MEMBRESÍA ---");
        System.out.print("Ingrese el ID: ");

        int id = leerEntero();

        if (controller.eliminar(id)) {
            System.out.println("Membresía eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la membresía.");
        }
    }

    // Mostrar información
    private void mostrarClub(ClubMascotas club) {

        System.out.println("\nID: " + club.getId());
        System.out.println("ID del dueño: " + club.getDuenoId());
        System.out.println("Puntos acumulados: " + club.getPuntosAcumulados());
        System.out.println("Puntos canjeados: " + club.getPuntosCanjeados());
        System.out.println("Puntos disponibles: " + club.getPuntosDisponibles());
        System.out.println("Nivel: " + club.getNivel());
        System.out.println("Fecha de inscripción: " + club.getFechaInscripcion());
        System.out.println("Última actualización: " + club.getFechaUltimaActualizacion());
        System.out.println("Activo: " + club.getActivo());
    }

    // Leer entero
    private int leerEntero() {

        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    // Leer booleano
    private Boolean leerBooleano(String mensaje) {

        while (true) {
            System.out.print(mensaje);

            String respuesta = scanner.nextLine().trim();

            if (respuesta.equalsIgnoreCase("S")) {
                return true;
            }

            if (respuesta.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Ingrese S o N.");
        }
    }
}
