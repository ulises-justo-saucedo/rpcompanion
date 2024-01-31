package com.RPCompanion.database.querys;
import java.sql.Connection;
public class DatabaseQuery {
    private Connection connection;
    public DatabaseQuery(Connection connection) {
        this.connection = connection;
    }
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
