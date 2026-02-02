public class NumeroNegativoException extends Exception {

    private static final long serialVersionUID = 9042416652381831997L;
    private int numero;

    public NumeroNegativoException(int numero) {
        super("Error, obtenido numero (" + numero + ") cuando numeros negativos no son deseados");
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "NumeroNegativoException{" +
                "numero=" + numero +
                "} " + super.toString();
    }
}
