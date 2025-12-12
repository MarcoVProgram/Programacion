import java.time.LocalDate;

public class Sensei extends Ninja {

    //Variables
    private String codSensei; //Autogenerado
    private double estrategia;
    private double liderazgo;

    //Contadores
    private int contadorCod = 0;

    //Constructor

    public Sensei(String nombre, String DNI, LocalDate fechaNacimiento, String direccion, String numeroDeContacto,
                  Chakra chakra, Rango rango, String tecnicaSecreta, double ataque, double defensa, double estrategia, double liderazgo) {

        super(nombre, DNI, fechaNacimiento, direccion, numeroDeContacto, chakra, rango, tecnicaSecreta, ataque, defensa);

        this.estrategia = estrategia;
        this.liderazgo = liderazgo;

        this.contadorCod++;
        this.codSensei = String.format("S-%04d", contadorCod);
    }

    //Getter

    public String getCodSensei() {
        return codSensei;
    }

    public double getEstrategia() {
        return estrategia;
    }

    public double getLiderazgo() {
        return liderazgo;
    }

    // toString

    @Override
    public String toString() {
        return super.toString() +
                "\nCodigo de Sensei: " + this.codSensei + "\n" +
                "Estrategia: " + this.estrategia + "\n" +
                "Liderazgo: " + this.liderazgo;
    }
}