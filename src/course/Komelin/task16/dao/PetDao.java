package course.Komelin.task16.dao;

import course.Komelin.task15.model.Client;
import course.Komelin.task15.model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PetDao {

    private final Connection connection;

    public PetDao(Connection connection) {
        this.connection = connection;
    }

    public List<Pet> getAllPets() throws SQLException {
        final String GET_ALL_PETS = "SELECT * FROM Pet";

        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PETS);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Pet> pets = new ArrayList<>();

        while (resultSet.next()) {
            Pet pet = new Pet();
            pet.setMedCardNumber(resultSet.getInt(1));
            pet.setPetName(resultSet.getString(2));
            pet.setAge(resultSet.getInt(3));
            pets.add(pet);
        }

        return pets;
    }

    public Pet createPet(String name, Integer age, List<Client> clients) throws SQLException {
        final String CREATE_PET = "INSERT INTO Pet(pet_name, age) VALUES(?, ?)";
        final String CREATE_RELATIONS = "INSERT INTO Client_to_pet VALUES(?, ?)";

        PreparedStatement preparedStatementCreatePet = connection.prepareStatement(CREATE_PET,
                Statement.RETURN_GENERATED_KEYS);
        PreparedStatement preparedStatementCreateRelations = connection.prepareStatement(CREATE_RELATIONS);

        preparedStatementCreatePet.setString(1, name);
        preparedStatementCreatePet.setInt(2, age);

        connection.setAutoCommit(false);
        preparedStatementCreatePet.executeUpdate();
        ResultSet resultSet = preparedStatementCreatePet.getGeneratedKeys();
        resultSet.next();

        int petId = resultSet.getInt(1);
        Pet pet = new Pet();

        for (Client client : clients) {
            preparedStatementCreateRelations.setInt(1, client.getId());
            preparedStatementCreateRelations.setInt(2, petId);
            preparedStatementCreateRelations.addBatch();
            pet.getOwners().add(client);
        }
        preparedStatementCreateRelations.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);

        pet.setMedCardNumber(petId);
        pet.setPetName(name);
        pet.setAge(age);

        return pet;
    }

    public Pet findPet(Integer medicalCardNumber) throws SQLException {
        final String FIND_PET = "SELECT * FROM Pet WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_PET);
        preparedStatement.setInt(1, medicalCardNumber);

        connection.setAutoCommit(false);
        ResultSet resultSet = preparedStatement.executeQuery();
        Pet pet = null;

        if (resultSet.next()) {
            pet = new Pet(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3));

            List<Client> clients = getOwnersOfPet(pet);

            for (Client client : clients) {
                pet.getOwners().add(client);
            }
        }
        connection.commit();
        connection.setAutoCommit(true);

        return pet;
    }

    public Pet updatePet(Pet pet) throws SQLException {
        final String UPDATE_PET = "UPDATE Pet SET pet_name = ?, age = ? WHERE id = ?";
        final String DELETE_OLD_RELATIONS = "DELETE FROM Client_to_pet WHERE pet_id = ?";
        final String CREATE_NEW_RELATIONS = "INSERT INTO Client_to_pet VALUES(?, ?)";

        // обновляем информацию о питомце
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PET);
        preparedStatement.setString(1, pet.getPetName());
        preparedStatement.setInt(2, pet.getAge());
        preparedStatement.setInt(3, pet.getMedCardNumber());
        preparedStatement.executeUpdate();

        // проверяем, изменились ли хозяева
        List<Client> oldOwners = getOwnersOfPet(pet);
        if (!new HashSet<>(oldOwners).containsAll(pet.getOwners())) {
            PreparedStatement preparedStatementDelete = connection.prepareStatement(DELETE_OLD_RELATIONS);
            PreparedStatement preparedStatementCreate = connection.prepareStatement(CREATE_NEW_RELATIONS);

            preparedStatementDelete.setInt(1, pet.getMedCardNumber());
            preparedStatementDelete.execute();
            for (Client client : pet.getOwners()) {
                preparedStatementCreate.setInt(1, client.getId());
                preparedStatementCreate.setInt(2, pet.getMedCardNumber());
                preparedStatementCreate.addBatch();
            }

            preparedStatementCreate.executeBatch();
        }
        connection.commit();
        connection.setAutoCommit(true);

        return pet;
    }

    public void deletePet(Integer medicalCardNumber) throws SQLException {
        final String DELETE_PET = "DELETE FROM Pet WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PET);
        preparedStatement.setInt(1, medicalCardNumber);
        preparedStatement.execute();
    }

    public List<Pet> getAllPetsOf(Client client) throws SQLException {
        final String GET_ALL_PETS_OF_CLIENT = "SELECT pet_id, pet_name, age " +
                "FROM Client_to_pet LEFT JOIN Pet ON pet_id = id WHERE client_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PETS_OF_CLIENT);
        preparedStatement.setInt(1, client.getId());
        List<Pet> pets = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Pet pet = new Pet(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3));
            pets.add(pet);
        }

        return pets;
    }

    private List<Client> getOwnersOfPet(Pet pet) throws SQLException {
        final String GET_OWNERS = "SELECT client_id, first_name, second_name, phone_number " +
                "FROM Client_to_pet LEFT JOIN Client on client_id = id WHERE pet_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(GET_OWNERS);
        preparedStatement.setInt(1, pet.getMedCardNumber());
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
}
