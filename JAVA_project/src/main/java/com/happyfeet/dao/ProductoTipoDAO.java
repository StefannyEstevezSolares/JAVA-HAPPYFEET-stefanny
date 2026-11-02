
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.ProductoTipo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoTipoDAO {

    // REGISTRAR
    public boolean registrar(ProductoTipo productoTipo) {
        String sql = "INSERT INTO producto_tipos (nombre, descripcion) VALUES (?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, productoTipo.getNombre());
            ps.setString(2, productoTipo.getDescripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar tipo de producto: " + e.getMessage());
            return false;
        }
    }

    // BUSCAR POR ID
    public ProductoTipo buscarPorId(int id) {
        String sql = "SELECT * FROM producto_tipos WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar tipo de producto: " + e.getMessage());
        }

        return null;
    }

    // LISTAR TODOS
    public List<ProductoTipo> listarTodos() {
        List<ProductoTipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto_tipos";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar tipos de productos: " + e.getMessage());
        }

        return lista;
    }

    // ACTUALIZAR
    public boolean actualizar(ProductoTipo productoTipo) {
        String sql = "UPDATE producto_tipos SET nombre = ?, descripcion = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, productoTipo.getNombre());
            ps.setString(2, productoTipo.getDescripcion());
            ps.setInt(3, productoTipo.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar tipo de producto: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        String sql = "DELETE FROM producto_tipos WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar tipo de producto: " + e.getMessage());
            return false;
        }
    }

    // CONVERTIR RESULTADO
    private ProductoTipo convertirResultado(ResultSet rs) throws SQLException {
        ProductoTipo productoTipo = new ProductoTipo();

        productoTipo.setId(rs.getInt("id"));
        productoTipo.setNombre(rs.getString("nombre"));
        productoTipo.setDescripcion(rs.getString("descripcion"));

        return productoTipo;
    }
}
