package course.Komelin.task16.dao;

import course.Komelin.task15.csv.reader.ClientCsvReader;
import course.Komelin.task15.csv.reader.PetCsvReader;
import course.Komelin.task15.database.DataBaseFiller;
import course.Komelin.task15.database.DateBaseCreator;
import course.Komelin.task15.model.Client;
import course.Komelin.task15.model.Pet;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestClientDao {

    static DateBaseCreator dateBaseCreator;
    static DataBaseFiller dataBaseFiller;
    static ClientDao clientDao;
    static List<Client> clientsBeforeTests;
    static Connection connection;

    @BeforeAll
    public static void initDataBase() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:h2:mem:~/vet_clinic", "gleb", "");
        dateBaseCreator = new DateBaseCreator(connection);
        dataBaseFiller = new DataBaseFiller(connection);

        dateBaseCreator.createClientTable();
        dateBaseCreator.createPetTable();
        dateBaseCreator.createClientsRelatedPets();

        List<Client> clients =
                ClientCsvReader.readUniqueClientsCsv(new File("src/course/Komelin/task15/test.csv"));
        List<Pet> pets =
                PetCsvReader.readUniquePetsCsv(new File("src/course/Komelin/task15/test.csv"));

        dataBaseFiller.insertClients(clients);
        dataBaseFiller.insertPets(pets);
        dataBaseFiller.createRelations(clients);

        clientDao = new ClientDao(connection);
        clientsBeforeTests = clientDao.getAllClients();
    }


    @Test
    @Order(1)
    public void testCreateClient() throws SQLException {

        // Телефон уникальный
        Client client1 = new Client();
        client1.setId(13); // Именно такой айдишник должен быть, т.к. предыдущий был 12
        client1.setFirstName("Глеб");
        client1.setSecondName("Комелин");
        client1.setPhoneNumber("+77777777777");
        clientDao.createClient(client1);

        clientsBeforeTests.add(client1);

        assertEquals(clientsBeforeTests, clientDao.getAllClients());

        // Телефон уже есть
        Client client2 = new Client();
        client2.setFirstName("Миша");
        client2.setSecondName("Иванов");
        client2.setPhoneNumber("+79995554433");

        assertThrows(SQLException.class, () -> clientDao.createClient(client2));
    }

    @Test
    @Order(2)
    public void testFindClient() throws SQLException {
        // Если нашли человека
        Client clientFromDao = clientDao.findClient(12);
        Client clientHardCoded = new Client(12,
                "Цветов",
                "Аркадий",
                "+79655002030");

        assertEquals(clientHardCoded, clientFromDao);

        // Если такого человека нет
        Client clientFromDao2 = clientDao.findClient(14);
        assertNull(clientFromDao2);
    }

    @Test
    @Order(3)
    public void testUpdateClient() throws SQLException {
        Client oldClient = clientDao.findClient(12);
        Client newClient = new Client(12, "Цветов", "Григорий", "+78888888888");
        clientDao.updateClient(newClient);
        assertEquals(newClient, clientDao.findClient(12));
        clientDao.updateClient(oldClient);
    }

    @Test
    @Order(4)
    public void testFindClientPhoneNumberByPet() throws SQLException {
        // Существующий питомец в бд
        PetDao petDao = new PetDao(connection);
        Pet pet = petDao.findPet(1);
        List<String> clientsPhoneNumber = clientDao.findClientPhoneNumbersBy(pet);
        List<String> realPhoneNumber = new ArrayList<>(List.of("+79995554433", "+79996654120"));
        assertEquals(realPhoneNumber, clientsPhoneNumber);

        // Несуществующий питомец в бд
        Pet pet1 = new Pet(15, "Бублик", 3);
        List<String> clientPhoneNumber1 = clientDao.findClientPhoneNumbersBy(pet1);
        assertEquals(new ArrayList<>(), clientPhoneNumber1);
    }

    @Test
    @Order(5)
    void testDeleteClient() throws SQLException {
        List<Client> clientsWithFirstRemoved = new ArrayList<>(clientsBeforeTests);
        clientsWithFirstRemoved.remove(clientDao.findClient(1));
        clientDao.deleteClient(1);
        assertEquals(clientsWithFirstRemoved, clientDao.getAllClients());
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

}
