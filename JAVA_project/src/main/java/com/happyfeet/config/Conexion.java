
package com.happyfeet.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/happy_feet_veterinaria";

    private static final String USER = "root";

    private static final String PASSWORD = "Sk8010203-$$";

    public static Connection getConnection() {

        Connection conexion = null;

        try {

            conexion = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Conexion exitosa a Happy Feet");

        } catch (SQLException e) {

            System.out.println("Error al conectar con MySQL");
            System.out.println(e.getMessage());

        }

        return conexion;
    }
}
