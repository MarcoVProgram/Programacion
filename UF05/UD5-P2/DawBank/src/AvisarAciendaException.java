import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AvisarAciendaException extends Exception {

    private static final long serialVersionUID = -6904428979564844926L;
    double cantidad;
    LocalDateTime fecha;

    public AvisarAciendaException(double cantidad) {
        super("⚠️ AVISO! ⚠️ NOTIFICAR A HACIENDA");
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AvisarAciendaException:\n");
        sb.append("- Cantidad: ").append(cantidad);
        sb.append("- Fecha: ").append(fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        return sb.toString();
    }
}
