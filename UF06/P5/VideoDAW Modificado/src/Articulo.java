import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase abstracta que representa un artículo disponible para alquiler en el sistema.
 * Implementa {@link Serializable} para permitir su persistencia.
 *
 * <p>Define los atributos y comportamientos comunes a todos los tipos de artículo,
 * como el código identificador generado automáticamente, el título, las fechas de
 * registro y baja, y el estado de alquiler. Las subclases deben completar
 * {@link #toString()} con su información específica.</p>
 *
 * @author Marco
 * @version 1.0
 */
public abstract class Articulo implements Serializable {

    private static final long serialVersionUID = 6846255428542505065L;

    /**
     * Código identificador único del artículo, generado automáticamente con formato {@code P-XXXX}.
     */
    protected String cod;

    /**
     * Título del artículo.
     */
    protected String titulo;

    /**
     * Fecha en la que el artículo fue registrado en el sistema.
     */
    protected LocalDate fechaRegistro;

    /**
     * Fecha en la que el artículo causó baja en el sistema. {@code null} si sigue activo.
     */
    private LocalDate fechaBaja;

    /**
     * Fecha y hora del último alquiler registrado para este artículo.
     * {@code null} si nunca ha sido alquilado.
     */
    private LocalDateTime ultimaFechaAlquiler;

    /**
     * Indica si el artículo se encuentra actualmente alquilado.
     */
    private boolean isAlquilada;

    /**
     * Contador estático utilizado para la generación automática y secuencial de códigos de artículo.
     */
    private static int codNumber = 0;

    /**
     * Construye un nuevo {@code Articulo} con el título proporcionado.
     *
     * <p>El código se genera automáticamente de forma secuencial con el formato {@code P-XXXX}.
     * La fecha de registro se establece a la fecha actual y el artículo se marca como
     * no alquilado.</p>
     *
     * @param titulo  Título del artículo.
     */
    public Articulo(String titulo) {

        this.cod = String.format("P-%04d", ++codNumber);
        this.titulo = titulo;
        this.fechaRegistro = LocalDate.now();
        this.isAlquilada = false;
    }

    /**
     * Configura el valor del contador interno de generación de códigos de artículo.
     *
     * <p>Solo se aplica el cambio si el valor proporcionado es estrictamente mayor que el
     * valor actual del contador, evitando la generación de códigos duplicados.</p>
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
     * Devuelve el valor actual del contador de generación de códigos de artículo.
     *
     * @return El valor actual de {@code codNumber}.
     */
    public static int getConfigCod() {
        return codNumber;
    }

    /**
     * Devuelve el código identificador del artículo.
     *
     * @return El código en formato {@code P-XXXX}.
     */
    public String getCod() {
        return this.cod;
    }

    /**
     * Devuelve el título del artículo.
     *
     * @return El título del artículo.
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Devuelve la fecha en la que el artículo fue registrado en el sistema.
     *
     * @return La {@link LocalDate} de registro.
     */
    public LocalDate getFechaRegistro() {
        return this.fechaRegistro;
    }

    /**
     * Devuelve la fecha de baja del artículo.
     *
     * @return La {@link LocalDate} de baja, o {@code null} si el artículo sigue activo.
     */
    public LocalDate getFechaBaja() {
        return this.fechaBaja;
    }

    /**
     * Devuelve la fecha y hora del último alquiler registrado para este artículo.
     *
     * @return El {@link LocalDateTime} del último alquiler, o {@code null} si nunca fue alquilado.
     */
    public LocalDateTime getUltimaFechaAlquiler() {
        return this.ultimaFechaAlquiler;
    }

    /**
     * Indica si el artículo se encuentra actualmente alquilado.
     *
     * @return {@code true} si el artículo está alquilado; {@code false} en caso contrario.
     */
    public boolean isAlquilada() {
        return this.isAlquilada;
    }

    /**
     * Establece la fecha de baja del artículo en el sistema.
     *
     * @param fechaBaja  La {@link LocalDate} en que el artículo causa baja.
     */
    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * Establece la fecha y hora del último alquiler de este artículo.
     *
     * @param ultimaFechaAlquiler  El {@link LocalDateTime} del último alquiler.
     */
    public void setUltimaFechaAlquiler(LocalDateTime ultimaFechaAlquiler) {
        this.ultimaFechaAlquiler = ultimaFechaAlquiler;
    }

    /**
     * Establece el estado de alquiler del artículo.
     *
     * @param alquilada  {@code true} para marcar el artículo como alquilado;
     *                   {@code false} para marcarlo como disponible.
     */
    public void setAlquilada(boolean alquilada) {
        this.isAlquilada = alquilada;
    }

    /**
     * Devuelve una representación en cadena con la información base del artículo, incluyendo
     * código, título, fecha de registro, fecha de baja, última fecha de alquiler y estado actual.
     *
     * <p><b>Nota:</b> Este método debe ser extendido por las subclases para incluir
     * los atributos específicos de cada tipo de artículo.</p>
     *
     * @return Un {@link String} con la información común del artículo.
     */
    @Override
    public String toString() {
        String info;

        String formattedRegistro = MyUtils.formatDate("dd/MM/yyyy", this.fechaRegistro);
        String formattedBaja = MyUtils.formatDate("dd/MM/yyyy", this.fechaBaja);
        String formattedAlquiler = MyUtils.formatDate("dd/MM/yyyy HH:mm:ss", this.ultimaFechaAlquiler);

        info = String.format("Codigo: %S\t|\tTitulo: %s\t|\tFecha de Registro: %s\t|\tFecha de Baja: %s\t|\tUltima Vez Alquilada: %s" +
                        "\t|\tEsta Alquilada: %b",
                this.cod, this.titulo, formattedRegistro, formattedBaja, formattedAlquiler, this.isAlquilada);

        return info;
    }
}