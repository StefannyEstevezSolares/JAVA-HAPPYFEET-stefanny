
package com.happyfeet.facturacion;

import java.math.BigDecimal;

// PATRÓN STRATEGY
// Define el contrato para calcular el total
// de una factura utilizando BigDecimal.

public interface CalculoFacturacion {

    BigDecimal calcular(
            BigDecimal subtotal,
            BigDecimal impuesto,
            BigDecimal descuento
    );
}

