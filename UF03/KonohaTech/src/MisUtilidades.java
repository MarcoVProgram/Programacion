import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MisUtilidades {

    //Comando para atajar al escribir texto
    public static void imprimir(String texto) {
        System.out.println(texto);
    }

    //Comando para hacer espera
    public static void esperar() {
        Scanner in = new Scanner(System.in);
        imprimir("=========================================");
        imprimir("Seleccione cualquier tecla para continuar");
        imprimir("=========================================");
        in.nextLine();
    }


    //Creacion Menu a traves de String
    public static void creacionMenu(String titulo, String[] opciones, String peticionDato) {
        String resultado = "\n"+ titulo + "\n";

        for (int i = 0; i < opciones.length; i++) {
            resultado += i+1 + ". " + opciones[i] + "\n";
        }

        resultado += "\n" + peticionDato;

        imprimir(resultado);
    }


    //Pide un dato por patron, repetir hasta lograr dato
    public static String pedirDatoConPatron(Pattern patron, String mensajeError) {
        Scanner in = new Scanner(System.in);
        String input;
        Matcher matcher;
        boolean isValido = false;

        do {
            input = in.nextLine();
            matcher = patron.matcher(input);
            if (matcher.matches()) {
                isValido = true;
            }
            if (!isValido) {
                imprimir(mensajeError);
            }
        } while (!isValido);

        return input;
    }

    //Metodo para poner fechas en patrones deseadoso
    public static String fechaFormato(String formato, Object fecha) {
        String fechaFormateada = "No hay Fecha";

        //Comprobar fecha existe
        if (fecha != null) {
            //Confirmar el Objeto es Temporal
            fechaFormateada = DateTimeFormatter.ofPattern(formato).format((TemporalAccessor) fecha);
        }

        return fechaFormateada;
    }
}
