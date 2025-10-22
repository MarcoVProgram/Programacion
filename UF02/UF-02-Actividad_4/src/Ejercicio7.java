import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio7 {
    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);//Definimos el Escanner
        
        /*
            Crea un programa que pida dos valores enteros P y Q, luego cree un array que contenga
            todos los valores desde P hasta Q, y lo muestre por pantalla.
        */
        System.out.println("\n\u001B[34mEjercicio 7\u001B[38m");

        System.out.println("Va a introducir dos numeros enteros P y Q donde P > Q y se nuestra un array con todos los valores");
        System.out.print("Ingresa el numero P: ");
        int p = en.nextInt();
        int q;
        do {
            System.out.print("Ingresa el numero Q (debe ser menor que P): ");
            q = en.nextInt();
        } while (q < p);


        int[] respuesta = new int[(1+q-p)];
        for (int i = 0; i < respuesta.length; i++) {
            respuesta[i] = p+i;
        }

        System.out.println("La lista en orden es: " +  Arrays.toString(respuesta));

        en.close();//Se cierra
    }//Fin de public static void main
}//Fin de Ejercicio 7