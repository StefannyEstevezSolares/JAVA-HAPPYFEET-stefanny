
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.BeneficioClub;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeneficioClubDAO {

    // Registrar beneficio
    public boolean registrar(BeneficioClub beneficio) {

        String sql = "INSERT INTO beneficios_club "
                + "(nombre, descripcion, nivel_requerido, puntos_necesarios, "
                + "tipo_beneficio, valor_beneficio, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, beneficio.getNombre());
            ps.setString(2, beneficio.getDescripcion());
            ps.setString(3, beneficio.getNivelRequerido());
            ps.setInt(4, beneficio.getPuntosNecesarios());
            ps.setString(5, beneficio.getTipoBeneficio());
            ps.setBigDecimal(6, beneficio.getValorBeneficio());
            ps.setObject(7, beneficio.getActivo());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        beneficio.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar beneficio: " + e.getMessage());
        }

        return false;
    }

    // Buscar beneficio por ID
    public BeneficioClub buscarPorId(int id) {

        String sql = "SELECT * FROM beneficios_club WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar beneficio: " + e.getMessage());
        }

        return null;
    }

    // Listar beneficios
    public List<BeneficioClub> listarTodos() {

        List<BeneficioClub> lista = new ArrayList<>();
        String sql = "SELECT * FROM beneficios_club";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar beneficios: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar beneficio
    public boolean actualizar(BeneficioClub beneficio) {

        String sql = "UPDATE beneficios_club SET nombre = ?, "
                + "descripcion = ?, nivel_requerido = ?, "
                + "puntos_necesarios = ?, tipo_beneficio = ?, "
                + "valor_beneficio = ?, activo = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, beneficio.getNombre());
            ps.setString(2, beneficio.getDescripcion());
            ps.setString(3, beneficio.getNivelRequerido());
            ps.setInt(4, beneficio.getPuntosNecesarios());
            ps.setString(5, beneficio.getTipoBeneficio());
            ps.setBigDecimal(6, beneficio.getValorBeneficio());
            ps.setObject(7, beneficio.getActivo());
            ps.setInt(8, beneficio.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar beneficio: " + e.getMessage());
        }

        return false;
    }

    // Eliminar beneficio
    public boolean eliminar(int id) {

        String sql = "DELETE FROM beneficios_club WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar beneficio: " + e.getMessage());
        }

        return false;
    }

    // Convertir resultado en objeto BeneficioClub
    private BeneficioClub convertirResultado(ResultSet rs) throws SQLException {

        BeneficioClub beneficio = new BeneficioClub();

        beneficio.setId(rs.getInt("id"));
        beneficio.setNombre(rs.getString("nombre"));
        beneficio.setDescripcion(rs.getString("descripcion"));
        beneficio.setNivelRequerido(rs.getString("nivel_requerido"));
        beneficio.setPuntosNecesarios(rs.getInt("puntos_necesarios"));
        beneficio.setTipoBeneficio(rs.getString("tipo_beneficio"));
        beneficio.setValorBeneficio(rs.getBigDecimal("valor_beneficio"));
        beneficio.setActivo((Boolean) rs.getObject("activo"));

        return beneficio;
    }
}
