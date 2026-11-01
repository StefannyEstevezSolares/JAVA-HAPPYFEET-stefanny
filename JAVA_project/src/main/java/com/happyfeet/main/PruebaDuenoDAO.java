
package com.happyfeet.main;

import com.happyfeet.dao.DuenoDAO;
import com.happyfeet.model.Dueno;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

public class PruebaDuenoDAO {

    public static void main(String[] args) {

        DuenoDAO dao = new DuenoDAO();

        try {

            // 1. LISTAR TODOS LOS DUEÑOS
            List<Dueno> duenos = dao.listarTodos();

            String resultado = "LISTA DE DUEÑOS\n\n";

            if (duenos.isEmpty()) {
                resultado += "No hay dueños registrados.";
            } else {
                for (Dueno dueno : duenos) {
                    resultado += "ID: " + dueno.getId()
                            + "\nNombre: " + dueno.getNombreCompleto()
                            + "\nDocumento: " + dueno.getDocumentoIdentidad()
                            + "\n------------------------\n";
                }
            }

            JOptionPane.showMessageDialog(null, resultado);

            // 2. BUSCAR DUEÑO POR ID
            Dueno dueno = dao.buscarPorId(1);

            if (dueno != null) {

                JOptionPane.showMessageDialog(null,
                        "DUEÑO ENCONTRADO\n\n"
                        + "ID: " + dueno.getId()
                        + "\nNombre: " + dueno.getNombreCompleto());

                // 3. ACTUALIZAR DUEÑO
                dueno.setDireccion("Zona 14, Guatemala");
                dueno.setTelefono("55559876");

                boolean actualizado = dao.actualizar(dueno);

                JOptionPane.showMessageDialog(null,
                        actualizado
                        ? "Dueño actualizado correctamente."
                        : "No se encontró el dueño para actualizar.");

                // 4. COMPROBAR ACTUALIZACIÓN
                Dueno actualizadoDueno = dao.buscarPorId(1);

                if (actualizadoDueno != null) {
                    JOptionPane.showMessageDialog(null,
                            "DATOS ACTUALIZADOS\n\n"
                            + "Nombre: " + actualizadoDueno.getNombreCompleto()
                            + "\nDireccion: " + actualizadoDueno.getDireccion()
                            + "\nTelefono: " + actualizadoDueno.getTelefono());
                }

                // 5. DESACTIVAR DUEÑO
                int respuesta = JOptionPane.showConfirmDialog(
                        null,
                        "¿Deseas desactivar este dueño?",
                        "Confirmar desactivacion",
                        JOptionPane.YES_NO_OPTION
                );

                if (respuesta == JOptionPane.YES_OPTION) {

                    boolean desactivado = dao.desactivar(1);

                    JOptionPane.showMessageDialog(null,
                            desactivado
                            ? "Dueño desactivado correctamente."
                            : "No se pudo desactivar el dueño.");
                }

            } else {

                JOptionPane.showMessageDialog(null,
                        "No se encontró el dueño con ID 1.");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "ERROR EN LA BASE DE DATOS:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
