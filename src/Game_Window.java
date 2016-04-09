import javax.swing.*;
import java.awt.*;

public class Game_Window extends JFrame {

    //
    Small_GMenu smallgmenu;
    Game_Field gamefield;

    public Game_Window(){
        init_panels();
        ops_Game_Window();
        ops_Small_GMenu();
        ops_Game_Field();
    }

    private void init_panels(){
        smallgmenu = new Small_GMenu();
        gamefield = new Game_Field();
    }

    private void ops_Small_GMenu(){
        smallgmenu.setBackground(Color.WHITE);
        smallgmenu.setVisible(true);
    }

    private void ops_Game_Field(){
        gamefield.setVisible(true);
    }

    private void ops_Game_Window(){
        //Tytul glownego okna gry
        setTitle("Lunar Laner");
        //Rozmiar startowy glownego okna gry
        setSize(500,500);
        //Minimalny rozmiar glownego okna gry
        setMinimumSize(new Dimension(500,500));
        //Rozmieszczenie paneli w glownym oknie gry
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        add(smallgmenu);
        add(gamefield);
        //ustawia okno na srodku ekranu
        setLocationRelativeTo(null);
        //Domyslna operacja po zamknieciu okna gry - zamkniecie aplikacji
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setIconImage(new ImageIcon("C:/Users/Jakub/OneDrive/ELKA/PROZE/PROJEKT/images/moon_icon.jpg").getImage());
        //Wyswietla okno na monitorze
        setVisible(true);
    }
}
