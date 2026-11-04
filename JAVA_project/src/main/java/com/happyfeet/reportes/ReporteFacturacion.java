
package com.happyfeet.reportes;

import com.happyfeet.dao.ReportesDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


// PATRÓN FACTORY METHOD
// INICIO: PRODUCTO CONCRETO
// Reporte de facturación por período.

public class ReporteFacturacion implements Reporte {

    private final ReportesDAO reportesDAO;

    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;

    public ReporteFacturacion(
            LocalDate fechaInicio,
            LocalDate fechaFin) {

        this.reportesDAO = new ReportesDAO();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public List<Map<String, Object>> generarReporte() {

        return reportesDAO.facturacionPorPeriodo(
                fechaInicio,
                fechaFin
        );
    }
}

