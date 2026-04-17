import java.io.Serial;

/**
 * Excepción lanzada cuando la entrada de usuario no coincide con el patrón o formato esperado.
 * 
 * <p>Esta excepción es utilizada por métodos de validación de entrada para indicar que
 * el texto introducido por el usuario no cumple con los requisitos especificados. Almacena
 * el valor incorrecto para propósitos de depuración y notificación al usuario.</p>
 * 
 * @author Marco
 * @version 1.0
 * @see Exception
 * @since 1.0
 */
public class InputIncorrectoException extends Exception {

    /** Identificador de versión para la serialización. */
    @Serial
    private static final long serialVersionUID = -707568092472381559L;
    
    /** La entrada de usuario que provocó la excepción. */
    private String inputMistake;

    /**
     * Construye una nueva excepción {@code InputIncorrectoException} con el input incorrecto especificado.
     * 
     * <p>La excepción contendrá un mensaje descriptivo que incluye el valor erróneo introducido.</p>
     * 
     * @param inputMistake la cadena de entrada que no coincide con el patrón o formato esperado
     */
    public InputIncorrectoException(String inputMistake) {
        super("El input mal introducido ha sido: " + inputMistake);
        this.inputMistake = inputMistake;
    }

    /**
     * Retorna una representación en cadena de texto de esta excepción.
     * 
     * <p>Proporciona un formato legible que incluye el nombre de la excepción y el valor
     * de entrada que causó el error.</p>
     * 
     * @return una cadena que describe la excepción y el input incorrecto
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InputIncorrectoException:\n");
        sb.append("Input Mistake: ").append(inputMistake);
        return sb.toString();
    }
}