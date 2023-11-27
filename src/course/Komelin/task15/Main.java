package course.Komelin.task15;

import course.Komelin.task15.csv.reader.ClientCsvReader;
import course.Komelin.task15.csv.reader.PetCsvReader;
import course.Komelin.task15.database.DataBaseFiller;
import course.Komelin.task15.database.DateBaseCreator;
import course.Komelin.task15.model.Client;
import course.Komelin.task15.model.Pet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (Connection connection =
                     DriverManager.getConnection("jdbc:h2:mem:~/vet_clinic", "gleb", "")) {

            List<Client> clients = readClients(args[0]);
            List<Pet> pets = readPets(args[0]);

            DateBaseCreator dateBaseCreator = new DateBaseCreator(connection);
            dateBaseCreator.createClientTable();
            dateBaseCreator.createPetTable();

            DataBaseFiller dataBaseFiller = new DataBaseFiller(connection);
            dataBaseFiller.insertClients(new HashSet<>(clients));
            dataBaseFiller.insertPets(new HashSet<>(pets));


        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Client> readClients(String path) throws IOException {
        File file = new File(path);
        return ClientCsvReader.readClientsCsv(file);
    }

    private static List<Pet> readPets(String path) throws IOException {
        File file = new File(path);
        return PetCsvReader.readPetsCsv(file);
    }
}
