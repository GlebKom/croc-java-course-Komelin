package course.Komelin.task16.dao;

import course.Komelin.task15.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDao {

    private final Connection connection;

    public ClientDao(Connection connection) {
        this.connection = connection;
    }

    public Client createClient(Client client) throws SQLException {
        final String CREATE_CLIENT = "INSERT INTO Client VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT);
        preparedStatement.setInt(1, client.getId());
        preparedStatement.setString(2, client.getFirstName());
        preparedStatement.setString(3, client.getSecondName());
        preparedStatement.setString(4, client.getPhoneNumber());
        preparedStatement.execute();
    }

    public
}
