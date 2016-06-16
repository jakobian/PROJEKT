package ui.menu.startmenu;

import ui.results.UserResult;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import ui.gamefield.GameField;

/**
 * Klasa opisujaca menu start gry
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

    /**
     * Pole inicjujace obiekt userResult
     */
    private UserResult userResult;

    /**
     * Konstruktor klasy Start Menu
     */
    public StartMenu(){
        createPanel();
        drawView();
        setupEvent();
    }

    /**
     * Metoda tworzaca i dodajaca czesci do okna
     */
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

    /**
     *Metoda inicjujaca odwolanie do obiektu klasy User Result
     */
    private void initUserResult(){
        userResult = UserResult.getInstance();
    }

    /**
     * Metoda opisujaca zachowanie programu po nacisnieciu przycsku "ok" wyswietlonego okna - zamkniecie go, pobranie
     * i zapis nazwy uzytkownika z pola tekstowego
     */
    public void setupEvent(){
        okbutton.addActionListener(e -> {
            userResult.writeUserName(nametextfield.getText());
            dispose();
        });
    }

    /**
     * Metoda wyswietlajaca okno menu start
     */
    private void drawView(){
        this.add(panel);
        this.setSize(300, 100);
        setResizable(false);
        setTitle("Start the Game");
        this.setLocationRelativeTo(null);
    }
}
