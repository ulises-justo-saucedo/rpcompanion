package com.RPCompanion.database.connection;
import com.RPCompanion.database.configuration.DatabaseConfiguration;
import com.RPCompanion.database.querys.DatabaseQuery;
import java.io.IOException;
import java.sql.*;
public class DatabaseConnection {
    private Connection connection;
    private DatabaseQuery databaseQuery;
    public DatabaseConnection() {
        try {
            this.connection = new DatabaseConfiguration().establishConnection();
            this.databaseQuery = new DatabaseQuery(this.connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public DatabaseQuery getDatabaseQuery() {
        return databaseQuery;
    }
}
