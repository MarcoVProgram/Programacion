import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        /*
        String path = "C:\\Users\\daw1\\Documents\\Repositorios - Marco\\Programacion\\UF06\\TestFicheros\\FicheroSalida\\";
         */
        String fileName = "pelicula.txt";
        boolean editExistingFiles = false;

        FileWriter file;

        try {
            file = new FileWriter(/*path +*/ fileName, editExistingFiles);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
            return;
        }

        BufferedWriter buffer = new BufferedWriter(file);
        try {
            buffer.write("Hola Bienvenido!");
            buffer.newLine();
            buffer.write("La dirección de este archivo es: " /*+ path*/ + fileName);
            buffer.newLine();
            buffer.newLine();
            buffer.write("Lista de Peliculas:");
            buffer.newLine();
            buffer.write("La Comunidad del Anillo");
            buffer.newLine();
            buffer.write("Las dos Torres");
            buffer.newLine();
            buffer.write("El Retorno del Rey");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo");
        }
        try {
            buffer.close();
            file.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo");
        }
    }
}