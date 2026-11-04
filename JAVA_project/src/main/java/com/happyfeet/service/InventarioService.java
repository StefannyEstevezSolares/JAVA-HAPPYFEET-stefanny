
package com.happyfeet.service;

import com.happyfeet.dao.InventarioDAO;
import com.happyfeet.model.Inventario;
import com.happyfeet.notificaciones.AlertaInventario;
import com.happyfeet.notificaciones.NotificadorInventario;
import java.time.LocalDate;
import java.util.List;

public class InventarioService {

    private final InventarioDAO inventarioDAO;
    private final NotificadorInventario notificador;

    public InventarioService() {
        this.inventarioDAO = new InventarioDAO();
        this.notificador = new NotificadorInventario();

        this.notificador.agregarObservador(new AlertaInventario());
    }

    public boolean registrar(Inventario producto) {

        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede estar vacío.");
        }

        boolean registrado = inventarioDAO.registrar(producto);

        if (registrado) {
            verificarAlertas(producto);
        }

        return registrado;
    }

    public Inventario buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser válido.");
        }

        return inventarioDAO.buscarPorId(id);
    }

    public List<Inventario> listarTodos() {
        return inventarioDAO.listarTodos();
    }

    public boolean actualizar(Inventario producto) {

        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede estar vacío.");
        }

        boolean actualizado = inventarioDAO.actualizar(producto);

        if (actualizado) {
            verificarAlertas(producto);
        }

        return actualizado;
    }

    public boolean eliminar(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser válido.");
        }

        return inventarioDAO.eliminar(id);
    }

    private void verificarAlertas(Inventario producto) {

        if (!Boolean.TRUE.equals(producto.getActivo())) {
            return;
        }

        if (producto.getCantidadStock() < producto.getStockMinimo()) {

            String mensaje = "Stock bajo: " + producto.getNombreProducto()
                    + ". Existencias actuales: " + producto.getCantidadStock()
                    + ". Stock mínimo: " + producto.getStockMinimo() + ".";

            notificador.notificar(
                    mensaje,
                    producto.getId(),
                    "STOCK_BAJO"
            );
        }

        LocalDate fechaVencimiento = producto.getFechaVencimiento();
        LocalDate hoy = LocalDate.now();

        if (fechaVencimiento != null
                && !fechaVencimiento.isBefore(hoy)
                && !fechaVencimiento.isAfter(hoy.plusDays(30))) {

            String mensaje = "Producto próximo a vencer: "
                    + producto.getNombreProducto()
                    + ". Fecha de vencimiento: " + fechaVencimiento + ".";

            notificador.notificar(
                    mensaje,
                    producto.getId(),
                    "PROXIMO_VENCIMIENTO"
            );
        }
    }
}
