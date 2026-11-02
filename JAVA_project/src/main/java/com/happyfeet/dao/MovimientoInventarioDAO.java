
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.MovimientoInventario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoInventarioDAO {

    // Registrar movimiento
    public boolean registrar(MovimientoInventario movimiento) {

        if (!validarMovimiento(movimiento)) {
            return false;
        }

        String sql = "INSERT INTO movimientos_inventario "
                + "(producto_id, tipo_movimiento, cantidad, stock_anterior, "
                + "stock_nuevo, motivo, referencia_consulta_id, "
                + "referencia_procedimiento_id, usuario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            llenarParametros(ps, movimiento);

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        movimiento.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar movimiento: " + e.getMessage());
        }

        return false;
    }

    // Buscar por ID
    public MovimientoInventario buscarPorId(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return null;
        }

        String sql = "SELECT * FROM movimientos_inventario WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar movimiento: " + e.getMessage());
        }

        return null;
    }

    // Listar todos
    public List<MovimientoInventario> listarTodos() {

        List<MovimientoInventario> movimientos = new ArrayList<>();

        String sql = "SELECT * FROM movimientos_inventario ORDER BY id DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                movimientos.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar movimientos: " + e.getMessage());
        }

        return movimientos;
    }

    // Actualizar
    public boolean actualizar(MovimientoInventario movimiento) {

        if (!validarMovimiento(movimiento) || movimiento.getId() <= 0) {
            return false;
        }

        String sql = "UPDATE movimientos_inventario SET "
                + "producto_id = ?, tipo_movimiento = ?, cantidad = ?, "
                + "stock_anterior = ?, stock_nuevo = ?, motivo = ?, "
                + "referencia_consulta_id = ?, referencia_procedimiento_id = ?, "
                + "usuario = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            llenarParametros(ps, movimiento);
            ps.setInt(10, movimiento.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar movimiento: " + e.getMessage());
        }

        return false;
    }

    // Eliminar
    public boolean eliminar(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return false;
        }

        String sql = "DELETE FROM movimientos_inventario WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar movimiento: " + e.getMessage());
        }

        return false;
    }

    // Validar movimiento
    private boolean validarMovimiento(MovimientoInventario m) {

        if (m == null
                || m.getProductoId() <= 0
                || m.getCantidad() <= 0
                || m.getStockAnterior() < 0
                || m.getStockNuevo() < 0
                || m.getTipoMovimiento() == null
                || m.getTipoMovimiento().trim().isEmpty()) {

            System.out.println("Error: verifica los datos del movimiento.");
            return false;
        }

        return true;
    }

    // Asignar parámetros
    private void llenarParametros(PreparedStatement ps,
            MovimientoInventario m) throws SQLException {

        ps.setInt(1, m.getProductoId());
        ps.setString(2, m.getTipoMovimiento());
        ps.setInt(3, m.getCantidad());
        ps.setInt(4, m.getStockAnterior());
        ps.setInt(5, m.getStockNuevo());
        ps.setString(6, m.getMotivo());
        ps.setObject(7, m.getReferenciaConsultaId());
        ps.setObject(8, m.getReferenciaProcedimientoId());
        ps.setString(9, m.getUsuario());
    }

    // Convertir resultado
    private MovimientoInventario convertirResultado(ResultSet rs)
            throws SQLException {

        MovimientoInventario m = new MovimientoInventario();

        m.setId(rs.getInt("id"));
        m.setProductoId(rs.getInt("producto_id"));
        m.setTipoMovimiento(rs.getString("tipo_movimiento"));
        m.setCantidad(rs.getInt("cantidad"));
        m.setStockAnterior(rs.getInt("stock_anterior"));
        m.setStockNuevo(rs.getInt("stock_nuevo"));
        m.setMotivo(rs.getString("motivo"));
        m.setReferenciaConsultaId(
                (Integer) rs.getObject("referencia_consulta_id"));
        m.setReferenciaProcedimientoId(
                (Integer) rs.getObject("referencia_procedimiento_id"));
        m.setUsuario(rs.getString("usuario"));

        Timestamp fecha = rs.getTimestamp("fecha_movimiento");
        m.setFechaMovimiento(
                fecha != null ? fecha.toLocalDateTime() : null
        );

        return m;
    }
}
