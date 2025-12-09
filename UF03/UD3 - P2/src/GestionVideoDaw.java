import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GestionVideoDaw {
    public static void main(String[] args) {

        //Comienzo de codigo
        ColeccionVideoDaw LibreCoders = new ColeccionVideoDaw();

        //Holders de Objetos
        VideoDaw videoClubSelected;
        Pelicula peliculaSelected;
        Cliente clienteSelected;
        LocalDate dt;

        //Creacion VideoClub inicial
        registroDeVideoDaw(LibreCoders);
        videoClubSelected = LibreCoders.getRegistroVideoDaw()[0];
        Scanner sc;

        //Input variables
        String inputMainMenu;
        String seleccionVideClub, tituloPelicula, generoPelicula, codPelicula, dniCliente,nombreCliente,direccionCliente,fechaNAnio, fechaNMes, fechaNDia;
        Genero generoSeleccion = Genero.ROMANCE; //Inicializamos para no tener errores
        String devolucion;
        int option;
        boolean diaValido;

        //Booleans de Comprobacion que operaciones funcionen
        boolean creacionPeliculaExito,creacionClienteExito,alquilacionExito,bajaClienteExito, bajaPeliculaExito;

        //Patrones
        Pattern seleccionform = Pattern.compile("[1-9][0-9]*");
        Pattern generoForm = Pattern.compile("[0-4]");
        Pattern codForm = Pattern.compile("P-[0-9]{4,}");
        Pattern menuInputPattern = Pattern.compile("[1-9]");
        Pattern nombreForm = Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+");
        Pattern dniForm = Pattern.compile("[0-9]{8}[A-Za-z]");
        Pattern anioForm = Pattern.compile("[0-9]{4}");
        Pattern mesForm = Pattern.compile("[0-9]{2}");
        Pattern diaForm = Pattern.compile("[0-9]{2}");

        //Menu
        String[] menuGestionVideoDaw = new String[9];
        menuGestionVideoDaw[0] = "Crear y registrar VideoClub en la franquicia.";
        menuGestionVideoDaw[1] = "Seleccionar VideoClub en la franquicia.";
        menuGestionVideoDaw[2] = "Registrar pelicula en videoclub.";
        menuGestionVideoDaw[3] = "Crear y registrar cliente en videoclub.";
        menuGestionVideoDaw[4] = "Alquilar pelicula.";
        menuGestionVideoDaw[5] = "Devolver pelicula.";
        menuGestionVideoDaw[6] = "Dar de baja cliente.";
        menuGestionVideoDaw[7] = "Dar de baja pelicula.";
        menuGestionVideoDaw[8] = "Salir (terminar programa)";
        do {
            sc = new Scanner(System.in);

            //Display Menu Principal
            MyUtils.creacionMenu("GESTION VIDEO DAW",menuGestionVideoDaw,"Introduce una opcion:");
            inputMainMenu = MyUtils.pedirDato("Input de Menu no Valido. Introduce un numero del 1 al 9",menuInputPattern);
            option = Integer.parseInt(inputMainMenu);

            //Implementacion del Menu Principal con sus funcionalidades
            switch (option) {
                case 1:
                    //Creacion de nuevo VideoClub
                    registroDeVideoDaw(LibreCoders);
                    //Fin Creacion de nuevo VideClub
                    break;
                case 2:
                    //Seleccion de VideoClub

                    //Comprobacion de que haya mas de 1 VideoClub
                    if (LibreCoders.getNumRegistros() > 1) {
                        //Muestra de los VideoClubs existentes
                        MyUtils.imprimir("Existen mas de un VideoClub existence. Por favor, escoja el VideoCLub que desee usar, del 1 al " + LibreCoders.getNumRegistros());
                        MyUtils.imprimir(LibreCoders.mostrarTodosVideoClub());

                        //Seleccion del VideoClub
                        MyUtils.imprimir("Seleccione un videoclub:");
                        do {
                            seleccionVideClub = MyUtils.pedirDato("Valor introducido no valido. Intenta introducir el indice del VideoClub deseado",seleccionform);

                            if (Integer.parseInt(seleccionVideClub) > LibreCoders.getNumRegistros() || Integer.parseInt(seleccionVideClub) < 1) {
                                MyUtils.imprimir("Has escogido un numero fuera del rango, intenta introducir el indice del VideoClub");
                            }
                            else {
                                MyUtils.imprimir("VideoClub seleccionado");
                                videoClubSelected = LibreCoders.getRegistroVideoDaw()[Integer.parseInt(seleccionVideClub) - 1];
                            }

                        } while (Integer.parseInt(seleccionVideClub) > LibreCoders.getNumRegistros() || Integer.parseInt(seleccionVideClub) < 1);
                    }

                    //Si no hay dos o mas VideoClubs a escoger.
                    else {
                        MyUtils.imprimir("Solo existe un VideoClub, escogido por defecto");
                    }

                    //Se muestra informacion al final del VideoClub activo
                    MyUtils.imprimir(videoClubSelected.mostrarInfoVideoDaw());
                    MyUtils.esperar();
                    //Fin Seleccion VideoClub
                    break;
                case 3:
                    //Inicio Registro de Nueva Pelicula en el VideoClub
                    MyUtils.imprimir("Registrando una nueva pelicula en el VideClub");

                    //Insercion de Titulo
                    MyUtils.imprimir("Introduce el Titulo de la Pelicula:");
                    tituloPelicula = sc.nextLine();

                    //Insercion del Genero
                    MyUtils.imprimir("Seleccione el Genero de la Pelicula");
                    for (Genero genero : Genero.values()) {
                        MyUtils.imprimir(genero.ordinal() + " " + genero.name());
                    }

                    MyUtils.imprimir("Introduce el Indice:");
                    generoPelicula = MyUtils.pedirDato("Seleccione un indice valido (numero a la izquierda):",generoForm);
                    for (Genero genero : Genero.values()) {
                        if (genero.ordinal() == Integer.parseInt(generoPelicula)) {
                            generoSeleccion = genero;
                        }
                    }

                    //Creacion de la Pelicula una vez hecha
                    peliculaSelected = new Pelicula(tituloPelicula,generoSeleccion);

                    //Suma de la Pelicula a nuestra coleccion de Peliculas
                    creacionPeliculaExito = videoClubSelected.registrarPelicula(peliculaSelected);
                    if (creacionPeliculaExito) {
                        //Si funciona
                        MyUtils.imprimir("Registrando pelicula exitosamente");
                        peliculaSelected.mostrarInfoPelicula();
                    }
                    else {
                        //Si falla
                        MyUtils.imprimir("Algo fue mal al crear pelicula, intentalo de nuevo");
                    }
                    MyUtils.esperar();
                    //Fin de Registro de Pelicula
                    break;
                case 4:
                    //Inicio Registro de Nuevo Cliente en el VideoClub
                    MyUtils.imprimir("Registrando un nuevo cliente en el VideClub");

                    //Insercion del DNI
                    MyUtils.imprimir("Introduce el DNI del cliente:");
                    dniCliente = MyUtils.pedirDato("Introduce el DNI apropiado, 8 numeros y 1 letra",dniForm);

                    //Insercion del Nombre
                    MyUtils.imprimir("Introduce el nombre del cliente:");
                    nombreCliente = MyUtils.pedirDato("Introduce el nombre del cliente con una mayuscula y minusculas, nombre y un apellido",nombreForm);

                    //Insercion de la direccion
                    MyUtils.imprimir("Introduce la Direccion del cliente");
                    direccionCliente = sc.nextLine();

                    //Insercion de la fecha de Nacimiento
                    MyUtils.imprimir("Introduce la fecha de nacimiento del cliente:");

                    //Insercion Anio (evitando caracteres que den problemas)
                    MyUtils.imprimir("Anio (year) de nacimiento:");
                    do {
                        fechaNAnio = MyUtils.pedirDato("Anio introducida no valido, introduce uno de 4 digitos:",anioForm);
                        if (Integer.parseInt(fechaNAnio) < 1909 || Integer.parseInt(fechaNAnio) > 2200) {
                            MyUtils.imprimir("Rango de anio no Valido, ha de ser al menos 1909 pero tambien mayor de edad, intentelo de nuevo:");
                        }
                    } while (Integer.parseInt(fechaNAnio) < 1909 || Integer.parseInt(fechaNAnio) > 2200);

                    //Insercion Mes
                    MyUtils.imprimir("Mes (month) de nacimiento:");
                    do {
                        fechaNMes = MyUtils.pedirDato("Mes introducido no valido, introduce uno de 2 digitos:",mesForm);
                        if (Integer.parseInt(fechaNMes) < 1 || Integer.parseInt(fechaNMes) > 12) {
                            MyUtils.imprimir("Rango de mes no Valido, tiene que ser del 1 al 12, intentelo de nuevo:");
                        }
                    } while (Integer.parseInt(fechaNMes) < 1 || Integer.parseInt(fechaNMes) > 12);

                    //Insercion Dia
                    MyUtils.imprimir("Dia (day) de nacimiento:");
                    do {
                        fechaNDia = MyUtils.pedirDato("Dia introducido no valido, introduce uno de 2 digitos:",diaForm);
                        switch (Integer.parseInt(fechaNMes)) {
                            case 1,3,5,7,8,10,12:
                                diaValido = Integer.parseInt(fechaNDia) < 1 || Integer.parseInt(fechaNDia) > 31;
                                break;
                            case 4,6,9,11:
                                diaValido = Integer.parseInt(fechaNDia) < 1 || Integer.parseInt(fechaNDia) > 30;
                                break;
                            case 2:
                                diaValido = Integer.parseInt(fechaNDia) < 1 || Integer.parseInt(fechaNDia) > 28;
                                if (Year.isLeap(Long.parseLong(fechaNAnio))) {
                                    diaValido = Integer.parseInt(fechaNDia) < 1 || Integer.parseInt(fechaNDia) > 29;
                                }
                                break;
                            default:
                                MyUtils.imprimir("Algo fue mal al comprobar dia, notifica a un desarrollador");
                                diaValido = true;
                                break;
                        }
                        if (diaValido) {
                            MyUtils.imprimir("Rango de dia no Valido, tiene que ser dependiendo de los dias del mes, intentelo de nuevo:");
                        }
                    } while (diaValido);
                    //Condicion de Salida, Dia Valido sea False

                    //Construccion final del Dia
                    dt = LocalDate.of(Integer.parseInt(fechaNAnio), Integer.parseInt(fechaNMes), Integer.parseInt(fechaNDia));

                    //Creacion del Cliente
                    clienteSelected = new Cliente(dniCliente,nombreCliente,direccionCliente,dt);

                    //Suma del Cliente al Registro
                    creacionClienteExito = videoClubSelected.registrarCliente(clienteSelected);
                    if (creacionClienteExito) {
                        //Si ha funcionado
                        MyUtils.imprimir("Registrando cliente exitosamente");
                    }
                    else {
                        //Si no se pudo registrar
                        MyUtils.imprimir("Algo fue mal al crear cliente, puede que ya exista o sea menor de edad, revisa e intentalo mas tarde");
                    }
                    MyUtils.esperar();
                    //Fin de Registrar Cliente
                    break;
                case 5:
                    //Inicio Alquiler de Pelicula
                    MyUtils.imprimir("Alquilando Pelicula en el VideoClub");

                    //Insercion del DNI para buscar Cliente
                    MyUtils.imprimir("Introduce el DNI del cliente que desea alquilar:");
                    dniCliente = MyUtils.pedirDato("Introduce el DNI apropiado, 8 numeros y 1 letra",dniForm);

                    //Busqueda de Cliente
                    clienteSelected = videoClubSelected.buscarCliente(dniCliente);

                    //Insercion del Codigo de Pelicula
                    MyUtils.imprimir("Introduce el codigo de la pelicula que desea alquilar:");
                    codPelicula = MyUtils.pedirDato("Introduce el codigo de Pelicula apropiado, P-XXXX:",codForm);

                    //Busqueda de Pelicula
                    peliculaSelected = videoClubSelected.buscarPelicula(codPelicula);

                    //Si se consigue hacer la alquilacion de forma exitosa
                    alquilacionExito = videoClubSelected.alquilarPelicula(clienteSelected, peliculaSelected);
                    if (alquilacionExito) {
                        //Si la operacion salio bien
                        MyUtils.imprimir("Pelicula " + peliculaSelected.getTitulo() + " alquilada exitosamente por Cliente " + clienteSelected.getNombre());
                    }
                    else {
                        //Si hubo algun error
                        MyUtils.imprimir("No se ha podido alquilar la pelicula, intentalo de nuevo y revisa fallos");
                    }
                    MyUtils.esperar();
                    //Fin de Alquiler
                    break;
                case 6:
                    //Inicio devolucion de Pelicula
                    MyUtils.imprimir("Devolviendo Pelicula en el VideoClub");

                    //Insercion del DNI
                    MyUtils.imprimir("Introduce el DNI del cliente que desea devolver:");
                    dniCliente = MyUtils.pedirDato("Introduce el DNI apropiado, 8 numeros y 1 letra",dniForm);

                    //Busqueda del Cliente
                    clienteSelected = videoClubSelected.buscarCliente(dniCliente);

                    //Insercion del Codigo de Pelicula
                    MyUtils.imprimir("Introduce el codigo de la pelicula que desea devolver:");
                    codPelicula = MyUtils.pedirDato("Introduce el codigo de Pelicula apropiado, P-XXXX:",codForm);

                    //Busqueda de Pelicula
                    peliculaSelected = videoClubSelected.buscarPelicula(codPelicula);

                    //Intento de devolver pelicula, imprimiendo si es un error o si fue exitoso, y advirtiendo si hubo mas de 48 horas
                    devolucion = videoClubSelected.devolverPelicula(clienteSelected, peliculaSelected);
                    MyUtils.imprimir(devolucion);
                    MyUtils.esperar();
                    //Fin de devolucion de Pelicula
                    break;
                case 7:
                    //Inicio dar Baja a Cliente
                    MyUtils.imprimir("Dando de Baja a un cliente en el VideClub");

                    //Muestra de Nuestros Clietnes
                    MyUtils.imprimir("\nEstos son todos nuestros clientes:");
                    MyUtils.imprimir(videoClubSelected.mostrarClientesRegistrados());

                    //Si existen Clientes
                    if (videoClubSelected.getNumClientes() != 0) {
                        //Insercion DNI dar de Baja
                        MyUtils.imprimir("Introduce el DNI del cliente que desea dar de Baja:");
                        dniCliente = MyUtils.pedirDato("Introduce el DNI apropiado, 8 numeros y 1 letra", dniForm);

                        //Busqueda de Cliente
                        clienteSelected = videoClubSelected.buscarCliente(dniCliente);

                        //Intento de Dar de Baja Cliente
                        bajaClienteExito = videoClubSelected.darBajaCliente(clienteSelected);
                        if (bajaClienteExito) {
                            //Si se da de Baja exitosamente
                            MyUtils.imprimir("Se ha dado de baja al cliente con exito");
                        } else {
                            //Si no se pudo
                            MyUtils.imprimir("No se ha podido dar de baja a ese cliente, revise que todos los datos esten correctos e intentalo de nuevo");
                        }
                    }

                    //Si no existen
                    else {
                        MyUtils.imprimir("No hay nada a eliminar");
                    }
                    MyUtils.esperar();
                    //Fin dada Baja Cliente
                    break;
                case 8:
                    //Inicio dada de Baja a Pelicula
                    MyUtils.imprimir("Dando de Baja a una pelicula en el VideClub");

                    //Muestra de Nuestras Peliculas
                    MyUtils.imprimir("\nEstos son todas nuestras peliculas:");
                    MyUtils.imprimir(videoClubSelected.mostrarPeliculasRegistradas());

                    //Si hay Peliculas Registradas
                    if (videoClubSelected.getNumPeliculas() != 0) {
                        //Insercion de Codigo de Pelicula
                        MyUtils.imprimir("Introduce el codigo de la pelicula que desea dar de Baja:");
                        codPelicula = MyUtils.pedirDato("Introduce el codigo de Pelicula apropiado, P-XXXX:", codForm);

                        //Busqueda de Pelicula
                        peliculaSelected = videoClubSelected.buscarPelicula(codPelicula);

                        //Intento de Dar de Baja Pelicula
                        bajaPeliculaExito = videoClubSelected.darBajaPelicula(peliculaSelected);
                        if (bajaPeliculaExito) {
                            //Si es exitoso
                            MyUtils.imprimir("Se ha dado de baja a la pelicula con exito");
                        } else {
                            //Si hay problemas
                            MyUtils.imprimir("No se ha podido dar de baja a esa pelicula, revise que todos los datos esten correctos e intentalo de nuevo");
                        }
                    }
                    //Si no hay Peliculas Registradas
                    else {
                        MyUtils.imprimir("No hay nada a eliminar");
                    }
                    MyUtils.esperar();
                    //Fin de Baja de Pelicula
                    break;
                case 9:
                    //Salirse de la Aplicacion
                    MyUtils.imprimir("Saliendo del Programa");
                    break;
                default:
                    //Error por si alguna vez puede ocurrir
                    MyUtils.imprimir("Error con el input (" + inputMainMenu + ") intentalo de nuevo");
                    MyUtils.esperar();
                    break;
            }
        } while (option != 9); //Loop sale cuando se Sale de la Aplicacion
    }

    //Metodos
    public static void registroDeVideoDaw(ColeccionVideoDaw coleccion) {
        //Declaracion de Variables
        Scanner in  = new Scanner(System.in);
        String direccion;
        String CIF;
        Pattern CIFform = Pattern.compile("[a-zA-Z][0-9]{7}[0-9A-Za-z]");
        boolean valido = true;

        //Texto Inicio Metodo
        MyUtils.imprimir("Registrando un Nuevo Videoclub");

        //CIF
        MyUtils.imprimir("Introduce el CIF (Codigo de Identificacion Fiscal):");
        CIF = MyUtils.pedirDato("El dato introducido no es un CIF valido. Usa una letra, 7 numeros y un caracter alfanumerico (letra o numero):",CIFform);


        //Direccion
        MyUtils.imprimir("Por favor, introduce la direccion del club:");
        direccion = in.nextLine(); //Direccion no tiene estructura especifica

        //Creacion del CLub

        //Comprobacion de que no Exista
        for (int i = 0; i < coleccion.getNumRegistros(); i++) {
            if (CIF.equalsIgnoreCase(coleccion.getRegistroVideoDaw()[i].getCIF())) {
                MyUtils.imprimir("VideoClub ya existe, abortando creacion");
                valido = false;
            }
        }

        //Si no existe, crear
        if (valido) {
            VideoDaw nuevoVideoClub = new VideoDaw(CIF,direccion);
            coleccion.addVideoClub(nuevoVideoClub);
            MyUtils.imprimir("Video Club registrado");
        }
    }
}