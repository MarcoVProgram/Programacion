public class Ejercicio2 {
    public static void main(String[] args) {

        /*
            Crea un programa que cree una matriz de 10x10 e introduzca los valores de las tablas de
            multiplicar del 1 al 10 (cada tabla en una fila). Luego mostrará la matriz por pantalla.
         */

        System.out.println("\u001B[36mEjercicio 2\u001B[0m");


        int dimensiones = 10;

        int[][] tabla;
        tabla = new int[dimensiones][dimensiones];

        for(int i = 0; i < tabla.length; i++){
            for(int j = 0; j < tabla[i].length; j++){
                tabla[i][j] = (j+1)*(i+1);
                System.out.print(tabla[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
