
package com.happyfeet.facturacion;

import java.math.BigDecimal;

// PATRÓN STRATEGY
// Calcula el total aplicando un descuento monetario.

public class CalculoDescuento implements CalculoFacturacion {

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

        BigDecimal base = subtotal.add(impuesto);

        if (descuento.compareTo(base) > 0) {
            throw new IllegalArgumentException(
                    "El descuento no puede superar el subtotal más el impuesto."
            );
        }

        return base.subtract(descuento);
    }
}

