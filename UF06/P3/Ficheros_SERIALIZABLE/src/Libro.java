//Importaciones de Java
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Clase Objeto
public class Libro implements Serializable {

    //Atributos
    private String ISBN;
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;

    private final transient String fechaFormato = "dd/MM/yyyy";
    private static final long serialVersionUID = 7215088754097564719L;

    //Constructor con Parametros
    //@param ISBN del libro
    //@param titulo del libro
    //@param autor del libro
    //@param fecha de publicacion del libro
    public Libro(String ISBN, String titulo, String autor, LocalDate fechaPublicacion) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    //Getter y Setter
    //Metodo 1
    //@return atributo ISBN de este objeto
    public String getISBN() {
        return this.ISBN;
    }

    //Metodo 2
    //@return atributo titulo de este objeto
    public String getTitulo() {
        return this.titulo;
    }

    //Metodo 3
    //@return atributo autor de este objeto
    public String getAutor() {
        return this.autor;
    }

    //Metodo 4
    //@return String de la fecha actual en dd/MM/yyyy
    public String getFechaPublicacion() {
        return DateTimeFormatter.ofPattern(fechaFormato).format(this.fechaPublicacion);
    }

    //Metodo 5
    //@return LocalDate del objeto
    public LocalDate getRawFechaPublicacion() {
        return this.fechaPublicacion;
    }

    //ToString
    //@return String con todos los parametros importantes
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Libro{");
        sb.append("ISBN='").append(ISBN).append('\'');
        sb.append(", titulo='").append(titulo).append('\'');
        sb.append(", autor='").append(autor).append('\'');
        sb.append(", fechaPublicacion=").append(DateTimeFormatter.ofPattern(fechaFormato).format(this.fechaPublicacion));
        sb.append('}');
        return sb.toString();
    }
}
