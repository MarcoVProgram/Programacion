public abstract class AstronomicalBody {

    //Variables
    private double mass;//Unit on kg
    private double equatorialRadius;//Unit on m
    private double rotationPeriod;//Unit on days
    private double gravity;//Unit on m/s^2
    private double averageTemperature;//Unit on K

    //Constructor
    public AstronomicalBody(double averageTemperature, double gravity, double rotationPeriod, double equatorialRadius, double mass) {
        this.averageTemperature = averageTemperature;
        this.gravity = gravity;
        this.rotationPeriod = rotationPeriod;
        this.equatorialRadius = equatorialRadius;
        this.mass = mass;
    }

    //Getter & Setter
    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(double rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public double getEquatorialRadius() {
        return equatorialRadius;
    }

    public void setEquatorialRadius(double equatorialRadius) {
        this.equatorialRadius = equatorialRadius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    //Methods
    @Override
    public String toString() {
        return "Mass: " + mass +
                "\nEquatorial Radius: " + equatorialRadius + " meters" +
                "\nRotation Period: " + rotationPeriod +  " days" +
                "\nGravity: " + gravity + " meters/second^2" +
                "\naverageTemperature: " + averageTemperature + " Kelvin";
    }
}
