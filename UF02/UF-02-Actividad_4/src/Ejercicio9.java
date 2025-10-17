import java.util.Scanner;

public class Ejercicio9 {
    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);//Definimos el Escanner
        
        /*
            Crea un programa que cree un array de enteros de tamaño 100 y lo rellene con valores
            enteros aleatorios entre 1 y 10 (utiliza 1 + Math.random()*10). Luego pedirá un valor N
            y mostrará en qué posiciones del array aparece N.
        */
        System.out.println("\n\u001B[34mEjercicio 9\u001B[38m");

        System.out.println("Va a introducir un valor N y se le dira en que posiciones esta dentro de 100 numeros de 1 a 10");
        System.out.print("Ingresa el numero N: ");
        int n = en.nextInt();
        int[] lista = new int[100];

        System.out.print("El valor N esta en las posiciones: ");

        for  (int i = 0; i < lista.length; i++) {
            lista[i] = (int) (1 + Math.random()*10);
            if(lista[i] == n) {
                System.out.print(i + " ");
            }
        }

        en.close();//Se cierra
    }//Fin de public static void main
}//Fin de Ejercicio 9