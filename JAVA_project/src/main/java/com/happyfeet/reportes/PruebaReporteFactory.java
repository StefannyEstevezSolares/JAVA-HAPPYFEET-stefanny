
package com.happyfeet.reportes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PruebaReporteFactory {

    public static void main(String[] args) {

        // RF33: Servicios más solicitados
        probarReporte(
                "SERVICIOS MÁS SOLICITADOS",
                new ServiciosFactory()
        );

        // RF34: Desempeño de veterinarios
        probarReporte(
                "DESEMPEÑO DE VETERINARIOS",
                new VeterinariosFactory()
        );

        // RF35: Productos con stock bajo
        probarReporte(
                "PRODUCTOS CON STOCK BAJO",
                new StockBajoFactory()
        );

        // RF35: Productos próximos a vencer
        probarReporte(
                "PRODUCTOS PRÓXIMOS A VENCER",
                new VencimientosFactory()
        );

        // RF36: Facturación por período
        probarReporte(
                "FACTURACIÓN POR PERÍODO",
                new FacturacionFactory(
                        LocalDate.of(2026, 1, 1),
                        LocalDate.of(2026, 12, 31)
                )
        );

        System.out.println("\nPRUEBAS DE FACTORY METHOD FINALIZADAS");
    }

    // ==================================================
    // MÉTODO AUXILIAR PARA PROBAR LAS FÁBRICAS
    // ==================================================

    private static void probarReporte(
            String titulo,
            ReporteFactory factory) {

        System.out.println("\n================================");
        System.out.println(titulo);
        System.out.println("================================");

        try {

            List<Map<String, Object>> resultados =
                    factory.generarReporte();

            if (resultados.isEmpty()) {
                System.out.println("No se encontraron registros.");
            } else {

                for (Map<String, Object> fila : resultados) {
                    System.out.println(fila);
                }
            }

            System.out.println(
                    "Total de registros: " + resultados.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al generar el reporte: " + e.getMessage()
            );
        }
    }
}
