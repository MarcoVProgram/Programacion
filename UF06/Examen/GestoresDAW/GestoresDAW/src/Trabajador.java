import java.io.Serializable;
import java.time.LocalDate;

public class Trabajador extends Persona implements Serializable {

    private static final long serialVersionUID = -4054474737460419034L;

    protected String numeroSS;
    protected String email;
    protected double salario;
    protected Departamento dept;

    private static int codNumber = 0;

    public static boolean configCodGeneration(int config) {

        if (config > codNumber) {
            codNumber = config;
            return true;
        }

        return false;
    }
    public static int getConfigCod() {
        return codNumber;
    }

    public Trabajador(String nombre, LocalDate fecha_nacimiento, String DNI, String direccion, String email, double salario, Departamento dept) {

        super(nombre, fecha_nacimiento, DNI, direccion);

        codNumber++;
        this.numeroSS = String.format("%010d", codNumber);

        this.email = email;
        this.salario = salario;
        this.dept = dept;
    }

    public String getNumeroSS() {
        return numeroSS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Departamento getDept() {
        return dept;
    }

    public void setDept(Departamento dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {

        String info;
        String fomattedNacimiento = MyUtils.formatDate("dd/MM/yyyy", this.getFechaNacimiento());

        String herencia = String.format("DNI: %S\t|\tNombre: %s\t|\tDireccion: %s\t|\tFecha de Nacimiento: %s",
                this.DNI, this.nombre, this.direccion, fomattedNacimiento);

        info = String.format("Trabajador [ Numero Seguridad Social: %S\t|\t" + herencia +
                "\t|\tEmail: %s\t|\tSalario: %.2f\t|\tDepartamento: %s\t]", this.numeroSS, this.email, this.salario, this.dept.name());

        return info;
    }
}
