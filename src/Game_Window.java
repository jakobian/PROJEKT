import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Klasa tworzaca okno gry
 */

public class Game_Window extends JFrame {

    /**Inicjacja obiektow*/
    Small_GMenu smallgmenu;
    Game_Field gamefield;

    private int width;
    private int height;

    /**Konstruktor glownego okna gry*/
    public Game_Window() throws IOException{
        File file = new File("resources/window.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        init_panels();
        ops_Game_Window();
        ops_Small_GMenu(properties);
        ops_Game_Field(properties);
    }

    /**Metoda tworzaca panele w glownym oknie gry*/
    private void init_panels() throws IOException{
        smallgmenu = new Small_GMenu();
        gamefield = new Game_Field();
    }

    /**Metoda opisujaca opcje menu gry*/
    private void ops_Small_GMenu(Properties properties){
        width = Integer.parseInt(properties.getProperty("width"));
        height = Integer.parseInt(properties.getProperty("height"));
        smallgmenu.setBackground(Color.WHITE);
        smallgmenu.setVisible(true);
        //smallgmenu.setPreferredSize(new Dimension(120,70));
        smallgmenu.setMaximumSize(new Dimension(5000,20));
        smallgmenu.setMinimumSize(new Dimension(width,width - height));
        smallgmenu.setDimensionButton(70, 20);
    }

    /**Metoda opisujaca opcje pola gry*/
    private void ops_Game_Field(Properties properties){
        width = Integer.parseInt(properties.getProperty("width"));
        height = Integer.parseInt(properties.getProperty("height"));
        gamefield.setVisible(true);
        gamefield.setBackground(Color.black);
        gamefield.setPreferredSize(new Dimension(width,height));
        //gamefield.setMaximumSize(new Dimension(width,height));
        gamefield.setMinimumSize(new Dimension(width,height));
        gamefield.setDimension(width, height);

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

        /**ustawia ikonke ramki*/
        setIconImage(new ImageIcon("..\\PROJEKT\\images\\moon_icon.jpg").getImage());

        setResizable(false);
        /**Domyslna operacja po zamknieciu okna gry - zamkniecie aplikacji*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
