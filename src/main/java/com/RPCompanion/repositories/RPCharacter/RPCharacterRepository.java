package com.RPCompanion.repositories.RPCharacter;

import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.QueriesPropertiesFileException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class RPCharacterRepository {
    private final Logger logger = Logger.getLogger(RPCharacterRepository.class.getName());
    private final String QUERIES_FILE_ROUTE = "src/main/java/com/RPCompanion/repositories/RPCharacter/rpcharacter-queries.properties";
    private Connection connection;
    private Properties queries;
    private PreparedStatement psSave;
    public RPCharacterRepository(Connection connection) throws DatabaseAccessException, QueriesPropertiesFileException {
        this.connection = connection;
        this.queries = loadQueries();
        try {
            this.psSave = this.connection.prepareStatement(queries.getProperty("save"));
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't create new PreparedStatement. Closed connection or wrong permissions can be the cause.");
        }
    }
    public Properties loadQueries() throws QueriesPropertiesFileException {
        try(FileReader fr = new FileReader(QUERIES_FILE_ROUTE)){
            Properties p = new Properties();
            p.load(fr);
            return p;
        } catch (IOException e) {
            throw new QueriesPropertiesFileException("Couldn't load required queries. Check if queries file exists.");
        }
    }
    public void save(RPCharacterEntity rpCharacterEntity){
        try {
            psSave.setInt(1,rpCharacterEntity.getId()); //ID
            psSave.setString(2,rpCharacterEntity.getName()); //Name
            psSave.setString(3,rpCharacterEntity.getSurname()); //Surname
            psSave.setString(4,rpCharacterEntity.getBirthDate().toString()); //Birthdate
            psSave.setInt(5,rpCharacterEntity.getAge()); //Age
            psSave.setString(6,rpCharacterEntity.getHistory()); //Story
            psSave.setString(7,rpCharacterEntity.getAspect()); //Aspect
            psSave.executeUpdate();
            logger.info("Successfully saved entity with ID '"+rpCharacterEntity.getId()+"'.\n");
        } catch (SQLException e) {
            logger.warning("Entity with ID '"+rpCharacterEntity.getId()+"' already exists. Save transaction failed.\n");
        }
    }
}
