import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {

    //Variables Privadas
    private String IBAN;
    private Cliente titular;
    private double saldo;

    //Constantes
    private final Transaccion ingreso = Transaccion.INGRESO,
            retirada = Transaccion.RETIRADA;

    //Colecciones
    private List<Movimiento> movimientos;

    //Constructor
    public CuentaBancaria(String IBAN, Cliente titular) {
        this.IBAN = IBAN;
        this.titular = titular;
        this.saldo = 0;
        this.movimientos = new ArrayList<>();
    }

    //Getter y Setter
    public String getIBAN() {
        return this.IBAN;
    }

    public Cliente getTitular() {
        return this.titular;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void hacerTransaccion(Movimiento transaccion) throws CuentaException,SaldoNegativoException,AvisarAciendaException {

        if (transaccion == null) {
            throw new CuentaException("⚠️ AVISO! ⚠️ La transaccion insertada no puede ser null");
        }
        if (transaccion.getTipoTransaccion() == ingreso) {
            this.saldo += transaccion.getCantidad();
        } else if (transaccion.getTipoTransaccion() == retirada) {
            if (this.saldo - transaccion.getCantidad() < -50) {
                throw new SaldoNegativoException(transaccion.getCantidad());
            }
            this.saldo -= transaccion.getCantidad();
        }
        movimientos.add(transaccion);

        if (transaccion.getCantidad() > 3000) {
            throw new AvisarAciendaException(transaccion.getCantidad());
        }
    }

    public String mostrarHistorial() {
        String resultado = "No hay ninguna transaccion realizada";
        if (!this.movimientos.isEmpty()) {
            resultado = "";
            for (Movimiento movimiento : this.movimientos) {
                resultado += "\n" + movimiento.toString() + "\n";
            }
        }
        return resultado;
    }

    public String buscarTransaccionesPorID(int ID) {
        String resultado = "No se ha encontrado esa transaccion";
        for (Movimiento movimiento : this.movimientos) {
            if (movimiento.getID() == ID) {
                resultado = movimiento.toString();
            }
        }
        return resultado;
    }

    public String buscarTodasTransaccionesPorCantidad(double cantidad) {
        String resultado = "No se ha encontrado esa transaccion";
        if (!this.movimientos.isEmpty()) {
            for (Movimiento movimiento : this.movimientos) {
                if (movimiento.getCantidad() == cantidad) {
                    if (resultado.equals("No se ha encontrado esa transaccion")) {
                        resultado = "";
                    }
                    resultado += movimiento.toString() + "\n";
                }
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        String infoCuentaBancaria;
        infoCuentaBancaria = String.format("IBAN: %S\nTitular: %s\nSaldo: %.2f", this.IBAN, this.titular.toString(), this.saldo);
        return infoCuentaBancaria;
    }
}
