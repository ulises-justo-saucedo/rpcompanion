package com.RPCompanion.repositories;

import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.services.RPCharacterService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class RPCharacterRepository {
    private final Logger logger = Logger.getLogger(RPCharacterRepository.class.getName());;
    private Connection connection;
    private PreparedStatement psSave;
    public RPCharacterRepository(Connection connection){
        this.connection = connection;
        try {
            this.psSave = this.connection.prepareStatement("INSERT INTO rpcharacter VALUES(?,?,?,?,?,?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            logger.warning("Entity with ID '"+rpCharacterEntity.getId()+"' already exists. No need to save it.\n");
        }
    }
}
