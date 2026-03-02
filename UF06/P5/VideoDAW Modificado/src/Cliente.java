import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Cliente extends Persona implements Serializable {
    private static final long serialVersionUID = 146639047836650119L;

    //Variables Privadas
    private String numSocio;
    private LocalDate fechaBaja;

    //Contador
    private static int codNumber = 0; //Contador para crear codigos

    //Arrays
    private LinkedList<Articulo> historialAlquilacion;
    private LinkedList<Articulo> articulosPendientes;

    //Constructor
    public Cliente(String DNI, String nombre, String direccion, LocalDate fechaNacimiento) {
        super(DNI, nombre, direccion, fechaNacimiento);

        this.historialAlquilacion = new LinkedList<>();
        this.articulosPendientes = new LinkedList<>();
        this.numSocio = String.format("P-%04d", ++codNumber);
    }

    //Configuracion del generador de codigos
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

    //Getter
    public String getNumSocio() {
        return this.numSocio;
    }

    public LocalDate getFechaBaja() {
        return this.fechaBaja;
    }

    public List<Articulo> getHistorialAlquilacion() {
        return this.historialAlquilacion;
    }

    public List<Articulo> getArticulosPendientes() {
        return this.articulosPendientes;
    }

    //Setter
    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    //Metodos

    //Metodo para mostrar cliente
    @Override
    public String toString() {
        String info;

        //Creacion de Fechas si existen
        String formattedBaja = MyUtils.formatDate("dd/MM/yyyy HH:mm:ss", this.fechaBaja);

        //String Final
        info = String.format("Cliente --> [ Numero de Socio: %S\t" + super.toString() + "\tCantidad total de Articulos Alquilados: %d ]",
                this.numSocio, formattedBaja, historialAlquilacion.size());

        return info;
    }

    //Metodo para alquilar articulos
    public boolean alquilarArticulo(Articulo a) {

        if (a != null && !a.isAlquilada()) {

            this.articulosPendientes.add(a);
            this.historialAlquilacion.add(a);
            return true;
        }

        return false;
    }

    //Metodo para devolver articulos
    public boolean devolverArticulo(Articulo a) {

        if (a != null && this.getArticulosPendientes().contains(a)) {

            return this.articulosPendientes.remove(a);
        }

        return false;
    }

    //Metodo mostrar articulos actualmente pendientes de devolucion
    public String mostrarArticulosAlquilados() {
        String alquilados = "El cliente no tiene pendiente ninguna entrega";

        if (!this.articulosPendientes.isEmpty()) {
            alquilados = "";

            for (Articulo a : this.articulosPendientes) {
                alquilados += a.toString() + "\n";
            }
        }

        return alquilados;
    }

    //Metodo mostrar historial completo
    public String mostrarHistorialArticulosAlquilados() {
        String alquilados = "El cliente no ha alquilado ningun articulo";

        if (!this.historialAlquilacion.isEmpty()) {
            alquilados = "";

            for (Articulo a : this.historialAlquilacion) {
                alquilados += a.toString() + "\n";
            }
        }

        return alquilados;
    }

    //Equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(numSocio, cliente.numSocio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numSocio);
    }
}
