package almacen;

import java.util.regex.Pattern;

public class Patrones {

    public static final Pattern NOT_NULL = Pattern.compile("^.+");
    public static final Pattern NOMBRE_FORM = Pattern.compile("^[A-Za-z 0-9]{1,20}$");
    public static final Pattern REFERENCIA_FORM = Pattern.compile("^[A-Za-z0-9]{10}$");
    public static final Pattern DESCRIPTION_FORM = Pattern.compile("^.{0,100}");
}
