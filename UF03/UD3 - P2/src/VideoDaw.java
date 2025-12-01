import java.time.LocalDate;

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

    
}
