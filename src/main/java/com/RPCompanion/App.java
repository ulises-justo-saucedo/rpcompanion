package com.RPCompanion;
import com.RPCompanion.database.connection.DatabaseConnection;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.ui.menu.mainmenu.MainMenu;
import com.RPCompanion.ui.window.Window;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App {
    public static void main(String[] args) throws PropertiesFileException, DatabaseAccessException {
        DatabaseConnection dbc = new DatabaseConnection();

        Window window = new Window(400,400,"RPCompanion");
        MainMenu mainMenu = new MainMenu(window,dbc.getConnection());
        window.display();

        //dbc.closeConnection();
    }
}
