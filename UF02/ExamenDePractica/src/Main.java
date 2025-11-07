import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        Objetivos:
        - Insertar cantidad total de numeros.
        - Creacion de un menu con: Modificar, Estadisticas, Ampliar valor de collecion, Salir.
        - Modificar: Insertar Num, Borrar Num, Modificar Num.
        - Estadistica: Media, Suma, Máximo, Mínimo.
        */

        //Antes de empezar escribiendo en la consola, se ha de escribir un Scanner el cual nos dará los primeros valores
        //para empezar la colección.

        Scanner sc = new Scanner(System.in);

        //Creacion de variables.
        //Vamos a crear en esta zona, todas las variables que vamos a necesitar.

        //Temenos los colores standard que vamos a usar.
        String black = "\033[30m", //Este es un caracter de control de String que se va a usar para hacer el color negro.
               red = "\033[31m", //Este es un elemento de String que se va a usar para hacer el color rojo.
               green = "\033[32m", //Este es un elemento de String que se va a usar para hacer el color verde.
               yellow = "\033[33m", //Este es un elemento de String que se va a usar para hacer el color amarillo.
               blue = "\033[34m", //Este es un elemento de String que se va a usar para hacer el color azul.
               purple = "\033[35m", //Este es un elemento de String que se va a usar para hacer el color morado.
               cyan = "\033[36m", //Este es un elemento de String que se va a usar para hacer el color cian.
               white = "\033[37m", //Este es un elemento de String que se va a usar para hacer el color blanco/gris.
               reset = "\u001B[0m"; //Este es un elemento de String que se va a usar para hacer el color default blanco.

        //Tenemos la String que vamos a usar para la interpretación directa del input, a la que llamaremos raw.
        String raw;
        //Y llamaremos a nuestra opcion el char option.
        char option;
        char subMenuOption;
        int subSubMenuOption; //Esta se usa para escojer entre los submenus que estan ennumerados.

        //Así como las variables numericas.
        int dataSize;
        int index;


        //Usaremos esta instrucción ahora para la creacion de la introducción de inicio del programa, donde se insertará el número.
        System.out.println(green + "Bienvenido" + reset + " al programa.");
        dataSize = changeSizeOfData(0); //Se le asigna el valor zero, que es el menor posible de los numeros naturales.

        //Con la estructura creada, ahora podemos crear el array de los valores.
        double[] valores = new double[dataSize]; //Estos son nuestros valores.
        double[] valoresMod; //Esta es una herramienta temporal para cambiar valores a nuestras variables.

        do {
            sc = new Scanner(System.in);
            System.out.println(black + "\n--" + cyan + "<" + purple + "MENU" + cyan + ">" + black + "--" + reset + ":" +
                    "\n" + blue + "Modificar (M)" + reset + " coleccion" +
                    "\n" + green + "Estadisticas (E)" + reset + " de coleccion" +
                    "\n" + yellow + "Ampliar (A)" + reset + " el valor de la coleccion" +
                    "\n" + red + "Salir" + reset);
            //El codigo superior muestra nuestro menu principal de forma decorada para verse mejor visualmente.
            System.out.print("Ingresa una opcion (Letra entre corchetes): ");
            raw = sc.nextLine();
            option = raw.toUpperCase().charAt(0);
            switch (option) {
                case 'M':
                    //Esta esta con la minima funcionalidad en esta para mostrar colores en la consola sin necesidad de reescribir tantas variables.
                    System.out.println("\nHas escogido la opcion de " + blue + "Modificar (A)" + reset +
                    "\nSelecciona una opcion:" +
                    "\n" + cyan + "Insertar (I)" + reset + " numero" +
                    "\n" + red + "Borrar (B)" + reset + " numero" +
                    "\n" + blue + "Modificar (M)" + reset + " numero de una posicion especifica");
                    System.out.print("\nIngresa una opcion: ");
                    raw = sc.nextLine();
                    subMenuOption = raw.toUpperCase().charAt(0);
                    switch (subMenuOption) {
                        case 'I':
                            System.out.println("\nVa a" + cyan + " insertar " + reset + " un numero a los valores. Escoja que desea hacer." +
                                "\n1. En la ultima posicion (1)" +
                                "\n2. En una posicion especifica (2)");
                            subSubMenuOption = takeInteger();
                            //Ahora nos toca escojer las opciones de los submenus dentro del submenu.
                            switch (subSubMenuOption) {
                                case 1:
                                    //Como vamos a tener que los 0 son los valores nulos, podremos editar directamente
                                    System.out.println("\nInsertando en el valor nulo mas cercano o el ultimo valor.");
                                    index = firstNullNumberIndex(valores);
                                    valores[index] = takeDouble();
                                    break;
                                case 2:
                                    System.out.println("\nInsertando el valor en posicion de su eleccion. Seleccione un numero del 0 al " + (valores.length-1));
                                    do {
                                        index = takeInteger();
                                        if (index >= valores.length) {
                                            System.out.println(red + "Error. Valor " + white + index + red + " fuera de rango." +
                                                    "Seleccione un numero del 0 al " + (valores.length-1) + reset);
                                        }
                                    } while (index >= valores.length);
                                    if (valores[index] == 0.0) {
                                        valores[index] = takeDouble();
                                    }
                                    else {
                                        System.out.println("Numero ya tiene un valor. Por favor, usa Modificar numero.");
                                    }
                                    //Medida de seguridad por si no se quieren borrar datos importantes.
                                    break;
                                default:
                                    System.out.println(red + "Opcion (" + white + subSubMenuOption + red + " no valida." + reset);
                            }
                            break;
                        case 'B':
                            System.out.println("\nVa a" + red + " borrar " + reset + " un numero a los valores. Escoja que desea hacer." +
                                "\n1. En la ultima posicion ocupada (1)" +
                                "\n2. En una posicion especifica (2)");
                            subSubMenuOption = takeInteger();
                            //Ahora nos toca escojer las opciones de los submenus dentro del submenu.
                            switch (subSubMenuOption) {
                                case 1:
                                    //Como vamos a tener que los 0 son los valores nulos, podremos editar directamente
                                    System.out.println("\nBorrando el valor no nulo mas cercano.");
                                    index = lastNonNullNumberIndex(valores);
                                    System.out.println("Borrando el valor " + valores[index]);
                                    valores[index] = 0.0;
                                    break;
                                case 2:
                                    System.out.println("\nBorrando el valor en posicion de su eleccion. Seleccione un numero del 0 al " + (valores.length-1));
                                    do {
                                        index = takeInteger();
                                        if (index >= valores.length) {
                                            System.out.println(red + "Error. Valor " + white + index + red + " fuera de rango." +
                                                    "Seleccione un numero del 0 al " + (valores.length-1) + reset);
                                        }
                                    } while (index >= valores.length);
                                    System.out.println("Borrando el valor " + valores[index]);
                                    valores[index] = 0.0; //No hace falta comprobar que fuese 0.0
                                    break;
                                default:
                                    System.out.println(red + "Opcion (" + white + subSubMenuOption + red + " no valida." + reset);
                                    break;
                            }
                        break;
                    case 'M':
                        System.out.println("\nModificando el valor en posicion de su eleccion. Seleccione un numero del 0 al " + (valores.length-1));
                                do {
                                    index = takeInteger();
                                    if (index >= valores.length) {
                                        System.out.println(red + "Error. Valor " + white + index + red + " fuera de rango." +
                                                "Seleccione un numero del 0 al " + (valores.length-1) + reset);
                                    }
                                } while (index >= valores.length);
                                System.out.println("Modificandoo el valor " + valores[index]);
                                valores[index] = takeDouble(); //No hace falta revisar si es nulo o no ya que se modifica igualmente.
                        break;
                    default:
                        System.out.println(red + "La opcion introducida (" + white + raw + red + ") no se puede interpretar." + reset);
                        break;
                }
                break;
            case 'E':
                System.out.println("\nHas escogido la opcion de " + green + "Estadisticas (E)" + reset +
                    "\nSelecciona una opcion:" +
                    "\n" + blue + "Media (A)" + reset + " de todos los valores no nulos" +
                    "\n" + yellow + "Suma (S)" + reset + " de todos los valores" +
                    "\n" + red + "Maximo (M)" + reset + " de la coleccioon" +
                    "\n" + green + "Minimo (N)" + reset + " de la coleccion");
                    System.out.print("\nIngresa una opcion: ");
                    raw = sc.nextLine();
                    subMenuOption = raw.toUpperCase().charAt(0);
                    switch (subMenuOption) {
                        case 'A':
                            System.out.println(average(valores));
                            break;
                        case 'S':
                            System.out.println(sum(valores));
                            break;
                        case 'M':
                            System.out.println(max(valores));
                            break;
                        case 'N':
                            System.out.println(min(valores));
                            break;
                        default:
                            System.out.println(red + "La opcion introducida (" + white + raw + red + ") no se puede interpretar." + reset);
                            break;
                    }
                break;
            case 'A':
                System.out.println("\nHas seleccionado " + yellow + "Ampliar" + reset + " la coleccion. Ahora tiene " + dataSize + " valores maximos.");
                    dataSize = changeSizeOfData(dataSize);
                    //Tomamos la nueva cantidad limite de valores y creamos un nuevo array con estas.
                    valoresMod = new double[dataSize];
                    //Y la introducimos todos los valores existentes de nuestra antigua variable.
                    for (int i = 0; i < valores.length; i++) {
                        valoresMod[i] = valores[i]; //Esto le asigna por cada valor de valores, su valor a valoresMod.
                    }
                    valores = valoresMod; //Al final del programa, llamamos por referencia a valoresMod para que esos datos sean nuestra nueva variable.
                    //Esto borrara lo que era nuestra antigua valores.
                    break;
            case 'S':
                System.out.println(red + "\nSaliendo" + reset + " del programa...\n¡Hasta la proxima!");//Simple comando para salir.
                break;
            default:
                System.out.println(red + "La opcion introducida (" + white + raw + red + ") no se puede interpretar. Intentalo de Nuevo." + reset);
                break;
            }
        } while (option != 'S');

        sc.close();//Se cerrara el programa al final del ejercicio.
    }
    /*De aqui en adelantee, estos son los METODOS usados:*/
    public static int changeSizeOfData(int previousSize) {
        //Vamos a crear como un metodo la forma de definir el size de nuestros valores. De esta forma, se puede reutilizar mas adelante.
        Scanner in = new Scanner(System.in); //Creamos un nuevo Scanner.
        String raw, rawFiltered; //Creamos nuesta String para interpretar el input que le introduzcamos.
        String yellow = "\033[33m", //Creamos el color amarillo para que quede mejor visualmente.
               red = "\033[31m", //Se crea el color rojo para errores.
               white = "\033[37m",//El color blanco es para mostrar literales.
               reset = "\033[0m"; //Creamos el color default.

        int dataSize = previousSize;//Un valor temporal para inicializarla que sera reescribido.

        do {
            System.out.print("\nPor Favor, Inserte la " + yellow + "cantidad" + reset + " de valores deseados: ");
            raw = in.nextLine(); //Esto toma el Input literal de nuestro usuario.
            rawFiltered =  raw.replaceAll("\\D","");
            //Esto elimina lo que no sea un numero. Convierte negativos y decimales en enteros al eliminar comas, puntos y signos.

            //Y con este dato podemos evaluar ahora si esta tomando el dato correcto o no.

            //Evitamos usar takeInteger() porque necesitamos controlar el tamaño especifico.
            if (rawFiltered.matches("")) {
                //Este IF solo se cumple si el usuario no ha introducido numeros.
                System.out.println(red + "Error. La expresion (" + white + raw + red + ") no se puede identificar como numero." + reset);
                //Expresion de error para mostrar al usuario
            } else {
                dataSize = Integer.parseInt(rawFiltered);
                if (dataSize < previousSize) {
                    //El ejercicio nos pide solo aumentar la cantidad, no reducir, asi que da error.
                    System.out.println("Numero" + red + " no " + reset + "valido. No se puede reducir la cantidad de valores.");
                    rawFiltered =  ""; //Cambiando este valor, hace que el programa do {} while vuelva a rehacerlo.
                }
                else {
                    System.out.println("Has introducido una " + yellow + "cantidad" + reset + " de " + dataSize + " valores maximos.");
                    //El valor es correctamente asignado. Puede ser igual al valor anterior.
                }
            }
        } while (rawFiltered.matches("")); //Solo sale del loop si hay un numero en rawfiltered.

        return dataSize; //Se devuelve la nueva cantidad una vez fuera del loop.
    }
    //Este codigo existe solo para tomar enteros, como las posiciones en el array. No esta junto con el de changeSizeOfData porque ese necesita
    //mas especificaciones.
    public static int takeInteger() {
        Scanner in = new Scanner(System.in);
        int entero = 0;
        String red = "\033[31m", //Se crea el color rojo para errores.
               white = "\033[37m",//El color blanco es para mostrar literales.
               reset = "\033[0m"; //Creamos el color default.
        String raw, rawFiltered;

        System.out.print("Ingresa el valor de un Entero (no decimales ni negativos): ");
        raw = in.nextLine();
        rawFiltered =  raw.replaceAll("\\D","");
        do {
            if (rawFiltered.matches("")) {
                //Este IF solo se cumple si el usuario no ha introducido numeros.
                System.out.println(red + "Error. La expresion (" + white + raw + red + ") no se puede identificar como numero." + reset);
                //Expresion de error para mostrar al usuario
            } else {
                entero = Integer.parseInt(rawFiltered);
            }
        } while (rawFiltered.matches(""));

        return entero;
    }
    public static int firstNullNumberIndex(double[] array) {
        int index = array.length - 1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 0.0) {
                index = i;
            }
        }
        return index;
        //Este programa nos devuelve la posicion del primer numero nulo de un programa, o de su ultimo numero.
    }
    public static int lastNonNullNumberIndex(double[] array) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0.0) {
                index = i;
            }
        }
        return index;
        //Este programa nos devuelve la posicion del ultimo numero no nulo de un programa, o de su primer numero.
    }
    public static double takeDouble() {
        Scanner in = new Scanner(System.in);
        double decimal;
        /*String red = "\033[31m", //Se crea el color rojo para errores.
               white = "\033[37m",//El color blanco es para mostrar literales.
               reset = "\033[0m"; //Creamos el color default.
        String raw, rawFiltered;*/
        //Este codigo se usaria en cuanto los errores anteriores pudiesen ser resueltos.

        System.out.print("Ingresa el valor de un decimal: ");
        decimal = in.nextDouble();
        //raw = in.nextLine;//Este codigo tomaria un input de String para intentar revisar fallos.
        //rawFiltered =  raw.replaceAll("[^1234567890,-]",""); //Este codigo elimina los caracteres deseado. Sin embargo, da error a la conversion.

        /*if (raw.matches("[^1234567890,-]")) {
            System.out.println(red + "Error. La expresión (" + white + raw + red + ") no se puede identificar como numero decimal." +
                    "Asignando valor nulo." + reset);
            decimal = 0.0;
        }
        else {
            decimal = Double.parseDouble(raw);
        }*/
        //Desafortunadamente este metodo tampoco funciona para la obtencion de un input deseado.

        /*do {
            if (rawFiltered.matches("")) {
                //Este IF solo se cumple si el usuario no ha introducido numeros.
                System.out.println(red + "Error. La expresión (" + white + raw + red + ") no se puede identificar como número." + reset);
                //Expresion de error para mostrar al usuario
            } else {
                decimal = in.nextDouble();
                //Da error por algun motivo.
            }
        } while (rawFiltered.matches(""));*/
        //El codigo de arriba es borrado por no poder funcionar.

        return decimal;
    }
    public static String sum(double[] array) {
        double sum = 0.0; //Suma.
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        String retorno = "La suma de nuestra coleccion es " + sum;
        return retorno;
    }
    public static String average(double[] array) {
        double sum = 0.0; //Suma.
        int values = 0; //Valores no cero.
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0.0) {
                sum += array[i];
                values++;
            }
        }
        sum /= values;

        String retorno = "La media de nuestra coleccion es " + sum;
        return retorno;
    }
    public static String max(double[] array) {
        double max = Double.NEGATIVE_INFINITY;
        boolean nulos = false;
        for (double value : array) {
            if (value > max && value != 0.0) {
                max = value;
            }
            if (value == 0.0) {
                nulos = true;
            }
        }
        String retorno  = "El valor maximo de nuestra coleccion es " + max ;
        if  (nulos) {
            retorno += ". Los valores nulos son incluidos.";
        }
        return retorno;
    }
    public static String min(double[] array) {
        double min = Double.POSITIVE_INFINITY;
        boolean nulos = false;
        for (double value : array) {
            if (value < min && value != 0.0) {
                min = value;
            }
            if (value == 0.0) {
                nulos = true;
            }
        }
        String retorno  = "El valor minimo de nuestra coleccion es " + min ;
        if  (nulos) {
            retorno += ". Los valores nulos no son incluidos.";
        }
        return retorno;
    }
}
