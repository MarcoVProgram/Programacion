import java.time.LocalDate;

public class Aldea {

    //Variables Privadas
    private String nombre;
    private String codigo;//5 letras mayusculas y 5 digitos
    private LocalDate fechaCreacion;
    private Sensei kage;

    //Arrays
    private int equiposMaximos;
    private Equipo[] equiposRegistrados;

    //Contadores
    private int numEquipos;

    //Constructor
    public Aldea(String nombre, String codigo, Sensei kage, int equiposMaximos) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.kage = kage;
        this.equiposMaximos = equiposMaximos;
        this.fechaCreacion = LocalDate.now();

        this.equiposRegistrados = new Equipo[equiposMaximos];
        this.numEquipos = 0;
    }

    //Getter

    public String getNombre() {
        return this.nombre;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    public Sensei getKage() {
        return this.kage;
    }

    public int getEquiposMaximos() {
        return this.equiposMaximos;
    }

    public Equipo[] getEquiposRegistrados() {
        return equiposRegistrados;
    }

    public int getNumEquipos() {
        return this.numEquipos;
    }


    // toString

    @Override
    public String toString() {
        String fechaCreacionFormato = MisUtilidades.fechaFormato("dd/MM/yyyy", this.fechaCreacion);

        return "Aldea:\n\n" +
                "Nombre: " + this.nombre + "\n" +
                "Codigo: " + this.codigo + "\n" +
                "Fecha de Creacion: " + fechaCreacionFormato + "\n" +
                "Kage: " + this.kage.getNombre() + "\n" +
                "Equipos Maximos: " + this.equiposMaximos + "\n" +
                "Equipos Actualmente Registrados: " + this.numEquipos + "\n";
    }

    //Metodos

    public boolean registrarEquipo(Equipo equipo) {
        boolean resultado = false;

        //Si Equipo existe
        if (equipo != null || this.numEquipos != this.equiposMaximos) {
            //Sumar Equipo al registro
            this.equiposRegistrados[this.numEquipos] = equipo;
            this.numEquipos++;
            resultado = true;
        }

        return resultado;
    }

    public Equipo buscarEquipoPorCodigo(String codEquipo) {
        Equipo equipo = null;
        int index = -1;

        //Buscando equipos a traves de Codigo de equipo
        for (int i = 0; i < this.numEquipos; i++) {
            if (this.equiposRegistrados[i].getCodigo().equalsIgnoreCase(codEquipo)) {
                index = i;
                break;
            }
        }

        //Solo si se encontro la equipo
        if (index != -1) {
            equipo = this.equiposRegistrados[index];
        }

        return equipo;
    }

    public String mostrarEquiposRegistrados() {
        String infoTodosEquipos = "No hay ningun registro de Equipos";

        //Accion solo si hay Equipos Registrados
        if (this.numEquipos > 0) {
            infoTodosEquipos = "";

            for (int i = 0; i < this.numEquipos; i++) {
                //Seguridad que Equipo exista
                if (this.equiposRegistrados[i] != null) {
                    infoTodosEquipos += "\n" + this.equiposRegistrados[i].toString() + "\n";
                }
            }
        }

        return infoTodosEquipos;
    }

    public int calcularNinjasAldea() {
        int ninjasAldea = 1 + this.numEquipos; //Kage + Sensei de cada equipo

        for (int i = 0; i < this.numEquipos; i++) {
            ninjasAldea += this.equiposRegistrados[i].getNumNinjas();//Cada Ninja normal
        }

        return ninjasAldea;
    }

    public String calcularEstadisticasTodaAldea() {
        double liderazgo = 0, estrategia = 0, ataque = 0, defensa = 0;
        int contadorTotal = 0;

        for (int i = 0; i < this.numEquipos; i++) {
            estrategia += this.equiposRegistrados[i].getSensei().getEstrategia();
            liderazgo  += this.equiposRegistrados[i].getSensei().getLiderazgo();
            ataque += this.equiposRegistrados[i].getSensei().getAtaque();
            defensa += this.equiposRegistrados[i].getSensei().getDefensa();
            for (int j = 0; j < this.equiposRegistrados[i].getNumNinjas(); j++) {
                ataque += this.equiposRegistrados[i].getNinjasMiembro()[j].getAtaque();
                defensa += this.equiposRegistrados[i].getNinjasMiembro()[j].getDefensa();
                contadorTotal++;
            }
        }
        contadorTotal = contadorTotal + this.numEquipos;

        estrategia /= this.numEquipos;
        liderazgo /= this.numEquipos;
        ataque /= contadorTotal;
        defensa /= contadorTotal;

        return "Liderazgo: " + liderazgo +
                "\nEstrategia: " + estrategia +
                "\nAtaque: " + ataque
                + "\nDefensa: " + defensa;
    }
}
