import java.util.Scanner;

public class Ejercicio8 {
    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);//Definimos el Escanner
        
        /*
            Crea un programa que cree un array con 100 números reales aleatorios entre 0.0 y 1.0,
            utilizando Math.random(), y luego le pida al usuario un valor real R. Por último, mostrará
            cuántos valores del array son igual o superiores a R.
        */
        System.out.println("\n\u001B[34mEjercicio 8\u001B[38m");

        System.out.println("Va a introducir un valor R y se le dira cuantos numeros entre 0 y 1 hemos obtenido mayor o igual");
        System.out.print("Ingresa el numero R: ");
        double r = en.nextDouble();
        double[] lista = new double[100];

        for  (int i = 0; i < lista.length; i++) {
            lista[i] = Math.random();
        }

        int contador = 0;

        for  (int i = 0; i < lista.length; i++) {
            if(lista[i] >= r) {
                contador++;
            }
        }

        System.out.println("La cantidad de numeros iguales o mayores es: " + contador);

        en.close();//Se cierra
    }//Fin de public static void main
}//Fin de Ejercicio 8