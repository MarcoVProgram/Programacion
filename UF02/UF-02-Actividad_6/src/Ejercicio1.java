import java.util.Arrays;

public class Ejercicio1 {
    public static void main(String[] args) {

        /*
            Crea un programa que cree una matriz de tamaño 5x5 que almacene los números del 1 al 25
            y luego muestre la matriz por pantalla.
         */

        System.out.println("\u001B[36mEjercicio 1\u001B[0m");

        int dimensiones = 5;

        int[][] matriz;
        matriz = new int[dimensiones][dimensiones];

        for(int i = 0, contador = 1; i < matriz.length; i++){
            for(int j = 0; j < matriz[i].length; j++){
                matriz[i][j] = contador;
                contador++;
            }
        }

        System.out.println("La matriz es:\n" +  Arrays.deepToString(matriz));
    }
}
