package almacen;

public class Producto {

    //Variables Privadas
    private int id;
    private String referencia;
    private String nombre;
    private String descripcion;
    private int tipo;
    private int cantidad;
    private double precio;
    private int descuento;
    private int IVA;
    private boolean aplicarDto;

    //Constructor
    public Producto(int id, String referencia, String nombre, String descripcion, int tipo,
                    int cantidad, double precio, int descuento, int IVA, boolean aplicarDto) {

        this.id = id;
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.IVA = IVA;
        this.aplicarDto = aplicarDto;
    }

    //Getter
    public int getId() {
        return this.id;
    }

    public String getReferencia() {
        return this.referencia;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public int getTipo() {
        return this.tipo;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public double getPrecio() {
        return this.precio;
    }

    public int getDescuento() {
        return this.descuento;
    }

    public int getIVA() {
        return this.IVA;
    }

    public boolean isAplicandoDto() {
        return this.aplicarDto;
    }

    //Setter
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public void setIVA(int IVA) {
        this.IVA = IVA;
    }

    public void setAplicarDto(boolean aplicarDto) {
        this.aplicarDto = aplicarDto;
    }

    //ToString
    @Override
    public String toString() {
        String info;

        //String Final
        info = String.format("Producto --> [ ID: %d\t|\tReferencia: %S\t|\t Nombre: %s\t|\tTipo: %d ]",
                this.id, this.referencia, this.nombre, this.tipo);

        info += String.format("\t( Cantidad: %d\t|\tPrecio: %.2f\t|\tDescuento: %d\t|\tIVA: %d\t|\tDescuento Activo: %b )",
                this.cantidad, this.precio, this.descuento, this.IVA, aplicarDto);

        info += String.format("\nDescripcion: %s", this.descripcion);

        return info;
    }
}