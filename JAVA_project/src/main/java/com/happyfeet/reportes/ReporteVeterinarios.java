
package com.happyfeet.reportes;

import com.happyfeet.dao.ReportesDAO;
import java.util.List;
import java.util.Map;

// PATRÓN FACTORY METHOD
// INICIO: PRODUCTO CONCRETO
// Implementa el reporte de desempeño de veterinarios.

public class ReporteVeterinarios implements Reporte {

    private final ReportesDAO reportesDAO;

    public ReporteVeterinarios() {
        this.reportesDAO = new ReportesDAO();
    }

    @Override
    public List<Map<String, Object>> generarReporte() {

        return reportesDAO.desempenoVeterinarios();
    }
}

