import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio4 {
    /*
    Implementa un programa con tres funciones:
        • void imprimePositivo(int p): Imprime el valor p. Lanza una ‘Exception’ si p < 0
        • void imprimeNegativo(int n): Imprime el valor n. Lanza una ‘Exception’ si p >= 0
        • La función main para realizar pruebas. Puedes llamar a ambas funciones varias veces
        con distintos valores, hacer un bucle para pedir valores por teclado y pasarlos a las
        funciones, etc. Maneja las posibles excepciones.
     */
    public static void imprimePositivo(int p) throws NumeroNegativoException {
        if (p < 0) {
            throw new NumeroNegativoException(p);
        }
        System.out.println("Valor positivo introducido: " + p);
    }
    public static void imprimeNegativo(int n) throws NumeroPositivoException {
        if (n >= 0) {
            throw new NumeroPositivoException(n);
        }
        System.out.println("Valor negativo introducido: " + n);
    }

    public static void main(String[] args) {
        Scanner sc;

        System.out.println("Ejercicio 4");
        int opcion;
        int numero;
        do {
            sc = new Scanner(System.in);
            System.out.println("\nIntroduzca una opcion");
            System.out.println("1. Imprimir numeros positivos");
            System.out.println("2. Imprimir numeros negativos");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Valor introducido incorrecto");
                opcion = -1;
            }
            try {
                switch (opcion) {
                    case 1:
                        System.out.println("Introduzca el numero positivo");
                        numero = sc.nextInt();
                        imprimePositivo(numero);
                        break;
                    case 2:
                        System.out.println("Introduzca el numero negativo");
                        numero = sc.nextInt();
                        imprimeNegativo(numero);
                        break;
                    case 3:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Has introducido un valor fuera de rango");
                        break;
                }
            } catch (NumeroNegativoException | NumeroPositivoException e) {
                System.out.println("Has encontrado el siguiente error");
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Valor introducido incorrecto y no valido");
            } catch (Exception e) {
                System.out.println("Error, algo fue mal");
                System.out.println(e.getMessage());
            }
        } while (opcion != 3);
        sc.close();
    }
}
