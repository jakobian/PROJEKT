package ui.gamefield;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jakub on 25.05.2016.
 */

/**
 * Klasa opisujaca ladowisko
 */
public class LandingArea extends Polygon {

    /**
     * Konstruktor klasy LandingArea obslugujacy wybor i wczytanie mapy poziomu z pliku konfiguracyjnego
     * @param mapId
     * @throws IOException
     */
    public LandingArea(int mapId) throws IOException {
        switch(mapId) {
            case 1: {
                File file = new File("resources/area1.properties");
                FileInputStream fileInput = new FileInputStream(file);
                Properties properties = new Properties();
                properties.load(fileInput);
                fileInput.close();

                createLandingPoints(properties);
                break;
            }
            case 2: {
                File file = new File("resources/area2.properties");
                FileInputStream fileInput = new FileInputStream(file);
                Properties properties = new Properties();
                properties.load(fileInput);
                fileInput.close();

                createLandingPoints(properties);
                break;
            }
        }
    }

    /**
     * Pole przechowujace tablice wspolrzednych X potrzebnych do tworzenia ladowiska przed skalowaniem
     */
    private int[] landingPoint_x;
    /**
     * Pole przechowujace tablice wspolrzednych Y potrzebnych do tworzenia ladowiska przed skalowaniem
     */
    private int[] landingPoint_y;
    /**
     * Pole przechowujace aktualna tablice wspolrzednych X potrzebnych do tworzenia ladowsika po skalowaniu
     */
    public int[] current_landing_point_x;
    /**
     * Pole przechowujace aktualna tablice wspolrzednych X potrzebnych do tworzenia ladowsika po skalowaniu
     */
    public int[] current_landing_point_y;


    /**
     * Metoda ustawiajaca aktualne rozmieszczenie punktow po zmianie wielkosci okna
     */
    public void setPoints(int gameWidth, int gameHeight){
        double xRatio = gameWidth/(double)500;
        double yRatio = gameHeight/(double)500;
        int total_number_points = landingPoint_x.length;

        current_landing_point_x = new int[total_number_points];
        current_landing_point_y = new int[total_number_points];
        for (int i = 0; i < total_number_points; ++i) {
            current_landing_point_x[i] = (int)(xRatio*landingPoint_x[i]);
            current_landing_point_y[i] = (int)(yRatio*landingPoint_y[i]);
            addPoint(current_landing_point_x[i],current_landing_point_y[i]);
        }
    }

    /**
     * Metoda wczytujaca wspolrzedne punktow ladowiska z pliku konfiguracyjnego i zapisujaca je w tablicy
     * @param properties
     */
    private void createLandingPoints(Properties properties){
        landingPoint_x = new int[4];
        landingPoint_y = new int[4];
        for (int i=0; i<4; ++i){
            landingPoint_x[i] = Integer.parseInt(properties.getProperty("landingPoint_x_" + Integer.toString(i)));
            landingPoint_y[i] = Integer.parseInt(properties.getProperty("landingPoint_y_" + Integer.toString(i)));
        }
    }

    /**
     * Metoda rysujaca ladowisko
     * @param g
     */
    public void drawLandingArea(Graphics g){
        g.setColor(Color.red);
        g.fillPolygon(current_landing_point_x, current_landing_point_y, landingPoint_x.length);
    }
}
