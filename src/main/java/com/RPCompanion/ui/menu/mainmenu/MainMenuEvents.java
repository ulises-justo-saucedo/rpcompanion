package com.RPCompanion.ui.menu.mainmenu;

import com.RPCompanion.services.RPCharacterService;
import com.RPCompanion.ui.menu.viewocsmenu.ViewOCsMenu;
import com.RPCompanion.ui.window.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuEvents {
    private RPCharacterService rpCharacterService;
    public MainMenuEvents(RPCharacterService rpCharacterService){
        this.rpCharacterService = rpCharacterService;
    }
    public ActionListener createCharacterButton(Window window){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.showMenu("rpCharacterMenu");
                window.repaint();
                window.revalidate();
            }
        };
    }
    public ActionListener viewOCSButton(Window window, ViewOCsMenu viewOCsMenu){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewOCsMenu.prepareViewOCsMenu(rpCharacterService.countRegisters(),rpCharacterService.selectAll());
                window.showMenu("viewOCsMenu");
                window.repaint();
                window.revalidate();
            }
        };
    }
}
