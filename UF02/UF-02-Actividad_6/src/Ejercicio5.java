import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        /*
            Necesitamos crear un programa para registrar sueldos de hombres y mujeres de una empresa
            y detectar si existe brecha salarial entre ambos. El programa pedirá por teclado la información
            de N personas distintas (valor también introducido por teclado). Para cada persona, pedirá su
            género (0 para varón y 1 para mujer) y su sueldo. Esta información debe guardarse en una única
            matriz. Luego se mostrará por pantalla el sueldo medio de cada género.
         */

        System.out.println("\u001B[36mEjercicio 5\u001B[0m\n");

        System.out.println("Introduce cuantos trabajadores se van a evaluar:");
        int n = sc.nextInt();

        double[][] trabajadores = new double[2][n];

        for (int i = 0,genero = 0; i < n; i++) {
            System.out.println("Introduce si es hombre o mujer (0 hombre, 1 mujer):");
            genero = sc.nextInt();

            System.out.println("Introduce su salario:");
            trabajadores[genero][i] = sc.nextDouble();
        }

        System.out.println("Es salario medio de los hombres es: " + sueldoMedio(trabajadores[0]));
        System.out.println("Es salario medio de las mujeres es: " + sueldoMedio(trabajadores[1]));
    }
    public static double sueldoMedio(double[] trabajador) {
        double media = 0;
        int numTrabajadores = 0;
        for (int i = 0; i < trabajador.length; i++) {
            if (!(trabajador[i] == 0.0)) {
                numTrabajadores++;
                media += trabajador[i];
            }
        }
        media = media / numTrabajadores;
        return media;
    }
}
