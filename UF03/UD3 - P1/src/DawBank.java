import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DawBank {
    public static void main(String[] args) {

        //Inicio del programa
        Scanner sc;
        int cuentaActual = 0;
        Banco dawBank = new Banco("dawBank", "39007");
        boolean exito = dawBank.crearCuentaBancaria(new CuentaBancaria(pedirCuenta()));
        if  (exito == true) {
            MyUtils.imprimir("Cuenta creada exitosamente");
        }
        else {
            MyUtils.imprimir("Ha ocurrido un error al crear la cuenta");
        }
        String rawInput;
        char option;
        String menuPrincipal[] = new String[8];
        menuPrincipal[0] = "Crear Cuenta Bancaria";
        menuPrincipal[1] = "Listar Cuentas Bancarias";
        menuPrincipal[2] = "Seleccionar Cuenta Bancaria";
        menuPrincipal[3] = "Buscar Transaccion";
        menuPrincipal[4] = "Hacer Transaccion";
        menuPrincipal[5] = "Ver Saldo";
        menuPrincipal[6] = "Ver Historial de Transacciones";
        menuPrincipal[7] = "Salir";

        Pattern MenuInputStructure = Pattern.compile("[1-8]");
        //Programa loop
        do {
            sc = new Scanner(System.in);
            MyUtils.menuMaker("MENU",menuPrincipal,"Escoje una opcion:");
            rawInput = MyUtils.inputRequest("Opcion invalida, es, Intentelo de Nuevo:",MenuInputStructure);

            option = rawInput.charAt(0);
            switch (option) {
                case '1':
                    exito = dawBank.crearCuentaBancaria(new CuentaBancaria(pedirCuenta()));
                    if  (exito == true) {
                        MyUtils.imprimir("Cuenta creada exitosamente");
                    }
                    else {
                        MyUtils.imprimir("Ha ocurrido un error al crear la cuenta");
                    }
                    MyUtils.esperar();
                    break;
                case '2':
                    MyUtils.imprimir("Lista de Cuentas Bancarias:\nHay " + dawBank.getNumCuentasBancarias() + " cuentas bancarias\n");
                    MyUtils.imprimir(dawBank.mostrarTodasLasCuentas());
                    MyUtils.esperar();
                    break;
                case '3':
                    cuentaActual = seleccionCuentaBancaria(dawBank);
                    MyUtils.esperar();
                    break;
                case '4':
                    MyUtils.imprimir(buscarUnaTransaccion(dawBank.obtenerCuentaBancaria(dawBank.getColeccionIBAN()[cuentaActual])));
                    MyUtils.esperar();
                    break;
                case '5':
                    nuevaTransaccion(dawBank.obtenerCuentaBancaria(dawBank.getColeccionIBAN()[cuentaActual]));
                    MyUtils.esperar();
                    break;
                case '6':
                    MyUtils.imprimir(verSaldo(dawBank.obtenerCuentaBancaria(dawBank.getColeccionIBAN()[cuentaActual])));
                    MyUtils.esperar();
                    break;
                case '7':
                    MyUtils.imprimir(verHistorial(dawBank.obtenerCuentaBancaria(dawBank.getColeccionIBAN()[cuentaActual])));
                    MyUtils.esperar();
                    break;
                case '8':
                    MyUtils.imprimir("\nSaliendo del Programa... ¡Adios!");
                    break;
                default:
                    MyUtils.imprimir("Algo fue mal. Intentelo de nuevo.");
                    break;
            }
        } while (option != '8');

        sc.close();
    }

    //Metodos
    public static String[] pedirCuenta() {
        Scanner in = new Scanner(System.in);

        MyUtils.imprimir("Creando CuentaBancaria...");

        //Creacion del IBAN
        Pattern IBANForm = Pattern.compile("[A-Za-z]{2}[0-9]{22}"); //Pattern.compile("[caracteres]{veces que aparecen}[mas caracteres]{minimo,maximo}")
        System.out.print("Introduce el ID del cuenta Bancaria (IBAN): ");
        String IBAN = MyUtils.inputRequest("ISBN mal introducido, usa 2 letras y 22 numeros, intentalo de nuevo:",IBANForm);

        //Creacion del Titular
        Pattern titularForm = Pattern.compile("[A-Z]{1}[a-z]{1,}\s{1}[A-Z]{1}[a-z]{1,}");
        System.out.print("Introduce su titular (nombre y apellidos): ");
        String titular = MyUtils.inputRequest("Titular mal introducido. Revisa letras y mayusculas, y asegurate que es solo un nombre y un apellido:", titularForm);

        //Devolucion de valores
        String resultado[] = new String[2];
        resultado[0] = IBAN;
        resultado[1] = titular;

        return resultado;
    }
    public static String verSaldo(CuentaBancaria cuenta) {
        String resultado = "No se ha encontrado la cuenta Bancaria";
        if (cuenta != null) {
            resultado = "El saldo de su cuenta " + cuenta.getTitular() + " es: " + cuenta.getSaldo();
        }
        return resultado;
    }
    public static String verHistorial(CuentaBancaria cuenta) {
        String resultado = "No se ha encontrado la cuenta Bancaria";
        if (cuenta != null) {
            resultado = "Su historial de transacciones es:\n" + cuenta.mostrarHistorial();
        }
        return resultado;
    }
    public static int seleccionCuentaBancaria(Banco banco) {
        Scanner in  = new Scanner(System.in);

        int IDcuenta = 0;

        String input;
        String resultado = "Seleccione una de sus Cuentas Bancarias:\n";
        int num = banco.getNumCuentasBancarias();
        for (int i = 0; i < num; i++) {
            resultado += i + ". " + banco.obtenerCuentaBancaria(banco.getColeccionIBAN()[i]).getTitular() +
                    " (" + banco.obtenerCuentaBancaria(banco.getColeccionIBAN()[i]).getIBAN() + ")\n";
        }
        MyUtils.imprimir(resultado);

        MyUtils.imprimir("Seleccione el numero asociado a esa cuenta o su IBAN:");

        Pattern IBANForm = Pattern.compile("[A-Za-z]{2}[0-9]{22}");
        Pattern numForm = Pattern.compile("[0-num]");
        Matcher matcherIBAN;
        Matcher matcherNum;
        do {
            in = new Scanner(System.in);
            input = in.nextLine();
            matcherIBAN = IBANForm.matcher(input);
            matcherNum = numForm.matcher(input);
            if (!matcherIBAN.matches() && !matcherNum.matches()) {
                MyUtils.imprimir("Input incorrecto, intentelo de nuevo:");
            }
            if (matcherIBAN.matches()) {
                for (int i = 0; i < num; i++) {
                    if(banco.obtenerCuentaBancaria(banco.getColeccionIBAN()[i]).getIBAN().equalsIgnoreCase(input)) {
                        IDcuenta = i;
                    }
                }
            }
            if (matcherNum.matches()) {
                IDcuenta = Integer.parseInt(input);
            }
        } while (!matcherIBAN.matches() && !matcherNum.matches());

        MyUtils.imprimir("Su cuenta seleccionada ha sido:\n");
        MyUtils.imprimir(banco.obtenerCuentaBancaria(banco.getColeccionIBAN()[IDcuenta]).mostrarCuentaBancaria());

        return IDcuenta;
    }
    public static String buscarUnaTransaccion(CuentaBancaria cuenta) {
        Scanner in;
        Pattern IDForm = Pattern.compile("[0-9]+");
        Pattern cantidadForm = Pattern.compile("[0-9]+,[0-9]{2}");
        String input;
        Matcher matcherID;
        Matcher matcherCantidad;
        String resultado = "No se ha encontrado esa transaccion";

        MyUtils.imprimir("Introduce el ID de una transaccion que desea buscar o todas las transacciones de esa misma cantidad:");
        do {
            in = new Scanner(System.in);
            input = in.nextLine();
            matcherID = IDForm.matcher(input);
            matcherCantidad = cantidadForm.matcher(input);
            if (matcherID.matches()) {
                resultado = cuenta.buscarTransaccionID(Integer.parseInt(input));
            }
            if (matcherCantidad.matches()) {
                resultado = cuenta.buscarTodasTransaccionesPorCantidad(Double.parseDouble(input));
            }
            if (!matcherID.matches() && !matcherCantidad.matches()) {
                MyUtils.imprimir("Input no valido, intentelo de nuevo:");
            }
        } while (!matcherID.matches() && !matcherCantidad.matches());

        return resultado;
    }
    public static void nuevaTransaccion(CuentaBancaria cuenta) {
        //Parametros
        Pattern patternTipo = Pattern.compile("1|2");
        String menuOpciones[] = new String[2];
        menuOpciones[0] = "Ingresar";
        menuOpciones[1] = "Retirar";
        String input;
        MyUtils.Transaccion tipo;
        Pattern cantidadForm[] = new Pattern[2];
        cantidadForm[0] = Pattern.compile("[0-9]+,?[0-9]{0,2}");
        cantidadForm[1] = Pattern.compile("[0-9]+.?[0-9]{0,2}");
        double cantidad;

        //InputsM
        MyUtils.menuMaker("Introduce el tipo de transaccion:",menuOpciones,"Introduca una Opcion:");
        input = MyUtils.inputRequest("Valor no valido, introduzca 1 o 2:",patternTipo);

        if (input.equalsIgnoreCase("1")) {
            tipo = MyUtils.Transaccion.INGRESO;
        }
        else if (input.equalsIgnoreCase("2")) {
            tipo = MyUtils.Transaccion.RETIRADA;
        }
        else {
            //Necesario solo como medida de verificacion
            MyUtils.imprimir("Ha habido un error, valor autoasignado a Ingreso");
            tipo = MyUtils.Transaccion.INGRESO;
        }

        MyUtils.imprimir("Introduce la cantidad de " + tipo.name() + ":");
        input = MyUtils.inputRequest("Error, introduce un valor absoluto sin signos y con hasta dos decimales (usando ,)",cantidadForm);
        input = input.replace(',','.');
        System.out.println("El valor introducido es " + input);
        cantidad = Double.parseDouble(input);

        //Creacion del nuevo movimiento
        Movimiento miMovimiento = new Movimiento(tipo, cantidad);
        cuenta.hacerTransaccion(miMovimiento);
        if (cuenta.getSaldo() < -50) {
            MyUtils.imprimir("AVISO!!!!!!! SALDO NEGATIVO");
            MyUtils.esperar();
        }
        else if (cantidad > 3000) {
            MyUtils.imprimir("AVISO!!!!!!! NOTIFICAR A HACIENDA");
            MyUtils.esperar();
        }
        MyUtils.imprimir("Transaccion realizada correctamente\n\n" + miMovimiento.mostrarInfoMovimiento());
    }
}