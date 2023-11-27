package course.Komelin.task15.csv.reader;

import course.Komelin.task15.model.Pet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PetCsvReader {

    public static List<Pet> readPetsCsv(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Pet> pets = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split(",");
            Pet pet = new Pet(Integer.parseInt(words[4]),
                    words[5],
                    Integer.parseInt(words[6]),
                    Integer.parseInt(words[0]));

            pets.add(pet);
        }

        reader.close();
        return pets;
    }
}
