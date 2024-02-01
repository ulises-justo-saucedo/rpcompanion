package com.RPCompanion;
import com.RPCompanion.database.connection.DatabaseConnection;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.services.RPCharacterService;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        DatabaseConnection dbc = new DatabaseConnection();

        RPCharacterEntity myEntity = new RPCharacterEntity(
                "Mariana",
                "Rivaldi",
                LocalDate.of(1990,8,9),
                34,
                "A beautiful history",
                "A good looking woman"
        );
        myEntity.setId(2);

        RPCharacterService rpCharacterService = new RPCharacterService(dbc.getConnection());
        rpCharacterService.save(myEntity);

        dbc.closeConnection();
    }
}
