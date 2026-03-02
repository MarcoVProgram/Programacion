import java.time.LocalDate;
import java.util.Objects;

public abstract class Persona {

    //Private variables
    protected String DNI;
    protected String nombre;
    protected String direccion;
    protected LocalDate fechaNacimiento;

    //Constructor
    public Persona(String DNI, String nombre, String direccion, LocalDate fechaNacimiento) {

        this.DNI = DNI;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    //Getter
    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    //Setter
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //ToString
    @Override
    public String toString() {
        String info;

        //Creacion de Fechas si existen
        String fomattedNacimiento = MyUtils.formatDate("dd/MM/yyyy", this.fechaNacimiento);

        //String Final
        info = String.format("DNI: %S\tNombre: %s\tDireccion: %s\tFecha de Nacimiento: %s",
                this.DNI, this.nombre, this.direccion, fomattedNacimiento);

        return info;
    }

    //Equals
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
