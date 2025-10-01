import java.util.Scanner;

public class Practica1 {
    public static void main(String[] args) {
        //Ejercicio 1

        System.out.println("Ejercicio 1");

        System.out.println("Buenos dias.");

        //Ejercicio 2

        System.out.println("\nEjercicio 2");

        double lado = 5;
        double area = lado * lado;

        System.out.println("El area del cuadrado de lado " + lado + " es: " + area + "\n");

        //Ejercicio 3

        System.out.println("\nEjercicio 3");

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el valor de lado: ");
        lado = sc.nextDouble();
        area = lado * lado;

        System.out.println("El area del cuadrado de lado " + lado + " es: " + area + "\n");

        //Ejercicio 4

        System.out.println("\nEjercicio 4");

        System.out.println("Calculemos la suma, resta, multiplicacion y division de dos numeros.");

        System.out.println("Introduce el primer numero: ");
        double numUno = sc.nextDouble();

        System.out.println("Introduce el segundo numero: ");
        double numDos = sc.nextDouble();

        double suma = numUno + numDos;
        double resta = numUno - numDos;
        double multiplicacion = numUno * numDos;
        double division;
        System.out.println("La suma de ambos numeros son:\n" + suma);
        System.out.println("La resta de ambos numeros son:\n" + resta);
        System.out.println("La multiplicacion de ambos numeros son:\n" + multiplicacion);
        if (numDos != 0) {
            division = numUno / numDos;
            System.out.println("La division de ambos numeros son:\n" + division);
        }
        else {
            System.out.println("No se puede calcular la division entre cero");
        }


        //Ejercicio 5

        System.out.println("\nEjercicio 5");

        System.out.println("Introduce el valor de un radio: ");

        double radio = sc.nextDouble();

        double perimetroRadio = 2 * Math.PI * radio;
        double areaRadio = Math.PI * radio * radio;
        double volumeRadio = (Math.PI * radio * radio * radio) * (4.0/3.0);

        System.out.println("Para un radio de valor: " + radio);
        System.out.println("La area de un circulo es: " + areaRadio);
        System.out.println("La perimetro de un circulo es: " + perimetroRadio);
        System.out.println("El volumen de una esfera es: " + volumeRadio);

        //Ejercicio 6

        System.out.println("\nEjercicio 6");

        System.out.println("Introduce el valor de un producto: ");
        double producto = sc.nextDouble();

        System.out.println("Introduce el valor real del producto: ");
        double valorReal = sc.nextDouble();

        double porcentajeDescuento = (1-(producto/valorReal))*100;

        System.out.println("El producto tiene un descuento del " + porcentajeDescuento + "%");

        //Ejercicio 7

        System.out.println("\nEjercicio 7");

        System.out.println("Introduce el valor en millas marinas: ");
        double valorMilla = sc.nextDouble();
        double valorMetro = 1852 *  valorMilla;

        System.out.println("El valor en metros de " + valorMilla + " millas es de: " + valorMetro);

        //Ejercicio 8

        System.out.println("\nEjercicio 8");

        System.out.println("Vamos a ordenar dos numeros de menor a mayor.");

        System.out.println("Introduce el primer numero: ");
        numUno = sc.nextDouble();

        System.out.println("Introduce el segundo numero: ");
        numDos = sc.nextDouble();

        if (numUno >= numDos) {
            System.out.println("El orden de menor a mayor es " + numUno + ", " + numDos);
        }
        else {
            System.out.println("El orden de menor a mayor es " + numDos +  ", " + numUno);
        }

        //Ejercicio 9

        System.out.println("\nEjercicio 9");

        System.out.println("Vamos a ver cual numero es mayor.");

        System.out.println("Introduce el primer numero: ");
        numUno = sc.nextDouble();

        System.out.println("Introduce el segundo numero: ");
        numDos = sc.nextDouble();

        if (numUno == numDos) {
            System.out.println("Ambos numeros son iguales.");
        }
        else if (numUno > numDos) {
            System.out.println("El numero mayor es " + numUno);
        }
        else if (numUno < numDos) {
            System.out.println("El numero mayor es " + numDos);
        }

        //Ejercicio 10

        System.out.println("\nEjercicio 10");

        System.out.println("\nEjercicio 9");

        System.out.println("Vamos a ver cual numero es mayor.");

        System.out.println("Introduce el primer numero: ");
        numUno = sc.nextDouble();

        System.out.println("Introduce el segundo numero: ");
        numDos = sc.nextDouble();

        System.out.println("Introduce el tercer numero: ");
        double numTres = sc.nextDouble();

        if (numUno >= numDos && numUno >= numTres) {
            System.out.println("El numero mayor es " + numUno);
        }
        else if (numUno <= numDos && numTres <= numDos) {
            System.out.println("El numero mayor es " + numDos);
        }
        else if (numTres >= numDos && numTres >= numUno) {
            System.out.println("El numero mayor es " + numTres);
        }

        //Ejercicio 11

        System.out.println("\nEjercicio 11");

        System.out.println("Calculemos la suma, resta, multiplicacion y division de dos numeros.");

        System.out.println("Introduce el primer numero: ");
        numUno = sc.nextDouble();

        System.out.println("Introduce el segundo numero: ");
        numDos = sc.nextDouble();

        suma = numUno + numDos;
        resta = numUno - numDos;
        multiplicacion = numUno * numDos;
        System.out.println("La suma de ambos numeros son:\n" + suma);
        System.out.println("La resta de ambos numeros son:\n" + resta);
        System.out.println("La multiplicacion de ambos numeros son:\n" + multiplicacion);
        if (numDos != 0) {
            division = numUno / numDos;
            System.out.println("La division de ambos numeros son:\n" + division);
        }
        else {
            System.out.println("No se puede calcular la division entre cero");
        }

        //Ejercicio 12

        System.out.println("\nEjercicio 12");

        System.out.println("Vamos a ver cual numero es mayor.");

        System.out.println("Introduce el primer numero: ");
        numUno = sc.nextDouble();

        System.out.println("Introduce el segundo numero: ");
        numDos = sc.nextDouble();

        if (numUno == numDos) {
            System.out.println("Ambos numeros son iguales y es: " + numUno);
        }
        else if (numUno > numDos) {
            System.out.println("El numero mayor es " + numUno);
        }
        else if (numUno < numDos) {
            System.out.println("El numero mayor es " + numDos);
        }

        //Ejercicio 13

        System.out.println("\nEjercicio 13");

        System.out.println("Vamos a ver si el numero es positivo.");

        System.out.println("Introduce el primer numero: ");
        numUno = sc.nextDouble();

        if (numUno >= 0) {
            System.out.println(numUno + " es positivo");
        }
        else {
            System.out.println(numUno + " es negativo");
        }
    }
}