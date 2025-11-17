import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Biblioteca Primera = new Biblioteca("546555","Parque Jado Libros","Parque de Jado", "pjado@email.com","72252");

        Libro l1 = new Libro("La comunidad del anillo","JRR Tolkien","75225A","17/09/1944");
        Libro l2 = new Libro("Las dos torres","JRR Tolkien","75225B","31/10/1956");
        Libro l3 = new Libro("A new Hope","James Abrams","766566D","01/05/1970");
        Primera.insertarLibro(l1);
        Primera.insertarLibro(l2);
        Primera.insertarLibro(l3);

        if (Primera.eliminarLibro(l2)) {
            System.out.println("Libro eliminado\n");
        }
        l1.setNumPaginas(469);
        l1.setEdicion("3º edicion");

        //Insertar libro
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del libro:");
        String nombre = sc.nextLine();
        System.out.println("Introduce el autor del libro:");
        String autor = sc.nextLine();
        System.out.println("Introduce el ISBN del libro:");
        String isbn = sc.nextLine();
        System.out.println("Introduce la fecha del libro:");
        String fecha = sc.nextLine();
        Libro libro = new Libro(nombre,autor,isbn,fecha);

        Primera.insertarLibro(libro);

        System.out.println("Biblioteca Iniciada:\n" + Primera.infoBiblioteca() + "\n\nLibros:\n\n" + Primera.infoTodosLibros());
    }
}
