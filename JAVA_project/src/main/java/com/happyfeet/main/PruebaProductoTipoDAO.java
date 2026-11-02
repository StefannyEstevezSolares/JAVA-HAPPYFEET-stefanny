
package com.happyfeet.main;

import com.happyfeet.config.Conexion;
import com.happyfeet.dao.ProductoTipoDAO;
import com.happyfeet.model.ProductoTipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PruebaProductoTipoDAO {

    public static void main(String[] args) {

        ProductoTipoDAO dao = new ProductoTipoDAO();

        int productoTipoId = 0;
        String descripcion = "PRUEBA_PRODUCTO_TIPO_" + System.currentTimeMillis();

        try {

            // REGISTRAR
            ProductoTipo productoTipo = new ProductoTipo(
                    0,
                    "Producto de prueba",
                    descripcion
            );

            if (dao.registrar(productoTipo)) {
                System.out.println("Tipo de producto registrado correctamente.");
            } else {
                System.out.println("Error al registrar tipo de producto.");
                return;
            }

            // OBTENER ID
            try (Connection con = Conexion.getConnection()) {

                String sql = "SELECT id FROM producto_tipos WHERE descripcion = ?";

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, descripcion);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            productoTipoId = rs.getInt("id");
                        }
                    }
                }
            }

            if (productoTipoId == 0) {
                System.out.println("No se pudo recuperar el ID.");
                return;
            }

            // BUSCAR
            ProductoTipo encontrado = dao.buscarPorId(productoTipoId);

            System.out.println(encontrado != null
                    ? "Tipo de producto encontrado correctamente."
                    : "No se encontro el tipo de producto.");

            // ACTUALIZAR
            encontrado.setNombre("Producto actualizado");
            encontrado.setDescripcion(descripcion + "_ACTUALIZADO");

            System.out.println(dao.actualizar(encontrado)
                    ? "Tipo de producto actualizado correctamente."
                    : "Error al actualizar tipo de producto.");

            // LISTAR
            System.out.println("Total de tipos de productos: "
                    + dao.listarTodos().size());

            // ELIMINAR
            System.out.println(dao.eliminar(productoTipoId)
                    ? "Tipo de producto eliminado correctamente."
                    : "Error al eliminar tipo de producto.");

        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}
