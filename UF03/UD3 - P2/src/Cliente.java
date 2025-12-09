import java.time.LocalDate;

public class Cliente {
    //Variables Privadas
    private String DNI;
    private String nombre;
    private String numSocio;
    private String direccion;
    private LocalDate fechaNacimiento;
    private LocalDate fechaBaja;
    private static int codNumber = 0; //Contador para crear codigos

    //Arrays
    private int numInicialPeliculas = 5;
    private Pelicula[] peliculasAlquiladas;
    private int numPeliculas;

    //Constructor

    public Cliente(String DNI, String nombre, String direccion, LocalDate fechaNacimiento) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.codNumber++;
        this.numSocio = String.format("S-%04d", codNumber);
        this.numPeliculas = 0;
        this.peliculasAlquiladas = new Pelicula[numInicialPeliculas];
    }

    //Getters

    public String getDNI() {
        return this.DNI;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getNumSocio() {
        return this.numSocio;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public LocalDate getFechaBaja() {
        return this.fechaBaja;
    }

    public int getNumPeliculas() {
        return this.numPeliculas;
    }

    //Setter
    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    //Metodo

    //Metodo para mostrar cliente
    public String mostrarInfoCliente() {
        String infoCliente;

        //Creacion de Fechas si existen
        String fomattedNacimiento = MyUtils.formatDate("dd/MM/yyyy", this.fechaNacimiento);
        String formattedBaja = MyUtils.formatDate("dd/MM/yyyy HH:mm:ss", this.fechaBaja);

        //String Final
        infoCliente = String.format("DNI: %S\nNombre: %s\nNumero de Socio: %S\nDireccion: %s\nFecha de Nacimiento: %s\nFecha de dada de Baja: %s",
                this.DNI, this.nombre, this.numSocio, this.numSocio, fomattedNacimiento, formattedBaja);

        return infoCliente;
    }

    //Metodo para alquilar peliculas
    public boolean alquilerPeliculaHistorial(Pelicula pelicula) {
        boolean resultado = false;

        if (pelicula != null) {
            //Ampliar Lista si lleno
            if (this.numPeliculas >= this.peliculasAlquiladas.length) {
                ampliarListaDePeliculas();
            }

            //Hacer Registro
            this.peliculasAlquiladas[this.numPeliculas] = pelicula;
            this.numPeliculas++;
            resultado = true;
        }

        return resultado;
    }

    //Metodos Privados para crear peliculas
    private void ampliarListaDePeliculas() {
        Pelicula[] nuevaLista = new Pelicula[this.numPeliculas*2];

        for (int i = 0; i < this.numPeliculas; i++) {
            nuevaLista[i] = this.peliculasAlquiladas[i];
        }

        this.peliculasAlquiladas = nuevaLista;
    }

    //Comprobacion de que Pelicula hubiese sido Alquilada
    public boolean alquiloEstaPelicula(Pelicula pelicula) {
        boolean resultado = false;

        for (int i = 0; i < this.numPeliculas; i++) {
            if (this.peliculasAlquiladas[i].getCod().equalsIgnoreCase(pelicula.getCod())) {
                resultado = true;
                break;
            }
        }

        return resultado;
    }

    //Metodo de ver historial de peliculas (Desuso / Funcional)
    /*public String mostrarPeliculasQueFueronAlquiladas() {
        String infoTodasPeliculas = "No hay ningun registro de peliculas alquiladas";
        if (this.numPeliculas > 0) {
            infoTodasPeliculas = "";
            for (int i = 0; i < this.numPeliculas; i++) {
                if (this.peliculasAlquiladas[i] != null) {
                    infoTodasPeliculas += "\n" + peliculasAlquiladas[i].mostrarInfoPelicula() + "\n";
                }
            }
        }
        return infoTodasPeliculas;
    }*/
}
