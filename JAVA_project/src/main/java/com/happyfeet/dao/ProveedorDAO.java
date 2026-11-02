
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public boolean registrar(Proveedor proveedor) {

        String sql = "INSERT INTO proveedores "
                + "(nombre_empresa, contacto, telefono, email, "
                + "direccion, sitio_web, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombreEmpresa());
            ps.setString(2, proveedor.getContacto());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());
            ps.setString(5, proveedor.getDireccion());
            ps.setString(6, proveedor.getSitioWeb());
            ps.setBoolean(7, proveedor.isActivo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar proveedor: " + e.getMessage());
            return false;
        }
    }

    public Proveedor buscarPorId(int id) {

        String sql = "SELECT * FROM proveedores WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar proveedor: " + e.getMessage());
        }

        return null;
    }

    public List<Proveedor> listarTodos() {

        List<Proveedor> lista = new ArrayList<>();

        String sql = "SELECT * FROM proveedores ORDER BY id DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Proveedor proveedor) {

        String sql = "UPDATE proveedores SET "
                + "nombre_empresa = ?, contacto = ?, telefono = ?, "
                + "email = ?, direccion = ?, sitio_web = ?, "
                + "activo = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombreEmpresa());
            ps.setString(2, proveedor.getContacto());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());
            ps.setString(5, proveedor.getDireccion());
            ps.setString(6, proveedor.getSitioWeb());
            ps.setBoolean(7, proveedor.isActivo());
            ps.setInt(8, proveedor.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM proveedores WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }

    private Proveedor convertirResultado(ResultSet rs)
            throws SQLException {

        Proveedor proveedor = new Proveedor();

        proveedor.setId(rs.getInt("id"));
        proveedor.setNombreEmpresa(rs.getString("nombre_empresa"));
        proveedor.setContacto(rs.getString("contacto"));
        proveedor.setTelefono(rs.getString("telefono"));
        proveedor.setEmail(rs.getString("email"));
        proveedor.setDireccion(rs.getString("direccion"));
        proveedor.setSitioWeb(rs.getString("sitio_web"));
        proveedor.setActivo(rs.getBoolean("activo"));

        return proveedor;
    }
}
