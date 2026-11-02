
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Especie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecieDAO {

    // Registrar especie
    public boolean registrar(Especie especie) {

        String sql = "INSERT INTO especies (nombre, descripcion) VALUES (?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, especie.getNombre());
            ps.setString(2, especie.getDescripcion());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        especie.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar especie: " + e.getMessage());
        }

        return false;
    }

    // Buscar especie por ID
    public Especie buscarPorId(int id) {

        String sql = "SELECT * FROM especies WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar especie: " + e.getMessage());
        }

        return null;
    }

    // Listar todas las especies
    public List<Especie> listarTodos() {

        List<Especie> lista = new ArrayList<>();
        String sql = "SELECT * FROM especies";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar especies: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar especie
    public boolean actualizar(Especie especie) {

        String sql = "UPDATE especies SET nombre = ?, descripcion = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, especie.getNombre());
            ps.setString(2, especie.getDescripcion());
            ps.setInt(3, especie.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar especie: " + e.getMessage());
        }

        return false;
    }

    // Eliminar especie
    public boolean eliminar(int id) {

        String sql = "DELETE FROM especies WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar especie: " + e.getMessage());
        }

        return false;
    }

    // Convertir resultado en objeto Especie
    private Especie convertirResultado(ResultSet rs) throws SQLException {

        Especie especie = new Especie();

        especie.setId(rs.getInt("id"));
        especie.setNombre(rs.getString("nombre"));
        especie.setDescripcion(rs.getString("descripcion"));

        return especie;
    }
}

