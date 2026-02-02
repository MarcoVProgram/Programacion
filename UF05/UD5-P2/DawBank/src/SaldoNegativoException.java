public class SaldoNegativoException extends Exception {

    private static final long serialVersionUID = 7908653899597671722L;
    private double cantidad;

    public SaldoNegativoException(double cantidad) {
        super("⚠️ AVISO! ⚠️ LA TRANSACCION NO SE PUDO REALIZAR, SALDO MENOR QUE -50 EUROS");
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SaldoNegativoException:\n");
        sb.append("- Cantidad: ").append(cantidad);
        return sb.toString();
    }
}
