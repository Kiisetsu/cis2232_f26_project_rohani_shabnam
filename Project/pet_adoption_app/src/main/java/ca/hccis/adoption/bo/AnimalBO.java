package ca.hccis.adoption.bo;

import ca.hccis.adoption.entity.Animal;

/**
 * Business object for performing calculations related to animals.
 */
public class AnimalBO {

    /**
     * Calculates the adoption fee for an animal.
     * Already input in Animal.java class, also here for testing.
     *
     * Fee = (speciesCost + coatCost) * ageRate
     *
     * Species costs:
     * Cat = 200
     * Dog = 500
     *
     * Coat costs:
     * Short = 50
     * Medium = 100
     * Long = 150
     * Bald = 200
     *
     * Age rates:
     * Young = 1.5
     * Adult = 1.0
     * Senior = 0.5
     * Geriatric = 0.25
     *
     * @param animal the animal being calculated
     * @return the animal's adoption fee
     */
    public double calculate(Animal animal) {
        double speciesCost;
        double ageRate;
        double age = animal.getAge();

        if ("cat".equalsIgnoreCase(animal.getSpecies())) {
            speciesCost = 200;
            if (age >= 0 && age < 1)        ageRate = 1.5;
            else if (age >= 1 && age < 8)   ageRate = 1.0;
            else if (age >= 8 && age < 15)  ageRate = 0.5;
            else if (age >= 15 && age <= 20) ageRate = 0.25;
            else throw new IllegalArgumentException("Cat age must be between 0 and 20.");
        } else if ("dog".equalsIgnoreCase(animal.getSpecies())) {
            speciesCost = 500;
            if (age >= 0 && age < 1)        ageRate = 1.5;
            else if (age >= 1 && age < 7)   ageRate = 1.0;
            else if (age >= 7 && age < 12)  ageRate = 0.5;
            else if (age >= 12 && age <= 15) ageRate = 0.25;
            else throw new IllegalArgumentException("Dog age must be between 0 and 15.");
        } else {
            throw new IllegalArgumentException("Species must be cat or dog.");
        }

        double coatCost;
        if ("short".equalsIgnoreCase(animal.getCoat()))       coatCost = 50;
        else if ("medium".equalsIgnoreCase(animal.getCoat())) coatCost = 100;
        else if ("long".equalsIgnoreCase(animal.getCoat()))   coatCost = 150;
        else if ("bald".equalsIgnoreCase(animal.getCoat()))   coatCost = 200;
        else throw new IllegalArgumentException("Coat must be short, medium, long or bald.");

        return (speciesCost + coatCost) * ageRate;
    }
}


