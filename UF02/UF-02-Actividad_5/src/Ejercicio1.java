import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {

        System.out.println("\u001B[36mEjercicio 1\u001B[0m");
        //Escribimos el nombre del ejercicio

        Scanner sc = new Scanner(System.in);
        //Declaramos Scanner

        System.out.println("\nVa a introoducir una frase. Cada palabra sera separada por una linea");
        System.out.print("Introduce una frase: ");
        String frase = sc.nextLine();
        String [] palabras = frase.split(" ");

        System.out.println("La frase es:\n");
        for (int i = 0; i < palabras.length; i++) {
            System.out.println(palabras[i]);
        }
        sc.close();//Cerramos Scanner
    }//Fin del Main
}//Fin del Ejercicio
