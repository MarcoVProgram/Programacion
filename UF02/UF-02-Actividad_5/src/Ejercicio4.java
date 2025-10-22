import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {

        /*
            Crea un programa que muestre por pantalla cuantas vocales de cada tipo hay (cuantas
            ‘a’, cuantas ‘e’, etc.) en una frase introducida por teclado. No se debe diferenciar entre
            mayúsculas y minúsculas. Por ejemplo, dada la frase “Mi mama me mima” dirá que hay:
                Nº de A's: 3
                Nº de E's: 1
                Nº de I's: 2
                Nº de O's: 0
                Nº de U's: 0
         */
        System.out.println("\u001B[36mEjercicio 4\u001B[38m");

        Scanner sc = new Scanner(System.in);

        System.out.println("\nIntroduce una frase: ");
        String frase = sc.nextLine();

        int a = 0,
            e = 0,
            i = 0,
            o = 0,
            u = 0;

        for (int j=0;j<frase.length();j++) {
            if (frase.matches("[aA]")) {
                //(frase.charAt(j)=='a' || frase.charAt(j)=='A') is a valid boolean
                a++;
            } else if (frase.matches("[bB]")) {
                //(frase.charAt(j)=='b' || frase.charAt(j)=='B') is a valid boolean
                e++;
            } else if (frase.matches("[cC]")) {
                //(frase.charAt(j)=='c' || frase.charAt(j)=='C') is a valid boolean
                i++;
            } else if (frase.matches("[dD]")) {
                //(frase.charAt(j)=='d' || frase.charAt(j)=='D') is a valid boolean
                o++;
            } else if (frase.matches("[eE]")) {
                //(frase.charAt(j)=='e' || frase.charAt(j)=='E') is a valid boolean
                u++;
            }
        }

        System.out.println("Nº de A's: " + a
                    + "\nNº de E's: " + e
                    + "\nNº de I's: " + i
                    + "\nNº de O's: " + o
                    + "\nNº de U's: " + u);

        sc.close();
    }
}
