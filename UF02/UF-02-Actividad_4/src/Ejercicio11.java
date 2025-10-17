import java.util.Arrays;

public class Ejercicio11 {
    public static void main(String[] args) {
        /*
            Crea un programa que cree dos arrays de enteros de tamaño 100. Luego introducirá en
            el primer array todos los valores del 1 al 100. Por último, deberá copiar todos los valores
            del primer array al segundo array en orden inverso, y mostrar ambos por pantalla.
        */
        System.out.println("\n\u001B[34mEjercicio 11\u001B[38m");
        int[] primeraLista;
        primeraLista = new int[100];
        int[] segundaLista;
        segundaLista = new int[100];

        for (int i = 0; i < primeraLista.length; i++) {
            primeraLista[i] = i+1;
        }

        for (int i = 0; i < segundaLista.length; i++) {
            segundaLista[i] = primeraLista[(99-i)];
        }

        System.out.println("Primera lista es: " + Arrays.toString(primeraLista));
        System.out.println("Segunda lista es: " + Arrays.toString(segundaLista));

    }//Fin de public static void main
}//Fin de Ejercicio 11