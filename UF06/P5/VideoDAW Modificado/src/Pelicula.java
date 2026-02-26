import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pelicula extends Articulo {
    //Variables Privadas

    private LocalDate fechaBaja;
    private LocalDateTime fechaAlquiler;
    private boolean isAlquilada;
    private String dniAlquilando;
    private static int codNumber = 0; //Contador para crear codigos

    //Constructor


    //Getter
    public String getCod() {
        return this.cod;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public GeneroPeli getGenero() {
        return this.generoPelicula;
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

        //Formato Fecha si existen
        String formattedRegistro = MyUtils.formatDate("dd/MM/yyyy", this.fechaRegistro);
        String formattedBaja = MyUtils.formatDate("dd/MM/yyyy", this.fechaBaja);
        String formattedAlquiler = MyUtils.formatDate("dd/MM/yyyy HH:mm:ss", this.fechaAlquiler);

        //String final
        infoPelicula = String.format("Codigo: %S\nTitulo: %s\nGenero: %s\nFecha de Registro: %s\nFecha de Baja: %s\nUltima Vez Alquilada: %s\n" +
                        "Esta Alquilada: %b",
                this.cod, this.titulo, this.generoPelicula.name(),
                formattedRegistro, formattedBaja, formattedAlquiler, this.isAlquilada);

        return infoPelicula;
    }
}
