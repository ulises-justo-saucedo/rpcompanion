package com.RPCompanion.ui.configurer;
import javax.swing.*;
import java.awt.*;

public class ImageConfigurer {
    public static ImageIcon resizeImage(Image image,int width,int height){
        return new ImageIcon(image.getScaledInstance(width,height,Image.SCALE_DEFAULT));
    }
}
