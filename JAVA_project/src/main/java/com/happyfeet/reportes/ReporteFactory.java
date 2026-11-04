
package com.happyfeet.reportes;

import java.util.List;
import java.util.Map;

// PATRÓN FACTORY METHOD
// INICIO: CREADOR ABSTRACTO
// Define el método para crear los diferentes reportes.

public abstract class ReporteFactory {

    // Factory Method
    public abstract Reporte crearReporte();

    // Método que utiliza el producto creado
    public List<Map<String, Object>> generarReporte() {

        Reporte reporte = crearReporte();

        return reporte.generarReporte();
    }
}

