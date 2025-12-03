import java.util.Scanner;
import java.util.regex.Pattern;

public class GestionVideoDaw {
    public static void main(String[] args) {

        //Comienzo de codigo
        ColeccionVideoDaw LibreCoders = new ColeccionVideoDaw();
        registroDeVideoDaw(LibreCoders);
        Scanner sc;
        String inputMainMenu;
        int option;
        Pattern menuInputPattern = Pattern.compile("[1-8]");

        String[] menuGestionVideoDaw = new String[8];
        menuGestionVideoDaw[0] = "1. Crear y registrar VideoClub en la franquicia.";
        menuGestionVideoDaw[1] = "2. Registrar película en videoclub.";
        menuGestionVideoDaw[2] = "3. Crear y registrar cliente en videoclub.";
        menuGestionVideoDaw[3] = "4. Alquilar película.";
        menuGestionVideoDaw[4] = "5. Devolver película.";
        menuGestionVideoDaw[5] = "6. Dar de baja cliente.";
        menuGestionVideoDaw[6] = "7. Dar de baja película.";
        menuGestionVideoDaw[7] = "8. Salir (terminar programa)";
        do {
            sc = new Scanner(System.in);
            MyUtils.menuMaker("GESTION VIDEO DAW",menuGestionVideoDaw,"Introduce una opcion:");
            inputMainMenu = MyUtils.inputRequest("Input de Menu no Valido. Introduce un numero del 1 al 8",menuInputPattern);
            option = Integer.parseInt(inputMainMenu);
        } while (option != 8);
    }
    public static void registroDeVideoDaw(ColeccionVideoDaw LibreCoders) {
        Scanner in  = new Scanner(System.in);
        String direccion;
        String CIF;
        Pattern CIFform = Pattern.compile("[a-zA-Z][0-9]{8}");
        String seleccion;
        Pattern seleccionform = Pattern.compile("[1-9][0-9]*");

        MyUtils.imprimir("Registrando un Nuevo Videoclub");

        //CIF
        MyUtils.imprimir("Introduce el CIF (Codigo de Identificacion Fiscal)");
        CIF = MyUtils.inputRequest("El dato introducido no es un CIF valido. Usa una letra y 8 numeros",CIFform);


        //Direccion
        MyUtils.imprimir("Por favor, introduce la direccion del club");
        direccion = in.nextLine(); //Direccion no tiene estructura especifica

        //Creacion del CLub
        VideoDaw nuevoVideoClub = new VideoDaw(direccion,CIF);
        LibreCoders.addVideoClub(nuevoVideoClub);
        MyUtils.imprimir("Video Club registrado");

        //Seleccion de VideoClub
        if (LibreCoders.getNumRegistros() > 1) {
            MyUtils.imprimir("Existen mas de un VideoClub existence. Por favor, escoja el VideoCLub que desee usar, del 1 al " + LibreCoders.getNumRegistros());
            MyUtils.imprimir(LibreCoders.mostrarTodosVideoClub());
            do {
                seleccion = MyUtils.inputRequest("Valor introducido no valido. Intenta introducir el indice del VideoClub deseado",seleccionform);
                if (Integer.parseInt(seleccion) > nuevoVideoClub.getNumClientes()) {
                    MyUtils.imprimir("Has escogido un numero fuera del rango, intenta introducir el indice del VideoClub");
                }
            } while (Integer.parseInt(seleccion) > nuevoVideoClub.getNumClientes());
        }
    }
}