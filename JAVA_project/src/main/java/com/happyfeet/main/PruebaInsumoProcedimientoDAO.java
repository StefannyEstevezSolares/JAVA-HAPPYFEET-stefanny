
package com.happyfeet.test;

import com.happyfeet.dao.InsumoProcedimientoDAO;
import com.happyfeet.model.InsumoProcedimiento;
import java.util.List;

public class PruebaInsumoProcedimientoDAO {

    public static void main(String[] args) {

        InsumoProcedimientoDAO dao = new InsumoProcedimientoDAO();

        // 1. Crear insumo de prueba
        InsumoProcedimiento insumo = new InsumoProcedimiento();

        insumo.setProcedimientoId(1);
        insumo.setProductoId(1);
        insumo.setCantidadUsada(2);
        insumo.setObservaciones("Insumo utilizado en procedimiento de prueba.");

        // 2. Registrar
        System.out.println("===== REGISTRAR INSUMO =====");

        if (dao.registrar(insumo)) {

            int id = insumo.getId();

            System.out.println("Insumo registrado correctamente.");
            System.out.println("ID generado: " + id);

            // 3. Buscar por ID
            System.out.println("\n===== BUSCAR INSUMO =====");

            InsumoProcedimiento encontrado = dao.buscarPorId(id);

            if (encontrado != null) {
                System.out.println("ID: " + encontrado.getId());
                System.out.println("Procedimiento: " + encontrado.getProcedimientoId());
                System.out.println("Producto: " + encontrado.getProductoId());
                System.out.println("Cantidad usada: " + encontrado.getCantidadUsada());
                System.out.println("Observaciones: " + encontrado.getObservaciones());
            }

            // 4. Actualizar
            System.out.println("\n===== ACTUALIZAR INSUMO =====");

            insumo.setCantidadUsada(3);
            insumo.setObservaciones("Cantidad actualizada durante la prueba.");

            if (dao.actualizar(insumo)) {
                System.out.println("Insumo actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar.");
            }

            // 5. Listar todos
            System.out.println("\n===== LISTAR INSUMOS =====");

            List<InsumoProcedimiento> lista = dao.listarTodos();

            for (InsumoProcedimiento i : lista) {
                System.out.println(
                        "ID: " + i.getId()
                        + " | Procedimiento: " + i.getProcedimientoId()
                        + " | Producto: " + i.getProductoId()
                        + " | Cantidad: " + i.getCantidadUsada()
                );
            }

            // 6. Eliminar
            System.out.println("\n===== ELIMINAR INSUMO =====");

            if (dao.eliminar(id)) {
                System.out.println("Insumo eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar.");
            }

        } else {
            System.out.println("No se pudo registrar el insumo.");
        }
    }
}
