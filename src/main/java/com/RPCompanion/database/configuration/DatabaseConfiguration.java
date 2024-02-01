package com.RPCompanion.database.configuration;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.fileloader.PropertiesFileLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DatabaseConfiguration {
    private final String PROPERTIES_FILE_ROUTE = "src/main/resources/database/database.properties";
    private final String DATABASE_NAME = "rpcompanion";
    private final String URL_KEY = "url";
    private final String USERNAME_KEY = "username";
    private final String PASSWORD_KEY = "password";
    private final Properties properties;
    public DatabaseConfiguration() throws PropertiesFileException {
        this.properties = PropertiesFileLoader.loadPropertiesFile(PROPERTIES_FILE_ROUTE);
    }
    public Connection establishConnection() throws DatabaseAccessException {
        try {
            createDatabase();
            return DriverManager.getConnection(
                    properties.getProperty(URL_KEY)+DATABASE_NAME,
                    properties.getProperty(USERNAME_KEY),
                    properties.getProperty(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access to the database provided.");
        }
    }
    public void createDatabase() throws DatabaseAccessException {
        Connection temporalConnection = null;
        try {
            temporalConnection = DriverManager.getConnection(
                    properties.getProperty(URL_KEY),
                    properties.getProperty(USERNAME_KEY),
                    properties.getProperty(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access to the database provided.");
        }
        new DatabaseConfigurationQuery(temporalConnection).setUpDatabase(DATABASE_NAME);
        try {
            temporalConnection.close();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access to the database provided. Close operation failed.");
        }
    }
    public Properties getProperties() {
        return properties;
    }
    public String getPROPERTIES_FILE_ROUTE() {
        return PROPERTIES_FILE_ROUTE;
    }
    public String getDATABASE_NAME() {
        return DATABASE_NAME;
    }
}
