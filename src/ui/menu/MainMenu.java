package ui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Jakub on 11.05.2016.
 */
public class MainMenu extends JPanel {

    /**
     * Pola przechowujace nazwe przycisku "Start" wyswietlanego w menu glownym
     */
    public final static String Start = "Start";
    /**
     * Pola przechowujace nazwe przycisku "Instruction" wyswietlanego w menu glownym
     */
    public final static String Instruction = "Instruction";

    /**
     * Pola przechowujace nazwe przycisku "BestResults" wyswietlanego w menu glownym
     */
    public final static String Results = "Results";
    /**
     * Pola przechowujace nazwe przycisku "BestResults" wyswietlanego w menu glownym
     */
    public final static String Quit = "Quit";


    /**
     * Pole inicjujace przycik "start" w menu
     */
    JButton startbutton;
    /**
     * Pole inicjujace przycik "instruction" w menu
     */
    JButton instructionbutton;
    /**
     * Pole inicjujace przycik "results" w menu
     */
    JButton resultsbutton;
    /**
     * Pole inicjujace przycik "Quit" w menu
     */
    JButton quitbutton;


    /**
     * Konstruktor menu okna gry
     */
    public MainMenu(){
        createButton();
    }

    /**
     * Metoda tworzaca przyciski menu okna gry
     */
    private void createButton(){
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        startbutton = new JButton(Start);
        instructionbutton = new JButton(Instruction);
        resultsbutton = new JButton(Results);
        quitbutton = new JButton(Quit);
        add(startbutton);
        add(instructionbutton);
        add(resultsbutton);
        add(quitbutton);

    }

    /**
     *  Metoda ustawiajaca wielkosc przyciskow menu okna gry
     */
    public void setDimensionButton(int width, int height){
        startbutton.setPreferredSize(new Dimension(width, height));
        instructionbutton.setPreferredSize(new Dimension(width, height));
        resultsbutton.setPreferredSize(new Dimension(width, height));
        quitbutton.setPreferredSize(new Dimension(width, height));
    }
}
