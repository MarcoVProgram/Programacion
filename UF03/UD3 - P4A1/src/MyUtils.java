import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

    //Writes text
    public static void print(String text) {
        System.out.println(text);
    }

    //Creates a press to continue
    public static void pause() {
        Scanner in = new Scanner(System.in);
        print("==========================================");
        print("Press any key to continue with the program");
        print("==========================================");
        in.nextLine();
    }


    //Creates menu
    public static void createMenu(String title, String[] options, String dataRequest) {
        String resultado = "\n"+ title + "\n";

        for (int i = 0; i < options.length; i++) {
            resultado += i+1 + ". " + options[i] + "\n";
        }

        resultado += "\n" + dataRequest;

        print(resultado);
    }


    //Method to request Data
    public static String requestDataWithPattern(Pattern p, String errorMsg) {
        Scanner in = new Scanner(System.in);
        String input;
        Matcher matcher;
        boolean isValid = false;

        do {
            input = in.nextLine();
            matcher = p.matcher(input);
            if (matcher.matches()) {
                isValid = true;
            }
            if (!isValid) {
                print(errorMsg);
            }
        } while (!isValid);

        return input;
    }

    //Methods to format dates
    public static String formatDate(String format, Object date) {
        String formattedDate = "No hay Fecha";

        //Checks if it exists
        if (date != null) {
            //Checks if it is a temporal object
            formattedDate = DateTimeFormatter.ofPattern(format).format((TemporalAccessor) date);
        }

        return formattedDate;
    }
}