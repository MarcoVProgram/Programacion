import java.io.*;

public class MainShop {
    public static void main(String[] args) {

        String path = "Resources\\";

        String fileName = "pelicula2.txt";

        //Estructura try catch con recursos
        try (FileReader file = new FileReader(path + fileName);
             BufferedReader buffer = new BufferedReader(file)) {

            //CSV, datos separados for comas

            String line = "";
            while (line != null) {
                line = buffer.readLine();
                if (line != null) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
        }
    }
}
