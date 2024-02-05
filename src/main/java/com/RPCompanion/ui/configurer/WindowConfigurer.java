package com.RPCompanion.ui.configurer;
import javax.swing.*;
import java.util.HashMap;

public class WindowConfigurer {
    public static void attachToWindow(JFrame window, HashMap<String, JPanel> panels){
        for(JPanel panel : panels.values()){
            window.add(panel);
        }
    }
}
