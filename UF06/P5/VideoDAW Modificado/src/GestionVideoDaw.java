import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GestionVideoDaw {

    //Patrones
    private static final Pattern codForm = Pattern.compile("P-[0-9]{4,}");
    private static final Pattern menuInputPattern = Pattern.compile("[1-9]");
    private static final Pattern nombreForm = Pattern.compile("[A-Z][a-z]+ [A-Z a-z]+");
    private static final Pattern dniForm = Pattern.compile("[0-9]{8}[A-Za-z]");
    private static final Pattern notNull = Pattern.compile("^.+");
    private static final Pattern seleccionform = Pattern.compile("[1-9][0-9]*");

    public static void main(String[] args) {

        //Comienzo de codigo
        List<VideoDaw> coleccionVideoClubs = new ArrayList<>();

        //Path de Archivos
        String pathFile = ".\\src\\resources\\";
        String fileData = "archivos_videoclub.dat";
        String fileConfig = "config.dat";

        MyUtils.print("BIENVENIDO AL VIDEOCLUB\n");

        //Configuracion
        try (FileInputStream fis = new FileInputStream(pathFile + fileConfig);
            DataInputStream dis = new DataInputStream(fis)) {

            //Lectura del valor
            boolean actualizarConfig = Articulo.configCodGeneration(dis.readInt()) && Cliente.configCodGeneration(dis.readInt());

            if (actualizarConfig) {
                MyUtils.print("Se la logrado cargar la configuracion");
            }
            else {
                MyUtils.print("Se mantiene la configuracion antigua, la nueva es desactualizada");
            }

            //Errores
        } catch (FileNotFoundException e) {

            MyUtils.print("El archivo '" + fileConfig + "' no existe, creando... ");
            File newConfigFile = new File(pathFile+fileConfig);

            try {

                if(newConfigFile.createNewFile()) {
                    MyUtils.print("Se ha creado el Archivo " + fileConfig);
                } else {

                    MyUtils.print("No se ha creado el archivo " + fileConfig);
                }

            } catch (IOException ex) {
                MyUtils.print("Error: " + ex.getMessage());
            }

        } catch (IOException e) {
            MyUtils.print("Ha habido un Error en el I/O de " +  fileConfig);
            MyUtils.print(e.getMessage());
        }

        boolean eof = false;
        try (FileInputStream fis = new FileInputStream(pathFile + fileData);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (!eof) {

                VideoDaw temp = (VideoDaw) ois.readObject();
                coleccionVideoClubs.add(temp);

            }

        } catch (EOFException e) {

            eof = true;
        } catch (FileNotFoundException e) {

            MyUtils.print("El archivo '" + fileData + "' no existe, creando... ");
            File newDataFile = new File(pathFile+fileData);

            try {

                if(newDataFile.createNewFile()) {
                    MyUtils.print("Se ha creado el Archivo " + fileData);
                } else {

                    MyUtils.print("No se ha creado el archivo " + fileData);
                }

            } catch (IOException ex) {
                MyUtils.print("Error: " + ex.getMessage());
            }

        } catch (IOException e) {

            MyUtils.print("Ha habido un Error en el I/O de " +  fileData);
            MyUtils.print(e.getMessage());

        } catch (ClassNotFoundException e) {
            MyUtils.print("Ha habido un Error en la lectura del archivo");
        }


        //Holders de Objetos
        VideoDaw miVideoClub;

        //Inicializacion de Archivos

        //Creacion VideoClub inicial
        if (coleccionVideoClubs.isEmpty()) {
            registroDeVideoDaw(coleccionVideoClubs);
        }

        //Seleccion por defecto
        miVideoClub = coleccionVideoClubs.get(0);

        //Input variables
        String inputMainMenu;
        char option;

        //Menu
        String[] menuGestionVideoDaw = new String[9];
        menuGestionVideoDaw[0] = "Crear y Registrar VideoClub en la franquicia";
        menuGestionVideoDaw[1] = "Seleccionar VideoClub en la franquicia";
        menuGestionVideoDaw[2] = "Registrar Articulo en VideoClub";
        menuGestionVideoDaw[3] = "Crear y Registrar Cliente en VideoClub";
        menuGestionVideoDaw[4] = "Alquilar";
        menuGestionVideoDaw[5] = "Devolver";
        menuGestionVideoDaw[6] = "Dar de Baja Cliente";
        menuGestionVideoDaw[7] = "Dar de Baja Articulo";
        menuGestionVideoDaw[8] = "Guardar y Salir";


        do {

            //Display Menu Principal
            MyUtils.menuMaker("GESTION VIDEO DAW", menuGestionVideoDaw, "Introduce una opcion: ");
            inputMainMenu = MyUtils.inputRequestLoop(menuInputPattern, "Input de Menu no Valido. Introduce un numero del 1 al 9:");
            option = inputMainMenu.charAt(0);

            //Implementacion del Menu Principal con sus funcionalidades
            switch (option) {

                //Case 1 - Crear y registrar VideoClub en la franquicia
                case '1':

                    //Creacion de nuevo VideoClub
                    registroDeVideoDaw(coleccionVideoClubs);
                    break;

                //Case 2 - Seleccionar VideoClub en la franquicia
                case '2':

                    //Seleccion de VideoClub
                    String seleccionVideoClub;

                    //Comprobacion de que haya mas de 1 VideoClub
                    if (coleccionVideoClubs.size() > 1) {

                        //Muestra de los VideoClubs existentes
                        MyUtils.print("\nExisten mas de un VideoClub existence. Por favor, escoja el VideoCLub que desee usar, del 1 al " + coleccionVideoClubs.size());
                        for (int i = 0; i < coleccionVideoClubs.size(); i++) {
                            MyUtils.print(i + 1 + coleccionVideoClubs.get(i).getCIF() + " | " + coleccionVideoClubs.get(i).getDireccion());
                        }

                        //Seleccion del VideoClub
                        do {

                            try {
                                MyUtils.printHere("Seleccione un VideoClub por numero: ");
                                seleccionVideoClub = MyUtils.inputRequest(seleccionform);

                                if (Integer.parseInt(seleccionVideoClub) > coleccionVideoClubs.size() || Integer.parseInt(seleccionVideoClub) < 1) {

                                    MyUtils.print("Has escogido un numero fuera del rango, intenta introducir el indice del VideoClub");
                                } else {

                                    MyUtils.print("VideoClub seleccionado");
                                    miVideoClub = coleccionVideoClubs.get(Integer.parseInt(seleccionVideoClub) - 1);
                                }

                            } catch (InputIncorrectoException e) {

                                seleccionVideoClub = "-1";
                                MyUtils.print("El valor introducido no es un numero, intentalo de nuevo");
                            }

                        } while (Integer.parseInt(seleccionVideoClub) > coleccionVideoClubs.size() || Integer.parseInt(seleccionVideoClub) < 1);
                    }

                    //Si no hay dos o mas VideoClubs a escoger.
                    else {
                        MyUtils.print("\nSolo existe un VideoClub, escogido por defecto");
                    }

                    //Se muestra informacion al final del VideoClub activo
                    MyUtils.print(miVideoClub.toString());
                    MyUtils.pause();
                    //Fin Seleccion VideoClub
                    break;

                //Case 3 - Registrar articulo en VideoClub
                case '3':

                    //Inicio Registro de Nuevo Articulo en el VideoClub
                    MyUtils.print("\nRegistrando un nuevo articulo en el VideClub");
                    Articulo nuevoArticulo;

                    //Insercion de Titulo
                    String titulo = getStringConPatron("Introduce el Titulo del Articulo que desees crear: ", notNull,
                            "Titulo no valido, no se deben dejar el campo en blanco");


                    //Seleccion del Tipo de Articulo
                    nuevoArticulo = getNuevoArticulo(titulo);

                    //Suma del Articulo a nuestra coleccion de Articulos
                    boolean articuloRegistradoExito = miVideoClub.registrarArticulo(nuevoArticulo);
                    if (articuloRegistradoExito) {

                        //Si funciona
                        MyUtils.print("Registrando pelicula exitosamente");
                        MyUtils.print(nuevoArticulo.toString());
                    }
                    else {

                        //Si falla
                        MyUtils.print("Algo fue mal al crear el Articulo, revisa que no exista o que no haya otro problema");
                    }
                    MyUtils.pause();
                    //Fin de Registro de Articulos
                    break;

                //Case 4 - Crear y Registrar Cliente en el VideoClub
                case '4':

                    //Inicio Registro de Nuevo Cliente en el VideoClub
                    MyUtils.print("\nRegistrando un nuevo cliente en el VideClub");
                    Cliente nuevoCliente;

                    //Insercion del DNI
                    String dniCliente = getStringConPatron("Introduce el DNI del cliente: ", dniForm,
                            "El DNI debe ser apropiado, 8 numeros y 1 letra");

                    //Insercion del Nombre
                    String nombreCliente = getStringConPatron("Introduce el Nombre y Apellidos del Cliente: ", nombreForm,
                            "Nombre no valido, Revisa mayusculas, minusculas, y que tenga al menos un Apellido");


                    //Insercion de la direccion
                    String direccionCliente = getStringConPatron("Introduce la Direccion del cliente: ", notNull,
                            "Direccion no valida, no dejes este campo en blanco");;

                    //Insercion de la fecha de Nacimiento
                    MyUtils.print("Introduce la fecha de nacimiento del cliente:");
                    LocalDate dt = MyUtils.requestBirthday();

                    //Creacion del Cliente
                    nuevoCliente = new Cliente(dniCliente,nombreCliente,direccionCliente,dt);

                    //Suma del Cliente al Registro
                    boolean clienteRegistradoExito = miVideoClub.registrarCliente(nuevoCliente);
                    if (clienteRegistradoExito) {

                        //Si ha funcionado
                        MyUtils.print("Registrando cliente exitosamente");
                        MyUtils.print(nuevoCliente.toString());
                    }

                    else {

                        //Si no se pudo registrar
                        MyUtils.print("Algo fue mal al crear cliente, puede que ya exista o sea menor de edad, revisa e intentalo mas tarde");
                    }

                    MyUtils.pause();
                    //Fin de Registrar Cliente
                    break;

                //Case 5 - Alquilar
                case '5':

                    //Inicio Alquiler de Articulo
                    MyUtils.print("\nAlquilando Articulo en el VideoClub");

                    //Insercion del DNI para buscar Cliente
                    MyUtils.print("Introduce el DNI del cliente que desea alquilar:");
                    String dniAlquilando = getStringConPatron("Introduce el DNI del cliente: ", dniForm,
                            "El DNI debe ser apropiado, 8 numeros y 1 letra");

                    //Busqueda de Cliente
                    Cliente clienteAlquilando = miVideoClub.buscarCliente(dniAlquilando);
                    if (clienteAlquilando == null) {
                        MyUtils.print("No se ha podido encontrar al Cliente");
                        continue;
                    }

                    //Insercion del Codigo de Articulo
                    String codAlquilando = getStringConPatron("Introduce el codigo del articulo que desea alquilar: ", codForm,
                            "Direccion no valida, no dejes este campo en blanco");

                    //Busqueda de Articulo
                    Articulo articuloSelected = miVideoClub.buscarArticulo(codAlquilando);

                    //Si se consigue hacer la alquilacion de forma exitosa
                    boolean alquilacionExito = miVideoClub.alquilarArticulo(clienteAlquilando, articuloSelected);
                    if (alquilacionExito) {

                        //Si la operacion salio bien
                        MyUtils.print("Articulo " + articuloSelected.getTitulo() + " alquilada exitosamente por Cliente " + clienteAlquilando.getNombre());
                    }
                    else {

                        //Si hubo algun error
                        MyUtils.print("No se ha podido alquilar el Articulo, intentalo de nuevo y revisa fallos");
                    }

                    MyUtils.pause();
                    //Fin de Alquiler
                    break;

                //Case 6 - Devolver
                case '6':

                    //Inicio devolucion de Articulo
                    MyUtils.print("\nDevolviendo Articulo en el VideoClub");

                    //Insercion del DNI
                    String dniDevolviendo = getStringConPatron("Introduce el DNI del cliente que desea devolver: ", dniForm,
                            "El DNI debe ser apropiado, 8 numeros y 1 letra");;

                    //Busqueda de Cliente
                    Cliente clienteDevolviendo = miVideoClub.buscarCliente(dniDevolviendo);
                    if (clienteDevolviendo == null) {
                        MyUtils.print("No se ha podido encontrar al Cliente");
                        continue;
                    }

                    MyUtils.print("\nEsta es la lista de los articulos pendientes de devolver para cliente " +  clienteDevolviendo.getNombre() + ":");
                    MyUtils.print(clienteDevolviendo.mostrarArticulosAlquilados());

                    //Insercion del Codigo de Articulo
                    String codDevolvido = getStringConPatron("Introduce el codigo del articulo que se desea devolver: ", codForm,
                            "Introduce el codigo de Articulo apropiado, con formato tal que P-XXXX:");

                    //Busqueda de Articulo
                    Articulo articuloDevolvido = miVideoClub.buscarArticulo(codDevolvido);

                    MyUtils.print("\nEstos son los datos de la devolucion:");
                    MyUtils.print(clienteDevolviendo.toString());
                    MyUtils.print(articuloDevolvido.toString());

                    //Intento de devolver pelicula, imprimiendo si es un error o si fue exitoso, y advirtiendo si hubo mas de 48 horas
                    String devolucion = miVideoClub.devolverArticulo(clienteDevolviendo, articuloDevolvido);
                    if (!devolucion.equals("No se puede devolver el Articulo")) {
                    }

                    MyUtils.print(devolucion);

                    MyUtils.pause();
                    //Fin de devolucion de Articulo
                    break;

                //Case 7 - Dar de Baja Cliente
                case '7':

                    //Inicio dar Baja a Cliente
                    MyUtils.print("\nDando de Baja a un cliente en el VideClub");

                    //Muestra de Nuestros Clietnes
                    MyUtils.print("\nEstos son todos nuestros clientes:");
                    String clientes = miVideoClub.mostrarClientesRegistrados();
                    MyUtils.print(clientes);

                    //Si existen Clientes
                    if (!clientes.equals("No hay ningun registro de clientes")) {

                        //Insercion DNI dar de Baja
                        String dniClienteBaja = getStringConPatron("Introduce el DNI del cliente que desea dar de Baja: ", dniForm,
                            "El DNI debe ser apropiado, 8 numeros y 1 letra");

                        //Busqueda de Cliente
                        Cliente clienteBaja = miVideoClub.buscarCliente(dniClienteBaja);

                        //Intento de Dar de Baja Cliente
                        boolean bajaClienteExito = miVideoClub.darBajaCliente(clienteBaja);
                        if (bajaClienteExito) {

                            //Si se da de Baja exitosamente
                            MyUtils.print("Se ha dado de baja al cliente con exito");

                            MyUtils.print("\nEsta es su informacion:");
                            MyUtils.print(clienteBaja.toString());

                            MyUtils.print("\nHistorial de todos sus Articulos Alquilados:");
                            MyUtils.print(clienteBaja.mostrarHistorialArticulosAlquilados());

                        } else {

                            //Si no se pudo
                            MyUtils.print("No se ha podido dar de baja a ese cliente, revise que todos los datos esten correctos e intentalo de nuevo");
                        }
                    }

                    //Si no existen
                    else {

                        MyUtils.print("No hay nada a eliminar");
                    }

                    MyUtils.pause();
                    //Fin dada Baja Cliente
                    break;

                //Dar de Baja Articulo
                case '8':

                    //Inicio dada de Baja a Articulo
                    MyUtils.print("\nDando de Baja a un articulo en el VideClub");

                    //Muestra de Nuestras Articulos
                    MyUtils.print("\nEstos son todos nuestros articulos:");
                    String articulos = miVideoClub.mostrarArticulosRegistrados();
                    MyUtils.print(articulos);

                    //Si hay Articulos Registrados
                    if (!articulos.equals("No hay ningun registro de articulos")) {

                        //Insercion de Codigo de Articulo
                        codAlquilando = getStringConPatron("Introduce el codigo del articulo que desea dar de Baja: ", codForm,
                            "Introduce el codigo de Articulo apropiado, con formato tal que P-XXXX:");

                        //Busqueda de Articulo
                        Articulo articuloBaja = miVideoClub.buscarArticulo(codAlquilando);

                        //Intento de Dar de Baja Articulo
                        boolean bajaArticuloExito = miVideoClub.darBajaArticulo(articuloBaja);
                        if (bajaArticuloExito) {

                            //Si es exitoso
                            MyUtils.print("Se ha dado de baja a la articulo con exito");

                            MyUtils.print("\nEsta es su informacion:");
                            MyUtils.print(articuloBaja.toString());

                        } else {

                            //Si hay problemas
                            MyUtils.print("No se ha podido dar de baja a esa articulo, revise que todos los datos esten correctos e intentalo de nuevo");
                        }
                    }
                    //Si no hay Articulos Registradas
                    else {
                        MyUtils.print("No hay nada a eliminar");
                    }
                    MyUtils.pause();
                    //Fin de Baja de Articulo
                    break;

                //Case 9 - Guardar y Salir
                case '9':

                    MyUtils.print("Guardando Cambios antes de Salir");

                    try (FileOutputStream fos = new FileOutputStream(pathFile + fileConfig);
                        DataOutputStream dos = new DataOutputStream(fos)) {

                        dos.writeInt(Articulo.getConfigCod());
                        dos.writeInt(Cliente.getConfigCod());

                    } catch (IOException e) {
                        MyUtils.print("Error: " + e.getMessage());
                    }

                    try (FileOutputStream fos = new FileOutputStream(pathFile + fileData);
                        ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                        for (VideoDaw v : coleccionVideoClubs) {
                            oos.writeObject(v);
                        }

                    } catch (IOException e) {
                        MyUtils.print("Error: " + e.getMessage());
                    }

                    //Salirse de la Aplicacion
                    MyUtils.print("Saliendo del Programa");
                    break;

                default:

                    //Error por si alguna vez puede ocurrir
                    MyUtils.print("Error con el input (" + inputMainMenu + ") intentalo de nuevo");
                    MyUtils.pause();
                    break;
            }
        } while (option != '9'); //Loop sale cuando se Sale de la Aplicacion
    }

    private static String getStringConPatron(String text, Pattern form, String err) {
        String stringConFormato;

        do {
            try {

                MyUtils.printHere(text);
                stringConFormato = MyUtils.inputRequest(form);

            } catch (InputIncorrectoException e) {

                stringConFormato = "";
                MyUtils.print(err);
            }
        } while (stringConFormato.equals(""));

        return stringConFormato;
    }

    private static Articulo getNuevoArticulo(String titulo) {

        Articulo nuevoArticulo = null;
        String generoArticulo;
        MyUtils.print("Seleccione si este titulo es una Pelicula o un VideoJuego");
        MyUtils.print("1. Pelicula");
        MyUtils.print("2. VideoJuego");

        do {
            try {

                MyUtils.printHere("Introduce una opcion: ");
                generoArticulo = MyUtils.inputRequest(seleccionform);

            } catch (InputIncorrectoException e) {

                generoArticulo = "-1";
                MyUtils.print("Opcion no valido, escoge una de las dos opciones");
            }
        } while (Integer.parseInt(generoArticulo) > 2 || Integer.parseInt(generoArticulo) < 1);


        MyUtils.print("Escoge una de las siguientes opciones:");

        if (generoArticulo.equals("1")) {

            for (int i = 0; i < GeneroPeli.values().length; i++) {

                MyUtils.print((i + 1) + ". " + GeneroPeli.values()[i].name());
            }

        } else if (generoArticulo.equals("2")) {

            for (int i = 0; i < GeneroVideoJuego.values().length; i++) {

                MyUtils.print((i + 1) + ". " + GeneroVideoJuego.values()[i].name());
            }

        } else {

            MyUtils.print("Opcion no valida, tomando valor por defecto de 1.");
            generoArticulo = "1";
        }

        //Seleccion del Genero basado en tipo
        switch (generoArticulo) {

            //Case 1 - Pelicula
            case "1":
                do {
                    try {

                        MyUtils.printHere("Introduce el Indice del Genero: ");
                        generoArticulo = MyUtils.inputRequest(seleccionform);

                        if (Integer.parseInt(generoArticulo) > GeneroPeli.values().length || Integer.parseInt(generoArticulo) < 1) {
                            MyUtils.print("Valor fuera de rango, intentalo de nuevo");
                        }

                    } catch (InputIncorrectoException e) {

                        generoArticulo = "-1";
                        MyUtils.print("Opcion no valido, el valor no es una opcion");
                    }
                } while (Integer.parseInt(generoArticulo) > GeneroPeli.values().length || Integer.parseInt(generoArticulo) < 1);

                GeneroPeli generoPeli = GeneroPeli.values()[Integer.parseInt(generoArticulo) - 1];

                //Creacion Objeto
                nuevoArticulo = new Pelicula(titulo, generoPeli);

                break;


            //Case 2 - VideoJuego
            case "2":
                do {
                    try {

                        MyUtils.printHere("Introduce el Indice del Genero: ");
                        generoArticulo = MyUtils.inputRequest(seleccionform);

                        if (Integer.parseInt(generoArticulo) > GeneroVideoJuego.values().length || Integer.parseInt(generoArticulo) < 1) {
                            MyUtils.print("Valor fuera de rango, intentalo de nuevo");
                        }

                    } catch (InputIncorrectoException e) {

                        generoArticulo = "-1";
                        MyUtils.print("Opcion no valido, el valor no es una opcion");
                    }
                } while (Integer.parseInt(generoArticulo) > GeneroVideoJuego.values().length || Integer.parseInt(generoArticulo) < 1);

                GeneroVideoJuego generoVideoJuego  = GeneroVideoJuego.values()[Integer.parseInt(generoArticulo) - 1];

                //Creacion Objeto
                nuevoArticulo = new VideoJuego(titulo, generoVideoJuego);

                break;

        }

        return nuevoArticulo;
    }

    public static void registroDeVideoDaw(List<VideoDaw> coleccion) {

        //Declaracion de Variables
        String direccion;
        String CIF;
        final Pattern CIFform = Pattern.compile("[a-zA-Z][0-9]{7}[0-9A-Za-z]");
        boolean valido = true;

        //Texto Inicio Metodo
        MyUtils.print("\nRegistrando un Nuevo Videoclub");

        //CIF
        CIF = getStringConPatron("Introduce el CIF (Codigo de Identificacion Fiscal): ", CIFform,
                "CIF incorrecto, debe ser 1 letra, 7 numeros y 1 letra de nuevo (Ejemplo: 'A1234567Z')");


        //Direccion
        direccion = getStringConPatron("Introduce la Direccion del VideClub: ", notNull,
                "La direccion no puede estar vacia");

        //Creacion del CLub
        //Comprobacion de que no Exista
        for (int i = 0; i < coleccion.size(); i++) {
            if (CIF.equalsIgnoreCase(coleccion.get(i).getCIF())) {
                MyUtils.print("VideoClub ya existe, abortando creacion");
                valido = false;
            }
        }

        //Si no existe, crear
        if (valido) {
            VideoDaw nuevoVideoClub = new VideoDaw(CIF,direccion);
            coleccion.add(nuevoVideoClub);
            MyUtils.print("Video Club registrado");
        }
    }
}