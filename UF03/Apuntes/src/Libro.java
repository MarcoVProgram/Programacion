public class Libro {

    //Datos encapsulados al usar 'private'.
    private String titulo;
    private String autor;
    private int numPaginas;
    private String ISBN;
    private String editora;
    private String fechaPublicacion;
    private String edicion;

    //Constructor, al escribir uno ya no existe el por defecto.
    public Libro(String titulo, String autor, String ISBN, String fechaPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.fechaPublicacion = fechaPublicacion;
        this.edicion = "1ª edicion";
        this.numPaginas = 1;
        this.editora = "Autopublicacion";
    }
    //Segundo constructor con todos los atributos
    public Libro(String titulo, String autor, String ISBN, String fechaPublicacion, String edicion, String editora, int numPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.fechaPublicacion = fechaPublicacion;
        this.edicion = edicion;
        this.setNumPaginas(numPaginas);
        this.editora = editora;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public String getEditora() {
        return this.editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getFechaPublicacion() {
        return this.fechaPublicacion;
    }

    public String getEdicion() {
        return this.edicion;
    }
    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public int getNumPaginas() {
        return this.numPaginas;
    }
    public void setNumPaginas(int numPaginas) {
        if (numPaginas > 0) {
            this.numPaginas = numPaginas;
        }
    }

    //Metodos
    public String mostrarLibro() {
        String infoLibro = "";
        infoLibro += "Titulo: " + this.titulo + "\n";
        infoLibro += "Autor: " + this.autor + "\n";
        infoLibro += "ISBN: " + this.ISBN + "\n";
        infoLibro += "Fecha de publicacion: " + this.fechaPublicacion + "\n";
        infoLibro += "Edicion: " + this.edicion + "\n";
        infoLibro += "Editora: " + this.editora + "\n";
        infoLibro += "Num Paginas: " + this.numPaginas + "\n";

        infoLibro = String.format("Titulo: %s\nAutor: %s\nISBN: %S\nFecha de Publicacion: %s\nEdicion: %s\nEditora: %s\nNum Paginas: %d",
                this.titulo, this.autor, this.ISBN, this.fechaPublicacion, this.edicion, this.editora, this.numPaginas);
        return infoLibro;
    }
}