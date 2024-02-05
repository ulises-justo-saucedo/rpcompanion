package com.RPCompanion.ui.factory;

import javax.swing.*;
import java.util.HashMap;

public class ComponentFactory {
    public static HashMap<String, JButton> instantiateButtons(String[] keys){
        HashMap<String, JButton> buttons = new HashMap<>();
        for(String key : keys){
            buttons.put(key,new JButton());
        }
        return buttons;
    }
    public static HashMap<String, JLabel> instantiateLabels(String[] keys){
        HashMap<String, JLabel> labels = new HashMap<>();
        for(String key : keys){
            labels.put(key,new JLabel());
        }
        return labels;
    }
    public static HashMap<String, JPanel> instantiatePanels(String[] keys){
        HashMap<String, JPanel> panels = new HashMap<>();
        for(String key : keys){
            panels.put(key,new JPanel());
        }
        return panels;
    }
}
