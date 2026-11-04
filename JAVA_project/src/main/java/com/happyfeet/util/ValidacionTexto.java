
package com.happyfeet.util;

public class ValidacionTexto {

    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static String limpiarTexto(String texto) {
        if (texto == null) {
            return "";
        }

        return texto.trim();
    }

    public static boolean validarCampoObligatorio(String texto, String nombreCampo) {
        if (!esTextoValido(texto)) {
            System.out.println("Error: El campo " + nombreCampo + " es obligatorio y no puede estar vacío.");
            return false;
        }

        return true;
    }
}
