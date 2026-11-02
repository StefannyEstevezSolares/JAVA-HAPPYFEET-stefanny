
package com.happyfeet.test;

import com.happyfeet.dao.MovimientoInventarioDAO;
import com.happyfeet.model.MovimientoInventario;

public class PruebaMovimientoInventarioDAO {

    public static void main(String[] args) {

        MovimientoInventarioDAO dao = new MovimientoInventarioDAO();

        MovimientoInventario movimiento = new MovimientoInventario();

        movimiento.setProductoId(1);
        movimiento.setTipoMovimiento("Entrada");
        movimiento.setCantidad(5);
        movimiento.setStockAnterior(10);
        movimiento.setStockNuevo(15);
        movimiento.setMotivo("Prueba de registro");
        movimiento.setReferenciaConsultaId(null);
        movimiento.setReferenciaProcedimientoId(null);
        movimiento.setUsuario("Prueba");

        boolean registrado = dao.registrar(movimiento);

        if (registrado) {
            System.out.println("Movimiento registrado correctamente.");

            System.out.println("\nMovimientos registrados:");

            for (MovimientoInventario m : dao.listarTodos()) {
                System.out.println(
                    "ID: " + m.getId()
                    + " | Producto: " + m.getProductoId()
                    + " | Tipo: " + m.getTipoMovimiento()
                    + " | Cantidad: " + m.getCantidad()
                    + " | Stock anterior: " + m.getStockAnterior()
                    + " | Stock nuevo: " + m.getStockNuevo()
                );
            }

        } else {
            System.out.println("No se pudo registrar el movimiento.");
        }
    }
}
