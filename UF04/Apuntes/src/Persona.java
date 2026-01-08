import java.util.Objects;

public class Persona {

    private String nombre;
    private String apellido;
    private int edad;
    private String dni;
    private String email;
    private String telefono;

    Persona(String nombre, String apellido, int edad, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.telefono = "";
        this.email = "";
    }

    Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = "";
        this.email = "";
        this.edad = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }
}
