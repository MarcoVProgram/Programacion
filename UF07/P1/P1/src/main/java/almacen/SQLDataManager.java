package almacen;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDataManager {

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
        final String path = ".\\src\\main\\java\\almacen\\resources\\";
        final String file = "application.dat";

        try (FileReader fr = new FileReader(path + file);
             BufferedReader br = new BufferedReader(fr)) {

            DRIVER = br.readLine();
            URL = br.readLine();
            SCHEMA = br.readLine();
            USUARIO = br.readLine();
            CLAVE = br.readLine();


        } catch (EOFException e) {
            MyUtils.print("Se ha leido el final del archivo");
        } catch (FileNotFoundException e) {
            MyUtils.print("No se ha encontrado la ruta");
            e.printStackTrace();
        } catch (IOException e) {
            MyUtils.print("Error en el I/O");
        }

        if (DRIVER == null || URL == null || SCHEMA == null || USUARIO == null || CLAVE == null) {
            MyUtils.print("==============================================================");
            MyUtils.print("ERROR AL LEER EL FICHERO DE CONFIGURACION - FICHERO INCORRECTO");
            MyUtils.print("==============================================================");
        }
    }
}
