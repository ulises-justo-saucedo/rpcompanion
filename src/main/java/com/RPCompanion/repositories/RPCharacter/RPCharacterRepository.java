package com.RPCompanion.repositories.RPCharacter;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.fileloader.PropertiesFileLoader;
import com.RPCompanion.repositories.AutoIncrementID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public boolean save(RPCharacterEntity rpCharacterEntity) throws DatabaseAccessException {
        boolean saved = true;
        try {
            rpCharacterEntity.setId(calculateID());
            queries.get("save").setInt(1,rpCharacterEntity.getId());
            queries.get("save").setString(2,rpCharacterEntity.getName());
            queries.get("save").setString(3,rpCharacterEntity.getSurname());
            queries.get("save").setString(4,rpCharacterEntity.getBirthDate().toString());
            queries.get("save").setInt(5,rpCharacterEntity.getAge());
            queries.get("save").setString(6,rpCharacterEntity.getStory());
            queries.get("save").setString(7,rpCharacterEntity.getAspect());
            queries.get("save").executeUpdate();
            logger.info("Successfully saved entity with ID '"+rpCharacterEntity.getId()+"'.\n");
        } catch (SQLException e) {
            logger.warning("Entity with ID '"+rpCharacterEntity.getId()+"' already exists. Save transaction failed.\n");
            saved = false;
        }
        return saved;
    }
    public boolean deleteByID(int id) {
        boolean deleted = true;
        try {
            queries.get("delete-by-id").setInt(1,id);
            queries.get("delete-by-id").executeUpdate();
            logger.info("Successfully deleted entity with ID '"+id+"'.\n");
        } catch (SQLException e) {
            logger.warning("Couldn't delete entity of ID '"+id+"'. ID provided may be wrong or a database access error happened.\n");
            deleted = false;
        }
        return deleted;
    }
    public ResultSet selectByID(int id){
        ResultSet rs = null;
        try {
            queries.get("select-by-id").setInt(1,id);
            rs = queries.get("select-by-id").executeQuery();
        } catch (SQLException e) {
            logger.warning("Couldn't select entity of ID '"+id+"'. ID provided may be wrong or a database access error happened.\n");
        }
        return rs;
    }
    public boolean modifyByID(RPCharacterEntity rpCharacterEntity) {
        boolean modified = true;
        try {
            queries.get("modify-by-id").setString(1,rpCharacterEntity.getName());
            queries.get("modify-by-id").setString(2,rpCharacterEntity.getSurname());
            queries.get("modify-by-id").setDate(3,rpCharacterEntity.getBirthDate());
            queries.get("modify-by-id").setInt(4,rpCharacterEntity.getAge());
            queries.get("modify-by-id").setString(5,rpCharacterEntity.getStory());
            queries.get("modify-by-id").setString(6,rpCharacterEntity.getAspect());
            queries.get("modify-by-id").setInt(7,rpCharacterEntity.getId());
            queries.get("modify-by-id").executeUpdate();
            logger.info("Successfully modified entity of ID '"+rpCharacterEntity.getId()+"'.");
        } catch (SQLException e) {
            modified = false;
            logger.warning("Couldn't modify the entity of ID '"+rpCharacterEntity.getId()+"'.");
        }
        return modified;
    }
    private int calculateID() throws DatabaseAccessException {
        try {
            return AutoIncrementID.calculateNextID(queries.get("get-last-id").executeQuery());
        } catch (SQLException e) {
            throw new DatabaseAccessException("SELECT transaction failed. A wrong connection, null PreparedStatement or wrong database credentials can be the cause.");
        }
    }
}
