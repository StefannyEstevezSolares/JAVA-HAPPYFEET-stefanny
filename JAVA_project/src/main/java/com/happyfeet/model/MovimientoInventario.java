
package com.happyfeet.model;

import java.time.LocalDateTime;

public class MovimientoInventario {

    private int id;
    private int productoId;
    private String tipoMovimiento;
    private int cantidad;
    private int stockAnterior;
    private int stockNuevo;
    private String motivo;
    private Integer referenciaConsultaId;
    private Integer referenciaProcedimientoId;
    private String usuario;
    private LocalDateTime fechaMovimiento;

    // Constructor vacío
    public MovimientoInventario() {
    }

    // Constructor completo
    public MovimientoInventario(int id, int productoId,
            String tipoMovimiento, int cantidad, int stockAnterior,
            int stockNuevo, String motivo, Integer referenciaConsultaId,
            Integer referenciaProcedimientoId, String usuario,
            LocalDateTime fechaMovimiento) {

        this.id = id;
        this.productoId = productoId;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.stockAnterior = stockAnterior;
        this.stockNuevo = stockNuevo;
        this.motivo = motivo;
        this.referenciaConsultaId = referenciaConsultaId;
        this.referenciaProcedimientoId = referenciaProcedimientoId;
        this.usuario = usuario;
        this.fechaMovimiento = fechaMovimiento;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStockAnterior() {
        return stockAnterior;
    }

    public void setStockAnterior(int stockAnterior) {
        this.stockAnterior = stockAnterior;
    }

    public int getStockNuevo() {
        return stockNuevo;
    }

    public void setStockNuevo(int stockNuevo) {
        this.stockNuevo = stockNuevo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getReferenciaConsultaId() {
        return referenciaConsultaId;
    }

    public void setReferenciaConsultaId(Integer referenciaConsultaId) {
        this.referenciaConsultaId = referenciaConsultaId;
    }

    public Integer getReferenciaProcedimientoId() {
        return referenciaProcedimientoId;
    }

    public void setReferenciaProcedimientoId(Integer referenciaProcedimientoId) {
        this.referenciaProcedimientoId = referenciaProcedimientoId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
}
