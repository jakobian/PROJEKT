/*Ta klasa nie dziala(meotdy dobre, zle odwolanie sie do niej), trzeba pomyslec jak ja zrobić zeby dziala*/






import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by Jakub on 11.04.2016.
 */


/**
 * Klasa definujaca wszechswiat - podloze, grawitacje...
 */
public class DrawUniverse extends GameField {

    /**
     *
     */
    private double gravity;

    /**
     * Pole obiektu klasy punkt przechowujacego punkty potrzebne do narysowania podloza
     */
    private Point[] area;


    /**
     * Konstruktor klasy DrawUniverse
     */
    DrawUniverse() throws IOException{
        File file = new File("resources/area.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        createAreaPoints(properties);
    }

    /**
     * Metoda pobierajaca punkty z pliku konfiguracyjnego i umieszczajaca je w obiekcie tablicowym area
     * @param properties
     */
    private void createAreaPoints(Properties properties){
        area = new Point[Integer.parseInt(properties.getProperty("point_number"))];
        for(int i = 0; i<area.length;++i){
            area[i] = new Point(Integer.parseInt(properties.getProperty("pointX_" + Integer.toString(i))),
                    Integer.parseInt(properties.getProperty("pointY_" + Integer.toString(i))));
        }
    }

    /**
     * Metoda zwracajaca obiekt area
     * @return
     */
    public Point[] getArea() {return area;}


}