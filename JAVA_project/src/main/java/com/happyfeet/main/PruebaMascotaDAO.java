
package com.happyfeet.main;

import com.happyfeet.dao.MascotaDAO;
import com.happyfeet.model.Mascota;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PruebaMascotaDAO {

    public static void main(String[] args) {

        MascotaDAO dao = new MascotaDAO();

        try {

            // 1. REGISTRAR UNA MASCOTA

            Mascota mascota = new Mascota();

            mascota.setDuenoId(1);
            mascota.setNombre("Luna QA");
            mascota.setRazaId(1);
            mascota.setFechaNacimiento(LocalDate.of(2022, 5, 10));
            mascota.setSexo("Hembra");
            mascota.setPesoActual(new BigDecimal("12.50"));
            mascota.setMicrochip("TEST-MASCOTA-001");
            mascota.setTatuaje("LUNA001");
            mascota.setUrlFoto("https://ejemplo.com/luna.jpg");
            mascota.setAlergias("Ninguna conocida");
            mascota.setCondicionesPreexistentes("Ninguna");
            mascota.setActivo(true);

            int idGenerado = dao.registrar(mascota);

            JOptionPane.showMessageDialog(null,
                    "MASCOTA REGISTRADA CORRECTAMENTE\n"
                    + "ID generado: " + idGenerado);

            // 2. BUSCAR POR ID

            Mascota encontrada = dao.buscarPorId(idGenerado);

            if (encontrada != null) {

                JOptionPane.showMessageDialog(null,
                        "MASCOTA ENCONTRADA\n\n"
                        + "ID: " + encontrada.getId()
                        + "\nNombre: " + encontrada.getNombre()
                        + "\nPeso: " + encontrada.getPesoActual()
                        + "\nMicrochip: " + encontrada.getMicrochip());

                // 3. ACTUALIZAR

                encontrada.setPesoActual(new BigDecimal("13.00"));
                encontrada.setAlergias("Alergia leve al pollo");

                boolean actualizado = dao.actualizar(encontrada);

                JOptionPane.showMessageDialog(null,
                        actualizado
                        ? "MASCOTA ACTUALIZADA CORRECTAMENTE"
                        : "NO SE PUDO ACTUALIZAR");

                // 4. LISTAR TODAS LAS MASCOTAS

                String listado = "";

                for (Mascota m : dao.listarTodos()) {

                    listado += "\nID: " + m.getId()
                            + " | Nombre: " + m.getNombre()
                            + " | Peso: " + m.getPesoActual();
                }

                if (listado.isEmpty()) {
                    listado = "\nNo hay mascotas registradas.";
                }

                JOptionPane.showMessageDialog(null,
                        "LISTADO DE MASCOTAS\n" + listado);

                // 5. DESACTIVAR

                boolean desactivado = dao.desactivar(idGenerado);

                JOptionPane.showMessageDialog(null,
                        desactivado
                        ? "MASCOTA DESACTIVADA CORRECTAMENTE"
                        : "NO SE PUDO DESACTIVAR");

            } else {

                JOptionPane.showMessageDialog(null,
                        "No se encontró la mascota registrada.");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "ERROR EN LA BASE DE DATOS:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
