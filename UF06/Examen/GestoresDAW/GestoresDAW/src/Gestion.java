import java.io.*;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestion {

    public static void main(String[] args) {

        final String path = ".\\src\\resources\\";
        final String fileAlmacen = "empresa.dat";
        final String fileConfig = "config.dat";

        Empresa gestionDAW = null;

        Scanner input = new Scanner(System.in);



        MyUtils.print("BIENVENIDO AL PROGRAMA");

        //LECTURA CONFIG
        try (FileInputStream fis = new FileInputStream(path + fileAlmacen);
            DataInputStream dis = new DataInputStream(fis);) {

            boolean exito = Trabajador.configCodGeneration(dis.readInt());

            if (exito) {

                MyUtils.print("\nSe cargo la configuracion previa\n");
            }

        }  catch (FileNotFoundException e) {

            MyUtils.print("\nNo hay datos de configuracion a cargar\n");
            File newConfigFile = new File(path+fileConfig);

            try {

                if(newConfigFile.createNewFile()) {
                    MyUtils.print("Se ha creado el Archivo " + fileConfig);
                } else {

                    MyUtils.print("No se ha creado el archivo " + fileConfig);
                }

            } catch (IOException ex) {
                MyUtils.print("Error: " + ex.getMessage());
            }

        }
        catch (IOException e) {

            throw new RuntimeException(e);

        }

        boolean eof = false;

        //LECTURA FICHERO
        try (FileInputStream fis = new FileInputStream(path+fileAlmacen);
             ObjectInputStream ois = new ObjectInputStream(fis)) {


            MyUtils.printHere("\nDesea cargar los datos anteriores?\nEscriba (S) para leer "
                    + fileAlmacen + " o cualquier otra tecla para cancelar: ");


            if (input.nextLine().equalsIgnoreCase("S")) {

                while (!eof) {
                    gestionDAW = (Empresa) ois.readObject();
                }
            }
            else {
                gestionDAW = crearEmpresa();
            }

        } catch (FileNotFoundException e) {

            MyUtils.print("No existe un fichero previo, creando Empresa automaticamente");
            File newFile = new File(path+fileAlmacen);

            try {

                if(newFile.createNewFile()) {
                    MyUtils.print("Se ha creado el Archivo " + fileAlmacen);
                } else {

                    MyUtils.print("No se ha creado el archivo " + fileAlmacen);
                }

            } catch (IOException ex) {
                MyUtils.print("Error: " + ex.getMessage());
            }
            gestionDAW = crearEmpresa();

        } catch (EOFException e) {

            eof = true;

        } catch (ClassNotFoundException e) {

            MyUtils.print("Clase no encontrada");

        } catch (IOException e) {

            MyUtils.print("Error al I/O");
            return;

        }

        String[] menu = new String[7];
        menu[0] = "Registar empleado en la Empresa";
        menu[1] = "Informacion general de la empresa";
        menu[2] = "Organigrama de la empresa";
        menu[3] = "Informacion de Departamento";
        menu[4] = "Eliminar trabajador de la Empresa";
        menu[5] = "Guardar Cambios";
        menu[6] = "Guardar Cambios y Salir del programa";

        char option;

        //LOOP PRINCIPAL

        do {
            MyUtils.menuMaker("MENU EMPRESA " + gestionDAW.getNombre(),
                        menu,
                        "Introduce una opcion: ");

            try {

                option = MyUtils.inputRequest(Patrones.MENU_FORM).charAt(0);

            } catch (InputIncorrectoException e) {

                option = 'e';
            }


            switch (option) {

                //CASE 1 - Registrar empleado
                case '1':

                    registrarEmpleadoEnEmpresa(gestionDAW);
                    break;

                //CASE 2 - INFORMACION GENERAL EMPRESA
                case '2':

                    MyUtils.print(gestionDAW.mostrarInfoTrabajadoresOrdenados());
                    String info = "estructura.dat";

                    try (FileWriter fileWriter = new FileWriter(path+info, false);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                        bufferedWriter.write(gestionDAW.mostrarInfoTrabajadoresOrdenados());

                        //Errores
                    } catch (FileNotFoundException e) {

                        File newFile = new File(path+info);

                        try {

                            if(newFile.createNewFile()) {
                                MyUtils.print("Se ha creado el Archivo " + info);
                            } else {

                                MyUtils.print("No se ha creado el archivo " + info);
                            }

                        } catch (IOException ex) {
                            MyUtils.print("Error: " + ex.getMessage());
                        }
                    }
                    catch (IOException e) {

                        //Fallo al editar el Archivo
                        MyUtils.print("Error al editar el archivo almacen: " + e.getMessage());
                    }
                    break;

                //CASE 3 - ORGANIGRAMA
                case '3':

                    MyUtils.print(gestionDAW.mostrarInfoTrabajadores());
                    String organi = "organigrama.dat";

                    try (FileWriter fileWriter = new FileWriter(path+organi, false);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                        bufferedWriter.write(gestionDAW.mostrarInfoTrabajadores());

                        //Errores
                    } catch (FileNotFoundException e) {

                        File newFile = new File(path+organi);

                        try {

                            if(newFile.createNewFile()) {
                                MyUtils.print("Se ha creado el Archivo " + organi);
                            } else {

                                MyUtils.print("No se ha creado el archivo " + organi);
                            }

                        } catch (IOException ex) {
                            MyUtils.print("Error: " + ex.getMessage());
                        }
                    }
                    catch (IOException e) {

                        //Fallo al editar el Archivo
                        MyUtils.print("Error al editar el archivo almacen: " + e.getMessage());
                    }

                    break;

                //CASE 4 - INFORMACION DEPARTAMENTO
                case '4':
                    Scanner sc = new Scanner(System.in);
                    Gerencia gerencia = Gerencia.INFORMATICA;

                    MyUtils.print("\nGerencia: ");
                    for (Gerencia de : Gerencia.values()) {

                        MyUtils.print(de.ordinal() + ". " + de.name());
                    }

                    MyUtils.printHere("\nEscribe el indice Gerencia (Por defecto INFORMATICA): ");

                    try {
                        int num = sc.nextInt();

                        for (Gerencia de : Gerencia.values()) {

                            if (de.ordinal() == num) {
                                gerencia = de;
                            }
                        }
                    } catch (InputMismatchException e) {

                        gerencia = Gerencia.INFORMATICA;

                    }

                    MyUtils.print(gestionDAW.mostrarInfoGerencia(gerencia));
                    String depInfo ="dep_"+ gerencia.name().toLowerCase() + ".dat";

                    try (FileWriter fileWriter = new FileWriter(path+depInfo, false);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                        bufferedWriter.write(gestionDAW.mostrarInfoTrabajadores());

                        //Errores
                    } catch (FileNotFoundException e) {

                        File newFile = new File(path+depInfo);

                        try {

                            if(newFile.createNewFile()) {
                                MyUtils.print("Se ha creado el Archivo " + depInfo);
                            } else {

                                MyUtils.print("No se ha creado el archivo " + depInfo);
                            }

                        } catch (IOException ex) {
                            MyUtils.print("Error: " + ex.getMessage());
                        }
                    }
                    catch (IOException e) {

                        //Fallo al editar el Archivo
                        MyUtils.print("Error al editar el archivo almacen: " + e.getMessage());
                    }

                    break;

                //CASE 5 - ELIMINAR TRABAJADOR
                case '5':
                    String DNI;
                    do {
                        try {

                            MyUtils.printHere("\nEscribe el DNI: ");
                            DNI = MyUtils.inputRequest(Patrones.DNI_FORM);

                        }  catch (InputIncorrectoException e) {

                            MyUtils.print("DNI incorrecto, revisalo");
                            DNI = "";

                        }
                    } while (DNI.equals(""));

                    gestionDAW.eliminarTrabajador(gestionDAW.buscarTrabajador(DNI));

                    break;

                //CASE 6 - GUARDAR
                case '6':

                    guardarConfig(path+fileConfig);
                    guardar(path+fileAlmacen, gestionDAW);
                    break;

                //CASE 7 - GUARDAR Y SALIR
                case '7':

                    guardarConfig(path+fileConfig);
                    guardar(path+fileAlmacen, gestionDAW);
                    break;

                //CASE ERROR
                default:
                    MyUtils.print("Error, intentalo de nuevo");
                    break;
            }


        } while (option != '7');
    }

    private static void guardarConfig(String loc) {

        try (FileOutputStream fos = new FileOutputStream(loc, false);
            DataOutputStream oos = new DataOutputStream(fos);) {

            oos.writeInt(Trabajador.getConfigCod());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void guardar(String loc, Empresa emp) {
        try (FileOutputStream fos = new FileOutputStream(loc, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            oos.writeObject(emp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static Empresa crearEmpresa() {
        String CIF = "";
        String nombre = "";
        LocalDate fechaFundacion;

        Empresa empresa;

        Scanner sc = new Scanner(System.in);

        MyUtils.print("Creando Empresa");

        do {
            try {

                MyUtils.printHere("Ingrese CIF de la empresa a guardar: ");
                CIF = sc.nextLine();
                Empresa.validacionCIF(CIF);

            } catch (CIFIncorrectoException e) {

                MyUtils.print(e.getMessage());
                CIF = "";

            }

        } while (CIF.equals(""));

        do {
            try {

                MyUtils.printHere("Ingrese Nombre de la empresa a guardar: ");
                nombre = MyUtils.inputRequest(Patrones.NOT_NULL);

            } catch (InputIncorrectoException e) {

                MyUtils.print("No dejes el nombre vacio");
                nombre = "";
            }
        } while (nombre.equals(""));

        MyUtils.print("Ingrese la fecha de fundacion de la empresa a guardar: ");
        fechaFundacion = MyUtils.requestDate();

        try {
        empresa = new Empresa(nombre, CIF, fechaFundacion);
        } catch (CIFIncorrectoException e) {

            MyUtils.print(e.getMessage());
            MyUtils.print("No se puede crear una empresa nula");
            empresa = null;
        }


        return empresa;
    }

    private static void registrarEmpleadoEnEmpresa(Empresa empresa) {
        Scanner sc = new Scanner(System.in);
        boolean exito = false;

        MyUtils.print("Registrando empresa en la empresa" +
                "\n1. Registrar Director" +
                "\n2. Registrar Gerente" +
                "\n3. Registrar Trabajador");
        MyUtils.printHere("Escoja un tipo de Empleado: ");

        char option = sc.nextLine().charAt(0);

        switch (option) {

            case '1':

                MyUtils.print("Ingresando Director");
                Director director = ingresarDirector();

                exito = empresa.registrarDirector(director);

                if (exito) {
                    MyUtils.print("Director registrado exitosamente");
                } else {
                    MyUtils.print("No se pudo Registrar este Director");
                }

                break;

            case '2':

                MyUtils.print("Ingresando Gerente");
                GerenteDep gerente = ingresarGerente();

                exito = empresa.registrarGerente(gerente);

                if (exito) {
                    MyUtils.print("Gerente registrado exitosamente");
                } else {
                    MyUtils.print("No se pudo Registrar este Gerente");
                }

                break;

            case '3':

                MyUtils.print("Ingresando Trabajador");

                Trabajador trabajador = ingresarTrabajador();

                exito = empresa.registrarTrabajador(trabajador);

                if (exito) {
                    MyUtils.print("Trabajador registrado exitosamente");
                } else {
                    MyUtils.print("No se pudo Registrar este Trabajador");
                }

                break;
        }
    }

    private static Director ingresarDirector() {
        Director director = null;

        Trabajador trabajador = ingresarTrabajador();
        String numTelefono = "";
        String coche = "";

        do {
            try {

                MyUtils.printHere("\nEscribe el Numero de telefono: ");
                numTelefono = MyUtils.inputRequest(Patrones.NUMERO_TELEFONO_FORM);

            }  catch (InputIncorrectoException e) {

                MyUtils.print("Numero incorrecto, esta vacio");
                numTelefono = "";

            }
        } while (numTelefono.equals(""));

        do {
            try {

                MyUtils.printHere("\nEscribe el modelo de coche: ");
                coche = MyUtils.inputRequest(Patrones.NOT_NULL);

            }  catch (InputIncorrectoException e) {

                MyUtils.print("Coche incorrecto, esta vacio");
                coche = "";

            }
        } while (coche.equals(""));

        director = new Director(trabajador.getNombre(), trabajador.getFechaNacimiento(), trabajador.getDNI(), trabajador.getDireccion(),
                trabajador.getEmail(), trabajador.getSalario(), trabajador.getDept(), numTelefono, coche);
        return director;
    }

    private static GerenteDep ingresarGerente() {
        GerenteDep gerente = null;
        Scanner sc = new Scanner(System.in);

        Trabajador trabajador = ingresarTrabajador();

        Gerencia gerencia = Gerencia.INFORMATICA;


        if (trabajador.getDept().name().equalsIgnoreCase("DIRECCION")) {
            MyUtils.print("Departamento no Valido, escoge un nuevo departamento para el Gerente");

            MyUtils.print("\nGerencia: ");
            for (Gerencia de : Gerencia.values()) {

                MyUtils.print(de.ordinal() + ". " + de.name());
            }

            MyUtils.printHere("\nEscribe el indice Gerencia (Por defecto INFORMATICA): ");

            try {
                int num = sc.nextInt();

                for (Gerencia de : Gerencia.values()) {

                    if (de.ordinal() == num) {
                        gerencia = de;
                        trabajador.setDept(Departamento.valueOf(gerencia.name()));
                    }
                }
            } catch (InputMismatchException e) {

                gerencia = Gerencia.INFORMATICA;
                trabajador.setDept(Departamento.INFORMATICA);
            }
        } else {

            gerencia = Gerencia.valueOf(trabajador.getDept().name());
        }

         gerente = new GerenteDep(trabajador.getNombre(), trabajador.getFechaNacimiento(), trabajador.getDNI(), trabajador.getDireccion(),
                trabajador.getEmail(), trabajador.getSalario(), trabajador.getDept(), gerencia);
        return gerente;
    }

    private static Trabajador ingresarTrabajador() {
        Trabajador trabajador = null;
        Scanner sc = new Scanner(System.in);

        String nombre;
        LocalDate fechaNacimiento;
        String DNI;
        String direccion;
        String email;
        double salario;
        Departamento departamento = Departamento.INFORMATICA;;

        do {
            try {

                MyUtils.printHere("\nEscribe el Nombre: ");
                nombre = MyUtils.inputRequest(Patrones.NOMBRE_PERSONA_FORM);

            }  catch (InputIncorrectoException e) {

                MyUtils.print("Nombre incorrecto, pon nombre y apellidos");
                nombre = "";

            }
        } while (nombre.equals(""));

        fechaNacimiento = MyUtils.requestDate();

        do {
            try {

                MyUtils.printHere("\nEscribe el DNI: ");
                DNI = MyUtils.inputRequest(Patrones.DNI_FORM);

            }  catch (InputIncorrectoException e) {

                MyUtils.print("DNI incorrecto, revisalo");
                DNI = "";

            }
        } while (DNI.equals(""));

        do {
            try {

                MyUtils.printHere("\nEscribe la direccion: ");
                direccion = MyUtils.inputRequest(Patrones.NOT_NULL);

            }  catch (InputIncorrectoException e) {

                MyUtils.print("Direccion incorrecta, esta vacia");
                direccion = "";

            }
        } while (direccion.equals(""));

        do {
            try {

                MyUtils.printHere("\nEscribe la direccion de correo: ");
                email = MyUtils.inputRequest(Patrones.EMAIL_FORM);

            }  catch (InputIncorrectoException e) {

                MyUtils.print("Email incorrecto, revisalo");
                email = "";

            }
        } while (email.equals(""));

        boolean valido = false;
        do {
            try {

                MyUtils.printHere("\nEscribe el salario del empleado: ");
                salario = sc.nextDouble();
                valido = true;

            }  catch (InputMismatchException e) {

                MyUtils.print("Salario incorrecto, revisalo");
                sc.nextLine();
                salario = 0;
            }
        } while (!valido);

        MyUtils.print("\nDepartamentos: ");
        for (Departamento de : Departamento.values()) {

            MyUtils.print(de.ordinal() + ". " + de.name());
        }

        MyUtils.printHere("\nEscribe el indice Departamento (Por defecto INFORMATICA): ");

        try {
            int num = sc.nextInt();

            for (Departamento de : Departamento.values()) {

                if (de.ordinal() == num) {
                    departamento = de;
                }
            }
        } catch (InputMismatchException e) {

            departamento = Departamento.INFORMATICA;
        }

        trabajador = new Trabajador(nombre, fechaNacimiento, DNI, direccion, email, salario, departamento);

        return trabajador;
    }
}