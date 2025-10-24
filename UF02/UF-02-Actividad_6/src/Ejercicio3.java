import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {

        /*
            Crea un programa que cree una matriz de tamaño NxM (tamaño introducido por teclado) e
            introduzca en ella NxM valores (también introducidos por teclado). Luego deberá recorrer la
            matriz y al final mostrar por pantalla cuántos valores son mayores que cero, cuántos son
            menores que cero y cuántos son igual a cero
         */

        System.out.println("\u001B[36mEjercicio 3\u001B[0m");
        Scanner sc = new Scanner(System.in);

        System.out.print("\nIngrese el numero de filas: ");
        int filas = sc.nextInt();
        System.out.print("\nIngrese el numero de columnas: ");
        int columnas = sc.nextInt();

        int[][] tabla = new int[filas][columnas];

        int menorZero = 0,
            mayorZero = 0,
            zero = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tabla[i][j] = (int) (Math.random()*21 - 11);
                if (tabla[i][j] < 0) {
                    menorZero++;
                }
                else if (tabla[i][j] > 0) {
                    mayorZero++;
                }
                else  {
                    zero++;
                }
            }
        }

        System.out.print("\nLa matriz es: " + Arrays.deepToString(tabla));

        System.out.print("\nMayor que 0: " + mayorZero);
        System.out.print("\nMenor que 0: " + menorZero);
        System.out.print("\nCero: " + zero);

        sc.close();
    }
}
