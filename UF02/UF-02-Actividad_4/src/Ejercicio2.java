import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);//Definimos el Escanner

        /*
            Crea un programa que pida diez números reales por teclado, los almacene en un array,
            y luego muestre la suma de todos los valores.
         */
        System.out.println("\n\u001B[34mEjercicio 2\u001B[38m");

        System.out.println("Va a introducir 10 numeros reales para mostrar su suma");
        double[] reales;
        reales = new double[10];
        double suma = 0;

        for  (int i = 0; i < reales.length; i++) {
            System.out.println("Ingrese el valor del real: ");
            reales[i] = en.nextDouble();
            suma += reales[i];
        }

        System.out.println("La suma de loso numeros introducidos es: " + suma);

        en.close();//Se cierra escaner
    }//Fin de public static void main
}//Fin de Ejercicio 2