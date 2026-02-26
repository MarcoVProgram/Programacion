//Dependencias
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

//Clase Abstracta
public abstract class Articulo {

    //Variables Privadas
    protected String cod;
    protected String titulo;
    protected LocalDate fechaRegistro;
    private LocalDate fechaBaja;
    private LocalDateTime ultimaFechaAlquiler;
    private boolean isAlquilada;

    //Variables Privadas Globales
    private static int codNumber = 0; //Contador para crear codigos

    //Constructor
    public Articulo(String titulo) {

        this.cod = String.format("P-%04d", ++codNumber);
        this.titulo = titulo;
        this.fechaRegistro = LocalDate.now();
        this.isAlquilada = false;
    }

    //Configuracion del generador de codigos
    public static boolean configCodGeneration(int config) {

        if (config > codNumber) {
            codNumber = config;
            return true;
        }

        return false;
    }
    public static int getConfigCod() {
        return codNumber;
    }

    //Getter
    public String getCod() {
        return this.cod;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public LocalDate getFechaRegistro() {
        return this.fechaRegistro;
    }

    public LocalDate getFechaBaja() {
        return this.fechaBaja;
    }

    public LocalDateTime getUltimaFechaAlquiler() {
        return this.ultimaFechaAlquiler;
    }

    public boolean isAlquilada() {
        return this.isAlquilada;
    }

    //Setter
    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public void setUltimaFechaAlquiler(LocalDateTime ultimaFechaAlquiler) {
        this.ultimaFechaAlquiler = ultimaFechaAlquiler;
    }

    public void setAlquilada(boolean alquilada) {
        this.isAlquilada = alquilada;
    }

    //ToString
    //Completar en clases hija
    @Override
    public String toString() {
        String info;

        //Formato Fecha si existen
        String formattedRegistro = MyUtils.formatDate("dd/MM/yyyy", this.fechaRegistro);
        String formattedBaja = MyUtils.formatDate("dd/MM/yyyy", this.fechaBaja);
        String formattedAlquiler = MyUtils.formatDate("dd/MM/yyyy HH:mm:ss", this.ultimaFechaAlquiler);

        //String final
        info = String.format("[ Codigo: %S\tTitulo: %s\tFecha de Registro: %s\tFecha de Baja: %s\tUltima Vez Alquilada: %s\t" +
                        "Esta Alquilada: %b",
                this.cod, this.titulo, formattedRegistro, formattedBaja, formattedAlquiler, this.isAlquilada);

        return info;
    }

    //Equals
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Articulo articulo)) return false;
        return Objects.equals(cod, articulo.cod);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cod);
    }
}
