import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);//Definimos el Escanner
        
        /*
            Crea un programa que pida veinte números reales por teclado, los almacene en un array
            y luego lo recorra para calcular y mostrar la media: (suma de valores) / nº de valores.
        */
        System.out.println("\n\u001B[34mEjercicio 5\u001B[38m");

        System.out.println("Va a introducir 20 numeros reales y mostraremos su media");
        double[] reales;
        reales = new double[20];
        double suma = 0;
        double media;

        for  (int i = 0; i < reales.length; i++) {
            System.out.println("Ingrese el valor del real: ");
            reales[i] = en.nextDouble();
            suma += reales[i];
        }

        media = suma / reales.length;

        System.out.println("La media de los numeros es: " + media);

        en.close();//Se cierra
    }//Fin de public static void main
}//Fin de Ejercicio 5