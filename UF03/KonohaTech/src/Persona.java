import java.time.LocalDate;

public abstract class Persona {

    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected String DNI;
    protected String direccion;
    protected String numeroContacto;

    protected Persona(String nombre, String DNI, LocalDate fechaNacimiento, String direccion, String numeroDeContacto) {

        this.nombre = nombre;
        this.DNI = DNI; //8 numeros y 1 letra
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.numeroContacto = numeroDeContacto; //Tendra 9 digitos
    }

    //Getter
    public String getNombre() {
        return this.nombre;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public String getDNI() {
        return this.DNI;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getNumeroContacto() {
        return this.numeroContacto;
    }

    // toString

    @Override
    public String toString() {
        String fechaNacimientoFormato = MisUtilidades.fechaFormato("dd/MM/yyyy", this.fechaNacimiento);

        return "Nombre: " + this.nombre + "\n" +
                "Fecha de Nacimiento: " + fechaNacimientoFormato + "\n" +
                "DNI: " + this.DNI + "\n" +
                "Direccion: " + this.direccion + "\n" +
                "Nº de Contacto: " + this.numeroContacto;
    }
}
