package com.RPCompanion.ui.menu.viewocsmenu;

import com.RPCompanion.ui.window.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewOCsMenuEvents {
    public static ActionListener mainMenuButton(Window window){
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
