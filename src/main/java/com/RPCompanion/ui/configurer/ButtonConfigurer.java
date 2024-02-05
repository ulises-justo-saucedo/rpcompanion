package com.RPCompanion.ui.configurer;
import javax.swing.*;
import java.util.HashMap;

public class ButtonConfigurer {
    public static void setFont(HashMap<String, JButton> buttons, int fontSize){
        for(JButton button : buttons.values()){
            button.setFont(Fonts.personalizedFont(fontSize));
        }
    }
}
