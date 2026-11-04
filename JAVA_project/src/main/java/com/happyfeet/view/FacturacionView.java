
package com.happyfeet.view;

import com.happyfeet.controller.FacturaController;
import com.happyfeet.model.Factura;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class FacturacionView {

    private final FacturaController controller;
    private final Scanner scanner;

    public FacturacionView() {
        this.controller = new FacturaController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE FACTURACIÓN =====");
            System.out.println("1. Registrar factura");
            System.out.println("2. Buscar factura");
            System.out.println("3. Listar facturas");
            System.out.println("4. Actualizar factura");
            System.out.println("5. Eliminar factura");
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

    // INTEGRACIÓN STRATEGY
    // El total será calculado por FacturaService.

    private void registrar() {

        System.out.println("\n--- REGISTRAR FACTURA ---");

        Factura factura = new Factura();

        System.out.print("ID del dueño: ");
        factura.setDuenoId(leerEntero());

        System.out.print("Número de factura: ");
        factura.setNumeroFactura(scanner.nextLine());

        factura.setFechaEmision(LocalDateTime.now());

        System.out.print("Subtotal: ");
        factura.setSubtotal(leerDecimal());

        System.out.print("Impuesto: ");
        factura.setImpuesto(leerDecimal());

        System.out.print("Descuento: ");
        factura.setDescuento(leerDecimal());

        System.out.print("Método de pago: ");
        factura.setMetodoPago(scanner.nextLine());

        System.out.print("Estado: ");
        factura.setEstado(scanner.nextLine());

        System.out.print("Observaciones: ");
        factura.setObservaciones(scanner.nextLine());

        try {

            if (controller.registrar(factura)) {

                System.out.println("Factura registrada correctamente.");
                System.out.println("Total calculado: Q" + factura.getTotal());

            } else {
                System.out.println("No se pudo registrar la factura.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // Buscar factura
    private void buscar() {

        System.out.println("\n--- BUSCAR FACTURA ---");
        System.out.print("Ingrese el ID: ");

        int id = leerEntero();

        Factura factura = controller.buscarPorId(id);

        if (factura != null) {
            mostrarFactura(factura);
        } else {
            System.out.println("No se encontró la factura.");
        }
    }

    // Listar facturas
    private void listar() {

        System.out.println("\n--- LISTA DE FACTURAS ---");

        List<Factura> facturas = controller.listarTodos();

        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
            return;
        }

        for (Factura factura : facturas) {
            mostrarFactura(factura);
            System.out.println("------------------------------");
        }
    }

    // ==================================================
    // INTEGRACIÓN STRATEGY
    // INICIO: ACTUALIZACIÓN DE FACTURA
    // El total se vuelve a calcular antes de actualizar.
    // ==================================================

    private void actualizar() {

        System.out.println("\n--- ACTUALIZAR FACTURA ---");
        System.out.print("Ingrese el ID de la factura: ");

        int id = leerEntero();

        Factura factura = controller.buscarPorId(id);

        if (factura == null) {
            System.out.println("No se encontró la factura.");
            return;
        }

        System.out.println("Factura encontrada. Ingrese los nuevos datos:");

        System.out.print("ID del dueño: ");
        factura.setDuenoId(leerEntero());

        System.out.print("Número de factura: ");
        factura.setNumeroFactura(scanner.nextLine());

        System.out.print("Subtotal: ");
        factura.setSubtotal(leerDecimal());

        System.out.print("Impuesto: ");
        factura.setImpuesto(leerDecimal());

        System.out.print("Descuento: ");
        factura.setDescuento(leerDecimal());

        System.out.print("Método de pago: ");
        factura.setMetodoPago(scanner.nextLine());

        System.out.print("Estado: ");
        factura.setEstado(scanner.nextLine());

        System.out.print("Observaciones: ");
        factura.setObservaciones(scanner.nextLine());

        try {

            if (controller.actualizar(factura)) {

                System.out.println("Factura actualizada correctamente.");
                System.out.println("Total calculado: Q" + factura.getTotal());

            } else {
                System.out.println("No se pudo actualizar la factura.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // Eliminar factura
    private void eliminar() {

        System.out.println("\n--- ELIMINAR FACTURA ---");
        System.out.print("Ingrese el ID: ");

        int id = leerEntero();

        if (controller.eliminar(id)) {
            System.out.println("Factura eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la factura.");
        }
    }

    // Mostrar información de la factura
    private void mostrarFactura(Factura factura) {

        System.out.println("\nID: " + factura.getId());
        System.out.println("ID del dueño: " + factura.getDuenoId());
        System.out.println("Número de factura: " + factura.getNumeroFactura());
        System.out.println("Fecha de emisión: " + factura.getFechaEmision());
        System.out.println("Subtotal: Q" + factura.getSubtotal());
        System.out.println("Impuesto: Q" + factura.getImpuesto());
        System.out.println("Descuento: Q" + factura.getDescuento());
        System.out.println("Total: Q" + factura.getTotal());
        System.out.println("Método de pago: " + factura.getMetodoPago());
        System.out.println("Estado: " + factura.getEstado());
        System.out.println("Observaciones: " + factura.getObservaciones());
    }

    // Leer número entero
    private int leerEntero() {

        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    // Leer importe decimal
    private BigDecimal leerDecimal() {

        while (true) {
            try {
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un importe válido: ");
            }
        }
    }
}
