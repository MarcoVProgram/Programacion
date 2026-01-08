import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        List<String> nombres = new ArrayList<>(5);

        nombres.add("Marco");
        nombres.add("Anuar");
        nombres.add("Oscar");
        nombres.add("Aron");

        nombres.remove("Anuar");

        for  (String nombre : nombres) {
            System.out.println(nombre);
        }
        System.out.println(nombres);

        int[] x = new int[5];
        System.out.println(Arrays.toString(x));

        nombres.add(1,"Anuar");
    }
}