
package com.happyfeet.reportes;

import com.happyfeet.dao.ReportesDAO;
import java.util.List;
import java.util.Map;

// PATRÓN FACTORY METHOD
// INICIO: PRODUCTO CONCRETO
// Reporte de productos próximos a vencer.

public class ReporteVencimientos implements Reporte {

    private final ReportesDAO reportesDAO;

    public ReporteVencimientos() {
        this.reportesDAO = new ReportesDAO();
    }

    @Override
    public List<Map<String, Object>> generarReporte() {

        return reportesDAO.productosProximosVencer();
    }
}
