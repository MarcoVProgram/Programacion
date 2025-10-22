import java.util.Scanner;

public class Ejercicio10 {
    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);//Definimos el Escanner

        /*
            Crea un programa para realizar cálculos relacionados con la altura (en metros) de
            personas. Pedirá un valor N y luego almacenará en un array N alturas introducidas por
            teclado. Luego mostrará la altura media, máxima y mínima, así como cuántas personas
            miden por encima y por debajo de la media.
        */
        System.out.println("\n\u001B[34mEjercicio 10\u001B[38m");

        System.out.println("Se introduciran alturas, luego, se verá cuales de estas es la maxima, minima, media y" +
                " cuantas encima y debajo de la media");
        System.out.print("Cuantas alturas desea introducir: ");
        int n = en.nextInt();
        double[] lista = new double[n];

        double media = 0.0;

        for  (int i = 0; i < lista.length; i++) {
            System.out.print("Introduce una altura, quedan " + (lista.length - i) + " valores a introducir: ");
            lista[i] = en.nextDouble();
            media += lista[i];
        }

        media /= lista.length;

        double min = lista[0],
               max = lista[0];
        int mayorMedia = 0,
            menorMedia = 0;
        //Double.MIN_VALUE y Double.MAX_VALUE

        for  (int i = 0; i < lista.length; i++) {
            if(lista[i] > max) {
                max = lista[i];
            }
            else if(lista[i] < min) {
                min = lista[i];
            }
            if(lista[i] > media) {
                mayorMedia++;
            }
            else if(lista[i] < media) {
                menorMedia++;
            }
        }

        System.out.println("El maximo es " + max + ", el minimo es " + min + ", la media es " + media + ", hay "
                + mayorMedia + " mayores que la media y hay " + menorMedia + " menores que la media");

        en.close();//Se cierra
    }//Fin de public static void main
}//Fin de Ejercicio 10