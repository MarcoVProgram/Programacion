import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        /*
            Crea un programa que pida por teclado tres cadenas de texto: nombre y dos apellidos.
            Luego mostrará un código de usuario (en mayúsculas) formado por la concatenación de
            las tres primeras letras de cada uno de ellos. Por ejemplo, si se introduce “Lionel”,
            “Tarazón” y “Alcocer” mostrará “LIOTARALC”
         */
        System.out.println("\n\u001B[36mEjercicio 3\u001B[38m");

        Scanner sc = new Scanner(System.in);
        System.out.println("\nIntroduce tu nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce tu primer apellido: ");
        String apellido1 = sc.nextLine();
        System.out.println("Introduce tu segundo apellido: ");
        String apellido2 = sc.nextLine();

        String resultado = nombre.substring(0,Math.min(nombre.length(),3)) + apellido1.substring(0,Math.min(apellido1.length(),3))
                + apellido2.substring(0,Math.min(apellido2.length(),3));

        System.out.println(resultado.toUpperCase());

        sc.close();
    }
}
