
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.RegistroJornadaVacunacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroJornadaVacunacionDAO {

    // Registrar vacunación
    public boolean registrar(RegistroJornadaVacunacion registro) {

        String sql = "INSERT INTO registros_jornadas_vacunacion "
                + "(jornada_id, mascota_id, dueno_id, vacuna_id, "
                + "veterinario_id, fecha_hora, lote_vacuna, "
                + "proxima_dosis, observaciones) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, registro.getJornadaId());
            ps.setInt(2, registro.getMascotaId());
            ps.setInt(3, registro.getDuenoId());
            ps.setInt(4, registro.getVacunaId());
            ps.setObject(5, registro.getVeterinarioId());
            ps.setTimestamp(6, Timestamp.valueOf(registro.getFechaHora()));
            ps.setString(7, registro.getLoteVacuna());

            if (registro.getProximaDosis() != null) {
                ps.setDate(8, Date.valueOf(registro.getProximaDosis()));
            } else {
                ps.setNull(8, Types.DATE);
            }

            ps.setString(9, registro.getObservaciones());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        registro.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar vacunación: " + e.getMessage());
        }

        return false;
    }

    // Buscar registro por ID
    public RegistroJornadaVacunacion buscarPorId(int id) {

        String sql = "SELECT * FROM registros_jornadas_vacunacion WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar registro: " + e.getMessage());
        }

        return null;
    }

    // Listar todos los registros
    public List<RegistroJornadaVacunacion> listarTodos() {

        List<RegistroJornadaVacunacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM registros_jornadas_vacunacion";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar registros: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar registro
    public boolean actualizar(RegistroJornadaVacunacion registro) {

        String sql = "UPDATE registros_jornadas_vacunacion SET "
                + "jornada_id = ?, mascota_id = ?, dueno_id = ?, "
                + "vacuna_id = ?, veterinario_id = ?, fecha_hora = ?, "
                + "lote_vacuna = ?, proxima_dosis = ?, observaciones = ? "
                + "WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, registro.getJornadaId());
            ps.setInt(2, registro.getMascotaId());
            ps.setInt(3, registro.getDuenoId());
            ps.setInt(4, registro.getVacunaId());
            ps.setObject(5, registro.getVeterinarioId());
            ps.setTimestamp(6, Timestamp.valueOf(registro.getFechaHora()));
            ps.setString(7, registro.getLoteVacuna());

            if (registro.getProximaDosis() != null) {
                ps.setDate(8, Date.valueOf(registro.getProximaDosis()));
            } else {
                ps.setNull(8, Types.DATE);
            }

            ps.setString(9, registro.getObservaciones());
            ps.setInt(10, registro.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar registro: " + e.getMessage());
        }

        return false;
    }

    // Eliminar registro
    public boolean eliminar(int id) {

        String sql = "DELETE FROM registros_jornadas_vacunacion WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar registro: " + e.getMessage());
        }

        return false;
    }

    // Convertir resultado en objeto RegistroJornadaVacunacion
    private RegistroJornadaVacunacion convertirResultado(ResultSet rs)
            throws SQLException {

        RegistroJornadaVacunacion registro = new RegistroJornadaVacunacion();

        registro.setId(rs.getInt("id"));
        registro.setJornadaId(rs.getInt("jornada_id"));
        registro.setMascotaId(rs.getInt("mascota_id"));
        registro.setDuenoId(rs.getInt("dueno_id"));
        registro.setVacunaId(rs.getInt("vacuna_id"));

        registro.setVeterinarioId(
                (Integer) rs.getObject("veterinario_id"));

        registro.setFechaHora(
                rs.getTimestamp("fecha_hora").toLocalDateTime());

        registro.setLoteVacuna(rs.getString("lote_vacuna"));

        Date proximaDosis = rs.getDate("proxima_dosis");

        if (proximaDosis != null) {
            registro.setProximaDosis(proximaDosis.toLocalDate());
        }

        registro.setObservaciones(rs.getString("observaciones"));

        return registro;
    }
}
