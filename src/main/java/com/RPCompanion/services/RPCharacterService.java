package com.RPCompanion.services;

import com.RPCompanion.database.connection.DatabaseConnection;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.repositories.RPCharacterRepository;

import java.sql.Connection;
import java.util.logging.Logger;

public class RPCharacterService {
    private Connection connection;
    private RPCharacterRepository rpCharacterRepository;
    public RPCharacterService(Connection connection){
        this.connection = connection;
        this.rpCharacterRepository = new RPCharacterRepository(this.connection);
    }
    public void save(RPCharacterEntity rpCharacterEntity){
        rpCharacterRepository.save(rpCharacterEntity);
    }
}
