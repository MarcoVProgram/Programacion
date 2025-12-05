import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class VideoDaw {
    //Variables privadas
    private String CIF;
    private String direccion;
    private LocalDate fechaAlta;

    //Arrays
    private int numPeliculas;
    private int numPeliculasIniciales = 5;
    private Pelicula[] peliculasRegistradas;
    private int numClientes;
    private int numClientesIniciales = 5;
    private Cliente[] clientesRegistrados;

    //Constructor
    /*public VideoDaw(String CIF, String direccion, LocalDate fechaAlta) {
        this.CIF = CIF;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.numPeliculas = 0;
        this.numClientes = 0;
        peliculasRegistradas = new Pelicula[numPeliculasIniciales];
        clientesRegistrados = new Cliente[numClientesIniciales];
    }*/
    public VideoDaw(String CIF, String direccion) {
        this.CIF = CIF;
        this.direccion = direccion;
        this.fechaAlta = LocalDate.now();
        this.numPeliculas = 0;
        this.numClientes = 0;
        this.peliculasRegistradas = new Pelicula[numPeliculasIniciales];
        this.clientesRegistrados = new Cliente[numClientesIniciales];
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

    public int getNumPeliculas() {
        return this.numPeliculas;
    }

    public int getNumClientes() {
        return this.numClientes;
    }

    //Setters no hay

    //Metodo para registrar peliculas
    public boolean registrarPelicula(Pelicula pelicula) {
        boolean resultado = false;

        if (pelicula != null) {
            if (this.numPeliculas >= this.peliculasRegistradas.length) {
                ampliarListaDePeliculas();
            }
            this.peliculasRegistradas[this.numPeliculas] = pelicula;
            this.numPeliculas++;
            resultado = true;
        }

        return resultado;
    }

    //Metodos Privados para crear y devolver peliculas
    private void ampliarListaDePeliculas() {
        Pelicula[] nuevaLista = new Pelicula[this.numPeliculas *2];
        for (int i = 0; i < this.numPeliculas; i++) {
            nuevaLista[i] = this.peliculasRegistradas[i];
        }

        this.peliculasRegistradas = nuevaLista;
    }
    public boolean darBajaPelicula(Pelicula pelicula) {
        boolean resultado = false;
        int index = -1;

        if (pelicula != null && pelicula.getFechaBaja() == null) {
            for  (int i = 0; i < this.numPeliculas; i++) {
                if (this.peliculasRegistradas[i] == pelicula) {
                    if (this.peliculasRegistradas[i].getCod().equalsIgnoreCase(pelicula.getCod())) {
                        index = i;
                        break;
                    }
                }
            }
            if (index != -1) {
                this.peliculasRegistradas[index].setFechaBaja(LocalDate.now());
                resultado = true;
            }
        }
        return resultado;
    }

    //Buscar Pelicula
    public Pelicula buscarPelicula(String codPelicula) {
        Pelicula pelicula = null;
        int index = -1;
        for (int i = 0; i < this.numPeliculas; i++) {
            if (this.peliculasRegistradas[i].getCod().equalsIgnoreCase(codPelicula)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            pelicula = this.peliculasRegistradas[index];
        }
        return pelicula;
    }

    //Metodo para registrar o dar baja a cliente
    public boolean registrarCliente(Cliente cliente) {
        boolean resultado = false;

        if (cliente != null) {
            if (this.numClientes >= this.clientesRegistrados.length) {
                ampliarListaDeClientes();
            }
            if (comprobarSiClienteExisteRepetido(cliente) == 0 && clienteMayorEdad(cliente)) {
                this.clientesRegistrados[this.numClientes] = cliente;
                this.numClientes++;
                resultado = true;
            }
        }

        return resultado;
    }

    //Metodos Privados para crear clientes
    private void ampliarListaDeClientes() {
        Cliente[] nuevaLista = new Cliente[this.numClientes *2];
        for (int i = 0; i < this.numClientes; i++) {
            nuevaLista[i] = this.clientesRegistrados[i];
        }

        this.clientesRegistrados = nuevaLista;
    }

    private int comprobarSiClienteExisteRepetido(Cliente cliente) {
        int repeticiones = 0;
        for (int i = 0; i < this.numClientes; i++) {
            if (cliente.getDNI().equals(this.clientesRegistrados[i].getDNI())) {
                repeticiones++;
            }
        }
        return repeticiones;
    }

    private boolean clienteMayorEdad(Cliente cliente) {
        boolean mayorEdad = false;
        /*if ((cliente.getFechaNacimiento().atStartOfDay(ZoneId.systemDefault()).toEpochSecond() - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) > 567950400) {
            //Epoch fecha es (3600 * 24 * 365,25* 18) (18 anios) - 1 dia (86400)
            mayorEdad = true;
        }*/
        if ((cliente.getFechaNacimiento().getYear() - LocalDate.now().getYear()) < 18) {
            mayorEdad = true;
        }
        if ((cliente.getFechaNacimiento().getYear() - LocalDate.now().getYear()) == 18) {
            if ((cliente.getFechaNacimiento().getDayOfYear() - LocalDate.now().getDayOfYear()) >= 0) {
                mayorEdad = true;
            }
        }
        return mayorEdad;
    }

    public boolean darBajaCliente(Cliente cliente) {
        boolean resultado = false;
        int index = -1;

        if (cliente != null && cliente.getFechaBaja() == null) {
            for  (int i = 0; i < this.numPeliculas; i++) {
                if (this.clientesRegistrados[i] == cliente) {
                    if (this.clientesRegistrados[i].getDNI().equalsIgnoreCase(cliente.getDNI())) {
                        index = i;
                        break;
                    }
                }
            }
            if (index != -1) {
                this.clientesRegistrados[index].setFechaBaja(LocalDate.now());
                resultado = true;
            }
        }
        return resultado;
    }

    //Buscar Cliente
    public Cliente buscarCliente(String DNI) {
        Cliente cliente = null;
        int index = -1;
        for (int i = 0; i < this.numClientes; i++) {
            if (this.clientesRegistrados[i].getDNI().equalsIgnoreCase(DNI)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            cliente = this.clientesRegistrados[index];
        }
        return cliente;
    }

    //Mostrar registrados
    public String mostrarPeliculasRegistradas() {
        String infoTodasPeliculas = "No hay ningun registro de peliculas";
        if (this.numPeliculas > 0) {
            infoTodasPeliculas = "";
            for (int i = 0; i < this.numPeliculas; i++) {
                if (this.peliculasRegistradas[i] != null) {
                    infoTodasPeliculas += peliculasRegistradas[i].mostrarInfoPelicula() + "\n\n";
                }
            }
        }
        return infoTodasPeliculas;
    }

    public String mostrarClientesRegistrados() {
        String infoTodosClientes = "No hay ningun registro de clientes";
        if (this.numClientes > 0) {
            infoTodosClientes = "";
            for (int i = 0; i < this.numClientes; i++) {
                if (this.clientesRegistrados[i] != null) {
                    infoTodosClientes += clientesRegistrados[i].mostrarInfoCliente() + "\n\n";
                    if (comprobarSiClienteExisteRepetido(this.clientesRegistrados[i]) > 1) {
                        infoTodosClientes += "ADVERTENCIA - CLIENTE EXISTE REPETIDO";
                    }
                }
            }
        }
        return infoTodosClientes;
    }

    //Alquilar
    public boolean alquilarPelicula(Cliente cliente, Pelicula pelicula) {
        boolean resultado = false;
        if (pelicula != null && cliente != null && !pelicula.isAlquilada() && pelicula.getFechaBaja() == null && cliente.getFechaBaja() == null) {
            cliente.alquilarUnaPelicula(pelicula);
            pelicula.setAlquilada(true);
            pelicula.setFechaAlquiler(LocalDateTime.now());
            pelicula.setDniAlquilando(cliente.getDNI());
            resultado = true;
        }
        return resultado;
    }
    public String devolverPelicula(Cliente cliente, Pelicula pelicula) {
        String resultado = "No se puede devolver la Pelicula";
        if (pelicula != null && cliente != null && pelicula.isAlquilada()) {
            if (cliente.alquiloEstaPelicula(pelicula) && pelicula.getDniAlquilando().equalsIgnoreCase(cliente.getDNI())) {
                pelicula.setAlquilada(false);
                pelicula.setDniAlquilando("NULL");
                resultado = "Pelicula " + pelicula.getTitulo() + " devuelta con Exito de Cliente " + cliente.getNombre();
                if ((pelicula.getSecondsSinceEpoch() - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) > 172800) {
                    resultado += "\n==============================================================";
                    resultado += "\nADVERTENCIA - PELICULA DEVUELTA CON MAS DE 48 HORAS DE RETRASO";
                    resultado += "\n==============================================================";
                }
            }
        }
        return resultado;
    }

    //Mostrar info VideoClub
    public String mostrarInfoVideoDaw() {
        String infoVideoDaw;
        String formattedDate = MyUtils.formatDate("dd/MM/yyyy", this.fechaAlta);
        infoVideoDaw = String.format("CIF: %S\nDireccion: %s\nFecha de Alta: %s\nNumero de Peliculas: %d\nNumero de Clientes: %d",
                this.CIF, this.direccion, formattedDate, this.numPeliculas, this.numClientes);
        return infoVideoDaw;
    }
}
