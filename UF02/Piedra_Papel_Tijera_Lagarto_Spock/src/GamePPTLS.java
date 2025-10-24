import javax.swing.*;
import java.util.Scanner;

public class GamePPTLS {
    public static void main(String[] args) throws InterruptedException {

        //Se va a crear el juego para diseñar piedra papel tijera lagarto Spock.
        //El concepto va a ser con unas instrucciones desconocidas, por lo que debido a esto, vamos a hacer que sea mas
        //accesible, con un menú principal al que se podra ir para atrás despues de una partida.

        //Primero vamos a definir las variables fuera de los bucles. Esto se va a hacer primero desde el Scanner.

        Scanner in = new Scanner(System.in);

        /*
            De aqui, vamos a definir dos variables. Una que sea el input RAW para escribir los errore, y otro que sea el
            procesado cuando intente leer el input que acabará siendo una sola tecla.
        */

        //Vamos a definir aquí los colores que vamos a usar a lo largo de la consola, para que estos estén intuitivos y
        //entendibles a lo largoo del programa.

        String black = "\033[30m", //Servira para elementos decorativos, pero no para el texto.
               red = "\033[31m", //Esta se reservará para errores y para marcar salir.
               green = "\033[32m", //Mayormente usado para colores Lagarto
               yellow = "\033[33m", //Mayormente utilizada para Tijeras
               blue = "\033[34m", //Mayormente usada para Spock
               purple = "\033[35m", //Mayormente usada para Piedra
               cyan = "\033[36m", //Se usara para el titulo del programa.
               white = "\033[37m", //Esta se reservará para el contenido que dió error, asi como Papel.
               reset = "\u001B[0m"; //Esto devuelve el color normal a la consola

        String raw;
        char opcion = 'X';
        boolean entradaNecesaria = true; //Usaremos esta de aqui para poder hacer loops sin pedir datos.

        //Aqui vamos a definir los controles principales del juego, que luego el usuario podrá cambiar en ajustes.
        char piedra = 'P', papel = 'L', tijera = 'T', lagarto = 'Z', spock = 'S', menu = 'M'; //Variables de Juego
        char reglas = 'R', controles = 'C', jugar = 'J', salir = 'X'; //Variables del Menu

        do {
            in = new Scanner(System.in);
            if (entradaNecesaria) {
                System.out.println(black + "\n===<|||" + cyan + "   Piedra Papel Tijera Lagarto Spock   " + black + "|||>===" + reset);
                System.out.print("\nOpciones: "
                        + green + "\nReglas de Juego:  " + reglas
                        + yellow + "\nControles:  " + controles
                        + blue + "\nJugar:  " + jugar
                        + red + "\nSalir:  " + salir
                        + reset + "\n\nPor Favor, seleccione una Opción: ");
                // Se diseña el menu
                raw = in.nextLine(); //Se toma el input, una String.
                opcion = raw.toUpperCase().charAt(0); //Se toma de esta opcion
            }

            switch (opcion) {
                case 'R':
                    System.out.println(cyan  + "\nReglas de Juego:\n\n"
                            + purple + "Piedra" + reset + " rompe a " + yellow + "Tijeras\n"
                            + "Tijeras" + reset + " cortan a " + white + "Papel\n"
                            + "Papel" + reset + " envuelve a " + purple + "Piedra\n"
                            + "Piedra" + reset + " aplasta a  " + green + "Lagarto\n"
                            + "Lagarto" + reset + " come a " + white + "Papel\n"
                            + "Papel" + reset + " despide a " + blue + "Spock\n"
                            + "Spock" + reset + " destroza a " + yellow + "Tijeras\n"
                            + "Tijeras" + reset + " decapitan a " + green + "Lagarto\n"
                            + "Lagarto" + reset + " envenena a " + blue + "Spock\n"
                            + "Spock" + reset + " desintegra a " + purple + "Piedra" + reset);
                    //Un display con todas las reglas de juego

                    Thread.sleep(2000); //Se pausa el programa por dos segundos para dar tiempo a leer las instrucciones.
                    break;
                case 'C':
                    System.out.println(cyan  + "\nControles:\n\n");
                    System.out.println(reset + "Menu:" +
                                        "\nAbrir Reglas de Juego: " + reglas +
                                        "\nCambiar controles del Programa: " + controles +
                                        "\nEmpezar a Jugar o Reiniciar Juego: " + jugar +
                                        "\nSalir del Programa y terminar: " + red + salir + reset +
                                        "\nVolver al Menu de Inicio: " + menu);
                    System.out.println(reset + "Juego: " +
                                        purple + "\nPiedra: " + piedra +
                                        white + "\nPapel: " + papel +
                                        yellow + "\nTijera: " + tijera +
                                        green + "\nLagarto: " + lagarto
                                        blue + "\nSpock: " + spock + reset);
                    System.out.println("Los controles de Menu funcionan incluso dentro de una partida, sin pedir el Menu.");
                    Thread.sleep(2000); //3 segundos de espera para leer los controles.
                    break;
            }
        } while (opcion != salir);
    }
}
