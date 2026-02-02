public class InputIncorrectoException extends Exception {

    private static final long serialVersionUID = 471174238787598357L;
    private String inputMistake;

    public InputIncorrectoException(String inputMistake) {
        super("El input mal introducido ha sido: " +  inputMistake);
        this.inputMistake = inputMistake;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InputIncorrectoException:\n");
        sb.append("Input Mistake: ").append(inputMistake);
        return sb.toString();
    }
}
