package ca.hccis.files;

import ca.hccis.files.FileManager;
import entity.Animal;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;

/**
 * Author: Shabnam Rohani
 * Class: CIS2232
 * Description: Pet Adoption App Project for the semester. Allows user to enter animal inputs and later calculate adoption fees. (to be continued)
 */

public class Controller {
    private static AtomicInteger animalCount;
    private static final Object lock = new Object();

    public static final String EXIT = "X";

    public static final String MENU =
            "\nA) Add\n" +
                    "V) View\n" +
                    "X) Exit\n" +
                    "Enter your choice: ";

    public static void main(String[] args) {

        FileManager.createDirectory();

        Scanner scanner = new Scanner(System.in);

        FileManager fileManager = new FileManager();

        ArrayList<Animal> animals = fileManager.loadAnimals();

        animalCount = new AtomicInteger(animals.size());

        System.out.println("Starting animal count: " + animalCount.get());

        Thread consoleThread = new Thread(() -> runConsole(scanner, animals, fileManager), "Console Thread");
        Thread dialogThread = new Thread(() -> runDialog(animals, fileManager), "DialogThread");
        consoleThread.start();
        dialogThread.start();

        try {
            consoleThread.join();
            dialogThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            scanner.close();
        }

    }

    private static void runDialog(ArrayList<Animal> animals, FileManager fileManager) {

        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "Enter animal ID (Cancel to stop):", "Add Animal", JOptionPane.QUESTION_MESSAGE);

                if (input == null) {
                    break;
                }

                int id = Integer.parseInt(input.trim());

                String name = JOptionPane.showInputDialog("Enter animal name:");
                if (name == null) break;

                String sex = JOptionPane.showInputDialog("Enter animal sex:");
                if (sex == null) break;

                String species = JOptionPane.showInputDialog("Enter animal species:");
                if (species == null) break;

                String colour = JOptionPane.showInputDialog("Enter animal colour:");
                if (colour == null) break;

                String coat = JOptionPane.showInputDialog("Enter animal coat:");
                if (coat == null) break;

                String ageInput = JOptionPane.showInputDialog("Enter animal age:");
                if (ageInput == null) break;

                double age = Double.parseDouble(ageInput.trim());

                Animal animal = new Animal(id, name, sex, species, colour, coat, age);

                double adoptionFee = animal.calculate();

                synchronized (lock) {
                    animals.add(animal);
                    fileManager.saveAnimals(animals);

                    int total = animalCount.incrementAndGet();

                    System.out.println("Animal added through dialog thread");
                    System.out.println(animal);
                    System.out.println("Adoption fee: " + adoptionFee);
                    System.out.println("Total Animals: " + total);
                }
                JOptionPane.showMessageDialog(null, "Animal added successfully!\n" + animal + "\nAdoption fee: " + adoptionFee + "\nTotal animals: " + animalCount.get());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number. Please enter a valid ID and age.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private static void runConsole(Scanner scanner, ArrayList<Animal> animals, FileManager fileManager) {
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

        } while (!choice.equals(EXIT));

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

        double adoptionFee = animal.calculate();

        synchronized (lock) {
            animals.add(animal);
            fileManager.saveAnimals(animals);
            int total = animalCount.incrementAndGet();

            System.out.println("Animal added successfully.");
            System.out.println(animal);
            System.out.println("Adoption fee: " + adoptionFee);
            System.out.println("Total Animals: " + total);
        }
    }

    public static void viewAnimals(ArrayList<Animal> animals) {

        synchronized (lock) {
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
}
