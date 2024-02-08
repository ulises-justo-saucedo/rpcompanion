package com.RPCompanion.converters;

import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.SQLException;

public class ImageConverter {
    public static Blob convertImageToBlob(Path image){
        Blob aspect = null;
        try {
            //Convert user's image to bytes in order to insert it in the database
            aspect = new SerialBlob(Files.readAllBytes(image));
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return aspect;
    }
}
