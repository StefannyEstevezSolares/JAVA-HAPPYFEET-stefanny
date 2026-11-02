
package com.happyfeet.main;

import com.happyfeet.dao.EspecieDAO;
import com.happyfeet.model.Especie;

public class PruebaEspecieDAO {

    public static void main(String[] args) {

        EspecieDAO dao = new EspecieDAO();

        // Crear especie de prueba
        Especie especie = new Especie();

        especie.setNombre("Especie de prueba");
        especie.setDescripcion("Registro para probar el DAO");

        // Registrar especie
        if (dao.registrar(especie)) {

            System.out.println("Especie registrada correctamente.");
            System.out.println("ID generado: " + especie.getId());

            // Buscar especie
            Especie encontrada = dao.buscarPorId(especie.getId());

            if (encontrada != null) {
                System.out.println("Especie encontrada: "
                        + encontrada.getNombre());
            }

            // Actualizar especie
            especie.setNombre("Especie actualizada");
            especie.setDescripcion("Descripción actualizada");

            if (dao.actualizar(especie)) {
                System.out.println("Especie actualizada correctamente.");
            }

            // Listar especies
            System.out.println("Total de especies: "
                    + dao.listarTodos().size());

            // Eliminar especie de prueba
            if (dao.eliminar(especie.getId())) {
                System.out.println("Especie eliminada correctamente.");
            }

        } else {
            System.out.println("No se pudo registrar la especie.");
        }
    }
}
