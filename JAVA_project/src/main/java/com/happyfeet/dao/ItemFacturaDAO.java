
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.ItemFactura;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemFacturaDAO {

    // Registrar un item de factura
    public boolean registrar(ItemFactura item) {

        String sql = "INSERT INTO items_factura "
                + "(factura_id, tipo_item, producto_id, servicio_id, "
                + "servicio_descripcion, cantidad, precio_unitario, subtotal) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, item.getFacturaId());
            ps.setString(2, item.getTipoItem());
            ps.setObject(3, item.getProductoId());
            ps.setObject(4, item.getServicioId());
            ps.setString(5, item.getServicioDescripcion());
            ps.setInt(6, item.getCantidad());
            ps.setBigDecimal(7, item.getPrecioUnitario());
            ps.setBigDecimal(8, item.getSubtotal());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        item.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar item: " + e.getMessage());
        }

        return false;
    }

    // Buscar un item por su ID
    public ItemFactura buscarPorId(int id) {

        String sql = "SELECT * FROM items_factura WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar item: " + e.getMessage());
        }

        return null;
    }

    // Listar todos los items
    public List<ItemFactura> listarTodos() {

        List<ItemFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM items_factura";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar items: " + e.getMessage());
        }

        return lista;
    }

    // Listar items de una factura
    public List<ItemFactura> listarPorFactura(int facturaId) {

        List<ItemFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM items_factura WHERE factura_id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, facturaId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(convertirResultado(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar items de factura: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar un item
    public boolean actualizar(ItemFactura item) {

        String sql = "UPDATE items_factura SET factura_id = ?, "
                + "tipo_item = ?, producto_id = ?, servicio_id = ?, "
                + "servicio_descripcion = ?, cantidad = ?, "
                + "precio_unitario = ?, subtotal = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getFacturaId());
            ps.setString(2, item.getTipoItem());
            ps.setObject(3, item.getProductoId());
            ps.setObject(4, item.getServicioId());
            ps.setString(5, item.getServicioDescripcion());
            ps.setInt(6, item.getCantidad());
            ps.setBigDecimal(7, item.getPrecioUnitario());
            ps.setBigDecimal(8, item.getSubtotal());
            ps.setInt(9, item.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar item: " + e.getMessage());
        }

        return false;
    }

    // Eliminar un item
    public boolean eliminar(int id) {

        String sql = "DELETE FROM items_factura WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar item: " + e.getMessage());
        }

        return false;
    }

    // Convertir el resultado de MySQL en un objeto ItemFactura
    private ItemFactura convertirResultado(ResultSet rs) throws SQLException {

        ItemFactura item = new ItemFactura();

        item.setId(rs.getInt("id"));
        item.setFacturaId(rs.getInt("factura_id"));
        item.setTipoItem(rs.getString("tipo_item"));

        item.setProductoId((Integer) rs.getObject("producto_id"));
        item.setServicioId((Integer) rs.getObject("servicio_id"));

        item.setServicioDescripcion(rs.getString("servicio_descripcion"));
        item.setCantidad(rs.getInt("cantidad"));
        item.setPrecioUnitario(rs.getBigDecimal("precio_unitario"));
        item.setSubtotal(rs.getBigDecimal("subtotal"));

        return item;
    }
}
