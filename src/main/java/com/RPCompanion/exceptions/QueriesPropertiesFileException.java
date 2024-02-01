package com.RPCompanion.exceptions;

import java.io.IOException;

public class QueriesPropertiesFileException extends IOException {
    public QueriesPropertiesFileException(String errorMessage){
        super(errorMessage);
    }
}
