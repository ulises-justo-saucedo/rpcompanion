package com.RPCompanion.ui.menu.viewocsmenu;

import com.RPCompanion.services.RPCharacterService;
import com.RPCompanion.ui.constants.MenuNames;
import com.RPCompanion.ui.window.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewOCsMenuEvents {
    private RPCharacterService rpCharacterService;
    public ViewOCsMenuEvents(RPCharacterService rpCharacterService){
        this.rpCharacterService = rpCharacterService;
    }
    public ActionListener mainMenuButton(Window window){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.showMenu(MenuNames.MAIN_MENU_NAME);
                window.repaint();
                window.revalidate();
            }
        };
    }
    public ActionListener deleteCharacterButton(int characterId,ViewOCsMenu viewOCsMenu,Window window){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rpCharacterService.deleteByID(characterId);
                viewOCsMenu.prepareViewOCsMenu(rpCharacterService.countRegisters(),rpCharacterService.selectAll());
                window.showMenu(MenuNames.VIEW_OCS_MENU_NAME);
                window.repaint();
                window.revalidate();
            }
        };
    }
}
