package com.RPCompanion.services;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.repositories.RPCharacterRepository;
import java.sql.Connection;
public class RPCharacterService {
    private Connection connection;
    private RPCharacterRepository rpCharacterRepository;
    public RPCharacterService(Connection connection) throws DatabaseAccessException {
        this.connection = connection;
        this.rpCharacterRepository = new RPCharacterRepository(this.connection);
    }
    public void save(RPCharacterEntity rpCharacterEntity){
        rpCharacterRepository.save(rpCharacterEntity);
    }
}
