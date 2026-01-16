import java.util.*;

public class Taller {

    //Atributos Privados
    private HashMap<String,Coche> inventario;

    //Constructor
    public Taller(){
        inventario = new HashMap<>();
    }

    //Metodos
    public boolean anadeElemento(String matricula, Coche coche){
        boolean resultado = false;

        if (coche != null && matricula != null) {
            inventario.put(matricula, coche);
            resultado = true;
        }

        return resultado;
    }

    public boolean elementoExiste(String matricula){
        return inventario.containsKey(matricula);
    }

    public boolean eliminaElemento(String matricula){
        boolean resultado = false;
        if (inventario.remove(matricula) != null) {
            resultado = true;
        }
        return resultado;
    }

    public String visualizaMatriculas() {
        String respuesta = "No hay matriculas";
        Set<String> matriculas;

        if (!inventario.isEmpty()) {
            respuesta = "";
            matriculas = inventario.keySet();

            for (String matricula : matriculas) {
                respuesta += matricula + "\n";
            }
        }

        return respuesta;
    }

    public String visualizaCoches() {
        String respuesta = "No hay coches";
        Collection<Coche> coches;

        if (!inventario.isEmpty()) {
            respuesta = "";
            coches = inventario.values();
            for (Coche coche : coches) {
                respuesta += coche.toString() + "\n";
            }
        }

        return respuesta;
    }

    public String visualizaTaller() {
        String respuesta = "No existe inventario";

        if (!inventario.isEmpty()) {
            respuesta = "";
            for (Map.Entry<String, Coche> entry : inventario.entrySet()) {
                respuesta += "Matricula -> " + entry.getKey() + " | Caracteristicas -> " + entry.getValue().toString() + "\n";
            }
        }

        return respuesta;
    }
}
