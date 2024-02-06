package com.RPCompanion.ui.menu.createrpcharactermenu;

import com.RPCompanion.database.connection.DatabaseConnection;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.services.RPCharacterService;
import com.RPCompanion.ui.window.Window;

import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class CreateRPCharacterMenuEvents {
    private RPCharacterService rpCharacterService;
    public CreateRPCharacterMenuEvents(Connection c) throws PropertiesFileException, DatabaseAccessException {
        this.rpCharacterService = new RPCharacterService(c);
    }
    public ActionListener cancelButton(Window window){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.showMenu("mainMenu");
                window.repaint();
                window.revalidate();
            }
        };
    }
    public ActionListener createButton(Window window,JTextField nameField,JTextField surnameField,JSpinner birthDateField,JSpinner ageField,JFileChooser aspectField){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Blob aspect = null;
                try {
                    //Convert user's image to bytes in order to insert it in the database
                    aspect = new SerialBlob(Files.readAllBytes(Path.of(aspectField.getSelectedFile().getAbsolutePath())));
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                RPCharacterEntity character = new RPCharacterEntity(nameField.getText(),surnameField.getText(),new java.sql.Date(((java.util.Date)birthDateField.getValue()).getTime()),(int) ageField.getValue(),aspect);
                try {
                    //This shouldn't fail. But it forces me to add the try/catch block
                    rpCharacterService.save(character);
                } catch (DatabaseAccessException ex) {
                    throw new RuntimeException(ex);
                }
                //If we get to this point, then user's character is already saved
                //Because of that we reset every single field of the formulary
                nameField.setText("");
                surnameField.setText("");
                birthDateField.setValue(new java.util.Date());
                ageField.setValue(0);
                aspectField.setSelectedFile(new File(""));

                window.showMenu("mainMenu");
                window.repaint();
                window.revalidate();
            }
        };
    }
    public ActionListener chooseImageButton(JFrame window,JFileChooser jFileChooser,JLabel imagePreview){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = jFileChooser.showOpenDialog(window);
                switch(input){
                    case JFileChooser.APPROVE_OPTION:
                        imagePreview.setIcon(new ImageIcon(jFileChooser.getSelectedFile().getAbsolutePath()));
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                }
            }
        };
    }
}
