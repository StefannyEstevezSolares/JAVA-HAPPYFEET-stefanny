
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Dueno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DuenoDAO {

    // REGISTRAR UN DUEÑO
    public int registrar(Dueno dueno) throws SQLException {

        String sql = "INSERT INTO duenos "
                + "(nombre_completo, documento_identidad, direccion, "
                + "telefono, email, contacto_emergencia, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dueno.getNombreCompleto());
            ps.setString(2, dueno.getDocumentoIdentidad());
            ps.setString(3, dueno.getDireccion());
            ps.setString(4, dueno.getTelefono());
            ps.setString(5, dueno.getEmail());
            ps.setString(6, dueno.getContactoEmergencia());
            ps.setBoolean(7, dueno.isActivo());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        throw new SQLException("No se pudo obtener el ID del dueño.");
    }

    // LISTAR TODOS LOS DUEÑOS
    public List<Dueno> listarTodos() throws SQLException {

        List<Dueno> lista = new ArrayList<>();

        String sql = "SELECT * FROM duenos ORDER BY id";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Dueno dueno = convertirResultado(rs);
                lista.add(dueno);
            }
        }

        return lista;
    }

    // BUSCAR UN DUEÑO POR ID
    public Dueno buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM duenos WHERE id = ?";

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

    // ACTUALIZAR LOS DATOS DE UN DUEÑO
    public boolean actualizar(Dueno dueno) throws SQLException {

        String sql = "UPDATE duenos SET "
                + "nombre_completo = ?, "
                + "documento_identidad = ?, "
                + "direccion = ?, "
                + "telefono = ?, "
                + "email = ?, "
                + "contacto_emergencia = ?, "
                + "activo = ? "
                + "WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dueno.getNombreCompleto());
            ps.setString(2, dueno.getDocumentoIdentidad());
            ps.setString(3, dueno.getDireccion());
            ps.setString(4, dueno.getTelefono());
            ps.setString(5, dueno.getEmail());
            ps.setString(6, dueno.getContactoEmergencia());
            ps.setBoolean(7, dueno.isActivo());
            ps.setInt(8, dueno.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // DESACTIVAR UN DUEÑO
    public boolean desactivar(int id) throws SQLException {

        String sql = "UPDATE duenos SET activo = FALSE WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }

    // CONVERTIR RESULTADO SQL EN OBJETO DUENO
    private Dueno convertirResultado(ResultSet rs) throws SQLException {

        Dueno dueno = new Dueno();

        dueno.setId(rs.getInt("id"));
        dueno.setNombreCompleto(rs.getString("nombre_completo"));
        dueno.setDocumentoIdentidad(rs.getString("documento_identidad"));
        dueno.setDireccion(rs.getString("direccion"));
        dueno.setTelefono(rs.getString("telefono"));
        dueno.setEmail(rs.getString("email"));
        dueno.setContactoEmergencia(
                rs.getString("contacto_emergencia"));

        Timestamp fecha = rs.getTimestamp("fecha_registro");

        if (fecha != null) {
            dueno.setFechaRegistro(fecha.toLocalDateTime());
        }

        dueno.setActivo(rs.getBoolean("activo"));

        return dueno;
    }
}
