package course.Komelin.task15.csv.reader;

import course.Komelin.task15.model.Client;
import course.Komelin.task15.model.Pet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PetCsvReader {

    public static List<Pet> readUniquePetsCsv(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Pet> pets = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split(",");
            Pet pet = new Pet(Integer.parseInt(words[4]),
                    words[5],
                    Integer.parseInt(words[6]));

            Client client = new Client(Integer.parseInt(words[0]),
                    words[1], words[2], words[3]);

            if (!pets.contains(pet)) {
                pets.add(pet);
            }

            if (!pets.get(pets.indexOf(pet)).getOwners().contains(client)) {
                pets.get(pets.indexOf(pet)).getOwners().add(client);
            }
        }

        reader.close();
        return pets;
    }
}
