import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

//Class Main
public class Main {
    public static void main(String[] args) {

        //Variables y Colecciones
        //Coleccion de Libros
        LinkedHashMap<String, Libro> biblioteca = new LinkedHashMap<>();

        //Objeto Scanner
        Scanner input = new Scanner(System.in);

        //Confirmacion si se necesita guardar el prgrama
        boolean saved = true;

        //Mensaje Bienvenida al programa
        MyUtils.print("Bienvenido al programa de la Biblioteca!");

        //Ficheros
        //Rutas
        final String path = ".\\src\\resources\\";
        final String fileName = "libreria.dat";

        //Variables
        File libraryFile = null;


        //Seleccion del archivo
        try {

            libraryFile = new File(path + fileName);

            //Verdadero y creado si no hay archivo, falso si hay archivo
            if (libraryFile.createNewFile()) {
                MyUtils.print("Creando el archivo libreria.dat: " + fileName);
            } else {
                MyUtils.print("El archivo libreria.dat existe, hay datos que cargar");
            }

            //Errores
        } catch (IOException e) {

            //Fallo si ruta no se encuentra u otro problema con el archivo
            MyUtils.print("Error al crear el archivo (libreria.dat): " + e.getMessage());
        }

        //Lectura de Fichero
        //Instanciamiento de lectura
        boolean eof = false;
        try (FileInputStream fileReader = new FileInputStream(libraryFile);
             ObjectInputStream bufferedReader = new ObjectInputStream(fileReader)) {

            //Loop hasta complecion
            while (eof == false) {

                //Lectura
                Libro temp = (Libro) bufferedReader.readObject();
                biblioteca.put(temp.getISBN(), temp);
                MyUtils.print("Leido Libro con ISBN: " + temp.getISBN());

            }

            //Errores
        } catch (EOFException e) {

            //Fin del Archivo
            MyUtils.print("Se han leido todos los datos");
            eof = true;

        } catch (IOException e) {

            //Fallo al intentar leer el archivo
            MyUtils.print("No se pudo usar el documento");
            MyUtils.print(e.getMessage());
            return; //Programa se acaba

        } catch (InputMismatchException e) {

            //Fallo al intentar insertar un dato
            MyUtils.print("Uno de los datos no se pudo leer");
            MyUtils.print(e.getMessage());

        } catch (Exception e) {

            //Captura de Fallos imprevistos
            MyUtils.print("Algo fue mal");
            MyUtils.print(e.getMessage());
        }

        //Datos Menu
        //Patron del Menu
        Pattern menu_options = Pattern.compile("[1-5]");
        //Variable opcion del Menu
        char choice;

        //Display del Menu
        String title = "Datos del Almacen";
        String[] options = new String[5];
        options[0] = "Crear Libro y registrarlo en la Biblioteca";
        options[1] = "Mostrar libros existentes";
        options[2] = "Eliminar libros por ISBN";
        options[3] = "Guardar Libros en el Fichero";
        options[4] = "Salir";
        String inputPetition = "Introduce el numero de una opcion: ";

        //Comienzo codigo por Loop Do-While
        do {

            //Display del Menu
            MyUtils.menuMaker(title,options,inputPetition);

            //Lectura del input
            try {

                //Seleccion del primer caracter como input
                choice = MyUtils.inputRequest(menu_options).charAt(0);

                //Errores
            } catch (InputIncorrectoException e) {

                //Si el Input no es valido, asignamos valor por defecto e
                choice = 'e';
            }

            //Seleccion del Input
            switch (choice) {

                //Caso 1 - Crear Libro y registrarlo en la Biblioteca
                case '1':

                    MyUtils.print("\nCreando un nuevo libro:");

                    saved = createLibro(biblioteca, saved);

                    MyUtils.pause();
                    break;

                //Caso 2 - Mostrar libros existentes
                case '2':

                    //Seleccion Metodo de Busqueda
                    mostrarLibros(biblioteca);

                    MyUtils.pause();
                    break;

                //Caso 3 - Eliminar libros por ISBN
                case '3':

                    MyUtils.print("\nEliminando libro por ISBN:");

                    //Datos no guardados
                    saved = borrarLibro(biblioteca, saved);

                    MyUtils.pause();
                    break;

                //Case 4 - Guardar Libros en el Fichero
                case '4':

                    MyUtils.print("\nComenzando actualizacion del archivo libreria.dat:");

                    //Edicion del Archivo
                    try (FileOutputStream fileWriter = new FileOutputStream(libraryFile, false);
                        ObjectOutputStream bufferedWriter = new ObjectOutputStream(fileWriter)) {

                        for  (Libro libro : biblioteca.values()) {
                            bufferedWriter.writeObject(libro);
                        }

                        //Errores
                    } catch (IOException e) {

                        //Fallo al editar el Archivo
                        MyUtils.print("Error al editar el archivo libreria.dat: " + e.getMessage());
                    }

                    //Datos guardados
                    saved = true;
                    MyUtils.pause();
                    break;

                //Case 5 - Salir
                case '5':

                    //Activacion del Scanner
                    input = new Scanner(System.in);
                    //Confirmacion si hay datos no Guardados
                    if (!saved) {
                        MyUtils.print("Hay cambios SIN guardar, desea SALIR SIN GUARDAR?");
                        MyUtils.printHere("Escribe (YES) si deseas salir: ");

                        //Literal "YES" para salir con datos sin guardar
                        if (input.nextLine().equals("YES")) {
                            MyUtils.print("\nSaliendo del programa...");
                        }
                        else {
                            MyUtils.print("Abortando...");
                            //Seleccion variable para no terminar Do-While
                            choice = 'e';
                        }
                    }
                    else {
                        MyUtils.print("\nSaliendo del programa...");
                    }
                    break;

                //Case e - Si saltaron errores
                case 'e':
                    MyUtils.print("\nEl input que has insertado no es un numero entre 1 y 5");
                    MyUtils.pause();
                    break;

                //Case default - valor no accessible para control
                default:
                    MyUtils.print("Ooops! No tendrias que estar aqui, input introducido (" + choice + "), notifica a un desarrollador");
                    MyUtils.pause();
                    break;
            }
        }
        //Fin del Do-While
        while (choice != '5');

        input.close();
    }

    //Metodo 2
    //@return String ISBN, loop hasta que se consiga
    private static String requestISBN() {

        final Pattern PATTERN_ISBN = Pattern.compile("[0-9]{13}");

        String ISBN = "";
        boolean success = false;

        //Loop hasta tener valor valido
        do {
            try {

                MyUtils.printHere("\nIntroduce el ISBN del libro: ");
                ISBN = MyUtils.inputRequest(PATTERN_ISBN).toUpperCase();
                success = true;

                //Errores
            } catch (InputIncorrectoException e) {

                //El Input no es correcto
                MyUtils.print(e.getMessage());
                MyUtils.print("El ISBN de un libro consiste en 13 numeros, Intentalo de Nuevo");
            }
        } while (!success);

        return ISBN;
    }

    //Metodo 3
    //@param biblioteca donde se tiene coleccion de ISBNs
    //@param boolean estado actual de si los archivos estan guardados
    //@return boolean si se deben guardar cambios o no, dependiendo de como se encontraba previamente, tras crear libro
    private static boolean createLibro(Map<String, Libro> biblioteca, boolean status) {

        String ISBN, titulo, autor;
        LocalDate fechaPublicacion;
        Scanner in = new Scanner(System.in);

        //ISBN
        ISBN = requestISBN();
        if (biblioteca.containsKey(ISBN)) {

            MyUtils.print("El ISBN del libro existe: " + ISBN +"\nAbortando");
            return status;
            //Metodo termina
        }

        //Titulo
        do {

            MyUtils.printHere("\nIntroduce el titulo del libro: ");
            titulo = in.nextLine();

            if (titulo.isEmpty()) {
                MyUtils.print("No introduzcas un titulo vacio");
            }
        } while (titulo.isEmpty());

        //Autor
        do {

            MyUtils.printHere("\nIntroduce el autor del libro: ");
            autor = in.nextLine();

            if (autor.isEmpty()) {
                MyUtils.print("No introduzcas un autor vacio");
            }
        } while (autor.isEmpty());

        //Fecha
        fechaPublicacion = MyUtils.requestDate();

        biblioteca.put(ISBN, new Libro(ISBN, titulo, autor, fechaPublicacion));

        return false;
    }

    //Metodo 4
    //@param Biblioteca con los libros a mostrar
    private static void mostrarLibros(Map<String, Libro> biblioteca) {

        if (biblioteca.isEmpty()) {
            MyUtils.print("\nLa biblioteca esta vacia, no hay libros que buscar");
            return;
        }

        //Menu
        String title = "Metodo de Busqueda";
        String[] modes = new String[4];
        modes[0] = "ISBN";
        modes[1] = "Titulo";
        modes[2] = "Autor";
        modes[3] = "Fecha";
        String request = "Seleccione una opcion (ISBN por defecto): ";

        MyUtils.menuMaker(title,modes,request);

        Scanner in = new Scanner(System.in);
        char opt = in.nextLine().charAt(0);

        //Logica de Ordenamiento
        //Creacion de una coleccion LinkedList para ordenar LinkedHashMap
        List<Libro> sorted = new LinkedList<>(biblioteca.values());
        String orderBy;

        switch (opt) {

            //Case 1 - ISBN -> Por defecto (default)

            //Case 2 - Titulo
            case '2':

                //Ordenacion
                Collections.sort(sorted, new ComparadorLibroTitulo());
                orderBy = "Titulo";
                break;

            //Case 3 - Autor
            case '3':

                //Ordenacion
                Collections.sort(sorted, new ComparadorLibroAutor());
                orderBy = "Autor";
                break;

            //Case 4 - Fecha
            case '4':

                //Ordenacion
                Collections.sort(sorted, new ComparadorLibroPublicacion());
                orderBy = "Fecha";
                break;

            //Default -> Case 1 - ISBN
            default:

                //Ordenacion
                Collections.sort(sorted, new ComparadorLibroISBN());
                orderBy = "ISBN";
                break;
        }

        //Imprimir
        MyUtils.print("\nMostrando todos los Libros (Ordenados por " + orderBy + "):\n");
        for  (Libro libro : sorted) {
            MyUtils.print(libro.toString());
        }
    }

    //Metodo 5
    //@param biblioteca donde se tiene coleccion de ISBNs
    //@param boolean estado actual de si los archivos estan guardados
    //@return boolean si se deben guardar cambios o no, dependiendo de como se encontraba previamente, tras borrar libro
    private static boolean borrarLibro(Map<String,Libro> biblioteca, boolean status) {

        if (biblioteca.isEmpty()) {
            MyUtils.print("No tienes libros a eliminar");
            return status;
        }

        String ISBN = requestISBN();

        if (!biblioteca.containsKey(ISBN)) {
            MyUtils.print("El Libro que buscas no esta en la coleccion");
            return status;
        }

        biblioteca.remove(ISBN);
        MyUtils.print("Libro eliminado exitosamente");

        return false;
    }
}