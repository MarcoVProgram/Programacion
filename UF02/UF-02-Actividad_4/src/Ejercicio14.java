import java.util.Arrays;

public class Ejercicio14 {
    public static void main(String[] args) {

        /*
            Crea un programa que cree un array de enteros e introduzca la siguiente secuencia de
            valores: 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, etc. hasta introducir 10 diez veces, y luego la
            muestre por pantalla.
         */

        System.out.println("\u001B[36mEjercicio 14\u001B[38m");

        int numValores = 0;
        /*for  (int i = 1; i <= 10; i++) {
            numValores += i;
        }*/
        //Este de aquí es una declaracion estandard para obtener la suma de todos los primeros numeros N. Sin embargo,
        //Podemos continuar aplicandolo con principios matematicos de serie. Para un sumatorio de los i primeros numeros:
        numValores = 10*11/2;
        //Se usa la formula = n(n+1)/2
        //Esta formula siempre nos da un entero, ya que estamos dividiendo siempre un numero par e impar entre dos, ademas
        //de ser el resultado.

        int[] secuencia = new int[numValores];

        //Aqui, tenemos dos formas de solucionarlo. Una de estas siendo que sea por un contador para tener siempre como
        //recordatorio cual puesto vamos a modificar, y la segunda forma es reemplazarlo por la formula basada en i, j.

        for (int i = 1, contador = 0; i <= 10; i++) {
            for (int j = i; j >= 1; j--) {
                secuencia[contador] = i;
                contador++;
                //Se puede cambiar la expresion matematica de contador por una usando las propiedades de las series.
                //Esto se haria cambiando contador por la formula  (i(i+=1)/2) - j
            }
        }

        System.out.println("\nNuestra secuencia artimetica es: " + Arrays.toString(secuencia));
    }
}
