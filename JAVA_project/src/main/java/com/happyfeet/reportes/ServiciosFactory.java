
package com.happyfeet.reportes;

// PATRÓN FACTORY METHOD
// INICIO: FÁBRICA CONCRETA
// Crea el reporte de servicios más solicitados.


public class ServiciosFactory extends ReporteFactory {

    @Override
    public Reporte crearReporte() {

        return new ReporteServicios();
    }
}
