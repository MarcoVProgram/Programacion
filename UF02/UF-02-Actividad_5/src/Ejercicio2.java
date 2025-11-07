import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        /*
            Crea un programa que pida dos cadenas de texto por teclado y luego indique si son
            iguales, además de si son iguales sin diferenciar entre mayúsculas y minúsculas.
         */

        System.out.println("\u001B[36mEjercicio 2\u001B[0m");

        //Definimos un scanner

        Scanner sc = new Scanner(System.in);
        System.out.println("\nIntroduce una frase: ");
        String frase1 = sc.nextLine();
        System.out.println("Introduce otra frase: ");
        String frase2 = sc.nextLine();

        boolean equal = frase1.equalsIgnoreCase(frase2);

        System.out.println("¿Son las frases iguales (ignorando mayusculas y minusculas)? " + equal);


        sc.close();
    }
}
