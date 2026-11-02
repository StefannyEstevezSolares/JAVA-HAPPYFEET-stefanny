
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.InsumoProcedimiento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsumoProcedimientoDAO {

    // Registrar insumo
    public boolean registrar(InsumoProcedimiento insumo) {

        if (!validarInsumo(insumo)) {
            return false;
        }

        String sql = "INSERT INTO insumos_procedimientos "
                + "(procedimiento_id, producto_id, cantidad_usada, observaciones) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            llenarParametros(ps, insumo);

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        insumo.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar insumo: " + e.getMessage());
        }

        return false;
    }

    // Buscar insumo por ID
    public InsumoProcedimiento buscarPorId(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return null;
        }

        String sql = "SELECT * FROM insumos_procedimientos WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar insumo: " + e.getMessage());
        }

        return null;
    }

    // Listar todos los insumos
    public List<InsumoProcedimiento> listarTodos() {

        List<InsumoProcedimiento> insumos = new ArrayList<>();

        String sql = "SELECT * FROM insumos_procedimientos ORDER BY id DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                insumos.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar insumos: " + e.getMessage());
        }

        return insumos;
    }

    // Actualizar insumo
    public boolean actualizar(InsumoProcedimiento insumo) {

        if (!validarInsumo(insumo) || insumo.getId() <= 0) {
            return false;
        }

        String sql = "UPDATE insumos_procedimientos SET "
                + "procedimiento_id = ?, producto_id = ?, "
                + "cantidad_usada = ?, observaciones = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            llenarParametros(ps, insumo);
            ps.setInt(5, insumo.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar insumo: " + e.getMessage());
        }

        return false;
    }

    // Eliminar insumo
    public boolean eliminar(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return false;
        }

        String sql = "DELETE FROM insumos_procedimientos WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar insumo: " + e.getMessage());
        }

        return false;
    }

    // Validar datos
    private boolean validarInsumo(InsumoProcedimiento insumo) {

        if (insumo == null
                || insumo.getProcedimientoId() <= 0
                || insumo.getProductoId() <= 0
                || insumo.getCantidadUsada() <= 0) {

            System.out.println("Error: verifica los datos del insumo.");
            return false;
        }

        return true;
    }

    // Asignar parámetros
    private void llenarParametros(PreparedStatement ps,
            InsumoProcedimiento insumo) throws SQLException {

        ps.setInt(1, insumo.getProcedimientoId());
        ps.setInt(2, insumo.getProductoId());
        ps.setInt(3, insumo.getCantidadUsada());
        ps.setString(4, insumo.getObservaciones());
    }

    // Convertir resultado de MySQL a objeto
    private InsumoProcedimiento convertirResultado(ResultSet rs)
            throws SQLException {

        InsumoProcedimiento insumo = new InsumoProcedimiento();

        insumo.setId(rs.getInt("id"));
        insumo.setProcedimientoId(rs.getInt("procedimiento_id"));
        insumo.setProductoId(rs.getInt("producto_id"));
        insumo.setCantidadUsada(rs.getInt("cantidad_usada"));
        insumo.setObservaciones(rs.getString("observaciones"));

        return insumo;
    }
}
