
package com.happyfeet.dao;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    // Registrar una factura
    public boolean registrar(Factura factura) {

        validarFactura(factura);

        String sql = "INSERT INTO facturas "
                + "(dueno_id, numero_factura, subtotal, impuesto, "
                + "descuento, total, metodo_pago, estado, observaciones) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            llenarParametros(ps, factura);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        factura.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar factura: " + e.getMessage());
        }

        return false;
    }

    // Buscar una factura por ID
    public Factura buscarPorId(int id) {

        String sql = "SELECT * FROM facturas WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultado(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar factura: " + e.getMessage());
        }

        return null;
    }

    // Listar todas las facturas
    public List<Factura> listarTodos() {

        List<Factura> facturas = new ArrayList<>();

        String sql = "SELECT * FROM facturas ORDER BY id";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                facturas.add(convertirResultado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar facturas: " + e.getMessage());
        }

        return facturas;
    }

    // Actualizar una factura
    public boolean actualizar(Factura factura) {

        validarFactura(factura);

        if (factura.getId() <= 0) {
            throw new IllegalArgumentException("El ID de la factura no es válido.");
        }

        String sql = "UPDATE facturas SET dueno_id = ?, "
                + "numero_factura = ?, subtotal = ?, impuesto = ?, "
                + "descuento = ?, total = ?, metodo_pago = ?, "
                + "estado = ?, observaciones = ? WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            llenarParametros(ps, factura);
            ps.setInt(10, factura.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar factura: " + e.getMessage());
        }

        return false;
    }

    // Eliminar una factura
    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser positivo.");
        }

        String sql = "DELETE FROM facturas WHERE id = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar factura: " + e.getMessage());
        }

        return false;
    }

    // Validar los datos de la factura
    private void validarFactura(Factura factura) {

        if (factura == null) {
            throw new IllegalArgumentException("La factura no puede ser nula.");
        }

        if (factura.getDuenoId() <= 0) {
            throw new IllegalArgumentException("El ID del dueño debe ser positivo.");
        }

        if (factura.getNumeroFactura() == null
                || factura.getNumeroFactura().isBlank()) {
            throw new IllegalArgumentException("El número de factura es obligatorio.");
        }

        if (factura.getSubtotal() == null
                || factura.getImpuesto() == null
                || factura.getDescuento() == null
                || factura.getTotal() == null) {
            throw new IllegalArgumentException("Los valores monetarios son obligatorios.");
        }

        if (factura.getSubtotal().signum() < 0
                || factura.getImpuesto().signum() < 0
                || factura.getDescuento().signum() < 0
                || factura.getTotal().signum() < 0) {
            throw new IllegalArgumentException("Los valores monetarios no pueden ser negativos.");
        }

        if (factura.getTotal().compareTo(
                factura.getSubtotal()
                        .add(factura.getImpuesto())
                        .subtract(factura.getDescuento())) != 0) {
            throw new IllegalArgumentException("El total no coincide con subtotal + impuesto - descuento.");
        }

        if (factura.getMetodoPago() != null
                && !factura.getMetodoPago().equals("Efectivo")
                && !factura.getMetodoPago().equals("Tarjeta")
                && !factura.getMetodoPago().equals("Transferencia")
                && !factura.getMetodoPago().equals("Mixto")) {
            throw new IllegalArgumentException("El método de pago no es válido.");
        }

        if (factura.getEstado() != null
                && !factura.getEstado().equals("Pendiente")
                && !factura.getEstado().equals("Pagada")
                && !factura.getEstado().equals("Anulada")) {
            throw new IllegalArgumentException("El estado de la factura no es válido.");
        }
    }

    // Colocar los parámetros en el PreparedStatement
    private void llenarParametros(PreparedStatement ps, Factura factura)
            throws SQLException {

        ps.setInt(1, factura.getDuenoId());
        ps.setString(2, factura.getNumeroFactura());
        ps.setBigDecimal(3, factura.getSubtotal());
        ps.setBigDecimal(4, factura.getImpuesto());
        ps.setBigDecimal(5, factura.getDescuento());
        ps.setBigDecimal(6, factura.getTotal());

        if (factura.getMetodoPago() == null) {
            ps.setNull(7, Types.VARCHAR);
        } else {
            ps.setString(7, factura.getMetodoPago());
        }

        ps.setString(8, factura.getEstado());
        ps.setString(9, factura.getObservaciones());
    }

    // Convertir el resultado de MySQL a un objeto Factura
    private Factura convertirResultado(ResultSet rs) throws SQLException {

        Factura factura = new Factura();

        factura.setId(rs.getInt("id"));
        factura.setDuenoId(rs.getInt("dueno_id"));
        factura.setNumeroFactura(rs.getString("numero_factura"));

        Timestamp fecha = rs.getTimestamp("fecha_emision");

        if (fecha != null) {
            factura.setFechaEmision(fecha.toLocalDateTime());
        }

        factura.setSubtotal(rs.getBigDecimal("subtotal"));
        factura.setImpuesto(rs.getBigDecimal("impuesto"));
        factura.setDescuento(rs.getBigDecimal("descuento"));
        factura.setTotal(rs.getBigDecimal("total"));
        factura.setMetodoPago(rs.getString("metodo_pago"));
        factura.setEstado(rs.getString("estado"));
        factura.setObservaciones(rs.getString("observaciones"));

        return factura;
    }
}
