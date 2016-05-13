package ui.menu;


import javax.swing.*;
import java.awt.*;

import sun.applet.Main;
import ui.menu.MainMenu;


/** Klasa menu okna gry */
public class SmallGMenu extends JPanel  {

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
     * Pole inicjujace przycik "Star" okna gry
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

    MainMenu mainmenu;


    /**
     * Konstruktor menu okna gry
     */
    public SmallGMenu(){
        createButton();
        setupEvent();
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

    }

    private void setupEvent(){
        menubutton.addActionListener(e -> initMenu());
    }

    private void initMenu(){
        mainmenu = new MainMenu();
        mainmenu.setVisible(true);
    }

    /**
     *  Metoda ustawiajaca wielkosc przyciskow menu okna gry
     */
    public void setDimensionButton(int width, int height){
        startbutton.setPreferredSize(new Dimension(width, height));
        menubutton.setPreferredSize(new Dimension(width, height));
        pausebutton.setPreferredSize(new Dimension(width, height));
    }

}
