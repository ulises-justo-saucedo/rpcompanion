package com.RPCompanion.ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomFont {
    private static String fontName;
    public CustomFont(){
        fontName = createFont();
    }
    private String createFont(){
        File file = new File("src/main/resources/font/zhcn.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,file);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        return font.getName();
    }
    public static String getFontName(){
        return fontName;
    }
}
