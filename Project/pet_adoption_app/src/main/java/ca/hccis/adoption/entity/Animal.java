package ca.hccis.adoption.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name can be at most 50 characters")
    private String name;

    @NotBlank(message = "Sex is required")
    @Pattern(regexp = "(?i)^(male|female|m|f)$", message = "Sex must be Male or Female (M or F)")
    private String sex;

    @NotBlank(message = "Species is required")
    @Pattern(regexp = "(?i)^(cat|dog)$", message = "Species must be Cat or Dog")
    private String species;

    @NotBlank(message = "Colour is required")
    @Size(max = 30, message = "Colour can be at most 30 characters")
    private String colour;

    @NotBlank(message = "Coat is required")
    @Pattern(regexp = "(?i)^(short|medium|long|bald)$", message = "Coat must be Short, Medium, Long or Bald")
    private String coat;

    @DecimalMin(value = "0.0", message = "Age cannot be negative")
    @DecimalMax(value = "20.0", message = "Age cannot be more than 20")
    private double age;

    // Required by JPA and by Spring when binding the form
    public Animal() {
    }

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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getColour() { return colour; }
    public void setColour(String colour) { this.colour = colour; }

    public String getCoat() { return coat; }
    public void setCoat(String coat) { this.coat = coat; }

    public double getAge() { return age; }
    public void setAge(double age) { this.age = age; }

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
