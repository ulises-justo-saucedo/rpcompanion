package com.RPCompanion.database.configuration;
import java.io.FileReader;
import java.io.IOException;
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
    public DatabaseConfiguration() throws IOException {
        this.properties = loadDatabaseProperties();
    }
    public Properties loadDatabaseProperties() throws IOException {
        Properties prop = new Properties();
        try(FileReader fr = new FileReader(PROPERTIES_FILE_ROUTE)){
            prop.load(fr);
        }
        return prop;
    }
    public Connection establishConnection(){
        try {
            createDatabase();
            return DriverManager.getConnection(
                    properties.getProperty(URL_KEY)+DATABASE_NAME,
                    properties.getProperty(USERNAME_KEY),
                    properties.getProperty(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDatabase() {
        Connection temporalConnection = null;
        try {
            temporalConnection = DriverManager.getConnection(
                    properties.getProperty(URL_KEY),
                    properties.getProperty(USERNAME_KEY),
                    properties.getProperty(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        new DatabaseConfigurationQuery(temporalConnection).setUpDatabase(DATABASE_NAME);
        try {
            temporalConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
