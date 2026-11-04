
package com.happyfeet.view;

import com.happyfeet.controller.FacturaController;
import com.happyfeet.facturacion.GeneradorFacturaPDF;
import com.happyfeet.model.Factura;

public class PruebaGeneradorFacturaPDF {

    public static void main(String[] args) {

        FacturaController controller = new FacturaController();
        GeneradorFacturaPDF generador = new GeneradorFacturaPDF();

        int idFactura = 3;

        System.out.println("===== GENERACIÓN DE FACTURA PDF =====");

        try {

            Factura factura = controller.buscarPorId(idFactura);

            if (factura == null) {
                System.out.println("No se encontró la factura.");
                return;
            }

            String ruta = generador.generar(factura);

            System.out.println("PDF generado correctamente.");
            System.out.println("Número de factura: "
                    + factura.getNumeroFactura());
            System.out.println("Total: Q" + factura.getTotal());
            System.out.println("Ubicación: " + ruta);

        } catch (Exception e) {
            System.out.println("Error al generar el PDF: "
                    + e.getMessage());
        }

        System.out.println("\nPRUEBA FINALIZADA");
    }
}
