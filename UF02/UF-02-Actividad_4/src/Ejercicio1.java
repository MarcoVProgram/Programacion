import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);//Definimos el Escanner

        /*
            1. Crea un programa que pida diez números reales por teclado, los almacene en un array,
            y luego muestre todos sus valores
        */

        System.out.println("\n\u001B[34mEjercicio 1\u001B[38m");

        System.out.println("Va a introducir 10 numeros reales para mostrar");
        double[] reales;
        reales = new double[10];

        for  (int i = 0; i < reales.length; i++) {
            System.out.println("Ingrese el valor del real: ");
            reales[i] = en.nextDouble();
        }

        System.out.println("Los numeros introducidos son: " + Arrays.toString(reales));

        en.close();//Se cierra escaner
    }//Fin de public static void main
}//Fin de Ejercicio 1
