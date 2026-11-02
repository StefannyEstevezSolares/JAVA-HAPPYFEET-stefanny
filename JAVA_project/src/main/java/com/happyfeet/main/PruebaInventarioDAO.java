
package com.happyfeet.main;

import com.happyfeet.dao.InventarioDAO;
import com.happyfeet.model.Inventario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

public class PruebaInventarioDAO {

    public static void main(String[] args) {

        InventarioDAO dao = new InventarioDAO();

        // Crear producto de prueba
        Inventario producto = new Inventario();

        producto.setNombreProducto("Producto de prueba Happy Feet");
        producto.setProductoTipoId(1);
        producto.setDescripcion("Producto para probar el DAO");
        producto.setFabricante("Fabricante de prueba");
        producto.setProveedorId(null);
        producto.setLote("LOTE-PRUEBA-001");
        producto.setCantidadStock(20);
        producto.setStockMinimo(5);
        producto.setUnidadMedida("unidad");
        producto.setFechaVencimiento(LocalDate.now().plusMonths(12));
        producto.setPrecioCompra(new BigDecimal("25.00"));
        producto.setPrecioVenta(new BigDecimal("35.00"));
        producto.setRequiereReceta(false);
        producto.setActivo(true);

        // 1. Registrar
        System.out.println("===== REGISTRAR PRODUCTO =====");

        if (!dao.registrar(producto)) {
            System.out.println("No se pudo registrar el producto.");
            return;
        }

        int id = producto.getId();

        System.out.println("Producto registrado correctamente.");
        System.out.println("ID generado: " + id);

        // 2. Buscar por ID
        System.out.println("\n===== BUSCAR PRODUCTO =====");

        Inventario encontrado = dao.buscarPorId(id);

        if (encontrado != null) {
            System.out.println("Nombre: " + encontrado.getNombreProducto());
            System.out.println("Stock: " + encontrado.getCantidadStock());
            System.out.println("Precio: " + encontrado.getPrecioVenta());
        } else {
            System.out.println("No se encontró el producto.");
        }

        // 3. Actualizar
        System.out.println("\n===== ACTUALIZAR PRODUCTO =====");

        if (encontrado != null) {

            encontrado.setCantidadStock(30);
            encontrado.setPrecioVenta(new BigDecimal("40.00"));

            if (dao.actualizar(encontrado)) {
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el producto.");
            }
        }

        // 4. Listar todos
        System.out.println("\n===== LISTAR PRODUCTOS =====");

        List<Inventario> productos = dao.listarTodos();

        for (Inventario p : productos) {
            System.out.println(
                    "ID: " + p.getId()
                    + " | Nombre: " + p.getNombreProducto()
                    + " | Stock: " + p.getCantidadStock()
                    + " | Precio: " + p.getPrecioVenta()
            );
        }

        // 5. Eliminar opcionalmente
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Deseas eliminar el producto de prueba?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            if (dao.eliminar(id)) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el producto.");
            }

        } else {
            System.out.println("El producto de prueba se conserva.");
        }
    }
}
