import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio6 {
    /*
    Crea una copia del programa anterior y modifica el main para hacer lo siguiente:
        • Crea un ArrayList<Gato>. Luego, utilizando un bucle, pide al usuario que introduzca los
        datos de 5 gatos: utiliza un Scanner para pedir los datos, instancia el objeto y guárdalo
        en el ArrayList. Por último, imprime la información de los gatos.
        • Maneja las posibles excepciones de modo que en el ArrayList solo almacenemos objetos
        Gato válidos y el bucle se repita hasta crear y almacenar correctamente 5 gatos.
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Gato> gatos = new ArrayList<>();
        String nombreGato;
        int edadGato;
        Gato gatoNuevo;

        while(gatos.size()<5){
            sc = new Scanner(System.in);
            try {
                System.out.print("Introduce el nombre del Gato: ");
                nombreGato = sc.nextLine();

                System.out.print("Introduce el edad del Gato: ");
                edadGato = sc.nextInt();

                System.out.println("Creando Gato...");
                gatoNuevo = new Gato(nombreGato, edadGato);

                gatos.add(gatoNuevo);
                System.out.println("Gato agregado correctamente\n");
            } catch (InputMismatchException e) {
                System.out.println("Valor introducido incorrecto\n");
            } catch (GatoNombreMuyCortoException e) {
                System.out.println("Error al introducir el nombre del Gato");
                System.out.println(e.getMessage() + "\n");
            } catch (GatoEdadNegativaException e) {
                System.out.println("Error al introducir el edad del Gato");
                System.out.println(e.getMessage() + "\n");
            } catch (Exception e) {
                System.out.println("Algo fue mal");
                System.out.println(e.getMessage() + "\n");
            }
        }

        for (Gato gato : gatos) {
            System.out.println(gato.toString() + "\n");
        }

        sc.close();
    }
}
