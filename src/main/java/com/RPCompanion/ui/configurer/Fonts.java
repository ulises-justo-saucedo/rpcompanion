package com.RPCompanion.ui.configurer;

import com.RPCompanion.ui.CustomFont;

import java.awt.*;

public class Fonts {
    public static Font personalizedFont(int fontSize){
        return new Font(CustomFont.getFontName(),Font.PLAIN,fontSize);
    }
}
