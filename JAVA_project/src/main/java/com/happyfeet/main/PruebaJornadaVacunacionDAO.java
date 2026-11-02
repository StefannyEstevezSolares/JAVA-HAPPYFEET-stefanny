
package com.happyfeet.main;

import com.happyfeet.dao.JornadaVacunacionDAO;
import com.happyfeet.model.JornadaVacunacion;
import java.time.LocalDate;
import java.time.LocalTime;

public class PruebaJornadaVacunacionDAO {

    public static void main(String[] args) {

        JornadaVacunacionDAO dao = new JornadaVacunacionDAO();

        // Crear jornada de prueba
        JornadaVacunacion jornada = new JornadaVacunacion();

        jornada.setNombre("Jornada de prueba");
        jornada.setFecha(LocalDate.now().plusDays(7));
        jornada.setHoraInicio(LocalTime.of(8, 0));
        jornada.setHoraFin(LocalTime.of(14, 0));
        jornada.setUbicacion("Clínica Happy Feet");
        jornada.setDescripcion("Jornada para probar el DAO");
        jornada.setCapacidadMaxima(50);
        jornada.setEstado("Planificada");
        // Registrar jornada
        if (dao.registrar(jornada)) {

            System.out.println("Jornada registrada correctamente.");
            System.out.println("ID generado: " + jornada.getId());

            // Buscar jornada
            JornadaVacunacion encontrada =
                    dao.buscarPorId(jornada.getId());

            if (encontrada != null) {
                System.out.println("Jornada encontrada: "
                        + encontrada.getNombre());
                System.out.println("Fecha: "
                        + encontrada.getFecha());
            }

            // Actualizar jornada
            jornada.setNombre("Jornada actualizada");
            jornada.setCapacidadMaxima(60);

            if (dao.actualizar(jornada)) {
                System.out.println("Jornada actualizada correctamente.");
            }

            // Listar jornadas
            System.out.println("Total de jornadas: "
                    + dao.listarTodos().size());

            // Eliminar jornada de prueba
            if (dao.eliminar(jornada.getId())) {
                System.out.println("Jornada eliminada correctamente.");
            }

        } else {
            System.out.println("No se pudo registrar la jornada.");
        }
    }
}
