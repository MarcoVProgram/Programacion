public class ColeccionVideoDaw {

    //Variables
    private VideoDaw[] registroVideoDaw;
    private int numRegistros = 0;
    private int initialSize = 10;

    //Constructor
    public ColeccionVideoDaw() {
        this.registroVideoDaw = new VideoDaw[initialSize];
    }

    //Getter

    public VideoDaw[] getRegistroVideoDaw() {
        return this.registroVideoDaw;
    }

    public int getNumRegistros() {
        return this.numRegistros;
    }

    //Nuevo VideoDaw
    public boolean addVideoClub(VideoDaw videoClub) {
        boolean resultado = false;

        if (videoClub != null) {
            if (this.numRegistros >= this.registroVideoDaw.length) {
                ampliarRegistro();
            }
            this.registroVideoDaw[this.numRegistros] = videoClub;
            this.numRegistros++;
            resultado = true;
        }

        return resultado;
    }

    //Metodos Privados para crear peliculas
    private void ampliarRegistro() {
        VideoDaw[] nuevaLista = new VideoDaw[this.numRegistros*2];
        for (int i = 0; i < this.numRegistros; i++) {
            nuevaLista[i] = this.registroVideoDaw[i];
        }

        this.registroVideoDaw = nuevaLista;
    }

    public String mostrarTodosVideoClub() {
        String infoTodosVideoDaws = "No hay ningun registro de VideoClubs VideoDaw";
        if (this.numRegistros > 0) {
            infoTodosVideoDaws = "";
            for (int i = 0; i < this.numRegistros; i++) {
                if (this.registroVideoDaw[i] != null) {
                    infoTodosVideoDaws += (i+1) + ". " + registroVideoDaw[i].mostrarInfoVideoDaw() + "\n\n";
                }
            }
        }
        return infoTodosVideoDaws;
    }
}
