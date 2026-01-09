import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hola Mundo\n");

        System.out.println("Seleccione, desea usar ArrayList o LinkedList");
        System.out.println("-----------------------------");
        System.out.println("1. ArrayList");
        System.out.println("2. LinkedList");
        System.out.print("Seleccione una opcion (Numero): ");

        Scanner sc;
        boolean valido = false;
        char opcion;
        do {
            sc = new Scanner(System.in);
            opcion = sc.nextLine().charAt(0);
            if (opcion != '1' && opcion != '2') {
                System.out.print("La opcion (" + opcion + ") no es una opcion valida, intentalo de nuevo: ");
            }
            else  {
                valido = true;
            }
        } while (!valido);

        System.out.println("\n");

        switch (opcion) {
            case '1':
                /* ARRAYLIST */
                //1. Crea 5 instancias de la clase Producto
                Producto producto1AL = new Producto("Torres PC", 12);
                Producto producto2AL = new Producto("Pantallas", 30);
                Producto producto3AL = new Producto("Teclados", 63);
                Producto producto4AL = new Producto("Laptops", 52);
                Producto producto5AL = new Producto("Tablets", 40);

                //2. Creación de un ArrayList
                List<Producto> listaProductosAL = new ArrayList<>();

                //3. Instancias del producto introducidas dentro de ArrayList
                listaProductosAL.add(producto1AL);
                listaProductosAL.add(producto2AL);
                listaProductosAL.add(producto3AL);
                listaProductosAL.add(producto4AL);
                listaProductosAL.add(producto5AL);

                //4. Visualización del contenido de ArrayList usando Iterator
                Iterator<Producto> iteraAL = listaProductosAL.iterator();
                Producto cadaProductoAL;

                System.out.println("Mi Lista:");
                while (iteraAL.hasNext()) {
                    cadaProductoAL = iteraAL.next();
                    System.out.println(cadaProductoAL.toString());
                }
                System.out.println("\n");

                //5. Eliminación de dos elementos de ArrayList
                listaProductosAL.remove(producto2AL);
                listaProductosAL.remove(producto3AL);

                //6. Inserción de un nuevo producto en medio de la lista
                Producto producto6AL = new Producto("Ratones", 25);
                listaProductosAL.add(2, producto6AL);

                //7. Re-visualización del contenido de ArrayList usando Iterator
                iteraAL = listaProductosAL.iterator();

                System.out.println("Mi Nueva Lista:");
                while (iteraAL.hasNext()) {
                    cadaProductoAL = iteraAL.next();
                    System.out.println(cadaProductoAL.toString());
                }
                System.out.println("\n");

                //8. Ordenación de la lista (usando CompareTo y sort)
                Collections.sort(listaProductosAL);

                iteraAL = listaProductosAL.iterator();

                System.out.println("Mi Nueva Lista Ordenada:");
                while (iteraAL.hasNext()) {
                    cadaProductoAL = iteraAL.next();
                    System.out.println(cadaProductoAL.toString());
                }
                System.out.println("\n");

                //9. Eliminación de todos los elementos del ArrayList
                listaProductosAL.clear();

                iteraAL = listaProductosAL.iterator();

                System.out.println("Mi Lista Vacia:");
                while (iteraAL.hasNext()) {
                    cadaProductoAL = iteraAL.next();
                    System.out.println(cadaProductoAL.toString());
                }

                //Fin de ArrayList
                break;

            case '2':
                /* LINKEDLIST */
                //1. Crea 5 instancias de la clase Producto
                Producto producto1LL = new Producto("Torres PC", 12);
                Producto producto2LL = new Producto("Pantallas", 30);
                Producto producto3LL = new Producto("Teclados", 63);
                Producto producto4LL = new Producto("Laptops", 52);
                Producto producto5LL = new Producto("Tablets", 40);

                //2. Creación de un LinkedList
                List<Producto> listaProductosLL = new LinkedList<>();

                //3. Instancias del producto introducidas dentro de LinkedList
                listaProductosLL.add(producto1LL);
                listaProductosLL.add(producto2LL);
                listaProductosLL.add(producto3LL);
                listaProductosLL.add(producto4LL);
                listaProductosLL.add(producto5LL);

                //4. Visualización del contenido de LinkedList usando Iterator
                Iterator<Producto> iteraLL = listaProductosLL.iterator();
                Producto cadaProductoLL;

                System.out.println("Mi Lista:");
                while (iteraLL.hasNext()) {
                    cadaProductoLL = iteraLL.next();
                    System.out.println(cadaProductoLL.toString());
                }
                System.out.println("\n");

                //5. Eliminación de dos elementos de LinkedList
                listaProductosLL.remove(producto2LL);
                listaProductosLL.remove(producto3LL);

                //6. Inserción de un nuevo producto en medio de la lista
                Producto producto6LL = new Producto("Ratones", 25);
                listaProductosLL.add(2, producto6LL);

                //7. Re-visualización del contenido de LinkedList usando Iterator
                iteraLL = listaProductosLL.iterator();

                System.out.println("Mi Nueva Lista:");
                while (iteraLL.hasNext()) {
                    cadaProductoLL = iteraLL.next();
                    System.out.println(cadaProductoLL.toString());
                }
                System.out.println("\n");

                //8. Ordenación de la lista (usando CompareTo y sort)
                Collections.sort(listaProductosLL);

                iteraLL = listaProductosLL.iterator();

                System.out.println("Mi Nueva Lista Ordenada:");
                while (iteraLL.hasNext()) {
                    cadaProductoLL = iteraLL.next();
                    System.out.println(cadaProductoLL.toString());
                }
                System.out.println("\n");

                //9. Eliminación de todos los elementos del LinkedList
                listaProductosLL.clear();

                iteraLL = listaProductosLL.iterator();

                System.out.println("Mi Lista Vacia:");
                while (iteraLL.hasNext()) {
                    cadaProductoLL = iteraLL.next();
                    System.out.println(cadaProductoLL.toString());
                }

                //Fin de LinkedList
                break;
        }

        System.out.println("\n");
        System.out.println("Fin del Programa");
        sc.close();
    }
}