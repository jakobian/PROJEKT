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
public class InstructionMenu extends JDialog {

    /**
     * Pola przechowujace nazwe "To move left, press key "A"" wyswietlanego w oknie instrukcji
     */
    public final static String PressKeyA = "To move left, press key \"A\"";
    /**
     * Pola przechowujace nazwe "To move up, press key "W"" wyswietlanego w oknie instrukcji
     */
    public final static String PressKeyW = "To move up, press key \"W\"";
    /**
     * Pola przechowujace nazwe "To move up, press key "W"" wyswietlanego w oknie instrukcji
     */
    public final static String PressKeyD = "To move right, press key \"D\"";

    /**
     * Pole inicjujace panel w ktorym umieszczone sa napisy menu pauzy
     */
    private JPanel panel;
    /**
     * Pole inicjujace napis "KeyW"
     */
    private JLabel keyW;
    /**
     * Pole inicjujace napis "KeyA"
     */
    private JLabel keyA;
    /**
     * Pole inicjujace napis "KeyD"
     */
    private JLabel keyD;

    public BufferedImage imgD;

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
        this.setSize(180, 80);
        setResizable(false);
        setTitle("Instruction");
        this.setLocationRelativeTo(null);
    }

}
