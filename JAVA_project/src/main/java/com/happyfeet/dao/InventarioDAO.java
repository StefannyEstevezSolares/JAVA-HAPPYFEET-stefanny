
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Inventario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    // Registrar producto
    public boolean registrar(Inventario producto) {

        if (!validarProducto(producto)) {
            return false;
        }

        String sql = "INSERT INTO inventario "
                + "(nombre_producto, producto_tipo_id, descripcion, fabricante, "
                + "proveedor_id, lote, cantidad_stock, stock_minimo, unidad_medida, "
                + "fecha_vencimiento, precio_compra, precio_venta, requiere_receta, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            llenarParametros(ps, producto);

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        producto.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
        }

        return false;
    }

    // Buscar producto por ID
    public Inventario buscarPorId(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return null;
        }

        String sql = "SELECT * FROM inventario WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }

        return null;
    }

    // Listar todos los productos
    public List<Inventario> listarTodos() {

        List<Inventario> productos = new ArrayList<>();

        String sql = "SELECT * FROM inventario ORDER BY id DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productos.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return productos;
    }

    // Actualizar producto
    public boolean actualizar(Inventario producto) {

        if (!validarProducto(producto) || producto.getId() <= 0) {
            return false;
        }

        String sql = "UPDATE inventario SET nombre_producto = ?, "
                + "producto_tipo_id = ?, descripcion = ?, fabricante = ?, "
                + "proveedor_id = ?, lote = ?, cantidad_stock = ?, "
                + "stock_minimo = ?, unidad_medida = ?, fecha_vencimiento = ?, "
                + "precio_compra = ?, precio_venta = ?, requiere_receta = ?, "
                + "activo = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            llenarParametros(ps, producto);
            ps.setInt(15, producto.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }

        return false;
    }

    // Eliminar producto
    public boolean eliminar(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return false;
        }

        String sql = "DELETE FROM inventario WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }

        return false;
    }

    // Validar información del producto
    private boolean validarProducto(Inventario producto) {

        if (producto == null
                || producto.getNombreProducto() == null
                || producto.getNombreProducto().trim().isEmpty()
                || producto.getProductoTipoId() <= 0
                || producto.getCantidadStock() < 0
                || producto.getStockMinimo() < 0
                || producto.getPrecioVenta() == null
                || producto.getPrecioVenta().signum() < 0) {

            System.out.println("Error: verifica los datos obligatorios del producto.");
            return false;
        }

        return true;
    }

    // Asignar parámetros para INSERT y UPDATE
    private void llenarParametros(PreparedStatement ps, Inventario producto)
            throws SQLException {

        ps.setString(1, producto.getNombreProducto());
        ps.setInt(2, producto.getProductoTipoId());
        ps.setString(3, producto.getDescripcion());
        ps.setString(4, producto.getFabricante());
        ps.setObject(5, producto.getProveedorId());
        ps.setString(6, producto.getLote());
        ps.setInt(7, producto.getCantidadStock());
        ps.setInt(8, producto.getStockMinimo());
        ps.setString(9, producto.getUnidadMedida());

        ps.setDate(10, producto.getFechaVencimiento() == null
                ? null : Date.valueOf(producto.getFechaVencimiento()));

        ps.setBigDecimal(11, producto.getPrecioCompra());
        ps.setBigDecimal(12, producto.getPrecioVenta());
        ps.setBoolean(13, Boolean.TRUE.equals(producto.getRequiereReceta()));
        ps.setBoolean(14, Boolean.TRUE.equals(producto.getActivo()));
    }

    // Convertir resultado de MySQL a objeto Inventario
    private Inventario convertirResultado(ResultSet rs) throws SQLException {

        Inventario producto = new Inventario();

        producto.setId(rs.getInt("id"));
        producto.setNombreProducto(rs.getString("nombre_producto"));
        producto.setProductoTipoId(rs.getInt("producto_tipo_id"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setFabricante(rs.getString("fabricante"));
        producto.setProveedorId((Integer) rs.getObject("proveedor_id"));
        producto.setLote(rs.getString("lote"));
        producto.setCantidadStock(rs.getInt("cantidad_stock"));
        producto.setStockMinimo(rs.getInt("stock_minimo"));
        producto.setUnidadMedida(rs.getString("unidad_medida"));

        Date fecha = rs.getDate("fecha_vencimiento");
        producto.setFechaVencimiento(
                fecha == null ? null : fecha.toLocalDate()
        );

        producto.setPrecioCompra(rs.getBigDecimal("precio_compra"));
        producto.setPrecioVenta(rs.getBigDecimal("precio_venta"));
        producto.setRequiereReceta(rs.getBoolean("requiere_receta"));
        producto.setActivo(rs.getBoolean("activo"));

        Timestamp registro = rs.getTimestamp("fecha_registro");
        producto.setFechaRegistro(
                registro == null ? null : registro.toLocalDateTime()
        );

        return producto;
    }
}
