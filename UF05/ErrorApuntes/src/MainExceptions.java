import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainExceptions {
    public static void main(String[] args) {

        FileReader fichero = null;
        try {
            fichero = new FileReader("c:/datos.txt");
            System.out.println("El fichero ha sido abierto");
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        }
        finally {
            try {
                close(fichero);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Fichero finalizado");
        }

    }
    public static void close(FileReader fichero) throws IOException {
        fichero.close();
    }
}