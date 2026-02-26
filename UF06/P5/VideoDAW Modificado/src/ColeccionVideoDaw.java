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

        //Solo hacer si videoclub input existe
        if (videoClub != null) {
            //Ampliar lista si esta llena
            if (this.numRegistros >= this.registroVideoDaw.length) {
                ampliarRegistro();
            }

            //Sumar al registro
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

    //Metodo Informacion
    public String mostrarTodosVideoClub() {
        String infoTodosVideoDaws = "No hay ningun registro de VideoClubs VideoDaw";

        //Si hay registro de VideoClubs
        if (this.numRegistros > 0) {
            infoTodosVideoDaws = "";
            for (int i = 0; i < this.numRegistros; i++) {
                if (this.registroVideoDaw[i] != null) {
                    infoTodosVideoDaws += "\n" + (i+1) + ". " + registroVideoDaw[i].mostrarInfoVideoDaw() + "\n";
                }
            }
        }

        return infoTodosVideoDaws;
    }
}
