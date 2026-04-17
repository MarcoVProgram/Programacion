import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Representa a un cliente del sistema, el cual extiende de {@link Persona} e implementa
 * {@link Serializable} para permitir su persistencia.
 *
 * <p>Cada cliente dispone de un número de socio generado automáticamente, un historial
 * completo de artículos alquilados y una lista de artículos pendientes de devolución.</p>
 *
 * @author Marco
 * @version 1.0
 * @see Persona
 * @see Articulo
 */
public class Cliente extends Persona implements Serializable {
    private static final long serialVersionUID = -4310468795650616251L;

    /**
     * Número de socio único del cliente, generado automáticamente con formato {@code S-XXXX}.
     */
    private String numSocio;

    /**
     * Fecha en la que el cliente causó baja en el sistema. {@code null} si el cliente sigue activo.
     */
    private LocalDate fechaBaja;

    /**
     * Contador estático utilizado para la generación automática y secuencial de números de socio.
     */
    private static int codNumber = 0;

    /**
     * Historial completo de todos los artículos que el cliente ha alquilado alguna vez.
     */
    private LinkedList<Articulo> historialAlquilacion;

    /**
     * Lista de artículos actualmente alquilados por el cliente y pendientes de devolución.
     */
    private LinkedList<Articulo> articulosPendientes;

    /**
     * Construye un nuevo {@code Cliente} con los datos personales proporcionados.
     *
     * <p>El número de socio se genera automáticamente de forma secuencial con el formato
     * {@code S-XXXX}. Las listas de historial y artículos pendientes se inicializan vacías.</p>
     *
     * @param DNI              DNI del cliente.
     * @param nombre           Nombre completo del cliente.
     * @param direccion        Dirección del cliente.
     * @param fechaNacimiento  Fecha de nacimiento del cliente.
     */
    public Cliente(String DNI, String nombre, String direccion, LocalDate fechaNacimiento) {
        super(DNI, nombre, direccion, fechaNacimiento);

        this.historialAlquilacion = new LinkedList<>();
        this.articulosPendientes = new LinkedList<>();
        this.numSocio = String.format("S-%04d", ++codNumber);
    }

    /**
     * Configura el valor del contador interno de generación de números de socio.
     *
     * <p>Solo se aplica el cambio si el valor proporcionado es estrictamente mayor que el
     * valor actual del contador, evitando así la generación de números de socio duplicados.</p>
     *
     * @param config  Nuevo valor a establecer para el contador.
     * @return {@code true} si el contador fue actualizado correctamente;
     *         {@code false} si el valor proporcionado no supera al actual.
     */
    public static boolean configCodGeneration(int config) {

        if (config > codNumber) {
            codNumber = config;
            return true;
        }

        return false;
    }

    /**
     * Devuelve el valor actual del contador de generación de números de socio.
     *
     * @return El valor actual de {@code codNumber}.
     */
    public static int getConfigCod() {
        return codNumber;
    }

    /**
     * Devuelve el número de socio del cliente.
     *
     * @return El número de socio en formato {@code S-XXXX}.
     */
    public String getNumSocio() {
        return this.numSocio;
    }

    /**
     * Devuelve la fecha de baja del cliente.
     *
     * @return La {@link LocalDate} de baja, o {@code null} si el cliente está activo.
     */
    public LocalDate getFechaBaja() {
        return this.fechaBaja;
    }

    /**
     * Devuelve una copia del historial completo de artículos alquilados por el cliente.
     *
     * @return Una {@link List} con todos los {@link Articulo} alquilados alguna vez.
     */
    public List<Articulo> getHistorialAlquilacion() {
        return (List<Articulo>) this.historialAlquilacion.clone();
    }

    /**
     * Devuelve una copia de la lista de artículos actualmente pendientes de devolución.
     *
     * @return Una {@link List} con los {@link Articulo} pendientes de devolver.
     */
    public List<Articulo> getArticulosPendientes() {
        return (List<Articulo>) this.articulosPendientes.clone();
    }

    /**
     * Establece la fecha de baja del cliente en el sistema.
     *
     * @param fechaBaja  La {@link LocalDate} en que el cliente causa baja.
     */
    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * Devuelve una representación en cadena del cliente con toda su información relevante,
     * incluyendo número de socio, datos heredados de {@link Persona}, fecha de baja,
     * total de artículos alquilados e artículos pendientes de devolución.
     *
     * @return Un {@link String} con la información completa del cliente.
     */
    @Override
    public String toString() {
        String info;

        String formattedBaja = MyUtils.formatDate("dd/MM/yyyy", this.fechaBaja);

        info = String.format("Cliente --> [ Numero de Socio: %S\t|\t" + super.toString() + "\t|\tFecha de Baja: %s\t|\tCantidad total de Articulos Alquilados: %d\t|\tArticulos Alquilados Actualmente: %d ]",
                this.numSocio, formattedBaja, historialAlquilacion.size(), articulosPendientes.size());

        return info;
    }

    /**
     * Registra el alquiler de un artículo para este cliente.
     *
     * <p>El artículo se añade tanto a la lista de pendientes como al historial.
     * La operación falla si el artículo es {@code null} o ya se encuentra alquilado.</p>
     *
     * @param a  El {@link Articulo} que se desea alquilar.
     * @return {@code true} si el alquiler se realizó con éxito; {@code false} en caso contrario.
     */
    public boolean alquilarArticulo(Articulo a) {

        if (a != null && !a.isAlquilada()) {

            this.articulosPendientes.add(a);
            this.historialAlquilacion.add(a);
            return true;
        }

        return false;
    }

    /**
     * Registra la devolución de un artículo previamente alquilado por este cliente.
     *
     * <p>El artículo se elimina de la lista de pendientes. La operación falla si el artículo
     * es {@code null} o no figura en la lista de pendientes del cliente.</p>
     *
     * @param a  El {@link Articulo} que se desea devolver.
     * @return {@code true} si la devolución se realizó con éxito; {@code false} en caso contrario.
     */
    public boolean devolverArticulo(Articulo a) {

        if (a != null && this.articulosPendientes.contains(a)) {

            return this.articulosPendientes.remove(a);
        }

        return false;
    }

    /**
     * Genera una cadena de texto con los artículos actualmente pendientes de devolución.
     *
     * @return Un {@link String} con la información de cada artículo pendiente, separados por
     *         saltos de línea; o un mensaje informativo si no hay ninguno pendiente.
     */
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

    /**
     * Genera una cadena de texto con el historial completo de artículos alquilados por el cliente.
     *
     * @return Un {@link String} con la información de cada artículo del historial, separados por
     *         saltos de línea; o un mensaje informativo si el historial está vacío.
     */
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

    /**
     * Compara este cliente con otro objeto para determinar si son iguales.
     *
     * <p>Dos clientes se consideran iguales si son iguales según {@link Persona#equals(Object)}
     * y además comparten el mismo número de socio.</p>
     *
     * @param o  El objeto con el que comparar.
     * @return {@code true} si los objetos son iguales; {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(numSocio, cliente.numSocio);
    }

    /**
     * Devuelve el código hash del cliente, calculado a partir del hash de {@link Persona}
     * y del número de socio.
     *
     * @return El código hash del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numSocio);
    }
}