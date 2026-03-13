import java.io.Serializable;
import java.time.LocalDate;

public abstract class Persona implements Serializable {

    private static final long serialVersionUID = -5074347564405688218L;

    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected String DNI;
    protected String direccion;

    public Persona(String nombre, LocalDate fechaNacimiento, String DNI, String direccion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.DNI = DNI;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDNI() {
        return DNI;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {

        String info;

        String fomattedNacimiento = MyUtils.formatDate("dd/MM/yyyy", this.fechaNacimiento);

        info = String.format("DNI: %S\t|\tNombre: %s\t|\tDireccion: %s\t|\tFecha de Nacimiento: %s",
                this.DNI, this.nombre, this.direccion, fomattedNacimiento);

        return info;
    }
}
