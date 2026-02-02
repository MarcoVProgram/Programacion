import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio2 {
    /*
    Ejercicio 2
    Implementa un programa que pida dos valores int A y B utilizando un nextInt() (de Scanner),
    calcule A/B y muestre el resultado por pantalla. Se deberán tratar de forma independiente las
    dos posibles excepciones, InputMismatchException y ArithmeticException, mostrando en cada
    caso un mensaje de error diferente en cada caso.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Ejercicio 2");
        try {
            System.out.print("Por favor ingrese un numero: ");
            int a = sc.nextInt();
            System.out.print("Por favor ingrese un segundo numero para dividirlo: ");
            int b = sc.nextInt();

            int c = a / b;
            System.out.println("Valor dividido: " + c);
        } catch (InputMismatchException e) {
            System.out.println("Valor introducido incorrecto");
        } catch (ArithmeticException e) {
            System.out.println("Error, no se puede dividir entre 0");
        } catch (Exception e) {
            System.out.println("Algo fue mal con el programa");
            System.out.println(e.getMessage());
        }
        sc.close();
    }
}
