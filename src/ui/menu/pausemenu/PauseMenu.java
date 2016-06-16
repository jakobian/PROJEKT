package ui.menu.pausemenu;

import javax.swing.*;

/**
 * Created by Jakub on 13.05.2016.
 */

/**
 * Klasa opisujaca menu pauzy
 */
public class PauseMenu extends JDialog {

    /**
     * Pola przechowujace nazwe "Press "P" key to pause" wyswietlanego w menu pauzy
     */
    public final static String TakeAbreak = "Press \"P\" key to pause";
    /**
     * Pola przechowujace nazwe "Press any key to continue" wyswietlanego w menu pauzy
     */
    public final static String PressKey = "Press \"P\" key to continue";


    /**
     * Pole inicjujace panel w ktorym umieszczone sa napisy menu pauzy
     */
    private JPanel panel;
    /**
     * Pole inicjujace napis "Press "P" key to pause"
     */
    private JLabel takeAbreak;
    /**
     * Pole inicjujace napis "Press "P" key to continue"
     */
    private JLabel presskey;

    /**
     * Konstruktor okna pauzy
     */
    public PauseMenu(){
        createPanel();
        drawView();
    }

    /**
     * Metoda tworzaca wnetrze okna dialogowego menu pauzy
     */
    private void createPanel(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        takeAbreak = new JLabel(TakeAbreak);
        presskey = new JLabel(PressKey);

        panel.add(takeAbreak);
        panel.add(presskey);
    }

    /**
     * Metoda wyswitlajaca okno menu pauzy
     */
    private void drawView(){
        this.add(panel);
        this.setSize(180, 60);
        setResizable(false);
        setTitle("Pause");
        this.setLocationRelativeTo(null);
    }
}