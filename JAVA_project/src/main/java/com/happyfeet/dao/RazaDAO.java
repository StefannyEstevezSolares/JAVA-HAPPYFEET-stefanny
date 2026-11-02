
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Raza;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RazaDAO {

    // Registrar raza
    public boolean registrar(Raza raza) {

        String sql = "INSERT INTO razas "
                + "(especie_id, nombre, caracteristicas) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, raza.getEspecieId());
            ps.setString(2, raza.getNombre());
            ps.setString(3, raza.getCaracteristicas());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        raza.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar raza: " + e.getMessage());
        }

        return false;
    }

    // Buscar raza por ID
    public Raza buscarPorId(int id) {

        String sql = "SELECT * FROM razas WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar raza: " + e.getMessage());
        }

        return null;
    }

    // Listar todas las razas
    public List<Raza> listarTodos() {

        List<Raza> lista = new ArrayList<>();
        String sql = "SELECT * FROM razas";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar razas: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar raza
    public boolean actualizar(Raza raza) {

        String sql = "UPDATE razas SET especie_id = ?, "
                + "nombre = ?, caracteristicas = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, raza.getEspecieId());
            ps.setString(2, raza.getNombre());
            ps.setString(3, raza.getCaracteristicas());
            ps.setInt(4, raza.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar raza: " + e.getMessage());
        }

        return false;
    }

    // Eliminar raza
    public boolean eliminar(int id) {

        String sql = "DELETE FROM razas WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar raza: " + e.getMessage());
        }

        return false;
    }

    // Convertir resultado en objeto Raza
    private Raza convertirResultado(ResultSet rs) throws SQLException {

        Raza raza = new Raza();

        raza.setId(rs.getInt("id"));
        raza.setEspecieId(rs.getInt("especie_id"));
        raza.setNombre(rs.getString("nombre"));
        raza.setCaracteristicas(rs.getString("caracteristicas"));

        return raza;
    }
}
