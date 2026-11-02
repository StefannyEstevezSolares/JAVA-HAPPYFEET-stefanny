
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.CanjeBeneficio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CanjeBeneficioDAO {

    // Registrar canje
    public boolean registrar(CanjeBeneficio canje) {

        String sql = "INSERT INTO canjes_beneficios "
                + "(club_mascotas_id, beneficio_id, fecha_canje, "
                + "puntos_canjeados, estado, fecha_expiracion, factura_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, canje.getClubMascotasId());
            ps.setInt(2, canje.getBeneficioId());
            ps.setTimestamp(3, Timestamp.valueOf(canje.getFechaCanje()));
            ps.setInt(4, canje.getPuntosCanjeados());
            ps.setString(5, canje.getEstado());

            if (canje.getFechaExpiracion() != null) {
                ps.setDate(6, Date.valueOf(canje.getFechaExpiracion()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            if (canje.getFacturaId() != null) {
                ps.setInt(7, canje.getFacturaId());
            } else {
                ps.setNull(7, Types.INTEGER);
            }

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        canje.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar canje: " + e.getMessage());
        }

        return false;
    }

    // Buscar canje por ID
    public CanjeBeneficio buscarPorId(int id) {

        String sql = "SELECT * FROM canjes_beneficios WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar canje: " + e.getMessage());
        }

        return null;
    }

    // Listar todos los canjes
    public List<CanjeBeneficio> listarTodos() {

        List<CanjeBeneficio> lista = new ArrayList<>();
        String sql = "SELECT * FROM canjes_beneficios";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar canjes: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar canje
    public boolean actualizar(CanjeBeneficio canje) {

        String sql = "UPDATE canjes_beneficios SET "
                + "club_mascotas_id = ?, beneficio_id = ?, "
                + "fecha_canje = ?, puntos_canjeados = ?, "
                + "estado = ?, fecha_expiracion = ?, factura_id = ? "
                + "WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, canje.getClubMascotasId());
            ps.setInt(2, canje.getBeneficioId());
            ps.setTimestamp(3, Timestamp.valueOf(canje.getFechaCanje()));
            ps.setInt(4, canje.getPuntosCanjeados());
            ps.setString(5, canje.getEstado());

            if (canje.getFechaExpiracion() != null) {
                ps.setDate(6, Date.valueOf(canje.getFechaExpiracion()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            if (canje.getFacturaId() != null) {
                ps.setInt(7, canje.getFacturaId());
            } else {
                ps.setNull(7, Types.INTEGER);
            }

            ps.setInt(8, canje.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar canje: " + e.getMessage());
        }

        return false;
    }

    // Eliminar canje
    public boolean eliminar(int id) {

        String sql = "DELETE FROM canjes_beneficios WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar canje: " + e.getMessage());
        }

        return false;
    }

    // Convertir resultado en objeto CanjeBeneficio
    private CanjeBeneficio convertirResultado(ResultSet rs) throws SQLException {

        CanjeBeneficio canje = new CanjeBeneficio();

        canje.setId(rs.getInt("id"));
        canje.setClubMascotasId(rs.getInt("club_mascotas_id"));
        canje.setBeneficioId(rs.getInt("beneficio_id"));
        canje.setFechaCanje(
                rs.getTimestamp("fecha_canje").toLocalDateTime());
        canje.setPuntosCanjeados(rs.getInt("puntos_canjeados"));
        canje.setEstado(rs.getString("estado"));

        Date fecha = rs.getDate("fecha_expiracion");

        if (fecha != null) {
            canje.setFechaExpiracion(fecha.toLocalDate());
        }

        Integer facturaId = (Integer) rs.getObject("factura_id");
        canje.setFacturaId(facturaId);

        return canje;
    }
}
