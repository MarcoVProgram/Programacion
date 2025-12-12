import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

public class Equipo {

    private String nombreEquipo;
    private String codigo;//2 letras mayusculas y 7 digitos
    private LocalDate fechaCreacion;
    private Sensei sensei;

    //Array
    private int maximoNinjas;
    private Ninja[] ninjasMiembro;
    private int numNinjas;

    //Constructor
    public Equipo(String nombreEquipo, String codigo, Sensei sensei, int maximoDeNinjas) {

        this.nombreEquipo = nombreEquipo;
        this.codigo = codigo;
        this.sensei = sensei;
        this.maximoNinjas = maximoDeNinjas;
        this.numNinjas = 0;
        this.ninjasMiembro = new Ninja[this.maximoNinjas];

        this.fechaCreacion = LocalDate.now();
    }

    //Getter

    public String getNombreEquipo() {
        return this.nombreEquipo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    public Sensei getSensei() {
        return this.sensei;
    }

    public Ninja[] getNinjasMiembro() {
        return ninjasMiembro;
    }

    public int getMaximoNinjas() {
        return this.maximoNinjas;
    }

    public int getNumNinjas() {
        return this.numNinjas;
    }

    // toString

    @Override
    public String toString() {
        String fechaCreacionFormato = MisUtilidades.fechaFormato("dd/MM/yyyy", this.fechaCreacion);

        return "Equipo:\n" +
                "Nombre del Equipo: " + this.nombreEquipo + "\n" +
                "Codigo: " + this.codigo + "\n" +
                "Fecha de Creacion: " + fechaCreacionFormato + "\n" +
                "Sensei:" + this.sensei.getNombre() + "\n" +
                "Maximo de Ninjas: " + this.maximoNinjas + "\n" +
                "Numero de Ninjas en el Equipo" + this.numNinjas;
    }

    public boolean registrarNinja(Ninja ninja) {
        boolean resultado = false;

        //Si existe
        if (ninja != null || this.numNinjas!= this.maximoNinjas) {
            //Sumar al registro
            this.ninjasMiembro[this.numNinjas] = ninja;
            this.numNinjas++;
            resultado = true;
            ninja.setFechaAlta(LocalDate.now());
        }

        return resultado;
    }

    public boolean eliminarNinja(Ninja ninja) {
        boolean resultado = false;
        int index = -1;

        if (ninja != null) {
            for  (int i = 0; i < this.numNinjas; i++) {
                if (this.ninjasMiembro[i] == ninja) {
                    if (this.ninjasMiembro[i].getDNI().equalsIgnoreCase(ninja.getDNI())) {
                        index = i;
                        break;
                    }
                }
            }
            if (index != -1) {
                this.ninjasMiembro[index] = null;
                this.ninjasMiembro[index] = this.ninjasMiembro[this.numNinjas-1];
                this.ninjasMiembro[this.numNinjas-1] = null;
                this.numNinjas--;
                resultado = true;
            }
        }

        return resultado;
    }

    public Ninja buscarNinjaPorDNI(String DNI) {
        Ninja ninja = null;
        int index = -1;

        //Buscando equipos a traves de Codigo de equipo
        for (int i = 0; i < this.numNinjas; i++) {
            if (this.ninjasMiembro[i].getDNI().equalsIgnoreCase(DNI)) {
                index = i;
                break;
            }
        }

        //Solo si se encontro la equipo
        if (index != -1) {
            ninja = this.ninjasMiembro[index];
        }

        return ninja;
    }
}
