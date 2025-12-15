import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    final static Pattern numberPattern = Pattern.compile("[0-9]+");
    static Matcher patternMatcher;
    static Planet[] planets = new Planet[10];
    static int numberPlanets = 0;
    static Satellite[] satellites = new Satellite[200];
    static int numberSatellites = 0;

    static Scanner input;
    public static void pause() {
        System.out.println("Press any key to continue...");
        input.nextLine();
    }

    public static void main(String[] args) {

        planets[0] = new Planet("Earth", 288.0,9.8, 1.0,
                6378000, (5.9722*Math.pow(10,24)), 1.0,true,
                1.496*Math.pow(10,11));
        numberPlanets++;

        satellites[0] = new Satellite("Moon",400.0,1.62,27.3,
                1737400.0,7.35*Math.pow(10,22),planets[0],27.3,384.4*Math.pow(10,6));
        numberSatellites++;

        loopMain();

        input.close();
    }

    private static void loopMain() {
        int option;
        String rawInput;
        do {
            input = new Scanner(System.in);
            System.out.println("Welcome to the Program!");

            System.out.println("1. Show info of a planet");
            System.out.println("2. Show info of all registered planets");
            System.out.println("3. Show info of a Satellite");
            System.out.println("4. Show info of all Satellites");
            System.out.println("5. Insert info of a Planet");
            System.out.println("6. Insert info of a Satellite");
            System.out.println("7. Exit");

            System.out.println("Select an option: ");
            rawInput = input.nextLine();
            patternMatcher = numberPattern.matcher(rawInput);
            while (!patternMatcher.find()) {
                input = new Scanner(System.in);
                System.out.println("Input " + rawInput + " is incorrect, try again:");
                rawInput = input.nextLine();
                patternMatcher = numberPattern.matcher(rawInput);
            }
            option = Integer.parseInt(rawInput);

            switch (option) {
                case 1:
                    showPlanet();
                    break;
                case 2:
                    showAllPlanets();
                    break;
                case 3:
                    showSatellite();
                    break;
                case 4:
                    showAllSatellites();
                    break;
                case 5:
                    insertPlanet();
                    break;
                case 6:
                    insertSatellite();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Option out of Range. Resetting menu...");
                    break;
            }
        } while (option != 7);
    }

    public static void showPlanet() {
        String inputPlanet;
        int optionPlanet;

        System.out.println("-----------------------------");
        if (numberPlanets < 2) {
            System.out.println("These are the statistics of your only planet:");
            System.out.println(planets[0].toString());
        }
        else {

            System.out.println("These are the planets to choose from:");
            for (int i = 0; i < numberPlanets; i++) {
                System.out.println((i+1) + ". " + planets[i].getPlanetName());
            }

            System.out.println("Choose a planet:");
            inputPlanet = input.nextLine();

            while (!patternMatcher.find()) {
                input = new Scanner(System.in);
                System.out.println("Input " + inputPlanet + " is incorrect, try again:");
                inputPlanet = input.nextLine();
                patternMatcher = numberPattern.matcher(inputPlanet);
            }

            optionPlanet = Integer.parseInt(inputPlanet);

            if (optionPlanet < 0 || optionPlanet > numberPlanets) {
                System.out.println("Option out of Range. Exiting ...");
            }

            else  {
                System.out.println(planets[optionPlanet-1].getPlanetName() + " chosen, showing information about the planet:");
                System.out.println(planets[optionPlanet-1].toString());
            }
        }
        System.out.println("-----------------------------");
        pause();
    }

    public static void showAllPlanets() {
        System.out.println("-----------------------------");
        System.out.println("These are all the planets:");

        for (int i = 0; i < numberPlanets; i++) {
            System.out.println((i+1) + ". " + planets[i].toString());
        }
        System.out.println("-----------------------------");
        pause();
    }

    public static void showSatellite() {
        String inputSatellite;
        int optionSatellite;

        System.out.println("-----------------------------");
        if (numberSatellites < 2) {
            System.out.println("These are the statistics of your only satellite:");
            System.out.println(satellites[0].toString());
        }
        else {

            System.out.println("These are the satellites to choose from:");
            for (int i = 0; i < numberSatellites; i++) {
                System.out.println((i+1) + ". " + satellites[i].getSatelliteName());
            }

            System.out.println("Choose a satellite:");
            inputSatellite = input.nextLine();

            while (!patternMatcher.find()) {
                input = new Scanner(System.in);
                System.out.println("Input " + inputSatellite + " is incorrect, try again:");
                inputSatellite = input.nextLine();
                patternMatcher = numberPattern.matcher(inputSatellite);
            }

            optionSatellite = Integer.parseInt(inputSatellite);

            if (optionSatellite < 0 || optionSatellite > numberSatellites) {
                System.out.println("Option out of Range. Exiting ...");
            }

            else  {
                System.out.println(satellites[optionSatellite-1].getSatelliteName() + " chosen, showing information about the satellite:");
                System.out.println(satellites[optionSatellite-1].toString());
            }
        }
        System.out.println("-----------------------------");
        pause();
    }

    public static void showAllSatellites() {
        System.out.println("-----------------------------");
        System.out.println("These are all the satellites:");

        for (int i = 0; i < numberSatellites; i++) {
            System.out.println((i+1) + ". " + satellites[i].toString());
        }
        System.out.println("-----------------------------");
        pause();
    }

    public static void insertPlanet() {
        System.out.println("-----------------------------");
        input = new Scanner(System.in);
        if (numberPlanets == planets.length) {
            System.out.println("You cannot add any more planets. Your list is full.");
        }
        else {
            System.out.println("Choose a planet name:");
            String planetName = input.nextLine();

            System.out.println("Choose the average temperature (Kelvin):");
            double averageTemperature = input.nextDouble();

            System.out.println("Choose the gravity (meters/second^2):");
            double gravity = input.nextDouble();

            System.out.println("Choose the rotation period around itself (days):");
            double rotation = input.nextDouble();

            System.out.println("Choose the equatorial radius (meters):");
            double equatorialRadius = input.nextDouble();

            System.out.println("Choose the mass of the planet (kg):");
            double mass = input.nextDouble();

            System.out.println("Choose the orbital period around the Sun:");
            double orbitSun = input.nextDouble();

            System.out.println("Does it have Satellites? (true or false):");
            boolean hasSatellites = input.nextBoolean();

            System.out.println("Choose the radius around the Sun (meters):");
            double radiusSun = input.nextDouble();

            planets[numberPlanets] = new Planet(planetName,averageTemperature,gravity,rotation,equatorialRadius,mass,orbitSun,hasSatellites,radiusSun);
        }
        System.out.println("-----------------------------");
        System.out.println("This is the planet:");
        System.out.println(planets[numberPlanets].toString());
        numberPlanets++;
        pause();
    }

    public static void insertSatellite() {
        System.out.println("-----------------------------");
        input = new Scanner(System.in);
        if (numberPlanets == planets.length) {
            System.out.println("You cannot add any more satellites. Your list is full.");
        }
        else {
            System.out.println("Choose a satellite name:");
            String satelliteName = input.nextLine();

            System.out.println("Choose the average temperature (Kelvin):");
            double averageTemperature = input.nextDouble();

            System.out.println("Choose the gravity (meters/second^2):");
            double gravity = input.nextDouble();

            System.out.println("Choose the rotation period around itself (days):");
            double rotation = input.nextDouble();

            System.out.println("Choose the equatorial radius (meters):");
            double equatorialRadius = input.nextDouble();

            System.out.println("Choose the mass of the planet (kg):");
            double mass = input.nextDouble();

            String inputPlanet;
            int optionPlanet;
            Planet myPlanet;

            if (numberPlanets < 2) {
                System.out.println("Selecting the only planet:");
                myPlanet = planets[0];
            }
            else {

                System.out.println("These are the planets to choose from:");
                for (int i = 0; i < numberPlanets; i++) {
                    System.out.println((i+1) + ". " + planets[i].getPlanetName());
                }

                System.out.println("Choose a planet:");
                inputPlanet = input.nextLine();

                while (!patternMatcher.find()) {
                    input = new Scanner(System.in);
                    System.out.println("Input " + inputPlanet + " is incorrect, try again:");
                    inputPlanet = input.nextLine();
                    patternMatcher = numberPattern.matcher(inputPlanet);
                }

                optionPlanet = Integer.parseInt(inputPlanet);

                if (optionPlanet < 0 || optionPlanet > numberPlanets) {
                    System.out.println("Option out of Range. Selecting latest planet: ...");
                    myPlanet = planets[numberPlanets-1];
                }

                else  {
                    System.out.println(planets[optionPlanet-1].getPlanetName() + " chosen, showing information about the planet:");
                    myPlanet = planets[optionPlanet];
                }
            }

            System.out.println("Choose the orbital period around the Planet:");
            double orbitPlanet = input.nextDouble();

            System.out.println("Choose the radius around the Planet (meters):");
            double radiusPlanet = input.nextDouble();

            satellites[numberSatellites] = new Satellite(satelliteName,averageTemperature,gravity,rotation,equatorialRadius,mass,myPlanet,orbitPlanet,radiusPlanet);
        }
        System.out.println("-----------------------------");
        System.out.println("This is the satellite:");
        System.out.println(satellites[numberSatellites].toString());
        numberSatellites++;
        pause();
    }
}