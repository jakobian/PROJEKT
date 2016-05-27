package ui.gamefield;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jakub on 25.05.2016.
 */
public class Area {
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

    public Area() throws IOException {
        File file = new File("resources/area.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        createAreaPoints(properties);
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
        }
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
