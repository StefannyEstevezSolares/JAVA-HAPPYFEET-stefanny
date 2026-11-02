
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.MascotaAdopcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaAdopcionDAO {

    // Registrar mascota para adopción
    public boolean registrar(MascotaAdopcion mascota) {

        String sql = "INSERT INTO mascotas_adopcion "
                + "(mascota_id, fecha_ingreso, motivo_ingreso, estado, "
                + "historia, temperamento, necesidades_especiales, "
                + "foto_adicional_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, mascota.getMascotaId());
            ps.setDate(2, Date.valueOf(mascota.getFechaIngreso()));
            ps.setString(3, mascota.getMotivoIngreso());
            ps.setString(4, mascota.getEstado());
            ps.setString(5, mascota.getHistoria());
            ps.setString(6, mascota.getTemperamento());
            ps.setString(7, mascota.getNecesidadesEspeciales());
            ps.setString(8, mascota.getFotoAdicionalUrl());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        mascota.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar mascota: " + e.getMessage());
        }

        return false;
    }

    // Buscar mascota por ID
    public MascotaAdopcion buscarPorId(int id) {

        String sql = "SELECT * FROM mascotas_adopcion WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar mascota: " + e.getMessage());
        }

        return null;
    }

    // Listar todas las mascotas en adopción
    public List<MascotaAdopcion> listarTodos() {

        List<MascotaAdopcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas_adopcion";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar mascotas: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar mascota
    public boolean actualizar(MascotaAdopcion mascota) {

        String sql = "UPDATE mascotas_adopcion SET mascota_id = ?, "
                + "fecha_ingreso = ?, motivo_ingreso = ?, estado = ?, "
                + "historia = ?, temperamento = ?, "
                + "necesidades_especiales = ?, foto_adicional_url = ? "
                + "WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, mascota.getMascotaId());
            ps.setDate(2, Date.valueOf(mascota.getFechaIngreso()));
            ps.setString(3, mascota.getMotivoIngreso());
            ps.setString(4, mascota.getEstado());
            ps.setString(5, mascota.getHistoria());
            ps.setString(6, mascota.getTemperamento());
            ps.setString(7, mascota.getNecesidadesEspeciales());
            ps.setString(8, mascota.getFotoAdicionalUrl());
            ps.setInt(9, mascota.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar mascota: " + e.getMessage());
        }

        return false;
    }

    // Eliminar mascota
    public boolean eliminar(int id) {

        String sql = "DELETE FROM mascotas_adopcion WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar mascota: " + e.getMessage());
        }

        return false;
    }

    // Convertir resultado en objeto MascotaAdopcion
    private MascotaAdopcion convertirResultado(ResultSet rs)
            throws SQLException {

        MascotaAdopcion mascota = new MascotaAdopcion();

        mascota.setId(rs.getInt("id"));
        mascota.setMascotaId(rs.getInt("mascota_id"));
        mascota.setFechaIngreso(rs.getDate("fecha_ingreso").toLocalDate());
        mascota.setMotivoIngreso(rs.getString("motivo_ingreso"));
        mascota.setEstado(rs.getString("estado"));
        mascota.setHistoria(rs.getString("historia"));
        mascota.setTemperamento(rs.getString("temperamento"));
        mascota.setNecesidadesEspeciales(
                rs.getString("necesidades_especiales"));
        mascota.setFotoAdicionalUrl(rs.getString("foto_adicional_url"));

        return mascota;
    }
}
