import java.time.LocalDate;
import java.time.LocalDateTime;
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

        //Si pelicula existe
        if (pelicula != null) {
            //Ampliar lista si llena
            if (this.numPeliculas >= this.peliculasRegistradas.length) {
                ampliarListaDePeliculas();
            }

            //Sumar Pelicula al registro
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

        //Solo si pelicula existe y no esta dada de baja
        if (pelicula != null && pelicula.getFechaBaja() == null) {

            //Comprobacion Seguridad que este en la lista
            for  (int i = 0; i < this.numPeliculas; i++) {
                if (this.peliculasRegistradas[i] == pelicula) {
                    //Comprobacion final, por precaucion
                    if (this.peliculasRegistradas[i].getCod().equalsIgnoreCase(pelicula.getCod())) {
                        index = i;
                        break;
                    }
                }
            }

            //Solo cuando estemos seguros se da de baja
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

        //Buscando peliculas a traves de Codigo de Pelicula
        for (int i = 0; i < this.numPeliculas; i++) {
            if (this.peliculasRegistradas[i].getCod().equalsIgnoreCase(codPelicula)) {
                index = i;
                break;
            }
        }

        //Solo si se encontro la pelicula
        if (index != -1) {
            pelicula = this.peliculasRegistradas[index];
        }

        return pelicula;
    }

    //Metodo para registrar o dar baja a cliente
    public boolean registrarCliente(Cliente cliente) {
        boolean resultado = false;

        //Se comprueba que exista
        if (cliente != null) {
            //Amplar lista si llena
            if (this.numClientes >= this.clientesRegistrados.length) {
                ampliarListaDeClientes();
            }
            //Comprobacion que no se repita mismo usuario por DNI y que sea de edad correcta
            if (comprobarSiClienteExisteRepetido(cliente) == 0 && clienteMayorEdad(cliente)) {
                //Regostro del Cliente a la lista
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

    //Cuantas veces se repite un cliente, por si es mas de una saberlo por cualquier motivo
    private int comprobarSiClienteExisteRepetido(Cliente cliente) {
        int repeticiones = 0;

        for (int i = 0; i < this.numClientes; i++) {
            //Comprbacion a traves de DNI
            if (cliente.getDNI().equals(this.clientesRegistrados[i].getDNI())) {
                repeticiones++;
            }
        }

        return repeticiones;
    }

    private boolean clienteMayorEdad(Cliente cliente) {
        boolean mayorEdad = false;
        /*if (Math.abs(cliente.getFechaNacimiento().atStartOfDay(ZoneId.systemDefault()).toEpochSecond() - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) > 567950400) {
            //Epoch fecha es (3600 * 24 * 365,25* 18) (18 anios) - 1 dia (86400)
            mayorEdad = true;
        }*/

        //Comprobacion si son mayores de 18
        if (Math.abs(LocalDate.now().getYear() - cliente.getFechaNacimiento().getYear()) > 18) {
            mayorEdad = true;
        }

        //Si estan en el umbral de edad, se comprueba con dia y mes
        if (Math.abs(LocalDate.now().getYear() - cliente.getFechaNacimiento().getYear()) == 18) {
            if (Math.abs(LocalDate.now().getDayOfYear() - cliente.getFechaNacimiento().getDayOfYear()) >= 0) {
                mayorEdad = true;
            }
        }

        return mayorEdad;
    }

    public boolean darBajaCliente(Cliente cliente) {
        boolean resultado = false;
        int index = -1;

        //Comprobacion que existe y no se haya dado de baja antes
        if (cliente != null && cliente.getFechaBaja() == null) {
            for  (int i = 0; i < this.numPeliculas; i++) {
                //Comprobacion Seguridad que este en nuestra lista
                if (this.clientesRegistrados[i] == cliente) {
                    //Comprobacion final, por precaucion
                    if (this.clientesRegistrados[i].getDNI().equalsIgnoreCase(cliente.getDNI())) {
                        index = i;
                        break;
                    }
                }
            }

            //Solo cuando estemos seguros se da de baja
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

        //Busqueda por DNI, se puede cambiar por Cod socio
        for (int i = 0; i < this.numClientes; i++) {
            if (this.clientesRegistrados[i].getDNI().equalsIgnoreCase(DNI)) {
                index = i;
                break;
            }
        }

        //Si se ha encontrado a Cliente
        if (index != -1) {
            cliente = this.clientesRegistrados[index];
        }

        return cliente;
    }

    //Mostrar registrados
    public String mostrarPeliculasRegistradas() {
        String infoTodasPeliculas = "No hay ningun registro de peliculas";

        //Accion solo si hay Peliculas Registradas
        if (this.numPeliculas > 0) {
            infoTodasPeliculas = "";

            for (int i = 0; i < this.numPeliculas; i++) {
                //Se imprimen solo las que existan, por seguridad
                if (this.peliculasRegistradas[i] != null) {
                    infoTodasPeliculas += "\n" + peliculasRegistradas[i].mostrarInfoPelicula() + "\n";
                }
            }
        }

        return infoTodasPeliculas;
    }

    public String mostrarClientesRegistrados() {
        String infoTodosClientes = "No hay ningun registro de clientes";

        //Accion solo si hay Clientes Registrados
        if (this.numClientes > 0) {
            infoTodosClientes = "";

            for (int i = 0; i < this.numClientes; i++) {
                //Seguridad que Cliente exista
                if (this.clientesRegistrados[i] != null) {
                    infoTodosClientes += "\n" + clientesRegistrados[i].mostrarInfoCliente() + "\n";

                    //Codigo de Advertencia si alguna vez existe un Cliente Repetido
                    if (comprobarSiClienteExisteRepetido(this.clientesRegistrados[i]) > 1) {
                        infoTodosClientes += "ADVERTENCIA - CLIENTE EXISTE REPETIDO";
                    }
                }
            }
        }

        return infoTodosClientes;
    }

    //Alquilar la Pelicula por Cliente
    public boolean alquilarPelicula(Cliente cliente, Pelicula pelicula) {
        boolean resultado = false;

        //Comprbacion Pelicula y Cliente existan, que pelicula no este alquilada, y que no esten de Baja
        if (pelicula != null && cliente != null && !pelicula.isAlquilada() && pelicula.getFechaBaja() == null && cliente.getFechaBaja() == null) {
            //Alquilacion de la Pelicula
            cliente.alquilerPeliculaHistorial(pelicula);
            pelicula.setAlquilada(true);
            pelicula.setFechaAlquiler(LocalDateTime.now());
            pelicula.setDniAlquilando(cliente.getDNI());
            resultado = true;
        }

        return resultado;
    }

    //Devolver la Pelicula de Cliente
    public String devolverPelicula(Cliente cliente, Pelicula pelicula) {
        //Mensaje si falla
        String resultado = "No se puede devolver la Pelicula";

        //Comprobacion que este alquilada y que existen los datos introducidos
        if (pelicula != null && cliente != null && pelicula.isAlquilada()) {
            //Comprobacion que este cliente particular alquilo la Pelicula
            if (cliente.alquiloEstaPelicula(pelicula) && pelicula.getDniAlquilando().equalsIgnoreCase(cliente.getDNI())) {
                //Devolucion de la Pelicula
                pelicula.setAlquilada(false);
                pelicula.setDniAlquilando("Sin Datos");
                resultado = "Pelicula " + pelicula.getTitulo() + " devuelta con Exito de Cliente " + cliente.getNombre();

                //Comprobacion de que este en el tiempo correcto, aviso si se pasa de 48 horas
                if (Math.abs(pelicula.getSecondsSinceEpoch() - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) > 172800) {
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

        //Fecha si existe
        String formattedDate = MyUtils.formatDate("dd/MM/yyyy", this.fechaAlta);

        //String final
        infoVideoDaw = String.format("CIF: %S\nDireccion: %s\nFecha de Alta: %s\nNumero de Peliculas: %d\nNumero de Clientes: %d",
                this.CIF, this.direccion, formattedDate, this.numPeliculas, this.numClientes);

        return infoVideoDaw;
    }
}
