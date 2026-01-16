public class Coche {

    //Atributos Privados
    private String color;
    private String marca;

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

    //ToString
    @Override
    public String toString() {
        return "{ Color='" + color + '\'' +
                ", Marca='" + marca + '\'' +
                " }";
    }
}
