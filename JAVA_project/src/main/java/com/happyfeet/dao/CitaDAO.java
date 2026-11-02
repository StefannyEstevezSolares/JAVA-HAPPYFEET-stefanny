
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Cita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    // 1. REGISTRAR UNA CITA
    public int registrar(Cita cita) throws SQLException {

        String sql = "INSERT INTO citas "
                + "(mascota_id, veterinario_id, fecha_hora, "
                + "motivo, estado_id, observaciones) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, cita.getMascotaId());

            if (cita.getVeterinarioId() != null) {
                ps.setInt(2, cita.getVeterinarioId());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            ps.setTimestamp(3, Timestamp.valueOf(cita.getFechaHora()));
            ps.setString(4, cita.getMotivo());
            ps.setInt(5, cita.getEstadoId());
            ps.setString(6, cita.getObservaciones());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        throw new SQLException("No se pudo obtener el ID de la cita.");
    }

    // 2. BUSCAR UNA CITA POR ID
    public Cita buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM citas WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }
        }

        return null;
    }

    // 3. LISTAR TODAS LAS CITAS
    public List<Cita> listarTodos() throws SQLException {

        List<Cita> lista = new ArrayList<>();

        String sql = "SELECT * FROM citas ORDER BY fecha_hora";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }
        }

        return lista;
    }

    // 4. ACTUALIZAR UNA CITA
    public boolean actualizar(Cita cita) throws SQLException {

        String sql = "UPDATE citas SET "
                + "mascota_id = ?, "
                + "veterinario_id = ?, "
                + "fecha_hora = ?, "
                + "motivo = ?, "
                + "estado_id = ?, "
                + "observaciones = ? "
                + "WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, cita.getMascotaId());

            if (cita.getVeterinarioId() != null) {
                ps.setInt(2, cita.getVeterinarioId());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            ps.setTimestamp(3, Timestamp.valueOf(cita.getFechaHora()));
            ps.setString(4, cita.getMotivo());
            ps.setInt(5, cita.getEstadoId());
            ps.setString(6, cita.getObservaciones());
            ps.setInt(7, cita.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // 5. ELIMINAR UNA CITA
    public boolean eliminar(int id) throws SQLException {

        String sql = "DELETE FROM citas WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }

    // 6. CONVERTIR RESULTADO SQL EN OBJETO CITA
    private Cita convertirResultado(ResultSet rs) throws SQLException {

        Cita cita = new Cita();

        cita.setId(rs.getInt("id"));
        cita.setMascotaId(rs.getInt("mascota_id"));

        int veterinarioId = rs.getInt("veterinario_id");

        if (rs.wasNull()) {
            cita.setVeterinarioId(null);
        } else {
            cita.setVeterinarioId(veterinarioId);
        }

        cita.setFechaHora(
                rs.getTimestamp("fecha_hora").toLocalDateTime());

        cita.setMotivo(rs.getString("motivo"));
        cita.setEstadoId(rs.getInt("estado_id"));
        cita.setObservaciones(rs.getString("observaciones"));

        return cita;
        
    }
}
