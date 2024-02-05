package com.RPCompanion.ui.factory;

import javax.swing.*;
import java.util.HashMap;

public class LabelFactory {
    public static HashMap<String, JLabel> instantiateLabels(String[] keys){
        HashMap<String, JLabel> labels = new HashMap<>();
        for(String key : keys){
            labels.put(key,new JLabel());
        }
        return labels;
    }
}
