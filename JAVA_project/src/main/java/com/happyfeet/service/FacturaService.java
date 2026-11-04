
package com.happyfeet.service;

import com.happyfeet.dao.FacturaDAO;
import com.happyfeet.facturacion.CalculoDescuento;
import com.happyfeet.facturacion.CalculoNormal;
import com.happyfeet.facturacion.CalculadoraFacturacion;
import com.happyfeet.model.Factura;

import java.math.BigDecimal;
import java.util.List;

// INTEGRACIÓN DEL PATRÓN STRATEGY
// Selecciona la estrategia y calcula el total
// antes de registrar o actualizar una factura.

public class FacturaService {

    private final FacturaDAO facturaDAO;

    public FacturaService() {
        this.facturaDAO = new FacturaDAO();
    }

    public boolean registrar(Factura factura) {

        if (factura == null) {
            throw new IllegalArgumentException(
                    "La factura no puede estar vacía."
            );
        }

        calcularTotal(factura);

        return facturaDAO.registrar(factura);
    }

    public Factura buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return facturaDAO.buscarPorId(id);
    }

    public List<Factura> listarTodos() {
        return facturaDAO.listarTodos();
    }

    public boolean actualizar(Factura factura) {

        if (factura == null) {
            throw new IllegalArgumentException(
                    "La factura no puede estar vacía."
            );
        }

        calcularTotal(factura);

        return facturaDAO.actualizar(factura);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser válido."
            );
        }

        return facturaDAO.eliminar(id);
    }

    // PATRÓN STRATEGY
    // Selecciona el cálculo según el descuento.

    private void calcularTotal(Factura factura) {

        BigDecimal subtotal = factura.getSubtotal();
        BigDecimal impuesto = factura.getImpuesto();
        BigDecimal descuento = factura.getDescuento();

        if (subtotal == null || impuesto == null || descuento == null) {
            throw new IllegalArgumentException(
                    "Subtotal, impuesto y descuento son obligatorios."
            );
        }

        if (subtotal.signum() < 0
                || impuesto.signum() < 0
                || descuento.signum() < 0) {

            throw new IllegalArgumentException(
                    "Los importes no pueden ser negativos."
            );
        }

        CalculadoraFacturacion calculadora;

        if (descuento.compareTo(BigDecimal.ZERO) > 0) {

            calculadora = new CalculadoraFacturacion(
                    new CalculoDescuento()
            );

        } else {

            calculadora = new CalculadoraFacturacion(
                    new CalculoNormal()
            );
        }

        BigDecimal total = calculadora.calcularTotal(
                subtotal,
                impuesto,
                descuento
        );

        factura.setTotal(total);
    }

   
}

