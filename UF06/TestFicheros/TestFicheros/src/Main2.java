import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main2 {
    public static void main(String[] args) {

        String path = "Resources\\";

        String fileName = "pelicula2.txt";
        boolean editExistingFiles = false;

        //Estructura try catch con recursos
        try (FileWriter file = new FileWriter (path + fileName, editExistingFiles);
             BufferedWriter buffer = new BufferedWriter(file)) {

            buffer.write("Hola Bienvenido!");
            buffer.newLine();
            buffer.write("La dirección de este archivo es: " + path + fileName);
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
            System.out.println("Error al abrir el archivo");
        }
    }
}
