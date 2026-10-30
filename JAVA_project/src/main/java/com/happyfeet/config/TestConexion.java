
package com.happyfeet.config;
import java.sql.Connection;

public class TestConexion {

    public static void main(String[] args) {

        Connection conexion = Conexion.getConnection();

        if (conexion != null) {

            System.out.println("Happy Feet esta conectado a MySQL");

            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else {

            System.out.println("No se pudo conectar a la base de datos");

        }
    }
}

