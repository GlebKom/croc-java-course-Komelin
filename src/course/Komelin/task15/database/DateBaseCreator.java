package course.Komelin.task15.database;

import course.Komelin.task15.model.Client;
import course.Komelin.task15.model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DateBaseCreator {

    private final Connection connection;

    public DateBaseCreator(Connection connection) {
        this.connection = connection;
    }

    public void createClientTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("""
                          CREATE TABLE IF NOT EXISTS Client(
                          id int PRIMARY KEY,
                          first_name varchar NOT NULL,
                          second_name varchar NOT NULL,
                          phone_number varchar UNIQUE)""");
    }

    public void createPetTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("""
                          CREATE TABLE IF NOT EXISTS Pet(
                          id int PRIMARY KEY,
                          pet_name varchar NOT NULL,
                          age int, CHECK (age >= 0),
                          owner_id int,
                          FOREIGN KEY (owner_id) REFERENCES Client(id) ON DELETE CASCADE)""");
    }
}
