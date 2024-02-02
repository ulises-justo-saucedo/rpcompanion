package com.RPCompanion.services;

import com.RPCompanion.entities.GalleryEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.repositories.GalleryRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class GalleryService {
    private final Logger logger = Logger.getLogger(GalleryService.class.getName());
    private GalleryRepository galleryRepository;
    public GalleryService(Connection connection) throws PropertiesFileException, DatabaseAccessException {
        this.galleryRepository = new GalleryRepository(connection);
    }
    public boolean save(GalleryEntity galleryEntity){
        return galleryRepository.save(galleryEntity);
    }
    public GalleryEntity selectByID(int id){
        GalleryEntity galleryEntity = new GalleryEntity();
        try (ResultSet rs = galleryRepository.selectByID(id)) {
            if(rs.next()){
                galleryEntity.setId(rs.getInt(1));
                galleryEntity.setRpCharacterId(rs.getInt(2));
                galleryEntity.setImage(rs.getBlob(3));
                logger.info("Successfully selected GalleryEntity of ID '"+id+"'.\n");
            }else{
                logger.warning("SELECT operation over GalleryEntity of ID '"+id+"' failed. No entity with such ID exists.\n");
            }
        } catch (SQLException e) {
            logger.warning("SELECT operation over GalleryEntity of ID '"+id+"' failed.\n");
        }
        return galleryEntity;
    }
    public boolean deleteByID(int id){
        return galleryRepository.deleteByID(id);
    }
    public boolean modifyByID(GalleryEntity galleryEntity){
        return galleryRepository.modifyByID(galleryEntity);
    }
}
