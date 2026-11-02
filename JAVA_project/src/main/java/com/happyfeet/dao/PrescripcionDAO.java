
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Prescripcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescripcionDAO {

    // Registrar prescripción
    public boolean registrar(Prescripcion prescripcion) {

        if (!validarPrescripcion(prescripcion)) {
            return false;
        }

        String sql = "INSERT INTO prescripciones "
                + "(consulta_id, procedimiento_id, producto_id, cantidad, "
                + "dosis, frecuencia, duracion_dias, instrucciones) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            llenarParametros(ps, prescripcion);

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        prescripcion.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar prescripción: " + e.getMessage());
        }

        return false;
    }

    // Buscar prescripción por ID
    public Prescripcion buscarPorId(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return null;
        }

        String sql = "SELECT * FROM prescripciones WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar prescripción: " + e.getMessage());
        }

        return null;
    }

    // Listar todas las prescripciones
    public List<Prescripcion> listarTodos() {

        List<Prescripcion> prescripciones = new ArrayList<>();

        String sql = "SELECT * FROM prescripciones ORDER BY id DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                prescripciones.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar prescripciones: " + e.getMessage());
        }

        return prescripciones;
    }

    // Actualizar prescripción
    public boolean actualizar(Prescripcion prescripcion) {

        if (!validarPrescripcion(prescripcion) || prescripcion.getId() <= 0) {
            return false;
        }

        String sql = "UPDATE prescripciones SET consulta_id = ?, "
                + "procedimiento_id = ?, producto_id = ?, cantidad = ?, "
                + "dosis = ?, frecuencia = ?, duracion_dias = ?, "
                + "instrucciones = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            llenarParametros(ps, prescripcion);
            ps.setInt(9, prescripcion.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar prescripción: " + e.getMessage());
        }

        return false;
    }

    // Eliminar prescripción
    public boolean eliminar(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return false;
        }

        String sql = "DELETE FROM prescripciones WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar prescripción: " + e.getMessage());
        }

        return false;
    }

    // Validar datos de la prescripción
    private boolean validarPrescripcion(Prescripcion p) {

        if (p == null
                || p.getProductoId() <= 0
                || p.getCantidad() <= 0
                || (p.getConsultaId() == null && p.getProcedimientoId() == null)
                || (p.getDuracionDias() != null && p.getDuracionDias() <= 0)) {

            System.out.println("Error: verifica los datos de la prescripción.");
            return false;
        }

        return true;
    }

    // Asignar parámetros
    private void llenarParametros(PreparedStatement ps, Prescripcion p)
            throws SQLException {

        ps.setObject(1, p.getConsultaId());
        ps.setObject(2, p.getProcedimientoId());
        ps.setInt(3, p.getProductoId());
        ps.setInt(4, p.getCantidad());
        ps.setString(5, p.getDosis());
        ps.setString(6, p.getFrecuencia());
        ps.setObject(7, p.getDuracionDias());
        ps.setString(8, p.getInstrucciones());
    }

    // Convertir resultado de MySQL a objeto Prescripcion
    private Prescripcion convertirResultado(ResultSet rs) throws SQLException {

        Prescripcion p = new Prescripcion();

        p.setId(rs.getInt("id"));
        p.setConsultaId((Integer) rs.getObject("consulta_id"));
        p.setProcedimientoId((Integer) rs.getObject("procedimiento_id"));
        p.setProductoId(rs.getInt("producto_id"));
        p.setCantidad(rs.getInt("cantidad"));
        p.setDosis(rs.getString("dosis"));
        p.setFrecuencia(rs.getString("frecuencia"));

        Object duracion = rs.getObject("duracion_dias");
        p.setDuracionDias(duracion != null ? ((Number) duracion).intValue() : null);

        p.setInstrucciones(rs.getString("instrucciones"));

        Timestamp fecha = rs.getTimestamp("fecha_prescripcion");
        p.setFechaPrescripcion(
                fecha != null ? fecha.toLocalDateTime() : null
        );

        return p;
    }
}
