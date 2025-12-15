public class Satellite extends AstronomicalBody {

    //Variables
    private String satelliteName;
    private double distancePlanet;//Units in m
    private double orbitPlanet;//Units in days
    private Planet planet;

    //Constructor
    public Satellite(String satelliteName, double averageTemperature, double gravity, double rotationPeriod, double equatorialRadius, double mass, Planet planet, double orbitPlanet, double distancePlanet) {
        super(averageTemperature, gravity, rotationPeriod, equatorialRadius, mass);
        this.satelliteName = satelliteName;
        this.planet = planet;
        this.orbitPlanet = orbitPlanet;
        this.distancePlanet = distancePlanet;
    }

    //Getter and Setter

    public String getSatelliteName() {
        return satelliteName;
    }

    public void setSatelliteName(String satelliteName) {
        this.satelliteName = satelliteName;
    }

    public double getDistancePlanet() {
        return distancePlanet;
    }

    public void setDistancePlanet(double distancePlanet) {
        this.distancePlanet = distancePlanet;
    }

    public double getOrbitPlanet() {
        return orbitPlanet;
    }

    public void setOrbitPlanet(double orbitPlanet) {
        this.orbitPlanet = orbitPlanet;
    }

    public Planet getPlanet() {
        return planet;
    }

    //ToString

    @Override
    public String toString() {
        return "Name of the Satellite: " + this.satelliteName + "\n" +
                super.toString() +
                "\nName of the Planet it Orbits: " + this.getPlanet().getPlanetName() +
                "\nDistance to the Planet: " + this.distancePlanet + " meters" +
                "\nOrbit around the Planet: " + this.orbitPlanet + " days";
    }
}
