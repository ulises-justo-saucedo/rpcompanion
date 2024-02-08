package com.RPCompanion.ui.configurer;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class ButtonConfigurer {
    public static void setFont(HashMap<String, JButton> buttons, int fontSize){
        for(JButton button : buttons.values()){
            button.setFont(Fonts.personalizedFont(fontSize));
        }
    }
    public static void setFont(List<List<JButton>> buttons, int fontSize, int buttonsQuantity){
        for(int i = 0 ; i < buttons.size() ; i++){
            for(int j = 0 ; j < buttonsQuantity ; j++){
                buttons.get(i).get(j).setFont(Fonts.personalizedFont(fontSize));
            }
        }
    }
}
