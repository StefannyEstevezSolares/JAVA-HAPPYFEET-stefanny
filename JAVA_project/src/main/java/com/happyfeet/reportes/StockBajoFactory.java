
package com.happyfeet.reportes;

// PATRÓN FACTORY METHOD
// INICIO: FÁBRICA CONCRETA
// Crea el reporte de productos con stock bajo.

public class StockBajoFactory extends ReporteFactory {

    @Override
    public Reporte crearReporte() {

        return new ReporteStockBajo();
    }
}

