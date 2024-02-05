package com.RPCompanion.ui.configurer;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class LabelConfigurer {
    public static void setTextColor(HashMap<String, JLabel> labels, Color color){
        for(JLabel label : labels.values()){
            label.setForeground(color);
        }
    }
    public static void setFont(HashMap<String, JLabel> labels, int fontSize){
        for(JLabel label : labels.values()){
            label.setFont(Fonts.personalizedFont(fontSize));
        }
    }
}
