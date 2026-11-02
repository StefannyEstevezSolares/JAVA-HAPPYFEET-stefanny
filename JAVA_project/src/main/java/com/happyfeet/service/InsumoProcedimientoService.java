
package com.happyfeet.service;

import com.happyfeet.dao.InsumoProcedimientoDAO;
import com.happyfeet.model.InsumoProcedimiento;
import java.util.List;

public class InsumoProcedimientoService {

    private final InsumoProcedimientoDAO insumoDAO;

    public InsumoProcedimientoService() {
        this.insumoDAO = new InsumoProcedimientoDAO();
    }

    public boolean registrar(InsumoProcedimiento insumo) {

        if (insumo == null) {
            throw new IllegalArgumentException(
                "El insumo no puede estar vacío."
            );
        }

        return insumoDAO.registrar(insumo);
    }

    public InsumoProcedimiento buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return insumoDAO.buscarPorId(id);
    }

    public List<InsumoProcedimiento> listarTodos() {
        return insumoDAO.listarTodos();
    }

    public boolean actualizar(InsumoProcedimiento insumo) {

        if (insumo == null) {
            throw new IllegalArgumentException(
                "El insumo no puede estar vacío."
            );
        }

        return insumoDAO.actualizar(insumo);
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser válido."
            );
        }

        return insumoDAO.eliminar(id);
    }
}
