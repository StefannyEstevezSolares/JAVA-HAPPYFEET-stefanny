
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Mascota;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {

    // REGISTRAR UNA MASCOTA
    public int registrar(Mascota mascota) throws SQLException {

        String sql = "INSERT INTO mascotas "
                + "(dueno_id, nombre, raza_id, fecha_nacimiento, sexo, "
                + "peso_actual, microchip, tatuaje, url_foto, alergias, "
                + "condiciones_preexistentes, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, mascota.getDuenoId());
            ps.setString(2, mascota.getNombre());
            ps.setInt(3, mascota.getRazaId());

            if (mascota.getFechaNacimiento() != null) {
                ps.setDate(4, Date.valueOf(mascota.getFechaNacimiento()));
            } else {
                ps.setDate(4, null);
            }

            ps.setString(5, mascota.getSexo());
            ps.setBigDecimal(6, mascota.getPesoActual());
            ps.setString(7, mascota.getMicrochip());
            ps.setString(8, mascota.getTatuaje());
            ps.setString(9, mascota.getUrlFoto());
            ps.setString(10, mascota.getAlergias());
            ps.setString(11, mascota.getCondicionesPreexistentes());
            ps.setBoolean(12, mascota.isActivo());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        throw new SQLException("No se pudo obtener el ID de la mascota.");
    }

    // LISTAR TODAS LAS MASCOTAS
    public List<Mascota> listarTodos() throws SQLException {

        List<Mascota> lista = new ArrayList<>();

        String sql = "SELECT * FROM mascotas ORDER BY id";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Mascota mascota = convertirResultado(rs);
                lista.add(mascota);
            }
        }

        return lista;
    }

    // BUSCAR MASCOTA POR ID
    public Mascota buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM mascotas WHERE id = ?";

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

    // ACTUALIZAR UNA MASCOTA
    public boolean actualizar(Mascota mascota) throws SQLException {

        String sql = "UPDATE mascotas SET "
                + "dueno_id = ?, "
                + "nombre = ?, "
                + "raza_id = ?, "
                + "fecha_nacimiento = ?, "
                + "sexo = ?, "
                + "peso_actual = ?, "
                + "microchip = ?, "
                + "tatuaje = ?, "
                + "url_foto = ?, "
                + "alergias = ?, "
                + "condiciones_preexistentes = ?, "
                + "activo = ? "
                + "WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, mascota.getDuenoId());
            ps.setString(2, mascota.getNombre());
            ps.setInt(3, mascota.getRazaId());

            if (mascota.getFechaNacimiento() != null) {
                ps.setDate(4, Date.valueOf(mascota.getFechaNacimiento()));
            } else {
                ps.setDate(4, null);
            }

            ps.setString(5, mascota.getSexo());
            ps.setBigDecimal(6, mascota.getPesoActual());
            ps.setString(7, mascota.getMicrochip());
            ps.setString(8, mascota.getTatuaje());
            ps.setString(9, mascota.getUrlFoto());
            ps.setString(10, mascota.getAlergias());
            ps.setString(11, mascota.getCondicionesPreexistentes());
            ps.setBoolean(12, mascota.isActivo());
            ps.setInt(13, mascota.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // DESACTIVAR UNA MASCOTA
    public boolean desactivar(int id) throws SQLException {

        String sql = "UPDATE mascotas SET activo = FALSE WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }

    // CONVERTIR RESULTADO SQL EN OBJETO MASCOTA
    private Mascota convertirResultado(ResultSet rs) throws SQLException {

        Mascota mascota = new Mascota();

        mascota.setId(rs.getInt("id"));
        mascota.setDuenoId(rs.getInt("dueno_id"));
        mascota.setNombre(rs.getString("nombre"));
        mascota.setRazaId(rs.getInt("raza_id"));

        Date fechaNacimiento = rs.getDate("fecha_nacimiento");

        if (fechaNacimiento != null) {
            mascota.setFechaNacimiento(fechaNacimiento.toLocalDate());
        }

        mascota.setSexo(rs.getString("sexo"));
        mascota.setPesoActual(rs.getBigDecimal("peso_actual"));
        mascota.setMicrochip(rs.getString("microchip"));
        mascota.setTatuaje(rs.getString("tatuaje"));
        mascota.setUrlFoto(rs.getString("url_foto"));
        mascota.setAlergias(rs.getString("alergias"));
        mascota.setCondicionesPreexistentes(
                rs.getString("condiciones_preexistentes"));

        Timestamp fechaRegistro = rs.getTimestamp("fecha_registro");

        if (fechaRegistro != null) {
            mascota.setFechaRegistro(fechaRegistro.toLocalDateTime());
        }

        mascota.setActivo(rs.getBoolean("activo"));

        return mascota;
    }
}

