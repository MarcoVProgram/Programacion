import java.time.LocalDate;
import java.time.Year;
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

    //Insert a Date between two given years
    public static LocalDate requestDate(int minYear, int maxYear) {
        Pattern yearPattern = Pattern.compile("[0-9]{4}");
        Pattern daymonthPattern = Pattern.compile("[0-9]{1,2}");

        String dateYear, dateMonth, dateDay;
        boolean isDayInvalid;

        //Inserting Year
        print("Insert the year:");
        do {
            dateYear = requestDataWithPattern(yearPattern,"Year chosen not valid, try at least make it a 4 digit number:");
            if (Integer.parseInt(dateYear) < minYear || Integer.parseInt(dateYear) > maxYear) {
                MyUtils.print("Range of years not valid, please try a number between the years " + minYear + " and " + maxYear +":");
            }
        } while (Integer.parseInt(dateYear) < minYear || Integer.parseInt(dateYear) > maxYear);

        //Inserting Month
        MyUtils.print("Insert the month:");
        do {
            dateMonth = requestDataWithPattern(daymonthPattern,"Month inserted not valid, try at least make it 1 or 2 digits long:");
            if (Integer.parseInt(dateMonth) < 1 || Integer.parseInt(dateMonth) > 12) {
                MyUtils.print("Range of month not valid, please try a number between 1 and 12:");
            }
        } while (Integer.parseInt(dateMonth) < 1 || Integer.parseInt(dateMonth) > 12);

        //Inserting Day
        MyUtils.print("Insert the day:");
        do {
            dateDay = requestDataWithPattern(daymonthPattern,"Day introduced is not valid, try a number between 1 and 31:");
            switch (Integer.parseInt(dateMonth)) {
                case 1,3,5,7,8,10,12:
                    isDayInvalid = Integer.parseInt(dateDay) < 1 || Integer.parseInt(dateDay) > 31;
                    break;
                case 4,6,9,11:
                    isDayInvalid = Integer.parseInt(dateDay) < 1 || Integer.parseInt(dateDay) > 30;
                    break;
                case 2:
                    isDayInvalid = Integer.parseInt(dateDay) < 1 || Integer.parseInt(dateDay) > 28;
                    if (Year.isLeap(Long.parseLong(dateYear))) {
                        isDayInvalid = Integer.parseInt(dateDay) < 1 || Integer.parseInt(dateDay) > 29;
                    }
                    break;
                default:
                    MyUtils.print("There has been a mistake, report this error to a developer");
                    isDayInvalid = true;
                    break;
            }
            if (isDayInvalid) {
                MyUtils.print("Range od day is not valid, please verify that day is part of the chosen month:");
            }
        } while (isDayInvalid);

        //To exit, isDayInvalid must be false.

        //Final construction of the date
        LocalDate dt = LocalDate.of(Integer.parseInt(dateYear), Integer.parseInt(dateMonth), Integer.parseInt(dateDay));

        return dt;
    }
}