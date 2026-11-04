
package com.happyfeet.view;

import com.happyfeet.util.ControladorErrores;
import com.happyfeet.controller.AdopcionController;
import com.happyfeet.model.Adopcion;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AdopcionView {

    private final AdopcionController controller;
    private final Scanner scanner;

    public AdopcionView() {
        this.controller = new AdopcionController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE ADOPCIONES =====");
            System.out.println("1. Registrar adopción");
            System.out.println("2. Buscar adopción");
            System.out.println("3. Listar adopciones");
            System.out.println("4. Actualizar adopción");
            System.out.println("5. Eliminar adopción");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            try {
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

            } catch (Exception e) {
                ControladorErrores.registrar(e);
                System.out.println("Ocurrió un error al procesar la operación.");
            }

        } while (opcion != 0);
    }

    private void registrar() {

        System.out.println("\n--- REGISTRAR ADOPCIÓN ---");

        Adopcion adopcion = new Adopcion();

        System.out.print("ID de la mascota en adopción: ");
        adopcion.setMascotaAdopcionId(leerEntero());

        System.out.print("ID del dueño: ");
        adopcion.setDuenoId(leerEntero());

        adopcion.setFechaAdopcion(leerFecha(
                "Fecha de adopción (yyyy-MM-dd): "));

        System.out.print("Texto del contrato: ");
        adopcion.setContratoTexto(scanner.nextLine());

        System.out.print("Condiciones especiales: ");
        adopcion.setCondicionesEspeciales(scanner.nextLine());

        adopcion.setSeguimientoRequerido(leerBooleano(
                "¿Requiere seguimiento? (S/N): "));

        System.out.print("Fecha del primer seguimiento (yyyy-MM-dd): ");
        adopcion.setFechaPrimerSeguimiento(leerFechaOpcional());

        if (controller.registrar(adopcion)) {
            System.out.println("Adopción registrada correctamente.");
        } else {
            System.out.println("No se pudo registrar la adopción.");
        }
    }

    private void buscar() {

        System.out.println("\n--- BUSCAR ADOPCIÓN ---");
        System.out.print("Ingrese el ID: ");

        int id = leerEntero();

        Adopcion adopcion = controller.buscarPorId(id);

        if (adopcion != null) {
            mostrarAdopcion(adopcion);
        } else {
            System.out.println("No se encontró la adopción.");
        }
    }

    private void listar() {

        System.out.println("\n--- LISTA DE ADOPCIONES ---");

        List<Adopcion> adopciones = controller.listarTodos();

        if (adopciones.isEmpty()) {
            System.out.println("No hay adopciones registradas.");
            return;
        }

        for (Adopcion adopcion : adopciones) {
            mostrarAdopcion(adopcion);
            System.out.println("------------------------------");
        }
    }

    private void actualizar() {

        System.out.println("\n--- ACTUALIZAR ADOPCIÓN ---");
        System.out.print("Ingrese el ID de la adopción: ");

        int id = leerEntero();

        Adopcion adopcion = controller.buscarPorId(id);

        if (adopcion == null) {
            System.out.println("No se encontró la adopción.");
            return;
        }

        System.out.println("Adopción encontrada. Ingrese los nuevos datos:");

        System.out.print("ID de la mascota en adopción: ");
        adopcion.setMascotaAdopcionId(leerEntero());

        System.out.print("ID del dueño: ");
        adopcion.setDuenoId(leerEntero());

        adopcion.setFechaAdopcion(leerFecha(
                "Fecha de adopción (yyyy-MM-dd): "));

        System.out.print("Texto del contrato: ");
        adopcion.setContratoTexto(scanner.nextLine());

        System.out.print("Condiciones especiales: ");
        adopcion.setCondicionesEspeciales(scanner.nextLine());

        adopcion.setSeguimientoRequerido(leerBooleano(
                "¿Requiere seguimiento? (S/N): "));

        System.out.print("Fecha del primer seguimiento (yyyy-MM-dd): ");
        adopcion.setFechaPrimerSeguimiento(leerFechaOpcional());

        if (controller.actualizar(adopcion)) {
            System.out.println("Adopción actualizada correctamente.");
        } else {
            System.out.println("No se pudo actualizar la adopción.");
        }
    }

    private void eliminar() {

        System.out.println("\n--- ELIMINAR ADOPCIÓN ---");
        System.out.print("Ingrese el ID: ");

        int id = leerEntero();

        if (controller.eliminar(id)) {
            System.out.println("Adopción eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la adopción.");
        }
    }

    private void mostrarAdopcion(Adopcion adopcion) {

        System.out.println("\nID: " + adopcion.getId());
        System.out.println("ID de mascota: " + adopcion.getMascotaAdopcionId());
        System.out.println("ID del dueño: " + adopcion.getDuenoId());
        System.out.println("Fecha de adopción: " + adopcion.getFechaAdopcion());
        System.out.println("Contrato: " + adopcion.getContratoTexto());
        System.out.println("Condiciones especiales: " + adopcion.getCondicionesEspeciales());
        System.out.println("Seguimiento requerido: " + adopcion.getSeguimientoRequerido());
        System.out.println("Fecha del primer seguimiento: " + adopcion.getFechaPrimerSeguimiento());
    }

    private int leerEntero() {

        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    private LocalDate leerFecha(String mensaje) {

        while (true) {
            System.out.print(mensaje);

            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Utilice yyyy-MM-dd.");
            }
        }
    }

    private LocalDate leerFechaOpcional() {

        String fecha = scanner.nextLine().trim();

        if (fecha.isEmpty()) {
            return null;
        }

        try {
            return LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            System.out.println("Fecha inválida. Se dejará vacía.");
            return null;
        }
    }

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
