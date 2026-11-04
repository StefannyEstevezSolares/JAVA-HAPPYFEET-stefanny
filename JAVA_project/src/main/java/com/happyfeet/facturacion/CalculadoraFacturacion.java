
package com.happyfeet.facturacion;

import java.math.BigDecimal;

// PATRÓN STRATEGY
// Permite seleccionar y cambiar la estrategia
// utilizada para calcular una factura.

public class CalculadoraFacturacion {

    private CalculoFacturacion estrategia;

    public CalculadoraFacturacion(CalculoFacturacion estrategia) {
        this.estrategia = estrategia;
    }

    public void establecerEstrategia(CalculoFacturacion estrategia) {
        this.estrategia = estrategia;
    }

    public BigDecimal calcularTotal(
            BigDecimal subtotal,
            BigDecimal impuesto,
            BigDecimal descuento) {

        if (estrategia == null) {
            throw new IllegalStateException(
                    "No se ha seleccionado una estrategia de cálculo."
            );
        }

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

        return estrategia.calcular(subtotal, impuesto, descuento);
    }
}

