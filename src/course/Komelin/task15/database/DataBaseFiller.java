package course.Komelin.task15.database;

import course.Komelin.task15.model.Client;
import course.Komelin.task15.model.Pet;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DataBaseFiller {

    private final Connection connection;

    public DataBaseFiller(Connection connection) {
        this.connection = connection;
    }

    public void insertClients(Collection<Client> clients) throws SQLException {
        String insertClient = "INSERT INTO Client(first_name, second_name, phone_number) VALUES(?, ?, ?)";
        PreparedStatement preparedStatementClient = connection.prepareStatement(insertClient);

        for (Client client : clients) {
            preparedStatementClient.setString(1, client.getFirstName());
            preparedStatementClient.setString(2, client.getSecondName());
            preparedStatementClient.setString(3, client.getPhoneNumber());
            preparedStatementClient.addBatch();
        }

        preparedStatementClient.executeBatch();
    }

    public void insertPets(Collection<Pet> pets) throws SQLException {
        String insertPet = "INSERT INTO Pet(pet_name, age) VALUES(? ,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertPet);
        for (Pet pet : pets) {
            preparedStatement.setString(1, pet.getPetName());
            preparedStatement.setInt(2, pet.getAge());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

    public void createRelations(List<Client> clients) throws SQLException {
        String insertClientToPet = "INSERT INTO Client_to_pet VALUES(?, ?)";
        PreparedStatement preparedStatementClientToPet = connection.prepareStatement(insertClientToPet);

        for (Client client : clients) {
            for (Pet pet : client.getPets()) {
                preparedStatementClientToPet.setInt(1, client.getId());
                preparedStatementClientToPet.setInt(2, pet.getMedCardNumber());
                preparedStatementClientToPet.addBatch();
            }
        }

        preparedStatementClientToPet.executeBatch();

    }
}
