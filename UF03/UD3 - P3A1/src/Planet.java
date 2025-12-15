public class Planet extends AstronomicalBody {

    //Variables
    private String planetName;//String literal
    private double radiusSun;//Unit on m
    private double orbitSun;//Unit on years
    private boolean gotSatelites;//Boolean

    //Constructor
    public Planet(String planetName, double averageTemperature, double gravity, double rotationPeriod,
                  double equatorialRadius, double mass, double orbitSun, boolean gotSatellites, double radiusSun) {
        super(averageTemperature, gravity, rotationPeriod, equatorialRadius, mass);
        this.planetName = planetName;
        this.orbitSun = orbitSun;
        this.gotSatelites = gotSatellites;
        this.radiusSun = radiusSun;
    }

    //Getter & Setter
    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public double getRadiusSun() {
        return radiusSun;
    }

    public void setRadiusSun(double radiusSun) {
        this.radiusSun = radiusSun;
    }

    public double getOrbitSun() {
        return orbitSun;
    }

    public void setOrbitSun(double orbitSun) {
        this.orbitSun = orbitSun;
    }

    public boolean isGotSatelites() {
        return gotSatelites;
    }

    public void setGotSatelites(boolean gotSatelites) {
        this.gotSatelites = gotSatelites;
    }

    //ToString
    @Override
    public String toString() {
        return "Name of the Planet: " + this.planetName + "\n" +
                super.toString() +
                "\nRadius to the Sun: " + this.radiusSun + " meters" +
                "\nOrbit around the Sun: " + this.orbitSun + " years" +
                "\nHas Satelites: " + this.gotSatelites;
    }
}
