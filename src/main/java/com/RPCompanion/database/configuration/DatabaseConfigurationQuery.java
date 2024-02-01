package com.RPCompanion.database.configuration;
import com.RPCompanion.exceptions.DatabaseAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConfigurationQuery {
    private final Logger logger = Logger.getLogger(DatabaseConfigurationQuery.class.getName());;
    private Connection connection;
    public DatabaseConfigurationQuery(Connection connection){
        this.connection = connection;
    }
    public void setUpDatabase(String databaseName) throws DatabaseAccessException {
        createDatabase(databaseName);
        createRPCharacterTable();
    }
    private void createDatabase(String databaseName) throws DatabaseAccessException {
        String query = "CREATE DATABASE "+databaseName;
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
            connection.setCatalog(databaseName); //Now point to the DB recently created
            logger.info("Database '"+databaseName+"' created successfully.\n");
        } catch (SQLException e) {
            try {
                connection.setCatalog(databaseName); //Now point to the existent DB
            } catch (SQLException ex) {
                throw new DatabaseAccessException("Couldn't access to database provided. Set catalog operation over database failed.");
            }
            logger.info("Database '"+databaseName+"' already exists. No need to create it again.\n");
        }
    }
    private void createRPCharacterTable(){
        String query = "CREATE TABLE rpcharacter(id INT,name VARCHAR(255),surname VARCHAR(255),birth_date DATE,age INT,history VARCHAR(255),aspect VARCHAR(255),primary key(id))";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
            logger.info("Table 'rpcharacter' created successfully.\n");
        } catch (SQLException e) {
            logger.info("Table 'rpcharacter' already exists. No need to create it again.\n");
        }
    }
}
