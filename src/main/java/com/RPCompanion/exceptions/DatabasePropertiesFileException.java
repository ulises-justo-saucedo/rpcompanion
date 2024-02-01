package com.RPCompanion.exceptions;

import java.io.IOException;

public class DatabasePropertiesFileException extends IOException {
    public DatabasePropertiesFileException(String errorMessage){
        super(errorMessage);
    }
}
