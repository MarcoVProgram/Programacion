import java.io.Serializable;

public class Producto implements Serializable {

    private static final long serialVersionUID = 2671796503100138849L;
    //Variables Privadas
    private String referencia;
    private String descripcion;
    private String tipo;
    private int cantidad;
    private double precio;
    private int descuento;
    private int IVA;
    private boolean aplicarDto;

    //Constructor
    public Producto(String referencia, String descripcion, String tipo) {
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getIVA() {
        return IVA;
    }

    public void setIVA(int IVA) {
        this.IVA = IVA;
    }

    public boolean isAplicarDto() {
        return aplicarDto;
    }

    public void setAplicarDto(boolean aplicarDto) {
        this.aplicarDto = aplicarDto;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Producto {");
        sb.append("referencia='").append(referencia).append('\'');
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", tipo='").append(tipo).append('\'');
        sb.append(", cantidad=").append(cantidad);
        sb.append(", precio=").append(precio);
        sb.append(", descuento=").append(descuento);
        sb.append(", IVA=").append(IVA);
        sb.append(", aplicarDto=").append(aplicarDto);
        sb.append('}');
        return sb.toString();
    }
}
