import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio5 {
    /*
    Implementa una clase Gato con los atributos nombre y edad, un constructor con parámetros,
    los getters y setters, además de un método imprimir() para mostrar los datos de un gato. El
    nombre de un gato debe tener al menos 3 caracteres y la edad no puede ser negativa. Por ello,
    tanto en el constructor como en los setters, deberás comprobar que los valores sean válidos y
    lanzar una ‘Exception’ si no lo son. Luego, haz una clase principal con main para hacer pruebas:
    instancia varios objetos Gato y utiliza sus setters, probando distintos valores (algunos válidos y
    otros incorrectos). Maneja las excepciones.
     */
    public static void main(String[] args) {

        System.out.println("Ejercicio 5");

        Gato miGato1 = null, miGato2 = null, miGato3 = null, miGato4 = null;
        String nombreGato1 = "Loco",
                nombreGato2 = "Mo",
                nomreGato3 = "Luci",
                nomreGato4 = "Po";
        int edadGato1 = 1,
                edadGato2 = 2,
                edadGato3 = -3,
                edadGato4 = -4;

        //Comprobacion de errores especficos
        System.out.println();
        try {
            miGato1 = new Gato(nombreGato1, edadGato1);
            System.out.println(miGato1.toString());
        } catch (GatoNombreMuyCortoException e) {
            System.out.println("Se ha producido un error al introducir el nombre del Gato");
            System.out.println(e.getMessage());
            try {
                System.out.println("Creando Gato con nombre por defecto");
                miGato1 = new Gato(edadGato1);
            } catch (GatoEdadNegativaException ex) {
                System.out.println("Se ha producido un error al introducir la edad del Gato");
                System.out.println(ex.getMessage());
                System.out.println("Creando Gato por defecto");
                miGato1 = new Gato();
            }
        } catch (GatoEdadNegativaException e) {
            System.out.println("Se ha producido un error al introducir la edad del Gato");
            System.out.println(e.getMessage());
            try {
                System.out.println("Creando Gato con edad por defecto");
                miGato1 = new Gato(nombreGato1);
            } catch (GatoNombreMuyCortoException ex) {
                System.out.println("Se ha producido un error al introducir la edad del Gato");
                System.out.println(ex.getMessage());
                System.out.println("Creando Gato por defecto");
                miGato1 = new Gato();
            }
        }

        System.out.println();
        try {
            miGato2 = new Gato(nombreGato2, edadGato2);
            System.out.println(miGato2.toString());
        } catch (GatoNombreMuyCortoException e) {
            System.out.println("Se ha producido un error al introducir el nombre del Gato");
            System.out.println(e.getMessage());
            try {
                System.out.println("Creando Gato con nombre por defecto");
                miGato2 = new Gato(edadGato2);
            } catch (GatoEdadNegativaException ex) {
                System.out.println("Se ha producido un error al introducir la edad del Gato");
                System.out.println(ex.getMessage());
                System.out.println("Creando Gato por defecto");
                miGato2 = new Gato();
            }
        } catch (GatoEdadNegativaException e) {
            System.out.println("Se ha producido un error al introducir la edad del Gato");
            System.out.println(e.getMessage());
            try {
                System.out.println("Creando Gato con edad por defecto");
                miGato2 = new Gato(nombreGato2);
            } catch (GatoNombreMuyCortoException ex) {
                System.out.println("Se ha producido un error al introducir la edad del Gato");
                System.out.println(ex.getMessage());
                System.out.println("Creando Gato por defecto");
                miGato2 = new Gato();
            }
        }

        System.out.println();
        try {
            miGato3 = new Gato(nomreGato3, edadGato3);
            System.out.println(miGato3.toString());
        } catch (GatoNombreMuyCortoException e) {
            System.out.println("Se ha producido un error al introducir el nombre del Gato");
            System.out.println(e.getMessage());
            try {
                System.out.println("Creando Gato con nombre por defecto");
                miGato3 = new Gato(edadGato3);
            } catch (GatoEdadNegativaException ex) {
                System.out.println("Se ha producido un error al introducir la edad del Gato");
                System.out.println(ex.getMessage());
                System.out.println("Creando Gato por defecto");
                miGato3 = new Gato();
            }
        } catch (GatoEdadNegativaException e) {
            System.out.println("Se ha producido un error al introducir la edad del Gato");
            System.out.println(e.getMessage());
            try {
                System.out.println("Creando Gato con edad por defecto");
                miGato3 = new Gato(nomreGato3);
            } catch (GatoNombreMuyCortoException ex) {
                System.out.println("Se ha producido un error al introducir la edad del Gato");
                System.out.println(ex.getMessage());
                System.out.println("Creando Gato por defecto");
                miGato3 = new Gato();
            }
        }

        System.out.println();
        try {
            miGato4 = new Gato(nomreGato4, edadGato4);
            System.out.println(miGato4.toString());
        } catch (GatoNombreMuyCortoException e) {
            System.out.println("Se ha producido un error al introducir el nombre del Gato");
            System.out.println(e.getMessage());
            try {
                System.out.println("Creando Gato con nombre por defecto");
                miGato4 = new Gato(edadGato4);
            } catch (GatoEdadNegativaException ex) {
                System.out.println("Se ha producido un error al introducir la edad del Gato");
                System.out.println(ex.getMessage());
                System.out.println("Creando Gato por defecto");
                miGato4 = new Gato();
            }
        } catch (GatoEdadNegativaException e) {
            System.out.println("Se ha producido un error al introducir la edad del Gato");
            System.out.println(e.getMessage());
            try {
                System.out.println("Creando Gato con edad por defecto");
                miGato4 = new Gato(nomreGato4);
            } catch (GatoNombreMuyCortoException ex) {
                System.out.println("Se ha producido un error al introducir la edad del Gato");
                System.out.println(ex.getMessage());
                System.out.println("Creando Gato por defecto");
                miGato4 = new Gato();
            }
        }

        System.out.println();
        try {
            System.out.println("Estos son mis Gatos:");
            System.out.println(miGato1.toString());
            System.out.println(miGato2.toString());
            System.out.println(miGato3.toString());
            System.out.println(miGato4.toString());

            System.out.println("\nCambiando nombre a Gato 1 a Copo");
            miGato1.setNombre("Copo");

            System.out.println("\nEdad de " + miGato1.getNombre() + " es " + miGato1.getEdad());
            miGato1.setEdad(miGato1.getEdad() -1);

            System.out.println("El anio pasado tenia: " + miGato1.getEdad());
            miGato1.setEdad(miGato1.getEdad() -1);

            System.out.println("Hace 2 anios tenia: " + miGato1.getEdad());

        } catch (GatoEdadNegativaException e) {
            System.out.println("Se ha producido un error al introducir la edad del Gato");
            System.out.println(e.getMessage());
            System.out.println("Abortando cambios");
        }  catch (GatoNombreMuyCortoException e) {
            System.out.println("Se ha producido un error al introducir el nombre del Gato");
            System.out.println(e.getMessage());
            System.out.println("Abortando cambios");
        }

        System.out.println();
        try {
            System.out.println("Cambiando nombre a Gato 2 y 4 a Alfa y Pi");
            miGato2.setNombre("Alfa");
            System.out.println(miGato2.toString());
            miGato4.setNombre("Pi");
            System.out.println(miGato4.toString());
        } catch (GatoNombreMuyCortoException e) {
            System.out.println("Se ha producido un error al introducir el nombre del Gato");
            System.out.println(e.getMessage());
            System.out.println("Abortando cambios");
        }


        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("\nIntroduce el nombre del Gato: ");
            String nombreGato = sc.nextLine();

            System.out.print("Introduce el edad del Gato: ");
            int edadGato = sc.nextInt();

            System.out.println("Creando Gato...");
            Gato gatoNuevo = new Gato(nombreGato, edadGato);

            System.out.println("Gato agregado correctamente\n");
            System.out.println(gatoNuevo.toString());
        } catch (InputMismatchException e) {
            System.out.println("Valor introducido incorrecto\n");
        } catch (GatoNombreMuyCortoException e) {
            System.out.println("Error al introducir el nombre del Gato");
            System.out.println(e.getMessage() + "\n");
        } catch (GatoEdadNegativaException e) {
            System.out.println("Error al introducir el edad del Gato");
            System.out.println(e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("Algo fue mal");
            System.out.println(e.getMessage() + "\n");
        }
        sc.close();
    }
}
