import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {


        //Definicion Variables
        String inputOriginal;
        int opcion;

        Pattern patronCod = Pattern.compile("[A-Za-z]{2}[0-9]{7}"); // 2 letras y 7 digitos
        Pattern patronDNI = Pattern.compile("[0-9]{8}[A-Za-z]"); // 2 letras y 7 digitos

        //Menu texto
        String tituloPrograma = "MENU KONOHATECH";
        String menu[] = new String[10];
        menu[0] = "Crear Equipo";
        menu[1] = "Registrar Ninja en el Equipo";
        menu[2] = "Mostrar el número de ninjas totales en la Aldea";
        menu[3] = "Mostrar información de un equipo";
        menu[4] = "Mostrar información de todos los equipos";
        menu[5] = "Mostrar información de toda la Aldea";
        menu[6] = "Calcular estadísticas de toda la Aldea";
        menu[7] = "Cambiar ninja de un equipo";
        menu[8] = "Eliminar ninja de la aldea";
        menu[9] = "Salir de la aplicacion";
        String peticionDato = "Por favor, escribe una opcion del 1 al " + menu.length + ":";


        //Patrones
        Pattern menuOpciones = Pattern.compile("[0-9]{1,2}");

        //Creacion de la Aldea
        Aldea aldea = fundarAldea();

        do {
            MisUtilidades.creacionMenu(tituloPrograma,menu,peticionDato);
            inputOriginal = MisUtilidades.pedirDatoConPatron(menuOpciones, "Introduciste un numero erroneo. Escribe un numero del 1 al "
                    + menu.length + ":");
            opcion = Integer.parseInt(inputOriginal);

            switch (opcion) {
                case 1:
                    crearEquipo(aldea);
                    MisUtilidades.esperar();
                    break;
                case 2:
                    creacionRegistroNinjaEnEquipo(aldea, patronCod);
                    break;
                case 3:
                    MisUtilidades.imprimir("Con el Kage y los Senseis, hay " + aldea.calcularNinjasAldea() + " ninjas totales");
                    MisUtilidades.esperar();
                    break;
                case 4:
                    mostrarInfoEquipo(aldea);
                    MisUtilidades.esperar();
                    break;
                case 5:
                    mostrarInfoTodosEquipos(aldea);
                    MisUtilidades.esperar();
                    break;
                case 6:
                    MisUtilidades.imprimir("Esta es la informacion de la Aldea");
                    MisUtilidades.imprimir(aldea.toString());
                    MisUtilidades.esperar();
                    break;
                case 7:
                    MisUtilidades.imprimir("Estas son las estadisticas de toda la Aldea:");
                    MisUtilidades.imprimir(aldea.calcularEstadisticasTodaAldea());
                    MisUtilidades.esperar();
                    break;
                case 8:
                    cambioNinjaEquipo(aldea, patronCod, patronDNI);
                    break;
                case 9:
                    eliminacionNinja(aldea, patronCod, patronDNI);
                    break;
                case 10:
                    MisUtilidades.imprimir("Saliendo del Programa ... ¡Adios!");
                    break;
                default:
                    MisUtilidades.imprimir("Hubo un error al introducir " + inputOriginal + ", intentelo de nuevo");
                    MisUtilidades.esperar();
                    break;
            }
        } while (opcion != 10);
    }

    private static void eliminacionNinja(Aldea aldea, Pattern patronCod, Pattern patronDNI) {
        Equipo equipoSeleccionado;
        Ninja ninjaSeleccionado;
        String dni;
        String codigo;
        if (aldea.getNumEquipos() != 0) {
            MisUtilidades.imprimir("Seleccione a un equipo");
            MisUtilidades.imprimir("Introduce el Codigo del Equipo:");
            codigo = MisUtilidades.pedirDatoConPatron(patronCod, "Codigo Mal introducido, 2 letras y 7 numeros:").toUpperCase();

            equipoSeleccionado = aldea.buscarEquipoPorCodigo(codigo);
            if (equipoSeleccionado != null && equipoSeleccionado.getNumNinjas() != 0) {
                MisUtilidades.imprimir("Selecciona a un ninja del equipo");
                MisUtilidades.imprimir("Introduce el DNI del Ninja:");
                dni = MisUtilidades.pedirDatoConPatron(patronDNI, "DNI mal introducido, 8 numeros y 1 letra");

                ninjaSeleccionado = equipoSeleccionado.buscarNinjaPorDNI(dni);

                if(equipoSeleccionado.eliminarNinja(ninjaSeleccionado)) {
                    MisUtilidades.imprimir("Se elimino con exito el Ninja");
                }
                else {
                    MisUtilidades.imprimir("No se pudo eliminar el ninja");
                }
            }
            else {
                MisUtilidades.imprimir("No se pudo encontrar o no tienes ninjas");
            }
        }
        else {
            MisUtilidades.imprimir("No tiene equipos");
        }
        MisUtilidades.esperar();
    }

    private static void cambioNinjaEquipo(Aldea aldea, Pattern patronCod, Pattern patronDNI) {
        Ninja ninjaSeleccionado;
        Equipo equipoSeleccionado;
        String dni;
        String codigo;
        if (aldea.getNumEquipos() > 1) {
            MisUtilidades.imprimir("Seleccione a un equipo");
            MisUtilidades.imprimir("Introduce el Codigo del Equipo:");
            codigo = MisUtilidades.pedirDatoConPatron(patronCod, "Codigo Mal introducido, 2 letras y 7 numeros:").toUpperCase();

            equipoSeleccionado = aldea.buscarEquipoPorCodigo(codigo);
            if (equipoSeleccionado != null && equipoSeleccionado.getNumNinjas() != 0) {
                MisUtilidades.imprimir("Selecciona a un ninja del equipo");
                MisUtilidades.imprimir("Introduce el DNI del Ninja:");
                dni = MisUtilidades.pedirDatoConPatron(patronDNI, "DNI mal introducido, 8 numeros y 1 letra").toUpperCase();

                ninjaSeleccionado = equipoSeleccionado.buscarNinjaPorDNI(dni);

                if(equipoSeleccionado.eliminarNinja(ninjaSeleccionado)) {
                    MisUtilidades.imprimir("Se elimino con exito el Ninja del anterior");
                    MisUtilidades.imprimir("Seleccione el nuevo equipo del Ninja");
                    MisUtilidades.imprimir("Introduce el Codigo del Equipo:");
                    codigo = MisUtilidades.pedirDatoConPatron(patronCod, "Codigo Mal introducido, 2 letras y 7 numeros:").toUpperCase();
                    equipoSeleccionado = aldea.buscarEquipoPorCodigo(codigo);
                    if(equipoSeleccionado.registrarNinja(ninjaSeleccionado)) {
                        MisUtilidades.imprimir("Se cambio al Ninja del equipo");
                    }
                    else {
                        MisUtilidades.imprimir("No se pudo encontrar el equipo, por favor, intenta registrar el Ninja desde 0");
                    }
                }
                else {
                    MisUtilidades.imprimir("No se pudo mover el ninja");
                }

            }
            else {
                MisUtilidades.imprimir("No se pudo encontrar o no tienes ninjas");
            }
        }
        else {
            MisUtilidades.imprimir("No tienes suficientes equipos");
        }
        MisUtilidades.esperar();
    }

    private static void creacionRegistroNinjaEnEquipo(Aldea aldea, Pattern patronCod) {
        String codigo;
        Equipo equipoSeleccionado;
        if (aldea.getNumEquipos() != 0) {
            MisUtilidades.imprimir("Seleccione a un equipo");
            MisUtilidades.imprimir("Introduce el Codigo del Equipo:");
            codigo = MisUtilidades.pedirDatoConPatron(patronCod, "Codigo Mal introducido, 2 letras y 7 numeros:").toUpperCase();

            equipoSeleccionado = aldea.buscarEquipoPorCodigo(codigo);
            crearNinja(equipoSeleccionado);
        }
        else {
            MisUtilidades.imprimir("No tiene equipos");
        }
        MisUtilidades.esperar();
    }

    //METODOS:
    public static Aldea fundarAldea() {
        //Variables
        Aldea nuevaAldea;
        Scanner sc = new Scanner(System.in);
        String codAldea, nombreAldea, equiposMaximosIntroducidos;
        int equiposMaximos;

        //Objetos
        Sensei kage;

        //Patrones
        Pattern patronCodAldea = Pattern.compile("[A-Za-z]{5}[0-9]{5}"); //5 letras, 5 numeros
        Pattern patronEquiposMaximos = Pattern.compile("[0-9]+"); //solo numeros

        //Peticion de datos de la Aldea
        MisUtilidades.imprimir("Empezamos a crear nuestra Aldea");

        //Codigo de la Aldea
        MisUtilidades.imprimir("Empieza introduciendo el codigo del aldea, escribe 5 letras y 5 numeros:");
        codAldea = MisUtilidades.pedirDatoConPatron(patronCodAldea, "Patron mal escrito. Son 5 letras" +
                "y 5 numeros para el Codigo de la aldea: ").toUpperCase();

        //Nombre de la Aldea
        MisUtilidades.imprimir("Ahora vamos a escribir el nombre de la Aldea, escribe el nombre que quieras:");
        nombreAldea = sc.nextLine();

        //Cantidad de Equipos en la Aldea
        MisUtilidades.imprimir("Introduce ahora la cantidad de equipos maximos que la aldea va a tener:");

        do {
            equiposMaximosIntroducidos = MisUtilidades.pedirDatoConPatron(patronEquiposMaximos,"La cantidad de" +
                    "Equipos Maximos de la aldea debe ser un numero entero mayor que 0: ");
            equiposMaximos = Integer.parseInt(equiposMaximosIntroducidos);

            if (equiposMaximos < 1) {
                MisUtilidades.imprimir("El dato introducido es incorrecto, introduce un numero entero mayor que 0");
            }
        } while (equiposMaximos < 1);

        //Kage Aldea
        MisUtilidades.imprimir("Ahora vamos a crear al Ninja Kage, lider de la aldea");
        kage = crearKage();

        //Creacion de la Aldea
        nuevaAldea = new Aldea(nombreAldea,codAldea, kage, equiposMaximos);
        MisUtilidades.imprimir("Se ha creado la nueva aldea");

        return nuevaAldea;
    }

    private static Sensei crearKage() {
        //Variables
        String nombreKage, kageDNI, kageNum, chakraIntroducido,tecnicaSecretaKage, direccionKage;
        double estrategiaKage, liderazgoKage, ataqueKage, defensaKage;
        Chakra chakraKage = Chakra.AGUA; //Inicializacion, va a ser reescrito
        Rango rangoKage = Rango.KAGE;
        LocalDate nacimientoKage;

        Scanner sc = new Scanner(System.in);

        //Sensei
        Sensei kage;

        //Patrones
        Pattern patronNombre = Pattern.compile("[A-Z][a-z A-Z]+"); //Solo letras y espacios
        Pattern patronDNI = Pattern.compile("[0-9]{8}[A-Za-z]"); // 8 numeros y 1 letra
        Pattern patronNumKage = Pattern.compile("[0-9]{9}"); //Numeros tienen 9 digitos
        Pattern patronChakra = Pattern.compile("[0-4]");

        //Nombre del Kage
        MisUtilidades.imprimir("Introduce el Nombre y Apellido del Kage:");
        nombreKage = MisUtilidades.pedirDatoConPatron(patronNombre,
                "El nombre del Kage tiene que tener al menos su primera letra en Mayuscula");

        //DNI del Kage
        MisUtilidades.imprimir("Introduce el DNI del Kage:");
        kageDNI = MisUtilidades.pedirDatoConPatron(patronDNI, "DNI mal introducido, 8 numeros y 1 letra:").toUpperCase();

        //Direccion de Kage
        MisUtilidades.imprimir("Introduce donde vive el Kage:");
        direccionKage = sc.nextLine();

        //Num Contacto
        MisUtilidades.imprimir("Introduce el Numero de Contacto del Kage:");
        kageNum = MisUtilidades.pedirDatoConPatron(patronNumKage, "El numero de contacto del Kage tiene que tener 9 digitos");

        //Insercion de la fecha de Nacimiento
        MisUtilidades.imprimir("Introduce la fecha de nacimiento del cliente:");
        nacimientoKage = pedirFechaNacimiento();

        //Chakra
        MisUtilidades.imprimir("Chakras para el Kage:");
        for (Chakra chakra : Chakra.values()) {
            MisUtilidades.imprimir(chakra.ordinal() + " " + chakra.name());
        }

        MisUtilidades.imprimir("Introduce el Indice del Chakra:");
        chakraIntroducido = MisUtilidades.pedirDatoConPatron(patronChakra,"Seleccione un numero del 0 al 4 (indice):");
        for (Chakra chakra : Chakra.values()) {
            if (chakra.ordinal() == Integer.parseInt(chakraIntroducido)) {
                chakraKage = chakra;
            }
        }

        //Tecnica secreta
        MisUtilidades.imprimir("Introduce la Tecnica Secreta:");
        tecnicaSecretaKage = sc.nextLine();

        //Estrategia del Kage
        MisUtilidades.imprimir("Introduce ahora la estrategia del Kage (numero de 0 a 100):");
        estrategiaKage = pedirDecimal_0_a_100();

        //Liderazgo del Kage
        MisUtilidades.imprimir("Introduce ahora el liderazgo del Kage (numero de 0 a 100):");
        liderazgoKage = pedirDecimal_0_a_100();

        //Ataque del Kage
        MisUtilidades.imprimir("Introduce ahora el ataque del Kage (numero de 0 a 100):");
        ataqueKage = pedirDecimal_0_a_100();

        //Defensa del Kage
        MisUtilidades.imprimir("Introduce ahora la defensa del Kage (numero de 0 a 100):");
        defensaKage = pedirDecimal_0_a_100();

        //Constructor final del Kage
        kage = new Sensei(nombreKage,kageDNI,nacimientoKage, direccionKage, kageNum, chakraKage, rangoKage, tecnicaSecretaKage, ataqueKage,
                defensaKage,estrategiaKage,liderazgoKage);
        MisUtilidades.imprimir("Se ha creado el Kage");
        return kage;
    }

    private static double pedirDecimal_0_a_100() {
        String entrada;
        double salida;
        Pattern patronDecimal = Pattern.compile("[0-9]+.?[0-9]+"); //Decimales positivos solo

        do {
            entrada =  MisUtilidades.pedirDatoConPatron(patronDecimal, "Introduce un Numero decimal (usa . para decimales)");
            entrada = entrada.replace(',','.');

            salida = Double.parseDouble(entrada);

            if (salida < 0 || salida > 100) {
                MisUtilidades.imprimir("El valor introducido esta fuera de rango. Introduce un valor entre 0 y 100:");
            }
        } while (salida < 0 || salida > 100);

        return salida;
    }

    private static LocalDate pedirFechaNacimiento() {
        Pattern patronYear = Pattern.compile("[0-9]{4}");
        Pattern patronMes = Pattern.compile("[0-9]{2}");
        Pattern patronDia = Pattern.compile("[0-9]{2}");

        String fechaYear, fechaMes, fechaDia;
        boolean diaValido;

        //Insercion Anio (evitando caracteres que den problemas)
        MisUtilidades.imprimir("Anio (year) de nacimiento:");
        do {
            fechaYear = MisUtilidades.pedirDatoConPatron(patronYear,"Anio introducida no valido, introduce uno de 4 digitos:");
            if (Integer.parseInt(fechaYear) < 1800) {
                MisUtilidades.imprimir("Rango de anio no Valido, ha de ser al menos del 1800, intentelo de nuevo:");
            }
        } while (Integer.parseInt(fechaYear) < 1800);

        //Insercion Mes
        MisUtilidades.imprimir("Mes (month) de nacimiento:");
        do {
            fechaMes = MisUtilidades.pedirDatoConPatron(patronMes,"Mes introducido no valido, introduce uno de 2 digitos:");
            if (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12) {
                MisUtilidades.imprimir("Rango de mes no Valido, tiene que ser del 1 al 12, intentelo de nuevo:");
            }
        } while (Integer.parseInt(fechaMes) < 1 || Integer.parseInt(fechaMes) > 12);

        //Insercion Dia
        MisUtilidades.imprimir("Dia (day) de nacimiento:");
        do {
            fechaDia = MisUtilidades.pedirDatoConPatron(patronDia,"Dia introducido no valido, introduce uno de 2 digitos:");
            switch (Integer.parseInt(fechaMes)) {
                case 1,3,5,7,8,10,12:
                    diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 31;
                    break;
                case 4,6,9,11:
                    diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 30;
                    break;
                case 2:
                    diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 28;
                    if (Year.isLeap(Long.parseLong(fechaYear))) {
                        diaValido = Integer.parseInt(fechaDia) < 1 || Integer.parseInt(fechaDia) > 29;
                    }
                    break;
                default:
                    MisUtilidades.imprimir("Algo fue mal al comprobar dia, notifica a un desarrollador");
                    diaValido = true;
                    break;
            }
            if (diaValido) {
                MisUtilidades.imprimir("Rango de dia no Valido, tiene que ser dependiendo de los dias del mes, intentelo de nuevo:");
            }
        } while (diaValido);

        //Condicion de Salida, Dia Valido sea False

        //Construccion final del Dia
        LocalDate dt = LocalDate.of(Integer.parseInt(fechaYear), Integer.parseInt(fechaMes), Integer.parseInt(fechaDia));

        return dt;
    }

    private static Ninja crearNinja(Equipo equipo) {
        //Variables
        String nombreNinja, ninjaDNI, ninjaNum, chakraIntroducido, rangoIntroducido,tecnicaSecretaNinja, direccionNinja;
        double ataqueNinja, defensaNinja;
        Chakra chakraNinja = Chakra.AGUA; //Inicializacion, va a ser reescrito
        Rango rangoNinja = Rango.GENIN;//Inicializacion
        LocalDate nacimientoNinja;

        Scanner sc = new Scanner(System.in);

        //Sensei
        Ninja nuevoNinja;

        //Patrones
        Pattern patronNombre = Pattern.compile("[A-Z][a-z A-Z]+"); //Solo letras y espacios
        Pattern patronDNI = Pattern.compile("[0-9]{8}[A-Za-z]"); // 8 numeros y 1 letra
        Pattern patronNum = Pattern.compile("[0-9]{9}"); //Numeros tienen 9 digitos
        Pattern patronChakra = Pattern.compile("[0-4]"); //Opciones Chakra
        Pattern patronRango = Pattern.compile("[0-3]"); //Opciones Rango

        //Nombre del Ninja
        MisUtilidades.imprimir("Introduce el Nombre y Apellido del Ninja:");
        nombreNinja = MisUtilidades.pedirDatoConPatron(patronNombre,
                "El nombre del Ninja tiene que tener al menos su primera letra en Mayuscula");

        //DNI del Ninja
        MisUtilidades.imprimir("Introduce el DNI del Ninja:");
        ninjaDNI = MisUtilidades.pedirDatoConPatron(patronDNI, "DNI mal introducido, 8 numeros y 1 letra:").toUpperCase();

        //Direccion de Ninja
        MisUtilidades.imprimir("Introduce donde vive el Ninja:");
        direccionNinja = sc.nextLine();

        //Num Contacto
        MisUtilidades.imprimir("Introduce el Numero de Contacto del Ninja:");
        ninjaNum = MisUtilidades.pedirDatoConPatron(patronNum, "El numero de contacto del Ninja tiene que tener 9 digitos");

        //Insercion de la fecha de Nacimiento
        MisUtilidades.imprimir("Introduce la fecha de nacimiento del cliente:");
        nacimientoNinja = pedirFechaNacimiento();

        //Chakra
        MisUtilidades.imprimir("Chakras para el Ninja:");
        for (Chakra chakra : Chakra.values()) {
            MisUtilidades.imprimir(chakra.ordinal() + " " + chakra.name());
        }

        MisUtilidades.imprimir("Introduce el Indice del Chakra:");
        chakraIntroducido = MisUtilidades.pedirDatoConPatron(patronChakra,"Seleccione un numero del 0 al 4 (indice):");
        for (Chakra chakra : Chakra.values()) {
            if (chakra.ordinal() == Integer.parseInt(chakraIntroducido)) {
                chakraNinja = chakra;
            }
        }

        //Rango
        MisUtilidades.imprimir("Rangos para el Ninja:");
        for (Rango rango : Rango.values()) {
            if (rango.name() != "KAGE") {
                MisUtilidades.imprimir(rango.ordinal() + " " + rango.name());
            }
        }

        MisUtilidades.imprimir("Introduce el Indice del Rango:");
        rangoIntroducido = MisUtilidades.pedirDatoConPatron(patronRango,"Seleccione un numero del 0 al 3 (indice):");
        for (Rango rango : Rango.values()) {
            if (rango.ordinal() == Integer.parseInt(rangoIntroducido)) {
                rangoNinja = rango;
            }
        }

        //Tecnica secreta
        MisUtilidades.imprimir("Introduce la Tecnica Secreta:");
        tecnicaSecretaNinja = sc.nextLine();

        //Ataque del Ninja
        MisUtilidades.imprimir("Introduce ahora el ataque del Ninja (numero de 0 a 100):");
        ataqueNinja = pedirDecimal_0_a_100();

        //Defensa del Ninja
        MisUtilidades.imprimir("Introduce ahora la defensa del Ninja (numero de 0 a 100):");
        defensaNinja = pedirDecimal_0_a_100();

        //Constructor final del Ninja
        nuevoNinja = new Ninja(nombreNinja,ninjaDNI,nacimientoNinja, direccionNinja, ninjaNum, chakraNinja, rangoNinja, tecnicaSecretaNinja, ataqueNinja,
                defensaNinja);
        MisUtilidades.imprimir("Se ha creado el Ninja");

        nuevoNinja.toString();

        //Suma
        if (equipo.registrarNinja(nuevoNinja)) {
            MisUtilidades.imprimir("Se registro exitosamente al Ninja en " + equipo.getNombreEquipo());
        }
        else {
            MisUtilidades.imprimir("No se pudo. Revisa que el equipo no este lleno");
        }

        return nuevoNinja;
    }

    private static Sensei crearSensei() {
        //Variables
        String nombreSensei, senseiDNI, senseiNum, chakraIntroducido, rangoIntroducido,tecnicaSecretaSensei, direccionSensei;
        double estrategiaSensei, liderazgoSensei, ataqueSensei, defensaSensei;
        Chakra chakraSensei = Chakra.AGUA; //Inicializacion, va a ser reescrito
        Rango rangoSensei = Rango.GENIN;//Inicializacion
        LocalDate nacimientoSensei;

        Scanner sc = new Scanner(System.in);

        //Sensei
        Sensei nuevoSensei;

        //Patrones
        Pattern patronNombre = Pattern.compile("[A-Z][a-z A-Z]+"); //Solo letras y espacios
        Pattern patronDNI = Pattern.compile("[0-9]{8}[A-Za-z]"); // 8 numeros y 1 letra
        Pattern patronNum = Pattern.compile("[0-9]{9}"); //Numeros tienen 9 digitos
        Pattern patronChakra = Pattern.compile("[0-4]"); //Opciones Chakra
        Pattern patronRango = Pattern.compile("[0-3]"); //Opciones Rango

        //Nombre del Sensei
        MisUtilidades.imprimir("Introduce el Nombre y Apellido del Sensei:");
        nombreSensei = MisUtilidades.pedirDatoConPatron(patronNombre,
                "El nombre del Sensei tiene que tener al menos su primera letra en Mayuscula");

        //DNI del Sensei
        MisUtilidades.imprimir("Introduce el DNI del Sensei:");
        senseiDNI = MisUtilidades.pedirDatoConPatron(patronDNI, "DNI mal introducido, 8 numeros y 1 letra:").toUpperCase();

        //Direccion de Sensei
        MisUtilidades.imprimir("Introduce donde vive el Sensei:");
        direccionSensei = sc.nextLine();

        //Num Contacto
        MisUtilidades.imprimir("Introduce el Numero de Contacto del Sensei:");
        senseiNum = MisUtilidades.pedirDatoConPatron(patronNum, "El numero de contacto del Sensei tiene que tener 9 digitos");

        //Insercion de la fecha de Nacimiento
        MisUtilidades.imprimir("Introduce la fecha de nacimiento del cliente:");
        nacimientoSensei = pedirFechaNacimiento();

        //Chakra
        MisUtilidades.imprimir("Chakras para el Sensei:");
        for (Chakra chakra : Chakra.values()) {
            MisUtilidades.imprimir(chakra.ordinal() + " " + chakra.name());
        }

        MisUtilidades.imprimir("Introduce el Indice del Chakra:");
        chakraIntroducido = MisUtilidades.pedirDatoConPatron(patronChakra,"Seleccione un numero del 0 al 4 (indice):");
        for (Chakra chakra : Chakra.values()) {
            if (chakra.ordinal() == Integer.parseInt(chakraIntroducido)) {
                chakraSensei = chakra;
            }
        }

        //Rango
        MisUtilidades.imprimir("Rangos para el Sensei:");
        for (Rango rango : Rango.values()) {
            if (rango.name() != "KAGE") {
                MisUtilidades.imprimir(rango.ordinal() + " " + rango.name());
            }
        }

        MisUtilidades.imprimir("Introduce el Indice del Rango:");
        rangoIntroducido = MisUtilidades.pedirDatoConPatron(patronRango,"Seleccione un numero del 0 al 3 (indice):");
        for (Rango rango : Rango.values()) {
            if (rango.ordinal() == Integer.parseInt(rangoIntroducido)) {
                rangoSensei = rango;
            }
        }

        //Tecnica secreta
        MisUtilidades.imprimir("Introduce la Tecnica Secreta:");
        tecnicaSecretaSensei = sc.nextLine();

        //Estrategia del Sensei
        MisUtilidades.imprimir("Introduce ahora la estrategia del Sensei (numero de 0 a 100):");
        estrategiaSensei = pedirDecimal_0_a_100();

        //Liderazgo del Sensei
        MisUtilidades.imprimir("Introduce ahora el liderazgo del Sensei (numero de 0 a 100):");
        liderazgoSensei = pedirDecimal_0_a_100();

        //Ataque del Sensei
        MisUtilidades.imprimir("Introduce ahora el ataque del Sensei (numero de 0 a 100):");
        ataqueSensei = pedirDecimal_0_a_100();

        //Defensa del Sensei
        MisUtilidades.imprimir("Introduce ahora la defensa del Sensei (numero de 0 a 100):");
        defensaSensei = pedirDecimal_0_a_100();

        //Constructor final del Sensei
        nuevoSensei = new Sensei(nombreSensei,senseiDNI,nacimientoSensei, direccionSensei, senseiNum, chakraSensei, rangoSensei, tecnicaSecretaSensei, ataqueSensei,
                defensaSensei,estrategiaSensei,liderazgoSensei);
        MisUtilidades.imprimir("Se ha creado el Sensei");

        return nuevoSensei;
    }

    private static Equipo crearEquipo(Aldea aldea) {
        //Variables
        String nombreEquipo, codigo, miembrosIntroducidos;
        int maximoNinjas;

        Scanner sc = new Scanner(System.in);

        //Sensei
        Sensei nuevoSensei;

        Equipo nuevoEquipo = null;

        //Patrones
        Pattern patronNombre = Pattern.compile("[A-Z][a-z A-Z]+"); //Solo letras y espacios
        Pattern patronCod = Pattern.compile("[A-Za-z]{2}[0-9]{7}"); // 2 letras y 7 digitos
        Pattern patronEquipo = Pattern.compile("[0-9]+"); //solo numeros

        //Nombre del Sensei
        MisUtilidades.imprimir("Introduce el nombre del Equipo:");
        nombreEquipo = MisUtilidades.pedirDatoConPatron(patronNombre,
                "El nombre del Equipo tiene que tener al menos su primera letra en Mayuscula");

        //DNI del Sensei
        MisUtilidades.imprimir("Introduce el Codigo del Equipo:");
        codigo = MisUtilidades.pedirDatoConPatron(patronCod, "Codigo Mal introducido, 2 letras y 7 numeros:").toUpperCase();

        //Sensei
        MisUtilidades.imprimir("Ahora crea el Sensei del Equipo:");
        nuevoSensei = crearSensei();

        //Maximo del Equipo
        MisUtilidades.imprimir("Introduce ahora la cantidad de ninjas que el equipo va a tener:");

        do {
            miembrosIntroducidos = MisUtilidades.pedirDatoConPatron(patronEquipo,"La cantidad de" +
                    "Ninjas debe ser un numero entero mayor que 0: ");
            maximoNinjas = Integer.parseInt(miembrosIntroducidos);

            if (maximoNinjas < 1) {
                MisUtilidades.imprimir("El dato introducido es incorrecto, introduce un numero entero mayor que 0");
            }
        } while (maximoNinjas < 1);


        //Constructor final del Equipo
        boolean valido = true;

        for (int i = 0; i < aldea.getNumEquipos(); i++) {
            if (aldea.getEquiposRegistrados()[i].getCodigo().equals(codigo)) {
                MisUtilidades.imprimir("Equipo ya existente");
                valido = false;
            }
        }

        //Si no existe, crear
        if (valido) {
            nuevoEquipo = new Equipo(nombreEquipo, codigo, nuevoSensei, maximoNinjas);
            if(aldea.registrarEquipo(nuevoEquipo)) {
                MisUtilidades.imprimir("Equipo registrado correctamente");
            }
            else {
                MisUtilidades.imprimir("No se pudo registrar correctamente, equipo lleno");
            }
            MisUtilidades.imprimir("Se ha creado el Equipo");
        }

        return nuevoEquipo;
    }

    private static void mostrarInfoEquipo(Aldea aldea) {
        Pattern patronCod = Pattern.compile("[A-Za-z]{2}[0-9]{7}"); // 2 letras y 7 digitos

        MisUtilidades.imprimir("Introduce el Codigo del Equipo:");
        String codigo = MisUtilidades.pedirDatoConPatron(patronCod, "Codigo Mal introducido, 2 letras y 7 numeros:").toUpperCase();

        Equipo equipoEncontrado = aldea.buscarEquipoPorCodigo(codigo);

        MisUtilidades.imprimir("Este es el equipo:");
        if (equipoEncontrado != null) {
            MisUtilidades.imprimir(equipoEncontrado.toString());
        }
        else {
            MisUtilidades.imprimir("No se encontro el equipo");
        }
    }

    private static void mostrarInfoTodosEquipos(Aldea aldea) {
        MisUtilidades.imprimir("Introduce el Codigo del Equipo:");

        MisUtilidades.imprimir("Estos son todos los equipos:");
        MisUtilidades.imprimir(aldea.mostrarEquiposRegistrados());
    }
}