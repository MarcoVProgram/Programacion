//Dependencias a importar
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase de utilidades que proporciona métodos auxiliares para operaciones comunes.
 *
 * Esta clase contiene métodos estáticos de propósito general para simplificar tareas
 * frecuentes como entrada/salida en consola, validación de patrones, manejo de menús
 * y procesamiento de fechas.
 *
 * <p>Todos los métodos de esta clase son estáticos y pueden ser invocados sin necesidad
 * de instanciar la clase.</p>
 *
 * @author Marco
 * @version 1.0
 * @since 1.0
 */
public class MyUtils {

    /**
     * Imprime un texto en la consola seguido de un salto de línea.
     *
     * <p>Este método actúa como un atajo para {@link System#out#println(String)}.</p>
     *
     * @param text el texto a imprimir en consola
     */
    public static void print(String text) {
        System.out.println(text);
    }

    /**
     * Imprime un texto en la consola sin salto de línea.
     *
     * <p>Este método actúa como un atajo para {@link System#out#print(String)}, permitiendo
     * que el siguiente texto se imprima en la misma línea.</p>
     *
     * @param text el texto a imprimir en consola
     */
    public static void printHere(String text) {
        System.out.print(text);
    }

    /**
     * Pausa la ejecución del programa hasta que el usuario presione cualquier tecla.
     *
     * <p>Muestra un mensaje en pantalla solicitando al usuario que seleccione una tecla
     * para continuar.</p>
     */
    public static void pause() {
        Scanner in = new Scanner(System.in);
        print("=========================================");
        print("Seleccione cualquier tecla para continuar");
        print("=========================================");
        in.nextLine();
    }

    /**
     * Genera y muestra un menú en consola con un título y opciones numeradas.
     *
     * <p>Las opciones se enumeran automáticamente comenzando desde el 1. El método
     * imprime el título, las opciones numeradas y solicita la entrada del usuario.</p>
     *
     * @param title el título del menú a mostrar
     * @param options un array de cadenas con las opciones del menú
     * @param inputRequest el mensaje solicitando la entrada del usuario
     */
    public static void menuMaker(String title, String[] options, String inputRequest) {
        String resultado = "\n" + title + "\n";

        for (int i = 0; i < options.length; i++) {
            resultado += i + 1 + ". " + options[i] + "\n";
        }

        print(resultado);
        printHere(inputRequest);
    }

    /**
     * Solicita entrada de texto al usuario en un bucle hasta que coincida con uno de los patrones.
     *
     * <p>Valida la entrada contra un array de patrones compilados. Si la entrada no coincide
     * con ningún patrón, muestra un mensaje de error y solicita nuevamente la entrada.</p>
     *
     * @param pattern un array de objetos {@link Pattern} compilados para validar la entrada
     * @param inputMistake el mensaje de error a mostrar si la entrada no es válida
     * @return la entrada del usuario validada que coincide con al menos uno de los patrones
     */
    public static String inputRequestLoop(Pattern[] pattern, String inputMistake) {
        Scanner in = new Scanner(System.in);
        String input;
        Matcher matcher[] = new Matcher[pattern.length];
        boolean valid = true;

        do {
            input = in.nextLine();

            if (!valid) {
                print(inputMistake);
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

    /**
     * Solicita entrada de texto al usuario en un bucle hasta que coincida con el patrón especificado.
     *
     * <p>Valida la entrada contra un patrón compilado. Si la entrada no coincide con el patrón,
     * muestra un mensaje de error y solicita nuevamente la entrada.</p>
     *
     * @param pattern un objeto {@link Pattern} compilado para validar la entrada
     * @param inputMistake el mensaje de error a mostrar si la entrada no es válida
     * @return la entrada del usuario validada que coincide con el patrón
     */
    public static String inputRequestLoop(Pattern pattern, String inputMistake) {
        Scanner in = new Scanner(System.in);
        String input;
        Matcher matcher;

        do {
            input = in.nextLine();
            matcher = pattern.matcher(input);

            if (!matcher.matches()) {
                print(inputMistake);
            }
        } while (!matcher.matches());
        return input;
    }

    /**
     * Solicita una única entrada de texto al usuario y la valida contra un patrón.
     *
     * <p>Si la entrada no coincide con el patrón especificado, lanza una excepción
     * {@link InputIncorrectoException}.</p>
     *
     * @param pattern un objeto {@link Pattern} compilado para validar la entrada
     * @return la entrada del usuario validada que coincide con el patrón
     * @throws InputIncorrectoException si la entrada no coincide con el patrón especificado
     */
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

    /**
     * Solicita al usuario una fecha de nacimiento válida (año >= 1900).
     *
     * <p>Este método solicita el año, mes y día de forma individual, validando cada
     * componente. El año debe ser mayor o igual a 1900, el mes debe estar entre 1 y 12,
     * y el día debe ser válido para el mes seleccionado, considerando años bisiestos
     * para el mes de febrero.</p>
     *
     * @return un objeto {@link LocalDate} representando la fecha de nacimiento válida
     */
    public static LocalDate requestBirthday() {
        Pattern patronYear = Pattern.compile("[0-9]{4}");
        Pattern patronMes = Pattern.compile("[0-9]{2}");
        Pattern patronDia = Pattern.compile("[0-9]{2}");

        String fechaYear, fechaMes, fechaDia;
        boolean diaValido;

        // Inserción Año (evitando caracteres que den problemas)
        do {
            try {
                printHere("\nAnio (year) de nacimiento: ");
                fechaYear = inputRequest(patronYear);

                if (Integer.parseInt(fechaYear) < 1900) {
                    print("Rango de anio no Valido, ha de ser al menos del 1900, intentelo de nuevo:");
                }
            } catch (InputIncorrectoException e) {
                print(e.getMessage());
                fechaYear = "0";
                print("Introduce un numero de 4 digitos que al menos sea de 1900 hacia delante");
            }
        } while (Integer.parseInt(fechaYear) < 1900);

        // Inserción Mes
        do {
            try {
                printHere("\nMes (month) de nacimiento: ");
                fechaMes = inputRequest(patronMes);

                if (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12) {
                    print("Rango de mes no Valido, tiene que ser del 1 al 12, intentelo de nuevo:");
                }
            } catch (InputIncorrectoException e) {
                print(e.getMessage());
                fechaMes = "0";
                print("Introduce un numero de 2 digitos que corresponda a un mes");
            }
        } while (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12);

        // Inserción Día
        do {
            try {
                printHere("\nDia (day) de nacimiento: ");
                fechaDia = inputRequest(patronDia);

                // Comprobación del Día válido para el mes seleccionado
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
                    print("Rango de dia no Valido, tiene que ser dependiendo de los dias del mes, intentelo de nuevo:");
                }
            } catch (InputIncorrectoException e) {
                print(e.getMessage());
                fechaDia = "0";
                diaValido = true;
                print("Introduce uno de 2 digitos que corresponda a un dia del mes");
            }
        } while (diaValido);

        LocalDate dt = LocalDate.of(Integer.parseInt(fechaYear), Integer.parseInt(fechaMes), Integer.parseInt(fechaDia));
        return dt;
    }

    /**
     * Solicita al usuario una fecha válida (año >= 1000).
     *
     * <p>Este método solicita el año, mes y día de forma individual, validando cada
     * componente. El año debe ser mayor o igual a 1000, el mes debe estar entre 1 y 12,
     * y el día debe ser válido para el mes seleccionado, considerando años bisiestos
     * para el mes de febrero.</p>
     *
     * @return un objeto {@link LocalDate} representando la fecha válida seleccionada
     */
    public static LocalDate requestDate() {
        Pattern patronYear = Pattern.compile("[0-9]{4}");
        Pattern patronMes = Pattern.compile("[0-9]{2}");
        Pattern patronDia = Pattern.compile("[0-9]{2}");

        String fechaYear, fechaMes, fechaDia;
        boolean diaValido;

        // Inserción Año (evitando caracteres que den problemas)
        do {
            try {
                printHere("\nSelecciona el Anio (year): ");
                fechaYear = inputRequest(patronYear);
            } catch (InputIncorrectoException e) {
                print(e.getMessage());
                fechaYear = "0";
                print("Introduce un numero de 4 digitos");
            }
        } while (Integer.parseInt(fechaYear) < 1000);

        // Inserción Mes
        do {
            try {
                printHere("\nSelecciona el Mes (month): ");
                fechaMes = inputRequest(patronMes);

                if (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12) {
                    print("Rango de mes no Valido, tiene que ser del 1 al 12, intentelo de nuevo:");
                }
            } catch (InputIncorrectoException e) {
                print(e.getMessage());
                fechaMes = "0";
                print("Introduce un numero de 2 digitos que corresponda a un mes");
            }
        } while (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12);

        // Inserción Día
        do {
            try {
                printHere("\nSelecciona un Dia (day): ");
                fechaDia = inputRequest(patronDia);

                // Comprobación del Día válido para el mes seleccionado
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
                    print("Rango de dia no Valido, tiene que ser dependiendo de los dias del mes, intentelo de nuevo:");
                }
            } catch (InputIncorrectoException e) {
                print(e.getMessage());
                fechaDia = "0";
                diaValido = true;
                print("Introduce uno de 2 digitos que corresponda a un dia del mes");
            }
        } while (diaValido);

        LocalDate dt = LocalDate.of(Integer.parseInt(fechaYear), Integer.parseInt(fechaMes), Integer.parseInt(fechaDia));
        return dt;
    }

    /**
     * Formatea un objeto de fecha temporal según el patrón especificado.
     *
     * <p>Convierte un objeto que implemente {@link TemporalAccessor} a una cadena formateada
     * según el patrón proporcionado. Si el objeto es null o no es una instancia válida de
     * {@link TemporalAccessor}, retorna "N/A".</p>
     *
     * @param pattern una cadena con el patrón de formato de fecha (ej: "dd/MM/yyyy")
     * @param fecha un objeto {@link TemporalAccessor} que representa la fecha a formatear
     * @return una cadena con la fecha formateada según el patrón, o "N/A" si la fecha es inválida
     * @see DateTimeFormatter#ofPattern(String)
     */
    public static String formatDate(String pattern, Object fecha) {
        String formattedResult = "N/A";

        if (fecha != null && fecha instanceof TemporalAccessor) {
            formattedResult = DateTimeFormatter.ofPattern(pattern).format((TemporalAccessor) fecha);
        }

        return formattedResult;
    }
}
