import java.util.Objects;

public class Producto {

    //Atributos
    String nombre;
    int cantidad;

    //Constructor
    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    //Getter & Setter
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //ToString
    @Override
    public String toString() {
        return "Producto:" +
                "\nNombre: " + this.nombre +
                "\nCantidad: " + this.cantidad;
    }

    //HashCode & equals
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
