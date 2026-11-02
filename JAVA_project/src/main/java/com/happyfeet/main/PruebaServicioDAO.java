
package com.happyfeet.main;

import com.happyfeet.dao.ServicioDAO;
import com.happyfeet.model.Servicio;
import java.math.BigDecimal;

public class PruebaServicioDAO {

    public static void main(String[] args) {

        ServicioDAO dao = new ServicioDAO();

        // Crear un servicio de prueba
        Servicio servicio = new Servicio();

        servicio.setNombre("Servicio de prueba");
        servicio.setDescripcion("Servicio para probar el DAO");
        servicio.setCategoria("Consulta");
        servicio.setPrecioBase(new BigDecimal("100.00"));
        servicio.setDuracionEstimadaMinutos(30);
        servicio.setActivo(true);

        // Registrar servicio
        if (dao.registrar(servicio)) {

            System.out.println("Servicio registrado correctamente.");
            System.out.println("ID generado: " + servicio.getId());

            // Buscar servicio
            Servicio encontrado = dao.buscarPorId(servicio.getId());

            if (encontrado != null) {
                System.out.println("Servicio encontrado: "
                        + encontrado.getNombre());
                System.out.println("Precio: "
                        + encontrado.getPrecioBase());
            }

            // Actualizar servicio
            servicio.setNombre("Servicio actualizado");
            servicio.setPrecioBase(new BigDecimal("125.00"));

            if (dao.actualizar(servicio)) {
                System.out.println("Servicio actualizado correctamente.");
            }

            // Listar servicios
            System.out.println("Total de servicios: "
                    + dao.listarTodos().size());

            // Eliminar servicio de prueba
            if (dao.eliminar(servicio.getId())) {
                System.out.println("Servicio eliminado correctamente.");
            }

        } else {
            System.out.println("No se pudo registrar el servicio.");
        }
    }
}
