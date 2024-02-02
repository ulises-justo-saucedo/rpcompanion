package com.RPCompanion.services;

import com.RPCompanion.entities.PowerEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.repositories.PowerRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PowerService {
    private final Logger logger = Logger.getLogger(PowerService.class.getName());
    private PowerRepository powerRepository;
    public PowerService(Connection connection) throws PropertiesFileException, DatabaseAccessException {
        this.powerRepository = new PowerRepository(connection);
    }
    public boolean save(PowerEntity powerEntity){
        return powerRepository.save(powerEntity);
    }
    public PowerEntity selectByID(int id){
        PowerEntity powerEntity = new PowerEntity();
        try(ResultSet rs = powerRepository.selectByID(id)){
            if(rs.next()){
                powerEntity.setId(rs.getInt(1));
                powerEntity.setRpCharacterId(rs.getInt(2));
                powerEntity.setName(rs.getString(3));
                powerEntity.setImage(rs.getBlob(4));
                powerEntity.setDescription(rs.getString(5));
                logger.info("Succesfully performed SELECT transaction over PowerEntity of ID '"+id+"'.\n");
            }else{
                logger.warning("No PowerEntity register avaible with ID '"+id+"'.\n");
            }
        } catch (SQLException e) {
            logger.warning("SELECT transaction over PowerEntity of ID '"+id+"' failed.\n");
        }
        return powerEntity;
    }
    public boolean deleteByID(int id){
        return powerRepository.deleteByID(id);
    }
}
