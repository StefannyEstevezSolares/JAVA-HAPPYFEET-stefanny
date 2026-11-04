
package com.happyfeet.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ControladorErrores {

    public static void registrar(Exception error) {

        try (PrintWriter archivo = new PrintWriter(
                new FileWriter("happyfeet-errores.txt", true))) {

            archivo.println("Fecha: " + LocalDateTime.now());
            archivo.println("Error: " + error.getMessage());
            error.printStackTrace(archivo);
            archivo.println("----------------------------------------");

        } catch (IOException e) {
            System.out.println("No se pudo guardar el error en el archivo TXT.");
        }
    }
}
