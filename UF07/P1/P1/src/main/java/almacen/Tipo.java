package almacen;

public class Tipo {

    private int idTipo;
    private String tipoNombre;

    public Tipo(int idTipo, String tipoNombre) {
        this.idTipo = idTipo;
        this.tipoNombre = tipoNombre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    @Override
    public String toString() {
        return "< ID Tipo: " + this.idTipo + "\t|\tNombre Tipo: " + this.tipoNombre + " >";
    }
}