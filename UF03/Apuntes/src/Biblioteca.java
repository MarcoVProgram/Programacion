public class Biblioteca {

    private String codigo;
    private String nombre;
    private String direcion;
    private String email;
    private String telefono;

    //Coleccion Array de Libros en la biblioteca
    private Libro[] libros;
    private int n;
    private int dimensionInicial = 100;

    //Constructor
    public Biblioteca(String codigo, String nombre, String direcion, String email, String telefono) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direcion = direcion;
        this.email = email;
        this.telefono = telefono;
        this.n = 0;
        this.libros = new Libro[dimensionInicial];
    }

    //Getters y setters
    public String getCodigo() {
        return this.codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDirecion() {
        return this.direcion;
    }
    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return this.telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean insertarLibro(Libro libro) {
        boolean resultado = false;

        //Toda la logica para insertar un libro
        if (libro != null) {
            if (this.n < this.libros.length) {
                this.libros[this.n] = libro;
                resultado = true;
                this.n++;
            }
        }
        else {
            //No tengo huecos suficientes
            this.ampliarDimensionBiblioteca();
            if (this.n < this.libros.length) {
                this.libros[this.n] = libro;
                resultado = true;
                this.n++;
            }
        }

        return resultado;
    }

    public boolean eliminarLibro(Libro libro) {
        boolean resultado = false;
        int index = -1;

        if (libro != null) {
            for  (int i = 0; i < this.n; i++) {
                if (this.libros[i] == libro) {
                    if (this.libros[i].getISBN().equalsIgnoreCase(libro.getISBN())) {
                        index = i;
                        break;
                    }
                }
            }
            if (index != -1) {
                this.libros[index] = null;
                //Opcion Marco, mover solo el ultimo
                this.libros[index] = this.libros[n-1];
                this.libros[n-1] = null;
                this.n--;
                resultado = true;
                //Opcion 2, recolocar el array
//                for (int i = index; i < this.libros.length-1; i++) {
//                    this.libros[i] = this.libros[i+1];
//                }
//                this.libros[this.libros.length-1] = null;
            }
        }

        return resultado;
    }

    private void ampliarDimensionBiblioteca() {
        Libro[] NuevosLibros = new Libro[this.libros.length+5];
        for (int i = 0; i < this.libros.length; i++) {
            NuevosLibros[i] = this.libros[i];
        }

        this.libros = NuevosLibros;
    }

    public String infoBiblioteca() {
        String infoBiblioteca;
        infoBiblioteca = String.format("Nombre: %s\nCodigo: %s\nDireccion: %s\nNumero de Libros: %d", this.nombre, this.codigo, this.direcion, this.n);

        StringBuilder iL = new StringBuilder();
        //Esto se puede ver despues

        return infoBiblioteca;
    }

    public String infoTodosLibros() {
        String infoTodosLibros = "No hay libros en la biblioteca";
        if (this.n > 0) {
            infoTodosLibros = "";
            for (int i = 0; i < this.n; i++) {
                if (this.libros[i] != null) {
                    infoTodosLibros += libros[i].mostrarLibro() + "\n\n";
                }
            }
        }
        return infoTodosLibros;
    }

    public Libro findLibro(String ISBN) {
        Libro libro = null;
        int index = -1;
        for (int i = 0; i < this.n; i++) {
            if (this.libros[i].getISBN().equalsIgnoreCase(ISBN)) {
                index = -1;
                break;
            }
        }
        if (index != -1) {
            libro = this.libros[index];
        }
        return libro;
    }
}
