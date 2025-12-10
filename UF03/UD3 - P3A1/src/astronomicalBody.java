public abstract class astronomicalBody {

    //Variables
    private int mass;//Unit on kg, no need for decimals
    private int equatorialRadius;//Unit on m, no need for decimals
    private double orbitalPeriod;//Unit on years
    private double rotationPeriod;//Unit on days
    private double gravity;//Unit on m/s^2
    private double averageTemperature;//Unit on K

    //Constructor


    public astronomicalBody(double averageTemperature, double gravity, double rotationPeriod, double orbitalPeriod, int equatorialRadius, int mass) {
        this.averageTemperature = averageTemperature;
        this.gravity = gravity;
        this.rotationPeriod = rotationPeriod;
        this.orbitalPeriod = orbitalPeriod;
        this.equatorialRadius = equatorialRadius;
        this.mass = mass;
    }

    //Methods
    @Override
    public String toString() {
        return "mass= " + mass +
                "\nEquatorial Radius= " + equatorialRadius +
                "\nOrbital Period= " + orbitalPeriod +
                "\nRotation Period= " + rotationPeriod +
                "\nGravity= " + gravity +
                "\naverageTemperature= " + averageTemperature;
    }
}
