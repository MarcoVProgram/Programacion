import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {

    //Variables Privadas
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static int contador = 0;
    private final int ID;
    private String fecha;
    private MyUtils.Transaccion tipo;
    private double cantidad;

    //Constructores
    public Movimiento(MyUtils.Transaccion tipo, double cantidad) {
        LocalDateTime now = LocalDateTime.now();
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
        return this.tipo.name();
    }

    public MyUtils.Transaccion getTipoTransaccion() {
        return this.tipo;
    }

    public double getCantidad() {
        return this.cantidad;
    }

    public String mostrarInfoMovimiento() {
        String infoMovimiento;
        infoMovimiento = String.format("ID: %d\nFecha: %s\nTipo: %s\nCantidad: %.2f", this.ID, this.fecha, this.tipo.name(), this.cantidad);
        return infoMovimiento;
    }
}
