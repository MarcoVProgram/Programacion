import java.util.Scanner;

public class Practica_3 {
    public static void main(String[] args) {

        /*
            Realiza un programa que dada una cantidad de euros que el usuario introduce por
            teclado (múltiplo de 5 €) mostrará los billetes de cada tipo que serán necesarios para
            alcanzar dicha cantidad (utilizando billetes de 500, 200, 100, 50, 20, 10 y 5). Hay que
            indicar el mínimo de billetes posible. Por ejemplo, si el usuario introduce 145 el
            programa indicará que será necesario 1 billete de 100 €, 2 billetes de 20 € y 1 billete de
            5 € (no será válido por ejemplo 29 billetes de 5, que aunque sume 145 € no es el mínimo
            número de billetes posible).
         */

        //Definimos Scanner
        Scanner input = new Scanner(System.in);

        System.out.println("Ejercicio 1:");

        int euros = 0;
        boolean noMultiplo = false;
        do {
            if (noMultiplo) {
                System.out.println("\u001B[31mEl numero de euros mo es un multiplo de 5\u001B[38m");
            }
            System.out.print("Ingresa una cantidad de euros que sea un multiplo de 5: ");
            euros = input.nextInt();
            noMultiplo = !(euros % 5 == 0);
        } while (noMultiplo);

        int tracker = euros;
        int[] billetes;
        billetes = new int[7];
        //Se definen todas las variables que vamos a usar
        String textoDinero = "El dinero en euros es:";
        if (tracker >= 500) {
            billetes[6] = tracker /500;
            tracker %= 500;
            textoDinero += "\n" + billetes[6] + " billetes de 500€ ";
        }
        if (tracker >= 200) {
            billetes[5] = tracker/200;
            tracker %= 200;
            textoDinero += "\n" + billetes[5] + " billetes de 200€ ";
        }
        if (tracker >= 100) {
            billetes[4] = tracker/100;
            tracker %= 100;
            textoDinero += "\n" + billetes[4] + " billetes de 100€ ";
        }
        if (tracker >= 50) {
            billetes[3] = tracker/50;
            tracker %= 50;
            textoDinero += "\n" + billetes[3] + " billetes de 50€ ";
        }
        if (tracker >= 20) {
            billetes[2] = tracker/20;
            tracker %= 20;
            textoDinero += "\n" + billetes[2] + " billetes de 20€ ";
        }
        if (tracker >= 10) {
            billetes[1] = tracker/10;
            tracker %= 10;
            textoDinero += "\n" + billetes[1] + " billetes de 10€";
        }
        if (tracker >= 5) {
            billetes[0] = tracker/5;
            textoDinero += "\n" + billetes[0] + " billetes de 5€";
        }
            System.out.println(textoDinero);


        /*
            Realiza un programa que muestre un menú de opciones como el siguiente:
                1. Sumar
                2. Restar
                3. Multiplicar
                4. Dividir (incluir manejo de división por 0)
                5. Salir
            El menú debe de repetirse hasta que se escoja la opción 5 (Salir)
         */
        System.out.println("\nEjercicio 2:");//Empezamos el Ejercicio 2
        input = new Scanner(System.in);//Limpiamos el Input
        String raw,opcion;
        double num[];
        num = new double[2];
        double resultado;

        do {
            input = new Scanner(System.in);//Es NECESARIO limpiar la consola, o da error.
            System.out.println("\n\u001B[32m1. Sumar\n\u001B[33m2. Restar\n\u001B[34m3. Multiplicar\n\u001B[35m4. Dividir\n\u001B[36m5. Salir\n\u001B[38mPor Favor, Elija un Numero: ");
            raw = input.nextLine();


            opcion = raw.replaceAll("\\D",""); //Este es una linea para ignorar caracteres no numeros.


            switch (opcion) {
                case "1"://Suma
                    input = new Scanner(System.in);//Es NECESARIO limpiar la consola, o da error.
                    System.out.println("Introduce el Primer Numero a Sumar: ");
                    num[0] = input.nextDouble();
                    System.out.println("Introduce el Segundo Numero a Sumar: ");
                    num[1] = input.nextDouble();

                    resultado = num[0] + num[1];

                    System.out.println("La Suma de ambos numeros es: \u001B[37m" + resultado + "\u001B[38m");
                    break;

                case "2":
                    input = new Scanner(System.in);//Es NECESARIO limpiar la consola, o da error.
                    System.out.println("Introduce el Primer Numero a Restar: ");
                    num[0] = input.nextDouble();
                    System.out.println("Introduce el Segundo Numero a Restar: ");
                    num[1] = input.nextDouble();

                    resultado = num[0] - num[1];

                    System.out.println("La Resta de ambos numeros es: \u001B[37m" + resultado + "\u001B[38m");
                    break;

                case "3":
                    input = new Scanner(System.in);//Es NECESARIO limpiar la consola, o da error.
                    System.out.println("Introduce el Primer Numero a Multiplicar: ");
                    num[0] = input.nextDouble();
                    System.out.println("Introduce el Segundo Numero a Multiplicar: ");
                    num[1] = input.nextDouble();

                    resultado = num[0] * num[1];

                    System.out.println("La Multiplicacion de ambos numeros es: \u001B[37m" + resultado + "\u001B[0m");
                    break;

                case "4":
                    input = new Scanner(System.in);//Es NECESARIO limpiar la consola, o da error.
                    System.out.println("Introduce el Primer Numero a Dividir: ");
                    num[0] = input.nextDouble();
                    num[1] = 1;
                    do {
                        input = new Scanner(System.in);//Es NECESARIO limpiar la consola, o da error.
                        if (num[1] == 0) {
                            System.out.println("\u001B[31mEl numero no puede ser 0\u001B[38m");
                        }
                        System.out.println("Introduce el Segundo Numero a Dividir (No Puede ser 0): ");
                        num[1] = input.nextDouble();
                    } while (num[1] == 0);

                    resultado = num[0] / num[1];

                    System.out.println("La Division de ambos numeros es: \u001B[37m" + resultado + "\u001B[0m");
                    break;

                case "5":
                    System.out.println("\u001B[37mSaliendo del programa...\u001B[0m");
                    break;

                default:
                    System.out.println("\u001B[31mEl input (\u001B[37m" + raw +"\u001B[31m) no es valido," +
                            " por favor, presione un numero del 1 al 5\u001B[38m");
            }
        } while (!opcion.equals("5"));
        input.close();//Se cierra
    }
}