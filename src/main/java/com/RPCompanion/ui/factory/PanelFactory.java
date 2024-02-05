package com.RPCompanion.ui.factory;

import javax.swing.*;
import java.util.HashMap;

public class PanelFactory {
    public static HashMap<String, JPanel> instantiatePanels(String[] keys){
        HashMap<String, JPanel> panels = new HashMap<>();
        for(String key : keys){
            panels.put(key,new JPanel());
        }
        return panels;
    }
}
