package com.RPCompanion.ui.menu;
import com.RPCompanion.ui.EnumMethods;
import com.RPCompanion.ui.configurer.*;
import com.RPCompanion.ui.factory.ButtonFactory;
import com.RPCompanion.ui.factory.LabelFactory;
import com.RPCompanion.ui.factory.PanelFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
public class MainMenu {
    private final com.RPCompanion.ui.window.Window WINDOW;
    //private final String IMAGE_LOGO_ROUTE = "logo's not finished yet";
    private JPanel container;
    private HashMap<String, JPanel> panels;
    private enum Panel{
        TITLE, CONTENT, BUTTONS
    }
    private HashMap<String, JLabel> labels;
    private enum Label{
        TITLE, CONTENT
    }
    private HashMap<String, JButton> buttons;
    private enum Button{
        CREATE, VIEW
    }
    public MainMenu(com.RPCompanion.ui.window.Window window){
        this.WINDOW = window;
        this.container = new JPanel();
        new CreateRPCharacterMenu(WINDOW);
        initializeComponents();
        configurePanels();
        configureLabels();
        configureButtons();
        addLabelsAndButtonsToPanels();
    }
    private void initializeComponents(){
        this.panels = PanelFactory.instantiatePanels(EnumMethods.getEnumNames(Panel.values()));
        this.labels = LabelFactory.instantiateLabels(EnumMethods.getEnumNames(Label.values()));
        this.buttons = ButtonFactory.instantiateButtons(EnumMethods.getEnumNames(Button.values()));
    }
    private void configurePanels(){
        PanelConfigurer.setBackgroundColor(panels, Colors.TWITCH_PURPLE);
    }
    private void configureLabels(){
        labels.get(Label.TITLE.name()).setText("Welcome to RPCompanion!");
        labels.get(Label.CONTENT.name()).setText("Your personal place to store your RP OC's!");
        //Image scaledImage = new ImageIcon(IMAGE_LOGO_ROUTE).getImage().getScaledInstance(650,500,Image.SCALE_DEFAULT);
        //labels.get(Label.CONTENT.name()).setIcon(new ImageIcon(scaledImage));
        labels.get(Label.CONTENT.name()).setHorizontalTextPosition(SwingConstants.CENTER);
        labels.get(Label.CONTENT.name()).setVerticalTextPosition(SwingConstants.CENTER);
        labels.get(Label.CONTENT.name()).setVerticalTextPosition(SwingConstants.TOP);
        LabelConfigurer.setTextColor(labels, Colors.CUSTOM_WHITE);
        labels.get(Label.TITLE.name()).setFont(Fonts.personalizedFont(96));
        labels.get(Label.CONTENT.name()).setFont(Fonts.personalizedFont(48));
    }
    private void configureButtons(){
        buttons.get(Button.CREATE.name()).setText("Create new OC");
        buttons.get(Button.VIEW.name()).setText("View your OC's");
        ButtonConfigurer.setFont(buttons, 24);
        buttons.get(Button.CREATE.name()).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WINDOW.showMenu("rpCharacterMenu");
                WINDOW.repaint();
                WINDOW.revalidate();
            }
        });
    }
    private void addLabelsAndButtonsToPanels(){
        panels.get(Panel.TITLE.name()).add(labels.get(Label.TITLE.name()));
        panels.get(Panel.CONTENT.name()).add(labels.get(Label.CONTENT.name()));
        panels.get(Panel.BUTTONS.name()).setLayout(new GridLayout());
        PanelConfigurer.addButtonsToPanel(panels.get(Panel.BUTTONS.name()),buttons);
        container.add(panels.get(Panel.TITLE.name()));
        container.add(panels.get(Panel.CONTENT.name()));
        container.add(panels.get(Panel.BUTTONS.name()));
        WINDOW.addMenu(container,"mainMenu");
        WINDOW.showMenu("mainMenu");
    }
}