public class GatoEdadNegativaException extends Exception {

    private static final long serialVersionUID = 6468963411896341872L;
    private int edad;

    public GatoEdadNegativaException(int edad) {
        super("La edad de un Gato introducida (" + edad + ") no puede ser negativa");
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "GatoEdadNegativaException{" +
                "edad=" + edad +
                "} " + super.toString();
    }
}
