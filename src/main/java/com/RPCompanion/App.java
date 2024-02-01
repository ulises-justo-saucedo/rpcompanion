package com.RPCompanion;
import com.RPCompanion.database.connection.DatabaseConnection;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.services.RPCharacterService;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws DatabaseAccessException, PropertiesFileException {
        DatabaseConnection dbc = new DatabaseConnection();

        RPCharacterEntity myEntity = new RPCharacterEntity(
                "NameTest",
                "SurnameTestOfCourse",
                LocalDate.of(2017,6,30),
                1,
                "I'm bad at writing stories",
                "My entity looks good"
        );

        RPCharacterService rpCharacterService = new RPCharacterService(dbc.getConnection());
        rpCharacterService.save(myEntity);

        dbc.closeConnection();
    }
}
