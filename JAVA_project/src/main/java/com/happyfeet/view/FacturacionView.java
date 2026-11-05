package com.happyfeet.view;

import com.happyfeet.controller.FacturaController;
import com.happyfeet.facturacion.GeneradorFacturaPDF;
import com.happyfeet.model.Factura;
import com.happyfeet.service.FacturaService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
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
            System.out.println("6. Consultar Facturación por Cliente");
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
                case 6:
                    consultarFacturacionPorCliente();
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

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
                System.out.println("ID: " + factura.getId());
                System.out.println("Total calculado: Q" + factura.getTotal());

                GeneradorFacturaPDF generador =
                        new GeneradorFacturaPDF();

                String ruta = generador.generar(factura);

                System.out.println("PDF generado correctamente.");
                System.out.println("Ubicación: " + ruta);

            } else {

                System.out.println("No se pudo registrar la factura.");
            }

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (IOException e) {

            System.out.println(
                    "La factura se registró, pero ocurrió un error al generar el PDF."
            );

            System.out.println(e.getMessage());
        }
    }

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

    private void eliminar() {

        System.out.println("\n--- ELIMINAR FACTURA ---");

        System.out.print("Ingrese el ID de la factura: ");
        int id = leerEntero();

        try {

            if (controller.eliminar(id)) {
                System.out.println("Factura eliminada correctamente.");
            } else {
                System.out.println("No se encontró la factura.");
            }

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
    //Consultar Facturación por cliente
    
    private void consultarFacturacionPorCliente() {

        System.out.println("\n--- CONSULTAR FACTURACIÓN POR CLIENTE ---");

        System.out.print("Ingrese el documento de identidad del dueño: ");
        String documento = scanner.nextLine();

        try {

            FacturaService.ReporteFacturacionCliente reporte =
                    controller.generarReporteFacturacionPorCliente(documento);

            if (reporte == null) {
                System.out.println(
                        "Error: No se encontró ningún dueño con el documento proporcionado"
                );
                return;
            }

            DateTimeFormatter formatoFecha =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            NumberFormat formatoMoneda =
                    NumberFormat.getInstance(Locale.forLanguageTag("es-CO"));

            formatoMoneda.setMaximumFractionDigits(0);
            formatoMoneda.setMinimumFractionDigits(0);

            System.out.println("\nINFORME DE FACTURACIÓN POR CLIENTE");
            System.out.println("DATOS DEL CLIENTE");
            System.out.println("Nombre: " + reporte.getDueno().getNombreCompleto());
            System.out.println("Documento: " + reporte.getDueno().getDocumentoIdentidad());

            System.out.println("\nHISTORIAL DE FACTURAS");
            System.out.println("ID Factura | Fecha de Emisión | Total Factura");

            List<Factura> facturas = reporte.getFacturas();

            for (Factura factura : facturas) {

                String fecha = "Sin fecha";

                if (factura.getFechaEmision() != null) {
                    fecha = factura.getFechaEmision().format(formatoFecha);
                }

                System.out.println(
                        factura.getId() + " | "
                        + fecha + " | $ "
                        + formatoMoneda.format(factura.getTotal())
                );
            }

            System.out.println(
                    "Monto Total General Facturado: $ "
                    + formatoMoneda.format(reporte.getTotalGeneral())
            );

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (java.sql.SQLException e) {

            System.out.println("Error al consultar facturación: " + e.getMessage());
        }
    }

    private void mostrarFactura(Factura factura) {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\nID: " + factura.getId());
        System.out.println("Número de factura: " + factura.getNumeroFactura());

        if (factura.getFechaEmision() != null) {
            System.out.println(
                    "Fecha de emisión: "
                    + factura.getFechaEmision().format(formato)
            );
        }

        System.out.println("ID del dueño: " + factura.getDuenoId());
        System.out.println("Subtotal: Q" + factura.getSubtotal());
        System.out.println("Impuesto: Q" + factura.getImpuesto());
        System.out.println("Descuento: Q" + factura.getDescuento());
        System.out.println("Total: Q" + factura.getTotal());
        System.out.println("Método de pago: " + factura.getMetodoPago());
        System.out.println("Estado: " + factura.getEstado());
        System.out.println("Observaciones: " + factura.getObservaciones());
    }

    private int leerEntero() {

        while (true) {

            try {

                int valor = Integer.parseInt(scanner.nextLine());
                return valor;

            } catch (NumberFormatException e) {

                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    private BigDecimal leerDecimal() {

        while (true) {

            try {

                BigDecimal valor = new BigDecimal(scanner.nextLine());
                return valor;

            } catch (NumberFormatException e) {

                System.out.print("Ingrese un importe válido: ");
            }
        }
    }
}