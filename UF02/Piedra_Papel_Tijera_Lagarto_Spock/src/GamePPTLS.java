import java.util.Scanner;

public class GamePPTLS {
    public static void main(String[] args) {

        //Se va a crear el juego para diseñar piedra papel tijeras lagarto Spock.
        //El concepto va a ser con unas instrucciones desconocidas, por lo que debido a esto, vamos a hacer que sea mas
        //accesible, con un menú principal al que se podra ir para atrás despues de una partida.

        //Primero vamos a definir las variables fuera de los bucles. Esto se va a hacer primero desde el Scanner.

        Scanner in;

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

        String raw = "";
        char opcion = 'X';
        boolean entradaNecesaria = true; //Usaremos esta de aqui para poder hacer loops sin pedir datos.

        int victorias;
        int derrotas;
        int resultado = 0;

        //Aqui vamos a definir los controles principales del juego, que luego el usuario podrá cambiar en ajustes.
        final char piedra = 'P', papel = 'L', tijeras = 'T', lagarto = 'Z', spock = 'S', menu = 'M'; //Variables de Juego
        final char reglas = 'R', controles = 'C', jugar = 'J', salir = 'X'; //Variables del Menu. Son constantes para que valgan para el 

        do {
            in = new Scanner(System.in);
            if (entradaNecesaria && opcion != menu) {
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
            entradaNecesaria = true;

            switch (opcion) {
                case reglas:
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

                    espera();
                    //pausa(3); //Se pausa el programa por algunos segundos para dar tiempo a leer las instrucciones. (Este es el caso si se quisiece usar el Thread.sleep)
                    break;
                case controles:
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
                                        yellow + "\nTijera: " + tijeras +
                                        green + "\nLagarto: " + lagarto +
                                        blue + "\nSpock: " + spock + reset);
                    System.out.println("Los controles de Menu funcionan incluso dentro de una partida, sin pedir el Menu.");
                    espera();
                    //pausa(3); //Unos segundos de espera para leer los controles. (Este es el caso si se quisiece usar el Thread.sleep)
                    break;
                case jugar:
                    victorias = 0;
                    derrotas = 0;
                    do {
                        /*
                            A la hora de desarrollar el grueso del juego, voy a optar por hacerlo que sea parte del programa en
                            si, ya que se va a necesitar algunas herramientas dentro del programa de igual manera.
                         */
                        System.out.println(black + "\n[ " + cyan + "Piedra Papel Tijera Lagarto Spock" + black + " ]" + reset);
                        if (victorias != 0 || derrotas != 0) {
                            System.out.println("¡Has ganado " + green + victorias + reset + " veces y has perdido + " + red + derrotas + reset + " veces!");
                        }
                        System.out.println("\nElije una " + red + "Opcion" + reset + " entre " + purple + piedra + " " + white + papel + " " +
                                yellow + tijeras + " " + green + lagarto + " " + blue + spock + reset + "\n(O cualquier otra tecla para abortar)");
                        System.out.print("\nHaga su eleccion: ");
                        in = new Scanner(System.in);
                        raw = in.nextLine();
                        opcion = raw.toUpperCase().charAt(0);
                        //Se usa el mismo sistema al anterior. Al estar usando variables dentro de este programa para escribir esto, se opta por no tener que
                        //convertir estas dos lineas en una función aparte, es innecesario.
                        switch (opcion) {
                            case piedra, papel, tijeras, lagarto, spock:
                                resultado = partida(opcion, piedra, papel, tijeras, lagarto, spock);
                                switch (resultado) {
                                    case 1:
                                        victorias++;
                                        break;
                                    case -1:
                                        derrotas++;
                                        break;
                                    default:
                                        break;
                                }
                                entradaNecesaria = true;
                                break;
                            case reglas, controles, menu, jugar, salir:
                                entradaNecesaria = false;
                                break;
                            default:
                                System.out.println("Abortando...");
                                entradaNecesaria = false;
                                opcion = menu;
                                break;
                        }
                    } while (entradaNecesaria);
                    break;
                case salir:
                    salir();
                    break;
                default:
                    System.out.println(red + "La opcion introducida (" + white + raw + red +") no es válida." + reset);
            }
        } while (opcion != salir);
    }
    /*
    Este es el código que he diseñado para ccrear pausas de n+1.3 segundos, para darle al programa la sensación de espaciado,
    pero al ser más nivel que el visto, se queda solo comentado como qué podria ser. Esto funciona con un try {} catch para
    detectar las excepciones que el prgrama nos pueda lanzar. De esta forma podemons usar el Thread.sleep(), una funcion de
    Java.
    Activa o desactiva el codigo con barra asterisco aquí:

    public static void pausa(int segundos) {
        //Este programa se llama para poder dar tiempo a los jugadores para leer texto sin que salga immediatamente la
        //siguiente prompt
        //Hace una cuenta atras de los segundos introducidos + algunos extra para darle calidad
        String white = "\033[37m",
               reset = "\033[0m";
        try {
            Thread.sleep(1000); //Buffer para el inicio
            System.out.println(); //Le hacemos un espacio a la consola
            for(int i = segundos; i > 0; i--){
                System.out.println(reset + "Continuando en " + white + i + reset + "...");
                Thread.sleep(1000); //Segundo mensaje con el número adecuado para que el total sean efectivamente i segundos
            }
            System.out.println("Continando...");
            Thread.sleep(300); //Buffer para el fin
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();//Salto de linea final
    }
    /**/

    //Vamos a crear una funcion alternativa a pausa(s) alternativa a la que hemos diseñado arriba nuestro para el uso, ya que a pesar de no usar el metodo de excepcion para hacer
    //pausas, seguimos necesitando reusar en mas partes que en solo una, así que interesa para no tener que repetir tanto codigo en simplemente crear una sola función.
    //Esta va a tener un nombre diferente, siendo espera(). Para usar la que tiene pausas temporales en su lugar, necesitariamos cambiar el uso de espera con el de pausa asociado.
    public static void espera() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\033[0mPresiona \033[37mcualquier tecla \033[0mpara continuar..");
        sc.nextLine();
        System.out.println(); //Y se hace un salto de linea final!
    }
    public static void salir() {
        //Esta función existe para no tener que repetir cada vez que se escribe el programa de salir, que al ser repetido más de una vez, esto nos salva código
        System.out.println("\n\033[37mSaliendo del Programa ...\033[0m");
        System.out.println("¡Hasta la próxima!");
    }
    public static int partida(char jugada, char piedra, char papel, char tijeras, char lagarto, char spock) {
        //Esta es la funcion que vamos a diseñar para introducir dentro de esta la jugada del jugador, crear la jugada de la máquina, y determinar quien es el ganador
        //Necesitamos reintroducir todas las variables de piedra/papel/tijeras/lagarto/spock por un motivo de elección personal. Si queremos tenerlas todas dependientes de
        //una unica variable para en cualquier momento decidir cambiarlas sin romper el codigo, entoonces tendremos que tenerlas siempre refrescadas.

        //Redefinimos los colores para poder usarlos aqui. Nos hacen falta menos colores, asi que podemos no importar todos.
        String red = "\033[31m", //Para derrotas.
               green = "\033[32m", //Para victorias y Lagarto
               yellow = "\033[33m", //Para Tijeras
               blue = "\033[34m", //Para Spock
               purple = "\033[35m", //Para Piedra
               white = "\033[37m", //Para empate y Papel
               reset = "\u001B[0m"; //Esto devuelve el color normal a la consola para evitar errores de color
        String interaccion = "";  //Almacemanos la jugada.

        String colorMaquina = ""; //Esto permite darnos el color que queramos a la jugada de la maquina una vez sabemos cual es.
        char jugadaMaquina = ' ';//Inicializando con un valor placeholedr que va a ser rapidamente sobreescrito, para que no de error.

        int resultado = 0; //Diseñado para que -1 es que el jugador a perdido, 0 para un empate, 1 para una victoria del jugador
        int aleatorio = (int) Math.floor((Math.random()*5)); //Creamos un numero para elejir jugada
        switch (aleatorio) {
            case 0:
                jugadaMaquina = piedra;
                if (jugada == papel) {
                    resultado = 1;
                    interaccion = white + "Papel" + reset + " envuelve a " + purple + "Piedra" + reset;
                } else if (jugada == spock) {
                    resultado = 1;
                    interaccion = blue + "Spock" + reset + " desintegra a " + purple + "Piedra" + reset;
                } else if (jugada == tijeras) {
                    resultado = -1;
                    interaccion = purple + "Piedra" + reset + " rompe a " + yellow + "Tijeras" + reset;
                } else if (jugada == lagarto) {
                    resultado = -1;
                    interaccion = purple + "Piedra" + reset + " aplasta a " + green + "Lagarto" + reset;
                } else {
                    resultado = 0;
                    interaccion = reset + "Ambos son " + purple + "Piedra" + reset;
                }
                colorMaquina = purple;
                break;
            case 1:
                jugadaMaquina = papel;
                if (jugada == piedra) {
                    resultado = -1;
                    interaccion = white + "Papel" + reset + " envuelve a " + purple + "Piedra" + reset;
                } else if (jugada == spock) {
                    resultado = -1;
                    interaccion = white + "Papel" + reset + " despide a " + blue + "Spock" + reset;
                } else if (jugada == tijeras) {
                    resultado = 1;
                    interaccion = yellow + "Tijeras" + reset + " corta a " + white + "Papel" + reset;
                } else if (jugada == lagarto) {
                    resultado = 1;
                    interaccion = green + "Lagarto" + reset + " come a " + white + "Papel" + reset;
                } else {
                    resultado = 0;
                    interaccion = reset + "Ambos son " + white + "Papel" + reset;
                }
                colorMaquina = white;
                break;
            case 2:
                jugadaMaquina = tijeras;
                if (jugada == papel) {
                    resultado = -1;
                    interaccion = yellow + "Tijeras" + reset + " corta a " + white + "Papel" + reset;
                } else if (jugada == spock) {
                    resultado = 1;
                    interaccion = blue + "Spock" + reset + " destroza a " + yellow + "Tijeras" + reset;
                } else if (jugada == piedra) {
                    resultado = 1;
                    interaccion = purple + "Piedra" + reset + " rompe a " + yellow + "Tijeras" + reset;
                } else if (jugada == lagarto) {
                    resultado = -1;
                    interaccion = yellow + "Tijeras" + reset + " decapitan a " + green + "Lagarto" + reset;
                } else {
                    resultado = 0;
                    interaccion = reset + "Ambos son " + yellow + "Tijeras" + reset;
                }
                colorMaquina = yellow;
                break;
            case 3:
                jugadaMaquina = lagarto;
                if (jugada == papel) {
                    resultado = -1;
                    interaccion = green + "Lagarto" + reset + " come a " + white + "Papel" + reset;
                } else if (jugada == spock) {
                    resultado = -1;
                    interaccion = green + "Lagarto" + reset + " envenena a " + purple + "Spock" + reset;
                } else if (jugada == tijeras) {
                    resultado = 1;
                    interaccion = yellow + "Tijeras" + reset + " decapitan a " + green + "Lagarto" + reset;
                } else if (jugada == piedra) {
                    resultado = 1;
                    interaccion = purple + "Piedra" + reset + " aplasta a " + green + "Lagarto" + reset;
                } else {
                    resultado = 0;
                    interaccion = reset + "Ambos son " + green + "Lagarto" + reset;
                }
                colorMaquina = green;
                break;
            case 4:
                jugadaMaquina = spock;
                if (jugada == papel) {
                    resultado = 1;
                    interaccion = white + "Papel" + reset + " despide a " + blue + "Spock" + reset;
                } else if (jugada == piedra) {
                    resultado = -1;
                    interaccion = blue + "Spock" + reset + " desintegra a " + purple + "Piedra" + reset;
                } else if (jugada == tijeras) {
                    resultado = -1;
                    interaccion = blue + "Spock" + reset + " destroza a " + yellow + "Tijeras" + reset;
                } else if (jugada == lagarto) {
                    resultado = 1;
                    interaccion = green + "Lagarto" + reset + " envenena a " + purple + "Spock" + reset;
                } else {
                    resultado = 0;
                    interaccion = reset + "Ambos son " + blue + "Spock" + reset;
                }
                colorMaquina = blue;
                break;
        }
        switch (resultado) {
            case -1:
                System.out.println(red + "¡Derrota! " + reset + "Tenemos que " + colorMaquina + jugadaMaquina + reset + " del ordenador te ha vencido.");
                break;
            case 0:
                System.out.println(white + "¡Empate! " + reset + "Tenemos que " + colorMaquina + jugadaMaquina + reset + " del ordenador son la misma.");
                break;
            case 1:
                System.out.println(green + "¡Victoria! " + reset + "Tenemos que " + colorMaquina + jugadaMaquina + reset + " del ordenador ha sido vencida.");
                break;
        }
        System.out.println(interaccion);
        espera();
        return resultado;
    }
}
