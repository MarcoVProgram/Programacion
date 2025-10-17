import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);//Definimos el Escanner
        
        /*
            Crea un programa que pida veinte números enteros por teclado, los almacene en un
            array y luego muestre por separado la suma de todos los valores positivos y negativos.
        */
        System.out.println("\n\u001B[34mEjercicio 4\u001B[38m");

        System.out.println("Va a introducir 20 numeros enteros y mostraremos los positivos o negativos");
        int[] enteros;
        enteros = new int[20];

        int positivos = 0;
        int negativos = 0;
        //Definimos ambas variables que usaremos para crear arrays respuesta

        for  (int i = 0; i < enteros.length; i++) {
            System.out.println("Ingrese el valor del real: ");
            enteros[i] = en.nextInt();
            if (enteros[i] >= 0) {
                positivos++;
            }
            else  {
                negativos++;
            }
        }

        boolean hayPositivos = positivos > 0;
        boolean hayNegativos = negativos > 0;
        int[] positivosTotales;
        positivosTotales = new int[positivos];
        int[] negativosTotales;
        negativosTotales = new int[negativos];

        int contadorPositivos = 0;
        int contadorNegativos = 0;
        //Estas variables nos sirven para contar.

        for (int i = 0; i < enteros.length; i++) {
            if(enteros[i] >= 0 && hayPositivos) {
                positivosTotales[contadorPositivos] = enteros[i];
                contadorPositivos++;
            }
            else if (hayNegativos) {
                negativosTotales[contadorNegativos] = enteros[i];
                contadorNegativos++;
            }
        }

        if (hayPositivos && hayNegativos) {
            System.out.println("Los numeros positivos son " + Arrays.toString(positivosTotales) + " y los negativos son " +
                    Arrays.toString(negativosTotales));
        }
        else if  (hayPositivos) {
            System.out.println("Los numeros positivos son " + Arrays.toString(positivosTotales));
        }
        else {
            System.out.println("Los numeros negativos son " + Arrays.toString(negativosTotales));
        }

        en.close();//Se cierra
    }//Fin de public static void main
}//Fin de Ejercicio 4