import java.io.Serial;

//Clase exception
public class InputIncorrectoException extends Exception {

    @Serial
    private static final long serialVersionUID = -707568092472381559L;
    private String inputMistake;

    //Constructor con parametros
    //@param inputMistake el input mal introducido que salta el fallo
    public InputIncorrectoException(String inputMistake) {

        super("El input mal introducido ha sido: " +  inputMistake);
        this.inputMistake = inputMistake;
    }

    //ToString
    //@return String, por defecto cuando se llama a system out println
    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("InputIncorrectoException:\n");
        sb.append("Input Mistake: ").append(inputMistake);

        return sb.toString();
    }
}
