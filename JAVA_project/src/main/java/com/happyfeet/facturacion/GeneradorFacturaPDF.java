
package com.happyfeet.facturacion;

import com.happyfeet.model.Factura;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class GeneradorFacturaPDF {

    public String generar(Factura factura) throws IOException {

        if (factura == null) {
            throw new IllegalArgumentException(
                    "La factura no puede ser nula."
            );
        }

        Path carpeta = Path.of("facturas");
        Files.createDirectories(carpeta);

        String numero = factura.getNumeroFactura()
                .replaceAll("[^a-zA-Z0-9_-]", "_");

        Path archivo = carpeta.resolve("Factura_" + numero + ".pdf");

        try (PDDocument documento = new PDDocument()) {

            PDPage pagina = new PDPage();
            documento.addPage(pagina);

            PDType1Font fuenteNormal =
                    new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            PDType1Font fuenteNegrita =
                    new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

            try (PDPageContentStream contenido =
                    new PDPageContentStream(documento, pagina)) {

                contenido.beginText();
                contenido.setFont(fuenteNegrita, 20);
                contenido.newLineAtOffset(50, 750);
                contenido.showText("HAPPY FEET - FACTURA");
                contenido.endText();

                contenido.moveTo(50, 730);
                contenido.lineTo(545, 730);
                contenido.stroke();

                escribirLinea(contenido, fuenteNegrita, 13,
                        "DATOS GENERALES", 50, 700);

                DateTimeFormatter formato =
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                String fecha = factura.getFechaEmision() != null
                        ? factura.getFechaEmision().format(formato)
                        : "No disponible";

                escribirLinea(contenido, fuenteNormal, 11,
                        "Numero de factura: " + factura.getNumeroFactura(),
                        50, 675);

                escribirLinea(contenido, fuenteNormal, 11,
                        "Fecha de emision: " + fecha,
                        50, 655);

                escribirLinea(contenido, fuenteNormal, 11,
                        "ID del dueno: " + factura.getDuenoId(),
                        50, 635);

                escribirLinea(contenido, fuenteNegrita, 13,
                        "RESUMEN DE IMPORTES", 50, 590);

                contenido.moveTo(50, 580);
                contenido.lineTo(545, 580);
                contenido.stroke();

                escribirLinea(contenido, fuenteNormal, 12,
                        "Subtotal: Q" + factura.getSubtotal(),
                        50, 550);

                escribirLinea(contenido, fuenteNormal, 12,
                        "Impuesto: Q" + factura.getImpuesto(),
                        50, 525);

                escribirLinea(contenido, fuenteNormal, 12,
                        "Descuento: Q" + factura.getDescuento(),
                        50, 500);

                escribirLinea(contenido, fuenteNegrita, 16,
                        "TOTAL: Q" + factura.getTotal(),
                        50, 460);

                contenido.moveTo(50, 445);
                contenido.lineTo(545, 445);
                contenido.stroke();

                escribirLinea(contenido, fuenteNormal, 11,
                        "Metodo de pago: " + factura.getMetodoPago(),
                        50, 415);

                escribirLinea(contenido, fuenteNormal, 11,
                        "Estado: " + factura.getEstado(),
                        50, 390);

                escribirLinea(contenido, fuenteNegrita, 12,
                        "OBSERVACIONES", 50, 350);

                escribirLinea(contenido, fuenteNormal, 11,
                        factura.getObservaciones() != null
                                ? factura.getObservaciones()
                                : "Sin observaciones",
                        50, 325);

                escribirLinea(contenido, fuenteNormal, 10,
                        "Gracias por confiar en Happy Feet.",
                        50, 100);
            }

            documento.save(archivo.toFile());
        }

        return archivo.toAbsolutePath().toString();
    }

    private void escribirLinea(
            PDPageContentStream contenido,
            PDType1Font fuente,
            int tamano,
            String texto,
            float x,
            float y) throws IOException {

        contenido.beginText();
        contenido.setFont(fuente, tamano);
        contenido.newLineAtOffset(x, y);
        contenido.showText(texto != null ? texto : "");
        contenido.endText();
    }
}
