//Clase objeto
public class Producto {

    //Atributos
    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    /*private static int contador = 0;*/

    //Constructores

    //Constructor codigo autogenerado
    //@param nombre
    //@param cantidad
    //@param precio
    /*public Producto(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        contador++;
        this.codigo = String.format("P-%04d", contador);
    }*/

    //@param codigo
    //@param nombre
    //@param cantidad
    //@param precio
    public Producto(String codigo, String nombre, int cantidad, double precio) {

        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.codigo = codigo;
    }

    //Getter y Setter
    //Obtener codigo
    //@return codigo de esta instancia
    public String getCodigo() {
        return codigo;
    }

    //Obtener nombre
    //@return nombre de esta instancia
    public String getNombre() {
        return nombre;
    }

    //Cambiar nombre
    //@param nuevo nombre de esta instancia
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Obtener cantidad
    //@return int cantidad de esta instancia
    public int getCantidad() {
        return cantidad;
    }

    //Cambiar cantidad
    //@param int nueva cantidad de esta instancia
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //Obtener precio
    //@return double precio de esta instancia
    public double getPrecio() {
        return precio;
    }

    //Cambiar precio
    //@param double nuevo precio de esta instancia
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //ToString
    //@return String, por defecto cuando se llama a system out println
    @Override
    public String toString() {

        //Formatted, codigo mayusculas, nombre, cantidad, precio dos decimales
        return String.format("Producto{codigo='%S', nombre='%s', cantidad=%d, precio=%.2f}", codigo, nombre, cantidad, precio);
    }
}