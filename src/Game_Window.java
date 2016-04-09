import javax.swing.*;
import java.awt.*;

/**
 * Klasa tworzaca okno gry
 */

public class Game_Window extends JFrame {

    /**Inicjacja obiektow*/
    Small_GMenu smallgmenu;
    Game_Field gamefield;

    /**Konstruktor glownego okna gry*/
    public Game_Window(){
        init_panels();
        ops_Game_Window();
        ops_Small_GMenu();
        ops_Game_Field();
    }

    /**Metoda tworzaca panele w glownym oknie gry*/
    private void init_panels(){
        smallgmenu = new Small_GMenu();
        gamefield = new Game_Field();
    }

    /**Metoda opisujaca opcje menu gry*/
    private void ops_Small_GMenu(){
        smallgmenu.setBackground(Color.WHITE);
        smallgmenu.setVisible(true);
        smallgmenu.setPreferredSize(new Dimension(500,20));
        smallgmenu.setMaximumSize(new Dimension(500,20));
        smallgmenu.setMinimumSize(new Dimension(500,20));
    }

    /**Metoda opisujaca opcje pola gry*/
    private void ops_Game_Field(){
        gamefield.setVisible(true);
        gamefield.setBackground(Color.GRAY);
        gamefield.setPreferredSize(new Dimension(500,480));
        gamefield.setMaximumSize(new Dimension(500,480));
        gamefield.setMinimumSize(new Dimension(500,480));

    }

    /**Metoda opisujaca opcje glownego okna gry*/
    private void ops_Game_Window(){

        /**Tytul glownego okna gry*/
        setTitle("Lunar Lander");

        /**Rozmiar startowy glownego okna gry*/
        setSize(500,500);

        /**Minimalny rozmiar glownego okna gry*/
        setMinimumSize(new Dimension(500,500));

        /**Rozmieszczenie paneli w glownym oknie gry*/
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        add(smallgmenu);
        add(gamefield);

        /**ustawia okno na srodku ekranu*/
        setLocationRelativeTo(null);

        setResizable(false);
        /**Domyslna operacja po zamknieciu okna gry - zamkniecie aplikacji*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setIconImage(new ImageIcon("C:/Users/Jakub/OneDrive/ELKA/PROZE/PROJEKT/images/moon_icon.jpg").getImage());

    }
}
