package com.RPCompanion.services;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.repositories.RPCharacterRepository;

import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RPCharacterService {
    private final Logger logger = Logger.getLogger(RPCharacterService.class.getName());
    private Connection connection;
    private RPCharacterRepository rpCharacterRepository;
    public RPCharacterService(Connection connection) throws DatabaseAccessException, PropertiesFileException {
        this.connection = connection;
        this.rpCharacterRepository = new RPCharacterRepository(this.connection);
    }
    public boolean save(RPCharacterEntity rpCharacterEntity) throws DatabaseAccessException {
        return rpCharacterRepository.save(rpCharacterEntity);
    }

    public boolean deleteByID(int id){
        return rpCharacterRepository.deleteByID(id);
    }

    public RPCharacterEntity selectByID(int id){
        RPCharacterEntity rpCharacterEntity = new RPCharacterEntity();
        try (ResultSet rs = rpCharacterRepository.selectByID(id)) {
            if(rs.next()){
                rpCharacterEntity = mapRPCharacterEntity(rs);
                logger.info("Successfully performed 'Select' transaction over RPCharacterEntity of ID '"+id+"'.\n");
            }else{
                logger.warning("'Select' transaction over RPCharacterEntity of ID '"+id+"' failed. No entity with such ID exists.\n");
            }
        } catch (SQLException e) {
            logger.warning("'Select' transaction over RPCharacterEntity of ID '"+id+"' failed.\n"+e.getLocalizedMessage()+"\n");
        }
        return rpCharacterEntity;
    }

    public boolean modifyByID(RPCharacterEntity rpCharacterEntity){
        return rpCharacterRepository.modifyByID(rpCharacterEntity);
    }

    public int countRegisters(){
        try (ResultSet rs = rpCharacterRepository.countRegisters()) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RPCharacterEntity> selectAll(){
        List<RPCharacterEntity> characterEntities = new ArrayList<>();
        try(ResultSet rs = rpCharacterRepository.selectAll()){
            while(rs.next()){
                characterEntities.add(mapRPCharacterEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return characterEntities;
    }
    private RPCharacterEntity mapRPCharacterEntity(ResultSet rs){
        RPCharacterEntity rpCharacterEntity = new RPCharacterEntity();
        try {
            rpCharacterEntity.setId(rs.getInt(1));
            rpCharacterEntity.setName(rs.getString(2));
            rpCharacterEntity.setSurname(rs.getString(3));
            rpCharacterEntity.setBirthDate(rs.getDate(4));
            rpCharacterEntity.setAge(rs.getInt(5));
            rpCharacterEntity.setStory(rs.getString(6));
            rpCharacterEntity.setAspect(rs.getBlob(7));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rpCharacterEntity;
    }
}
