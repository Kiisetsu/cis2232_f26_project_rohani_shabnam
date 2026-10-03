package bo;

import entity.Animal;

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

        double speciesCost = 0;
        double coatCost = 0;
        double ageRate = 0;

        if (animal.getSpecies().equalsIgnoreCase("cat")) {
            speciesCost = 200;

            if (animal.getAge() < 1) {
                ageRate = 1.5;
            } else if (animal.getAge() < 8) {
                ageRate = 1.0;
            } else if (animal.getAge() < 15) {
                ageRate = 0.5;
            } else {
                ageRate = 0.25;
            }

        } else if (animal.getSpecies().equalsIgnoreCase("dog")) {
            speciesCost = 500;

            if (animal.getAge() < 1) {
                ageRate = 1.5;
            } else if (animal.getAge() < 7) {
                ageRate = 1.0;
            } else if (animal.getAge() < 12) {
                ageRate = 0.5;
            } else {
                ageRate = 0.25;
            }
        }

        if (animal.getCoat().equalsIgnoreCase("short")) {
            coatCost = 50;
        } else if (animal.getCoat().equalsIgnoreCase("medium")) {
            coatCost = 100;
        } else if (animal.getCoat().equalsIgnoreCase("long")) {
            coatCost = 150;
        } else if (animal.getCoat().equalsIgnoreCase("bald")) {
            coatCost = 200;
        }

        return (speciesCost + coatCost) * ageRate;
    }
}

