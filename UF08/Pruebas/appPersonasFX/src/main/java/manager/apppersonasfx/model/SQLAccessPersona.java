package manager.apppersonasfx.model;

import manager.apppersonasfx.config.SQLDatabaseManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SQLAccessPersona {

    public static List<Persona> getAllPersonas(){
        List<Persona> personas = new LinkedList<>();

        String sql = "SELECT * FROM person";

        try (Connection connection = SQLDatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Persona p =
                        Persona.builder().dni(resultSet.getString("dni"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .age(resultSet.getInt("age"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString("phone"))
                        .build();

                personas.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener lista personas: " + e);

        }
        return personas;
    }

    public static Persona getPersonaByDNI(String dni){
        Persona persona = null;

        String sql = "SELECT * FROM person WHERE dni = ?";

        try (Connection connection = SQLDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                persona =
                        Persona.builder().dni(resultSet.getString("dni"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .age(resultSet.getInt("age"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString("phone"))
                        .build();
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener persona: " + e);
        }

        return persona;
    }

    public static Persona getPersonaByEmail(String email){
        Persona persona = null;

        String sql = "SELECT * FROM person WHERE email = ?";

        try (Connection connection = SQLDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                persona =
                        Persona.builder().dni(resultSet.getString("dni"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .age(resultSet.getInt("age"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString("phone"))
                        .build();
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener persona: " + e);
        }

        return persona;
    }

    public static boolean createPersona(Persona persona){
        boolean respuesta = false;

        String sql = "INSERT INTO person (dni, name, surname, email, age, phone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = SQLDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, persona.getDni());
            statement.setString(2, persona.getName());
            statement.setString(3, persona.getSurname());
            statement.setString(4, persona.getEmail());
            statement.setInt(5, persona.getAge());
            statement.setString(6, persona.getPhone());

            statement.execute();
            respuesta = true;


        } catch (SQLException e) {
            System.err.println("Error al insertar persona: " + e.getMessage());
        }
        return  respuesta;
    }

    public static boolean updatePersona(Persona persona){
        boolean respuesta = false;

        String sql = "UPDATE person SET name = ?, surname = ?, email = ?, age = ?, phone = ? WHERE dni = ?";

        try (Connection connection = SQLDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(6, persona.getDni());
            statement.setString(1, persona.getName());
            statement.setString(2, persona.getSurname());
            statement.setString(3, persona.getEmail());
            statement.setInt(4, persona.getAge());
            statement.setString(5, persona.getPhone());

            statement.execute();
            respuesta = true;


        } catch (SQLException e) {
            System.err.println("Error al actualizar persona: " + e.getMessage());
        }
        return  respuesta;
    }

    public static boolean deletePersonaByDNI(String dni){
        boolean respuesta = false;

        String sql = "DELETE FROM person WHERE dni = ?";

        try (Connection connection = SQLDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, dni);

            statement.execute();
            respuesta = true;


        } catch (SQLException e) {
            System.err.println("Error al eliminar persona: " + e.getMessage());
        }
        return  respuesta;
    }
}
