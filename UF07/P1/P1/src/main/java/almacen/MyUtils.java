package almacen;

//Dependencias a importar
import almacen.exceptions.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.InputMismatchException;
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

    //Metodo 6
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

                if (Integer.parseInt(fechaYear) < 1900) {
                    print("Rango de anio no Valido, ha de ser al menos del 1900, intentelo de nuevo:");
                }

                //Errores
            } catch (InputIncorrectoException e) {

                //Si no coincide con el patron
                print(e.getMessage());
                fechaYear = "0";
                print("Introduce un numero de 4 digitos que al menos sea de 1900 hacia delante");
            }
        } while (Integer.parseInt(fechaYear) < 1900);

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

    //Metodo 7
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

    //Metodo 8
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

    //Metodo 9
    //Peticion de String input hasta que sea correcto
    //@param pregunta por el dato que deseas obtener
    //@param patrones que debe cumplir, repetir hasta que se cumpla
    //@param inputmistake, mensaje error al no tener campo correcto
    //@return String con el patron devuelto al menos coincidiendo en uno
    public static String insertValidString(String query, Pattern pattern, String errorMsg) {
        String response;
        boolean valid = false;

        do {
            try {

                MyUtils.printHere(query + ": ");
                response = MyUtils.inputRequest(pattern);
                valid = true;

            }  catch (InputIncorrectoException e) {

                MyUtils.print(errorMsg);
                response = "";

            }
        } while (!valid);

        return response;
    }

    //Metodo 10
    //Peticion de double input hasta que sea aceptable
    //@param pregunta por el double que desees introducir
    //@param inputmistake, mensaje error al no tener campo correcto
    //@return double que este bien definido
    public static double insertValidDouble(String query, String errorMsg) {
        double response;
        boolean valid = false;
        Scanner in = new Scanner(System.in);

        do {
            try {

                MyUtils.printHere(query + ": ");
                in = new Scanner(System.in);
                response = in.nextDouble();
                valid = true;

            }  catch (InputMismatchException e) {

                MyUtils.print(errorMsg);
                response = 0.0;
                in.nextLine();

            }
        } while (!valid);

        return response;
    }

    //Metodo 10
    //Peticion de double input hasta que sea aceptable
    //@param pregunta por el double que desees introducir
    //@param inputmistake, mensaje error al no tener campo correcto
    //@param numero de decimales que este patron va a tener, se desprecian el resto
    //@return double que este bien definido
    public static double insertValidDouble(String query, String errorMsg, double decimalDigits) {
        double response;
        boolean valid = false;
        Scanner in = new Scanner(System.in);

        do {
            try {

                MyUtils.printHere(query + ": ");
                in = new Scanner(System.in);
                response = in.nextDouble();
                response = Math.floor(response * Math.pow(10,decimalDigits))/Math.pow(10,decimalDigits);
                valid = true;

            }  catch (InputMismatchException e) {

                MyUtils.print(errorMsg);
                response = 0.0;
                in.nextLine();

            }
        } while (!valid);

        return response;
    }

    //Metodo 11
    //Peticion de int input hasta que sea aceptable
    //@param pregunta por el int que desees introducir
    //@param inputmistake, mensaje error al no tener int correcto
    //@return double que este bien definido
    public static int insertValidInt(String query, String errorMsg) {
        int response;
        boolean valid = false;
        Scanner in = new Scanner(System.in);

        do {
            try {

                MyUtils.printHere(query + ": ");
                in = new Scanner(System.in);
                response = in.nextInt();
                valid = true;

            }  catch (InputMismatchException e) {

                MyUtils.print(errorMsg);
                response = 0;
                in.nextLine();

            }
        } while (!valid);

        return response;
    }

    //Metodo 12
    //Peticion de int input hasta que sea aceptable
    //@param pregunta por el int que desees introducir
    //@param inputmistake, mensaje error al no tener int correcto
    //@param valor minimo
    //@param valor maximo
    //@return double que este bien definido
    public static int insertValidInt(String query, String errorMsg, int minRange, int maxRange) {
        int response;
        boolean valid = false;
        Scanner in = new Scanner(System.in);

        do {
            try {

                MyUtils.printHere(query + " (Debe ser Entre: " + minRange + " - " + maxRange + "): ");
                in = new Scanner(System.in);
                response = in.nextInt();
                valid = response >= minRange && response <= maxRange;

            }  catch (InputMismatchException e) {

                MyUtils.print(errorMsg);
                response = 0;
                in.nextLine();

            }
        } while (!valid);

        return response;
    }

    //Metodo 13
    //Peticion de int input o tomar un int por defecto
    //@param pregunta por el int que desees introducir
    //@param inputDefault cuando no se ha escrito un valor correcto
    //@return int correcto o no int
    public static int insertInt(String query, int inputDefault) {
        int response;
        Scanner in = new Scanner(System.in);

        try {

            MyUtils.printHere(query + " (Valor por Defecto: " + inputDefault + "): ");
            in = new Scanner(System.in);
            response = in.nextInt();

        }  catch (InputMismatchException e) {

            response = inputDefault;
            in.nextLine();

        }

        return response;
    }

    //Metodo 14
    //Peticion de un index (util para ENUMs). El valor devuleto es entre 1 y length (sustraer 1 si deseas entre 0 y length-1)
    //@param length del cual realizar una seleccion
    //@return indice
    public static Enum selectEnum(Class enumClass) {
        Enum[] valuesEnum = (Enum[]) enumClass.getEnumConstants();
        Enum selection = valuesEnum[0];

        MyUtils.print("\n" + enumClass.getSimpleName() + ":");
        for (Enum e : valuesEnum) {

            MyUtils.print(e.ordinal() + ". " + e.name());
        }

        int index = insertInt("Introduce el Indice de una opcion ", 0);

        for (Enum e : valuesEnum) {
            if (e.ordinal() == index) {
                selection = e;
            }
        }

        return selection;
    }

    //Metodo 15
    //Realizacion de una pregunta a responder de si o no
    //@param texto de la pregunta
    //@return booleano si la respuesta es si o no
    public static boolean confirm(String question) {
        Scanner scanner  = new Scanner(System.in);

        MyUtils.printHere(question + "(Escribe la letra 'S' si la respuesta es Si, o cualquier otra tecla para No): ");

        if  (scanner.nextLine().equalsIgnoreCase("S")) {
            return true;
        }

        return false;
    }
}
