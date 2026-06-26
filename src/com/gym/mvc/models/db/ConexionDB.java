package com.gym.mvc.models.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static ConexionDB instancia;
    private Connection conexion;

    private static final String URL  = "jdbc:mysql://localhost:3306/gimnasio_bd";
    private static final String USER = "root";
    private static final String PASS = "D+A13092022d+a"; 

    private ConexionDB() { 
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConexionDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
}