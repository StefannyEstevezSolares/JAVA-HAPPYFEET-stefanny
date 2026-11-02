
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.EventoTipo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoTipoDAO {

    // REGISTRAR
    public boolean registrar(EventoTipo evento) {
        String sql = "INSERT INTO evento_tipos (nombre, descripcion) VALUES (?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getDescripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar tipo de evento: " + e.getMessage());
            return false;
        }
    }

    // BUSCAR POR ID
    public EventoTipo buscarPorId(int id) {
        String sql = "SELECT * FROM evento_tipos WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar tipo de evento: " + e.getMessage());
        }

        return null;
    }

    // LISTAR TODOS
    public List<EventoTipo> listarTodos() {
        List<EventoTipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM evento_tipos";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar tipos de eventos: " + e.getMessage());
        }

        return lista;
    }

    // ACTUALIZAR
    public boolean actualizar(EventoTipo evento) {
        String sql = "UPDATE evento_tipos SET nombre = ?, descripcion = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getDescripcion());
            ps.setInt(3, evento.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar tipo de evento: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        String sql = "DELETE FROM evento_tipos WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar tipo de evento: " + e.getMessage());
            return false;
        }
    }

    // CONVERTIR RESULTADO
    private EventoTipo convertirResultado(ResultSet rs) throws SQLException {
        EventoTipo evento = new EventoTipo();

        evento.setId(rs.getInt("id"));
        evento.setNombre(rs.getString("nombre"));
        evento.setDescripcion(rs.getString("descripcion"));

        return evento;
    }
}
