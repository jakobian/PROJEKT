package ui.menu;


import ui.menu.mainmenu.MainMenu;
import ui.menu.pausemenu.PauseMenu;
import ui.menu.startmenu.StartMenu;

import javax.swing.*;
import java.awt.*;


/** Klasa menu okna gry */
public class SmallGMenu extends JPanel {

    /**
     * Pola przechowujace nazwe przycisku "Start" wyswietlanego w menu okna gry
     */
    public final static String Start = "Start";
    /**
     * Pola przechowujace nazwe przycisku "Menu" wyswietlanego w menu okna gry
     */
    public final static String Menu = "Menu";
    /**
     * Pola przechowujace nazwe przycisku "Pause" wyswietlanego w menu okna gry
     */
    public final static String Pause = "Pause";

    /**
     * Pole inicjujace przycik "Start" okna gry
     */
    JButton startbutton;
    /**
     * Pole inicjujace przycik "Menu" okna gry
     */
    JButton menubutton;
    /**
     * Pole inicjujace przycik "Pause" okna gry
     */
    JButton pausebutton;
    /**
     * Pole inicjujace mainmenu
     */
    MainMenu mainmenu;
    /**
     * Pole inicjujace pausemenu
     */
    PauseMenu pausemenu;
    /**
     * Pole inicjujace startgame
     */
    StartMenu startgame;



    /**
     * Konstruktor menu okna gry
     */
    public SmallGMenu(){
        createButton();
    }

    /**
     * Metoda tworzaca przyciski menu okna gry
     */
    private void createButton(){
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        startbutton = new JButton(Start);
        menubutton = new JButton(Menu);
        pausebutton = new JButton(Pause);
        add(startbutton);
        add(menubutton);
        add(pausebutton);
        setupEvent();
    }

    /**
     * Metoda wywolujaca akcje po nacisnieciu danego przycisku
     */
    private void setupEvent(){
        startbutton.addActionListener(e -> initStart());
        menubutton.addActionListener(e -> initMenu());
        pausebutton.addActionListener(e -> initPause());

    }

    /**
     * Metoda inicjujaca i wyswietlajaca okno menu glownego
     */
    private void initMenu(){
        mainmenu = new MainMenu();
        mainmenu.setModal(true);
        mainmenu.setVisible(true);
    }
    /**
     * Metoda inicjujaca i wyswietlajaca okno menu pauzy
     */
    private void initPause(){
        pausemenu = new PauseMenu();
        pausemenu.setModal(true);
        pausemenu.setVisible(true);
    }
    /**
     * Metoda inicjujaca i wyswietlajaca okno menu start
     */
    private void initStart(){
        startgame = new StartMenu();
        startgame.setModal(true);
        startgame.setVisible(true);
    }

    /**
     *  Metoda ustawiajaca wielkosc przyciskow menu okna gry
     */
    public void setDimensionButton(int width, int height){
        startbutton.setPreferredSize(new Dimension(width, height));
        menubutton.setPreferredSize(new Dimension(width, height));
        pausebutton.setPreferredSize(new Dimension(width, height));

        startbutton.setRequestFocusEnabled(false);
        menubutton.setRequestFocusEnabled(false);
        pausebutton.setRequestFocusEnabled(false);
    }

}