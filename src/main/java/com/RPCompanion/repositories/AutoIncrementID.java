package com.RPCompanion.repositories;

import com.RPCompanion.exceptions.DatabaseAccessException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoIncrementID {
    public static int calculateNextID(ResultSet resultSet) throws DatabaseAccessException {
        try {
            resultSet.next();
            return resultSet.getInt(1) + 1;
        } catch (SQLException e) {
            throw new DatabaseAccessException("Calculate next ID operation failed.\n"+e.getLocalizedMessage());
        }
    }
}
