import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

    public static void imprimir(String text) {
        System.out.println(text);
    }

    public static void esperar() {
        Scanner in = new Scanner(System.in);
        imprimir("Seleccione cualquier tecla para continuar");
        in.nextLine();
    }

    public static void menuMaker(String title, String[] options, String inputRequest) {
        String resultado = "\n"+ title + "\n";
        for (int i = 0; i < options.length; i++) {
            resultado += i+1 + ". " + options[i] + "\n";
        }
        resultado += "\n" + inputRequest;
        imprimir(resultado);
    }

    //Esta solo se usa para multiples patrones
    public static String inputRequest(String inputMistake, Pattern[] pattern) {
        Scanner in = new Scanner(System.in);
        String input;
        Matcher matcher[] = new Matcher[pattern.length];
        boolean valid = true;
        do {
            input = in.nextLine();
            if (!valid) {
                imprimir(inputMistake);
            }
            valid = false;
            for (int i = 0; i < matcher.length; i++) {
                matcher[i] = pattern[i].matcher(input);
                if (matcher[i].matches()) {
                    valid = true;
                }
            }
        } while (!valid);
        return input;
    }

    //Esta solo se usa para un unico patron
    public static String inputRequest(String inputMistake, Pattern pattern) {
        Scanner in = new Scanner(System.in);
        String input;
        Matcher matcher;
        boolean valid = false;
        do {
            input = in.nextLine();
            matcher = pattern.matcher(input);
            if (matcher.matches()) {
                valid = true;
            }
            if (!valid) {
                imprimir(inputMistake);
            }
        } while (!valid);
        return input;
    }

    //Metodo para poner fechas en patrones deseadoso
    public static String formatDate(String pattern, Object fecha) {
        String formattedResult = "N/A";
        if (fecha != null) {
            formattedResult = DateTimeFormatter.ofPattern(pattern).format((TemporalAccessor) fecha);
        }
        return formattedResult;
    }
}