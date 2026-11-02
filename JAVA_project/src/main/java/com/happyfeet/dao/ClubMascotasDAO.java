
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.ClubMascotas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubMascotasDAO {

    public boolean registrar(ClubMascotas club) {
        String sql = "INSERT INTO club_mascotas "
                + "(dueno_id, puntos_acumulados, puntos_canjeados, "
                + "puntos_disponibles, nivel, fecha_inscripcion, "
                + "fecha_ultima_actualizacion, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, club.getDuenoId());
            ps.setInt(2, club.getPuntosAcumulados());
            ps.setInt(3, club.getPuntosCanjeados());
            ps.setInt(4, club.getPuntosDisponibles());
            ps.setString(5, club.getNivel());
            ps.setDate(6, Date.valueOf(club.getFechaInscripcion()));
            ps.setTimestamp(7, Timestamp.valueOf(club.getFechaUltimaActualizacion()));
            ps.setObject(8, club.getActivo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar club: " + e.getMessage());
            return false;
        }
    }

    public ClubMascotas buscarPorId(int id) {
        String sql = "SELECT * FROM club_mascotas WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar club: " + e.getMessage());
        }

        return null;
    }

    public List<ClubMascotas> listarTodos() {
        List<ClubMascotas> lista = new ArrayList<>();
        String sql = "SELECT * FROM club_mascotas";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clubes: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(ClubMascotas club) {
        String sql = "UPDATE club_mascotas SET "
                + "dueno_id = ?, puntos_acumulados = ?, "
                + "puntos_canjeados = ?, puntos_disponibles = ?, "
                + "nivel = ?, fecha_inscripcion = ?, "
                + "fecha_ultima_actualizacion = ?, activo = ? "
                + "WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, club.getDuenoId());
            ps.setInt(2, club.getPuntosAcumulados());
            ps.setInt(3, club.getPuntosCanjeados());
            ps.setInt(4, club.getPuntosDisponibles());
            ps.setString(5, club.getNivel());
            ps.setDate(6, Date.valueOf(club.getFechaInscripcion()));
            ps.setTimestamp(7, Timestamp.valueOf(club.getFechaUltimaActualizacion()));
            ps.setObject(8, club.getActivo());
            ps.setInt(9, club.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar club: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM club_mascotas WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar club: " + e.getMessage());
            return false;
        }
    }

    private ClubMascotas convertirResultado(ResultSet rs) throws SQLException {
        ClubMascotas club = new ClubMascotas();

        club.setId(rs.getInt("id"));
        club.setDuenoId(rs.getInt("dueno_id"));
        club.setPuntosAcumulados(rs.getInt("puntos_acumulados"));
        club.setPuntosCanjeados(rs.getInt("puntos_canjeados"));
        club.setPuntosDisponibles(rs.getInt("puntos_disponibles"));
        club.setNivel(rs.getString("nivel"));
        club.setFechaInscripcion(rs.getDate("fecha_inscripcion").toLocalDate());

        Timestamp fecha = rs.getTimestamp("fecha_ultima_actualizacion");
        club.setFechaUltimaActualizacion(
                fecha != null ? fecha.toLocalDateTime() : null
        );

        boolean activo = rs.getBoolean("activo");
        club.setActivo(rs.wasNull() ? null : activo);

        return club;
    }
}
