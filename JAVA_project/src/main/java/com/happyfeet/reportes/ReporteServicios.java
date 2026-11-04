
package com.happyfeet.reportes;

import com.happyfeet.dao.ReportesDAO;
import java.util.List;
import java.util.Map;

// PATRÓN FACTORY METHOD
// Implementa el reporte de servicios más solicitados.

public class ReporteServicios implements Reporte {

    private final ReportesDAO reportesDAO;

    public ReporteServicios() {
        this.reportesDAO = new ReportesDAO();
    }

    @Override
    public List<Map<String, Object>> generarReporte() {

        return reportesDAO.serviciosMasSolicitados();
    }
}

