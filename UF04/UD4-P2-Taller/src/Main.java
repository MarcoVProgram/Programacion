import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //Patrones
    public static final Pattern PATRONMATRICULA = Pattern.compile("[0-9]{4}[A-Z]{3}");
    public static final Pattern PATRONMENU = Pattern.compile("[1-3]");

    public static void main(String[] args) {

        //Objetos
        Taller taller = new Taller();
        Scanner sc = new Scanner(System.in);
        Matcher matcherMenu;

        //Variables
        String inputMenu;
        char option;

        System.out.println("Bienvenido a tu taller");
        do {
            System.out.println("Menu");
            System.out.println(
                    "1. Anadir Coche\n" +
                    "2. Eliminar Coche\n" +
                    "3. Salir\n");
            System.out.print("Por Favor, Escoge el numero de una opcion: ");
            do {
                sc = new Scanner(System.in);
                inputMenu = sc.nextLine();
                matcherMenu = PATRONMENU.matcher(inputMenu);
                if (!matcherMenu.matches()) {
                    System.out.println("Opcion invalida, intenta usar un numero del 1 al 3:");
                }
            } while (!matcherMenu.matches());
            option = inputMenu.charAt(0);

            switch (option) {
                case '1':
                    //Anadir Coche
                    System.out.println();
                    nuevoCoche(taller);
                    pausa();
                    break;
                case '2':
                    //Eliminar Coche
                    System.out.println();
                    quitarCoche(taller);
                    pausa();
                    break;
                case '3':
                    //Salir Y Mostrar
                    System.out.println();
                    salir(taller);
                    break;
                default:
                    //Error, no deberia ocurrir nunca
                    System.out.println();
                    System.out.println("Algo fue mal, Por Favor, Comunicaselo a un Desarrollador");
                    break;
            }
        } while(option != '3');
        sc.close();
    }

    public static void pausa() {
        Scanner in = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("Pulse cualquier tecla para continuar");
        System.out.println("====================================");
        in.nextLine();
    }

    private static String getCorrectMatricula() {
        Matcher matcherMatricula;
        String matricula;
        Scanner in;

        System.out.print("Por Favor, Escribe la matricula del coche: ");

        do {
            in = new Scanner(System.in);
            matricula = in.nextLine().toUpperCase();
            matcherMatricula = PATRONMATRICULA.matcher(matricula);
            if (!matcherMatricula.matches()) {
                System.out.print("Opcion invalida, intenta usar 4 numeros seguidos de 3 letras: ");
            }
        } while (!matcherMatricula.matches());

        return matricula;
    }

    public static void nuevoCoche(Taller taller) {
        Scanner in =  new Scanner(System.in);
        String matricula,color,marca;
        char confirmar;
        boolean continuar = true;

        System.out.println("Vas a registrar un coche en tu taller");

        //Matricula
        matricula = getCorrectMatricula();

        //Color
        System.out.print("Por Favor, Escriba el color del coche: ");
        color = in.nextLine();

        //Marca
        System.out.print("Por Favor, Escriba la marca del coche: ");
        marca = in.nextLine();

        //Revision
        if (taller.elementoExiste(matricula)) {
            System.out.println("El coche ya existe, desea reescribirlo? Escribe S (si) o cualquier otra tecla (no)");
            confirmar = in.nextLine().charAt(0);

            if (confirmar != 'S') {
                System.out.println("Abortando adicion del coche");
                continuar = false;
            }
        }

        //Creacion
        if (continuar) {
            Coche nuevoCoche = new Coche(color, marca);
            boolean exitoso = taller.anadeElemento(matricula, nuevoCoche);
            if (exitoso) {
                System.out.println("El coche ahora esta en tu taller");
            }
            else {
                System.out.println("El coche no se ha podido registrar en tu taller");
            }
        }
    }

    public static void quitarCoche(Taller taller) {
        String matricula;

        System.out.println("Vas a eliminar un coche en tu taller");

        //Matricula
        matricula = getCorrectMatricula();

        //Eliminacion
        boolean exitoso = taller.eliminaElemento(matricula);
        if (exitoso) {
            System.out.println("El coche se ha eliminado de tu taller");
        }
        else {
            System.out.println("El coche que tu intentas eliminar de tu taller no existe");
        }
    }

    public static void salir(Taller taller) {
        System.out.println("Saliendo de la taller");
        System.out.println(taller.visualizaMatriculas()+"\n");
        System.out.println(taller.visualizaCoches()+"\n");
        System.out.println(taller.visualizaTaller()+"\n");
    }
}