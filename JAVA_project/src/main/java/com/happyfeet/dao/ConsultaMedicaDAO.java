
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.ConsultaMedica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConsultaMedicaDAO {

    // 1. REGISTRAR UNA CONSULTA MEDICA
    public int registrar(ConsultaMedica consulta) throws SQLException {

        String sql = "INSERT INTO consultas_medicas "
                + "(mascota_id, veterinario_id, cita_id, fecha_hora, "
                + "motivo, sintomas, diagnostico, recomendaciones, "
                + "observaciones, peso_registrado, temperatura) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, consulta.getMascotaId());
            ps.setInt(2, consulta.getVeterinarioId());

            if (consulta.getCitaId() != null) {
                ps.setInt(3, consulta.getCitaId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setTimestamp(4, Timestamp.valueOf(consulta.getFechaHora()));
            ps.setString(5, consulta.getMotivo());
            ps.setString(6, consulta.getSintomas());
            ps.setString(7, consulta.getDiagnostico());
            ps.setString(8, consulta.getRecomendaciones());
            ps.setString(9, consulta.getObservaciones());
            ps.setBigDecimal(10, consulta.getPesoRegistrado());
            ps.setBigDecimal(11, consulta.getTemperatura());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        throw new SQLException("No se pudo obtener el ID de la consulta.");
    }

    // 2. BUSCAR CONSULTA POR ID
    public ConsultaMedica buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM consultas_medicas WHERE id = ?";

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

    // 3. LISTAR TODAS LAS CONSULTAS
    public List<ConsultaMedica> listarTodos() throws SQLException {

        List<ConsultaMedica> lista = new ArrayList<>();

        String sql = "SELECT * FROM consultas_medicas ORDER BY fecha_hora DESC";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }
        }

        return lista;
    }

    // 4. ACTUALIZAR CONSULTA
    public boolean actualizar(ConsultaMedica consulta) throws SQLException {

        String sql = "UPDATE consultas_medicas SET "
                + "mascota_id = ?, "
                + "veterinario_id = ?, "
                + "cita_id = ?, "
                + "fecha_hora = ?, "
                + "motivo = ?, "
                + "sintomas = ?, "
                + "diagnostico = ?, "
                + "recomendaciones = ?, "
                + "observaciones = ?, "
                + "peso_registrado = ?, "
                + "temperatura = ? "
                + "WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, consulta.getMascotaId());
            ps.setInt(2, consulta.getVeterinarioId());

            if (consulta.getCitaId() != null) {
                ps.setInt(3, consulta.getCitaId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setTimestamp(4, Timestamp.valueOf(consulta.getFechaHora()));
            ps.setString(5, consulta.getMotivo());
            ps.setString(6, consulta.getSintomas());
            ps.setString(7, consulta.getDiagnostico());
            ps.setString(8, consulta.getRecomendaciones());
            ps.setString(9, consulta.getObservaciones());
            ps.setBigDecimal(10, consulta.getPesoRegistrado());
            ps.setBigDecimal(11, consulta.getTemperatura());
            ps.setInt(12, consulta.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // 5. ELIMINAR CONSULTA
    public boolean eliminar(int id) throws SQLException {

        String sql = "DELETE FROM consultas_medicas WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }

    // 6. CONVERTIR RESULTADO SQL EN OBJETO
    private ConsultaMedica convertirResultado(ResultSet rs)
            throws SQLException {

        ConsultaMedica consulta = new ConsultaMedica();

        consulta.setId(rs.getInt("id"));
        consulta.setMascotaId(rs.getInt("mascota_id"));
        consulta.setVeterinarioId(rs.getInt("veterinario_id"));

        int citaId = rs.getInt("cita_id");

        if (rs.wasNull()) {
            consulta.setCitaId(null);
        } else {
            consulta.setCitaId(citaId);
        }

        consulta.setFechaHora(
                rs.getTimestamp("fecha_hora").toLocalDateTime());

        consulta.setMotivo(rs.getString("motivo"));
        consulta.setSintomas(rs.getString("sintomas"));
        consulta.setDiagnostico(rs.getString("diagnostico"));
        consulta.setRecomendaciones(rs.getString("recomendaciones"));
        consulta.setObservaciones(rs.getString("observaciones"));
        consulta.setPesoRegistrado(rs.getBigDecimal("peso_registrado"));
        consulta.setTemperatura(rs.getBigDecimal("temperatura"));

        return consulta;
    }
}
