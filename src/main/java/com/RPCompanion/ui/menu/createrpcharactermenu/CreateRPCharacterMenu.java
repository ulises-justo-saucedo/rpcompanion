package com.RPCompanion.ui.menu.createrpcharactermenu;
import com.RPCompanion.exceptions.DatabaseAccessException;
import com.RPCompanion.exceptions.PropertiesFileException;
import com.RPCompanion.services.RPCharacterService;
import com.RPCompanion.ui.EnumMethods;
import com.RPCompanion.ui.configurer.*;
import com.RPCompanion.ui.factory.ComponentFactory;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CreateRPCharacterMenu {
    private final com.RPCompanion.ui.window.Window WINDOW;
    private JPanel container;
    private HashMap<String, JPanel> panels;
    private enum Panel{
        TITLE, FORMULARY, IMAGE, BUTTONS
    }
    private HashMap<String, JLabel> labels;
    private enum Label{
        TITLE, NAME, SURNAME, BIRTHDATE, AGE, ASPECT, IMAGE
    }
    private HashMap<String, JTextField> textFields;
    private enum TextField{
        NAME, SURNAME
    }
    private HashMap<String, JButton> buttons;
    private enum Button{
        CHOOSE_IMAGE, CREATE, CANCEL
    }
    private JFileChooser jFileChooser;
    private JSpinner birthdateSpinner;
    private JSpinner ageSpinner;
    private CreateRPCharacterMenuEvents createRPCharacterMenuEvents;
    private RPCharacterService rpCharacterService;
    public CreateRPCharacterMenu(com.RPCompanion.ui.window.Window window, Connection c,RPCharacterService rpCharacterService) throws PropertiesFileException, DatabaseAccessException {
        this.WINDOW = window;
        this.container = new JPanel();
        this.rpCharacterService = rpCharacterService;
        this.createRPCharacterMenuEvents = new CreateRPCharacterMenuEvents(c,rpCharacterService);
        initializeComponents();
        initializeSpinners();
        initializeFileChooser();
        configureButtons();
        configureLabels();
        configurePanels();
        addComponentsToPanels();
    }
    public void initializeComponents(){
        this.panels = ComponentFactory.instantiatePanels(EnumMethods.getEnumNames(Panel.values()));
        this.labels = ComponentFactory.instantiateLabels(EnumMethods.getEnumNames(Label.values()));
        this.textFields = ComponentFactory.instantiateTextFields(EnumMethods.getEnumNames(TextField.values()));
        this.buttons = ComponentFactory.instantiateButtons(EnumMethods.getEnumNames(Button.values()));
        panels.get(Panel.FORMULARY.name()).setLayout(new GridLayout(0,2));
        panels.get(Panel.BUTTONS.name()).setLayout(new GridLayout());
    }
    public void initializeSpinners(){
        SpinnerModel spinnerDateModel = new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_MONTH);
        this.birthdateSpinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthdateSpinner,"dd/MM/yyyy");
        this.birthdateSpinner.setEditor(dateEditor);
        this.ageSpinner = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
    }
    public void initializeFileChooser(){
        this.jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter("JPG & JPEG Images","jpg","jpeg"));
    }
    public void configurePanels(){
        PanelConfigurer.setBackgroundColor(panels, Colors.TWITCH_PURPLE);
    }
    public void configureLabels(){
        labels.get(Label.TITLE.name()).setText("Creating new OC!");
        labels.get(Label.NAME.name()).setText("Name:");
        labels.get(Label.SURNAME.name()).setText("Surname:");
        labels.get(Label.BIRTHDATE.name()).setText("Birthdate:");
        labels.get(Label.AGE.name()).setText("Age:");
        labels.get(Label.ASPECT.name()).setText("Aspect:");
        LabelConfigurer.setFont(labels,28);
        LabelConfigurer.setTextColor(labels,Colors.CUSTOM_WHITE);
        labels.get(Label.TITLE.name()).setFont(Fonts.personalizedFont(96));
    }
    public void configureButtons(){
        buttons.get(Button.CREATE.name()).setText("Create");
        buttons.get(Button.CANCEL.name()).setText("Cancel");
        buttons.get(Button.CHOOSE_IMAGE.name()).setText("Choose image");
        ButtonConfigurer.setFont(buttons,24);
        buttons.get(Button.CHOOSE_IMAGE.name()).addActionListener(createRPCharacterMenuEvents.chooseImageButton(WINDOW,jFileChooser,labels.get(Label.IMAGE.name())));
        buttons.get(Button.CREATE.name()).addActionListener(createRPCharacterMenuEvents.createButton(WINDOW,textFields.get(TextField.NAME.name()),textFields.get(TextField.SURNAME.name()),birthdateSpinner,ageSpinner,jFileChooser));
        buttons.get(Button.CANCEL.name()).addActionListener(createRPCharacterMenuEvents.cancelButton(WINDOW));
    }
    public void addComponentsToPanels(){
        panels.get(Panel.FORMULARY.name()).add(labels.get(Label.NAME.name()));
        panels.get(Panel.FORMULARY.name()).add(textFields.get(TextField.NAME.name()));
        panels.get(Panel.FORMULARY.name()).add(labels.get(Label.SURNAME.name()));
        panels.get(Panel.FORMULARY.name()).add(textFields.get(TextField.SURNAME.name()));
        panels.get(Panel.FORMULARY.name()).add(labels.get(Label.AGE.name()));
        panels.get(Panel.FORMULARY.name()).add(ageSpinner);
        panels.get(Panel.FORMULARY.name()).add(labels.get(Label.BIRTHDATE.name()));
        panels.get(Panel.FORMULARY.name()).add(birthdateSpinner);
        panels.get(Panel.FORMULARY.name()).add(labels.get(Label.ASPECT.name()));
        PanelConfigurer.addButtonsToPanel(panels.get(Panel.BUTTONS.name()),buttons);
        panels.get(Panel.FORMULARY.name()).add(buttons.get(Button.CHOOSE_IMAGE.name()));
        panels.get(Panel.TITLE.name()).add(labels.get(Label.TITLE.name()));
        panels.get(Panel.IMAGE.name()).add(labels.get(Label.IMAGE.name()));
        container.setLayout(new BorderLayout());
        container.add(BorderLayout.NORTH,panels.get(Panel.TITLE.name()));
        container.add(BorderLayout.WEST,panels.get(Panel.FORMULARY.name()));
        container.add(BorderLayout.CENTER,panels.get(Panel.IMAGE.name()));
        container.add(BorderLayout.SOUTH,panels.get(Panel.BUTTONS.name()));
        WINDOW.addMenu(container,"rpCharacterMenu");
    }
}
