
package com.happyfeet.main;

import com.happyfeet.config.Conexion;
import com.happyfeet.model.Dueno;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        int opcion =0;

        do {

            String menu = """
                    HAPPY FEET VETERINARIA

                    1. Probar conexion a MySQL
                    2. Probar modelo Dueno
                    0. Salir

                    Selecciona una opcion:
                    """;

            String respuesta = JOptionPane.showInputDialog(menu);

            if (respuesta == null) {
                break;
            }

            try {

                opcion = Integer.parseInt(respuesta);

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Debes ingresar un numero valido."
                );

                continue;
            }

            switch (opcion) {

                case 1:

                    Connection conexion = Conexion.getConnection();

                    if (conexion != null) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Happy Feet conectado a MySQL."
                        );

                        try {
                            conexion.close();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    e.getMessage()
                            );
                        }

                    } else {

                        JOptionPane.showMessageDialog(
                                null,
                                "No se pudo conectar a MySQL."
                        );

                    }

                    break;

                case 2:

                    Dueno dueno = new Dueno();

                    dueno.setId(1);
                    dueno.setNombreCompleto("Carlos Ramirez");
                    dueno.setEmail("carlos@gmail.com");
                    dueno.setActivo(true);

                    JOptionPane.showMessageDialog(
                            null,
                            "ID: " + dueno.getId()
                            + "\nNombre: " + dueno.getNombreCompleto()
                            + "\nEmail: " + dueno.getEmail()
                            + "\nActivo: " + dueno.isActivo()
                    );

                    break;

                case 0:

                    JOptionPane.showMessageDialog(
                            null,
                            "Cerrando Happy Feet."
                    );

                    break;

                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Opcion no valida."
                    );

            }

        } while (opcion != 0);

    }
}
