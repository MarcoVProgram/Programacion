import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio3 {
    /*
    Ejercicio 3
    Implementa un programa que cree un vector tipo double de tamaño 5 y luego, utilizando un
    bucle, pida cinco valores por teclado y los introduzca en el vector. Tendrás que manejar la/las
    posibles excepciones y seguir pidiendo valores hasta rellenar completamente el vector.
     */
    public static void main(String[] args) {

        Scanner sc;
        System.out.print("Ejercicio 3");
        double[] vector = new double[5];
        int datosIntroducidos = 0;

        do {
            sc = new Scanner(System.in);
            try {
                System.out.print("Introduce un numero para el vector: ");
                vector[datosIntroducidos] = sc.nextDouble();
                datosIntroducidos++;
            } catch (InputMismatchException e) {
                System.out.println("Valor introducido incorrecto");
            } catch (Exception e) {
                System.out.println("Algo fue mal con el programa");
                System.out.println(e.getMessage());
            }
        } while (datosIntroducidos < 5);
        System.out.print("Has introducido el siguiente vector: " + Arrays.toString(vector));
        sc.close();
    }
}
