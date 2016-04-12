import javax.swing.*;
import java.awt.*;


/** Klasa menu okna gry */
public class SmallGMenu extends JPanel {

    /**
     * Pola przechowujace nazwe przycisku "Menu" wyswietlanego w menu okna gry
     */
    public final static String Menu = "Menu";
    /**
     * Pola przechowujace nazwe przycisku "Pause" wyswietlanego w menu okna gry
     */
    public final static String Pause = "Pause";

    /**
     * Pole inicjujace przycik "Menu" okna gry
     */
    JButton menubutton;
    /**
     * Pole inicjujace przycik "Pause" okna gry
     */
    JButton pausebutton;


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

        menubutton = new JButton(Menu);
        pausebutton = new JButton(Pause);
        add(menubutton);
        add(pausebutton);
    }

    /**
     *  Metoda ustawiajaca wielkosc przyciskow menu okna gry
     */
    public void setDimensionButton(int width, int height){
        menubutton.setPreferredSize(new Dimension(width, height));
        pausebutton.setPreferredSize(new Dimension(width, height));
    }
}
