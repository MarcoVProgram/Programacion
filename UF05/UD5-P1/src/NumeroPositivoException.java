public class NumeroPositivoException extends Exception {

    private static final long serialVersionUID = 1618907944978430762L;
    private int numero;

    public NumeroPositivoException(int numero) {
        super("Error, obtenido numero (" + numero + ") cuando numeros positivos no son deseados");
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "NumeroPositivoException{" +
                "numero=" + numero +
                "} " + super.toString();
    }
}
