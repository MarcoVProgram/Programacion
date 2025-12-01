import java.util.Scanner;
import java.util.regex.Pattern;

public class GestionVideoDaw {
    public static void main(String[] args) {

        //Comienzo de codigo
        ColeccionVideoDaw LibreCoders = new ColeccionVideoDaw();
        registroDeVideoDaw(LibreCoders);
    }
    public static void registroDeVideoDaw(ColeccionVideoDaw LibreCoders) {
        Scanner in  = new Scanner(System.in);
        String direccion;
        String CIF;
        Pattern CIFform = Pattern.compile("[a-zA-Z][0-9]{8}");

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

        //Seleccion del Club

    }
}