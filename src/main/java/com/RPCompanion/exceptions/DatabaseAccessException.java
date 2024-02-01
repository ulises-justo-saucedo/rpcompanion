package com.RPCompanion.exceptions;

import java.sql.SQLException;

public class DatabaseAccessException extends SQLException {
    public DatabaseAccessException(String errorMessage){
        super(errorMessage);
    }
}
