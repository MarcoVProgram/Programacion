public class CuentaBancaria {

    //Variables Privadas
    private String IBAN;
    private String titular;
    private double saldo;

    //Arrays
    private Movimiento[] registro;
    private int numRegistros;
    private int registrosIniciales = 10;

    //Constructor
    CuentaBancaria(String IBAN, String titular) {
        this.IBAN = IBAN;
        this.titular = titular;
        this.saldo = 0;
        this.registro = new Movimiento[this.registrosIniciales];
        this.numRegistros = 0;
    }
    public CuentaBancaria(String[] datos) {
        this.IBAN = datos[0];
        this.titular = datos[1];
        this.saldo = 0;
        this.registro = new Movimiento[this.registrosIniciales];
        this.numRegistros = 0;
    }

    //Getter y Setter
    public String getIBAN() {
        return IBAN;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean hacerTransaccion(Movimiento transaccion) {
        boolean resultado = false;

        if (transaccion != null) {
            if (this.numRegistros < this.registro.length) {
                resultado = transaccionRealizada(transaccion);
            }
            else {
                ampliarRegistro();
                if (this.numRegistros < this.registro.length) {
                    resultado = transaccionRealizada(transaccion);
                }
            }
        }

        return resultado;
    }

    private boolean transaccionRealizada(Movimiento transaccion) {
        boolean resultado = false;
        switch (transaccion.getTipo()) {
            case "Ingreso":
                this.saldo += transaccion.getCantidad();
                this.registro[this.numRegistros] = transaccion;
                this.numRegistros++;
                resultado = true;
                break;
            case "Retirada":
                this.saldo -= transaccion.getCantidad();
                this.registro[this.numRegistros] = transaccion;
                this.numRegistros++;
                resultado = true;
                break;
            default:
                break;
        }
        return resultado;
    }

    private void ampliarRegistro() {
        Movimiento[] nuevoRegistro = new Movimiento[this.numRegistros*2];
        for (int i = 0; i < this.numRegistros; i++) {
            nuevoRegistro[i] = this.registro[i];
        }

        this.registro = nuevoRegistro;
    }

    public String mostrarHistorial() {
        String resultado = "No hay ninguna transaccion realizada.";
        if (this.numRegistros > 0) {
            resultado += "";
            for (int i = 0; i < this.numRegistros; i++) {
                if  (this.registro[i] != null) {
                    resultado += registro[i].mostrarInfoMovimiento() + "\n\n";
                }
            }
        }
        return resultado;
    }

    public String buscarTransaccion(int ID) {
        String resultado = "No se ha encontrado esa transaccion";
        for (int i = 0; i < this.numRegistros; i++) {
            if (this.registro[i].getID() == ID) {
                resultado = this.registro[i].mostrarInfoMovimiento();
            }
        }
        return resultado;
    }

    public String mostrarCuentaBancaria() {
        String infoCuentaBancaria;
        infoCuentaBancaria = String.format("IBAN: %S\nTitular: %s\nSaldo: %.2f", this.IBAN, this.titular, this.saldo);
        return infoCuentaBancaria;
    }
}
