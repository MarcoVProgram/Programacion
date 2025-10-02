import java.util.Scanner;

public class Practica3 {
    public static void main(String[] args) {

        //Definimos Scanner
        Scanner input = new Scanner(System.in);

        System.out.print("Ingresa una cantidad de euros que sea un multiplo de 5: ");
        int euros = input.nextInt();

        int tracker = euros;
        int billetes500 = 0;
        int billetes200 = 0;
        int billetes100 = 0;
        int billetes50 = 0;
        int billetes20 = 0;
        int billetes10 = 0;
        int billetes5 = 0;
        //Se definen todas las variables que vamos a usar

        boolean multiplo = (euros % 5 == 0);
        if (!multiplo) {
            System.err.println("El numero de euros mo es un multiplo de 5");
        }
        else {
            while(tracker > 0) {
                if (tracker >= 500) {
                    billetes500++;
                    tracker -= 500;
                }
                else if (tracker >= 200) {
                    billetes200++;
                    tracker -= 200;
                }
                else if (tracker >= 100) {
                    billetes100++;
                    tracker -= 100;
                }
                else if (tracker >= 50) {
                    billetes50++;
                    tracker -= 50;
                }
                else if (tracker >= 20) {
                    billetes20++;
                    tracker -= 20;
                }
                else if (tracker >= 10) {
                    billetes10++;
                    tracker -= 10;
                }
                else if (tracker >= 5) {
                    billetes5++;
                    tracker -= 5;
                }
            }
            System.out.println("Los billetes son de " + billetes500 + " de 500, " +  billetes200 + " de 200, "
                    + billetes100 + " de 100, " + billetes50  + " de 50, " + billetes20 + " de 20, "
                    +  billetes10 +  " de 10 y " + billetes5 + " de 5 ");
        }
    }
}