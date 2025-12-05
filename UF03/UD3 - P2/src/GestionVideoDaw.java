import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.chrono.ChronoLocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GestionVideoDaw {
    public static void main(String[] args) {

        //Comienzo de codigo
        ColeccionVideoDaw LibreCoders = new ColeccionVideoDaw();

        //Holders
        VideoDaw videoClubSelected;
        Pelicula peliculaSelected;
        Cliente clienteSelected;
        LocalDate dt;

        registroDeVideoDaw(LibreCoders);
        videoClubSelected = LibreCoders.getRegistroVideoDaw()[0];
        Scanner sc;

        //Input variables
        String inputMainMenu;
        String seleccionVideClub, tituloPelicula, generoPelicula, codPelicula, dniCliente,nombreCliente,direccionCliente,fechaNAnio, fechaNMes, fechaNDia;
        Genero generoSeleccion = Genero.ROMANCE; //Inicializamos para no tener errores
        int option;
        boolean diaValido;

        //Patrones
        Pattern seleccionform = Pattern.compile("[1-9][0-9]*");
        Pattern generoForm = Pattern.compile("[0-4]");
        Pattern codForm = Pattern.compile("[1-9][0-9]*");
        Pattern menuInputPattern = Pattern.compile("[1-9]");
        Pattern nombreForm = Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+");
        Pattern dniForm = Pattern.compile("[0-9]{8}[A-Za-z]");
        Pattern anioForm = Pattern.compile("[0-9]{4}");
        Pattern mesForm = Pattern.compile("[0-9]{2}");
        Pattern diaForm = Pattern.compile("[0-9]{2}");

        String[] menuGestionVideoDaw = new String[9];
        menuGestionVideoDaw[0] = "Crear y registrar VideoClub en la franquicia.";
        menuGestionVideoDaw[1] = "Seleccionar VideoClub en la franquicia.";
        menuGestionVideoDaw[2] = "Registrar película en videoclub.";
        menuGestionVideoDaw[3] = "Crear y registrar cliente en videoclub.";
        menuGestionVideoDaw[4] = "Alquilar película.";
        menuGestionVideoDaw[5] = "Devolver película.";
        menuGestionVideoDaw[6] = "Dar de baja cliente.";
        menuGestionVideoDaw[7] = "Dar de baja película.";
        menuGestionVideoDaw[8] = "Salir (terminar programa)";
        do {
            sc = new Scanner(System.in);
            MyUtils.menuMaker("GESTION VIDEO DAW",menuGestionVideoDaw,"Introduce una opcion:");
            inputMainMenu = MyUtils.inputRequest("Input de Menu no Valido. Introduce un numero del 1 al 8",menuInputPattern);
            option = Integer.parseInt(inputMainMenu);

            //Implementacion del Menu Principal con sus funcionalidades
            switch (option) {
                case 1:
                    registroDeVideoDaw(LibreCoders);
                    break;
                case 2:
                    //Seleccion de VideoClub
                    if (LibreCoders.getNumRegistros() > 1) {
                        MyUtils.imprimir("Existen mas de un VideoClub existence. Por favor, escoja el VideoCLub que desee usar, del 1 al " + LibreCoders.getNumRegistros());
                        MyUtils.imprimir(LibreCoders.mostrarTodosVideoClub());
                        do {
                            seleccionVideClub = MyUtils.inputRequest("Valor introducido no valido. Intenta introducir el indice del VideoClub deseado",seleccionform);
                            if (Integer.parseInt(seleccionVideClub) > LibreCoders.getNumRegistros() || Integer.parseInt(seleccionVideClub) < 1) {
                                MyUtils.imprimir("Has escogido un numero fuera del rango, intenta introducir el indice del VideoClub");
                            }
                            else {
                                MyUtils.imprimir("VideoClub seleccionado");
                                videoClubSelected = LibreCoders.getRegistroVideoDaw()[Integer.parseInt(seleccionVideClub) - 1];
                            }
                        } while (Integer.parseInt(seleccionVideClub) > LibreCoders.getNumRegistros() || Integer.parseInt(seleccionVideClub) < 1);
                    }
                    else {
                        MyUtils.imprimir("Solo existe un VideoClub, escogido por defecto");
                    }
                    MyUtils.imprimir(videoClubSelected.mostrarInfoVideoDaw());
                    MyUtils.esperar();
                    break;
                case 3:
                    MyUtils.imprimir("Registrando una nueva pelicula en el VideClub");
                    MyUtils.imprimir("Introduce el Titulo de la Pelicula:");
                    tituloPelicula = sc.nextLine();
                    MyUtils.imprimir("Seleccione el Genero de la Pelicula");
                    for (Genero genero : Genero.values()) {
                        MyUtils.imprimir(genero.ordinal() + " " + genero.name());
                    }
                    MyUtils.imprimir("Introduce el Indice:");
                    generoPelicula = MyUtils.inputRequest("Seleccione un indice valido (numero a la izquierda):",generoForm);
                    for (Genero genero : Genero.values()) {
                        if (genero.ordinal() == Integer.parseInt(generoPelicula)) {
                            generoSeleccion = genero;
                        }
                    }
                    peliculaSelected = new Pelicula(tituloPelicula,generoSeleccion);
                    if (videoClubSelected.registrarPelicula(peliculaSelected)) {
                        MyUtils.imprimir("Registrando pelicula exitosamente");
                        peliculaSelected.mostrarInfoPelicula();
                    }
                    else {
                        MyUtils.imprimir("Algo fue mal al crear pelicula, intentalo de nuevo");
                    }
                    MyUtils.esperar();
                    break;
                case 4:
                    MyUtils.imprimir("Registrando un nuevo cliente en el VideClub");
                    MyUtils.imprimir("Introduce el DNI del cliente:");
                    dniCliente = MyUtils.inputRequest("Introduce el DNI apropiado, 8 numeros y 1 letra",dniForm);
                    MyUtils.imprimir("Introduce el nombre del cliente:");
                    nombreCliente = MyUtils.inputRequest("Introduce el nombre del cliente con una mayuscula y minusculas, nombre y un apellido",nombreForm);
                    MyUtils.imprimir("Introduce la Direccion del cliente");
                    direccionCliente = sc.nextLine();
                    MyUtils.imprimir("Introduce la fecha de nacimiento del cliente:");
                    MyUtils.imprimir("Anio (year) de nacimiento:");
                    do {
                        fechaNAnio = MyUtils.inputRequest("Anio introducida no valido, introduce uno de 4 digitos:",anioForm);
                        if (Integer.parseInt(fechaNAnio) < 1909 || Integer.parseInt(fechaNAnio) > 2200) {
                            MyUtils.imprimir("Rango de anio no Valido, ha de ser al menos 1909 pero tambien mayor de edad, intentelo de nuevo:");
                        }
                    } while (Integer.parseInt(fechaNAnio) < 1909 || Integer.parseInt(fechaNAnio) > 2200);

                    MyUtils.imprimir("Mes (month) de nacimiento:");
                    do {
                        fechaNMes = MyUtils.inputRequest("Mes introducido no valido, introduce uno de 2 digitos:",mesForm);
                        if (Integer.parseInt(fechaNMes) < 1 || Integer.parseInt(fechaNMes) > 12) {
                            MyUtils.imprimir("Rango de mes no Valido, tiene que ser del 1 al 12, intentelo de nuevo:");
                        }
                    } while (Integer.parseInt(fechaNMes) < 1 || Integer.parseInt(fechaNMes) > 12);

                    MyUtils.imprimir("Dia (day) de nacimiento:");
                    do {
                        fechaNDia = MyUtils.inputRequest("Dia introducido no valido, introduce uno de 2 digitos:",diaForm);
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
                                diaValido = Integer.parseInt(fechaNDia) < 1 || Integer.parseInt(fechaNDia) > 12;
                                break;
                        }
                        if (diaValido) {
                            MyUtils.imprimir("Rango de dia no Valido, tiene que ser dependiendo de los dias del mes, intentelo de nuevo:");
                        }
                    } while (diaValido);

                    dt = LocalDate.of(Integer.parseInt(fechaNAnio), Integer.parseInt(fechaNMes), Integer.parseInt(fechaNDia));

                    clienteSelected = new Cliente(dniCliente,nombreCliente,direccionCliente,dt);
                    if (videoClubSelected.registrarCliente(clienteSelected)) {
                        MyUtils.imprimir("Registrando cliente exitosamente");
                    }
                    else {
                        MyUtils.imprimir("Algo fue mal al crear cliente, puede que ya exista o sea menor de edad, revisa e intentalo mas tarde");
                    }
                    MyUtils.esperar();
                    break;
                case 5:
                    MyUtils.imprimir("Alquilando Pelicula en el VideoClub");
                    MyUtils.imprimir("Introduce el DNI del cliente que desea alquilar:");
                    dniCliente = MyUtils.inputRequest("Introduce el DNI apropiado, 8 numeros y 1 letra",dniForm);
                    clienteSelected = videoClubSelected.buscarCliente(dniCliente);

                    MyUtils.imprimir("Introduce el codigo de la pelicula que desea alquilar:");
                    codPelicula = MyUtils.inputRequest("Introduce el codigo de Pelicula apropiado, un numero entero",codForm);
                    peliculaSelected = videoClubSelected.buscarPelicula(codPelicula);

                    if (videoClubSelected.alquilarPelicula(clienteSelected, peliculaSelected)) {
                        MyUtils.imprimir("Pelicula " + peliculaSelected.getTitulo() + " alquilado exitosamente por Cliente " + clienteSelected.getNombre());
                    }
                    else {
                        MyUtils.imprimir("No se ha podido alquilar la pelicula, intentalo de nuevo y revisa fallos");
                    }
                    MyUtils.esperar();
                    break;
                case 6:
                    MyUtils.imprimir("Devolviendo Pelicula en el VideoClub");
                    MyUtils.imprimir("Introduce el DNI del cliente que desea devolver:");
                    dniCliente = MyUtils.inputRequest("Introduce el DNI apropiado, 8 numeros y 1 letra",dniForm);
                    clienteSelected = videoClubSelected.buscarCliente(dniCliente);

                    MyUtils.imprimir("Introduce el codigo de la pelicula que desea devolver:");
                    codPelicula = MyUtils.inputRequest("Introduce el codigo de Pelicula apropiado, un numero entero",codForm);
                    peliculaSelected = videoClubSelected.buscarPelicula(codPelicula);

                    MyUtils.imprimir(videoClubSelected.devolverPelicula(clienteSelected, peliculaSelected));
                    MyUtils.esperar();
                    break;
                case 7:
                    MyUtils.imprimir("Dando de Baja a un cliente en el VideClub");
                    MyUtils.imprimir("Introduce el DNI del cliente que desea dar de Baja:");
                    dniCliente = MyUtils.inputRequest("Introduce el DNI apropiado, 8 numeros y 1 letra",dniForm);
                    clienteSelected = videoClubSelected.buscarCliente(dniCliente);
                    if (videoClubSelected.darBajaCliente(clienteSelected)) {
                        MyUtils.imprimir("Se ha dado de baja al cliente con exito");
                    }
                    else {
                        MyUtils.imprimir("No se ha podido dar de baja a ese cliente, revise que todos los datos esten correctos e intentalo de nuevo");
                    }
                    MyUtils.esperar();
                    break;
                case 8:
                    MyUtils.imprimir("Dando de Baja a una pelicula en el VideClub");
                    MyUtils.imprimir("Introduce el codigo de la pelicula que desea dar de Baja:");
                    codPelicula = MyUtils.inputRequest("Introduce el codigo de Pelicula apropiado, un numero entero",codForm);
                    peliculaSelected = videoClubSelected.buscarPelicula(codPelicula);
                    if (videoClubSelected.darBajaPelicula(peliculaSelected)) {
                        MyUtils.imprimir("Se ha dado de baja a la pelicula con exito");
                    }
                    else {
                        MyUtils.imprimir("No se ha podido dar de baja a esa pelicula, revise que todos los datos esten correctos e intentalo de nuevo");
                    }
                    MyUtils.esperar();
                    break;
                case 9:
                    MyUtils.imprimir("Saliendo del Programa");
                    break;
                default:
                    MyUtils.imprimir("Error con el input (" + inputMainMenu + ") intentalo de nuevo");
                    MyUtils.esperar();
                    break;
            }
        } while (option != 9);
    }

    //Metodos
    public static void registroDeVideoDaw(ColeccionVideoDaw LibreCoders) {
        Scanner in  = new Scanner(System.in);
        String direccion;
        String CIF;
        Pattern CIFform = Pattern.compile("[a-zA-Z][0-9]{8}");

        MyUtils.imprimir("Registrando un Nuevo Videoclub");

        //CIF
        MyUtils.imprimir("Introduce el CIF (Codigo de Identificacion Fiscal):");
        CIF = MyUtils.inputRequest("El dato introducido no es un CIF valido. Usa una letra y 8 numeros:",CIFform);


        //Direccion
        MyUtils.imprimir("Por favor, introduce la direccion del club:");
        direccion = in.nextLine(); //Direccion no tiene estructura especifica

        //Creacion del CLub
        VideoDaw nuevoVideoClub = new VideoDaw(CIF,direccion);
        LibreCoders.addVideoClub(nuevoVideoClub);
        MyUtils.imprimir("Video Club registrado");
    }
}