import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //Patrones
    public final static Pattern PATRONMENU = Pattern.compile("[1-6]");//Solo del 1 al 6
    public final static Pattern PATRONNOMBRE = Pattern.compile("[A-Z][a-z A-Z]+"); //Solo letras y espacios
    public final static Pattern PATRONTELEFONO = Pattern.compile("[679][0-9]{8}");//Un numero de 9 digitos que empieza por 6,7 o 9
    public final static Pattern PATRONCORREO = Pattern.compile("[a-z-._0-9]+@[a-z]+[.][a-z]{2,4}");//Correo valido

    public static void main(String[] args) {

        //Objetos
        Agenda miAgenda = new Agenda();
        Scanner sc;
        Matcher matcherMenu;

        //Variables
        String inputMenu;
        char option;

        System.out.println("Bienvenido a Agenda\n");
        do {
            System.out.println("Menu");
            System.out.println(
                    "1. Anadir Contacto\n" +
                    "2. Buscar Contacto\n" +
                    "3. Eliminar Contacto\n" +
                    "4. Visualizar Agenda\n" +
                    "5. Numero de Contactos de mi Agenda\n" +
                    "6. Salir\n");
            System.out.print("Por Favor, Escoge el numero de una opcion: ");
            do {
                sc = new Scanner(System.in);
                inputMenu = sc.nextLine();
                matcherMenu = PATRONMENU.matcher(inputMenu);
                if (!matcherMenu.matches()) {
                    System.out.println("Opcion invalida, intenta usar un numero del 1 al 6:");
                }
            } while (!matcherMenu.matches());
            option = inputMenu.charAt(0);

            switch (option) {
                case '1':
                    //Anadir Contacto
                    System.out.println();
                    crearContactoMenu(miAgenda);
                    pausa();
                    break;
                case '2':
                    //Buscar Contacto
                    System.out.println();
                    mostrarContactoMenu(miAgenda);
                    pausa();
                    break;
                case '3':
                    //Eliminar Contacto
                    System.out.println();
                    eliminarContactoMenu(miAgenda);
                    pausa();
                    break;
                case '4':
                    //Ver toda la Agenda
                    System.out.println();
                    System.out.println("Mostrando toda la Agenda:");
                    System.out.println(miAgenda.visualizaAgenda());
                    pausa();
                    break;
                case '5':
                    //Ver numero total Contactos
                    System.out.println();
                    System.out.println("Tu Agenda tiene " + miAgenda.getNumeroContactos() + " contactos");
                    pausa();
                    break;
                case '6':
                    //Salir
                    System.out.println();
                    System.out.println("Saliendo del Programa");
                    break;
                default:
                    //Error, no deberia ocurrir nunca
                    System.out.println();
                    System.out.println("Algo fue mal, Por Favor, Comunicaselo a un Desarrollador");
                    break;
            }
        } while(option != '6');
        sc.close();
    }

    //Metodos
    public static void pausa() {
        Scanner in = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("Pulse cualquier tecla para continuar");
        System.out.println("====================================");
        in.nextLine();
    }

    public static void crearContactoMenu(Agenda agenda) {
        Scanner in;
        String nombre,telefono,correo;
        Matcher matcherNombre,
                matcherTelefono,
                matcherCorreo;

        System.out.println("Has seleccionado la opcion de anadir contacto");

        System.out.print("Por Favor, ingresa el nombre del contacto: ");
        do {
            in = new Scanner(System.in);
            nombre = in.nextLine();
            matcherNombre = PATRONNOMBRE.matcher(nombre);
            if (!matcherNombre.matches()) {
                System.out.println("El nombre del contacto no es valido, usa solo letras y pon la primera em mayusculas:");
            }
        }  while (!matcherNombre.matches());

        System.out.print("Por Favor, ingresa el telefono del contacto: ");
        do {
            in = new Scanner(System.in);
            telefono = in.nextLine();
            matcherTelefono = PATRONTELEFONO.matcher(telefono);
            if (!matcherTelefono.matches()) {
                System.out.println("El numero de telefono no es valido. Escribe un numero de 9 digitos que empiece por 6,7 o 9");
            }
        }  while (!matcherTelefono.matches());

        System.out.print("Por Favor, ingresa el correo del contacto: ");
        do {
            in = new Scanner(System.in);
            correo = in.nextLine();
            matcherCorreo = PATRONCORREO.matcher(correo);
            if (!matcherCorreo.matches()) {
                System.out.println("El correo dado no es valido, usa caracteres alfanumericos asi como ._- para el cuerpo, " +
                        " un @ para el dominio, solo letras para el nombre del servicio, un . y dos a cuatro letras mas para la extension:");
            }
        }  while (!matcherCorreo.matches());

        Contacto contactoNuevo = new Contacto(nombre, telefono, correo);
        boolean anadirContacto = agenda.anadeContacto(contactoNuevo);

        if (anadirContacto) {
            System.out.println("Contacto anadido con exito");
        }
        else {
            System.out.println("Contacto no se ha podido anadir, revisa que no exista ya");
        }
    }

    public static void mostrarContactoMenu(Agenda agenda) {
        Scanner in;
        String nombre;
        Matcher matcherNombre;

        System.out.println("Has seleccionado la opcion de buscar contacto");

        System.out.print("Por Favor, ingresa el nombre del contacto: ");
        do {
            in = new Scanner(System.in);
            nombre = in.nextLine();
            matcherNombre = PATRONNOMBRE.matcher(nombre);
            if (!matcherNombre.matches()) {
                System.out.println("El nombre del contacto no es valido, usa solo letras y pon la primera em mayusculas:");
            }
        }  while (!matcherNombre.matches());

        Contacto contactoEncontrado = agenda.buscaContacto(nombre);

        if (contactoEncontrado != null) {
            System.out.println("Contacto encontrado:\n");
            System.out.println(contactoEncontrado.toString());
        }
        else {
            System.out.println("No se ha podido encontrar ese contacto");
        }
    }

    public static void eliminarContactoMenu(Agenda agenda) {
        Scanner in;
        String nombre;
        Matcher matcherNombre;

        System.out.println("Has seleccionado la opcion de eliminar contacto");

        System.out.print("Por Favor, ingresa el nombre del contacto: ");
        do {
            in = new Scanner(System.in);
            nombre = in.nextLine();
            matcherNombre = PATRONNOMBRE.matcher(nombre);
            if (!matcherNombre.matches()) {
                System.out.println("El nombre del contacto no es valido, usa solo letras y pon la primera em mayusculas:");
            }
        }  while (!matcherNombre.matches());

        Contacto contactoAEliminar = agenda.buscaContacto(nombre);

        if (agenda.eliminaContacto(contactoAEliminar)) {
            System.out.println("Contacto eliminado de forma exitosa");
        }
        else {
            System.out.println("No se ha encontrado dicho contacto a eliminar");
        }
    }
}