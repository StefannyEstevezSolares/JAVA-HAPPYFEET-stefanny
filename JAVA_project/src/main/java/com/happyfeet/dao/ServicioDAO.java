
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Servicio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    // Registrar servicio
    public boolean registrar(Servicio servicio) {

        String sql = "INSERT INTO servicios "
                + "(nombre, descripcion, categoria, precio_base, "
                + "duracion_estimada_minutos, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setString(3, servicio.getCategoria());
            ps.setBigDecimal(4, servicio.getPrecioBase());
            ps.setObject(5, servicio.getDuracionEstimadaMinutos());
            ps.setObject(6, servicio.getActivo());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        servicio.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar servicio: " + e.getMessage());
        }

        return false;
    }

    // Buscar servicio por ID
    public Servicio buscarPorId(int id) {

        String sql = "SELECT * FROM servicios WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar servicio: " + e.getMessage());
        }

        return null;
    }

    // Listar todos los servicios
    public List<Servicio> listarTodos() {

        List<Servicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar servicios: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar servicio
    public boolean actualizar(Servicio servicio) {

        String sql = "UPDATE servicios SET nombre = ?, descripcion = ?, "
                + "categoria = ?, precio_base = ?, "
                + "duracion_estimada_minutos = ?, activo = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setString(3, servicio.getCategoria());
            ps.setBigDecimal(4, servicio.getPrecioBase());
            ps.setObject(5, servicio.getDuracionEstimadaMinutos());
            ps.setObject(6, servicio.getActivo());
            ps.setInt(7, servicio.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar servicio: " + e.getMessage());
        }

        return false;
    }

    // Eliminar servicio
    public boolean eliminar(int id) {

        String sql = "DELETE FROM servicios WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar servicio: " + e.getMessage());
        }

        return false;
    }

    // Convertir resultado en objeto Servicio
    private Servicio convertirResultado(ResultSet rs) throws SQLException {

        Servicio servicio = new Servicio();

        servicio.setId(rs.getInt("id"));
        servicio.setNombre(rs.getString("nombre"));
        servicio.setDescripcion(rs.getString("descripcion"));
        servicio.setCategoria(rs.getString("categoria"));
        servicio.setPrecioBase(rs.getBigDecimal("precio_base"));

        servicio.setDuracionEstimadaMinutos(
                (Integer) rs.getObject("duracion_estimada_minutos"));

        servicio.setActivo((Boolean) rs.getObject("activo"));

        return servicio;
    }
}
