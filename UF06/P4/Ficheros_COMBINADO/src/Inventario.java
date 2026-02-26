import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inventario {

    public static void main(String[] args) {

        //path a la carpeta Recursos del proyecto
		final String pathFile = "./src/Recursos/";

		//Nombre del fichero Binario
		final String fileNameBinario = "almacen.dat";

		//Nombre del fichero UNICODE
		final String fileNameUnicode = "productos.csv";

        //Nombre del fichero Serializable
        final String fileNameSerializable = "inventario.dat";
        File inventario = new File(pathFile + fileNameSerializable);

        //Variables Lectura de Archivos y Scanner
        Scanner scanner = new Scanner(System.in);
        boolean isReadingSerializable = false;
        boolean eof = false;
        LinkedList<Producto> productosLeidos = new LinkedList<>();

        if (inventario.exists()) {
            MyUtils.printHere("Existe un inventario.dat con datos de uso anteriormente." +
                    "\nPresione 1 si desea usar este o presione cualquier otra tecla si desea usar los archivos por defecto en su lugar." +
                    "\nEscoja su opcion: ");
            String input = scanner.nextLine();

            if (!input.isEmpty() && input.charAt(0) == '1') {
                isReadingSerializable = true;

                //Lectura de Fichero Serializable
                //Instanciamiento de lectura
                try (FileInputStream fileReader = new FileInputStream(inventario);
                     ObjectInputStream bufferedReader = new ObjectInputStream(fileReader)) {

                    //Loop hasta complecion
                    while (eof == false) {

                        //Lectura
                        Producto temp = (Producto) bufferedReader.readObject();
                        productosLeidos.add(temp);

                    }

                    //Errores
                } catch (EOFException e) {

                    //Fin del Archivo
                    eof = true;

                } catch (IOException e) {

                    //Fallo al intentar leer el archivo
                    MyUtils.print("No se pudo usar el documento en el I/O");
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
                    e.printStackTrace();
                }
            }


        }

        if (!isReadingSerializable) {
            //Lectura Ficheros Unicode
            productosLeidos = leerFicheroUnicode(pathFile, fileNameUnicode);

            //Lectura de fichero binario
            int i = 0;

            try (FileInputStream fichero = new FileInputStream(pathFile + fileNameBinario);
                 DataInputStream lector = new DataInputStream(fichero)) {
                while (!eof) {

                    int cantidad = lector.readInt();
                    double precio = lector.readDouble();
                    int descuento = lector.readInt();
                    int IVA = lector.readInt();
                    boolean aplicarDto = lector.readBoolean();


                    productosLeidos.get(i).setCantidad(cantidad);
                    productosLeidos.get(i).setPrecio(precio);
                    productosLeidos.get(i).setDescuento(descuento);
                    productosLeidos.get(i).setIVA(IVA);
                    productosLeidos.get(i).setAplicarDto(aplicarDto);

                    if (i < productosLeidos.size()) {
                        i++;
                    }

                }


            } catch (EOFException e) {
                eof = true;


            } catch (IOException e) {
                MyUtils.print("Ha ocurrido un error al I/O");
                MyUtils.print(e.getMessage());

            } catch (Exception e) {
                MyUtils.print("Ha ocurrido un error inexperado");
                MyUtils.print(e.getMessage());
            }
        }

        // LOOP PRINCIPAL
        String ref;
        Pattern refPattern = Pattern.compile("ref-[0-9]{3,}");
        boolean success;

        //Datos Menu
        //Patron del Menu
        Pattern menu_options = Pattern.compile("[1-4]");
        //Variable opcion del Menu
        char choice;

        //Display del Menu
        String title = "Datos del Inventario";
        String[] options = new String[4];
        options[0] = "Mostrar Productos en el Inventario";
        options[1] = "Eliminar Productos por Referencia";
        options[2] = "Guardar y Salir";
        options[3] = "Registrar Producto en el Inventario";
        String inputPetition = "Introduce el numero de una opcion: ";

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

            switch (choice) {

                //Case 1 - Mostrar Productos en el Inventario
                case '1':

                    MyUtils.print("\nMostrando los Productos existentes en el Inventario\n");

                    for (Producto producto : productosLeidos) {
                        MyUtils.print(producto.toString());
                    }

                    MyUtils.pause();
                    break;

                //Case 2 - Eliminar Producto por Referencia
                case '2':

                    MyUtils.print("\nBorrando Producto por Referencia\n(Sintaxis de referencia: 'ref-XXX')\n");
                    scanner = new Scanner(System.in);

                    MyUtils.printHere("Introduce la referencia del producto a borrar: ");
                    ref = scanner.nextLine();
                    success = false;
                    Producto temp = null;

                    Matcher matcher = refPattern.matcher(ref);
                    if (!matcher.matches()) {
                        MyUtils.print("Cancelando intento de Borrado, referencia no es valida");
                        MyUtils.pause();
                        continue;
                    }

                    for (Producto producto : productosLeidos) {
                        if (producto.getReferencia().equalsIgnoreCase(ref)) {

                            MyUtils.print("Producto existe en el Inventario, borrando");
                            MyUtils.print(producto.toString());
                            temp = producto;
                            success = true;
                            break;
                        }
                    }

                    if (success) {
                        productosLeidos.remove(temp);
                    }
                    else {
                        MyUtils.print("Producto de codigo (" + ref + ") No existe en el Inventario");
                    }

                    MyUtils.pause();
                    break;

                //Case 3 - Guardar y Salir
                case '3':

                    MyUtils.print("\nSaliendo del Programa, Guardando Inventario antes de Salir");

                    //Seleccion del archivo
                    try {

                        inventario = new File(pathFile + fileNameSerializable);

                        //Verdadero y creado si no hay archivo, falso si hay archivo
                        if (inventario.createNewFile()) {
                            MyUtils.print("Creando el archivo inventario.dat en ruta (" + inventario + ")");
                        } else {
                            MyUtils.print("El archivo (inventario.dat) existe, sobreescribiendo");
                        }

                        //Errores
                    } catch (IOException e) {

                        //Fallo si ruta no se encuentra u otro problema con el archivo
                        MyUtils.print("Error al usar el archivo (inventario.dat): " + e.getMessage());
                    }

                    //Edicion del Archivo
                    try (FileOutputStream fileWriter = new FileOutputStream(inventario, false);
                        ObjectOutputStream bufferedWriter = new ObjectOutputStream(fileWriter)) {

                        for  (Producto p : productosLeidos) {
                            bufferedWriter.writeObject(p);
                        }

                        MyUtils.print("Saliendo del programa de forma exitosa");

                        //Errores
                    } catch (IOException e) {

                        //Fallo al editar el Archivo
                        MyUtils.print("Error al editar el archivo inventario.dat: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                //Registrar Producto en el Inventario
                case '4':

                    MyUtils.print("\nRegistrando Producto en el Inventario");

                    MyUtils.print("\nIntroduce la referencia del producto:");
                    ref = MyUtils.inputRequestLoop(refPattern, "Referencia mal introducida, sigue la sintaxis 'ref-XXX'" +
                            " donde XXX pueden ser 3 o mas digitos");

                    boolean abort = false;
                    for (Producto producto : productosLeidos) {
                        if (producto.getReferencia().equalsIgnoreCase(ref)) {
                            MyUtils.print("Producto existe en el Inventario, no se admiten repetidos");
                            abort = true;
                            MyUtils.pause();
                            break;
                        }
                    }
                    if (abort) {
                        continue;
                    }

                    Producto nuevoProducto = registrarNuevoProducto(ref);
                    productosLeidos.add(nuevoProducto);
                    MyUtils.pause();
                    break;

                //Case e - Si saltaron errores
                case 'e':
                    MyUtils.print("\nEl input que has insertado no es un numero entre 1 y 4");
                    MyUtils.pause();
                    break;

                //Case default - valor no accessible para control
                default:
                    MyUtils.print("Ooops! No tendrias que estar aqui, input introducido (" + choice + "), notifica a un desarrollador");
                    MyUtils.pause();
                    break;
            }
        } while (choice != '3');

        scanner.close();
    }

    private static Producto registrarNuevoProducto(String ref) {
        Scanner sc = new Scanner(System.in);

        //Parametros creacion del Objeto
        String desc, tipo;
        int cantidad;
        double precio;
        int descuento, IVA;
        boolean aplicarDto;

        Producto nuevoProducto;

        boolean success;

        MyUtils.printHere("Escriba la descripcion del producto: ");
        desc = sc.nextLine();

        MyUtils.printHere("Escriba el tipo del producto: ");
        tipo = sc.nextLine();

        nuevoProducto = new Producto(ref, desc, tipo);

        success = false;
        do {
            try {

                MyUtils.printHere("Introduce la cantidad del producto: ");
                cantidad = sc.nextInt();
                success = true;
                nuevoProducto.setCantidad(cantidad);

                //Errores
            } catch (InputMismatchException e) {

                //El Input no es correcto
                MyUtils.print("Cantidad introducida no es valida, intentalo de nuevo");
                sc.nextLine();//Se necesita para evitar un bucle infinito de error
            }
        } while (!success);

        success = false;
        do {
            try {

                MyUtils.printHere("Introduce el precio del producto de hasta 2 decimales: ");
                precio = sc.nextDouble();
                precio = Math.round(precio * 100.0) / 100.0;
                success = true;
                nuevoProducto.setPrecio(precio);

                //Errores
            } catch (InputMismatchException e) {

                //El Input no es correcto
                MyUtils.print("Precio introducido no es valido, intentalo de nuevo");
                sc.nextLine();//Se necesita para evitar un bucle infinito de error
            }
        } while (!success);

        success = false;
        do {
            try {

                MyUtils.printHere("Introduce el descuento del producto (0 si no hay descuento): ");
                descuento = sc.nextInt();
                success = true;
                nuevoProducto.setDescuento(descuento);

                //Errores
            } catch (InputMismatchException e) {

                //El Input no es correcto
                MyUtils.print("Descuento introducido no es valido, intentalo de nuevo");
                sc.nextLine();//Se necesita para evitar un bucle infinito de error
            }
        } while (!success);

        success = false;
        do {
            try {

                MyUtils.printHere("Introduce el IVA del producto: ");
                IVA = sc.nextInt();
                success = true;
                nuevoProducto.setIVA(IVA);

                //Errores
            } catch (InputMismatchException e) {

                //El Input no es correcto
                MyUtils.print("IVA introducido no es valido, intentalo de nuevo");
                sc.nextLine();//Se necesita para evitar un bucle infinito de error
            }
        } while (!success);

        success = false;
        do {
            try {

                MyUtils.printHere("Introduce si aplicamos descuento: ");
                aplicarDto = sc.nextBoolean();
                success = true;
                nuevoProducto.setAplicarDto(aplicarDto);

                //Errores
            } catch (InputMismatchException e) {

                //El Input no es correcto
                MyUtils.print("Booleano introducido no es valido, escriba 'true' o 'false', intentalo de nuevo");
                sc.nextLine();//Se necesita para evitar un bucle infinito de error
            }
        } while (!success);

        MyUtils.print("\nProducto Completado");
        MyUtils.print(nuevoProducto.toString());
        return nuevoProducto;
    }

    private static LinkedList<Producto> leerFicheroUnicode(final String pathFile, String fileName) {
		LinkedList<Producto> lineas = null;
		if(pathFile != null && fileName != null)
		{
			lineas = new LinkedList<Producto>();

			try (FileReader file = new FileReader(pathFile+fileName);
                 BufferedReader buffer = new BufferedReader(file);)
			{
					String linea = null;
					do {
						linea = buffer.readLine();

						if(linea != null) {

							String [] elementos = linea.split("/");
							Producto p =
									new Producto(elementos[0], elementos[2], elementos[3]);




							lineas.add(p);

						}

					}while(linea != null);

					return lineas;

			}catch(IOException e) {
				e.printStackTrace();
			}
		}

		return lineas;

	}
}
