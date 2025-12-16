import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        Inventory myInventory = new Inventory();


        System.out.println("Adding a Parrot!");
        Parrot myParrot = new Parrot("Ben", 12.0, "Alive", LocalDate.now(),"Big",true,"Brasil",false);
        if(myInventory.addParrot(myParrot)){
            System.out.println("Success!");
        }

        System.out.println(myInventory.animalList());
        System.out.println(myInventory.showAllAnimals());

        System.out.println("Emptying collection");
        myInventory.emptyOutInventory();
    }
}