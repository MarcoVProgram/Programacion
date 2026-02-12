import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//Class Main
public class Main extends MyUtils {
    public static void main(String[] args) {

        //Variables y Colecciones
        //Coleccion de Libros
        LinkedHashMap<String, Libro> biblioteca = new LinkedHashMap<>();

        //Placeholders
        Libro libro;
        String ISBN;

        //Objeto Scanner
        Scanner input = new Scanner(System.in);

        //Confirmacion si se necesita guardar el prgrama
        boolean saved = true;

        //Mensaje Bienvenida al programa
        print("Bienvenido al programa de la Biblioteca!");

        //Ficheros
        //Rutas
        final String path = ".\\resources\\";
        final String fileName = "libreria.dat";

        //Variables
        File libreriaFile = null;


        //Seleccion del archivo
        try {

            libreriaFile = new File(path + fileName);

            //Verdadero y creado si no hay archivo, falso si hay archivo
            if (libreriaFile.createNewFile()) {
                print("Creando el archivo almacen: " + fileName);
            } else {
                print("El archivo ya existe, hay datos que cargar");
            }

            //Errores
        } catch (IOException e) {

            //Fallo si ruta no se encuentra u otro problema con el archivo
            print("Error al crear el archivo (libreria.dat): " + e.getMessage());
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
            menuMaker(title,options,inputPetition);

            //Lectura del input
            try {

                //Seleccion del primer caracter como input
                choice = inputRequest(menu_options).charAt(0);

                //Errores
            } catch (InputIncorrectoException e) {

                //Si el Input no es valido, asignamos valor por defecto e
                choice = 'e';
            }

            //Seleccion del Input
            switch (choice) {

                //Caso 1 - Crear Producto
                case '1':

                    print("\nCreando un nuevo libro:");

                    saved = createLibro(biblioteca, saved);

                    pause();
                    break;

                //Caso 2 - Mostrar Productos Existentes
                case '2':

                    //Seleccion Metodo de Busqueda
                    mostrarLibros(biblioteca);

                    pause();
                    break;

                //Caso 3 - Eliminar Producto por Codigo
                case '3':

                    print("\nEliminando producto por codigo:");

                    cod = requestCodigo();
                    libro = biblioteca.remove(cod);

                    if (libro == null) {
                        print("\nProducto no existe");
                    }
                    else {
                        print("\nProducto eliminado es:");
                        print(libro.toString());
                        print("Se ha eliminado exitosamente");
                    }

                    //Datos no guardados
                    saved = false;
                    pause();
                    break;

                //Case 4 - Guardar Libros en el Fichero
                case '4':

                    print("\nComenzando actualizacion del archivo almacen.dat:");

                    //Edicion del Archivo
                    try (FileWriter fileWriter = new FileWriter(almacenFile, false);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                        for (String key : biblioteca.keySet()) {
                            bufferedWriter.write(key + "," + biblioteca.get(key).getNombre()
                                    + "," + biblioteca.get(key).getCantidad() + "," + biblioteca.get(key).getPrecio());
                            bufferedWriter.newLine();
                            print("Producto ha sido almacenado: " + key);
                        }

                        //Errores
                    } catch (IOException e) {

                        //Fallo al editar el Archivo
                        print("Error al editar el archivo almacen: " + e.getMessage());
                    }

                    //Datos guardados
                    saved = true;
                    pause();
                    break;

                //Case 5 - Salir
                case '5':

                    //Activacion del Scanner
                    input = new Scanner(System.in);
                    //Confirmacion si hay datos no Guardados
                    if (!saved) {
                        print("Hay cambios sin guardar, desea salir?");
                        printHere("Escribe (YES) si deseas salir: ");

                        //Literal "YES" para salir con datos sin guardar
                        if (input.nextLine().equals("YES")) {
                            print("\nSaliendo del programa...");
                        }
                        else {
                            print("Abortando...");
                            //Seleccion variable para no terminar Do-While
                            choice = 'e';
                        }
                    }
                    else {
                        print("\nSaliendo del programa...");
                    }
                    break;

                //Case e - Si saltaron errores
                case 'e':
                    print("\nEl input que has insertado no es un numero entre 1 y 7");
                    pause();
                    break;

                //Case default - valor no accessible para control
                default:
                    print("Ooops! No tendrias que estar aqui, input introducido (" + choice + "), notifica a un desarrollador");
                    pause();
                    break;
            }
        }
        //Fin del Do-While
        while (choice != '7');

        input.close();
    }

    //Metodo 1
    //@return String ISBN, loop hasta que se consiga
    public static String requestISBN() {

        final Pattern PATTERN_ISBN = Pattern.compile("[0-9]{13}");

        String ISBN = "";
        boolean success = false;

        //Loop hasta tener valor valido
        do {
            try {

                printHere("\nIntroduce el ISBN del libro: ");
                ISBN = inputRequest(PATTERN_ISBN);
                success = true;

                //Errores
            } catch (InputIncorrectoException e) {

                //El Input no es correcto
                print(e.getMessage());
                print("El ISBN de un libro consiste en 2 letras y 22 numeros, Intentalo de Nuevo");
            }
        } while (!success);

        return ISBN;
    }

    //Metodo
    //@param biblioteca donde se tiene coleccion de ISBNs
    //@return boolean si se deben guardar cambios o no, dependiendo de como se encontraba previamente
    public static boolean createLibro(LinkedHashMap<String, Libro> biblioteca, boolean status) {

        String ISBN, titulo, autor;
        LocalDate fechaPublicacion;
        Scanner in = new Scanner(System.in);

        //ISBN
        ISBN = requestISBN();
        if (biblioteca.containsKey(ISBN)) {

            print("El ISBN del libro existe: " + ISBN +"\nAbortando");
            return status;
            //Metodo termina
        }

        //Titulo
        do {

            printHere("\nIntroduce el titulo del libro: ");
            titulo = in.nextLine();

            if (titulo.isEmpty()) {
                print("No introduzcas un titulo vacio");
            }
        } while (titulo.isEmpty());

        //Autor
        do {

            printHere("\nIntroduce el autor del libro: ");
            autor = in.nextLine();

            if (autor.isEmpty()) {
                print("No introduzcas un autor vacio");
            }
        } while (autor.isEmpty());

        //Fecha
        fechaPublicacion = requestDate();

        biblioteca.put(ISBN, new Libro(ISBN, titulo, autor, fechaPublicacion));

        return true;
    }

    //Metodo 3
    //@param Biblioteca con los libros a mostrar
    private static void mostrarLibros(LinkedHashMap<String, Libro> biblioteca) {

        if (biblioteca.isEmpty()) {
            print("\nLa biblioteca esta vacia, no hay libros que buscar");
            return;
        }

        //Menu
        String title = "Metodo de Busqueda";
        String[] modes = new String[4];
        modes[0] = "ISBN";
        modes[1] = "Titulo";
        modes[2] = "Autor";
        modes[3] = "Fecha";
        String request = "Seleccione una opcion (ISBN por defecto):";

        menuMaker(title,modes,request);

        Scanner in = new Scanner(System.in);
        char opt = in.nextLine().charAt(0);

        LinkedHashMap<String, Libro> sorted;

        //Ordenacion
        switch (opt) {
            case '2':
                sorted = biblioteca.entrySet().stream().sorted( e -> e.getValue().getTitulo()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                break;
        }
    }
}