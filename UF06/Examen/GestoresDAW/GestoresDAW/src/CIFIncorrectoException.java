public class CIFIncorrectoException extends Exception {

    private static final long serialVersionUID = -6420405881965918431L;

    public CIFIncorrectoException() {
        super("El CIF introducido es incorrecto, debe ser una letra mayuscula seguida de 10 digitos");
    }
}

