
package com.happyfeet.view;

import com.happyfeet.util.ControladorErrores;
import com.happyfeet.controller.ReportesController;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReportesView {

    private final ReportesController controller;
    private final Scanner scanner;

    public ReportesView() {
        this.controller = new ReportesController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {

            System.out.println("\n================================");
            System.out.println("       REPORTES HAPPY FEET");
            System.out.println("================================");
            System.out.println("1. Servicios más solicitados");
            System.out.println("2. Desempeño de veterinarios");
            System.out.println("3. Productos con stock bajo");
            System.out.println("4. Productos próximos a vencer");
            System.out.println("5. Facturación por período");
            System.out.println("0. Regresar");
            System.out.println("================================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        mostrarResultados(
                                "SERVICIOS MÁS SOLICITADOS",
                                controller.consultarServiciosMasSolicitados()
                        );
                        break;

                    case 2:
                        mostrarResultados(
                                "DESEMPEÑO DE VETERINARIOS",
                                controller.consultarDesempenoVeterinarios()
                        );
                        break;

                    case 3:
                        mostrarResultados(
                                "PRODUCTOS CON STOCK BAJO",
                                controller.consultarProductosStockBajo()
                        );
                        break;

                    case 4:
                        mostrarResultados(
                                "PRODUCTOS PRÓXIMOS A VENCER",
                                controller.consultarProductosProximosVencer()
                        );
                        break;

                    case 5:
                        consultarFacturacion();
                        break;

                    case 0:
                        System.out.println("Regresando al menú principal...");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (NumberFormatException e) {
                opcion = -1;
                System.out.println("Ingrese una opción numérica válida.");
            }

        } while (opcion != 0);
    }

    private void consultarFacturacion() {

        try {

            System.out.println("\n===== FACTURACIÓN POR PERÍODO =====");

            System.out.print("Fecha inicial (AAAA-MM-DD): ");
            LocalDate fechaInicio =
                    LocalDate.parse(scanner.nextLine());

            System.out.print("Fecha final (AAAA-MM-DD): ");
            LocalDate fechaFin =
                    LocalDate.parse(scanner.nextLine());

            List<Map<String, Object>> resultados =
                    controller.consultarFacturacionPorPeriodo(
                            fechaInicio,
                            fechaFin
                    );

            mostrarResultados("FACTURACIÓN POR PERÍODO", resultados);

        } catch (DateTimeParseException e) {

            System.out.println(
                    "Formato de fecha inválido. Utilice AAAA-MM-DD."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }
    }

    private void mostrarResultados(
            String titulo,
            List<Map<String, Object>> resultados) {

        System.out.println("\n===== " + titulo + " =====");

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron registros.");
            return;
        }

        for (Map<String, Object> fila : resultados) {

            System.out.println("--------------------------------");

            for (Map.Entry<String, Object> dato : fila.entrySet()) {

                System.out.println(
                        dato.getKey() + ": " + dato.getValue()
                );
            }
        }

        System.out.println("--------------------------------");
        System.out.println("Total de registros: " + resultados.size());
    }
}

