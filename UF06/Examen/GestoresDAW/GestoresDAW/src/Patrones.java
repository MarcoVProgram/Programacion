import java.util.regex.Pattern;

public class Patrones {

    public static final Pattern NOT_NULL = Pattern.compile("^.+");
    public static final Pattern DNI_FORM = Pattern.compile("[0-9]{8}[A-Za-z]");
    public static final Pattern NOMBRE_PERSONA_FORM = Pattern.compile("[A-Z][a-z]+ [A-Z a-z]+");
    public final static Pattern EMAIL_FORM = Pattern.compile("[A-Za-z-._0-9]+@[A-Za-z]+[.][A-Za-z]{2,4}");
    public final static Pattern CIF_FORM = Pattern.compile("[A-Za-z][0-9]{10}");
    public final static Pattern NUMERO_TELEFONO_FORM = Pattern.compile("[0-9]{9}");
    public final static Pattern MENU_FORM = Pattern.compile("[1-7]");

}
