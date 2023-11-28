package course.Komelin.task15;

import course.Komelin.task15.csv.reader.ClientCsvReader;
import course.Komelin.task15.csv.reader.PetCsvReader;
import course.Komelin.task15.database.DataBaseFiller;
import course.Komelin.task15.database.DateBaseCreator;
import course.Komelin.task15.model.Client;
import course.Komelin.task15.model.Pet;
import course.Komelin.task16.dao.ClientDao;
import course.Komelin.task16.dao.PetDao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try (Connection connection =
                     DriverManager.getConnection("jdbc:h2:mem:~/vet_clinic", "gleb", "")) {

            List<Client> clients = readClients(args[0]);
            List<Pet> pets = readPets(args[0]);

            DateBaseCreator dateBaseCreator = new DateBaseCreator(connection);
            dateBaseCreator.createClientTable();
            dateBaseCreator.createPetTable();
            dateBaseCreator.createClientsRelatedPets();

            DataBaseFiller dataBaseFiller = new DataBaseFiller(connection);
            dataBaseFiller.insertClients(clients);
            dataBaseFiller.insertPets(pets);
            dataBaseFiller.createRelations(clients);

            ClientDao clientDao = new ClientDao(connection);
            for (Client client : clientDao.getAllClients()) {
                System.out.println(client);
            }

            PetDao petDao = new PetDao(connection);
            for (Pet pet : petDao.getAllPets()) {
                System.out.println(pet);
            }

            for (Map.Entry<Client, List<Pet>> entry: clientDao.getAllClientsWithTheirPet().entrySet()) {
                System.out.println("\nВладелец: " + entry.getKey().getFirstName() + " " + entry.getKey().getSecondName());
                System.out.println("Питомцы: ");
                entry.getValue().forEach(System.out::println);
            }

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Client> readClients(String path) throws IOException {
        File file = new File(path);
        return ClientCsvReader.readUniqueClientsCsv(file);
    }

    private static List<Pet> readPets(String path) throws IOException {
        File file = new File(path);
        return PetCsvReader.readUniquePetsCsv(file);
    }
}
