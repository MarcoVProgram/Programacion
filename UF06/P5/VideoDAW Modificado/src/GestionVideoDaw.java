import java.awt.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GestionVideoDaw {

    //Patrones
    private static final Pattern codForm = Pattern.compile("P-[0-9]{4,}");
    private static final Pattern menuInputPattern = Pattern.compile("[1-9]");
    private static final Pattern nombreForm = Pattern.compile("[A-Z][a-z]+ [A-Z a-z]+");
    private static final Pattern dniForm = Pattern.compile("[0-9]{8}[A-Za-z]");
    private static final Pattern notNull = Pattern.compile("/S");

    public static void main(String[] args) {

        //Comienzo de codigo
        List<VideoDaw> coleccionVideoClubs = new ArrayList<>();

        //Holders de Objetos
        VideoDaw videoClubSelected;

        //Creacion VideoClub inicial
        registroDeVideoDaw(coleccionVideoClubs);
        videoClubSelected = coleccionVideoClubs.get(0);

        //Input variables
        String inputMainMenu;
        String seleccionVideoClub, tituloPelicula, generoArticulo, codPelicula, dniCliente,nombreCliente,direccionCliente,fechaNAnio, fechaNMes, fechaNDia;
        String devolucion;
        char option;

        //Booleans de Comprobacion que operaciones funcionen
        boolean creacionPeliculaExito,creacionClienteExito,alquilacionExito,bajaClienteExito, bajaPeliculaExito;

        //Patrones
        Pattern seleccionform = Pattern.compile("[1-9][0-9]*");

        //Menu
        String[] menuGestionVideoDaw = new String[9];
        menuGestionVideoDaw[0] = "Crear y registrar VideoClub en la franquicia";
        menuGestionVideoDaw[1] = "Seleccionar VideoClub en la franquicia";
        menuGestionVideoDaw[2] = "Registrar articulo en videoclub";
        menuGestionVideoDaw[3] = "Crear y registrar cliente en videoclub";
        menuGestionVideoDaw[4] = "Alquilar";
        menuGestionVideoDaw[5] = "Devolver";
        menuGestionVideoDaw[6] = "Dar de baja cliente";
        menuGestionVideoDaw[7] = "Dar de baja articulo";
        menuGestionVideoDaw[8] = "Salir (terminar programa)";

        do {

            //Display Menu Principal
            MyUtils.menuMaker("GESTION VIDEO DAW", menuGestionVideoDaw, "Introduce una opcion:");
            inputMainMenu = MyUtils.inputRequestLoop(menuInputPattern, "Input de Menu no Valido. Introduce un numero del 1 al 9");
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

                    //Comprobacion de que haya mas de 1 VideoClub
                    if (coleccionVideoClubs.size() > 1) {

                        //Muestra de los VideoClubs existentes
                        MyUtils.print("Existen mas de un VideoClub existence. Por favor, escoja el VideoCLub que desee usar, del 1 al " + coleccionVideoClubs.size());
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
                                    videoClubSelected = coleccionVideoClubs.get(Integer.parseInt(seleccionVideoClub) - 1);
                                }

                            } catch (InputIncorrectoException e) {

                                seleccionVideoClub = "-1";
                                MyUtils.print("El valor introducido no es un numero, intentalo de nuevo");
                            }

                        } while (Integer.parseInt(seleccionVideoClub) > coleccionVideoClubs.size() || Integer.parseInt(seleccionVideoClub) < 1);
                    }

                    //Si no hay dos o mas VideoClubs a escoger.
                    else {
                        MyUtils.print("Solo existe un VideoClub, escogido por defecto");
                    }

                    //Se muestra informacion al final del VideoClub activo
                    MyUtils.print(videoClubSelected.toString());
                    MyUtils.pause();
                    //Fin Seleccion VideoClub
                    break;

                //Case 3 - Registrar pelicula en VideoClub
                case '3':

                    //Inicio Registro de Nueva Pelicula en el VideoClub
                    MyUtils.print("Registrando un nuevo articulo en el VideClub");

                    //Insercion de Titulo
                    do {
                        try {

                            MyUtils.printHere("Introduce el Titulo de la Pelicula: ");
                            tituloPelicula = MyUtils.inputRequest(notNull);

                        } catch (InputIncorrectoException e) {

                            tituloPelicula = "";
                            MyUtils.print("Titulo no valido, no se deben dejar el campo en blanco ");
                        }
                    } while (tituloPelicula.equals(""));


                    //Insercion del Genero
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

                            MyUtils.print((i + 1) + GeneroPeli.values()[i].name());
                        }

                    } else if (generoArticulo.equals("2")) {

                        for (int i = 0; i < GeneroVideoJuego.values().length; i++) {

                            MyUtils.print((i + 1) + "." + GeneroVideoJuego.values()[i].name());
                        }
                    } else {

                        MyUtils.print("Opcion no valida, tomando valor por defecto de 1.");
                        generoArticulo = "1";
                    }


                    switch (generoArticulo) {
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

                            break;


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

                            break;

                    }

                    //Suma de la Pelicula a nuestra coleccion de Peliculas
                    creacionPeliculaExito = videoClubSelected.registrarPelicula(art);
                    if (creacionPeliculaExito) {
                        //Si funciona
                        MyUtils.print("Registrando pelicula exitosamente");
                        art.mostrarInfoPelicula();
                    }
                    else {
                        //Si falla
                        MyUtils.print("Algo fue mal al crear pelicula, intentalo de nuevo");
                    }
                    MyUtils.pause();
                    //Fin de Registro de Pelicula
                    break;
                case '4':
                    //Inicio Registro de Nuevo Cliente en el VideoClub
                    MyUtils.print("Registrando un nuevo cliente en el VideClub");

                    //Insercion del DNI
                    MyUtils.print("Introduce el DNI del cliente:");
                    dniCliente = MyUtils.inputRequestLoop("Introduce el DNI apropiado, 8 numeros y 1 letra",dniForm);

                    //Insercion del Nombre
                    MyUtils.print("Introduce el nombre del cliente:");
                    nombreCliente = MyUtils.inputRequestLoop("Introduce el nombre del cliente con una mayuscula y minusculas, nombre y un apellido",nombreForm);

                    //Insercion de la direccion
                    MyUtils.print("Introduce la Direccion del cliente");
                    direccionCliente = sc.nextLine();

                    //Insercion de la fecha de Nacimiento
                    MyUtils.print("Introduce la fecha de nacimiento del cliente:");

                    //Insercion Anio (evitando caracteres que den problemas)
                    MyUtils.print("Anio (year) de nacimiento:");
                    do {
                        fechaNAnio = MyUtils.inputRequestLoop("Anio introducida no valido, introduce uno de 4 digitos:",anioForm);
                        if (Integer.parseInt(fechaNAnio) < 1909 || Integer.parseInt(fechaNAnio) > 2200) {
                            MyUtils.print("Rango de anio no Valido, ha de ser al menos 1909 pero tambien mayor de edad, intentelo de nuevo:");
                        }
                    } while (Integer.parseInt(fechaNAnio) < 1909 || Integer.parseInt(fechaNAnio) > 2200);

                    //Insercion Mes
                    MyUtils.print("Mes (month) de nacimiento:");
                    //Condicion de Salida, Dia Valido sea False

                    //Construccion final del Dia
                    dt = LocalDate.of(Integer.parseInt(fechaNAnio), Integer.parseInt(fechaNMes), Integer.parseInt(fechaNDia));

                    //Creacion del Cliente
                    clienteSelected = new Cliente(dniCliente,nombreCliente,direccionCliente,dt);

                    //Suma del Cliente al Registro
                    creacionClienteExito = videoClubSelected.registrarCliente(clienteSelected);
                    if (creacionClienteExito) {
                        //Si ha funcionado
                        MyUtils.print("Registrando cliente exitosamente");
                    }
                    else {
                        //Si no se pudo registrar
                        MyUtils.print("Algo fue mal al crear cliente, puede que ya exista o sea menor de edad, revisa e intentalo mas tarde");
                    }
                    MyUtils.pause();
                    //Fin de Registrar Cliente
                    break;
                case '5':
                    //Inicio Alquiler de Pelicula
                    MyUtils.print("Alquilando Pelicula en el VideoClub");

                    //Insercion del DNI para buscar Cliente
                    MyUtils.print("Introduce el DNI del cliente que desea alquilar:");
                    dniCliente = MyUtils.inputRequestLoop("Introduce el DNI apropiado, 8 numeros y 1 letra",dniForm);

                    //Busqueda de Cliente
                    clienteSelected = videoClubSelected.buscarCliente(dniCliente);

                    //Insercion del Codigo de Pelicula
                    MyUtils.print("Introduce el codigo de la pelicula que desea alquilar:");
                    codPelicula = MyUtils.inputRequestLoop("Introduce el codigo de Pelicula apropiado, P-XXXX:",codForm);

                    //Busqueda de Pelicula
                    art = videoClubSelected.buscarArticulo(codPelicula);

                    //Si se consigue hacer la alquilacion de forma exitosa
                    alquilacionExito = videoClubSelected.alquilarArticulo(clienteSelected, art);
                    if (alquilacionExito) {
                        //Si la operacion salio bien
                        MyUtils.print("Pelicula " + art.getTitulo() + " alquilada exitosamente por Cliente " + clienteSelected.getNombre());
                    }
                    else {
                        //Si hubo algun error
                        MyUtils.print("No se ha podido alquilar la pelicula, intentalo de nuevo y revisa fallos");
                    }
                    MyUtils.pause();
                    //Fin de Alquiler
                    break;
                case '6':
                    //Inicio devolucion de Pelicula
                    MyUtils.print("Devolviendo Pelicula en el VideoClub");

                    //Insercion del DNI
                    MyUtils.print("Introduce el DNI del cliente que desea devolver:");
                    dniCliente = MyUtils.inputRequestLoop("Introduce el DNI apropiado, 8 numeros y 1 letra",dniForm);

                    //Busqueda del Cliente
                    clienteSelected = videoClubSelected.buscarCliente(dniCliente);

                    //Insercion del Codigo de Pelicula
                    MyUtils.print("Introduce el codigo de la pelicula que desea devolver:");
                    codPelicula = MyUtils.inputRequestLoop("Introduce el codigo de Pelicula apropiado, P-XXXX:",codForm);

                    //Busqueda de Pelicula
                    art = videoClubSelected.buscarArticulo(codPelicula);

                    //Intento de devolver pelicula, imprimiendo si es un error o si fue exitoso, y advirtiendo si hubo mas de 48 horas
                    devolucion = videoClubSelected.devolverArticulo(clienteSelected, art);
                    MyUtils.print(devolucion);
                    MyUtils.pause();
                    //Fin de devolucion de Pelicula
                    break;
                case '7':
                    //Inicio dar Baja a Cliente
                    MyUtils.print("Dando de Baja a un cliente en el VideClub");

                    //Muestra de Nuestros Clietnes
                    MyUtils.print("\nEstos son todos nuestros clientes:");
                    MyUtils.print(videoClubSelected.mostrarClientesRegistrados());

                    //Si existen Clientes
                    if (videoClubSelected.getNumClientes() != 0) {
                        //Insercion DNI dar de Baja
                        MyUtils.print("Introduce el DNI del cliente que desea dar de Baja:");
                        dniCliente = MyUtils.inputRequestLoop("Introduce el DNI apropiado, 8 numeros y 1 letra", dniForm);

                        //Busqueda de Cliente
                        clienteSelected = videoClubSelected.buscarCliente(dniCliente);

                        //Intento de Dar de Baja Cliente
                        bajaClienteExito = videoClubSelected.darBajaCliente(clienteSelected);
                        if (bajaClienteExito) {
                            //Si se da de Baja exitosamente
                            MyUtils.print("Se ha dado de baja al cliente con exito");
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
                case '8':
                    //Inicio dada de Baja a Pelicula
                    MyUtils.print("Dando de Baja a una pelicula en el VideClub");

                    //Muestra de Nuestras Peliculas
                    MyUtils.print("\nEstos son todas nuestras peliculas:");
                    MyUtils.print(videoClubSelected.mostrarPeliculasRegistradas());

                    //Si hay Peliculas Registradas
                    if (videoClubSelected.getNumPeliculas() != 0) {
                        //Insercion de Codigo de Pelicula
                        MyUtils.print("Introduce el codigo de la pelicula que desea dar de Baja:");
                        codPelicula = MyUtils.inputRequestLoop("Introduce el codigo de Pelicula apropiado, P-XXXX:", codForm);

                        //Busqueda de Pelicula
                        art = videoClubSelected.buscarArticulo(codPelicula);

                        //Intento de Dar de Baja Pelicula
                        bajaPeliculaExito = videoClubSelected.darBajaPelicula(art);
                        if (bajaPeliculaExito) {
                            //Si es exitoso
                            MyUtils.print("Se ha dado de baja a la pelicula con exito");
                        } else {
                            //Si hay problemas
                            MyUtils.print("No se ha podido dar de baja a esa pelicula, revise que todos los datos esten correctos e intentalo de nuevo");
                        }
                    }
                    //Si no hay Peliculas Registradas
                    else {
                        MyUtils.print("No hay nada a eliminar");
                    }
                    MyUtils.pause();
                    //Fin de Baja de Pelicula
                    break;
                case '9':
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

    //Metodos
    public static void registroDeVideoDaw(List coleccion) {

        //Declaracion de Variables
        Scanner in  = new Scanner(System.in);
        String direccion;
        String CIF;
        final Pattern CIFform = Pattern.compile("[a-zA-Z][0-9]{7}[0-9A-Za-z]");
        final Pattern noNull = Pattern.compile("/S");
        boolean valido = true;

        //Texto Inicio Metodo
        MyUtils.print("Registrando un Nuevo Videoclub");

        //CIF
        boolean cifValido = false;
        do {
            try {
                MyUtils.printHere("Introduce el CIF (Codigo de Identificacion Fiscal): ");
                CIF = MyUtils.inputRequest(CIFform).toUpperCase();
            } catch (InputIncorrectoException e) {
                MyUtils.print("CIF incorrecto, debe ser 1 letra, 7 numeros y 1 letra de nuevo (Ejemplo: 'A1234567Z')");
            }
        } while (!cifValido);


        //Direccion
        MyUtils.print("Por favor, introduce la direccion del club:");
        direccion = in.nextLine(); //Direccion no tiene estructura especifica

        //Creacion del CLub

        //Comprobacion de que no Exista
        for (int i = 0; i < coleccion.getNumRegistros(); i++) {
            if (CIF.equalsIgnoreCase(coleccion.getRegistroVideoDaw()[i].getCIF())) {
                MyUtils.print("VideoClub ya existe, abortando creacion");
                valido = false;
            }
        }

        //Si no existe, crear
        if (valido) {
            VideoDaw nuevoVideoClub = new VideoDaw(CIF,direccion);
            coleccion.addVideoClub(nuevoVideoClub);
            MyUtils.print("Video Club registrado");
        }
    }
}