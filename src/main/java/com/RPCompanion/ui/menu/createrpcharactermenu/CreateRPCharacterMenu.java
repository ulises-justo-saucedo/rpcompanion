package com.RPCompanion.ui.menu.createrpcharactermenu;
import com.RPCompanion.ui.EnumMethods;
import com.RPCompanion.ui.configurer.*;
import com.RPCompanion.ui.factory.ComponentFactory;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CreateRPCharacterMenu {
    private final com.RPCompanion.ui.window.Window WINDOW;
    private JPanel container;
    private HashMap<String, JPanel> panels;
    private enum Panel{
        TITLE, FORMULARY, BUTTONS
    }
    private HashMap<String, JLabel> labels;
    private enum Label{
        TITLE, NAME, SURNAME, BIRTHDATE, AGE, STORY, ASPECT
    }
    private HashMap<String, JButton> buttons;
    private enum Button{
        CREATE, CANCEL
    }
    public CreateRPCharacterMenu(com.RPCompanion.ui.window.Window window){
        this.WINDOW = window;
        this.container = new JPanel();
        initializeComponents();
        configureButtons();
        configureLabels();
        configurePanels();
        addComponentsToPanels();
    }
    public void initializeComponents(){
        this.panels = ComponentFactory.instantiatePanels(EnumMethods.getEnumNames(Panel.values()));
        this.labels = ComponentFactory.instantiateLabels(EnumMethods.getEnumNames(Label.values()));
        this.buttons = ComponentFactory.instantiateButtons(EnumMethods.getEnumNames(Button.values()));
        panels.get(Panel.BUTTONS.name()).setLayout(new GridLayout());
    }
    public void configurePanels(){
        PanelConfigurer.setBackgroundColor(panels, Colors.TWITCH_PURPLE);
        panels.get(Panel.FORMULARY.name()).setLayout(new BoxLayout(panels.get(Panel.FORMULARY.name()),BoxLayout.X_AXIS));
    }
    public void configureLabels(){
        labels.get(Label.TITLE.name()).setText("Creating new OC!");
        labels.get(Label.NAME.name()).setText("Name:");
        labels.get(Label.SURNAME.name()).setText("Surname:");
        labels.get(Label.BIRTHDATE.name()).setText("Birthdate:");
        labels.get(Label.AGE.name()).setText("Age:");
        labels.get(Label.STORY.name()).setText("Story:");
        labels.get(Label.ASPECT.name()).setText("Aspect:");
        LabelConfigurer.setFont(labels,12);
        LabelConfigurer.setTextColor(labels,Colors.CUSTOM_WHITE);
        labels.get(Label.TITLE.name()).setFont(Fonts.personalizedFont(96));
    }
    public void configureButtons(){
        buttons.get(Button.CREATE.name()).setText("Create");
        buttons.get(Button.CANCEL.name()).setText("Cancel");
        ButtonConfigurer.setFont(buttons,24);
        buttons.get(Button.CANCEL.name()).addActionListener(CreateRPCharacterMenuEvents.cancelButton(WINDOW));
    }
    public void addComponentsToPanels(){
        panels.get(Panel.TITLE.name()).add(labels.get(Label.TITLE.name()));
        PanelConfigurer.addLabelsToPanel(panels.get(Panel.FORMULARY.name()),labels);
        PanelConfigurer.addButtonsToPanel(panels.get(Panel.BUTTONS.name()),buttons);
        container.setLayout(new BorderLayout());
        container.add(BorderLayout.NORTH,panels.get(Panel.TITLE.name()));
        container.add(BorderLayout.CENTER,panels.get(Panel.FORMULARY.name()));
        container.add(BorderLayout.SOUTH,panels.get(Panel.BUTTONS.name()));
        WINDOW.addMenu(container,"rpCharacterMenu");
    }
}
