package com.RPCompanion.ui.menu.createrpcharactermenu;

import com.RPCompanion.ui.window.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateRPCharacterMenuEvents {
    public static ActionListener cancelButton(Window window){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.showMenu("mainMenu");
                window.repaint();
                window.revalidate();
            }
        };
    }
}
