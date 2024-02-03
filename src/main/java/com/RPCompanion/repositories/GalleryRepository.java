package com.RPCompanion.repositories;

import com.RPCompanion.entities.GalleryEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.fileloader.PropertiesFileLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

public class GalleryRepository {
    private final Logger logger = Logger.getLogger(GalleryRepository.class.getName());
    private final String QUERIES_FILE_ROUTE = "src/main/resources/gallery/gallery-queries.properties";
    private HashMap<String, PreparedStatement> queries;
    public GalleryRepository(Connection connection) throws PropertiesFileException, DatabaseAccessException {
        this.queries = PropertiesFileLoader.loadQueries(connection,QUERIES_FILE_ROUTE);
    }
    public boolean save(GalleryEntity galleryEntity){
        boolean saved = true;
        try {
            galleryEntity.setId(AutoIncrementID.calculateNextID(queries.get("get-last-id").executeQuery()));
            queries.get("save").setInt(1,galleryEntity.getId());
            queries.get("save").setInt(2,galleryEntity.getRpCharacterId());
            queries.get("save").setBlob(3,galleryEntity.getImage());
            queries.get("save").executeUpdate();
            logger.info("Successfully performed 'Save' transaction over GalleryEntity of ID '"+galleryEntity.getId()+"'.\n");
        } catch (SQLException e) {
            logger.warning("'Save' transaction over GalleryEntity of ID '"+galleryEntity.getId()+"' failed.\n"+e.getLocalizedMessage()+"\n");
            saved = false;
        }
        return saved;
    }
    public ResultSet selectByID(int id) {
        ResultSet rs = null;
        try {
            queries.get("select-by-id").setInt(1,id);
            rs = queries.get("select-by-id").executeQuery();
        } catch (SQLException e) {
            logger.warning("'Select' transaction over GalleryEntity of ID '"+id+"' failed.\n"+e.getLocalizedMessage()+"\n");
        }
        return rs;
    }
    public boolean deleteByID(int id){
        boolean deleted = true;
        try {
            queries.get("delete-by-id").setInt(1,id);
            queries.get("delete-by-id").executeUpdate();
            logger.info("Successfully performed 'Delete' operation over GalleryEntity of ID '"+id+"'.\n");
        } catch (SQLException e) {
            logger.warning("'Delete' transaction over GalleryEntity of ID '"+id+"' failed.\n"+e.getLocalizedMessage()+"\n");
            deleted = false;
        }
        return deleted;
    }
    public boolean modifyByID(GalleryEntity galleryEntity){
        boolean modified = true;
        try {
            queries.get("modify-by-id").setBlob(1,galleryEntity.getImage());
            queries.get("modify-by-id").setInt(2,galleryEntity.getId());
            queries.get("modify-by-id").executeUpdate();
            logger.info("Successfully performed 'Modify' transaction over GalleryEntity of ID '"+galleryEntity.getId()+"'.\n");
        } catch (SQLException e) {
            logger.warning("'Modify' transaction over GalleryEntity of ID '"+galleryEntity.getId()+"' failed.\n"+e.getLocalizedMessage()+"\n");
            modified = false;
        }
        return modified;
    }
}
