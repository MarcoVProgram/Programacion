import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DawBank {
    public static void main(String[] args) {

        //Inicio del programa
        Scanner sc;
        int cuentaActual = 0;
        Banco dawBank = new Banco("dawBank", "39007");
        registrarNuevaCuenta(dawBank);

        String rawInput;
        String imprimirLista, transaccionBuscada, miSaldo, miHistorial;

        CuentaBancaria cuentaSeleccionada = dawBank.obtenerCuentaBancaria(dawBank.getColeccionIBAN()[cuentaActual]);
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
            rawInput = MyUtils.inputRequest("Opcion invalida, es un numero del 1 al 8, Intentelo de Nuevo:",MenuInputStructure);

            option = rawInput.charAt(0);
            switch (option) {
                case '1':
                    registrarNuevaCuenta(dawBank);
                    MyUtils.esperar();
                    break;
                case '2':
                    imprimirLista = "Lista de Cuentas Bancarias:\nHay " + dawBank.getNumCuentasBancarias() + " cuentas bancarias\n";
                    imprimirLista += dawBank.mostrarTodasLasCuentas();
                    MyUtils.imprimir(imprimirLista);
                    MyUtils.esperar();
                    break;
                case '3':
                    cuentaActual = seleccionCuentaBancaria(dawBank);
                    cuentaSeleccionada = dawBank.obtenerCuentaBancaria(dawBank.getColeccionIBAN()[cuentaActual]);
                    MyUtils.esperar();
                    break;
                case '4':
                    transaccionBuscada = buscarUnaTransaccion(cuentaSeleccionada);
                    MyUtils.imprimir(transaccionBuscada);
                    MyUtils.esperar();
                    break;
                case '5':
                    nuevaTransaccion(cuentaSeleccionada);
                    MyUtils.esperar();
                    break;
                case '6':
                    miSaldo = verSaldo(cuentaSeleccionada);
                    MyUtils.imprimir(miSaldo);
                    MyUtils.esperar();
                    break;
                case '7':
                    miHistorial = verHistorial(cuentaSeleccionada);
                    MyUtils.imprimir(miHistorial);
                    MyUtils.esperar();
                    break;
                case '8':
                    MyUtils.imprimir("\nSaliendo del Programa... ¡Adios!");
                    break;
                default:
                    MyUtils.imprimir("Algo fue mal. " + rawInput + " No es una opcion valida. Intentelo de Nuevo.");
                    break;
            }
        } while (option != '8');

        sc.close();
    }

    //Metodos
    public static void registrarNuevaCuenta(Banco banco) {
        MyUtils.imprimir("Creando CuentaBancaria...");

        //Creacion del IBAN
        Pattern IBANForm = Pattern.compile("[A-Za-z]{2}[0-9]{22}"); //Pattern.compile("[caracteres]{veces que aparecen}[mas caracteres]{minimo,maximo}")
        System.out.print("Introduce el ID del cuenta Bancaria (IBAN): ");
        String IBAN = MyUtils.inputRequest("ISBN mal introducido, usa 2 letras y 22 numeros, intentalo de nuevo:",IBANForm);

        //Creacion del Titular
        Pattern titularForm = Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+");
        System.out.print("Introduce su titular (nombre y apellidos): ");
        String titular = MyUtils.inputRequest("Titular mal introducido. Revisa letras y mayusculas, y asegurate que es solo un nombre y un apellido:", titularForm);

        //Devolucion de valores

        CuentaBancaria nuevaCuenta = new CuentaBancaria(IBAN,titular);
        boolean existe = banco.crearCuentaBancaria(nuevaCuenta);
        if  (existe == true) {
            MyUtils.imprimir("Cuenta creada exitosamente");
        }
        else {
            MyUtils.imprimir("Ha ocurrido un error al crear la cuenta");
        }
    }
    public static String verSaldo(CuentaBancaria cuenta) {
        String resultado = "No se ha encontrado la cuenta Bancaria";
        if (cuenta != null) {
            resultado = String.format("El saldo de su cuenta (%s) es: %.2f",cuenta.getTitular(), cuenta.getSaldo());
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

        String inputSeleccionCuenta;
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
            inputSeleccionCuenta = in.nextLine();
            matcherIBAN = IBANForm.matcher(inputSeleccionCuenta);
            matcherNum = numForm.matcher(inputSeleccionCuenta);
            if (!matcherIBAN.matches() && !matcherNum.matches()) {
                MyUtils.imprimir("Input incorrecto, intentelo de nuevo:");
            }
            if (matcherIBAN.matches()) {
                for (int i = 0; i < num; i++) {
                    if(banco.obtenerCuentaBancaria(banco.getColeccionIBAN()[i]).getIBAN().equalsIgnoreCase(inputSeleccionCuenta)) {
                        IDcuenta = i;
                    }
                }
            }
            if (matcherNum.matches()) {
                IDcuenta = Integer.parseInt(inputSeleccionCuenta);
            }
        } while (!matcherIBAN.matches() && !matcherNum.matches());

        MyUtils.imprimir("Su cuenta seleccionada ha sido:\n");
        MyUtils.imprimir(banco.obtenerCuentaBancaria(banco.getColeccionIBAN()[IDcuenta]).mostrarCuentaBancaria());

        return IDcuenta;
    }
    public static String buscarUnaTransaccion(CuentaBancaria cuenta) {
        Scanner in  = new Scanner(System.in);
        Pattern IDForm = Pattern.compile("[0-9]+");
        Pattern cantidadForm = Pattern.compile("[0-9]+,[0-9]{2}");
        String inputBusqueda;
        Matcher matcherID;
        Matcher matcherCantidad;
        String resultado = "No se ha encontrado esa transaccion";

        MyUtils.imprimir("Introduce el ID de una transaccion que desea buscar o todas las transacciones de esa misma cantidad:");
        do {
            inputBusqueda = in.nextLine();
            matcherID = IDForm.matcher(inputBusqueda);
            matcherCantidad = cantidadForm.matcher(inputBusqueda);
            if (matcherID.matches()) {
                resultado = cuenta.buscarTransaccionesPorID(Integer.parseInt(inputBusqueda));
            }
            if (matcherCantidad.matches()) {
                resultado = cuenta.buscarTodasTransaccionesPorCantidad(Double.parseDouble(inputBusqueda));
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
        String inputNuevaTransaccion;
        MyUtils.Transaccion tipo;
        Pattern cantidadForm[] = new Pattern[2];
        cantidadForm[0] = Pattern.compile("[0-9]+,?[0-9]{0,2}");
        cantidadForm[1] = Pattern.compile("[0-9]+.?[0-9]{0,2}");
        double cantidad;

        //InputsM
        MyUtils.menuMaker("Introduce el tipo de transaccion:",menuOpciones,"Introduca una Opcion:");
        inputNuevaTransaccion = MyUtils.inputRequest("Valor no valido, introduzca 1 o 2:",patternTipo);

        if (inputNuevaTransaccion.equalsIgnoreCase("1")) {
            tipo = MyUtils.Transaccion.INGRESO;
        }
        else if (inputNuevaTransaccion.equalsIgnoreCase("2")) {
            tipo = MyUtils.Transaccion.RETIRADA;
        }
        else {
            //Necesario solo como medida de verificacion
            MyUtils.imprimir("Ha habido un error, valor autoasignado a Ingreso");
            tipo = MyUtils.Transaccion.INGRESO;
        }

        MyUtils.imprimir("Introduce la cantidad de " + tipo.name() + ":");
        inputNuevaTransaccion = MyUtils.inputRequest("Error, introduce un valor absoluto sin signos y con hasta dos decimales (usando , o .)",cantidadForm);
        inputNuevaTransaccion = inputNuevaTransaccion.replace(',','.');
        System.out.println("La cantidad introducida es " + inputNuevaTransaccion);
        cantidad = Double.parseDouble(inputNuevaTransaccion);

        //Creacion del nuevo movimiento
        Movimiento miMovimiento = new Movimiento(tipo, cantidad);

        if (miMovimiento.getTipoTransaccion() == MyUtils.Transaccion.RETIRADA && cuenta.getSaldo() - miMovimiento.getCantidad()< -50) {
            MyUtils.imprimir("⚠️ AVISO! ⚠️ LA TRANSACCION NO SE PUDO REALIZAR, SALDO MENOR QUE -50 EUROS");
        }
        else if (cantidad > 3000) {
            cuenta.hacerTransaccion(miMovimiento);
            MyUtils.imprimir("Transaccion realizada\n\n" + miMovimiento.mostrarInfoMovimiento());
            MyUtils.imprimir("⚠️ AVISO! ⚠️ NOTIFICAR A HACIENDA");
        }
        else {
            cuenta.hacerTransaccion(miMovimiento);
            MyUtils.imprimir("Transaccion realizada correctamente\n\n" + miMovimiento.mostrarInfoMovimiento());
        }
    }
}