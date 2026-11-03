
package com.happyfeet.view;

import com.happyfeet.controller.PrescripcionController;
import com.happyfeet.model.Prescripcion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class PrescripcionView {

    private final PrescripcionController controller;
    private final Scanner scanner;

    public PrescripcionView() {
        this.controller = new PrescripcionController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE PRESCRIPCIONES =====");
            System.out.println("1. Registrar prescripción");
            System.out.println("2. Buscar prescripción");
            System.out.println("3. Listar prescripciones");
            System.out.println("4. Actualizar prescripción");
            System.out.println("5. Eliminar prescripción");
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
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    // Registrar
    private void registrar() {

        System.out.println("\n--- REGISTRAR PRESCRIPCIÓN ---");

        Prescripcion p = new Prescripcion();

        p.setConsultaId(leerEnteroOpcional("ID de consulta (Enter para omitir): "));
        p.setProcedimientoId(leerEnteroOpcional("ID de procedimiento (Enter para omitir): "));

        System.out.print("ID del producto: ");
        p.setProductoId(leerEntero());

        System.out.print("Cantidad: ");
        p.setCantidad(leerEntero());

        System.out.print("Dosis: ");
        p.setDosis(scanner.nextLine());

        System.out.print("Frecuencia: ");
        p.setFrecuencia(scanner.nextLine());

        p.setDuracionDias(leerEnteroOpcional("Duración en días (Enter para omitir): "));

        System.out.print("Instrucciones: ");
        p.setInstrucciones(scanner.nextLine());

        p.setFechaPrescripcion(LocalDateTime.now());

        if (controller.registrar(p)) {
            System.out.println("Prescripción registrada correctamente.");
        } else {
            System.out.println("No se pudo registrar la prescripción.");
        }
    }

    // Buscar
    private void buscar() {

        System.out.println("\n--- BUSCAR PRESCRIPCIÓN ---");
        System.out.print("Ingrese el ID: ");

        int id = leerEntero();

        Prescripcion p = controller.buscarPorId(id);

        if (p != null) {
            mostrarPrescripcion(p);
        } else {
            System.out.println("No se encontró la prescripción.");
        }
    }

    // Listar
    private void listar() {

        System.out.println("\n--- LISTA DE PRESCRIPCIONES ---");

        List<Prescripcion> prescripciones = controller.listarTodos();

        if (prescripciones.isEmpty()) {
            System.out.println("No hay prescripciones registradas.");
            return;
        }

        for (Prescripcion p : prescripciones) {
            mostrarPrescripcion(p);
            System.out.println("------------------------------");
        }
    }

    // Actualizar
    private void actualizar() {

        System.out.println("\n--- ACTUALIZAR PRESCRIPCIÓN ---");
        System.out.print("Ingrese el ID de la prescripción: ");

        int id = leerEntero();

        Prescripcion p = controller.buscarPorId(id);

        if (p == null) {
            System.out.println("No se encontró la prescripción.");
            return;
        }

        System.out.println("Prescripción encontrada. Ingrese los nuevos datos:");

        p.setConsultaId(leerEnteroOpcional("ID de consulta (Enter para omitir): "));
        p.setProcedimientoId(leerEnteroOpcional("ID de procedimiento (Enter para omitir): "));

        System.out.print("ID del producto: ");
        p.setProductoId(leerEntero());

        System.out.print("Cantidad: ");
        p.setCantidad(leerEntero());

        System.out.print("Dosis: ");
        p.setDosis(scanner.nextLine());

        System.out.print("Frecuencia: ");
        p.setFrecuencia(scanner.nextLine());

        p.setDuracionDias(leerEnteroOpcional("Duración en días (Enter para omitir): "));

        System.out.print("Instrucciones: ");
        p.setInstrucciones(scanner.nextLine());

        if (controller.actualizar(p)) {
            System.out.println("Prescripción actualizada correctamente.");
        } else {
            System.out.println("No se pudo actualizar la prescripción.");
        }
    }

    // Eliminar
    private void eliminar() {

        System.out.println("\n--- ELIMINAR PRESCRIPCIÓN ---");
        System.out.print("Ingrese el ID: ");

        int id = leerEntero();

        if (controller.eliminar(id)) {
            System.out.println("Prescripción eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la prescripción.");
        }
    }

    // Mostrar información
    private void mostrarPrescripcion(Prescripcion p) {

        System.out.println("\nID: " + p.getId());
        System.out.println("ID de consulta: " + p.getConsultaId());
        System.out.println("ID de procedimiento: " + p.getProcedimientoId());
        System.out.println("ID del producto: " + p.getProductoId());
        System.out.println("Cantidad: " + p.getCantidad());
        System.out.println("Dosis: " + p.getDosis());
        System.out.println("Frecuencia: " + p.getFrecuencia());
        System.out.println("Duración (días): " + p.getDuracionDias());
        System.out.println("Instrucciones: " + p.getInstrucciones());
        System.out.println("Fecha: " + p.getFechaPrescripcion());
    }

    // Leer número entero
    private int leerEntero() {

        while (true) {
            try {
                int numero = Integer.parseInt(scanner.nextLine().trim());
                return numero;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    // Leer número entero opcional
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
}
