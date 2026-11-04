
package com.happyfeet.controller;

import com.happyfeet.reportes.ReporteFactory;
import com.happyfeet.reportes.ServiciosFactory;
import com.happyfeet.reportes.VeterinariosFactory;
import com.happyfeet.reportes.StockBajoFactory;
import com.happyfeet.reportes.VencimientosFactory;
import com.happyfeet.reportes.FacturacionFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ReportesController {

    // PATRÓN FACTORY METHOD
    // INICIO: INTEGRACIÓN EN EL CONTROLLER
    // El Controller utiliza las fábricas para obtener
    // los reportes sin crear directamente sus objetos.

    
    // RF33: Servicios más solicitados
    public List<Map<String, Object>> consultarServiciosMasSolicitados() {

        ReporteFactory factory = new ServiciosFactory();

        return factory.generarReporte();
    }

    // RF34: Desempeño de veterinarios
    public List<Map<String, Object>> consultarDesempenoVeterinarios() {

        ReporteFactory factory = new VeterinariosFactory();

        return factory.generarReporte();
    }

    // RF35: Productos con stock bajo
    public List<Map<String, Object>> consultarProductosStockBajo() {

        ReporteFactory factory = new StockBajoFactory();

        return factory.generarReporte();
    }

    // RF35: Productos próximos a vencer
    public List<Map<String, Object>> consultarProductosProximosVencer() {

        ReporteFactory factory = new VencimientosFactory();

        return factory.generarReporte();
    }

    // RF36: Facturación por período
    public List<Map<String, Object>> consultarFacturacionPorPeriodo(
            LocalDate fechaInicio, LocalDate fechaFin) {

        ReporteFactory factory =
                new FacturacionFactory(fechaInicio, fechaFin);

        return factory.generarReporte();
    }

}
