package course.Komelin.task15.csv.reader;

import course.Komelin.task15.model.Client;
import course.Komelin.task15.model.Pet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientCsvReader {

    public static List<Client> readUniqueClientsCsv(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Client> clients = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split(",");
            Client client = new Client(Integer.parseInt(words[0]),
                    words[1], words[2], words[3]);

            if (!clients.contains(client)) {
                clients.add(client);
            }

            Pet pet = new Pet(Integer.parseInt(words[4]), words[5], Integer.parseInt(words[6]));

            if (!clients.get(clients.indexOf(client)).getPets().contains(pet)) {
                clients.get(clients.indexOf(client)).getPets().add(pet);
            }
        }

        reader.close();
        return clients;
    }
}
