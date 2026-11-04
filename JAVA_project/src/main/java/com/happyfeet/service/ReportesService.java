package com.happyfeet.service;

import com.happyfeet.dao.ReportesDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ReportesService {

    private final ReportesDAO reportesDAO;

    public ReportesService() {
        this.reportesDAO = new ReportesDAO();
    }

    // RF33: Servicios más solicitados
    public List<Map<String, Object>> obtenerServiciosMasSolicitados() {

        return reportesDAO.serviciosMasSolicitados();
    }

    // RF34: Desempeño de veterinarios
    public List<Map<String, Object>> obtenerDesempenoVeterinarios() {

        return reportesDAO.desempenoVeterinarios();
    }

    // RF35: Productos con stock bajo
    public List<Map<String, Object>> obtenerProductosStockBajo() {

        return reportesDAO.productosStockBajo();
    }

    // RF35: Productos próximos a vencer
    public List<Map<String, Object>> obtenerProductosProximosVencer() {

        return reportesDAO.productosProximosVencer();
    }

    // RF36: Facturación por período
    public List<Map<String, Object>> obtenerFacturacionPorPeriodo(
            LocalDate fechaInicio, LocalDate fechaFin) {

        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException(
                    "Las fechas de inicio y fin son obligatorias."
            );
        }

        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException(
                    "La fecha de inicio no puede ser posterior a la fecha de fin."
            );
        }

        return reportesDAO.facturacionPorPeriodo(
                fechaInicio,
                fechaFin
        );
    }
}