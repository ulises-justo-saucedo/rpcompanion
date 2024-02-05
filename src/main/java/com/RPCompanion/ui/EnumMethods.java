package com.RPCompanion.ui;

public class EnumMethods {
    public static String[] getEnumNames(Enum<?>[] enumValues){
        String[] enumNames = new String[enumValues.length];
        for(int i = 0 ; i < enumValues.length ; i++){
            enumNames[i] = enumValues[i].name();
        }
        return enumNames;
    }
}
