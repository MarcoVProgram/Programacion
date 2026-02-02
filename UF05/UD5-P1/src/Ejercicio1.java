import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio1 {
    /*
    Ejercicio 2
    Implementa un programa que pida al usuario un valor entero A utilizando un nextInt() (de
    Scanner) y luego muestre por pantalla el mensaje “Valor introducido: …”. Se deberá tratar la
    excepción InputMismatchException que lanza nextInt() cuando no se introduce un entero
    válido. En tal caso se mostrará el mensaje “Valor introducido incorrecto”
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Ejercicio 1");
        System.out.print("Por favor, inserte un numero entero");
        try {
            int a = sc.nextInt();
            System.out.println("Valor inserido: " + a);
        } catch (InputMismatchException e) {
            System.out.println("Valor introducido incorrecto");
        } catch (Exception e) {
            System.out.println("Algo fue mal con el programa");
            System.out.println(e.getMessage());
        }
        sc.close();
    }
}
