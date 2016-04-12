import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jakub on 12.04.2016.
 */


public class GameField extends JPanel{

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
     * Konstruktor klasy GameField
     * @throws IOException
     */
    public GameField() throws IOException{
        File file = new File("resources/area.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        createAreaPoints(properties);
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
     * @param point_xc
     * @param point_yc
     */
    private void drawArea(Graphics g, int[] point_xc, int[] point_yc){

        g.fillPolygon(point_xc, point_yc, point_x.length);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Graphics2D g2d = (Graphics2D) g;
        setBorder(BorderFactory.createLineBorder(Color.white));
        double xRatio = getWidth()/(double)500;
        double yRatio = getHeight()/(double)500;
        int total_number_points = point_x.length;
        current_point_x = new int[total_number_points];
        current_point_y = new int[total_number_points];

        for (int i = 0; i < total_number_points; ++i) {
            current_point_x[i] = (int)(xRatio*point_x[i]);
            current_point_y[i] = (int)(yRatio*point_y[i]);
        }
        drawArea(g, current_point_x, current_point_y);
        g.setColor(Color.gray);
    }
}