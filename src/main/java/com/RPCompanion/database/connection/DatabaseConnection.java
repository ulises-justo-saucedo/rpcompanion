package com.RPCompanion.database.connection;
import com.RPCompanion.database.configuration.DatabaseConfiguration;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;

import java.sql.*;
import java.util.logging.Logger;

public class DatabaseConnection {
    private final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());;
    private Connection connection;
    public DatabaseConnection() throws DatabaseAccessException, PropertiesFileException {
        this.connection = new DatabaseConfiguration().establishConnection();
        try {
            logger.info("Successfully connected to '"+connection.getCatalog()+"' database.\n");
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access database provided. Get catalog operation over connection failed.");
        }
    }
    public void closeConnection() throws DatabaseAccessException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access to the database provided. Close operation failed.");
        }
        try {
            logger.info("Successfully closed connection to '"+connection.getCatalog()+"' database.");
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access database provided. Get catalog operation over connection failed.");
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
