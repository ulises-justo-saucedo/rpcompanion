package com.RPCompanion;
import com.RPCompanion.database.connection.DatabaseConnection;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.services.RPCharacterService;
import java.sql.Date;

public class App {
    public static void main(String[] args) throws DatabaseAccessException, PropertiesFileException {
        DatabaseConnection dbc = new DatabaseConnection();

        RPCharacterEntity myEntity = new RPCharacterEntity(
                "Name for the first entity!",
                "Hello I'm a surname",
                Date.valueOf("2093-1-31"),
                72,
                "I'm good at writing stories",
                "My entity looks bad ;("
        );

        RPCharacterService rpCharacterService = new RPCharacterService(dbc.getConnection());

        rpCharacterService.save(myEntity);

        RPCharacterEntity myEntityRetrievedFromDB = rpCharacterService.selectByID(1);

        myEntityRetrievedFromDB.setAspect("Well, now my entity looks good! ;)");

        rpCharacterService.modifyByID(myEntityRetrievedFromDB);

        dbc.closeConnection();
    }
}
