package com.RPCompanion;
import com.RPCompanion.database.connection.DatabaseConnection;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;

public class App {
    public static void main(String[] args) throws PropertiesFileException, DatabaseAccessException {
        DatabaseConnection dbc = new DatabaseConnection();
        dbc.closeConnection();
    }
}
