package com.RPCompanion.ui.menu.createrpcharactermenu;

import com.RPCompanion.converters.ImageConverter;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.services.RPCharacterService;
import com.RPCompanion.ui.configurer.ImageConfigurer;
import com.RPCompanion.ui.window.Window;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;

public class CreateRPCharacterMenuEvents {
    private RPCharacterService rpCharacterService;
    public CreateRPCharacterMenuEvents(Connection c,RPCharacterService rpCharacterService) throws PropertiesFileException, DatabaseAccessException {
        this.rpCharacterService = rpCharacterService;
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
                RPCharacterEntity character = new RPCharacterEntity(nameField.getText(),surnameField.getText(),new java.sql.Date(((java.util.Date)birthDateField.getValue()).getTime()),(int) ageField.getValue(), ImageConverter.convertImageToBlob(Path.of(aspectField.getSelectedFile().getAbsolutePath())));
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
                        imagePreview.setIcon(ImageConfigurer.resizeImage(new ImageIcon(jFileChooser.getSelectedFile().getAbsolutePath()).getImage(),256,256));
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                }
            }
        };
    }
}
