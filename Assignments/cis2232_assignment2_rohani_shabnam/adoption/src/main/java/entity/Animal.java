package entity;
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
        double speciesCost = 0;
        double coatCost = 0;
        double ageRate = 0;

        if (species.equalsIgnoreCase("cat")) {
            speciesCost = 200;

            if (age >= 0 && age < 1) {
                ageRate = 1.5;
            } else if (age >= 1 && age < 8) {
                ageRate = 1;
            } else if (age >= 8 && age < 15) {
                ageRate = 0.5;
            } else if (age >= 15) {
                ageRate = 0.25;
            }

        } else if (species.equalsIgnoreCase("dog")) {
            speciesCost = 500;

            if (age >= 0 && age < 1) {
                ageRate = 1.5;
            } else if (age >= 1 && age < 7) {
                ageRate = 1;
            } else if (age >= 7 && age < 12) {
                ageRate = 0.5;
            } else if (age >= 12) {
                ageRate = 0.25;
            }
        }

        if (coat.equalsIgnoreCase("short")) {
            coatCost = 50;
        } else if (coat.equalsIgnoreCase("medium")) {
            coatCost = 100;
        } else if (coat.equalsIgnoreCase("long")) {
            coatCost = 150;
        } else if (coat.equalsIgnoreCase("bald")) {
            coatCost = 200;
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

