import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);//Definimos el Escanner
        
        /*
            Crea un programa que pida diez números reales por teclado, los almacene en un array,
            y luego lo recorra para averiguar el máximo y mínimo y mostrarlos por pantalla.
        */
        System.out.println("\n\u001B[34mEjercicio 3\u001B[38m");

        System.out.println("Va a introducir 10 numeros reales y mostraremos su maximo");
        double[] reales;
        reales = new double[10];

        for  (int i = 0; i < reales.length; i++) {
            System.out.println("Ingrese el valor del real: ");
            reales[i] = en.nextDouble();
        }

        Arrays.sort(reales);

        System.out.println("El valor maximo de los numeros es: " + reales[reales.length-1] + " y el minimo es " + reales[0]);

        en.close();//Se cierra
    }//Fin de public static void main
}//Fin de Ejercicio 3