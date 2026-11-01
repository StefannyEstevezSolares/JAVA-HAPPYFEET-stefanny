
package com.happyfeet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProcedimientoEspecial {

    private int id;
    private int mascotaId;
    private int veterinarioId;
    private String tipoProcedimiento;
    private String nombreProcedimiento;
    private LocalDateTime fechaHora;
    private Integer duracionEstimadaMinutos;
    private String informacionPreoperatoria;
    private String detalleProcedimiento;
    private String complicaciones;
    private String seguimientoPostoperatorio;
    private LocalDate proximoControl;
    private String estado;
    private BigDecimal costoProcedimiento;

    // Constructor vacio
    public ProcedimientoEspecial() {
    }

    // Constructor con parametros
    public ProcedimientoEspecial(int id, int mascotaId,
            int veterinarioId, String tipoProcedimiento,
            String nombreProcedimiento, LocalDateTime fechaHora,
            Integer duracionEstimadaMinutos,
            String informacionPreoperatoria,
            String detalleProcedimiento, String complicaciones,
            String seguimientoPostoperatorio, LocalDate proximoControl,
            String estado, BigDecimal costoProcedimiento) {

        this.id = id;
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.tipoProcedimiento = tipoProcedimiento;
        this.nombreProcedimiento = nombreProcedimiento;
        this.fechaHora = fechaHora;
        this.duracionEstimadaMinutos = duracionEstimadaMinutos;
        this.informacionPreoperatoria = informacionPreoperatoria;
        this.detalleProcedimiento = detalleProcedimiento;
        this.complicaciones = complicaciones;
        this.seguimientoPostoperatorio = seguimientoPostoperatorio;
        this.proximoControl = proximoControl;
        this.estado = estado;
        this.costoProcedimiento = costoProcedimiento;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public int getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(int veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public String getTipoProcedimiento() {
        return tipoProcedimiento;
    }

    public void setTipoProcedimiento(String tipoProcedimiento) {
        this.tipoProcedimiento = tipoProcedimiento;
    }

    public String getNombreProcedimiento() {
        return nombreProcedimiento;
    }

    public void setNombreProcedimiento(String nombreProcedimiento) {
        this.nombreProcedimiento = nombreProcedimiento;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getDuracionEstimadaMinutos() {
        return duracionEstimadaMinutos;
    }

    public void setDuracionEstimadaMinutos(Integer duracionEstimadaMinutos) {
        this.duracionEstimadaMinutos = duracionEstimadaMinutos;
    }

    public String getInformacionPreoperatoria() {
        return informacionPreoperatoria;
    }

    public void setInformacionPreoperatoria(String informacionPreoperatoria) {
        this.informacionPreoperatoria = informacionPreoperatoria;
    }

    public String getDetalleProcedimiento() {
        return detalleProcedimiento;
    }

    public void setDetalleProcedimiento(String detalleProcedimiento) {
        this.detalleProcedimiento = detalleProcedimiento;
    }

    public String getComplicaciones() {
        return complicaciones;
    }

    public void setComplicaciones(String complicaciones) {
        this.complicaciones = complicaciones;
    }

    public String getSeguimientoPostoperatorio() {
        return seguimientoPostoperatorio;
    }

    public void setSeguimientoPostoperatorio(String seguimientoPostoperatorio) {
        this.seguimientoPostoperatorio = seguimientoPostoperatorio;
    }

    public LocalDate getProximoControl() {
        return proximoControl;
    }

    public void setProximoControl(LocalDate proximoControl) {
        this.proximoControl = proximoControl;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getCostoProcedimiento() {
        return costoProcedimiento;
    }

    public void setCostoProcedimiento(BigDecimal costoProcedimiento) {
        this.costoProcedimiento = costoProcedimiento;
    }
}

