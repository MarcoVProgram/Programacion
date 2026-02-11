//Clases Importadas
import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

//Extension de MyUtils por variables de comodidad
public class Main extends MyUtils {

    //Constantes
    private static final Pattern PATTERNCOD = Pattern.compile("P-[0-9]{4,}");

    public static void main(String[] args) {

        //Variables y colecciones
        //Coleccion de productos
        LinkedHashMap<String, Producto> storage = new LinkedHashMap<>();

        //Producto placeholder guardado
        Producto product = null;
        String cod = "";
        int amt = 0;

        //Confirmacion si se necesitaria de actualizar almacen.dat
        boolean allSaved = true;

        //Objeto Scanner para leer input
        Scanner input;

        //Mensaje Inicio Programa
        print("Bienvenido al programa del Almacen!");


        //Ficheros
        //Rutas
        final String path = ".\\resources\\";
        final String fileName = "almacen.dat";

        //Placeholders de datos del archivo
        String line = "";
        String[] lineData;
        File almacenFile = null;

        //Seleccion del archivo
        try {

            almacenFile = new File(path + fileName);

            //Verdadero y creado si no hay archivo, falso si hay archivo
            if (almacenFile.createNewFile()) {
                print("Creando el archivo almacen: " + fileName);
            } else {
                print("El archivo ya existe, hay datos que cargar");
            }

            //Errores
        } catch (IOException e) {

            //Fallo si ruta no se encuentra u otro problema con el archivo
            print("Error al crear el archivo almacen: " + e.getMessage());
        }

        //Lectura del Archivo
        //Instanciamiento de lectura
        try (FileReader fileReader = new FileReader(almacenFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            //Loop que acaba cuando Linea es null
            while (line != null) {

                //Lectura Linea, es null si se acaba
                line = bufferedReader.readLine();

                //Leer datos cuando exista Linea y no este vacia
                if (line != null && !line.isEmpty()) {

                    //Division de linea por comas
                    lineData = line.split(",");
                    //Toma de datos de linea
                    String codigo = lineData[0];
                    String nombre = lineData[1];
                    int cantidad = Integer.parseInt(lineData[2].trim());
                    double precio = Double.parseDouble(lineData[3].trim());

                    //Insercion de producto en coleccion
                    storage.put(codigo, new Producto(codigo, nombre, cantidad, precio));
                    print("Producto ha sido almacenado: " + codigo);
                }
            }

            //Errores
        } catch (IOException e) {

            //Fallo al intentar leer el archivo
            print("No se pudo usar el documento");
            print(e.getMessage());
            return; //Programa se acaba

        } catch (InputMismatchException e) {

            //Fallo al intentar insertar un dato
            print("Uno de los datos no se pudo leer");
            print(e.getMessage());

        } catch (Exception e) {

            //Captura de Fallos imprevistos
            print("Algo fue mal");
            print(e.getMessage());
        }

        //Datos Menu
        //Patron del Menu
        Pattern menu_options = Pattern.compile("[1-7]");
        //Variable opcion del Menu
        char selected;

        //Display del Menu
        String title = "Datos del Almacen";
        String[] options = new String[7];
        options[0] = "Crear producto";
        options[1] = "Mostrar productos existentes";
        options[2] = "Eliminar producto por codigo";
        options[3] = "Restaurar ultimo cambio del inventario";
        options[4] = "Modificar cantidad de un producto";
        options[5] = "Guardar productos en el Fichero";
        options[6] = "Salir";
        String inputPetition = "Introduce el numero de una opcion: ";

        //Comienzo codigo por Loop Do-While
        do {

            //Scanner lectura por si se necesita
            input = new Scanner(System.in);
            //Display del Menu
            menuMaker(title,options,inputPetition);

            //Lectura del input
            try {

                //Seleccion del primer caracter como input
                selected = inputRequest(menu_options).charAt(0);

                //Errores
            } catch (InputIncorrectoException e) {

                //Si el Input no es valido, asignamos valor por defecto e
                selected = 'e';
            }

            //Seleccion del Input
            switch (selected) {

                //Caso 1 - Crear Producto
                case '1':

                    print("\nCreando un nuevo producto:");

                    product = createProduct();
                    product = storage.put(product.getCodigo(), product);
                    print("\nProducto creado exitosamente");

                    //Producto no es null si reemplaza a un producto
                    if (product != null) {
                        print("\nEl siguiente producto ha sido sobreescrito:");
                        print(product.toString());
                    }

                    //Datos no guardados
                    allSaved = false;
                    pause();
                    break;

                //Caso 2 - Mostrar Productos Existentes
                case '2':

                    print("\nMostrando todos los productos:\n");

                    if (!storage.isEmpty()) {
                        for (Producto p : storage.values()) {
                            print(p.toString());
                        }
                    }
                    else {
                        print("Almacen esta vacio");
                    }

                    pause();
                    break;

                //Caso 3 - Eliminar Producto por Codigo
                case '3':

                    print("\nEliminando producto por codigo:");

                    cod = requestCodigo();
                    product = storage.remove(cod);

                    if (product == null) {
                        print("\nProducto no existe");
                    }
                    else {
                        print("\nProducto eliminado es:");
                        print(product.toString());
                        print("Se ha eliminado exitosamente");
                    }

                    //Datos no guardados
                    allSaved = false;
                    pause();
                    break;

                //Case 4 - Restaurar Ultimo cambio del Inventario
                case '4':

                    //Buscando si existe algun producto en placeholder
                    if (product == null) {
                        print("\nNo hay cambios que restaurar");
                    } else {
                        print("\nIntentando restaurar el producto:");
                        print(product.toString());

                        //Producto es null si no reemplaza a otro producto
                        product = storage.put(product.getCodigo(), product);
                        print("\nProducto se ha insertado de nuevo de forma exitosa");

                        if (product != null) {
                            print("\nEl siguiente producto ha sobreescrito:");
                            print(product.toString());
                        }
                    }

                    //Datos no guardados
                    allSaved = false;
                    pause();
                    break;

                //Case 5 - Modificar Cantidad de un Producto
                case '5':

                    print("\nModificando productos en el almacen:");
                    cod = requestCodigo();

                    if (storage.containsKey(cod)) {
                        print("Se ha encontrado en el almacen, el producto: " + storage.get(cod).getNombre() +
                                " con una cantidad: " + storage.get(cod).getCantidad());

                        print("\nIntroduce ahora la nueva cantidad");
                        amt = requestCantidad();

                        storage.get(cod).setCantidad(amt);
                        print("\nSe ha actualizado la cantidad correctamente");
                        print(storage.get(cod).toString());
                    }
                    else {
                        print("No existe este codigo en el almacen");
                    }

                    //Datos no guardados
                    allSaved = false;
                    pause();
                    break;

                //Case 6 - Guardar Productos en el Fichero
                case '6':

                    print("\nComenzando actualizacion del archivo almacen.dat:");

                    //Edicion del Archivo
                    try (FileWriter fileWriter = new FileWriter(almacenFile, false);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                        for (String key : storage.keySet()) {
                            bufferedWriter.write(key + "," + storage.get(key).getNombre()
                                    + "," + storage.get(key).getCantidad() + "," + storage.get(key).getPrecio());
                            bufferedWriter.newLine();
                            print("Producto ha sido almacenado: " + key);
                        }

                        //Errores
                    } catch (IOException e) {

                        //Fallo al editar el Archivo
                        print("Error al editar el archivo almacen: " + e.getMessage());
                    }

                    //Datos guardados
                    allSaved = true;
                    pause();
                    break;

                //Case 7 - Salir
                case '7':

                    //Confirmacion si hay datos no Guardados
                    if (!allSaved) {
                        print("Hay cambios sin guardar, desea salir?");
                        printHere("Escribe (YES) si deseas salir: ");

                        //Literal "YES" para salir con datos sin guardar
                        if (input.nextLine().equals("YES")) {
                            print("\nSaliendo del programa...");
                        }
                        else {
                            print("Abortando...");
                            //Seleccion variable para no terminar Do-While
                            selected = 'e';
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
                    print("Ooops! No tendrias que estar aqui, input introducido (" + selected + "), notifica a un desarrollador");
                    pause();
                    break;
            }
        }
        //Fin del Do-While
        while (selected != '7');

        //Cerrar inputs
        input.close();
    }

    //Metodo 2
    //requestCodigo - Pedir datos para un Codigo valido
    //@return String de un codigo valido
    private static String requestCodigo() {

        String codigo = "";
        boolean success = false;

        //Loop hasta tener valor valido
        do {
            try {

                printHere("\nIntroduce el codigo del producto: ");
                codigo = inputRequest(PATTERNCOD);
                success = true;

                //Errores
            } catch (InputIncorrectoException e) {

                //El Input no es correcto
                print(e.getMessage());
                print("El codigo del producto tiene que ser 'P-XXXX' donde XXXX son 4 numeros o mas, Intentalo de Nuevo");
            }
        } while (!success);

        return codigo;
    }

    //Metodo 3
    //requestCantidad - Pedir datos para una cantidad valida
    //@return int de una cantidad valida
    private static int requestCantidad() {

        Scanner  in = new Scanner(System.in);
        int cantidad = 0;
        boolean success;
        success = false;

        //Loop hasta tener valor valido
        do {
            try {

                printHere("\nIntroduce la cantidad del producto: ");
                cantidad = in.nextInt();
                success = true;
            } catch (InputMismatchException e) {

                //El Input no es correcto
                print("Cantidad introducida no es valida, intentalo de nuevo");
                in.nextLine();//Se necesita para evitar un bucle infinito de error
                print("Limpiando consola");
            }
        } while (!success);

        return cantidad;
    }

    //Metodo 4
    //CreateProduct - Creacion del Producto
    //@return Producto creado a partir de parametros
    public static Producto createProduct() {

        Producto newProduct;
        String codigo, nombre;
        int cantidad = 0;
        double precio = 0.0;
        Scanner in = new Scanner(System.in);
        boolean success;


        //Creacion de atributos

        //Codigo
        codigo = requestCodigo();

        //Nombre
        do {

            printHere("\nIntroduce el nombre del producto: ");
            nombre = in.nextLine().replaceAll(",", "");

            if (nombre.isEmpty()) {
                print("No introduzcas un nombre vacio");
            }
        } while (nombre.isEmpty());

        //Cantidad
        cantidad = requestCantidad();

        //Precio
        success = false;
        do {

            try {

                printHere("\nIntroduce el precio del producto de hasta 2 decimales: ");
                precio = in.nextDouble();
                precio = Math.round(precio * 100.0) / 100.0;
                success = true;

                //Errores
            } catch (InputMismatchException e) {

                //El Input no es correcto
                print("Precio introducido no es valido, intentalo de nuevo");
                in.nextLine();//Se necesita para evitar un bucle infinito de error
                print("Limpiando consola");
            }
        } while (!success);

        //Creacion Objeto
        newProduct = new Producto(codigo,nombre,cantidad,precio);

        return newProduct;
    }
}