package com.RPCompanion.ui.factory;

import javax.swing.*;
import java.util.HashMap;

public class ButtonFactory {
    public static HashMap<String, JButton> instantiateButtons(String[] keys){
        HashMap<String, JButton> buttons = new HashMap<>();
        for(String key : keys){
            buttons.put(key,new JButton());
        }
        return buttons;
    }
}
