package com.RPCompanion;
import com.RPCompanion.database.connection.DatabaseConnection;
import com.RPCompanion.entities.GalleryEntity;
import com.RPCompanion.entities.PowerEntity;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.services.GalleryService;
import com.RPCompanion.services.PowerService;
import com.RPCompanion.services.RPCharacterService;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws PropertiesFileException, DatabaseAccessException {
        DatabaseConnection dbc = new DatabaseConnection();
        dbc.closeConnection();
    }
}
