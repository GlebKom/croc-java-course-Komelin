package course.Komelin.task16.dao;

import course.Komelin.task15.model.Client;
import course.Komelin.task15.model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientDao {

    private final Connection connection;

    public ClientDao(Connection connection) {
        this.connection = connection;
    }

    public Client createClient(Client client) throws SQLException {
        final String CREATE_CLIENT = "INSERT INTO Client(first_name, second_name, phone_number) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT);
        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getSecondName());
        preparedStatement.setString(3, client.getPhoneNumber());
        preparedStatement.execute();

        return client;
    }

    public Client findClient(Integer id) throws SQLException {
        final String FIND_CLIENT = "SELECT * FROM Client WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        Client client = null;

        if (resultSet.next()) {
            client = new Client();
            client.setId(resultSet.getInt(1));
            client.setFirstName(resultSet.getString(2));
            client.setSecondName(resultSet.getString(3));
            client.setPhoneNumber(resultSet.getString(4));
        }

        return client;
    }

    public Client updateClient(Client client) throws SQLException {
        final String UPDATE_CLIENT = "UPDATE Client SET first_name = ?, second_name = ?, phone_number = ?" +
                " WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT);
        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getSecondName());
        preparedStatement.setString(3, client.getPhoneNumber());
        preparedStatement.setInt(4, client.getId());

        preparedStatement.execute();
        return client;
    }

    public void deleteClient(Integer id) throws SQLException {
        final String DELETE_CLIENT = "DELETE FROM Client WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Client> getAllClients() throws SQLException {
        final String GET_ALL_CLIENTS = "SELECT * FROM Client";
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLIENTS);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Client> clients = new ArrayList<>();
        while (resultSet.next()) {
            Client client = new Client();
            client.setId(resultSet.getInt(1));
            client.setFirstName(resultSet.getString(2));
            client.setSecondName(resultSet.getString(3));
            client.setPhoneNumber(resultSet.getString(4));
            clients.add(client);
        }

        return clients;
    }

    public HashMap<Client, List<Pet>> getAllClientsWithTheirPet() throws SQLException {
        final String CLIENT_WITH_PET = "SELECT client_id, first_name, second_name, phone_number, pet_id, pet_name, age " +
                "FROM Client_to_pet LEFT JOIN Pet ON pet_id = Pet.id LEFT JOIN Client on client_id = Client.id";

        PreparedStatement preparedStatement = connection.prepareStatement(CLIENT_WITH_PET);
        ResultSet resultSet = preparedStatement.executeQuery();

        HashMap<Client, List<Pet>> clientPetHashMap = new HashMap<>();

        while (resultSet.next()) {
            Client client = new Client(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));

            Pet pet = new Pet(resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getInt(7));

            if (!clientPetHashMap.containsKey(client)) {
                clientPetHashMap.put(client, new ArrayList<>());
            }

            clientPetHashMap.get(client).add(pet);
        }

        return clientPetHashMap;
    }

    public List<String> findClientPhoneNumbersBy(Pet pet) throws SQLException {
        final String PHONE_NUMBER_BY_PET = "SELECT phone_number " +
                "FROM Client_to_pet LEFT JOIN Client ON client_id = id " +
                "WHERE pet_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(PHONE_NUMBER_BY_PET);
        preparedStatement.setInt(1, pet.getMedCardNumber());

        List<String> phoneNumbers = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            phoneNumbers.add(resultSet.getString(1));
        }

        return phoneNumbers;
    }
}
