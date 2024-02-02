package com.RPCompanion.repositories.RPCharacter;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.fileloader.PropertiesFileLoader;
import com.RPCompanion.repositories.AutoIncrementID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

public class RPCharacterRepository {
    private final Logger logger = Logger.getLogger(RPCharacterRepository.class.getName());
    private final String QUERIES_FILE_ROUTE = "src/main/resources/rpcharacter/rpcharacter-queries.properties";
    private Connection connection;
    private HashMap<String,PreparedStatement> queries;
    public RPCharacterRepository(Connection connection) throws DatabaseAccessException, PropertiesFileException {
        this.connection = connection;
        this.queries = PropertiesFileLoader.loadQueries(this.connection,PropertiesFileLoader.loadPropertiesFile(QUERIES_FILE_ROUTE));
    }
    public void save(RPCharacterEntity rpCharacterEntity) throws DatabaseAccessException {
        try(PreparedStatement psSave = queries.get("save")) {
            rpCharacterEntity.setId(calculateID());
            psSave.setInt(1,rpCharacterEntity.getId());
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
        try(PreparedStatement psLastId = queries.get("get-last-id")) {
            return AutoIncrementID.calculateNextID(psLastId.executeQuery());
        } catch (SQLException e) {
            throw new DatabaseAccessException("SELECT transaction failed. A wrong connection, null PreparedStatement or wrong database credentials can be the cause.");
        }
    }
}
