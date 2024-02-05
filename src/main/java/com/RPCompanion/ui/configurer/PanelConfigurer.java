package com.RPCompanion.ui.configurer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PanelConfigurer {
    public static void setBackgroundColor(HashMap<String, JPanel> panels, Color color){
        for(JPanel panel : panels.values()){
            panel.setBackground(color);
        }
    }
    public static void addLabelsToPanel(JPanel panel,HashMap<String, JLabel> labels){
        for(JLabel label : labels.values()){
            panel.add(label);
        }
    }
    public static void addButtonsToPanel(JPanel panel,HashMap<String, JButton> buttons){
        for(JButton button : buttons.values()){
            panel.add(button);
        }
    }
}
