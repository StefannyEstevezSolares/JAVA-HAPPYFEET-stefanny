
package com.happyfeet.main;

import com.happyfeet.dao.ReportesDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PruebaReportesDAO {

    public static void main(String[] args) {

        ReportesDAO dao = new ReportesDAO();

        // RF33: Servicios más solicitados
        System.out.println("\n===== SERVICIOS MÁS SOLICITADOS =====");

        List<Map<String, Object>> servicios =
                dao.serviciosMasSolicitados();

        for (Map<String, Object> fila : servicios) {
            System.out.println(fila);
        }

        // RF34: Desempeño de veterinarios
        System.out.println("\n===== DESEMPEÑO DE VETERINARIOS =====");

        List<Map<String, Object>> veterinarios =
                dao.desempenoVeterinarios();

        for (Map<String, Object> fila : veterinarios) {
            System.out.println(fila);
        }

        // RF35: Productos con stock bajo
        System.out.println("\n===== PRODUCTOS CON STOCK BAJO =====");

        List<Map<String, Object>> stockBajo =
                dao.productosStockBajo();

        for (Map<String, Object> fila : stockBajo) {
            System.out.println(fila);
        }

        // RF35: Productos próximos a vencer
        System.out.println("\n===== PRODUCTOS PRÓXIMOS A VENCER =====");

        List<Map<String, Object>> vencimientos =
                dao.productosProximosVencer();

        for (Map<String, Object> fila : vencimientos) {
            System.out.println(fila);
        }

        // RF36: Facturación por período
        System.out.println("\n===== FACTURACIÓN POR PERÍODO =====");

        LocalDate fechaInicio = LocalDate.of(2026, 1, 1);
        LocalDate fechaFin = LocalDate.of(2026, 12, 31);

        List<Map<String, Object>> facturacion =
                dao.facturacionPorPeriodo(fechaInicio, fechaFin);

        for (Map<String, Object> fila : facturacion) {
            System.out.println(fila);
        }

        System.out.println("\n===== PRUEBAS FINALIZADAS =====");
    }
}
