
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.ProcedimientoEspecial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcedimientoEspecialDAO {

    public boolean registrar(ProcedimientoEspecial procedimiento) {

        String sql = "INSERT INTO procedimientos_especiales "
                + "(mascota_id, veterinario_id, tipo_procedimiento, "
                + "nombre_procedimiento, fecha_hora, duracion_estimada_minutos, "
                + "informacion_preoperatoria, detalle_procedimiento, "
                + "complicaciones, seguimiento_postoperatorio, proximo_control, "
                + "estado, costo_procedimiento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, procedimiento.getMascotaId());
            ps.setInt(2, procedimiento.getVeterinarioId());
            ps.setString(3, procedimiento.getTipoProcedimiento());
            ps.setString(4, procedimiento.getNombreProcedimiento());
            ps.setTimestamp(5, Timestamp.valueOf(procedimiento.getFechaHora()));

            if (procedimiento.getDuracionEstimadaMinutos() != null) {
                ps.setInt(6, procedimiento.getDuracionEstimadaMinutos());
            } else {
                ps.setNull(6, Types.INTEGER);
            }

            ps.setString(7, procedimiento.getInformacionPreoperatoria());
            ps.setString(8, procedimiento.getDetalleProcedimiento());
            ps.setString(9, procedimiento.getComplicaciones());
            ps.setString(10, procedimiento.getSeguimientoPostoperatorio());

            if (procedimiento.getProximoControl() != null) {
                ps.setDate(11, Date.valueOf(procedimiento.getProximoControl()));
            } else {
                ps.setNull(11, Types.DATE);
            }

            ps.setString(12, procedimiento.getEstado());

            if (procedimiento.getCostoProcedimiento() != null) {
                ps.setBigDecimal(13, procedimiento.getCostoProcedimiento());
            } else {
                ps.setNull(13, Types.DECIMAL);
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar procedimiento: " + e.getMessage());
            return false;
        }
    }

    public ProcedimientoEspecial buscarPorId(int id) {

        String sql = "SELECT * FROM procedimientos_especiales WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar procedimiento: " + e.getMessage());
        }

        return null;
    }

    public List<ProcedimientoEspecial> listarTodos() {

        List<ProcedimientoEspecial> lista = new ArrayList<>();

        String sql = "SELECT * FROM procedimientos_especiales ORDER BY id DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar procedimientos: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(ProcedimientoEspecial procedimiento) {

        String sql = "UPDATE procedimientos_especiales SET "
                + "mascota_id = ?, veterinario_id = ?, tipo_procedimiento = ?, "
                + "nombre_procedimiento = ?, fecha_hora = ?, "
                + "duracion_estimada_minutos = ?, informacion_preoperatoria = ?, "
                + "detalle_procedimiento = ?, complicaciones = ?, "
                + "seguimiento_postoperatorio = ?, proximo_control = ?, "
                + "estado = ?, costo_procedimiento = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, procedimiento.getMascotaId());
            ps.setInt(2, procedimiento.getVeterinarioId());
            ps.setString(3, procedimiento.getTipoProcedimiento());
            ps.setString(4, procedimiento.getNombreProcedimiento());
            ps.setTimestamp(5, Timestamp.valueOf(procedimiento.getFechaHora()));

            if (procedimiento.getDuracionEstimadaMinutos() != null) {
                ps.setInt(6, procedimiento.getDuracionEstimadaMinutos());
            } else {
                ps.setNull(6, Types.INTEGER);
            }

            ps.setString(7, procedimiento.getInformacionPreoperatoria());
            ps.setString(8, procedimiento.getDetalleProcedimiento());
            ps.setString(9, procedimiento.getComplicaciones());
            ps.setString(10, procedimiento.getSeguimientoPostoperatorio());

            if (procedimiento.getProximoControl() != null) {
                ps.setDate(11, Date.valueOf(procedimiento.getProximoControl()));
            } else {
                ps.setNull(11, Types.DATE);
            }

            ps.setString(12, procedimiento.getEstado());

            if (procedimiento.getCostoProcedimiento() != null) {
                ps.setBigDecimal(13, procedimiento.getCostoProcedimiento());
            } else {
                ps.setNull(13, Types.DECIMAL);
            }

            ps.setInt(14, procedimiento.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar procedimiento: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM procedimientos_especiales WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar procedimiento: " + e.getMessage());
            return false;
        }
    }

    private ProcedimientoEspecial convertirResultado(ResultSet rs)
            throws SQLException {

        ProcedimientoEspecial procedimiento = new ProcedimientoEspecial();

        procedimiento.setId(rs.getInt("id"));
        procedimiento.setMascotaId(rs.getInt("mascota_id"));
        procedimiento.setVeterinarioId(rs.getInt("veterinario_id"));
        procedimiento.setTipoProcedimiento(rs.getString("tipo_procedimiento"));
        procedimiento.setNombreProcedimiento(rs.getString("nombre_procedimiento"));
        procedimiento.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());

        int duracion = rs.getInt("duracion_estimada_minutos");
        procedimiento.setDuracionEstimadaMinutos(
                rs.wasNull() ? null : duracion
        );

        procedimiento.setInformacionPreoperatoria(
                rs.getString("informacion_preoperatoria")
        );
        procedimiento.setDetalleProcedimiento(
                rs.getString("detalle_procedimiento")
        );
        procedimiento.setComplicaciones(rs.getString("complicaciones"));
        procedimiento.setSeguimientoPostoperatorio(
                rs.getString("seguimiento_postoperatorio")
        );

        Date proximoControl = rs.getDate("proximo_control");
        procedimiento.setProximoControl(
                proximoControl != null ? proximoControl.toLocalDate() : null
        );

        procedimiento.setEstado(rs.getString("estado"));
        procedimiento.setCostoProcedimiento(
                rs.getBigDecimal("costo_procedimiento")
        );

        return procedimiento;
    }
}

