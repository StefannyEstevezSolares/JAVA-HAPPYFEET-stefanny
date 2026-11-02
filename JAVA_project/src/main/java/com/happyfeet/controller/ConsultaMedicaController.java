
package com.happyfeet.controller;

import com.happyfeet.model.ConsultaMedica;
import com.happyfeet.service.ConsultaMedicaService;
import java.sql.SQLException;
import java.util.List;

public class ConsultaMedicaController {

    private final ConsultaMedicaService consultaService;

    public ConsultaMedicaController() {
        this.consultaService = new ConsultaMedicaService();
    }

    public int registrar(ConsultaMedica consulta) throws SQLException {
        return consultaService.registrar(consulta);
    }

    public ConsultaMedica buscarPorId(int id) throws SQLException {
        return consultaService.buscarPorId(id);
    }

    public List<ConsultaMedica> listarTodos() throws SQLException {
        return consultaService.listarTodos();
    }

    public boolean actualizar(ConsultaMedica consulta) throws SQLException {
        return consultaService.actualizar(consulta);
    }

    public boolean eliminar(int id) throws SQLException {
        return consultaService.eliminar(id);
    }
}
