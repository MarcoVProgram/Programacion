public class GatoNombreMuyCortoException extends Exception {

    private static final long serialVersionUID = -4814729920259609666L;
    private String nombre;

    public GatoNombreMuyCortoException(String nombre) {
        super("El nombre de un Gato introducida (" + nombre + ") no puede ser menor a 3 caracteres");
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "GatoNombreMuyCortoException{" +
                "nombre='" + nombre + '\'' +
                "} " + super.toString();
    }
}
