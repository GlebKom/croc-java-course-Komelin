package course.Komelin.task16.dao;

import java.sql.Connection;

public class PetDao {

    private final Connection connection;

    public PetDao(Connection connection) {
        this.connection = connection;
    }


}
