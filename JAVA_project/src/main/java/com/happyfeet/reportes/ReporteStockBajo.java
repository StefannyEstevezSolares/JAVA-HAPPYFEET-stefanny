
package com.happyfeet.reportes;

import com.happyfeet.dao.ReportesDAO;
import java.util.List;
import java.util.Map;

// PATRÓN FACTORY METHOD
// INICIO: PRODUCTO CONCRETO
// Reporte de productos con stock inferior al mínimo.

public class ReporteStockBajo implements Reporte {

    private final ReportesDAO reportesDAO;

    public ReporteStockBajo() {
        this.reportesDAO = new ReportesDAO();
    }

    @Override
    public List<Map<String, Object>> generarReporte() {

        return reportesDAO.productosStockBajo();
    }
}

