import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio13 {
    public static void main(String[] args) {

        /*  Crea un programa que permita al usuario almacenar una secuencia aritmética en un
            array y luego mostrarla. Una secuencia aritmética es una serie de números que
            comienza por un valor inicial V, y continúa con incrementos de I. Por ejemplo, con V=1
            e I=2, la secuencia sería 1, 3, 5, 7, 9… Con V=7 e I=10, la secuencia sería 7, 17, 27, 37… El
            programa solicitará al usuario V, I además de N (nº de valores a crear).
        */

        Scanner en = new Scanner(System.in);

        System.out.println("\n\u001B[36mEjercicio 13\u001B[38m");

        System.out.println("\nVamos a crear una serie aritmetica");
        System.out.print("Por favor, escribe el valor inicial a tomar: ");
        int inicial = en.nextInt();
        System.out.print("Por favor, escribe la longitud de cada paso artimetico: ");
        int longitud = en.nextInt();
        System.out.print("Por favor, cuantos valores vas a querer creados: ");
        int valores  = en.nextInt();

        int[] secuencia = new int[valores+1];
        secuencia[0] = inicial;

        for (int i = 1; i < secuencia.length; i++) {
            secuencia[i] = secuencia[i-1] + longitud;
        }

        System.out.print("Nuestra secuencia artimetica es: " + Arrays.toString(secuencia));

        en.close();
    }
}
