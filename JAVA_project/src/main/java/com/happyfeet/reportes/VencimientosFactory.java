
package com.happyfeet.reportes;

// PATRÓN FACTORY METHOD
// INICIO: FÁBRICA CONCRETA
// Crea el reporte de productos próximos a vencer.

public class VencimientosFactory extends ReporteFactory {

    @Override
    public Reporte crearReporte() {

        return new ReporteVencimientos();
    }
}

