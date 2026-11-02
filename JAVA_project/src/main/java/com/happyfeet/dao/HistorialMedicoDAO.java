
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.HistorialMedico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialMedicoDAO {

    public boolean registrar(HistorialMedico historial) {

        String sql = "INSERT INTO historial_medico "
                + "(mascota_id, fecha_evento, evento_tipo_id, descripcion, "
                + "diagnostico, tratamiento_recomendado, veterinario_id, "
                + "consulta_id, procedimiento_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, historial.getMascotaId());
            ps.setDate(2, Date.valueOf(historial.getFechaEvento()));
            ps.setInt(3, historial.getEventoTipoId());
            ps.setString(4, historial.getDescripcion());
            ps.setString(5, historial.getDiagnostico());
            ps.setString(6, historial.getTratamientoRecomendado());

            if (historial.getVeterinarioId() != null) {
                ps.setInt(7, historial.getVeterinarioId());
            } else {
                ps.setNull(7, Types.INTEGER);
            }

            if (historial.getConsultaId() != null) {
                ps.setInt(8, historial.getConsultaId());
            } else {
                ps.setNull(8, Types.INTEGER);
            }

            if (historial.getProcedimientoId() != null) {
                ps.setInt(9, historial.getProcedimientoId());
            } else {
                ps.setNull(9, Types.INTEGER);
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar historial: " + e.getMessage());
            return false;
        }
    }

    public HistorialMedico buscarPorId(int id) {

        String sql = "SELECT * FROM historial_medico WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar historial: " + e.getMessage());
        }

        return null;
    }

    public List<HistorialMedico> listarTodos() {

        List<HistorialMedico> lista = new ArrayList<>();

        String sql = "SELECT * FROM historial_medico ORDER BY id DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar historiales: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(HistorialMedico historial) {

        String sql = "UPDATE historial_medico SET "
                + "mascota_id = ?, fecha_evento = ?, evento_tipo_id = ?, "
                + "descripcion = ?, diagnostico = ?, "
                + "tratamiento_recomendado = ?, veterinario_id = ?, "
                + "consulta_id = ?, procedimiento_id = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, historial.getMascotaId());
            ps.setDate(2, Date.valueOf(historial.getFechaEvento()));
            ps.setInt(3, historial.getEventoTipoId());
            ps.setString(4, historial.getDescripcion());
            ps.setString(5, historial.getDiagnostico());
            ps.setString(6, historial.getTratamientoRecomendado());

            if (historial.getVeterinarioId() != null) {
                ps.setInt(7, historial.getVeterinarioId());
            } else {
                ps.setNull(7, Types.INTEGER);
            }

            if (historial.getConsultaId() != null) {
                ps.setInt(8, historial.getConsultaId());
            } else {
                ps.setNull(8, Types.INTEGER);
            }

            if (historial.getProcedimientoId() != null) {
                ps.setInt(9, historial.getProcedimientoId());
            } else {
                ps.setNull(9, Types.INTEGER);
            }

            ps.setInt(10, historial.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar historial: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM historial_medico WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar historial: " + e.getMessage());
            return false;
        }
    }

    private HistorialMedico convertirResultado(ResultSet rs)
            throws SQLException {

        HistorialMedico historial = new HistorialMedico();

        historial.setId(rs.getInt("id"));
        historial.setMascotaId(rs.getInt("mascota_id"));
        historial.setFechaEvento(rs.getDate("fecha_evento").toLocalDate());
        historial.setEventoTipoId(rs.getInt("evento_tipo_id"));
        historial.setDescripcion(rs.getString("descripcion"));
        historial.setDiagnostico(rs.getString("diagnostico"));
        historial.setTratamientoRecomendado(
                rs.getString("tratamiento_recomendado")
        );

        int veterinarioId = rs.getInt("veterinario_id");
        historial.setVeterinarioId(
                rs.wasNull() ? null : veterinarioId
        );

        int consultaId = rs.getInt("consulta_id");
        historial.setConsultaId(
                rs.wasNull() ? null : consultaId
        );

        int procedimientoId = rs.getInt("procedimiento_id");
        historial.setProcedimientoId(
                rs.wasNull() ? null : procedimientoId
        );

        return historial;
    }
}
