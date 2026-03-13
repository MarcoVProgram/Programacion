import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;

public class Empresa implements Serializable {

    private static final long serialVersionUID = 9145084859148796132L;

    private String nombre;
    private String CIF;
    private LocalDate fechaFundacion;

    private Director director;
    private GerenteDep[] gerentes;
    private Map<String, Trabajador> empleados;

    public Empresa(String nombre, String CIF, LocalDate fechaFundacion) throws CIFIncorrectoException {

        if (!validacionCIF(CIF)) {
            throw new CIFIncorrectoException();
        }

        this.nombre = nombre;
        this.CIF = CIF;
        this.fechaFundacion = fechaFundacion;

        this.director = null;
        this.gerentes = new GerenteDep[3];
        this.empleados = new LinkedHashMap<>();

    }

    public static boolean validacionCIF(String CIF) throws CIFIncorrectoException {
        boolean valido = true;
        Matcher matcher;

        matcher = Patrones.CIF_FORM.matcher(CIF);

        if (!matcher.matches()) {
            throw new CIFIncorrectoException();
        }

        return valido;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public String getCIF() {
        return CIF;
    }

    public Map<String, Trabajador> getEmpleados() {
        return empleados;
    }

    public int getNumTrabajadores() {
        int sum = this.empleados.size();
        if (this.director != null) {
            sum++;
        }
        for (int i = 0; i < this.gerentes.length; i++) {
            if (this.gerentes[i] != null) {
                sum++;
            }
        }

        return sum;
    }

    @Override
    public String toString() {

        String info;
        String formattedCreacion = MyUtils.formatDate("dd/MM/yyyy", this.getFechaFundacion());

        info = String.format("Empresa [ Nombre: %s\t|\tCIF: %s\t|\tFecha de Fundacion: %s\t|\tNumero de Trabajadores: %d\t]",
                this.nombre, this.getCIF(), formattedCreacion, getNumTrabajadores());

        return info;
    }

    private boolean existeDNI(String DNI) {

        if (this.empleados.containsKey(DNI)) {
            return true;
        }

        if (this.director != null && this.director.getDNI().equalsIgnoreCase(DNI)) {
            return true;
        }

        for (GerenteDep gerente : this.gerentes) {
            if (gerente != null && gerente.getDNI().equalsIgnoreCase(DNI)) {
                return true;
            }
        }

        return false;
    }

    public Trabajador buscarTrabajador(String DNI) {

        if (this.empleados.containsKey(DNI)) {
            return this.empleados.get(DNI);
        }

        if (this.director != null && this.director.getDNI().equalsIgnoreCase(DNI)) {
            return this.director;
        }

        for (GerenteDep gerente : this.gerentes) {
            if (gerente != null && gerente.getDNI().equalsIgnoreCase(DNI)) {
                return gerente;
            }
        }

        return null;
    }

    public boolean registrarDirector(Director nuevoDirector) {
        boolean exito = false;

        if (nuevoDirector != null && director == null && !existeDNI(nuevoDirector.getDNI())) {
            exito = true;
            this.director = nuevoDirector;
        }

        return exito;
    }

    public boolean registrarTrabajador(Trabajador trabajador) {
        boolean exito = false;

        if (trabajador != null && !existeDNI(trabajador.getDNI())) {
            empleados.put(trabajador.getDNI(), trabajador);
            exito = true;

            actualizarDepts(trabajador);
        }

        return exito;
    }

    public boolean registrarGerente(GerenteDep gerente) {
        boolean exito = false;

        if (gerente != null && !existeDNI(gerente.getDNI())) {
            switch (gerente.getGerencia().name()) {

                case "INFORMATICA":
                    if (gerentes[0] == null) {

                        gerentes[0] = gerente;

                        for (Trabajador trabajador : empleados.values()) {

                            if (trabajador.getDept().name().equalsIgnoreCase(gerentes[0].getGerencia().name()))
                            {
                                gerentes[0].addTrabajador(trabajador);
                                break;
                            }
                        }

                        exito = true;
                    }
                    break;

                case "GESTION":
                    if (gerentes[1] == null) {

                        gerentes[1] = gerente;
                        exito = true;

                        for (Trabajador trabajador : empleados.values()) {

                            if (trabajador.getDept().name().equalsIgnoreCase(gerentes[1].getGerencia().name()))
                            {
                                gerentes[1].addTrabajador(trabajador);
                                break;
                            }
                        }
                    }
                    break;

                case "MARKETING":
                    if (gerentes[2] == null) {

                        gerentes[2] = gerente;
                        exito = true;

                        for (Trabajador trabajador : empleados.values()) {

                            if (trabajador.getDept().name().equalsIgnoreCase(gerentes[2].getGerencia().name()))
                            {
                                gerentes[2].addTrabajador(trabajador);
                                break;
                            }
                        }
                    }
                    break;

                default:
                    exito = false;
                    break;
            }
        }

        return exito;
    }

    private void actualizarDepts(Trabajador trabajador) {

        for (int i = 0; i < gerentes.length; i++) {
            if (gerentes[i] != null) {

                if (trabajador.getDept().name().equalsIgnoreCase(gerentes[i].getGerencia().name()))
                {
                    gerentes[i].addTrabajador(trabajador);
                    break;
                }
            }
        }
    }

    public boolean eliminarTrabajador(Trabajador trabajador) {
        boolean exito = false;

        if (trabajador != null) {

            if(empleados.containsKey(trabajador.getDNI())) {

                empleados.remove(trabajador.getDNI());
                exito = true;

                switch (trabajador.getDept().name()) {

                    case "INFORMATICA":
                        if (gerentes[0] != null) {
                            gerentes[0].quitarTrabajador(trabajador);
                        }
                        break;

                    case "GESTION":
                        if (gerentes[1] != null) {
                            gerentes[1].quitarTrabajador(trabajador);
                        }
                        break;

                    case "MARKETING":
                        if (gerentes[2] != null) {
                            gerentes[2].quitarTrabajador(trabajador);
                        }
                        break;

                    default:
                        break;
                }
            }

            if (this.director != null && this.director.getDNI().equalsIgnoreCase(trabajador.getDNI())) {
                this.director = null;
            }

            int index = -1;

            for (int i = 0; i < gerentes.length; i++) {

                if (gerentes[i] != null && gerentes[i].getDNI().equalsIgnoreCase(trabajador.getDNI())) {

                    index = i;
                }
            }

            if (index != -1) {

                gerentes[index] = null;
            }
        }

        return  exito;
    }

    public String mostrarInfoTrabajadoresOrdenados() {

        String info = "No Hay Trabajadores";

        if (!this.empleados.isEmpty()) {
            info = "";
            List<Trabajador> sorted = new LinkedList<>(this.empleados.values());



            if (this.director != null) {
                sorted.add(this.director);
            }

            for (int i = 0; i < gerentes.length; i++) {
                if (gerentes[i] != null) {
                    sorted.add(gerentes[i]);
                }
            }

            Collections.sort(sorted, new ComparadorPersonaDNI());
            for (Trabajador trabajador : sorted) {

                info += trabajador.toString() + "\n";
            }
        }

        return info;
    }

    public String mostrarInfoTrabajadores() {

        String info = "No Hay Trabajadores";

        if (!this.empleados.isEmpty()) {
            info = "Numero de Trabajadores: " + this.getNumTrabajadores() + "\n";



            if (this.director != null) {
                info += this.director.toString() + "\n\n";
            }

            for (int i = 0; i < gerentes.length; i++) {
                if (gerentes[i] != null) {
                    info += gerentes[i].toString() + "\n\n";

                    info += gerentes[i].mostrarInfoDepartamento() + "\n";
                }
            }
            info += "\nTrabajadores:\n";
            for (Trabajador trabajador : this.empleados.values()) {

                switch (trabajador.getDept().name()) {
                    case "INFORMATICA":
                        if  (gerentes[0] == null) {
                            info += trabajador.toString() + "\n";
                        }
                        break;
                    case "GESTION":
                        if  (gerentes[1] == null) {
                            info += trabajador.toString() + "\n";
                        }
                        break;
                    case "MARKETING":
                        if  (gerentes[2] == null) {
                            info += trabajador.toString() + "\n";
                        }
                        break;
                    default:
                        info += trabajador.toString() + "\n";

                }
            }
        }

        return info;
    }

    public String mostrarInfoGerencia(Gerencia gerencia) {

        String info = "No Hay Gerencia Definida";

        for (int i = 0; i < gerentes.length; i++) {

            if (gerentes[i] != null && gerentes[i].getGerencia() == gerencia) {
                info = gerentes[i].mostrarInfoDepartamento();
            }
        }

        return info;
    }
}
