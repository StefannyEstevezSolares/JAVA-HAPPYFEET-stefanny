
package com.happyfeet.reportes;

// PATRÓN FACTORY METHOD
// INICIO: FÁBRICA CONCRETA
// Crea el reporte de desempeño de veterinarios.

public class VeterinariosFactory extends ReporteFactory {

    @Override
    public Reporte crearReporte() {

        return new ReporteVeterinarios();
    }
}
