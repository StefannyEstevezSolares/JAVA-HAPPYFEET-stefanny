
package com.happyfeet.facturacion;

import java.math.BigDecimal;

// PATRÓN STRATEGY
// Calcula la factura sin aplicar descuentos.

public class CalculoNormal implements CalculoFacturacion {

    @Override
    public BigDecimal calcular(
            BigDecimal subtotal,
            BigDecimal impuesto,
            BigDecimal descuento) {

        if (subtotal == null || impuesto == null || descuento == null) {
            throw new IllegalArgumentException(
                    "Los importes son obligatorios."
            );
        }

        if (subtotal.signum() < 0
                || impuesto.signum() < 0
                || descuento.signum() < 0) {

            throw new IllegalArgumentException(
                    "Los importes no pueden ser negativos."
            );
        }

        return subtotal.add(impuesto);
    }
}

