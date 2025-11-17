import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Movimiento {

    //Variables Privadas
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static int contador = 0;
    private final int ID;
    private String fecha;
    private String tipo;
    private double cantidad;

    //Constructores
    Movimiento(String tipo, double cantidad) {
        LocalDate now = LocalDate.now();
        this.fecha = now.format(formatter);
        this.tipo = tipo;
        this.cantidad = Math.abs(cantidad);

        this.ID = ++contador;
    }

    //Getter y Setter
    public int getID() {
        return this.ID;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getTipo() {
        return this.tipo;
    }

    public double getCantidad() {
        return this.cantidad;
    }

    public String mostrarInfoMovimiento() {
        String infoMovimiento;
        infoMovimiento = String.format("ID: %d\nFecha: %s\nTipo: %s\nCantidad: ", this.ID, this.fecha, this.tipo, this.cantidad);
        return infoMovimiento;
    }
}
