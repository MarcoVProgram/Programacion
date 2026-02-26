import java.time.LocalDate;
import java.util.LinkedList;

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
    private LinkedList<Articulo> articulos;

    //Constructor

    public Cliente(String DNI, String nombre, String direccion, LocalDate fechaNacimiento) {

        this.DNI = DNI;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;

        this.numSocio = String.format("S-%04d", ++codNumber);
        this.articulos = new LinkedList<>();
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

    //Setter
    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    //Metodos

    //Metodo para mostrar cliente
    @Override
    public String toString() {
        String infoCliente;

        //Creacion de Fechas si existen
        String fomattedNacimiento = MyUtils.formatDate("dd/MM/yyyy", this.fechaNacimiento);
        String formattedBaja = MyUtils.formatDate("dd/MM/yyyy HH:mm:ss", this.fechaBaja);

        //String Final
        infoCliente = String.format("[ DNI: %S\tNombre: %s\tNumero de Socio: %S\tDireccion: %s\tFecha de Nacimiento: %s\tFecha de dada de Baja: %s ]",
                this.DNI, this.nombre, this.numSocio, this.numSocio, fomattedNacimiento, formattedBaja);

        return infoCliente;
    }

    //Metodo para alquilar peliculas
    public boolean alquilarArticulo(Articulo a) {

        boolean resultado = false;

        if (a != null) {

            if (a instanceof Pelicula) {}
            this.articulos.add(a);
            resultado = true;
        }

        return resultado;
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
