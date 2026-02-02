import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Persona {

    //Atributos
    protected String nombre;
    protected String DNI;
    protected LocalDate fechaNacimiento;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Constructor
    public Persona(String nombre, String DNI, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.fechaNacimiento = fechaNacimiento;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.format(formatter);
    }

    //toString
    @Override
    public String toString() {
        String infoPersona;
        infoPersona = String.format("Nombre: %s\nDNI: %S\nFecha de Nacimiento: %S",
                this.nombre, this.DNI, this.fechaNacimiento.format(formatter));
        return infoPersona;
    }

    //Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(DNI, persona.DNI);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(DNI);
    }
}
