
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.CitaEstado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaEstadoDAO {

    public boolean registrar(CitaEstado estado) {
        String sql = "INSERT INTO cita_estados (nombre, descripcion) VALUES (?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado.getNombre());
            ps.setString(2, estado.getDescripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar estado: " + e.getMessage());
            return false;
        }
    }

    public CitaEstado buscarPorId(int id) {
        String sql = "SELECT * FROM cita_estados WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar estado: " + e.getMessage());
        }

        return null;
    }

    public List<CitaEstado> listarTodos() {
        List<CitaEstado> lista = new ArrayList<>();
        String sql = "SELECT * FROM cita_estados";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar estados: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(CitaEstado estado) {
        String sql = "UPDATE cita_estados SET nombre = ?, descripcion = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado.getNombre());
            ps.setString(2, estado.getDescripcion());
            ps.setInt(3, estado.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM cita_estados WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar estado: " + e.getMessage());
            return false;
        }
    }

    private CitaEstado convertirResultado(ResultSet rs) throws SQLException {
        CitaEstado estado = new CitaEstado();

        estado.setId(rs.getInt("id"));
        estado.setNombre(rs.getString("nombre"));
        estado.setDescripcion(rs.getString("descripcion"));

        return estado;
    }
}

