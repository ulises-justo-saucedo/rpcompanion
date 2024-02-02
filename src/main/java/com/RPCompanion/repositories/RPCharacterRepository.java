package com.RPCompanion.repositories;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.fileloader.PropertiesFileLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

public class RPCharacterRepository {
    private final Logger logger = Logger.getLogger(RPCharacterRepository.class.getName());
    private final String QUERIES_FILE_ROUTE = "src/main/resources/rpcharacter/rpcharacter-queries.properties";
    private HashMap<String,PreparedStatement> queries;
    public RPCharacterRepository(Connection connection) throws DatabaseAccessException, PropertiesFileException {
        this.queries = PropertiesFileLoader.loadQueries(connection,QUERIES_FILE_ROUTE);
    }
    public boolean save(RPCharacterEntity rpCharacterEntity) throws DatabaseAccessException {
        boolean saved = true;
        try {
            rpCharacterEntity.setId(AutoIncrementID.calculateNextID(queries.get("get-last-id").executeQuery()));
            queries.get("save").setInt(1,rpCharacterEntity.getId());
            queries.get("save").setString(2,rpCharacterEntity.getName());
            queries.get("save").setString(3,rpCharacterEntity.getSurname());
            queries.get("save").setString(4,rpCharacterEntity.getBirthDate().toString());
            queries.get("save").setInt(5,rpCharacterEntity.getAge());
            queries.get("save").setString(6,rpCharacterEntity.getStory());
            queries.get("save").setBlob(7,rpCharacterEntity.getAspect());
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
            queries.get("modify-by-id").setBlob(6,rpCharacterEntity.getAspect());
            queries.get("modify-by-id").setInt(7,rpCharacterEntity.getId());
            queries.get("modify-by-id").executeUpdate();
            logger.info("Successfully modified entity of ID '"+rpCharacterEntity.getId()+"'.");
        } catch (SQLException e) {
            modified = false;
            logger.warning("Couldn't modify the entity of ID '"+rpCharacterEntity.getId()+"'.");
        }
        return modified;
    }
}
