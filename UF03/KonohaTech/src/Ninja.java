import java.time.LocalDate;

public class Ninja extends Persona {

    protected Chakra chakra;
    protected Rango rango;
    protected int idNinja;
    protected static int contadorNinja = 0;
    protected String tecnicaSecreta;
    protected LocalDate fechaAlta;
    protected double ataque;
    protected double defensa;

    //Constructor
    public Ninja(String nombre, String DNI, LocalDate fechaNacimiento, String direccion, String numeroDeContacto,
                 Chakra chakra, Rango rango, String tecnicaSecreta, double ataque, double defensa) {

        super(nombre, DNI, fechaNacimiento, direccion, numeroDeContacto);

        this.chakra = chakra;
        this.rango = rango;
        this.tecnicaSecreta = tecnicaSecreta;
        this.ataque = ataque;
        this.defensa = defensa;

        this.contadorNinja++;
        this.idNinja = contadorNinja;
    }

    //Getter

    public Chakra getChakra() {
        return chakra;
    }

    public Rango getRango() {
        return rango;
    }

    public int getIdNinja() {
        return idNinja;
    }

    public String getTecnicaSecreta() {
        return tecnicaSecreta;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public double getAtaque() {
        return ataque;
    }

    public double getDefensa() {
        return defensa;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    // toString

    @Override
    public String toString() {
        String fechaAltaFormato = MisUtilidades.fechaFormato("dd/MM/yyyy", this.fechaAlta);
        return super.toString() +
                "\nID de Ninja: " + this.idNinja + "\n" +
                "Chakra: " + this.chakra.name() + "\n" +
                "Rango: " + this.rango.name() + "\n" +
                "Tecnica secreta: " + this.tecnicaSecreta + "\n" +
                "Fecha de Alta: " + fechaAltaFormato + "\n" +
                "Ataque: " + this.ataque + "\n" +
                "Defensa: " + this.defensa;
    }
}
