package course.Komelin.task15.database;

import course.Komelin.task15.model.Client;
import course.Komelin.task15.model.Pet;

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
        String insertClient = "INSERT INTO Client VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertClient);
        for (Client client : clients) {
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getSecondName());
            preparedStatement.setString(4, client.getPhoneNumber());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

    public void insertPets(Collection<Pet> pets) throws SQLException {
        String insertPet = "INSERT INTO Pet VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertPet);
        for (Pet pet : pets) {
            preparedStatement.setInt(1, pet.getMedCardNumber());
            preparedStatement.setString(2, pet.getPetName());
            preparedStatement.setInt(3, pet.getAge());
            preparedStatement.setInt(4, pet.getOwnerId());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }
}
