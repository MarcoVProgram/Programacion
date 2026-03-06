import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

public class VideoDaw implements Serializable {

    private static final long serialVersionUID = 6995874545231776936L;
    //Variables privadas
    private String CIF;
    private String direccion;
    private LocalDate fechaAlta;

    //Arrays
    private List<Articulo> articulosRegistrados;
    private List<Cliente> clientesRegistrados;

    //Constructor
    public VideoDaw(String CIF, String direccion) {
        this.CIF = CIF;
        this.direccion = direccion;
        this.fechaAlta = LocalDate.now();

        this.articulosRegistrados = new LinkedList<>();
        this.clientesRegistrados = new LinkedList<>();
    }

    //Getter
    public String getCIF() {
        return this.CIF;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public LocalDate getFechaAlta() {
        return this.fechaAlta;
    }


    //Setters no hay

    //Metodo para registrar peliculas
    public boolean registrarArticulo(Articulo articulo) {
        boolean registrable = false;

        if (articulo != null && !articuloExiste(articulo.getCod())) {

            this.articulosRegistrados.add(articulo);
            registrable = true;
        }

        return registrable;
    }

    //Metodo actualizar fecha baja de articulo ahora
    public boolean darBajaArticulo(Articulo articulo) {
        boolean resultado = false;
        int index;

        if (!this.articulosRegistrados.isEmpty() && articulo != null && articulo.getFechaBaja() == null) {

            index = this.articulosRegistrados.indexOf(articulo);//Devuelve -1 si no se encuentra

            if (index != -1) {
                this.articulosRegistrados.get(index).setFechaBaja(LocalDate.now());
                resultado = true;
            }
        }

        return resultado;
    }

    //Metodo Buscar Articulo
    public Articulo buscarArticulo(String codArticulo) {
        Articulo articulo = null;

        if (!this.articulosRegistrados.isEmpty()) {

            for (Articulo a : this.articulosRegistrados) {
                if (a.getCod().equalsIgnoreCase(codArticulo)) {

                    articulo = a;
                }
            }
        }

        return articulo;
    }

    //Metodo Articulo Existe
    public boolean articuloExiste(String codArticulo) {
        boolean existe = false;

        if (!this.articulosRegistrados.isEmpty()) {

            for (Articulo a : this.articulosRegistrados) {
                if (a.getCod().equalsIgnoreCase(codArticulo)) {

                    existe = true;
                }
            }
        }

        return existe;
    }

    //Metodo para registrar o dar baja a cliente
    public boolean registrarCliente(Cliente cliente) {

        if (cliente != null && clienteMayorEdad(cliente) && !clienteExiste(cliente.getDNI())) {

            this.clientesRegistrados.add(cliente);
            return true;
        }

        return false;
    }

    //Metodo comprobacion mayor de edad
    private boolean clienteMayorEdad(Cliente cliente) {
        boolean mayorEdad = false;

        //Caso mayores de 18
        if (Math.abs(LocalDate.now().getYear() - cliente.getFechaNacimiento().getYear()) > 18) {
            mayorEdad = true;
        }

        //Metodo comprobacion del Dia
        if (Math.abs(LocalDate.now().getYear() - cliente.getFechaNacimiento().getYear()) == 18) {
            if (Math.abs(LocalDate.now().getDayOfYear() - cliente.getFechaNacimiento().getDayOfYear()) >= 0) {
                mayorEdad = true;
            }
        }

        return mayorEdad;
    }

    //Metodo actualizar decha de baja del Cliente
    public boolean darBajaCliente(Cliente cliente) {
        boolean resultado = false;
        int index;

        if (!this.clientesRegistrados.isEmpty() && cliente != null && cliente.getFechaBaja() == null) {

            index = this.articulosRegistrados.indexOf(cliente);//Devuelve -1 si no se encuentra

            if (index != -1) {
                this.articulosRegistrados.get(index).setFechaBaja(LocalDate.now());
                resultado = true;
            }
        }

        return resultado;
    }

    //Metodo Buscar Cliente
    public Cliente buscarCliente(String DNI) {
        Cliente cliente = null;

        if (!this.clientesRegistrados.isEmpty()) {

            for (Cliente c : this.clientesRegistrados) {
                if (c.getDNI().equalsIgnoreCase(DNI)) {

                    cliente = c;
                }
            }
        }

        return cliente;
    }

    //Metodo Verificar si Cliente existe
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


    //Metodos Mostrar registrados

    //Version articulos
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

    //Version articulos tipo especificado
    public String mostrarArticulosRegistrados(String simpleCLassName) {
        String infoClase = "No hay ningun registro de " + simpleCLassName;

        if (!this.articulosRegistrados.isEmpty()) {
            infoClase = "";

            for (Articulo a : this.articulosRegistrados) {

                if (a.getClass().getSimpleName().equalsIgnoreCase(simpleCLassName)) {
                    infoClase += a.toString() + "\n";
                }
            }

            if (infoClase.equals("")) {
                infoClase = "No hay ningun registro de " + simpleCLassName;
            }
        }

        return infoClase;
    }

    //Version clientes
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

    //Alquilar Articulo por Cliente
    public boolean alquilarArticulo(Cliente cliente, Articulo articulo) {
        boolean resultado = false;

        //Comprobacion no null, no estar alquilado, no estar de bajam y pertenecer al videoclub
        if (articulo != null && cliente != null && !articulo.isAlquilada()
                && articulo.getFechaBaja() == null && cliente.getFechaBaja() == null
                && this.articulosRegistrados.contains(articulo) && this.clientesRegistrados.contains(cliente)) {

            cliente.alquilarArticulo(articulo);
            articulo.setAlquilada(true);//Despues de alquilarArticulo, o hay problemas
            resultado = true;
        }

        return resultado;
    }

    //Devolver Articulo de Cliente
    public String devolverArticulo(Cliente cliente, Articulo articulo) {
        String resultado = "No se puede devolver el Articulo";
        String tipo = articulo.getClass().getSimpleName();

        //Comprobacion que este alquilada y que existen los datos introducidos
        if (articulo != null && cliente != null && !articulo.isAlquilada()
                && this.articulosRegistrados.contains(articulo) && this.clientesRegistrados.contains(cliente)) {

            if (cliente.getArticulosPendientes().contains(articulo)) {

                cliente.devolverArticulo(articulo);
                articulo.setAlquilada(false);

                resultado = tipo + articulo.getTitulo() + " devuelta con exito de Cliente " + cliente.getNombre();

                //Comprobacion de que este en el tiempo correcto, aviso si se pasa de 48 horas
                Long retraso = Math.abs(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - articulo.getUltimaFechaAlquiler().toEpochSecond(ZoneOffset.UTC));

                //172800 segundos = 48 horas
                if (retraso > 172800) {

                    resultado +=    "\n==============================================================" +
                                    "\nADVERTENCIA - ARTICULO DEVUELTA CON MAS DE 48 HORAS DE RETRASO" +
                                    "\n==============================================================" ;
                }
            }
        }

        return resultado;
    }

    //Metodo ToString
    //Mostrar info VideoClub
    @Override
    public String toString() {
        String infoVideoDaw;

        //Fecha si existe
        String formattedDate = MyUtils.formatDate("dd/MM/yyyy", this.fechaAlta);

        //String final
        infoVideoDaw = String.format("CIF: %S\nDireccion: %s\nFecha de Alta: %s\nNumero de Peliculas: %d\nNumero de Clientes: %d",
                this.CIF, this.direccion, formattedDate, this.articulosRegistrados.size(), this.clientesRegistrados.size());

        return infoVideoDaw;
    }
}
