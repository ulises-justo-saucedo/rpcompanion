package com.RPCompanion;
import com.RPCompanion.database.connection.DatabaseConnection;
public class App {
    public static void main(String[] args) throws Exception{
        DatabaseConnection dbc = new DatabaseConnection();
        dbc.closeConnection();
    }
}
