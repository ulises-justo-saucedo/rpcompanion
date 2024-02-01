package com.RPCompanion.database.connection;
import com.RPCompanion.database.configuration.DatabaseConfiguration;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

public class DatabaseConnection {
    private final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());;
    private Connection connection;
    public DatabaseConnection() {
        try {
            this.connection = new DatabaseConfiguration().establishConnection();
            logger.info("Successfully connected to '"+connection.getCatalog()+"' database.\n");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection(){
        try {
            connection.close();
            logger.info("Successfully closed connection to '"+connection.getCatalog()+"' database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
