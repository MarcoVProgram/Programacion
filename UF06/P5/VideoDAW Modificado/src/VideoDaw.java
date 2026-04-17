import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

/**
 * Representa el videoclub, actuando como clase principal del sistema.
 * Implementa {@link Serializable} para permitir su persistencia.
 *
 * <p>Gestiona el registro de {@link Articulo artículos} y {@link Cliente clientes},
 * así como las operaciones de alquiler y devolución. Solo acepta clientes mayores
 * de edad y artículos no duplicados.</p>
 *
 * @author Marco
 * @version 1.0
 * @see Articulo
 * @see Cliente
 */
public class VideoDaw implements Serializable {

    private static final long serialVersionUID = -6173850164554653075L;

    /**
     * Código de Identificación Fiscal del videoclub.
     */
    private String CIF;

    /**
     * Dirección física del videoclub.
     */
    private String direccion;

    /**
     * Fecha en la que el videoclub fue dado de alta en el sistema.
     */
    private LocalDate fechaAlta;

    /**
     * Lista de todos los artículos registrados en el videoclub,
     * incluyendo los que han causado baja.
     */
    private List<Articulo> articulosRegistrados;

    /**
     * Lista de todos los clientes registrados en el videoclub,
     * incluyendo los que han causado baja.
     */
    private List<Cliente> clientesRegistrados;

    /**
     * Construye un nuevo {@code VideoDaw} con el CIF y dirección proporcionados.
     *
     * <p>La fecha de alta se establece a la fecha actual. Las listas de artículos
     * y clientes se inicializan vacías.</p>
     *
     * @param CIF        Código de Identificación Fiscal del videoclub.
     * @param direccion  Dirección física del videoclub.
     */
    public VideoDaw(String CIF, String direccion) {
        this.CIF = CIF;
        this.direccion = direccion;
        this.fechaAlta = LocalDate.now();

        this.articulosRegistrados = new LinkedList<>();
        this.clientesRegistrados = new LinkedList<>();
    }

    /**
     * Devuelve el CIF del videoclub.
     *
     * @return El CIF como {@link String}.
     */
    public String getCIF() {
        return this.CIF;
    }

    /**
     * Devuelve la dirección física del videoclub.
     *
     * @return La dirección como {@link String}.
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     * Devuelve la fecha de alta del videoclub en el sistema.
     *
     * @return La {@link LocalDate} de alta.
     */
    public LocalDate getFechaAlta() {
        return this.fechaAlta;
    }

    /**
     * Registra un nuevo artículo en el videoclub.
     *
     * <p>El registro falla si el artículo es {@code null} o ya existe en el sistema
     * un artículo activo con el mismo código.</p>
     *
     * @param articulo  El {@link Articulo} a registrar.
     * @return {@code true} si el artículo fue registrado con éxito;
     *         {@code false} en caso contrario.
     */
    public boolean registrarArticulo(Articulo articulo) {
        boolean registrable = false;

        if (articulo != null && !articuloExiste(articulo.getCod())) {

            this.articulosRegistrados.add(articulo);
            registrable = true;
        }

        return registrable;
    }

    /**
     * Da de baja un artículo activo del videoclub, estableciendo su fecha de baja a la actual.
     *
     * <p>La operación falla si el artículo es {@code null}, ya tiene fecha de baja asignada,
     * o no se encuentra registrado en el sistema.</p>
     *
     * @param articulo  El {@link Articulo} a dar de baja.
     * @return {@code true} si la baja se realizó con éxito; {@code false} en caso contrario.
     */
    public boolean darBajaArticulo(Articulo articulo) {
        boolean resultado = false;
        int index;

        if (!this.articulosRegistrados.isEmpty() && articulo != null && articulo.getFechaBaja() == null) {

            index = this.articulosRegistrados.indexOf(articulo);

            if (index != -1) {
                this.articulosRegistrados.get(index).setFechaBaja(LocalDate.now());
                resultado = true;
            }
        }

        return resultado;
    }

    /**
     * Busca y devuelve un artículo activo a partir de su código identificador.
     *
     * <p>Solo se consideran artículos sin fecha de baja. La comparación del código
     * no distingue entre mayúsculas y minúsculas.</p>
     *
     * @param codArticulo  El código del artículo a buscar.
     * @return El {@link Articulo} encontrado, o {@code null} si no existe ninguno activo
     *         con ese código.
     */
    public Articulo buscarArticulo(String codArticulo) {
        Articulo articulo = null;

        if (!this.articulosRegistrados.isEmpty()) {

            for (Articulo a : this.articulosRegistrados) {
                if (a.getCod().equalsIgnoreCase(codArticulo) && a.getFechaBaja() == null) {

                    articulo = a;
                    break;
                }
            }
        }

        return articulo;
    }

    /**
     * Comprueba si existe un artículo activo con el código proporcionado.
     *
     * <p>Solo se consideran artículos sin fecha de baja. La comparación no distingue
     * entre mayúsculas y minúsculas.</p>
     *
     * @param codArticulo  El código del artículo a comprobar.
     * @return {@code true} si existe un artículo activo con ese código;
     *         {@code false} en caso contrario.
     */
    public boolean articuloExiste(String codArticulo) {
        boolean existe = false;

        if (!this.articulosRegistrados.isEmpty()) {

            for (Articulo a : this.articulosRegistrados) {
                if (a.getCod().equalsIgnoreCase(codArticulo) && a.getFechaBaja() == null) {

                    existe = true;
                }
            }
        }

        return existe;
    }

    /**
     * Registra un nuevo cliente en el videoclub.
     *
     * <p>El registro falla si el cliente es {@code null}, es menor de edad,
     * o ya existe en el sistema un cliente activo con el mismo DNI.</p>
     *
     * @param cliente  El {@link Cliente} a registrar.
     * @return {@code true} si el cliente fue registrado con éxito;
     *         {@code false} en caso contrario.
     */
    public boolean registrarCliente(Cliente cliente) {

        if (cliente != null && clienteMayorEdad(cliente) && !clienteExiste(cliente.getDNI())) {

            this.clientesRegistrados.add(cliente);
            return true;
        }

        return false;
    }

    /**
     * Comprueba si un cliente es mayor de edad, calculando la diferencia entre
     * su fecha de nacimiento y la fecha actual.
     *
     * @param cliente  El {@link Cliente} a comprobar.
     * @return {@code true} si el cliente tiene 18 años o más; {@code false} en caso contrario.
     */
    private boolean clienteMayorEdad(Cliente cliente) {
        boolean mayorEdad = false;

        mayorEdad = Period.between(cliente.getFechaNacimiento(), LocalDate.now()).getYears() >= 18;

        return mayorEdad;
    }

    /**
     * Da de baja un cliente activo del videoclub, estableciendo su fecha de baja a la actual.
     *
     * <p>La operación falla si el cliente es {@code null}, ya tiene fecha de baja asignada,
     * o no se encuentra registrado en el sistema.</p>
     *
     * @param cliente  El {@link Cliente} a dar de baja.
     * @return {@code true} si la baja se realizó con éxito; {@code false} en caso contrario.
     */
    public boolean darBajaCliente(Cliente cliente) {
        boolean resultado = false;
        int index;

        if (!this.clientesRegistrados.isEmpty() && cliente != null && cliente.getFechaBaja() == null) {

            index = this.clientesRegistrados.indexOf(cliente);

            if (index != -1) {
                this.clientesRegistrados.get(index).setFechaBaja(LocalDate.now());
                resultado = true;
            }
        }

        return resultado;
    }

    /**
     * Busca y devuelve un cliente activo a partir de su DNI.
     *
     * <p>Solo se consideran clientes sin fecha de baja. La comparación del DNI
     * no distingue entre mayúsculas y minúsculas.</p>
     *
     * @param DNI  El DNI del cliente a buscar.
     * @return El {@link Cliente} encontrado, o {@code null} si no existe ninguno activo
     *         con ese DNI.
     */
    public Cliente buscarCliente(String DNI) {
        Cliente cliente = null;

        if (!this.clientesRegistrados.isEmpty()) {

            for (Cliente c : this.clientesRegistrados) {
                if (c.getDNI().equalsIgnoreCase(DNI) && c.getFechaBaja() == null) {

                    cliente = c;
                    break;
                }
            }
        }

        return cliente;
    }

    /**
     * Comprueba si existe un cliente activo con el DNI proporcionado.
     *
     * <p>Solo se consideran clientes sin fecha de baja. La comparación no distingue
     * entre mayúsculas y minúsculas.</p>
     *
     * @param DNI  El DNI del cliente a comprobar.
     * @return {@code true} si existe un cliente activo con ese DNI;
     *         {@code false} en caso contrario.
     */
    public boolean clienteExiste(String DNI) {
        boolean existe = false;

        if (!this.clientesRegistrados.isEmpty()) {

            for (Cliente c : this.clientesRegistrados) {
                if (c.getDNI().equalsIgnoreCase(DNI) && c.getFechaBaja() == null) {

                    existe = true;
                }
            }
        }

        return existe;
    }

    /**
     * Genera una cadena de texto con la información de todos los artículos registrados,
     * incluyendo los dados de baja.
     *
     * @return Un {@link String} con la información de cada artículo, separados por saltos
     *         de línea; o un mensaje informativo si no hay ninguno registrado.
     */
    public String mostrarArticulosRegistrados() {
        String infoArti = "No hay ningun registro de articulos";

        if (!this.articulosRegistrados.isEmpty()) {
            infoArti = "";

            for (Articulo a : this.articulosRegistrados) {

                infoArti += a.toString() + "\n";
            }
        }

        return infoArti;
    }

    /**
     * Genera una cadena de texto con la información de los artículos registrados
     * que pertenezcan a un tipo concreto, identificado por el nombre simple de su clase.
     *
     * <p>La comparación del nombre de clase no distingue entre mayúsculas y minúsculas.</p>
     *
     * @param simpleClassName  El nombre simple de la clase del tipo de artículo a filtrar
     *                         (p. ej. {@code "Pelicula"}).
     * @return Un {@link String} con la información de los artículos del tipo indicado,
     *         separados por saltos de línea; o un mensaje informativo si no hay ninguno.
     */
    public String mostrarArticulosRegistrados(String simpleClassName) {
        String infoClase = "No hay ningun registro de " + simpleClassName;

        if (!this.articulosRegistrados.isEmpty()) {
            infoClase = "";

            for (Articulo a : this.articulosRegistrados) {

                if (a.getClass().getSimpleName().equalsIgnoreCase(simpleClassName)) {
                    infoClase += a.toString() + "\n";
                }
            }

            if (infoClase.equals("")) {
                infoClase = "No hay ningun registro de " + simpleClassName;
            }
        }

        return infoClase;
    }

    /**
     * Genera una cadena de texto con la información de todos los clientes registrados,
     * incluyendo los dados de baja.
     *
     * @return Un {@link String} con la información de cada cliente, separados por saltos
     *         de línea; o un mensaje informativo si no hay ninguno registrado.
     */
    public String mostrarClientesRegistrados() {
        String infoCliente = "No hay ningun registro de clientes";

        if (!this.clientesRegistrados.isEmpty()) {
            infoCliente = "";

            for (Cliente c : this.clientesRegistrados) {

                infoCliente += c.toString() + "\n";
            }
        }

        return infoCliente;
    }

    /**
     * Registra el alquiler de un artículo por parte de un cliente.
     *
     * <p>La operación requiere que tanto el artículo como el cliente sean no nulos,
     * estén activos (sin fecha de baja), estén registrados en el videoclub, y que
     * el artículo no se encuentre ya alquilado.</p>
     *
     * @param cliente   El {@link Cliente} que realiza el alquiler.
     * @param articulo  El {@link Articulo} a alquilar.
     * @return {@code true} si el alquiler se realizó con éxito; {@code false} en caso contrario.
     */
    public boolean alquilarArticulo(Cliente cliente, Articulo articulo) {
        boolean resultado = false;

        if (articulo != null && cliente != null && !articulo.isAlquilada()
                && articulo.getFechaBaja() == null && cliente.getFechaBaja() == null
                && this.articulosRegistrados.contains(articulo) && this.clientesRegistrados.contains(cliente)) {

            cliente.alquilarArticulo(articulo);
            articulo.setAlquilada(true);
            articulo.setUltimaFechaAlquiler(LocalDateTime.now());
            resultado = true;
        }

        return resultado;
    }

    /**
     * Registra la devolución de un artículo alquilado por un cliente.
     *
     * <p>Verifica que el artículo esté efectivamente alquilado, que pertenezca a la lista
     * de pendientes del cliente y que ambos estén registrados en el videoclub. Si la
     * devolución supera las 48 horas desde el momento del alquiler, se añade un aviso
     * de retraso al resultado.</p>
     *
     * @param cliente   El {@link Cliente} que realiza la devolución.
     * @param articulo  El {@link Articulo} a devolver.
     * @return Un {@link String} con el resultado de la operación, incluyendo un aviso
     *         si la devolución se realizó con más de 48 horas de retraso.
     */
    public String devolverArticulo(Cliente cliente, Articulo articulo) {
        String resultado = "No se puede devolver el Articulo";

        if (articulo != null && cliente != null && articulo.isAlquilada()
                && this.articulosRegistrados.contains(articulo) && this.clientesRegistrados.contains(cliente)) {

            String tipo = articulo.getClass().getSimpleName();

            if (cliente.getArticulosPendientes().contains(articulo)) {

                cliente.devolverArticulo(articulo);
                articulo.setAlquilada(false);

                resultado = tipo + " " + articulo.getTitulo() + " devuelta con exito de Cliente " + cliente.getNombre();

                Long retraso = Math.abs(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - articulo.getUltimaFechaAlquiler().toEpochSecond(ZoneOffset.UTC));

                if (retraso > 172800) {

                    resultado +=    "\n==============================================================" +
                                    "\nADVERTENCIA - ARTICULO DEVUELTO CON MAS DE 48 HORAS DE RETRASO" +
                                    "\n==============================================================" ;
                }
            }
        }

        return resultado;
    }

    /**
     * Devuelve el número de artículos actualmente activos en el videoclub,
     * es decir, aquellos sin fecha de baja asignada.
     *
     * @return El número de artículos activos registrados.
     */
    public int numeroArticulosEnVideoClub() {
        int resultado = 0;

        for (Articulo a : this.articulosRegistrados) {
            if (a.getFechaBaja() == null) {
                resultado++;
            }
        }

        return resultado;
    }

    /**
     * Devuelve el número de clientes actualmente activos en el videoclub,
     * es decir, aquellos sin fecha de baja asignada.
     *
     * @return El número de clientes activos registrados.
     */
    public int numeroClientesEnVideoClub() {
        int resultado = 0;

        for (Cliente c : this.clientesRegistrados) {
            if (c.getFechaBaja() == null) {
                resultado++;
            }
        }

        return resultado;
    }

    /**
     * Devuelve una representación en cadena del videoclub con su información principal,
     * incluyendo CIF, dirección, fecha de alta, número de artículos activos y número
     * de clientes activos.
     *
     * @return Un {@link String} con la información del videoclub.
     */
    @Override
    public String toString() {
        String infoVideoDaw;

        String formattedDate = MyUtils.formatDate("dd/MM/yyyy", this.fechaAlta);

        infoVideoDaw = String.format("CIF: %S\nDireccion: %s\nFecha de Alta: %s\nNumero de Articulos Totales Registrados: %d\nNumero de Clientes Totales Registrados: %d",
                this.CIF, this.direccion, formattedDate, this.numeroArticulosEnVideoClub(), this.numeroClientesEnVideoClub());

        return infoVideoDaw;
    }
}