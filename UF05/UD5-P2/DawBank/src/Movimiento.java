import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {

    //Variables Privadas
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static int contador = 0;
    private final int ID;
    private LocalDateTime fecha;
    private Transaccion tipo;
    private double cantidad;

    //Constructores
    public Movimiento(Transaccion tipo, double cantidad) {
        this.fecha = LocalDateTime.now();
        this.tipo = tipo;
        this.cantidad = Math.abs(cantidad);

        contador++;
        this.ID = contador;
    }

    //Getter y Setter
    public int getID() {
        return this.ID;
    }

    public String getFecha() {
        return this.fecha.format(formatter);
    }

    public String getTipo() {
        return this.tipo.name();
    }

    public Transaccion getTipoTransaccion() {
        return this.tipo;
    }

    public double getCantidad() {
        return this.cantidad;
    }

    @Override
    public String toString() {
        String infoMovimiento;
        infoMovimiento = String.format("ID: %d\nFecha: %s\nTipo: %s\nCantidad: %.2f",
                this.ID, this.fecha.format(formatter), this.tipo.name(), this.cantidad);
        return infoMovimiento;
    }
}
