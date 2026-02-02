import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

    public static void imprimir(String text) {
        System.out.println(text);
    }

    public static void imprimirSinSalto(String text) {
        System.out.print(text);
    }

    public static void esperar() {
        Scanner in = new Scanner(System.in);
        imprimir("=========================================");
        imprimir("Seleccione cualquier tecla para continuar");
        imprimir("=========================================");
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
    public static String inputRequestLoop(String inputMistake, Pattern[] pattern) {
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
                    break;
                }
            }
        } while (!valid);
        return input;
    }

    //Esta solo se usa para un unico patron
    public static String inputRequestLoop(String inputMistake, Pattern pattern) {
        Scanner in = new Scanner(System.in);
        String input;
        Matcher matcher;
        do {
            input = in.nextLine();
            matcher = pattern.matcher(input);
            if (!matcher.matches()) {
                imprimir(inputMistake);
            }
        } while (!matcher.matches());
        return input;
    }

    //Se pide el Dato, sin mayores complicaciones
    public static String inputRequest(Pattern pattern) throws InputIncorrectoException {
        Scanner in = new Scanner(System.in);
        String input;
        Matcher matcher;
        input = in.nextLine();
        matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new InputIncorrectoException(input);
        }
        return input;
    }

    //Peticion de Fecha
    public static LocalDate pedirFecha() {
        Pattern patronYear = Pattern.compile("[0-9]{4}");
        Pattern patronMes = Pattern.compile("[0-9]{2}");
        Pattern patronDia = Pattern.compile("[0-9]{2}");

        String fechaYear, fechaMes, fechaDia;
        boolean diaValido;

        //Insercion Anio (evitando caracteres que den problemas)
        do {
            try {
                imprimirSinSalto("\nAnio (year) de nacimiento: ");
                fechaYear = inputRequest(patronYear);
                if (Integer.parseInt(fechaYear) < 1800) {
                    imprimir("Rango de anio no Valido, ha de ser al menos del 1800, intentelo de nuevo:");
                }
            } catch (InputIncorrectoException e) {
                imprimir(e.getMessage());
                fechaYear = "0";
                imprimir("Introduce un numero de 4 digitos que al menos de 1800 hacia delante");
            }
        } while (Integer.parseInt(fechaYear) < 1800);

        //Insercion Mes
        do {
            try {
                imprimirSinSalto("\nMes (month) de nacimiento: ");
                fechaMes = inputRequest(patronMes);
                if (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12) {
                    imprimir("Rango de mes no Valido, tiene que ser del 1 al 12, intentelo de nuevo:");
                }
            } catch (InputIncorrectoException e) {
                imprimir(e.getMessage());
                fechaMes = "0";
                imprimir("Introduce un numero de 2 digitos que corresponda a un mes");
            }
        } while (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12);

        //Insercion Dia
        do {
            try {
                imprimirSinSalto("\nDia (day) de nacimiento: ");
                fechaDia = inputRequest(patronDia);
                switch (Integer.parseInt(fechaMes)) {
                    case 1, 3, 5, 7, 8, 10, 12:
                        diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 31;
                        break;
                    case 4, 6, 9, 11:
                        diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 30;
                        break;
                    case 2:
                        diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 28;
                        if (Year.isLeap(Long.parseLong(fechaYear))) {
                            diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 29;
                        }
                        break;
                    default:
                        throw new InputIncorrectoException(fechaDia);
                }
                if (diaValido) {
                    imprimir("Rango de dia no Valido, tiene que ser dependiendo de los dias del mes, intentelo de nuevo:");
                }
            } catch (InputIncorrectoException e) {
                imprimir(e.getMessage());
                fechaDia = "0";
                diaValido = true;
                imprimir("Introduce uno de 2 digitos que corresponda a un dia del mes");
            }
        } while (diaValido);

        //Condicion de Salida, Dia Valido sea False

        //Construccion final del Dia
        LocalDate dt = LocalDate.of(Integer.parseInt(fechaYear), Integer.parseInt(fechaMes), Integer.parseInt(fechaDia));

        return dt;
    }
}
