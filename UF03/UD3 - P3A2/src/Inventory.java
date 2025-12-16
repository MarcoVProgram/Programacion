public class Inventory {

    private Parrot[] listParrots = new Parrot[10];
    private int numberParrots;
    private Cat[] listCats = new Cat[10];
    private int numberCats;
    private Dog[] listDogs = new Dog[10];
    private int numberDogs;
    private Canary[] listCanaries = new Canary[10];
    private int numberCanaries;

    public Inventory() {
        this.numberCats = 0;
        this.numberCanaries = 0;
        this.numberDogs = 0;
        this.numberParrots = 0;
    }

    public boolean addParrot(Parrot parrot) {
        boolean result = false;

        //
        if (parrot != null) {

            if (this.numberParrots >= this.listParrots.length) {
                Parrot[] newList = new Parrot[this.numberParrots*2];

                for (int i = 0; i < this.numberParrots; i++) {
                    newList[i] = this.listParrots[i];
                }
                this.listParrots = newList;
            }

            this.listParrots[this.numberParrots] = parrot;
            this.numberParrots++;
            result = true;

        }

        return result;
    }

    public boolean addCat(Cat cat) {
        boolean result = false;

        //
        if (cat != null) {

            if (this.numberCats >= this.listCats.length) {
                Cat[] newList = new Cat[this.numberCats*2];

                for (int i = 0; i < this.numberCats; i++) {
                    newList[i] = this.listCats[i];
                }
                this.listCats = newList;
            }

            this.listCats[this.numberCats] = cat;
            this.numberCats++;
            result = true;

        }

        return result;
    }

    public boolean addDog(Dog dog) {
        boolean result = false;

        //
        if (dog != null) {

            if (this.numberDogs >= this.listDogs.length) {
                Dog[] newList = new Dog[this.numberDogs*2];

                for (int i = 0; i < this.numberDogs; i++) {
                    newList[i] = this.listDogs[i];
                }
                this.listDogs = newList;
            }

            this.listDogs[this.numberDogs] = dog;
            this.numberDogs++;
            result = true;

        }

        return result;
    }

    public boolean addCanary(Canary canary) {
        boolean result = false;

        //
        if (canary != null) {

            if (this.numberCanaries >= this.listCanaries.length) {
                Canary[] newList = new Canary[this.numberCanaries*2];

                for (int i = 0; i < this.numberCanaries; i++) {
                    newList[i] = this.listCanaries[i];
                }
                this.listCanaries = newList;
            }

            this.listCanaries[this.numberCanaries] = canary;
            this.numberCanaries++;
            result = true;

        }

        return result;
    }

    public boolean eliminateParrot(Parrot pet) {
        boolean result = false;
        int index = -1;

        if (pet != null) {
            for  (int i = 0; i < this.numberParrots; i++) {
                if (this.listParrots[i] == pet) {
                    index = i;
                }
            }
            if (index != -1) {
                this.listParrots[index] = null;
                this.listParrots[index] = this.listParrots[this.numberParrots - 1];
                this.listParrots[this.numberParrots - 1] = null;
                this.numberParrots--;
                result = true;
            }
        }

        return result;
    }

    public boolean eliminateDog(Dog pet) {
        boolean result = false;
        int index = -1;

        if (pet != null) {
            for  (int i = 0; i < this.numberDogs; i++) {
                if (this.listDogs[i] == pet) {
                    index = i;
                }
            }
            if (index != -1) {
                this.listDogs[index] = null;
                this.listDogs[index] = this.listDogs[this.numberDogs - 1];
                this.listDogs[this.numberDogs - 1] = null;
                this.numberDogs--;
                result = true;
            }
        }

        return result;
    }

    public boolean eliminateCat(Cat pet) {
        boolean result = false;
        int index = -1;

        if (pet != null) {
            for  (int i = 0; i < this.numberCats; i++) {
                if (this.listCats[i] == pet) {
                    index = i;
                }
            }
            if (index != -1) {
                this.listCats[index] = null;
                this.listCats[index] = this.listCats[this.numberCats - 1];
                this.listCats[this.numberCats - 1] = null;
                this.numberCats--;
                result = true;
            }
        }

        return result;
    }

    public boolean eliminateCanary(Canary pet) {
        boolean result = false;
        int index = -1;

        if (pet != null) {
            for  (int i = 0; i < this.numberCanaries; i++) {
                if (this.listCanaries[i] == pet) {
                    index = i;
                }
            }
            if (index != -1) {
                this.listCanaries[index] = null;
                this.listCanaries[index] = this.listCanaries[this.numberCanaries - 1];
                this.listCanaries[this.numberCanaries - 1] = null;
                this.numberCanaries--;
                result = true;
            }
        }

        return result;
    }

    public String animalList() {
        String result = "";

        for (int i = 0; i<numberParrots;i++) {
            result += "\nParrot: " + this.listParrots[i].getName();
        }
        for (int i = 0; i<numberDogs;i++) {
            result += "\nDog: " + this.listDogs[i].getName();
        }
        for (int i = 0; i<numberCats;i++) {
            result += "\nCat: " + this.listCats[i].getName();
        }
        for (int i = 0; i<numberCanaries;i++) {
            result += "\nCanary: " + this.listCanaries[i].getName();
        }

        return result;
    }

    public String showParticularAnimal(String animal) {
        String result = "Not valid animal";

        if (animal.equalsIgnoreCase("Parrot")) {
            result = "";
            for (int i = 0; i<numberParrots;i++) {
                result +=  (i+1)+":" + this.listParrots[i].toString() + "\n\n";
            }
        }

        if (animal.equalsIgnoreCase("Dog")) {
            result = "";
            for (int i = 0; i<numberDogs;i++) {
                result +=  (i+1)+":" + this.listDogs[i].toString() + "\n\n";
            }
        }

        if (animal.equalsIgnoreCase("Cat")) {
            result = "";
            for (int i = 0; i<numberCats;i++) {
                result +=  (i+1)+":" + this.listCats[i].toString() + "\n\n";
            }
        }

        if (animal.equalsIgnoreCase("Canary")) {
            result = "";
            for (int i = 0; i<numberCanaries;i++) {
                result +=  (i+1)+":" + this.listCanaries[i].toString() + "\n\n";
            }
        }

        return result;
    }

    public String showAllAnimals() {
        String result = "";

        for (int i = 0; i<numberParrots;i++) {
            result +=  (i+1)+":" + this.listParrots[i].toString() + "\n\n";
        }
        for (int i = 0; i<numberDogs;i++) {
            result +=  (i+1)+":" + this.listDogs[i].toString() + "\n\n";
        }
        for (int i = 0; i<numberCats;i++) {
            result +=  (i+1)+":" + this.listCats[i].toString() + "\n\n";
        }

        for (int i = 0; i<numberCanaries;i++) {
            result +=  (i+1)+":" + this.listCanaries[i].toString() + "\n\n";
        }

        return result;
    }

    public void emptyOutInventory() {

        for (int i = 0; i<numberParrots;i++) {
            listParrots[i] = null;
            numberParrots = 0;
        }
        for (int i = 0; i<numberDogs;i++) {
            listCats[i] = null;
            numberCats = 0;
        }
        for (int i = 0; i<numberCats;i++) {
            listDogs[i] = null;
            numberDogs = 0;
        }

        for (int i = 0; i<numberCanaries;i++) {
            listCanaries = null;
            numberCanaries = 0;
        }
    }
}
