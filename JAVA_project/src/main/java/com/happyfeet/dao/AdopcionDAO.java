
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Adopcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdopcionDAO {

    public boolean registrar(Adopcion adopcion) {
        String sql = "INSERT INTO adopciones "
                + "(mascota_adopcion_id, dueno_id, fecha_adopcion, "
                + "contrato_texto, condiciones_especiales, "
                + "seguimiento_requerido, fecha_primer_seguimiento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, adopcion.getMascotaAdopcionId());
            ps.setInt(2, adopcion.getDuenoId());
            ps.setDate(3, Date.valueOf(adopcion.getFechaAdopcion()));
            ps.setString(4, adopcion.getContratoTexto());
            ps.setString(5, adopcion.getCondicionesEspeciales());
            ps.setObject(6, adopcion.getSeguimientoRequerido());

            if (adopcion.getFechaPrimerSeguimiento() != null) {
                ps.setDate(7, Date.valueOf(adopcion.getFechaPrimerSeguimiento()));
            } else {
                ps.setNull(7, Types.DATE);
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar adopción: " + e.getMessage());
            return false;
        }
    }

    public Adopcion buscarPorId(int id) {
        String sql = "SELECT * FROM adopciones WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar adopción: " + e.getMessage());
        }

        return null;
    }

    public List<Adopcion> listarTodos() {
        List<Adopcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM adopciones";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar adopciones: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Adopcion adopcion) {
        String sql = "UPDATE adopciones SET "
                + "mascota_adopcion_id = ?, dueno_id = ?, "
                + "fecha_adopcion = ?, contrato_texto = ?, "
                + "condiciones_especiales = ?, seguimiento_requerido = ?, "
                + "fecha_primer_seguimiento = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, adopcion.getMascotaAdopcionId());
            ps.setInt(2, adopcion.getDuenoId());
            ps.setDate(3, Date.valueOf(adopcion.getFechaAdopcion()));
            ps.setString(4, adopcion.getContratoTexto());
            ps.setString(5, adopcion.getCondicionesEspeciales());
            ps.setObject(6, adopcion.getSeguimientoRequerido());

            if (adopcion.getFechaPrimerSeguimiento() != null) {
                ps.setDate(7, Date.valueOf(adopcion.getFechaPrimerSeguimiento()));
            } else {
                ps.setNull(7, Types.DATE);
            }

            ps.setInt(8, adopcion.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar adopción: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM adopciones WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar adopción: " + e.getMessage());
            return false;
        }
    }

    private Adopcion convertirResultado(ResultSet rs) throws SQLException {
        Adopcion adopcion = new Adopcion();

        adopcion.setId(rs.getInt("id"));
        adopcion.setMascotaAdopcionId(rs.getInt("mascota_adopcion_id"));
        adopcion.setDuenoId(rs.getInt("dueno_id"));
        adopcion.setFechaAdopcion(rs.getDate("fecha_adopcion").toLocalDate());
        adopcion.setContratoTexto(rs.getString("contrato_texto"));
        adopcion.setCondicionesEspeciales(rs.getString("condiciones_especiales"));

        boolean seguimiento = rs.getBoolean("seguimiento_requerido");
        adopcion.setSeguimientoRequerido(
                rs.wasNull() ? null : seguimiento
        );

        Date fechaSeguimiento = rs.getDate("fecha_primer_seguimiento");
        adopcion.setFechaPrimerSeguimiento(
                fechaSeguimiento != null ? fechaSeguimiento.toLocalDate() : null
        );

        return adopcion;
    }
}
