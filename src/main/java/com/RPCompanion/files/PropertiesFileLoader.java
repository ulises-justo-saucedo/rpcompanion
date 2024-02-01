package com.RPCompanion.files;
import com.RPCompanion.exceptions.PropertiesFileException;

import java.io.FileReader;
import java.io.IOException;
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
}
