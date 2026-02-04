import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.StructureViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DawBank {

    //Creacion de variables globales

    //Pattern.compile("[caracteres]{veces que aparecen}[mas caracteres]{minimo,maximo}")
    private static final Pattern IBAN_FORM = Pattern.compile("[A-Za-z]{2}[0-9]{22}");
    private static final Pattern DNI_FORM = Pattern.compile("[0-9]{8}[A-Za-z]");
    private final static Pattern NOMBRE_USER_FORM = Pattern.compile("[A-Z][a-z A-Z]+ [A-Z][a-z A-Z]+"); //Solo letras y espacios, nombre y apellido
    private final static Pattern TELEFONO_FORM = Pattern.compile("[679][0-9]{8}");//Un numero de 9 digitos que empieza por 6,7 o 9
    private final static Pattern EMAIL_FORM = Pattern.compile("[A-Za-z-._0-9]+@[A-Za-z]+[.][A-Za-z]{2,4}");//Correo valido
    //Correo necesita letras, numeros y -. o _ para el cuerpo, un @, despues solo letras, un punto, y un dominio de solo letras de 2 a 4 caracteres

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
            try {
                rawInput = MyUtils.inputRequest(MenuInputStructure);
            } catch (InputIncorrectoException e) {
                MyUtils.imprimir(e.getMessage());
                rawInput = "e";
            }

            option = rawInput.charAt(0);
            switch (option) {
                case '1':
                    registrarNuevaCuenta(dawBank);
                    MyUtils.esperar();
                    break;
                case '2':
                    imprimirLista = "Lista de Cuentas Bancarias:\nHay " + dawBank.getNumCuentasBancarias() + " cuentas bancarias\n\n";
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
                case 'e':
                    MyUtils.imprimir("Input introducido seleccionado incorrecto. Intentelo de Nuevo.");
                    break;
                default:
                    MyUtils.imprimir("Algo fue mal. " + rawInput + " No es una opcion valida. Comunicaselo a un desarrollador.");
                    break;
            }
        } while (option != '8');

        sc.close();
    }

    //Metodos
    public static void registrarNuevaCuenta(Banco banco) {
        MyUtils.imprimir("Creando Cuenta Bancaria...");
        String IBAN = "";
        Cliente titular;

        //Creacion del IBAN
        do {
            try {
                MyUtils.imprimirSinSalto("\nIntroduce el ID del cuenta Bancaria (IBAN): ");
                IBAN = MyUtils.inputRequest(IBAN_FORM);
            } catch (InputIncorrectoException e) {
                MyUtils.imprimir(e.getMessage());
                MyUtils.imprimir("Usa 2 letras y 22 numeros, intentalo de Nuevo.");
            }
        } while (!IBAN_FORM.matcher(IBAN).matches());

        //Creacion del Titular
        titular = crearTitular(banco);

        //Devolucion de valores

        CuentaBancaria nuevaCuenta = new CuentaBancaria(IBAN, titular);
        try {
            boolean existe = banco.crearCuentaBancaria(nuevaCuenta);
            if (existe == true) {
                MyUtils.imprimir("Cuenta creada exitosamente");
            } else {
                MyUtils.imprimir("Cuenta ya existe, abortando");
            }
        } catch (CuentaException e) {
            MyUtils.imprimir(e.getMessage());
        }
    }
    public static Cliente crearTitular(Banco banco) {
        Scanner in = new Scanner(System.in);
        Cliente titular;
        String DNI,nombre,telefono,email,direccion;
        LocalDate fechaNacimiento;

        do {
            try {
                MyUtils.imprimirSinSalto("\nIntroduce el DNI del titular: ");
                DNI = MyUtils.inputRequest(DNI_FORM);
            } catch (InputIncorrectoException e) {
                MyUtils.imprimir(e.getMessage());
                DNI = "";
                MyUtils.imprimir("DNI mal introducido. Asegurate de que sean 8 numeros y 1 letra");
            }
        } while (!DNI_FORM.matcher(DNI).matches());

        titular = banco.clienteExiste(DNI);

        if (titular != null) {
            MyUtils.imprimir("\nEl cliente ya tiene una cuenta previa en el sistema");
            MyUtils.imprimir(titular.toString());
            MyUtils.imprimir("Seleccionando automaticamente");
        }
        else {
            do {
                try {
                    MyUtils.imprimirSinSalto("\nIntroduce el nombre y apellido del titular: ");
                    nombre = MyUtils.inputRequest(NOMBRE_USER_FORM);
                } catch (InputIncorrectoException e) {
                    MyUtils.imprimir(e.getMessage());
                    nombre = "";
                    MyUtils.imprimir("Usa un nombre y un apellido con correctas mayusculas y minusculas");
                }
            } while (!NOMBRE_USER_FORM.matcher(nombre).matches());

            fechaNacimiento = MyUtils.pedirFecha();

            do {
                try {
                    MyUtils.imprimirSinSalto("\nIntroduce el telefono del titular: ");
                    telefono = MyUtils.inputRequest(TELEFONO_FORM);
                } catch (InputIncorrectoException e) {
                    MyUtils.imprimir(e.getMessage());
                    telefono = "";
                    MyUtils.imprimir("El telefono son 9 numeros en total y deben empezar en 6,7 o 9");
                }
            } while (!TELEFONO_FORM.matcher(telefono).matches());

            do {
                try {
                    MyUtils.imprimirSinSalto("\nIntroduce el email del titular: ");
                    email = MyUtils.inputRequest(EMAIL_FORM).toLowerCase();
                } catch (InputIncorrectoException e) {
                    MyUtils.imprimir(e.getMessage());
                    email = "";
                    MyUtils.imprimir("Usa letras, numeros o caracteres como '-', '_' o '.', un simbolo @," +
                            "despues una direccion de solo letras, un . y un dominio que sea de 2 a 4 letras");
                }
            } while (!EMAIL_FORM.matcher(email).matches());

            MyUtils.imprimirSinSalto("\nIntroduce la direccion del titular: ");
            direccion = in.nextLine();

            titular = new Cliente(nombre, DNI, fechaNacimiento, telefono, email, direccion);
            MyUtils.imprimir("\nEl cliente es:\n" + titular.toString());
        }

        return titular;
    }

    public static String verSaldo(CuentaBancaria cuenta) {
        String resultado = "No se ha encontrado la cuenta Bancaria";
        if (cuenta != null) {
            resultado = String.format("El saldo de su cuenta (%s) es: %.2f",cuenta.getTitular().getNombre(), cuenta.getSaldo());
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
        Scanner in;

        int IDcuenta = 0;
        boolean encontrada = false;

        String inputSeleccionCuenta;
        String resultado = "Seleccione una de sus Cuentas Bancarias:\n";
        int num = banco.getNumCuentasBancarias();
        StringBuilder colNums  = new StringBuilder("");

        for (int i = 0; i < num; i++) {
            resultado += i + ". " + banco.obtenerCuentaBancaria(banco.getColeccionIBAN()[i]).getTitular().getNombre() +
                    " (" + banco.obtenerCuentaBancaria(banco.getColeccionIBAN()[i]).getIBAN() + ")\n";
        }
        MyUtils.imprimir(resultado);

        for (int i = 0; i < num; i++) {
            colNums.append(i);
            if (i<num-1) {
                colNums.append("|");
            }
        }

        Pattern numForm = Pattern.compile("(" + colNums + ")");
        Matcher matcherIBAN;
        Matcher matcherNum;
        do {
            MyUtils.imprimirSinSalto("\nSeleccione el numero asociado a esa cuenta o su IBAN: ");
            in = new Scanner(System.in);
            inputSeleccionCuenta = in.nextLine();
            matcherIBAN = IBAN_FORM.matcher(inputSeleccionCuenta);
            matcherNum = numForm.matcher(inputSeleccionCuenta);
            if (!matcherIBAN.matches() && !matcherNum.matches()) {
                MyUtils.imprimir("Input no Valido, intentalo de Nuevo");
            }
            if (matcherIBAN.matches()) {
                for (int i = 0; i < num; i++) {
                    if(banco.obtenerCuentaBancaria(banco.getColeccionIBAN()[i]).getIBAN().equalsIgnoreCase(inputSeleccionCuenta)) {
                        IDcuenta = i;
                        encontrada = true;
                    }
                }
                if (!encontrada) {
                    MyUtils.imprimir("\nIBAN NO EXISTENTE, seleccionando cuenta por defecto\n");
                }
            }
            if (matcherNum.matches()) {
                IDcuenta = Integer.parseInt(inputSeleccionCuenta);
            }
        } while (!matcherIBAN.matches() && !matcherNum.matches());

        MyUtils.imprimir("Su cuenta seleccionada ha sido:\n");
        MyUtils.imprimir(banco.obtenerCuentaBancaria(banco.getColeccionIBAN()[IDcuenta]).toString());

        return IDcuenta;
    }
    public static String buscarUnaTransaccion(CuentaBancaria cuenta) {
        Scanner in;
        Pattern IDForm = Pattern.compile("[0-9]+");
        Pattern cantidadForm = Pattern.compile("[0-9]+,?[0-9]{0,2}");
        String inputBusqueda;
        Matcher matcherID;
        Matcher matcherCantidad;
        String resultado = "No se ha encontrado esa transaccion";

        do {
            try {
                in = new Scanner(System.in);
                MyUtils.imprimirSinSalto("\nIntroduce el ID de una transaccion que desea buscar o todas las transacciones de esa misma cantidad: ");
                inputBusqueda = in.nextLine();
                matcherID = IDForm.matcher(inputBusqueda);
                matcherCantidad = cantidadForm.matcher(inputBusqueda);
                if (matcherID.matches()) {
                    resultado = cuenta.buscarTransaccionesPorID(Integer.parseInt(inputBusqueda));
                }
                if (matcherCantidad.matches() && resultado.equals("No se ha encontrado esa transaccion")) {
                    resultado = cuenta.buscarTodasTransaccionesPorCantidad(Double.parseDouble(inputBusqueda.replaceAll(",", ".")));
                }
                if (!matcherID.matches() && !matcherCantidad.matches()) {
                    MyUtils.imprimir("Input no Valido, intentalo de Nuevo");
                }
            } catch (NumberFormatException e) {
                inputBusqueda = "";
                matcherID = IDForm.matcher(inputBusqueda);
                matcherCantidad = cantidadForm.matcher(inputBusqueda);
                MyUtils.imprimir("Ha habido un error al intentar leer el numero, intentalo de nuevo");
            } catch (Exception e) {
                inputBusqueda = "";
                matcherID = IDForm.matcher(inputBusqueda);
                matcherCantidad = cantidadForm.matcher(inputBusqueda);
                MyUtils.imprimir("Ha habido un error inesperado");
                MyUtils.imprimir(e.getMessage());
            }
        } while (!matcherID.matches() && !matcherCantidad.matches());

        return resultado;
    }
    public static void nuevaTransaccion(CuentaBancaria cuenta) {
        //Parametros
        Scanner in;
        Pattern patternTipo = Pattern.compile("1|2");
        String menuOpciones[] = new String[2];
        menuOpciones[0] = "Ingresar";
        menuOpciones[1] = "Retirar";
        String inputNuevaTransaccion;
        Transaccion tipo;
        boolean valido;
        double cantidad = 0.0;

        //InputsM
        do {
            try {
                MyUtils.menuMaker("Introduce el tipo de transaccion:",menuOpciones,"Introduca una Opcion:");
                inputNuevaTransaccion = MyUtils.inputRequest(patternTipo);
            } catch (InputIncorrectoException e) {
                MyUtils.imprimir(e.getMessage());
                inputNuevaTransaccion = "";
                MyUtils.imprimir("Valor no valido, introduzca 1 o 2");
            }
        } while (!patternTipo.matcher(inputNuevaTransaccion).matches());

        if (inputNuevaTransaccion.equalsIgnoreCase("1")) {
            tipo = Transaccion.INGRESO;
        }
        else if (inputNuevaTransaccion.equalsIgnoreCase("2")) {
            tipo = Transaccion.RETIRADA;
        }
        else {
            //Necesario solo como medida de verificacion
            MyUtils.imprimir("Ha habido un error, valor autoasignado a Ingreso");
            tipo = Transaccion.INGRESO;
        }

        do {
            in = new Scanner(System.in);
            try {
                MyUtils.imprimirSinSalto("\nIntroduce la cantidad de " + tipo.name() + ": ");
                cantidad = in.nextDouble();
                valido = true;

                cantidad = Math.floor(cantidad * 100)/100;
                MyUtils.imprimir("Cantidad de " + tipo.name() + ": " + cantidad);
            } catch (InputMismatchException e) {
                MyUtils.imprimir("El numero introducido no es un decimal. Intentalo de Nuevo");
                valido = false;
            } catch (Exception e) {
                MyUtils.imprimir(e.getMessage());
                valido = false;
                MyUtils.imprimir("Se ha encontrado un error inesperado");
            }
        } while (!valido);

        //Creacion del nuevo movimiento
        Movimiento miMovimiento = new Movimiento(tipo, cantidad);

        try {
            cuenta.hacerTransaccion(miMovimiento);
            MyUtils.imprimir("Transaccion realizada correctamente\n" + miMovimiento.toString());
        } catch (AvisarAciendaException e) {
            MyUtils.imprimir(e.getMessage());
            MyUtils.imprimir("Transaccion realizada de todas formas\n" + miMovimiento.toString());
        } catch (SaldoNegativoException e) {
            MyUtils.imprimir(e.getMessage());
            MyUtils.imprimir("La transaccion ha sido detenida");
        } catch (CuentaException e) {
            MyUtils.imprimir(e.getMessage());
            MyUtils.imprimir("No se puede insertar transacciones nulas, transaccion abortada");
        } catch (Exception e) {
            MyUtils.imprimir(e.getMessage());
            MyUtils.imprimir("Algo fue mal, comunicaselo a un desarrollador");
        }
    }
}