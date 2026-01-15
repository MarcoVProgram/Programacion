public class Coche {

    //Atributos Privados
    String color;
    String marca;

    //Constructor
    public Coche(String color, String marca) {
        this.color = color;
        this.marca = marca;
    }

    //Get & Set
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
