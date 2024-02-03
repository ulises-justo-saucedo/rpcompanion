package com.RPCompanion.fileloader;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesFileLoader {
    public static Properties loadPropertiesFile(String fileRoute) throws PropertiesFileException {
        Properties prop = new Properties();
        try(FileReader fr = new FileReader(fileRoute)){
            prop.load(fr);
        } catch (IOException e) {
            throw new PropertiesFileException("Couldn't load properties file. Check if exists: "+fileRoute);
        }
        return prop;
    }
    public static HashMap<String, PreparedStatement> loadQueries(Connection connection, String queriesFileRoute) throws DatabaseAccessException, PropertiesFileException {
        HashMap<String,PreparedStatement> queries = new HashMap<>();
        Properties queriesFile = loadPropertiesFile(queriesFileRoute);
        Enumeration<Object> queriesKeys = queriesFile.keys();
        while(queriesKeys.hasMoreElements()){
            String key = String.valueOf(queriesKeys.nextElement());
            try {
                queries.put(key,connection.prepareStatement(queriesFile.getProperty(key)));
            } catch (SQLException e) {
                throw new DatabaseAccessException("Load queries operation failed.\n"+e.getLocalizedMessage());
            }
        }
        return queries;
    }
}
