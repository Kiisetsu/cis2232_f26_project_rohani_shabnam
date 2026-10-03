package bo;

import entity.Animal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for AnimalBO.
 */
public class AnimalBOTest {

    /**
     * Tests the calculate method for a young cat with a short coat.
     * This test was created following a test-driven development approach.
     */
    @Test
    void testCalculateYoungCat() {
        Animal animal = new Animal(
                1,
                "Whiskers",
                "Female",
                "Cat",
                "Black",
                "Short",
                0.5
        );

        AnimalBO animalBO = new AnimalBO();

        double result = animalBO.calculate(animal);

        assertEquals(375.0, result, 0.001);
    }

    /**
     * Tests the calculate method for an adult dog with a long coat.
     * This test was created following a test-driven development approach.
     */
    @Test
    void testCalculateAdultDog() {
        Animal animal = new Animal(
                2,
                "Buddy",
                "Male",
                "Dog",
                "Brown",
                "Long",
                5
        );

        AnimalBO animalBO = new AnimalBO();

        double result = animalBO.calculate(animal);

        assertEquals(650.0, result, 0.001);
    }

    /**
     * Tests the calculate method for a senior dog with a medium coat.
     * This test was created following a test-driven development approach.
     */
    @Test
    void testCalculateSeniorDog() {
        Animal animal = new Animal(
                3,
                "Max",
                "Male",
                "Dog",
                "White",
                "Medium",
                9
        );

        AnimalBO animalBO = new AnimalBO();

        double result = animalBO.calculate(animal);

        assertEquals(300.0, result, 0.001);
        assertTrue(result > 0);
    }
}

