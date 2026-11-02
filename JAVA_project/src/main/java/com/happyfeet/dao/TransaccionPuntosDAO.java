
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.TransaccionPuntos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionPuntosDAO {

    public boolean registrar(TransaccionPuntos transaccion) {
        String sql = "INSERT INTO transacciones_puntos "
                + "(club_mascotas_id, factura_id, puntos, tipo, fecha, "
                + "descripcion, saldo_anterior, saldo_nuevo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, transaccion.getClubMascotasId());

            if (transaccion.getFacturaId() != null) {
                ps.setInt(2, transaccion.getFacturaId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setInt(3, transaccion.getPuntos());
            ps.setString(4, transaccion.getTipo());
            ps.setTimestamp(5, Timestamp.valueOf(transaccion.getFecha()));
            ps.setString(6, transaccion.getDescripcion());
            ps.setInt(7, transaccion.getSaldoAnterior());
            ps.setInt(8, transaccion.getSaldoNuevo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar transaccion: " + e.getMessage());
            return false;
        }
    }

    public TransaccionPuntos buscarPorId(int id) {
        String sql = "SELECT * FROM transacciones_puntos WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar transaccion: " + e.getMessage());
        }

        return null;
    }

    public List<TransaccionPuntos> listarTodos() {
        List<TransaccionPuntos> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacciones_puntos";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar transacciones: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(TransaccionPuntos transaccion) {
        String sql = "UPDATE transacciones_puntos SET "
                + "club_mascotas_id = ?, factura_id = ?, puntos = ?, "
                + "tipo = ?, fecha = ?, descripcion = ?, "
                + "saldo_anterior = ?, saldo_nuevo = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, transaccion.getClubMascotasId());

            if (transaccion.getFacturaId() != null) {
                ps.setInt(2, transaccion.getFacturaId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setInt(3, transaccion.getPuntos());
            ps.setString(4, transaccion.getTipo());
            ps.setTimestamp(5, Timestamp.valueOf(transaccion.getFecha()));
            ps.setString(6, transaccion.getDescripcion());
            ps.setInt(7, transaccion.getSaldoAnterior());
            ps.setInt(8, transaccion.getSaldoNuevo());
            ps.setInt(9, transaccion.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar transaccion: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM transacciones_puntos WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar transaccion: " + e.getMessage());
            return false;
        }
    }

    private TransaccionPuntos convertirResultado(ResultSet rs) throws SQLException {
        TransaccionPuntos transaccion = new TransaccionPuntos();

        transaccion.setId(rs.getInt("id"));
        transaccion.setClubMascotasId(rs.getInt("club_mascotas_id"));

        int facturaId = rs.getInt("factura_id");
        transaccion.setFacturaId(rs.wasNull() ? null : facturaId);

        transaccion.setPuntos(rs.getInt("puntos"));
        transaccion.setTipo(rs.getString("tipo"));

        Timestamp fecha = rs.getTimestamp("fecha");
        transaccion.setFecha(fecha != null ? fecha.toLocalDateTime() : null);

        transaccion.setDescripcion(rs.getString("descripcion"));
        transaccion.setSaldoAnterior(rs.getInt("saldo_anterior"));
        transaccion.setSaldoNuevo(rs.getInt("saldo_nuevo"));

        return transaccion;
    }
}
