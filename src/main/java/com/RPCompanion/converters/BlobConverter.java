package com.RPCompanion.converters;

import javax.swing.*;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class BlobConverter {
    public static ImageIcon convertBlobToImage(Blob image){
        if(image != null){
            try {
                return new ImageIcon(image.getBinaryStream().readAllBytes());
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
