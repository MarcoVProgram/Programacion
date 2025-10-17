import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio6 {
    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);//Definimos el Escanner
        
        /*
            Crea un programa que pida dos valores enteros N y M, luego cree un array de tamaño
            N, escriba M en todas sus posiciones y lo muestre por pantalla.
        */
        System.out.println("\n\u001B[34mEjercicio 6\u001B[38m");

        System.out.println("Va a introducir dos numeros enteros M y N y se le va a mostrar un vector N dimensional con M como valores");
        System.out.print("Ingresa el numero M: ");
        int m = en.nextInt();
        System.out.print("Ingresa el numero N: ");
        int n = en.nextInt();

        int[] vector = new int[n];
        Arrays.fill(vector, m);

        System.out.println("El vector es: " +  Arrays.toString(vector));

        en.close();//Se cierra
    }//Fin de public static void main
}//Fin de Ejercicio 6