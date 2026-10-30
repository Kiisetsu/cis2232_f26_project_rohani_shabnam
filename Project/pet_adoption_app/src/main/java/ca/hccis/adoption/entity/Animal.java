package ca.hccis.adoption.entity;

public class Animal {

    private int id;
    private String name;
    private String sex;
    private String species;
    private String colour;
    private String coat;
    private double age;

    public Animal(int id, String name, String sex, String species,
                  String colour, String coat, double age) {

        this.id = id;
        this.name = name;
        this.sex = sex;
        this.species = species;
        this.colour = colour;
        this.coat = coat;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getSpecies() {
        return species;
    }

    public String getColour() {
        return colour;
    }

    public String getCoat() {
        return coat;
    }

    public double getAge() {
        return age;
    }

    //Calculations for adoption fees.
    public double calculate() {
        double speciesCost;
        double ageRate;

        if ("cat".equalsIgnoreCase(species)) {
            speciesCost = 200;
            if (age >= 0 && age < 1) {
                ageRate = 1.5;
            } else if (age >= 1 && age < 8) {
                ageRate = 1;
            } else if (age >= 8 && age < 15) {
                ageRate = 0.5;
            } else if (age >= 15 && age <= 20) {
                ageRate = 0.25;
            } else {
                throw new IllegalArgumentException("Cat age must be between 0 and 20.");
            }
        } else if ("dog".equalsIgnoreCase(species)) {
            speciesCost = 500;
            if (age >= 0 && age < 1) {
                ageRate = 1.5;
            } else if (age >= 1 && age < 7) {
                ageRate = 1;
            } else if (age >= 7 && age < 12) {
                ageRate = 0.5;
            } else if (age >= 12 && age <= 15) {
                ageRate = 0.25;
            } else {
                throw new IllegalArgumentException("Dog age must be between 0 and 15.");
            }
        } else {
            throw new IllegalArgumentException("Species must be cat or dog.");
        }

        double coatCost;
        if ("short".equalsIgnoreCase(coat)) {
            coatCost = 50;
        } else if ("medium".equalsIgnoreCase(coat)) {
            coatCost = 100;
        } else if ("long".equalsIgnoreCase(coat)) {
            coatCost = 150;
        } else if ("bald".equalsIgnoreCase(coat)) {
            coatCost = 200;
        } else {
            throw new IllegalArgumentException("Coat must be short, medium, long or bald.");
        }

        return (speciesCost + coatCost) * ageRate;
    }


    @Override
    public String toString() {
        return "ID: " + id
                + ", Name: " + name
                + ", Sex: " + sex
                + ", Species: " + species
                + ", Colour: " + colour
                + ", Coat: " + coat
                + ", Age: " + age;
    }
}
