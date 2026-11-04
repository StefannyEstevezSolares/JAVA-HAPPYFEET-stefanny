
package com.happyfeet.reportes;

import java.util.List;
import java.util.Map;

// PATRÓN FACTORY METHOD
// Define el contrato que deben cumplir los reportes.

public interface Reporte {

    List<Map<String, Object>> generarReporte();

}

