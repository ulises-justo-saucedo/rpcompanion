package com.RPCompanion;
import com.RPCompanion.ui.menu.mainmenu.MainMenu;
import com.RPCompanion.ui.window.Window;
public class App {
    public static void main(String[] args) {
        /*DatabaseConnection dbc = new DatabaseConnection();
        dbc.closeConnection();*/
        Window window = new Window(400,400,"RPCompanion");
        MainMenu mainMenu = new MainMenu(window);
        window.display();
    }
}
