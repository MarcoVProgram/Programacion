import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hola Mundo\n");

        Producto producto1 = new Producto("Torres PC", 12);
        Producto producto2 = new Producto("Pantallas 23 pulgadas", 30);
        Producto producto3 = new Producto("Teclados", 63);
        Producto producto4 = new Producto("Laptops", 52);
        Producto producto5 = new Producto("Tablets", 40);

        List<Producto> listaProductos = new ArrayList<>();

        listaProductos.add(producto1);
        listaProductos.add(producto2);
        listaProductos.add(producto3);
        listaProductos.add(producto4);
        listaProductos.add(producto5);

        Iterator<Producto> itera = listaProductos.iterator();
        Producto cadaProducto;
        while (itera.hasNext()) {
            cadaProducto = itera.next();
            System.out.println(cadaProducto.toString());
        }

        listaProductos.sort();
    }
}