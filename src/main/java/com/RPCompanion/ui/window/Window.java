package com.RPCompanion.ui.window;
import com.RPCompanion.ui.CustomFont;
import javax.swing.*;
import java.awt.*;
public class Window extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public Window(int width,int height,String title){
        this.cardLayout = new CardLayout();
        this.mainContainer = new JPanel();
        CustomFont customFont = new CustomFont();
        configureFrame(width,height,title);
    }
    public void configureFrame(int width,int height,String title){
        setTitle(title);
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(this.mainContainer);
        this.mainContainer.setLayout(this.cardLayout);
    }
    public void configureMainPanel(){

    }
    public void display(){
        setVisible(true);
    }
    public void addMenu(JPanel menu,String menuName){
        mainContainer.add(menu,menuName);
    }
    public void showMenu(String menuName){
        cardLayout.show(mainContainer,menuName);
    }
}
