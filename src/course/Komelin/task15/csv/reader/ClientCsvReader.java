package course.Komelin.task15.csv.reader;

import course.Komelin.task15.model.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientCsvReader {

    public static List<Client> readClientsCsv(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Client> clients = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split(",");
            Client client = new Client(Integer.parseInt(words[0]),
                    words[1], words[2], words[3]);
            clients.add(client);
        }

        reader.close();
        return clients;
    }
}
