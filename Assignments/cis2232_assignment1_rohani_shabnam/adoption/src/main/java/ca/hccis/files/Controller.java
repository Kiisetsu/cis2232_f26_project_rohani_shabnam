package ca.hccis.files;

import entity.Animal;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static final String EXIT = "X";

    public static final String MENU =
            "\nA) Add\n" +
                    "V) View\n" +
                    "X) Exit\n" +
                    "Enter your choice: ";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        FileManager fileManager = new FileManager();

        ArrayList<Animal> animals = fileManager.loadAnimals();

        String choice;

        do {
            System.out.print(MENU);
            choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {

                case "A":
                    addAnimal(scanner, animals, fileManager);
                    break;

                case "V":
                    viewAnimals(animals);
                    break;

                case "X":
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (!choice.equals("X"));


        scanner.close();
    }

    public static void addAnimal(
            Scanner scanner,
            ArrayList<Animal> animals,
            FileManager fileManager) {

        System.out.print("Enter animal ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();

        System.out.print("Enter animal sex: ");
        String sex = scanner.nextLine();

        System.out.print("Enter animal species: ");
        String species = scanner.nextLine();

        System.out.print("Enter animal colour: ");
        String colour = scanner.nextLine();

        System.out.print("Enter animal coat: ");
        String coat = scanner.nextLine();

        System.out.print("Enter animal age: ");
        double age = Double.parseDouble(scanner.nextLine());

        Animal animal = new Animal(
                id,
                name,
                sex,
                species,
                colour,
                coat,
                age
        );

        animals.add(animal);

        fileManager.saveAnimals(animals);

        System.out.println("Animal added successfully.");
    }

    public static void viewAnimals(ArrayList<Animal> animals) {

        if (animals.isEmpty()) {
            System.out.println("No animals have been added.");
            return;
        }

        System.out.println("\nAnimals:");

        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
