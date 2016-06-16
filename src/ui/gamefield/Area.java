package ui.gamefield;

import com.sun.javafx.geom.*;

import java.awt.*;
import java.awt.Shape;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Klasa definiujaca podloze planszy
 */
public class Area extends Polygon{
    /**
     * Pole przechowujace tablice wspolrzednych X potrzebnych do tworzenia planszy, przed skalowaniem
     */
    private int[] point_x;
    /**
     * Pole przechowujace tablice wspolrzednych Y potrzebnych do tworzenia planszy, przed skalowaniem
     */
    private int[] point_y;
    /**
     * Pole przechowujace aktualna tablice wspolrzednych X potrzebnych do tworzenia planszy, po skalowaniu
     */
    private int[] current_point_x;
    /**
     * Pole przechowujace aktualna tablice wspolrzednych Y potrzebnych do tworzenia planszy, po skalowaniu
     */
    private int[] current_point_y;
    /**
     * Pole przechowujace maksymalna liczbe punktow mozliwa do uzyskania na danej planszy
     */
    private long maxPoints;
    /**
     * Pole przechowujace wspolczynnik trudnosci przypisany do danej planszy
     */
    private long coefficient;

    /**
     * Konstruktor klasy Area
     * @param mapId
     * @throws IOException
     */
    public Area(int mapId) throws IOException {

        switch(mapId) {
            case 1: {
                File file = new File("resources/area1.properties");
                FileInputStream fileInput = new FileInputStream(file);
                Properties properties = new Properties();
                properties.load(fileInput);
                fileInput.close();

                createAreaPoints(properties);
                getMaxPoints(properties);
                break;
            }
            case 2: {
                File file = new File("resources/area2.properties");
                FileInputStream fileInput = new FileInputStream(file);
                Properties properties = new Properties();
                properties.load(fileInput);
                fileInput.close();

                createAreaPoints(properties);
                getMaxPoints(properties);
                break;
            }
        }
    }

    /**
     * Metoda ustawiajaca aktualne rozmieszczenie punktow po zmianie wielkosci okna
     */
    public void setPoints(int gameWidth, int gameHeight){
        double xRatio = gameWidth/(double)500;
        double yRatio = gameHeight/(double)500;
        int total_number_points = point_x.length;
        current_point_x = new int[total_number_points];
        current_point_y = new int[total_number_points];

        for (int i = 0; i < total_number_points; ++i) {
            current_point_x[i] = (int)(xRatio*point_x[i]);
            current_point_y[i] = (int)(yRatio*point_y[i]);
            addPoint(current_point_x[i],current_point_y[i]);
        }
    }

    /**
     * Metoda pobierajaca z pliku konfiguracyjnego wspolczynnik i maksymalna liczbe punktow przypisana do danej planszy
     * @param properties
     */
    private void getMaxPoints(Properties properties){
        maxPoints = Long.parseLong(properties.getProperty("maxPoints"));
        coefficient = Long.parseLong(properties.getProperty("coefficient"));
    }

    /**
     * Metoda zwracajaca maksymalna liczbe punktow mozliwa do uzyskania na danej planszy
     * @return
     */
    public long getLevelPoint(){
        return maxPoints;
    }

    /**Metoda zwracajaca wspolczynnik trudnosci danej planszy
     * @return
     */
    public long getLevelCoefficient(){
        return coefficient;
    }

    /**
     * Metoda wczytujaca wsporzedne punktow z pliku konfiguracyjnego i zapisujaca je w tablicy
     * @param properties
     */
    private void createAreaPoints(Properties properties) {
        int total_number_points = Integer.parseInt(properties.getProperty("total_number_points"));
        point_x = new int[total_number_points];
        point_y = new int[total_number_points];
        for (int i = 0; i < total_number_points; ++i) {
            point_x[i] = Integer.parseInt(properties.getProperty("point_x_" + Integer.toString(i)));
            point_y[i] = Integer.parseInt(properties.getProperty("point_y_" + Integer.toString(i)));
        }
    }

    /**
     * Metoda rysujaca podloze
     * @param g
     */
    public void drawArea(Graphics g){
        g.fillPolygon(current_point_x, current_point_y, point_x.length);
    }
}
