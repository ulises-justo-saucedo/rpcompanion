package com.RPCompanion.database.configuration;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.fileloader.PropertiesFileLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

public class DatabaseConfigurationQuery {
    private final Logger logger = Logger.getLogger(DatabaseConfigurationQuery.class.getName());;
    private final String QUERIES_FILE_ROUTE = "src/main/resources/database/configuration-queries.properties";
    private Connection connection;
    private HashMap<String,PreparedStatement> queries;
    public DatabaseConfigurationQuery(Connection connection) throws PropertiesFileException, DatabaseAccessException {
        this.connection = connection;
        this.queries = PropertiesFileLoader.loadQueries(this.connection,PropertiesFileLoader.loadPropertiesFile(QUERIES_FILE_ROUTE));
    }
    public void setUpDatabase(String databaseName) throws DatabaseAccessException, PropertiesFileException {
        createDatabase(databaseName);
        createRPCharacterTable();
    }
    private void createDatabase(String databaseName) throws DatabaseAccessException, PropertiesFileException {
        try(PreparedStatement ps = queries.get("create-database")) {
            ps.executeUpdate();
            connection.setCatalog(databaseName); //Now point to the DB recently created
            deleteObsoleteQueriesFromMemory(); //We delete the queries with the old connection (the one who didn't have "databaseName" as catalog)
            this.queries = PropertiesFileLoader.loadQueries(this.connection,PropertiesFileLoader.loadPropertiesFile(QUERIES_FILE_ROUTE)); //Since we changed our connection catalog, we need to update the connection of our queries
            logger.info("Database '"+databaseName+"' created successfully.\n");
        } catch (SQLException e) {
            try {
                connection.setCatalog(databaseName); //Now point to the existent DB
                this.queries = PropertiesFileLoader.loadQueries(this.connection,PropertiesFileLoader.loadPropertiesFile(QUERIES_FILE_ROUTE)); //Since we changed our connection catalog, we need to update the connection of our queries
            } catch (SQLException ex) {
                throw new DatabaseAccessException("Couldn't access to database provided. Set catalog operation over database failed.");
            }
            logger.info("Database '"+databaseName+"' already exists. No need to create it again.\n");
        } catch (PropertiesFileException e) {
            throw new PropertiesFileException("Couldn't found queries file provided. Check if exists.");
        }
    }
    private void createRPCharacterTable(){
        try(PreparedStatement ps = queries.get("create-table-rpcharacter")) {
            ps.executeUpdate();
            logger.info("Table 'rpcharacter' created successfully.\n");
        } catch (SQLException e) {
            logger.info("Table 'rpcharacter' already exists. No need to create it again.\n");
        }
    }
    private void deleteObsoleteQueriesFromMemory() throws DatabaseAccessException {
        for(PreparedStatement ps : queries.values()){
            try {
                ps.close();
            } catch (SQLException e) {
                throw new DatabaseAccessException("Couldn't close the old PreparedStatement.");
            }
        }
    }
}
