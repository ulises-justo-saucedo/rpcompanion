package com.RPCompanion;
import com.RPCompanion.database.connection.DatabaseConnection;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.services.RPCharacterService;
import com.RPCompanion.ui.menu.mainmenu.MainMenu;
import com.RPCompanion.ui.window.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws PropertiesFileException, DatabaseAccessException {
        DatabaseConnection dbc = new DatabaseConnection();

        Window window = new Window(400,400,"RPCompanion");
        MainMenu mainMenu = new MainMenu(window,dbc.getConnection(),new RPCharacterService(dbc.getConnection()));
        window.display();

        //dbc.closeConnection();
    }
}
