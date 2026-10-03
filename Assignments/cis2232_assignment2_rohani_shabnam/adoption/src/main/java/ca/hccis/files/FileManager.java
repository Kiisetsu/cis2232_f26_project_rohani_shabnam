package ca.hccis.files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Animal;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileManager {

    public static final String DIRECTORY = "C:\\cis2232";
    public static final String FILE = DIRECTORY + "\\data_rohani_shabnam.json";

    public static void createDirectory() {

        File directory = new File(DIRECTORY);

        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static void saveAnimals(ArrayList<Animal> animals) {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(FILE)) {

            gson.toJson(animals, writer);

        } catch (IOException e) {

            System.out.println("Error saving file.");
        }
    }

    public static ArrayList<Animal> loadAnimals() {

        Gson gson = new Gson();

        File file = new File(FILE);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(FILE)) {

            Type type =
                    new TypeToken<ArrayList<Animal>>() {}.getType();

            ArrayList<Animal> animals =
                    gson.fromJson(reader, type);

            if (animals == null) {
                return new ArrayList<>();
            }

            return animals;

        } catch (IOException e) {

            System.out.println("Error reading file.");
            return new ArrayList<>();
        }
    }
}

