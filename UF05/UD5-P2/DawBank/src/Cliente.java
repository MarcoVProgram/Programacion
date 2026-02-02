import java.time.LocalDate;
import java.util.Objects;

public class Cliente extends Persona{

    //Atributos
    String telefono;
    String email;
    String direccion;

    //Constructor
    public Cliente(String nombre, String DNI, LocalDate fechaNacimiento, String telefono, String email, String direccion) {
        super(nombre, DNI, fechaNacimiento);
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    //Getter & Setter
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //toString
    @Override
    public String toString() {
        String infoCliente = super.toString();
        infoCliente += String.format("\ntelefono: %S\nemail: %s\ndireccion: %s", telefono, email, direccion);
        return infoCliente;
    }
}
