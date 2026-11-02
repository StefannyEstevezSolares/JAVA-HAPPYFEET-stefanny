
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Veterinario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    // REGISTRAR UN VETERINARIO
    public int registrar(Veterinario veterinario) throws SQLException {

        String sql = "INSERT INTO veterinarios "
                + "(nombre_completo, documento_identidad, "
                + "licencia_profesional, especialidad, telefono, "
                + "email, fecha_contratacion, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, veterinario.getNombreCompleto());
            ps.setString(2, veterinario.getDocumentoIdentidad());
            ps.setString(3, veterinario.getLicenciaProfesional());
            ps.setString(4, veterinario.getEspecialidad());
            ps.setString(5, veterinario.getTelefono());
            ps.setString(6, veterinario.getEmail());

            if (veterinario.getFechaContratacion() != null) {
                ps.setDate(7, Date.valueOf(
                        veterinario.getFechaContratacion()));
            } else {
                ps.setDate(7, null);
            }

            ps.setBoolean(8, veterinario.isActivo());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        throw new SQLException(
                "No se pudo obtener el ID del veterinario.");
    }

    // LISTAR TODOS LOS VETERINARIOS
    public List<Veterinario> listarTodos() throws SQLException {

        List<Veterinario> lista = new ArrayList<>();

        String sql = "SELECT * FROM veterinarios ORDER BY id";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }
        }

        return lista;
    }

    // BUSCAR VETERINARIO POR ID
    public Veterinario buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM veterinarios WHERE id = ?";

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

    // ACTUALIZAR UN VETERINARIO
    public boolean actualizar(Veterinario veterinario)
            throws SQLException {

        String sql = "UPDATE veterinarios SET "
                + "nombre_completo = ?, "
                + "documento_identidad = ?, "
                + "licencia_profesional = ?, "
                + "especialidad = ?, "
                + "telefono = ?, "
                + "email = ?, "
                + "fecha_contratacion = ?, "
                + "activo = ? "
                + "WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, veterinario.getNombreCompleto());
            ps.setString(2, veterinario.getDocumentoIdentidad());
            ps.setString(3, veterinario.getLicenciaProfesional());
            ps.setString(4, veterinario.getEspecialidad());
            ps.setString(5, veterinario.getTelefono());
            ps.setString(6, veterinario.getEmail());

            if (veterinario.getFechaContratacion() != null) {
                ps.setDate(7, Date.valueOf(
                        veterinario.getFechaContratacion()));
            } else {
                ps.setDate(7, null);
            }

            ps.setBoolean(8, veterinario.isActivo());
            ps.setInt(9, veterinario.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // DESACTIVAR UN VETERINARIO
    public boolean desactivar(int id) throws SQLException {

        String sql = "UPDATE veterinarios SET activo = FALSE WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }

    // CONVERTIR RESULTADO SQL EN OBJETO VETERINARIO
    private Veterinario convertirResultado(ResultSet rs)
            throws SQLException {

        Veterinario veterinario = new Veterinario();

        veterinario.setId(rs.getInt("id"));
        veterinario.setNombreCompleto(
                rs.getString("nombre_completo"));
        veterinario.setDocumentoIdentidad(
                rs.getString("documento_identidad"));
        veterinario.setLicenciaProfesional(
                rs.getString("licencia_profesional"));
        veterinario.setEspecialidad(rs.getString("especialidad"));
        veterinario.setTelefono(rs.getString("telefono"));
        veterinario.setEmail(rs.getString("email"));

        Date fecha = rs.getDate("fecha_contratacion");

        if (fecha != null) {
            veterinario.setFechaContratacion(fecha.toLocalDate());
        }

        veterinario.setActivo(rs.getBoolean("activo"));

        return veterinario;
    }
}
