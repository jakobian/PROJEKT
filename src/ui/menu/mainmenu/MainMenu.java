package ui.menu.mainmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Jakub on 11.05.2016.
 */
public class MainMenu extends JDialog {


    /**
     * Pola przechowujace nazwe przycisku "Instruction" wyswietlanego w menu glownym
     */
    public final static String Instruction = "Instruction";
    /**
     * Pola przechowujace nazwe przycisku "BestResults" wyswietlanego w menu glownym
     */
    public final static String Results = "Results";
    /**
     * Pola przechowujace nazwe przycisku "Internet function" wyswietlanego w menu glownym
     */
    public final static String Internet = "Internet's function";
    /**
     * /**
     * Pola przechowujace nazwe przycisku "Quit" wyswietlanego w menu glownym
     */
    public final static String Quit = "Quit";


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
     * Pole inicjujace przycik "Internet's function" w menu
     */
    JButton internetnbutton;

    /**
     * Pole inicjujace panel do ktorego beda wstawiane przyciski w oknie dialogowym menu glownego
     */
    private JPanel panel;

    InstructionMenu instrmenu;
    ResultsMenu resMenu;


    /**
     * Konstruktor menu okna gry
     */
    public MainMenu() {
        createButton();
        drawView();
        setupEvent();
        setDimensionButton(300, 50);
    }

    /**
     * Metoda tworzaca przyciski menu okna gry
     */
    private void createButton() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); // pionowo;
        instructionbutton = new JButton(Instruction);
        resultsbutton = new JButton(Results);
        internetnbutton = new JButton(Internet);
        quitbutton = new JButton(Quit);
        panel.add(instructionbutton);
        panel.add(resultsbutton);
        panel.add(internetnbutton);
        panel.add(quitbutton);
    }

    private void drawView() {
        this.add(panel);
        this.setSize(300, 150);
        setResizable(false);
        setTitle("Menu");
        this.setLocationRelativeTo(null);
    }

    /**
     * Metoda ustawiajaca wielkosc przyciskow menu okna gry
     */
    public void setDimensionButton(int width, int height) {
        instructionbutton.setPreferredSize(new Dimension(width, height));
        resultsbutton.setPreferredSize(new Dimension(width, height));
        internetnbutton.setPreferredSize(new Dimension(width, height));
        quitbutton.setPreferredSize(new Dimension(width, height));

        instructionbutton.setMaximumSize(new Dimension(width, height));
        resultsbutton.setMaximumSize(new Dimension(width, height));
        internetnbutton.setMaximumSize(new Dimension(width, height));
        quitbutton.setMaximumSize(new Dimension(width, height));

        instructionbutton.setRequestFocusEnabled(false);
        resultsbutton.setRequestFocusEnabled(false);
        internetnbutton.setRequestFocusEnabled(false);
        quitbutton.setRequestFocusEnabled(false);
    }


    /**
     * Metoda wywolujaca akcje po nacisnieciu danego przycisku
     */
    private void setupEvent() {
        instructionbutton.addActionListener(e -> initInstrMenu());
        quitbutton.addActionListener(e ->  System.exit(0));
        resultsbutton.addActionListener(e -> {
            try {
                initResultsMenu();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * Metoda inicjujaca i wyswietlajaca okno menu glownego
     */
    private void initInstrMenu() {
        instrmenu = new InstructionMenu();
        instrmenu.setModal(true);
        instrmenu.setVisible(true);
    }

    /**
     * Metoda inicjujaca i wyswietlajaca okno menu rezultatow
     */
    private void initResultsMenu() throws IOException {
        resMenu = new ResultsMenu();
        resMenu.setModal(true);
        resMenu.setVisible(true);
    }
}
