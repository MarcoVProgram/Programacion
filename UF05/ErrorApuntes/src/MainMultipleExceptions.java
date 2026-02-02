import java.util.Scanner;

public class MainMultipleExceptions {
        public static void main(String args[]) {

        Scanner lector = new Scanner(System.in);

            try {
            System.out.println("Introduce dividendo: ");
            String texto = lector.nextLine();
            int dividendo = Integer.parseInt(texto);

            System.out.println("Introduce divisor: ");
            texto = lector.nextLine();
            int divisor = Integer.parseInt(texto);
            int resultado = dividendo / divisor;
            int resto = dividendo % divisor;
            System.out.println("Resultado división: " + resultado);
            System.out.println("Resto: " + resto);
        } catch (NumberFormatException e) {
            System.out.println("Error en la entrada: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error. No dividas por cero.");
        } catch (RuntimeException e) {
            System.out.println("Error en la entrada: " + e.getMessage());
        }
        finally {
            lector.close();
        }
    }
}