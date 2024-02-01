package com.RPCompanion;
import com.RPCompanion.database.connection.DatabaseConnection;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.DatabasePropertiesFileException;

public class App {
    public static void main(String[] args) throws DatabasePropertiesFileException, DatabaseAccessException {
        DatabaseConnection dbc = new DatabaseConnection();
        dbc.closeConnection();
    }
}
