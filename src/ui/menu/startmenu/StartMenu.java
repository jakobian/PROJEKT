package ui.menu.startmenu;

import ui.results.UserResult;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import ui.gamefield.GameField;

/**
 * Created by Jakub on 13.05.2016.
 */
public class StartMenu extends JDialog{


    /**
     * Pola przechowujace nazwe pola informacyjnego infotext
     */
    public final static String InfoText = "Type your name";
    /**
     * Pola przechowujace nazwe pola nametextfield do wypelniena "Fill the gap"
     */
    public final static String FillTheGap = "Fill the gap";
    /**
     * Pola przechowujace nazwe przycisku "Ok"
     */
    public final static String OkText = "Ok";
    /**
     * Pole inicjujace panel w ktorym umieszczone sa poszczegolne kontenery -
     * przycisk ok, tekst, miejsce na nazwe
     */
    JPanel panel;
    /**
     * Pole inicjujace napis informacyjny "Type your name"
     */
    JLabel infotext;
    /**
     * Pole inicjujace napis wpisany w polu na nazwe uzytkownika -"Fill the gap"
     */
    JTextField nametextfield;
    /**
     * Pole inicjujace przycik "Ok"
     */
    JButton okbutton;

    private UserResult userResult;

    public StartMenu(){
        createPanel();
        drawView();
        setupEvent();
    }

    private void createPanel(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        infotext = new JLabel(InfoText);
        nametextfield = new JTextField(FillTheGap);
        okbutton = new JButton(OkText);

        panel.add(infotext);
        panel.add(nametextfield);
        panel.add(okbutton);



        initUserResult();

    }

    private void initUserResult(){
        userResult = UserResult.getInstance();
    }


    public void setupEvent(){

        okbutton.addActionListener(e -> {
            userResult.writeUserName(nametextfield.getText());
            dispose();
        });
    }

    /**
     * Metoda wyswietlajaca okno menu pauzy
     */
    private void drawView(){
        this.add(panel);
        this.setSize(300, 100);
        setResizable(false);
        setTitle("Start the Game");
        this.setLocationRelativeTo(null);

    }
}
