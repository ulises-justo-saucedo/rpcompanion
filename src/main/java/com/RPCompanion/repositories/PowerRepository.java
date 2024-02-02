package com.RPCompanion.repositories;

import com.RPCompanion.entities.PowerEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.fileloader.PropertiesFileLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

public class PowerRepository {
    private final Logger logger = Logger.getLogger(PowerRepository.class.getName());
    private final String QUERIES_FILE_ROUTE = "src/main/resources/power/power-queries.properties";
    private HashMap<String, PreparedStatement> queries;
    public PowerRepository(Connection connection) throws PropertiesFileException, DatabaseAccessException {
        this.queries = PropertiesFileLoader.loadQueries(connection,QUERIES_FILE_ROUTE);
    }
    public boolean save(PowerEntity powerEntity){
        boolean saved = true;
        try {
            powerEntity.setId(AutoIncrementID.calculateNextID(queries.get("get-last-id").executeQuery()));
            queries.get("save").setInt(1,powerEntity.getId());
            queries.get("save").setInt(2,powerEntity.getRpCharacterId());
            queries.get("save").setString(3, powerEntity.getName());
            queries.get("save").setBlob(4,powerEntity.getImage());
            queries.get("save").setString(5,powerEntity.getDescription());
            queries.get("save").executeUpdate();
            logger.info("Successfully saved PowerEntity of ID '"+powerEntity.getId()+"'.\n");
        } catch (SQLException e) {
            logger.warning("Failed INSERT operation over PowerEntity of ID '"+powerEntity.getId()+"'.\n");
            saved = false;
        }
        return saved;
    }
    public ResultSet selectByID(int id){
        ResultSet rs = null;
        try {
            queries.get("select-by-id").setInt(1,id);
            rs = queries.get("select-by-id").executeQuery();
        } catch (SQLException e) {
            logger.warning("SELECT transaction over PowerEntity of ID '"+id+"' failed.\n");
        }
        return rs;
    }
    public boolean deleteByID(int id){
        boolean deleted = true;
        try {
            queries.get("delete-by-id").setInt(1,id);
            queries.get("delete-by-id").executeUpdate();
            logger.info("Successfully performed DELETE transaction over PowerEntity of ID '"+id+"'.\n");
        } catch (SQLException e) {
            logger.warning("DELETE transaction failed over PowerEntity of ID '"+id+"'.\n");
            deleted = false;
        }
        return deleted;
    }
    public boolean modifyByID(PowerEntity powerEntity){
        boolean modified = true;
        try {
            queries.get("modify-by-id").setString(1,powerEntity.getName());
            queries.get("modify-by-id").setBlob(2,powerEntity.getImage());
            queries.get("modify-by-id").setString(3,powerEntity.getDescription());
            queries.get("modify-by-id").executeUpdate();
            logger.info("Successfully performed UPDATE transaction over PowerEntity of ID '"+powerEntity.getId()+"'.\n");
        } catch (SQLException e) {
            logger.info("UPDATE transaction over PowerEntity of ID '"+powerEntity.getId()+"' failed.\n");
            modified = false;
        }
        return modified;
    }
}
