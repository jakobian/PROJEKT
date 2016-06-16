package ui.menu.mainmenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jakub on 14.05.2016.
 */

/**
 * Metoda opisujaca menu instrukcji
 */
public class InstructionMenu extends JDialog {

    /**
     * Pola przechowujace nazwe "To move left, press left arrow" wyswietlanego w oknie instrukcji
     */
    public final static String PressKeyA = "To move left, press left arrow";
    /**
     * Pola przechowujace nazwe "To move up, press up arrow" wyswietlanego w oknie instrukcji
     */
    public final static String PressKeyW = "To move up, press up arrow";
    /**
     * Pola przechowujace nazwe "To move up, press right arrow" wyswietlanego w oknie instrukcji
     */
    public final static String PressKeyD = "To move right, press right arrow";

    /**
     * Pole inicjujace panel w ktorym umieszczone sa napisy menu pauzy
     */
    private JPanel panel;
    /**
     * Pole inicjujace napis "up arrow"
     */
    private JLabel keyW;
    /**
     * Pole inicjujace napis "left arrow"
     */
    private JLabel keyA;
    /**
     * Pole inicjujace napis "right arrow"
     */
    private JLabel keyD;

    public InstructionMenu(){
        createPanel();
        drawView();
    }
    /**
     * Metoda tworzaca wnetrze okna dialogowego menu pauzy
     */
    private void createPanel(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); // pionowo;
        keyA = new JLabel(PressKeyA);
        keyW = new JLabel(PressKeyW);
        keyD = new JLabel(PressKeyD);

        panel.add(keyA);
        panel.add(keyW);
        panel.add(keyD);
    }

    /**
     * Metoda wyswitlajaca okno menu pauzy
     */
    private void drawView(){
        this.add(panel);
        this.setSize(200, 80);
        setResizable(false);
        setTitle("Instruction");
        this.setLocationRelativeTo(null);
    }

}
