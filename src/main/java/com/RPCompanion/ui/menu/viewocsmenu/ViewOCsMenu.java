package com.RPCompanion.ui.menu.viewocsmenu;
import com.RPCompanion.converters.BlobConverter;
import com.RPCompanion.entities.RPCharacterEntity;
import com.RPCompanion.ui.configurer.*;
import com.RPCompanion.ui.window.Window;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ViewOCsMenu {
    private final String BUTTON_SEE_CHARACTER = "Inspect";
    private final String BUTTON_WRITE_STORY = "Write Story";
    private final String BUTTON_MODIFY_CHARACTER = "Modify";
    private final String BUTTON_DELETE_CHARACTER = "Delete";
    private final int BUTTONS_FOR_EACH_PANEL = 4;
    private final Window WINDOW;
    private JPanel card;
    private JPanel mainContainer;
    private JScrollPane mainContainerScroller;
    private List<JPanel> charactersContainer;
    private List<JLabel> charactersImageAndName;
    private List<List<JButton>> myButtons;
    private JPanel mainMenuButtonContainer;
    private JButton mainMenuButton;
    private int characters;
    public ViewOCsMenu(Window window, Connection c){
        this.WINDOW = window;
        this.charactersContainer = new ArrayList<>();
        this.charactersImageAndName = new ArrayList<>();
        this.myButtons = new ArrayList<>();
        this.card = new JPanel();
        this.mainContainer = new JPanel();
        this.mainContainerScroller = new JScrollPane(mainContainer);
        this.mainMenuButtonContainer = new JPanel();
        this.mainMenuButton = new JButton("Go back to Main Menu");
    }
    public void prepareViewOCsMenu(int characters,List<RPCharacterEntity> rpCharacterEntities){
        setCharacters(characters);
        clearLists();
        clearMainContainerPanel();
        initializeLists();
        fillLists(rpCharacterEntities);
        PanelConfigurer.setBackgroundColor(charactersContainer, Colors.TWITCH_PURPLE);
        LabelConfigurer.setFont(charactersImageAndName,24);
        ButtonConfigurer.setFont(myButtons,18,BUTTONS_FOR_EACH_PANEL);

        mainMenuButton.addActionListener(ViewOCsMenuEvents.mainMenuButton(WINDOW));
        mainMenuButton.setFont(Fonts.personalizedFont(20));

        mainContainer.setLayout(new GridLayout(0,1));
        for(JPanel characterContainer : charactersContainer){
            mainContainer.add(characterContainer);
        }

        mainContainerScroller.setAutoscrolls(true);
        mainContainerScroller.getVerticalScrollBar().setUnitIncrement(16);

        mainMenuButtonContainer.setBackground(Colors.TWITCH_PURPLE);
        mainMenuButtonContainer.setLayout(new GridLayout());
        mainMenuButtonContainer.add(mainMenuButton);

        card.setLayout(new BorderLayout());
        card.add(BorderLayout.CENTER,mainContainerScroller);
        card.add(BorderLayout.SOUTH,mainMenuButtonContainer);

        /*if(WINDOW.containsComponent("viewOCsMenu") != null){
            WINDOW.remove(WINDOW.containsComponent("viewOCsMenu"));
        }*/
        WINDOW.addMenu(card,"viewOCsMenu");
    }
    public void setCharacters(int characters){
        this.characters = characters;
    }
    public void clearLists(){
        charactersContainer.clear();
        charactersImageAndName.clear();
        myButtons.clear();
    }
    public void clearMainContainerPanel(){
        mainContainer.removeAll();
    }
    public void initializeLists(){
        for(int i = 0 ; i < characters ; i++){
            charactersContainer.add(new JPanel());
            charactersImageAndName.add(new JLabel());
            List<JButton> buttons = new ArrayList<>();
            buttons.add(new JButton(BUTTON_SEE_CHARACTER));
            buttons.add(new JButton(BUTTON_WRITE_STORY));
            buttons.add(new JButton(BUTTON_MODIFY_CHARACTER));
            buttons.add(new JButton(BUTTON_DELETE_CHARACTER));
            myButtons.add(buttons);
        }
    }
    public void fillLists(List<RPCharacterEntity> rpCharacterEntities){
        for(int i = 0 ; i < rpCharacterEntities.size() ; i++){
            RPCharacterEntity characterEntity = rpCharacterEntities.get(i);
            JLabel characterImageAndName = charactersImageAndName.get(i);
            List<JButton> buttons = myButtons.get(i);
            JPanel characterContainer = charactersContainer.get(i);

            characterImageAndName.setText(characterEntity.getName()+" "+characterEntity.getSurname());
            characterImageAndName.setHorizontalTextPosition(JLabel.CENTER);
            characterImageAndName.setVerticalTextPosition(JLabel.TOP);
            characterImageAndName.setIconTextGap(25);
            characterImageAndName.setIcon(ImageConfigurer.resizeImage(BlobConverter.convertBlobToImage(characterEntity.getAspect()).getImage(),256,256));

            characterContainer.add(BorderLayout.NORTH,characterImageAndName);
            for(int j = 0 ; j < BUTTONS_FOR_EACH_PANEL ; j++){
                characterContainer.add(BorderLayout.CENTER,buttons.get(j));
            }
        }
    }
}
