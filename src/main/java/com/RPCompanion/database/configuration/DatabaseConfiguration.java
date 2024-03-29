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
    private final String URL_KEY = "url";
    private final String DATABASE_NAME_KEY = "database-name";
    private final String USERNAME_KEY = "username";
    private final String PASSWORD_KEY = "password";
    private final Properties properties;
    public DatabaseConfiguration() throws PropertiesFileException {
        this.properties = PropertiesFileLoader.loadPropertiesFile(PROPERTIES_FILE_ROUTE);
    }
    public Connection establishConnection() throws DatabaseAccessException, PropertiesFileException {
        createDatabase();
        try {
            return DriverManager.getConnection(
                    properties.getProperty(URL_KEY)+properties.getProperty(DATABASE_NAME_KEY),
                    properties.getProperty(USERNAME_KEY),
                    properties.getProperty(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access to the database provided.\n"+e.getLocalizedMessage());
        }
    }
    public void createDatabase() throws DatabaseAccessException, PropertiesFileException {
        Connection temporalConnection = null;
        try {
            temporalConnection = DriverManager.getConnection(
                    properties.getProperty(URL_KEY),
                    properties.getProperty(USERNAME_KEY),
                    properties.getProperty(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access to the database provided.\n"+e.getLocalizedMessage());
        }
        new DatabaseConfigurationQuery(temporalConnection).setUpDatabase(properties.getProperty(DATABASE_NAME_KEY));
        try {
            temporalConnection.close();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Close operation failed.\n"+e.getLocalizedMessage());
        }
    }
    public Properties getProperties() {
        return properties;
    }
    public String getPROPERTIES_FILE_ROUTE() {
        return PROPERTIES_FILE_ROUTE;
    }
    public String getDATABASE_NAME_KEY() {
        return DATABASE_NAME_KEY;
    }
}
