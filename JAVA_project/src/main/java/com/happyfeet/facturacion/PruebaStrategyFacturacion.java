
package com.happyfeet.facturacion;

import java.math.BigDecimal;



public class PruebaStrategyFacturacion {

    public static void main(String[] args) {

        BigDecimal subtotal = new BigDecimal("500.00");
        BigDecimal impuesto = new BigDecimal("0.00");

        // Primera estrategia: cálculo normal
        System.out.println("===== CÁLCULO NORMAL =====");

        CalculadoraFacturacion calculadora =
                new CalculadoraFacturacion(new CalculoNormal());

        BigDecimal totalNormal = calculadora.calcularTotal(
                subtotal,
                impuesto,
                BigDecimal.ZERO
        );

        System.out.println("Subtotal: Q" + subtotal);
        System.out.println("Impuesto: Q" + impuesto);
        System.out.println("Total: Q" + totalNormal);

        // Segunda estrategia: descuento monetario de Q50
        System.out.println("\n===== CÁLCULO CON DESCUENTO =====");

        calculadora.establecerEstrategia(new CalculoDescuento());

        BigDecimal descuento = new BigDecimal("50.00");

        BigDecimal totalDescuento = calculadora.calcularTotal(
                subtotal,
                impuesto,
                descuento
        );

        System.out.println("Subtotal: Q" + subtotal);
        System.out.println("Impuesto: Q" + impuesto);
        System.out.println("Descuento: Q" + descuento);
        System.out.println("Total: Q" + totalDescuento);

        System.out.println("\nPRUEBAS STRATEGY FINALIZADAS");
    }
}
