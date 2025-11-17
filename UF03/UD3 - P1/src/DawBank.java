import com.sun.tools.javac.Main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DawBank {
    public static void main(String[] args) {

        //Inicio del programa
        Scanner sc;
        int cuentaActual = 0;
        String[] coleccionIBAN = new String[10];
        int numeroIBAN
        Banco DawBank = new Banco("DawBank", "39007");
        DawBank.crearCuentaBancaria(new CuentaBancaria(pedirCuenta()));
        String rawInput;
        char option;

        Pattern MenuInputStructure = Pattern.compile("[1-8]{1}");
        Matcher MenuOptionIsCorrect;
        //Programa loop
        do {
            sc = new Scanner(System.in);
            System.out.println("\nMENU");
            System.out.println("1. Crear Cuenta Bancaria");
            System.out.println("2. Listar Cuentas Bancarias");
            System.out.println("3. Seleccionar Cuenta Bancaria");
            System.out.println("4. Borrar Cuenta Bancaria");
            System.out.println("5. Hacer Transaccion");
            System.out.println("6. Ver Saldo");
            System.out.println("7. Ver Historial de Transacciones");
            System.out.println("8. Salir");
            System.out.print("Escoje una opcion:");
            rawInput = sc.nextLine();
            MenuOptionIsCorrect = MenuInputStructure.matcher(rawInput);
            while(!MenuOptionIsCorrect.matches()) {
                System.out.print("Opcion invalida, Intentelo de Nuevo: ");
                rawInput = sc.nextLine();
                MenuOptionIsCorrect = MenuInputStructure.matcher(rawInput);
            }
            option = rawInput.charAt(0);
            switch (option) {
                case '1':
                    DawBank.crearCuentaBancaria(new CuentaBancaria(pedirCuenta()));
                    break;
                case '2':
                    System.out.println("Lista de Cuentas Bancarias:\n");
                    System.out.println(DawBank.mostrarTodasLasCuentas());
                    break;
                case '3':
                    break;
                case '4':
                    break;
                case '5':
                    break;
                case '6':
                    System.out.println("El saldo de esta cuenta es: " + DawBank.listaDeCuentasBancarias[cuentaActual].getSaldo());
                    break;
                case '7':
                    System.out.println("Su historial de transacciones es:\n");
                    System.out.println(DawBank.listaDeCuentasBancarias[cuentaActual].mostrarHistorial());
                    break;
                case '8':
                    System.out.println("\nSaliendo del Programa... ¡Adios!");
                    break;
                default:
                    System.out.println("Algo fue mal. Intentelo de nuevo.");
                    break;
            }
        } while (option != '8');

        sc.close();
    }

    //Metodos
    public static String[] pedirCuenta() {
        Scanner in = new Scanner(System.in);

        //Creacion del IBAN
        Pattern IBANForm = Pattern.compile("[A-Za-z]{2}[0-9]{22}"); //Pattern.compile("[caracteres]{veces que aparecen}[mas caracteres]{minimo,maximo}")
        System.out.print("Introduce el ID del cuenta Bancaria (IBAN): ");
        String IBAN = in.nextLine();
        Matcher correctIBAN = IBANForm.matcher(IBAN);
        while (!correctIBAN.matches()) {
            System.out.print("ISBN mal introducido, usa 2 letras y 22 numeros, intentalo de nuevo: ");
            IBAN = in.nextLine();
            correctIBAN = IBANForm.matcher(IBAN);
        }

        //Creacion del Titular
        Pattern titularForm = Pattern.compile("[A-Z]{1}[a-z]{1,} [A-Z]{1}[a-z]{1,}");
        System.out.print("Introduce su titular (nombre y apellidos): ");
        String titular = in.nextLine();
        Matcher correctTitular = titularForm.matcher(titular);
        while (!correctTitular.matches()) {
            System.out.println("Titular mal introducido. Revisa letras y mayusculas, y asegurate al menos un nombre y un apellido: ");
            titular = in.nextLine();
            correctTitular = titularForm.matcher(titular);
        }

        //Devolucion de valores
        String resultado[] = new String[2];
        resultado[0] = IBAN;
        resultado[1] = titular;
        return resultado;
    }
}
