
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.JornadaVacunacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JornadaVacunacionDAO {

    // Registrar jornada
    public boolean registrar(JornadaVacunacion jornada) {

        String sql = "INSERT INTO jornadas_vacunacion "
                + "(nombre, fecha, hora_inicio, hora_fin, ubicacion, "
                + "descripcion, capacidad_maxima, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, jornada.getNombre());
            ps.setDate(2, Date.valueOf(jornada.getFecha()));
            ps.setTime(3, Time.valueOf(jornada.getHoraInicio()));
            ps.setTime(4, Time.valueOf(jornada.getHoraFin()));
            ps.setString(5, jornada.getUbicacion());
            ps.setString(6, jornada.getDescripcion());
            ps.setObject(7, jornada.getCapacidadMaxima());
            ps.setString(8, jornada.getEstado());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        jornada.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar jornada: " + e.getMessage());
        }

        return false;
    }

    // Buscar jornada por ID
    public JornadaVacunacion buscarPorId(int id) {

        String sql = "SELECT * FROM jornadas_vacunacion WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar jornada: " + e.getMessage());
        }

        return null;
    }

    // Listar todas las jornadas
    public List<JornadaVacunacion> listarTodos() {

        List<JornadaVacunacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM jornadas_vacunacion";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar jornadas: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar jornada
    public boolean actualizar(JornadaVacunacion jornada) {

        String sql = "UPDATE jornadas_vacunacion SET nombre = ?, "
                + "fecha = ?, hora_inicio = ?, hora_fin = ?, "
                + "ubicacion = ?, descripcion = ?, "
                + "capacidad_maxima = ?, estado = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, jornada.getNombre());
            ps.setDate(2, Date.valueOf(jornada.getFecha()));
            ps.setTime(3, Time.valueOf(jornada.getHoraInicio()));
            ps.setTime(4, Time.valueOf(jornada.getHoraFin()));
            ps.setString(5, jornada.getUbicacion());
            ps.setString(6, jornada.getDescripcion());
            ps.setObject(7, jornada.getCapacidadMaxima());
            ps.setString(8, jornada.getEstado());
            ps.setInt(9, jornada.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar jornada: " + e.getMessage());
        }

        return false;
    }

    // Eliminar jornada
    public boolean eliminar(int id) {

        String sql = "DELETE FROM jornadas_vacunacion WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar jornada: " + e.getMessage());
        }

        return false;
    }

    // Convertir resultado en objeto JornadaVacunacion
    private JornadaVacunacion convertirResultado(ResultSet rs) throws SQLException {

        JornadaVacunacion jornada = new JornadaVacunacion();

        jornada.setId(rs.getInt("id"));
        jornada.setNombre(rs.getString("nombre"));
        jornada.setFecha(rs.getDate("fecha").toLocalDate());
        jornada.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
        jornada.setHoraFin(rs.getTime("hora_fin").toLocalTime());
        jornada.setUbicacion(rs.getString("ubicacion"));
        jornada.setDescripcion(rs.getString("descripcion"));
        jornada.setCapacidadMaxima(
                (Integer) rs.getObject("capacidad_maxima"));
        jornada.setEstado(rs.getString("estado"));

        return jornada;
    }
}

