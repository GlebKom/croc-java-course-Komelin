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
public class TestPetDao {
    static DateBaseCreator dateBaseCreator;
    static DataBaseFiller dataBaseFiller;
    static Connection connection;
    static List<Pet> petsBeforeTest;
    static PetDao petDao;
    static ClientDao clientDao;
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

        petDao = new PetDao(connection);
        clientDao = new ClientDao(connection);

        petsBeforeTest = petDao.getAllPets();
    }

    @Test
    @Order(1)
    void testCreatePet() throws SQLException {
        Pet petCreated = new Pet();
        petCreated.setMedCardNumber(14); // именно такой id сгенерируется на стороне бд
        petCreated.setPetName("Бублик");
        petCreated.setAge(3);
        Client client1 = clientDao.findClient(1);
        Client client2 = clientDao.findClient(2);
        petCreated.setOwners(new ArrayList<>(List.of(client1, client2)));

        Pet petFromDb = petDao.createPet("Бублик", 3, new ArrayList<>(List.of(client1, client2)));
        petsBeforeTest.add(petCreated);

        assertEquals(petsBeforeTest, petDao.getAllPets());
    }

    @Test
    @Order(2)
    void testFindPet() throws SQLException {
        // Если такой питомец есть в БД
        Pet petHardCoded = new Pet();
        petHardCoded.setMedCardNumber(1);
        petHardCoded.setPetName("Котик");
        petHardCoded.setAge(2);
        petHardCoded.setOwners(new ArrayList<>(List.of(clientDao.findClient(1), clientDao.findClient(10))));

        Pet petFromDb = petDao.findPet(1);

        assertEquals(petHardCoded, petFromDb);

        // Если питомца нет в БД
        Pet pet = petDao.findPet(19);
        assertNull(pet);
    }

    @Test
    @Order(3)
    void testUpdatePet() throws SQLException {
        Pet petBeforeUpdate = petDao.findPet(3);
        Pet updatedPet = new Pet();
        updatedPet.setMedCardNumber(3);
        updatedPet.setPetName("Котяра");
        updatedPet.setAge(3);
        updatedPet.setOwners(new ArrayList<>(List.of(clientDao.findClient(3), clientDao.findClient(6))));

        petDao.updatePet(updatedPet);

        assertEquals(updatedPet, petDao.findPet(3));

        // вернем обратно, чтобы дальше тесты отработали нормально без лишних танцев с бубном
        petDao.updatePet(petBeforeUpdate);
    }

    @Test
    @Order(4)
    void testDeletePet() throws SQLException {
        List<Pet> petsWithFirstRemoved = new ArrayList<>(petsBeforeTest);
        petsWithFirstRemoved.remove(petDao.findPet(1));
        petDao.deletePet(1);

        assertEquals(petsWithFirstRemoved, petDao.getAllPets());
    }

    @Test
    @Order(5)
    void testGetAllPetsOfClient() throws SQLException {
        List<Pet> expectedPet = new ArrayList<>();
        expectedPet.add(petDao.findPet(4));
        expectedPet.add(petDao.findPet(7));
        Client client = clientDao.findClient(4);
        assertEquals(expectedPet, petDao.getAllPetsOf(client));
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
