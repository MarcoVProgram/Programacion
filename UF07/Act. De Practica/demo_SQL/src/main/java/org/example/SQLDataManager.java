package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDataManager {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "employees";
    private static final String USUARIO = "root";
    private static final String CLAVE = "daw12";

    public static Connection getConnection() {
        Connection conn = null;

        try {

            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL + SCHEMA, USUARIO, CLAVE);

        } catch (ClassNotFoundException e) {
            System.err.println("Driver Error: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        return conn;
    }
}
