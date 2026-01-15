import java.util.Objects;

public class Contacto {

    //Atributos
    private String nombre;
    private String telefono;
    private String correo;

    //Constructores
    public Contacto(String nombre, String telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    //Getter & Setter
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //ToString
    @Override
    public String toString() {
        return "Contacto { " +
                "nombre: " + nombre +
                " | telefono: " + telefono +
                " | correo: " + correo + " }";
    }

    //Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return Objects.equals(nombre, contacto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
