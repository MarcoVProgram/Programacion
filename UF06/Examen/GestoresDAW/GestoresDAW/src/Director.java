import java.io.Serializable;
import java.time.LocalDate;

public class Director extends Trabajador implements Serializable {

    private static final long serialVersionUID = -9167524753081037231L;

    private String numeroTelefono;
    private String cocheEmpresa;

    public Director(String nombre, LocalDate fecha_nacimiento, String DNI, String direccion, String email, double salario, Departamento dept, String numeroTelefono, String cocheEmpresa) {

        super(nombre, fecha_nacimiento, DNI, direccion, email, salario, dept);

        this.numeroTelefono = numeroTelefono;
        this.cocheEmpresa = cocheEmpresa;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCocheEmpresa() {
        return cocheEmpresa;
    }

    public void setCocheEmpresa(String cocheEmpresa) {
        this.cocheEmpresa = cocheEmpresa;
    }

    @Override
    public String toString() {

        String info;
        String fomattedNacimiento = MyUtils.formatDate("dd/MM/yyyy", this.getFechaNacimiento());

        String herencia = String.format("DNI: %S\t|\tNombre: %s\t|\tDireccion: %s\t|\tFecha de Nacimiento: %s",
                this.DNI, this.nombre, this.direccion, fomattedNacimiento);

        info = String.format("Director [ Numero Seguridad Social: %S\t|\t" + herencia +
                "\t|\tEmail: %s\t|\tSalario: %.2f\t|\tDepartamento: %s\t|\tNumero de Telefono: %S\t|\tCoche Empresa: %s\t]",
                this.numeroSS, this.email, this.salario, this.dept.name(), this.numeroTelefono, this.cocheEmpresa);

        return info;
    }
}
