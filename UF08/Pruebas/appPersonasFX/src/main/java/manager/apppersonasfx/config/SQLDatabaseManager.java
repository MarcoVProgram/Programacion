package manager.apppersonasfx.config;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseManager {

    private static String DRIVER;
    private static String URL;
    private static String SCHEMA;
    private static String USUARIO;
    private static String CLAVE;

    public static Connection getConnection() {
        Connection conn = null;

        if (DRIVER == null || URL == null || SCHEMA == null || USUARIO == null || CLAVE == null) {
            readConnection();
        }

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

    public static void readConnection() {
        final String path = ".\\src\\main\\java\\manager\\apppersonasfx\\config\\";
        final String file = "config.dat";

        try (FileReader fr = new FileReader(path + file);
             BufferedReader br = new BufferedReader(fr)) {

            DRIVER = br.readLine();
            URL = br.readLine();
            SCHEMA = br.readLine();
            USUARIO = br.readLine();
            CLAVE = br.readLine();


        } catch (EOFException e) {
        } catch (FileNotFoundException e) {
            System.err.println("Creando fichero: " + path + file);
            try {
                File f = new File(path + file);
                if(f.createNewFile()) {
                }

            } catch (IOException ex) {
                System.err.println("Error al crear fichero: " + path + file);
            }

        } catch (IOException e) {
            System.err.println("Error al obtener acceso a personas DB: " + e);
        }

        if (DRIVER == null || URL == null || SCHEMA == null || USUARIO == null || CLAVE == null) {
            System.err.println("No se ha leido. Reinicia.");
        }
    }
}
