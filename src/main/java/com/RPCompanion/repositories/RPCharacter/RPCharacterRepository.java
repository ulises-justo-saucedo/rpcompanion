package com.RPCompanion.repositories.RPCharacter;

import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.fileloader.PropertiesFileLoader;
import com.RPCompanion.repositories.AutoIncrementID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class RPCharacterRepository {
    private final Logger logger = Logger.getLogger(RPCharacterRepository.class.getName());
    private final String LOCAL_QUERIES_FILE_ROUTE = "src/main/java/com/RPCompanion/repositories/RPCharacter/rpcharacter-queries.properties";
    private final String GLOBAL_QUERIES_FILE_ROUTE = "src/main/java/com/RPCompanion/repositories/global-queries.properties";
    private Connection connection;
    private Properties localQueries;
    private Properties globalQueries;
    private PreparedStatement psSave;
    private PreparedStatement psGlobalQueryGetLastID;
    public RPCharacterRepository(Connection connection) throws DatabaseAccessException, PropertiesFileException {
        this.connection = connection;
        this.localQueries = PropertiesFileLoader.loadPropertiesFile(LOCAL_QUERIES_FILE_ROUTE);
        this.globalQueries = PropertiesFileLoader.loadPropertiesFile(GLOBAL_QUERIES_FILE_ROUTE);
        try {
            this.psSave = this.connection.prepareStatement(localQueries.getProperty("save"));
            this.psGlobalQueryGetLastID = this.connection.prepareStatement(globalQueries.getProperty("get-last-id")+" rpcharacter");
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't create new PreparedStatement. Closed connection or wrong permissions can be the cause.");
        }
    }
    public void save(RPCharacterEntity rpCharacterEntity){
        try {
            rpCharacterEntity.setId(calculateID());
            psSave.setInt(1,calculateID());
            psSave.setString(2,rpCharacterEntity.getName());
            psSave.setString(3,rpCharacterEntity.getSurname());
            psSave.setString(4,rpCharacterEntity.getBirthDate().toString());
            psSave.setInt(5,rpCharacterEntity.getAge());
            psSave.setString(6,rpCharacterEntity.getHistory());
            psSave.setString(7,rpCharacterEntity.getAspect());
            psSave.executeUpdate();
            logger.info("Successfully saved entity with ID '"+rpCharacterEntity.getId()+"'.\n");
        } catch (SQLException e) {
            logger.warning("Entity with ID '"+rpCharacterEntity.getId()+"' already exists. Save transaction failed.\n");
        }
    }
    private int calculateID() throws DatabaseAccessException {
        try {
            return AutoIncrementID.calculateNextID(psGlobalQueryGetLastID.executeQuery());
        } catch (SQLException e) {
            throw new DatabaseAccessException("SELECT transaction failed. A wrong connection, null PreparedStatement or wrong database credentials can be the cause.");
        }
    }
}
