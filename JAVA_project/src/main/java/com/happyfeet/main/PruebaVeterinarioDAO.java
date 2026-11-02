
package com.happyfeet.main;

import com.happyfeet.dao.VeterinarioDAO;
import com.happyfeet.model.Veterinario;

import java.time.LocalDate;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class PruebaVeterinarioDAO {

    public static void main(String[] args) {

        VeterinarioDAO dao = new VeterinarioDAO();

        try {

            // 1. REGISTRAR UN VETERINARIO

            Veterinario veterinario = new Veterinario();

            veterinario.setNombreCompleto("Andrea Martinez");
            veterinario.setDocumentoIdentidad("VET-TEST-001");
            veterinario.setLicenciaProfesional("LIC-TEST-001");
            veterinario.setEspecialidad("Medicina General");
            veterinario.setTelefono("55556677");
            veterinario.setEmail("andrea.vet.test@example.com");
            veterinario.setFechaContratacion(
                    LocalDate.of(2025, 3, 15));
            veterinario.setActivo(true);

            int idGenerado = dao.registrar(veterinario);

            JOptionPane.showMessageDialog(null,
                    "VETERINARIO REGISTRADO CORRECTAMENTE\n"
                    + "ID generado: " + idGenerado);

            // 2. BUSCAR POR ID

            Veterinario encontrado = dao.buscarPorId(idGenerado);

            if (encontrado != null) {

                JOptionPane.showMessageDialog(null,
                        "VETERINARIO ENCONTRADO\n\n"
                        + "ID: " + encontrado.getId()
                        + "\nNombre: " + encontrado.getNombreCompleto()
                        + "\nEspecialidad: " + encontrado.getEspecialidad()
                        + "\nLicencia: " + encontrado.getLicenciaProfesional());
                
                // 3. ACTUALIZAR

                encontrado.setEspecialidad("Cirugia Veterinaria");
                encontrado.setTelefono("55559988");

                boolean actualizado = dao.actualizar(encontrado);

                JOptionPane.showMessageDialog(null,
                        actualizado
                        ? "VETERINARIO ACTUALIZADO CORRECTAMENTE"
                        : "NO SE PUDO ACTUALIZAR");

            } else {

                JOptionPane.showMessageDialog(null,
                        "No se encontró el veterinario.");
            }

            // 4. LISTAR TODOS LOS VETERINARIOS

            List<Veterinario> veterinarios = dao.listarTodos();

            String listado = "";

            for (Veterinario v : veterinarios) {
                listado += "\nID: " + v.getId()
                        + " | Nombre: " + v.getNombreCompleto()
                        + " | Especialidad: " + v.getEspecialidad();
            }

            JOptionPane.showMessageDialog(null,
                    "LISTADO DE VETERINARIOS\n"
                    + (listado.isEmpty()
                    ? "No hay veterinarios registrados."
                    : listado));

            // 5. DESACTIVAR

            int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Deseas desactivar este veterinario?",
                    "Confirmar desactivación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                boolean desactivado = dao.desactivar(idGenerado);

                JOptionPane.showMessageDialog(null,
                        desactivado
                        ? "VETERINARIO DESACTIVADO CORRECTAMENTE"
                        : "NO SE PUDO DESACTIVAR");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "ERROR EN LA BASE DE DATOS:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

