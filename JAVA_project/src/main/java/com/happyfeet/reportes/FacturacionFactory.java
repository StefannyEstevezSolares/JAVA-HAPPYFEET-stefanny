
package com.happyfeet.reportes;

import java.time.LocalDate;

// PATRÓN FACTORY METHOD
// INICIO: FÁBRICA CONCRETA
// Crea el reporte de facturación por período.

public class FacturacionFactory extends ReporteFactory {

    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;

    public FacturacionFactory(
            LocalDate fechaInicio,
            LocalDate fechaFin) {

        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public Reporte crearReporte() {

        return new ReporteFacturacion(
                fechaInicio,
                fechaFin
        );
    }
}

