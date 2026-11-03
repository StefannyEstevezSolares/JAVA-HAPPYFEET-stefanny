
package com.happyfeet.view;

import com.happyfeet.controller.RegistroJornadaVacunacionController;
import com.happyfeet.model.RegistroJornadaVacunacion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class VacunacionView {

    private final RegistroJornadaVacunacionController controller;
    private final Scanner scanner;

    public VacunacionView() {
        this.controller = new RegistroJornadaVacunacionController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE VACUNACIÓN =====");
            System.out.println("1. Registrar vacunación");
            System.out.println("2. Buscar vacunación");
            System.out.println("3. Listar vacunaciones");
            System.out.println("4. Actualizar vacunación");
            System.out.println("5. Eliminar vacunación");
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

    // Registrar vacunación
    private void registrar() {

        System.out.println("\n--- REGISTRAR VACUNACIÓN ---");

        RegistroJornadaVacunacion registro =
                new RegistroJornadaVacunacion();

        System.out.print("ID de la jornada: ");
        registro.setJornadaId(leerEntero());

        System.out.print("ID de la mascota: ");
        registro.setMascotaId(leerEntero());

        System.out.print("ID del dueño: ");
        registro.setDuenoId(leerEntero());

        System.out.print("ID de la vacuna: ");
        registro.setVacunaId(leerEntero());

        registro.setVeterinarioId(
                leerEnteroOpcional("ID del veterinario (Enter para omitir): ")
        );

        System.out.print("Lote de la vacuna: ");
        registro.setLoteVacuna(scanner.nextLine());

        registro.setProximaDosis(
                leerFechaOpcional(
                        "Próxima dosis (yyyy-MM-dd, Enter para omitir): "
                )
        );

        System.out.print("Observaciones: ");
        registro.setObservaciones(scanner.nextLine());

        registro.setFechaHora(LocalDateTime.now());

        if (controller.registrar(registro)) {
            System.out.println("Registro de vacunación creado correctamente.");
        } else {
            System.out.println("No se pudo registrar la vacunación.");
        }
    }

    // Buscar vacunación
    private void buscar() {

        System.out.println("\n--- BUSCAR VACUNACIÓN ---");
        System.out.print("Ingrese el ID: ");

        int id = leerEntero();

        RegistroJornadaVacunacion registro =
                controller.buscarPorId(id);

        if (registro != null) {
            mostrarRegistro(registro);
        } else {
            System.out.println("No se encontró el registro.");
        }
    }

    // Listar vacunaciones
    private void listar() {

        System.out.println("\n--- LISTA DE VACUNACIONES ---");

        List<RegistroJornadaVacunacion> registros =
                controller.listarTodos();

        if (registros.isEmpty()) {
            System.out.println("No hay vacunaciones registradas.");
            return;
        }

        for (RegistroJornadaVacunacion registro : registros) {
            mostrarRegistro(registro);
            System.out.println("------------------------------");
        }
    }

    // Actualizar vacunación
    private void actualizar() {

        System.out.println("\n--- ACTUALIZAR VACUNACIÓN ---");
        System.out.print("Ingrese el ID del registro: ");

        int id = leerEntero();

        RegistroJornadaVacunacion registro =
                controller.buscarPorId(id);

        if (registro == null) {
            System.out.println("No se encontró el registro.");
            return;
        }

        System.out.println("Registro encontrado. Ingrese los nuevos datos:");

        System.out.print("ID de la jornada: ");
        registro.setJornadaId(leerEntero());

        System.out.print("ID de la mascota: ");
        registro.setMascotaId(leerEntero());

        System.out.print("ID del dueño: ");
        registro.setDuenoId(leerEntero());

        System.out.print("ID de la vacuna: ");
        registro.setVacunaId(leerEntero());

        registro.setVeterinarioId(
                leerEnteroOpcional("ID del veterinario (Enter para omitir): ")
        );

        System.out.print("Lote de la vacuna: ");
        registro.setLoteVacuna(scanner.nextLine());

        registro.setProximaDosis(
                leerFechaOpcional(
                        "Próxima dosis (yyyy-MM-dd, Enter para omitir): "
                )
        );

        System.out.print("Observaciones: ");
        registro.setObservaciones(scanner.nextLine());

        if (controller.actualizar(registro)) {
            System.out.println("Registro actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el registro.");
        }
    }

    // Eliminar vacunación
    private void eliminar() {

        System.out.println("\n--- ELIMINAR VACUNACIÓN ---");
        System.out.print("Ingrese el ID: ");

        int id = leerEntero();

        if (controller.eliminar(id)) {
            System.out.println("Registro eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el registro.");
        }
    }

    // Mostrar información
    private void mostrarRegistro(RegistroJornadaVacunacion registro) {

        System.out.println("\nID: " + registro.getId());
        System.out.println("ID de jornada: " + registro.getJornadaId());
        System.out.println("ID de mascota: " + registro.getMascotaId());
        System.out.println("ID del dueño: " + registro.getDuenoId());
        System.out.println("ID de vacuna: " + registro.getVacunaId());
        System.out.println("ID de veterinario: " + registro.getVeterinarioId());
        System.out.println("Fecha y hora: " + registro.getFechaHora());
        System.out.println("Lote de vacuna: " + registro.getLoteVacuna());
        System.out.println("Próxima dosis: " + registro.getProximaDosis());
        System.out.println("Observaciones: " + registro.getObservaciones());
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

    // Leer entero opcional
    private Integer leerEnteroOpcional(String mensaje) {

        System.out.print(mensaje);

        String valor = scanner.nextLine().trim();

        if (valor.isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Se dejará vacío.");
            return null;
        }
    }

    // Leer fecha opcional
    private LocalDate leerFechaOpcional(String mensaje) {

        while (true) {
            System.out.print(mensaje);

            String valor = scanner.nextLine().trim();

            if (valor.isEmpty()) {
                return null;
            }

            try {
                return LocalDate.parse(valor);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Utilice yyyy-MM-dd.");
            }
        }
    }
}
