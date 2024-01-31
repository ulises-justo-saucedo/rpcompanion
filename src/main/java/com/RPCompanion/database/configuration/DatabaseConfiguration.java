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
    private Properties properties;
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
            createDatabaseIfNotExists();
            return DriverManager.getConnection(
                    properties.getProperty("url")+DATABASE_NAME,
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDatabaseIfNotExists(){
        try {
            Connection temporalConnection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
            temporalConnection.prepareStatement("CREATE DATABASE IF NOT EXISTS "+DATABASE_NAME).executeUpdate();
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
