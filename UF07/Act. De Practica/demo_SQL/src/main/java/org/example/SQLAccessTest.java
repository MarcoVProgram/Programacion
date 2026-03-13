package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class SQLAccessTest {

    public static List<String> getEmployeesNames() {
        List<String> list = new LinkedList<>();

        //Sentencia SQL
        String sqlNames = "SELECT e.first_name, e.last_name FROM employees e";

        try (Connection connection = SQLDataManager.getConnection();) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlNames);

            while (resultSet.next()) {
                list.add(resultSet.getNString(1));
            }


        } catch (SQLException e) {
            System.err.println("Error al conectar al banco de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }
}
