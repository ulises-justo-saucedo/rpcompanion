package com.RPCompanion.ui.menu.mainmenu;

import com.RPCompanion.ui.window.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuEvents {
    public static ActionListener createCharacterButton(Window window){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.showMenu("rpCharacterMenu");
                window.repaint();
                window.revalidate();
            }
        };
    }
}
