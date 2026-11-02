
package com.happyfeet.main;

import com.happyfeet.dao.FacturaDAO;
import com.happyfeet.model.Factura;
import java.math.BigDecimal;
import java.util.List;

public class PruebaFacturaDAO {

    public static void main(String[] args) {

        FacturaDAO dao = new FacturaDAO();

        // Sustituir por un ID de dueño existente.
        int duenoId = 1;

        Factura factura = new Factura();

        factura.setDuenoId(duenoId);
        factura.setNumeroFactura("FAC-PRUEBA-001");
        factura.setSubtotal(new BigDecimal("100.00"));
        factura.setImpuesto(new BigDecimal("12.00"));
        factura.setDescuento(new BigDecimal("0.00"));
        factura.setTotal(new BigDecimal("112.00"));
        factura.setMetodoPago("Efectivo");
        factura.setEstado("Pendiente");
        factura.setObservaciones("Factura de prueba DAO");

        // 1. REGISTRAR
        System.out.println("===== REGISTRAR FACTURA =====");

        boolean registrado = dao.registrar(factura);

        if (!registrado) {
            System.out.println("No se pudo registrar la factura.");
            return;
        }

        System.out.println("Factura registrada correctamente.");
        System.out.println("ID generado: " + factura.getId());

        // 2. BUSCAR POR ID
        System.out.println("\n===== BUSCAR FACTURA =====");

        Factura encontrada = dao.buscarPorId(factura.getId());

        if (encontrada != null) {
            System.out.println("Número: " + encontrada.getNumeroFactura());
            System.out.println("Total: " + encontrada.getTotal());
            System.out.println("Estado: " + encontrada.getEstado());
        } else {
            System.out.println("No se encontró la factura.");
        }

        // 3. ACTUALIZAR
        System.out.println("\n===== ACTUALIZAR FACTURA =====");

        factura.setEstado("Pagada");

        boolean actualizada = dao.actualizar(factura);

        System.out.println("Actualización: " + actualizada);

        // 4. LISTAR
        System.out.println("\n===== LISTAR FACTURAS =====");

        List<Factura> facturas = dao.listarTodos();

        for (Factura f : facturas) {
            System.out.println(
                "ID: " + f.getId()
                + " | Número: " + f.getNumeroFactura()
                + " | Total: " + f.getTotal()
                + " | Estado: " + f.getEstado()
            );
        }

        // 5. ELIMINAR
        System.out.println("\n===== ELIMINAR FACTURA =====");

        boolean eliminada = dao.eliminar(factura.getId());

        System.out.println("Eliminación: " + eliminada);

        // Verificar eliminación
        Factura verificacion = dao.buscarPorId(factura.getId());

        if (verificacion == null) {
            System.out.println("Factura eliminada correctamente.");
        } else {
            System.out.println("La factura todavía existe.");
        }
    }
}
