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


}
