import javax.swing.*;
import java.awt.*;


/** Klasa menu okna gry */
public class Small_GMenu extends JPanel {

    /** Pola do przechowywania nazw przyciskow menu okna gry */
    public final static String Menu = "Menu";
    public final static String Pause = "Pause";

    /** Pole inicjujace przyciki menu okna gry */
    JButton menubutton;
    JButton pausebutton;

    /** Konstruktor menu okna gry */
    public Small_GMenu(){
        createbutton();
        //setDimensionButton(70,20);
    }

    /** Metoda tworzaca przyciski menu okna gry */
    private void createbutton(){
       //this.setLayout(null);
       this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        menubutton = new JButton(Menu);
        pausebutton = new JButton(Pause);
        add(menubutton);
        add(pausebutton);
    }

    /** Metoda ustawiajaca wielkosc przyciskow menu okna gry */
    public void setDimensionButton(int width, int height){
        //menubutton.setBounds(0,0,width,height);
        //pausebutton.setBounds(width,0,width,height);
        menubutton.setPreferredSize(new Dimension(width, height));
        pausebutton.setPreferredSize(new Dimension(width, height));
    }
}
