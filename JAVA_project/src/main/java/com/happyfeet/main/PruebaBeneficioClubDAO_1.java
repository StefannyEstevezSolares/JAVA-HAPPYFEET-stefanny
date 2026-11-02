
package com.happyfeet.main;

import com.happyfeet.dao.BeneficioClubDAO;
import com.happyfeet.model.BeneficioClub;
import java.math.BigDecimal;

public class PruebaBeneficioClubDAO_1 {

    public static void main(String[] args) {

        BeneficioClubDAO dao = new BeneficioClubDAO();

        // Crear beneficio de prueba
        BeneficioClub beneficio = new BeneficioClub();

        beneficio.setNombre("Beneficio de prueba");
        beneficio.setDescripcion("Registro para probar el DAO");
        beneficio.setNivelRequerido("Bronce");
        beneficio.setPuntosNecesarios(100);
        beneficio.setTipoBeneficio("Descuento");
        beneficio.setValorBeneficio(new BigDecimal("10.00"));
        beneficio.setActivo(true);

        // Registrar beneficio
        if (dao.registrar(beneficio)) {

            System.out.println("Beneficio registrado correctamente.");
            System.out.println("ID generado: " + beneficio.getId());

            // Buscar beneficio
            BeneficioClub encontrado = dao.buscarPorId(beneficio.getId());

            if (encontrado != null) {
                System.out.println("Beneficio encontrado: "
                        + encontrado.getNombre());
            }

            // Actualizar beneficio
            beneficio.setNombre("Beneficio actualizado");
            beneficio.setValorBeneficio(new BigDecimal("15.00"));

            if (dao.actualizar(beneficio)) {
                System.out.println("Beneficio actualizado correctamente.");
            }

            // Listar beneficios
            System.out.println("Total de beneficios: "
                    + dao.listarTodos().size());

            // Eliminar beneficio de prueba
            if (dao.eliminar(beneficio.getId())) {
                System.out.println("Beneficio eliminado correctamente.");
            }

        } else {
            System.out.println("No se pudo registrar el beneficio.");
        }
    }
}

