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

        System.out.print("Ingresa una cantidad de euros que sea un multiplo de 5: ");
        int euros = input.nextInt();

        int tracker = euros;
        int[] billetes;
        billetes = new int[7];
        //Se definen todas las variables que vamos a usar

        boolean multiplo = (euros % 5 == 0);
        if (!multiplo) {
            System.out.println("\u001B[31mEl numero de euros mo es un multiplo de 5\u001B[38m");
        }//Saltamos los que no pueden tener multiplos de 5.
        else {
            while(tracker > 0) {
                if (tracker >= 500) {
                    billetes[6]++;
                    tracker -= 500;
                }
                else if (tracker >= 200) {
                    billetes[5]++;
                    tracker -= 200;
                }
                else if (tracker >= 100) {
                    billetes[4]++;
                    tracker -= 100;
                }
                else if (tracker >= 50) {
                    billetes[3]++;
                    tracker -= 50;
                }
                else if (tracker >= 20) {
                    billetes[2]++;
                    tracker -= 20;
                }
                else if (tracker >= 10) {
                    billetes[1]++;
                    tracker -= 10;
                }
                else if (tracker >= 5) {
                    billetes[0]++;
                    tracker -= 5;
                }
            }//Clasificamos todos los que si pueden tener multiplos
            System.out.println("Los billetes en euros son de " + billetes[6] + " de 500, " +  billetes[5]
                    + " de 200, " + billetes[4] + " de 100, " + billetes[3]  + " de 50, " + billetes[2]
                    + " de 20, " +  billetes[1] +  " de 10 y " + billetes[0] + " de 5 ");
        }

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
        String opcion;
        double num[];
        num = new double[2];
        double resultado;

        do {
            input = new Scanner(System.in);//Es NECESARIO limpiar la consola, o da error.
            System.out.println("\n\u001B[32m1. Sumar\n\u001B[33m2. Restar\n\u001B[34m3. Multiplicar\n\u001B[35m4. Dividir\n\u001B[36m5. Salir\n\u001B[38mPor Favor, Elija un Numero: ");
            opcion = input.nextLine();

            /*
            opcion = opcion.replaceAll("\\D",""); //Este es una posible linea para ignorar caracteres no numeros.
            */

            switch (opcion) {
                case "1"://Suma
                    input = new Scanner(System.in);//Es NECESARIO limpiar la consola, o da error.
                    System.out.println("Introduce el Primer Numero a Sumar: ");
                    num[0] = input.nextDouble();
                    System.out.println("Introduce el Segundo Numero a Sumar: ");
                    num[1] = input.nextDouble();

                    resultado = num[0] + num[1];

                    System.out.println("La Suma de ambos numeros es: " + resultado);
                    break;

                case "2":
                    input = new Scanner(System.in);//Es NECESARIO limpiar la consola, o da error.
                    System.out.println("Introduce el Primer Numero a Restar: ");
                    num[0] = input.nextDouble();
                    System.out.println("Introduce el Segundo Numero a Restar: ");
                    num[1] = input.nextDouble();

                    resultado = num[0] - num[1];

                    System.out.println("La Resta de ambos numeros es: " + resultado);
                    break;

                case "3":
                    input = new Scanner(System.in);//Es NECESARIO limpiar la consola, o da error.
                    System.out.println("Introduce el Primer Numero a Multiplicar: ");
                    num[0] = input.nextDouble();
                    System.out.println("Introduce el Segundo Numero a Multiplicar: ");
                    num[1] = input.nextDouble();

                    resultado = num[0] * num[1];

                    System.out.println("La Multiplicacion de ambos numeros es: " + resultado);
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

                    System.out.println("La Division de ambos numeros es: " + resultado);
                    break;

                case "5":
                    System.out.println("Saliendo del programa");
                    break;

                default:
                    System.out.println("\u001B[31mEl input (\u001B[37m" + opcion +"\u001B[31m) no es valido," +
                            " por favor, presione un numero del 1 al 5\u001B[38m");
            }
        } while (!opcion.equals("5"));
        input.close();//Se cierra
    }
}