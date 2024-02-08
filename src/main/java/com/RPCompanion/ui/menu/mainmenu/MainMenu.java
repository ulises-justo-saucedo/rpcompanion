package com.RPCompanion.ui.menu.mainmenu;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.services.RPCharacterService;
import com.RPCompanion.ui.EnumMethods;
import com.RPCompanion.ui.constants.MenuNames;
import com.RPCompanion.ui.configurer.*;
import com.RPCompanion.ui.constants.Routes;
import com.RPCompanion.ui.factory.ComponentFactory;
import com.RPCompanion.ui.menu.createrpcharactermenu.CreateRPCharacterMenu;
import com.RPCompanion.ui.menu.viewocsmenu.ViewOCsMenu;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.HashMap;
public class MainMenu {
    private final com.RPCompanion.ui.window.Window WINDOW;
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
    private MainMenuEvents mainMenuEvents;
    private ViewOCsMenu viewOCsMenu;
    private RPCharacterService rpCharacterService;
    public MainMenu(com.RPCompanion.ui.window.Window window, Connection c, RPCharacterService rpCharacterService) throws PropertiesFileException, DatabaseAccessException {
        this.WINDOW = window;
        this.container = new JPanel();
        this.rpCharacterService = rpCharacterService;
        this.mainMenuEvents = new MainMenuEvents(rpCharacterService);
        new CreateRPCharacterMenu(WINDOW,c,rpCharacterService);
        this.viewOCsMenu = new ViewOCsMenu(WINDOW,c,rpCharacterService);
        initializeComponents();
        configurePanels();
        configureLabels();
        configureButtons();
        addLabelsAndButtonsToPanels();
    }
    private void initializeComponents(){
        this.panels = ComponentFactory.instantiatePanels(EnumMethods.getEnumNames(Panel.values()));
        this.labels = ComponentFactory.instantiateLabels(EnumMethods.getEnumNames(Label.values()));
        this.buttons = ComponentFactory.instantiateButtons(EnumMethods.getEnumNames(Button.values()));
    }
    private void configurePanels(){
        PanelConfigurer.setBackgroundColor(panels, Colors.TWITCH_PURPLE);
        panels.get(Panel.BUTTONS.name()).setLayout(new GridLayout());
    }
    private void configureLabels(){
        labels.get(Label.TITLE.name()).setText("Welcome to RPCompanion!");
        labels.get(Label.CONTENT.name()).setText("Your personal place to store your RP OC's!");
        Image scaledImage = new ImageIcon(Routes.IMAGE_LOGO_ROUTE).getImage().getScaledInstance(650,500,Image.SCALE_DEFAULT);
        labels.get(Label.CONTENT.name()).setIcon(new ImageIcon(scaledImage));
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
        buttons.get(Button.CREATE.name()).addActionListener(mainMenuEvents.createCharacterButton(WINDOW));
        buttons.get(Button.VIEW.name()).addActionListener(mainMenuEvents.viewOCSButton(WINDOW,viewOCsMenu));
    }
    private void addLabelsAndButtonsToPanels(){
        panels.get(Panel.TITLE.name()).add(labels.get(Label.TITLE.name()));
        panels.get(Panel.CONTENT.name()).add(labels.get(Label.CONTENT.name()));
        PanelConfigurer.addButtonsToPanel(panels.get(Panel.BUTTONS.name()),buttons);
        container.setLayout(new BorderLayout());
        container.add(BorderLayout.NORTH,panels.get(Panel.TITLE.name()));
        container.add(BorderLayout.CENTER,panels.get(Panel.CONTENT.name()));
        container.add(BorderLayout.SOUTH,panels.get(Panel.BUTTONS.name()));
        WINDOW.addMenu(container, MenuNames.MAIN_MENU_NAME);
        WINDOW.showMenu(MenuNames.MAIN_MENU_NAME);
    }
}