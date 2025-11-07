public class Main {
    public static void main(String[] args) {

        Persona Marco = new Persona("Marco", "Valiente", 22, "mvalienter@correo.es", "72267107A");

        String hola = Marco.getNombre();

        System.out.println("Nombre: " + Marco.getNombre());
    }
}
