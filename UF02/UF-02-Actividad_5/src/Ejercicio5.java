import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {

        /*
            Realiza un programa que lea una frase por teclado e indique si la frase es un palíndromo
            o no (ignorando espacios y sin diferenciar entre mayúsculas y minúsculas).
            Supondremos que el usuario solo introducirá letras y espacios (ni comas, ni puntos, ni
            acentos, etc.). Un palíndromo es un texto que se lee igual de izquierda a derecha que de
            derecha a izquierda.
                Por ejemplo:
                Amigo no gima
                Dabale arroz a la zorra el abad
                Amo la pacífica paloma
                A man a plan a canal Panama
         */

        Scanner sc = new Scanner(System.in);
        //Definimos Scanner

        System.out.println("\u001B[36mEjercicio 5\u001B[0m");

        System.out.println("\nIntroduce una frase: ");
        String frase = sc.nextLine();

        String resultado = frase;
        resultado = resultado.replaceAll(" ","");

        char[] letras = resultado.toCharArray();

        for  (int i = 0; i < letras.length; i++) {
            letras[i] = resultado.charAt(letras.length - i - 1);
        }
        String comparacion = String.valueOf(letras);
        //String comparacion = new String(letras);
        //Este es otro metodo alternativo para crear la String.

        boolean sonIguales = comparacion.equalsIgnoreCase(resultado);

        System.out.println("\nEs una frase polindroma: " + sonIguales);

        //System.out.println("\nDebug:\n" + resultado + "\n" + comparacion +  "\n" + frase);
        //Esta linea arriba nos sirve para comprobar que resultado esta dando cada cadena string.

        /*
        Se puede mejorar. Menos pasos si empiezas desde la izquierda y la derecha hasta que un lado se cruze con el otro
        */

        sc.close();
    }
}
