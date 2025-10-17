import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio12 {
    public static void main(String[] args) {

        /*  Crea un programa que cree un array de 10 enteros y luego muestre el siguiente menú
            con distintas opciones:
                a. Mostrar valores.
                b. Introducir valor.
                c. Salir.
            La opción ‘a’ mostrará todos los valores por pantalla. La opción ‘b’ pedirá un valor V y una
            posición P, luego escribirá V en la posición P del array. El menú se repetirá indefinidamente hasta
            que el usuario elija la opción ‘c’ que terminará el programa.*/

        System.out.println("\n\u001B[36mEjercicio 12\u001B[38m");//Se define el Ejercicio

        char opcion;//Nuestro valor de control
        int mod; //Nuestro valor a modificar
        Scanner en;//Definimos el Escaner que se le renovará el valor en cada loop para evitar errores

        int[] enteros = new int[10];//Definimos el programa con nuestros numeros enteros
        for  (int i = 0; i < enteros.length; i++) {
            enteros[i] = (int) (1+Math.random()*100);
        }

        do {
            en = new Scanner(System.in);
            System.out.println("\n\u001B[32ma. Mostrar Valores" +
                    "\n\u001B[34mb. Introducir Valor" +
                    "\n\u001B[35mc. Salir");
            System.out.print("\n\u001B[38mPor favor, introduzca una opción: ");
            opcion = en.next().charAt(0);
            //Con esto, tenemos la introducción resuelta. Ahora hacemos cada opcion

            switch (opcion) {
                case 'a':
                    System.out.println("Nuestros valores son: " + Arrays.toString(enteros));
                    break;
                case 'b':
                    do {
                        System.out.print("Indica que posicion quieres modificar: ");
                        mod = en.nextInt();
                        if (mod < 1 || mod > enteros.length) {
                            System.out.println("\u001B[31mError al introducir valor. (\u001B[37m" + mod + "\u001B[31m) no" +
                                    " es un valor entre 1 y 10\u001B[38m");
                        }
                        else {
                            System.out.print("Has escogido el valor \u001B[37m" + mod + "\u001B[38m" +
                                    ", introduce su nuevo valor para continuar: ");
                            enteros[mod-1] = en.nextInt();
                        }
                    } while (mod < 1 || mod > enteros.length);
                    break;
                case 'c':
                    System.out.println("\u001B[33mSaliendo del programa...\u001B[38m");
                    break;
                default:
                    System.out.println("\u001B[31mError, valor (\u001B[37m" + opcion + "\u001B[31m) no es una opcion valida\u001B[38m");
            }
        } while (opcion != 'c');//Fin del loop

        en.close();//Cierra scanner
    }//Fin del Main
}//Fin del Ejercicio 12
