
package com.happyfeet.view;

import com.happyfeet.controller.FacturaController;
import com.happyfeet.model.Factura;
import java.util.List;

public class PruebaFacturacion {

    public static void main(String[] args) {

        FacturaController controller = new FacturaController();

        System.out.println("===== LISTADO DE FACTURAS =====");

        List<Factura> facturas = controller.listarTodos();

        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
            return;
        }

        for (Factura factura : facturas) {

            System.out.println("------------------------------");
            System.out.println("ID: " + factura.getId());
            System.out.println("Número: " + factura.getNumeroFactura());
            System.out.println("ID del dueño: " + factura.getDuenoId());
            System.out.println("Subtotal: Q" + factura.getSubtotal());
            System.out.println("Impuesto: Q" + factura.getImpuesto());
            System.out.println("Descuento: Q" + factura.getDescuento());
            System.out.println("Total: Q" + factura.getTotal());
            System.out.println("Método de pago: " + factura.getMetodoPago());
            System.out.println("Estado: " + factura.getEstado());
            System.out.println("Observaciones: " + factura.getObservaciones());
        }

        System.out.println("\nCONSULTA FINALIZADA");
    }
}
