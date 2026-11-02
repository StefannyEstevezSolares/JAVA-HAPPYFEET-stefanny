
package com.happyfeet.main;

import com.happyfeet.dao.ItemFacturaDAO;
import com.happyfeet.model.ItemFactura;
import com.happyfeet.config.Conexion;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PruebaItemFacturaDAO {

    public static void main(String[] args) {

        ItemFacturaDAO dao = new ItemFacturaDAO();

        try {
            // Buscar una factura existente
            int facturaId = 0;
            int productoId = 0;

            try (Connection con = Conexion.getConnection()) {

                String sqlFactura = "SELECT id FROM facturas LIMIT 1";

                try (PreparedStatement ps = con.prepareStatement(sqlFactura);
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        facturaId = rs.getInt("id");
                    }
                }

                String sqlProducto = "SELECT id FROM inventario LIMIT 1";

                try (PreparedStatement ps = con.prepareStatement(sqlProducto);
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        productoId = rs.getInt("id");
                    }
                }
            }

            if (facturaId == 0 || productoId == 0) {
                System.out.println("No hay facturas o productos registrados.");
                return;
            }

            // Crear un item de factura
            ItemFactura item = new ItemFactura();

            item.setFacturaId(facturaId);
            item.setTipoItem("Producto");
            item.setProductoId(productoId);
            item.setServicioId(null);
            item.setServicioDescripcion(null);
            item.setCantidad(2);
            item.setPrecioUnitario(new BigDecimal("25.00"));
            item.setSubtotal(new BigDecimal("50.00"));

            // Registrar
            if (dao.registrar(item)) {

                System.out.println("Item registrado correctamente.");
                System.out.println("ID generado: " + item.getId());

                // Buscar
                ItemFactura encontrado = dao.buscarPorId(item.getId());

                if (encontrado != null) {
                    System.out.println("Item encontrado: "
                            + encontrado.getTipoItem());
                }

                // Listar por factura
                System.out.println("Items de la factura: "
                        + dao.listarPorFactura(facturaId).size());

                // Actualizar
                item.setCantidad(3);
                item.setSubtotal(new BigDecimal("75.00"));

                System.out.println("Actualización: "
                        + dao.actualizar(item));

                // Listar todos
                System.out.println("Total de items: "
                        + dao.listarTodos().size());

                // Eliminar
                System.out.println("Eliminación: "
                        + dao.eliminar(item.getId()));

            } else {
                System.out.println("No se pudo registrar el item.");
            }

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}
