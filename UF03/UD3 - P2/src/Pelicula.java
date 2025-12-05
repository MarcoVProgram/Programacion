import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Pelicula {
    //Variables Privadas
    private final String cod;
    private final String titulo;
    private final Genero genero;
    private final LocalDate fechaRegistro;
    private LocalDate fechaBaja;
    private LocalDateTime fechaAlquiler;
    private boolean isAlquilada;
    private String dniAlquilando;
    private static int codNumber = 0;
    private final long secondsSinceEpoch;

    //Constructor
    public Pelicula(String titulo, Genero genero) {
        this.titulo = titulo;
        this.genero = genero;
        this.cod = String.format("P-%04d", ++codNumber);
        this.secondsSinceEpoch = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        this.fechaRegistro = LocalDate.from(LocalDateTime.ofEpochSecond(secondsSinceEpoch, 0, ZoneOffset.UTC));
        this.isAlquilada = false;
        this.dniAlquilando = "NULL";
    }

    //Getter
    public String getCod() {
        return this.cod;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public LocalDate getFechaRegistro() {
        return this.fechaRegistro;
    }

    public LocalDate getFechaBaja() {
        return this.fechaBaja;
    }

    public LocalDateTime getFechaAlquiler() {
        return this.fechaAlquiler;
    }
    
    public long getSecondsSinceEpoch() {
        return this.secondsSinceEpoch;
    }

    public boolean isAlquilada() {
        return this.isAlquilada;
    }

    public String getDniAlquilando() {
        return this.dniAlquilando;
    }
    
    //Setter
    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public void setFechaAlquiler(LocalDateTime fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public void setAlquilada(boolean alquilada) {
        this.isAlquilada = alquilada;
    }

    public void setDniAlquilando(String dniAlquilando) {
        this.dniAlquilando = dniAlquilando;
    }

    //Metodos
    public String mostrarInfoPelicula() {
        String infoPelicula;
        String formattedRegistro = MyUtils.formatDate("dd/MM/yyyy", this.fechaRegistro);
        String formattedBaja = MyUtils.formatDate("dd/MM/yyyy", this.fechaBaja);
        String formattedAlquiler = MyUtils.formatDate("dd/MM/yyyy HH:mm:ss", this.fechaAlquiler);
        infoPelicula = String.format("Codigo: %S\nTitulo: %s\nGenero: %s\nFecha de Registro: %s\nFecha de Baja: %s\nUltima Vez Alquilada: %s\n" +
                        "Esta Alquilada: %b",
                this.cod, this.titulo, this.genero.name(),
                formattedRegistro, formattedBaja, formattedAlquiler, this.isAlquilada);
        return infoPelicula;
    }
}
