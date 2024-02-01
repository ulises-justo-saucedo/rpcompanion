package com.RPCompanion.exceptions;

import java.io.IOException;
public class PropertiesFileException extends IOException {
    public PropertiesFileException(String errorMessage){
        super(errorMessage);
    }
}
