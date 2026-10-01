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

