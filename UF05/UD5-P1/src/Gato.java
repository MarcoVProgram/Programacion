public class Gato {

    private String nombre;
    private int edad;

    public Gato(String nombre, int edad) throws GatoNombreMuyCortoException, GatoEdadNegativaException {
        if (nombre.length() < 3) {
            throw new GatoNombreMuyCortoException(nombre);
        }
        if (edad < 0) {
            throw new GatoEdadNegativaException(edad);
        }

        this.nombre = nombre;
        this.edad = edad;
    }
    public Gato(String nombre) throws GatoNombreMuyCortoException {
        if (nombre.length() < 3) {
            throw new GatoNombreMuyCortoException(nombre);
        }
        this.nombre = nombre;
        this.edad = 0;
    }
    public Gato(int edad) throws GatoEdadNegativaException {
        if (edad < 0) {
            throw new GatoEdadNegativaException(edad);
        }
        this.edad = edad;
        this.nombre = "Gato";
    }
    public Gato () {
        this.nombre = "Gato";
        this.edad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws GatoNombreMuyCortoException {
        if (nombre.length() < 3) {
            throw new GatoNombreMuyCortoException(nombre);
        }
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) throws GatoEdadNegativaException {
        if (edad < 0) {
            throw new GatoEdadNegativaException(edad);
        }
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Gato{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
