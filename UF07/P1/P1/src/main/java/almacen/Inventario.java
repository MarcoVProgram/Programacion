package almacen;

import almacen.exceptions.InputIncorrectoException;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Inventario {
    public static void main(String[] args) {

        MyUtils.print("BIENVENIDO AL INVENTARIO DE MERCADAW");

        Map<String, Producto> productos = actualizarProductosPorSQL();
        ColeccionTipos tipos = actualizarTiposPorSQL();

        if (productos.isEmpty()) {
            MyUtils.print("\nExiste una carpeta 'resources' en el mismo nivel que 'almacen' que posee Scripts SQL para crear la base de datos" +
                    ", si desea usarlos, por favor, ejecutelos antes de continuar (salga del programa primero)\n\n" +

                    "Tambien puede crear la base desde cero. Asegurese que el script sqlCreateTables haya sido ejecutado al menos");
        }

        String[] menu = new String[9];
        menu[0] = "Mostrar Todos los Productos en el Inventario";
        menu[1] = "Buscar Producto por Referencia";
        menu[2] = "Buscar Producto por Tipo";
        menu[3] = "Buscar Producto por Cantidad";
        menu[4] = "Insertar Nuevo Producto";
        menu[5] = "Eliminar Producto por Referencia";
        menu[6] = "Actualizar Producto por Referencia";
        menu[7] = "Insertar un nuevo Tipo de Producto";
        menu[8] = "Salir";

        char option;
        Pattern menuPattern = Pattern.compile("[1-9]");

        do {

            MyUtils.menuMaker("MERCADAW - MENU",
                    menu,
                    "Inserte una opcion: ");

            try {
                option = MyUtils.inputRequest(menuPattern).charAt(0);
            } catch (InputIncorrectoException e) {
                option = 'e';
            }

            switch (option) {

                //CASE 1 - Mostrar Todos los Productos en el Inventario
                case '1':

                    verListaProds(productos);
                    MyUtils.pause();
                    break;

                //CASE 2 - Buscar Producto por Referencia
                case '2':

                    MyUtils.print("\nBuscando Producto por Referencia");
                    String refBuscar = pedirReferenciaProd();

                    Producto productoBuscar = SQLAccessProducts.buscarProductoPorRef(refBuscar);

                    if (productoBuscar != null) {
                        MyUtils.print("Producto encontrado:\n" + productoBuscar.toString());
                    } else {
                        MyUtils.print("No se ha encontrado este producto");
                    }

                    MyUtils.pause();
                    break;

                //CASE 3 - Buscar Producto por Tipo
                case '3':

                    MyUtils.print("\nBuscando Producto por Tipo");

                    int idTipo = pedirTipoExistente(tipos);

                    List<Producto> productosPorTipo = SQLAccessProducts.buscarProductoPorTipo(idTipo);

                    if (productosPorTipo.isEmpty()) {
                        MyUtils.print("\nNo se han encontrado productos con este tipo");
                    } else {
                        MyUtils.print("\nProductos encontrados:");
                        for (Producto producto : productosPorTipo) {
                            MyUtils.print(producto.toString());
                        }
                    }

                    MyUtils.pause();
                    break;

                //CASE 4 - Buscar Producto por Cantidad
                case '4':

                    MyUtils.print("\nBuscando Producto por Cantidad");

                    int cantidad = MyUtils.insertValidInt("Introduce una cantidad del producto (Minimo 0)",
                            "El valor introducido no es un numero");

                    if (cantidad < 0) {
                        cantidad = 0;
                    }

                    List<Producto> productosPorCantidad = SQLAccessProducts.buscarProductoPorCantidad(cantidad);

                    if (productosPorCantidad.isEmpty()) {
                        MyUtils.print("\nNo se han encontrado productos con esta cantidad");
                    } else {
                        MyUtils.print("\nProductos encontrados:");
                        for (Producto producto : productosPorCantidad) {
                            MyUtils.print(producto.toString());
                        }
                    }

                    MyUtils.pause();
                    break;

                //CASE 5 - Insertar Nuevo Producto
                case '5':

                    Producto newProduct = crearNuevoProducto(tipos);
                    newProduct = productos.putIfAbsent(newProduct.getReferencia() ,newProduct);

                    if (newProduct == null) {
                        MyUtils.print("Producto ha sido insertado de forma correcta");
                    } else {
                        MyUtils.print("Producto ya existe, abortando");
                    }

                    MyUtils.pause();
                    break;

                //CASE 6 - Eliminar Producto por Referencia
                case '6':

                    verListaProds(productos);
                    MyUtils.print("\nBorrando Producto por Referencia");

                    String refBorrar = pedirReferenciaProd();

                    int exitoBorrar = SQLAccessProducts.borrarProductoPorRef(refBorrar);

                    if (exitoBorrar == 1) {
                        MyUtils.print("Producto borrado con exito");
                        productos.remove(refBorrar);
                    } else {
                        MyUtils.print("Producto no existe o no se pudo borrar, abortando");
                    }

                    MyUtils.pause();
                    break;

                //CASE 7 - Actualizar Producto por Referencia
                case '7':

                    verListaProds(productos);
                    Producto prodActualizado = actualizarProducto();

                    if (prodActualizado != null) {
                        productos.put(prodActualizado.getReferencia(), prodActualizado);
                        MyUtils.print("Producto actualizado:\n" + prodActualizado.toString());
                    }

                    MyUtils.pause();
                    break;

                //CASE 8 - Insertar un nuevo Tipo de Producto
                case '8':

                    crearNuevoTipoProducto(tipos);
                    MyUtils.pause();
                    break;

                //CASE 9 - Salir
                case '9':

                    final String path = ".\\src\\main\\resources\\";
                    final String sqlFile = "sqlExportInserts.sql";

                    boolean save = MyUtils.confirm("Deseas crear/actualizar sqlExportInserts.sql?" +
                            "\nAVISO - puede que el archivo no este protegido a inyecciones como texto plano, utilizalo unicamente para crear datos a exportar en texto plano" +
                            "\nQuieres continuar de todos modos? ");

                    if (save) {
                        MyUtils.print("\nEditando Archivo");
                        try (FileWriter fw = new FileWriter(path + sqlFile, false);
                             BufferedWriter bw = new BufferedWriter(fw)) {


                            Iterator<Tipo> itTipos = tipos.getListaTipos().iterator();

                            while (itTipos.hasNext()) {
                                Tipo t = (Tipo) itTipos.next();
                                String insert = "INSERT INTO tipos VALUE (" + t.getIdTipo() + ", '" + t.getTipoNombre() + "');";
                                bw.write(insert);
                                bw.newLine();
                            }

                            Iterator<Producto> itProd = productos.values().iterator();
                            bw.newLine();

                            while (itProd.hasNext()) {
                                Producto p = (Producto) itProd.next();
                                String insert = "INSERT INTO productos VALUE (" + p.getId() + ", '" + p.getReferencia() + "', '" + p.getNombre() + "', '" +
                                        p.getDescripcion().replace("'", "''").replace("\\","\\\\") +
                                        "', " + p.getTipo() + ", " + p.getCantidad() + ", " + p.getPrecio() + ", " +
                                        p.getDescuento() + ", " + p.getIVA() + ", " + p.isAplicandoDto() + ");";
                                bw.write(insert);
                                bw.newLine();
                            }

                        } catch (FileNotFoundException e) {

                            try {
                                File file = new File(path + sqlFile);

                                if (file.createNewFile()) {
                                    MyUtils.print("Archivo creado con exito");
                                }

                            } catch (IOException e1) {
                                MyUtils.print("Error al crear el archivo");
                            }
                        }
                        catch (IOException e) {

                            MyUtils.print("Error en el I/O: " + e.getMessage());
                        }
                    }

                    MyUtils.print("\nSaliendo del Programa");

                    break;

                //CASE ERROR
                default:
                    MyUtils.print("Error al leer input, no opcion valida");
            }

        } while (option != '9');
    }

    public static Map<String, Producto> actualizarProductosPorSQL() {
        Map<String, Producto> misProductos = new LinkedHashMap<>();

        List<Producto> productosSQL = SQLAccessProducts.obtenerProductos();

        for (Producto producto : productosSQL) {

            misProductos.put(producto.getReferencia(), producto);
        }

        return misProductos;
    }

    public static ColeccionTipos actualizarTiposPorSQL() {
        ColeccionTipos misTipos = new ColeccionTipos();

        List<Tipo> tiposSQL = SQLAccessProducts.obtenerTipos();

        for (Tipo tipo : tiposSQL) {

            misTipos.add(tipo);
        }

        return misTipos;
    }

    public static String pedirReferenciaProd() {
        String referencia = MyUtils.insertValidString("Introduce la Referencia del Producto " +
                "(Usa letras o numeros, debe ser hasta 10 caracteres)",
                Patrones.REFERENCIA_FORM,
                "La referencia deben ser 10 caracteres, no mas, y no menos. Deben usar solo letras y numeros. " +
                        "(Ej: 'A111223Z')").toUpperCase();

        return referencia;
    }

    public static void verListaProds(Map<String, Producto> productos) {
        MyUtils.print("\nLista de Productos en el Inventario:");
        for  (Map.Entry<String, Producto> entry : productos.entrySet()) {
            MyUtils.print(entry.getValue().toString());
        }
    }

    public static String verListaTipos(ColeccionTipos tipos) {
        String lista = "";

        List<String> todosLosTipos = tipos.getStringListaTipos();

        for (String tipoString : todosLosTipos) {

            lista += tipoString + "\n";
        }

        return lista;
    }

    public static boolean crearNuevoTipoProducto(ColeccionTipos tipos) {

        MyUtils.print("\nCreando Tipo de Producto");

        if (!tipos.getListaTipos().isEmpty()) {
            MyUtils.print(verListaTipos(tipos));
        }

        String nombreTipo = MyUtils.insertValidString("Introduzca el nombre de este producto",
                Patrones.NOMBRE_FORM,
                "No puedes introducir mas de 20 caracteres o menos de 1, usa solo letras, numeros y espacios").toUpperCase();

        if (!tipos.tipoExists(nombreTipo)) {

            int index = SQLAccessProducts.insertarTipoProducto(new Tipo(-1, nombreTipo));
            Tipo tipo = SQLAccessProducts.buscarTipoPorId(index);
            return tipos.add(tipo);
        }

        return false;
    }

    public static Producto crearNuevoProducto(ColeccionTipos tipos) {
        Producto nuevoProducto;

        if (tipos.getListaTipos().isEmpty()) {
            crearNuevoTipoProducto(tipos);
        }


        MyUtils.print("\nCreando Producto");


        String referencia;
        boolean existe;
        do {

            referencia = pedirReferenciaProd();
            existe = SQLAccessProducts.referenciaExiste(referencia);

            if (existe) {
                MyUtils.print("Existe la referencia: " + referencia + ", por favor, use otra");
            }
        } while (existe);


        String nombre = MyUtils.insertValidString("Introduce el nombre del Producto",
                Patrones.NOMBRE_FORM,
                "El nombre no puede estar vacio, y debe ser de hasta 20 caracteres, usando letras, espacios y numeros (Ej: 'Coche')");


        String descripcion = MyUtils.insertValidString("Escribe ahora la Descripcion del Producto (No mas de 100 caracteres)",
                Patrones.DESCRIPTION_FORM,
                "No puedes exceder los 100 caracteres para la descripcion. (Puedes dar enter para dejar el campo en blanco)");


        int idTipo = pedirTipoExistente(tipos);


        int cantidad = MyUtils.insertValidInt("Introduce una cantidad del producto (Minimo 0)",
                "El valor introducido no es un numero");

        if (cantidad < 0) {

            cantidad = 0;

        }


        double precio = MyUtils.insertValidDouble("Introduce el precio de este producto (entre 0.01 y 9999.99)",
                "El numero introducido no es un numero decimal", 2);

        if (precio < 0.01) {
            precio = 0.01;
        } else if (precio > 9999.99) {
            precio = 9999.99;
        }


        int descuento = MyUtils.insertValidInt("Introduce el porcentaje de descuento del producto",
                "El numero introducido no es un numero valido, no ha de tener decimales y debe estar entre 0 y 100", 0, 100);


        int IVA = MyUtils.insertValidInt("Introduce el porcentaje de IVA que este producto posee",
                "El valor introducido no es un IVA valido, debe estar entre 0 y 100", 0, 100);


        boolean aplicarDto = MyUtils.confirm("Se aplica el descuento? ");



        SQLAccessProducts.insertarProducto(new Producto(-1, referencia, nombre, descripcion, idTipo, cantidad, precio, descuento, IVA, aplicarDto));
        nuevoProducto = SQLAccessProducts.buscarProductoPorRef(referencia);

        return nuevoProducto;
    }

    private static int pedirTipoExistente(ColeccionTipos tipos) {
        MyUtils.print("La lista de tipos disponibles a elegir es la siguiente:");
        MyUtils.print(verListaTipos(tipos));
        int idTipo;

        if (tipos.getListaTipos().size() > 1) {

            idTipo = MyUtils.insertInt("Introduce el ID del Tipo de Producto", tipos.getListaTipos().get(0).getIdTipo());

            boolean isValid = false;

            for (Tipo tipo : tipos.getListaTipos()) {
                if (tipo.getIdTipo() == idTipo) {
                    isValid = true;
                }
            }

            if (!isValid) {

                idTipo = tipos.getListaTipos().get(0).getIdTipo();
                MyUtils.print("Se ha seleccionado el valor " + idTipo + ", ya que no existe el proporcionado");
            }

        } else {

            idTipo = tipos.getListaTipos().get(0).getIdTipo();
            MyUtils.print("Existe solo un unico tipo de producto, autoasignando");

        }
        return idTipo;
    }

    public static Producto actualizarProducto() {

        MyUtils.print("\nSelecciona el producto a actualizar");
        String refActualizar = pedirReferenciaProd();

        Producto actProd = SQLAccessProducts.buscarProductoPorRef(refActualizar);

        if (actProd != null) {


            int cantidad = MyUtils.insertValidInt("Introduce una cantidad del producto (Minimo 0)",
                    "El valor introducido no es un numero");

            if (cantidad < 0) {

                cantidad = 0;

            }


            double precio = MyUtils.insertValidDouble("Introduce el precio de este producto (entre 0.01 y 9999.99)",
                    "El numero introducido no es un numero decimal", 2);

            if (precio < 0.01) {
                precio = 0.01;
            } else if (precio > 9999.99) {
                precio = 9999.99;
            }


            int descuento = MyUtils.insertValidInt("Introduce el porcentaje de descuento del producto",
                    "El numero introducido no es un numero valido, no ha de tener decimales y debe estar entre 0 y 100", 0, 100);


            int IVA = MyUtils.insertValidInt("Introduce el porcentaje de IVA que este producto posee",
                    "El valor introducido no es un IVA valido, debe estar entre 0 y 100", 0, 100);


            boolean aplicarDto = MyUtils.confirm("Se aplica el descuento?");

            actProd.setCantidad(cantidad);
            actProd.setPrecio(precio);
            actProd.setDescuento(descuento);
            actProd.setIVA(IVA);
            actProd.setAplicarDto(aplicarDto);

            int exitoActualizar = SQLAccessProducts.actualizarProducto(actProd);

            if (exitoActualizar == 1) {
                MyUtils.print("Producto actualizado correctamente");
            } else {
                MyUtils.print("Producto no actualizado");
            }

            actProd = SQLAccessProducts.buscarProductoPorRef(refActualizar);

            return actProd;

        } else {

            MyUtils.print("No se puede actualizar el producto por referencia");
            return actProd;
        }
    }


}