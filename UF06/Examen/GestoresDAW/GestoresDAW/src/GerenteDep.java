import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class GerenteDep extends Trabajador implements Serializable {

    private static final long serialVersionUID = -7512024584836996831L;

    private Gerencia gerencia;
    private Map<String,Trabajador> trabajadores;

    public GerenteDep(String nombre, LocalDate fecha_nacimiento, String DNI, String direccion, String email, double salario, Departamento dept, Gerencia gerencia) {

        super(nombre, fecha_nacimiento, DNI, direccion, email, salario, dept);
        this.gerencia = gerencia;

        this.trabajadores = new LinkedHashMap<>();
    }

    public Gerencia getGerencia() {
        return gerencia;
    }

    public int getNumTrabajadoresDep() {
        return trabajadores.size();
    }

    @Override
    public String toString() {

        String info;
        String fomattedNacimiento = MyUtils.formatDate("dd/MM/yyyy", this.getFechaNacimiento());

        String herencia = String.format("DNI: %S\t|\tNombre: %s\t|\tDireccion: %s\t|\tFecha de Nacimiento: %s",
                this.DNI, this.nombre, this.direccion, fomattedNacimiento);

        info = String.format("Gerente de Departamento [ Numero Seguridad Social: %S\t|\t" + herencia +
                "\t|\tEmail: %s\t|\tSalario: %.2f\t|\tDepartamento: %s\t|\tGerencia: %S\t|\tNumero de Trabajadores: %d\t]",
                this.numeroSS, this.email, this.salario, this.dept.name(), this.gerencia.name(), this.trabajadores.size());

        return info;
    }

    public String mostrarInfoDepartamento() {

        String info = "INFO DEPARTAMENTO:\n" + this.toString();
        double salarioFinal = this.salario;

        if (!this.trabajadores.isEmpty()) {

            for (Trabajador trabajador : this.trabajadores.values()) {

                info += "\n" + trabajador.toString();
                salarioFinal = salarioFinal + trabajador.getSalario();
            }
        }

        info += "\nCOSTE FINAL DE SALARIOS DEL DEPARTAMENTO: " + salarioFinal;

        return info;
    }

    public boolean addTrabajador(Trabajador trabajador) {
        boolean resultado = false;

        if (trabajador != null && !this.trabajadores.containsKey(trabajador.getDNI())) {
            trabajadores.put(trabajador.getDNI(), trabajador);
            resultado = true;
        }

        return resultado;
    }

    public boolean quitarTrabajador(Trabajador trabajador) {
        boolean resultado = false;

        if  (trabajador != null && this.trabajadores.containsKey(trabajador.getDNI())) {
            trabajadores.remove(trabajador.getDNI());
            resultado = true;
        }

        return resultado;
    }
}
