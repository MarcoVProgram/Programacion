//Dependencias a importar
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Clase de comodidades
public class MyUtils {

    //Metodo 1
    //Acortar Display en Consola
    public static void print(String text) {

        System.out.println(text);
    }

    //Metodo 2
    //Acortar display en Consola sin salto de Linea
    public static void printHere(String text) {

        System.out.print(text);
    }

    //Metodo 3
    //Pausa antes de continuar
    public static void pause() {

        Scanner in = new Scanner(System.in);
        print("=========================================");
        print("Seleccione cualquier tecla para continuar");
        print("=========================================");
        in.nextLine();
    }

    //Metodo 4
    //Creacion de menu
    //@param title, titulo del menu
    //@param options, array con las opciones de menu, el metodo las enumera
    //@param inputrequest, peticion del dato
    //@return
    public static void menuMaker(String title, String[] options, String inputRequest) {

        String resultado = "\n"+ title + "\n";

        for (int i = 0; i < options.length; i++) {
            resultado += i+1 + ". " + options[i] + "\n";
        }

        print(resultado);
        printHere(inputRequest);
    }

    //Metodo 5
    //Peticion de String input hasta que sea correcto
    //@param pattern array de patrones compilado por clase pattern
    //@param inputmistake, mensaje error al no tener nombre correcto
    //@return String con el patron devuelto al menos coincidiendo en uno
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

    //Metodo 6
    //Peticion de String input hasta que sea correcto
    //@param pattern, patron compilado por clase pattern
    //@param inputmistake, mensaje error al no tener nombre correcto
    //@return String con el patron devuelto
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

    //Metodo 7
    //Peticion de String input, InputIncorrectoException si no coincide
    //@param pattern, patron compilado por clase pattern
    //@return String con el patron devuelto, o Exception
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

    //Metodo 8
    //Peticion de una Fecha de cumpleanios de al menos anio 1800
    //@return LocalDate año creado
    public static LocalDate requestBirthday() {

        Pattern patronYear = Pattern.compile("[0-9]{4}");
        Pattern patronMes = Pattern.compile("[0-9]{2}");
        Pattern patronDia = Pattern.compile("[0-9]{2}");

        String fechaYear, fechaMes, fechaDia;
        boolean diaValido;

        //Insercion Año (evitando caracteres que den problemas)
        do {

            try {

                printHere("\nAnio (year) de nacimiento: ");
                fechaYear = inputRequest(patronYear);

                if (Integer.parseInt(fechaYear) < 1800) {
                    print("Rango de anio no Valido, ha de ser al menos del 1800, intentelo de nuevo:");
                }

                //Errores
            } catch (InputIncorrectoException e) {

                //Si no coincide con el patron
                print(e.getMessage());
                fechaYear = "0";
                print("Introduce un numero de 4 digitos que al menos sea de 1800 hacia delante");
            }
        } while (Integer.parseInt(fechaYear) < 1800);

        //Insercion Mes
        do {

            try {

                printHere("\nMes (month) de nacimiento: ");
                fechaMes = inputRequest(patronMes);

                if (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12) {
                    print("Rango de mes no Valido, tiene que ser del 1 al 12, intentelo de nuevo:");
                }

                //Errores
            } catch (InputIncorrectoException e) {

                //Si no coincide con el patron
                print(e.getMessage());
                fechaMes = "0";
                print("Introduce un numero de 2 digitos que corresponda a un mes");
            }
        } while (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12);

        //Insercion Dia
        do {

            try {

                printHere("\nDia (day) de nacimiento: ");
                fechaDia = inputRequest(patronDia);

                //Combrobacion del Dia valido para el mes seleccionado
                switch (Integer.parseInt(fechaMes)) {

                    //Caso meses de 31 dias
                    case 1, 3, 5, 7, 8, 10, 12:
                        diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 31;
                        break;

                    //Caso meses de 30 dias
                    case 4, 6, 9, 11:

                        diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 30;
                        break;

                    //Caso meses de 28 o 29 dias, leyendo bisiestos
                    case 2:

                        diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 28;

                        if (Year.isLeap(Long.parseLong(fechaYear))) {
                            diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 29;
                        }
                        break;

                    default:
                        throw new InputIncorrectoException(fechaDia);
                }

                //Dia no es valido cuando diaValido = true
                if (diaValido) {
                    print("Rango de dia no Valido, tiene que ser dependiendo de los dias del mes, intentelo de nuevo:");
                }

                //Errores
            } catch (InputIncorrectoException e) {

                //Datos del dia mal introducidos
                print(e.getMessage());
                fechaDia = "0";
                diaValido = true;
                print("Introduce uno de 2 digitos que corresponda a un dia del mes");
            }
        } while (diaValido);

        //Condicion de Salida, Dia Valido sea False

        //Construccion final del Dia
        LocalDate dt = LocalDate.of(Integer.parseInt(fechaYear), Integer.parseInt(fechaMes), Integer.parseInt(fechaDia));

        return dt;
    }

    //Metodo 8
    //Peticion de una Fecha de al menos 4 digitos
    //@return LocalDate año creado
    public static LocalDate requestDate() {

        Pattern patronYear = Pattern.compile("[0-9]{4}");
        Pattern patronMes = Pattern.compile("[0-9]{2}");
        Pattern patronDia = Pattern.compile("[0-9]{2}");

        String fechaYear, fechaMes, fechaDia;
        boolean diaValido;

        //Insercion Año (evitando caracteres que den problemas)
        do {

            try {

                printHere("\nSelecciona el Anio (year): ");
                fechaYear = inputRequest(patronYear);

                //Errores
            } catch (InputIncorrectoException e) {

                //Si no coincide con el patron
                print(e.getMessage());
                fechaYear = "0";
                print("Introduce un numero de 4 digitos");
            }
        } while (Integer.parseInt(fechaYear) < 1000);

        //Insercion Mes
        do {

            try {

                printHere("\nSelecciona el Mes (month): ");
                fechaMes = inputRequest(patronMes);

                if (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12) {
                    print("Rango de mes no Valido, tiene que ser del 1 al 12, intentelo de nuevo:");
                }

                //Errores
            } catch (InputIncorrectoException e) {

                //Si no coincide con el patron
                print(e.getMessage());
                fechaMes = "0";
                print("Introduce un numero de 2 digitos que corresponda a un mes");
            }
        } while (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12);

        //Insercion Dia
        do {

            try {

                printHere("\nSelecciona un Dia (day): ");
                fechaDia = inputRequest(patronDia);

                //Combrobacion del Dia valido para el mes seleccionado
                switch (Integer.parseInt(fechaMes)) {

                    //Caso meses de 31 dias
                    case 1, 3, 5, 7, 8, 10, 12:
                        diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 31;
                        break;

                    //Caso meses de 30 dias
                    case 4, 6, 9, 11:

                        diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 30;
                        break;

                    //Caso meses de 28 o 29 dias, leyendo bisiestos
                    case 2:

                        diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 28;

                        if (Year.isLeap(Long.parseLong(fechaYear))) {
                            diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 29;
                        }
                        break;

                    default:
                        throw new InputIncorrectoException(fechaDia);
                }

                //Dia no es valido cuando diaValido = true
                if (diaValido) {
                    print("Rango de dia no Valido, tiene que ser dependiendo de los dias del mes, intentelo de nuevo:");
                }

                //Errores
            } catch (InputIncorrectoException e) {

                //Datos del dia mal introducidos
                print(e.getMessage());
                fechaDia = "0";
                diaValido = true;
                print("Introduce uno de 2 digitos que corresponda a un dia del mes");
            }
        } while (diaValido);

        //Condicion de Salida, Dia Valido sea False

        //Construccion final del Dia
        LocalDate dt = LocalDate.of(Integer.parseInt(fechaYear), Integer.parseInt(fechaMes), Integer.parseInt(fechaDia));

        return dt;
    }

    //Metodo 9
    //Metodo para poner fechas en patrones deseadoso
    //@param string con el patron para la fecha deseado
    //@param objeto de acceso temporal fecha
    //@return String de fecha formateada, o N/A si invalida o nula
    public static String formatDate(String pattern, Object fecha) {
        String formattedResult = "N/A";

        //Comprobar fecha existe
        if (fecha != null && fecha instanceof TemporalAccessor) {
            //Confirmar el Objeto es Temporal
            formattedResult = DateTimeFormatter.ofPattern(pattern).format((TemporalAccessor) fecha);
        }

        return formattedResult;
    }
}