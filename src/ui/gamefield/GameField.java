package ui.gamefield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * Created by Jakub on 12.04.2016.
 */

/**
 * Klasa tworzaca okno rozgrywki
 */
public class GameField extends JPanel implements ActionListener {

    private Rocket rocket;
    private Timer timer;
    private final int DELAY = 10;

    /**
     * Pole przechowujace tablice wspolrzednych X potrzebnych do tworzenia planszy, przed skalowaniem
     */
    private int[] point_x;
    /**
     * Pole przechowujace tablice wspolrzednych Y potrzebnych do tworzenia planszy, przed skalowaniem
     */
    private int[] point_y;

    /**
     * Pole przechowujace tablice wspolrzednych X potrzebnych do tworzenia ladowiska przed skalowaniem
     */
    private int[] landingPoint_x;
    /**
     * Pole przechowujace tablice wspolrzednych Y potrzebnych do tworzenia ladowiska przed skalowaniem
     */
    private int[] landingPoint_y;

    /**
     * Pole przechowujace aktualna tablice wspolrzednych X potrzebnych do tworzenia planszy, po skalowaniu
     */
    private int[] current_point_x;
    /**
     * Pole przechowujace aktualna tablice wspolrzednych Y potrzebnych do tworzenia planszy, po skalowaniu
     */
    private int[] current_point_y;

    /**
     * Pole przechowujace aktualna tablice wspolrzednych X potrzebnych do tworzenia ladowsika po skalowaniu
     */
    private int[] current_landing_point_x;
    /**
     * Pole przechowujace aktualna tablice wspolrzednych X potrzebnych do tworzenia ladowsika po skalowaniu
     */
    private int[] current_landing_point_y;

    /**
     * Pole przechowujace aktualna szerokosc okna gry
     */
    public static int gameWidth;
    /**
     * Pole przechowujace aktualna wysokosc okna gry
     */
    public static int gameHeight;

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
        createLandingPoints(properties);
        initField();
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

    private void initField() {
        addKeyListener(new TAdapter());

        rocket = new Rocket();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    //private Dimension  initSize = new Dimension(500,500);

    /**
     * Metoda rysujaca podloze
     * @param g
     * @param point_xc
     * @param point_yc
     */
    private void drawArea(Graphics g, int[] point_xc, int[] point_yc){

        g.fillPolygon(point_xc, point_yc, point_x.length);
    }

    /**
     * Metoda rysujaca ladowisko
     * @param g
     * @param point_xc
     * @param point_yc
     */
    private void drawLandingArea(Graphics g, int[] point_xc, int[] point_yc){

        g.fillPolygon(point_xc, point_yc, landingPoint_x.length);
    }

    /**
     * Metoda ustawiajaca aktualne rozmieszczenie punktow po zmianie wielkosci okna
     */
    private void setPoints(){
        gameWidth = this.getWidth();
        gameHeight = this.getHeight();
        double xRatio = gameWidth/(double)500;
        double yRatio = gameHeight/(double)500;
        int total_number_points = point_x.length;
        current_point_x = new int[total_number_points];
        current_point_y = new int[total_number_points];

        for (int i = 0; i < total_number_points; ++i) {
            current_point_x[i] = (int)(xRatio*point_x[i]);
            current_point_y[i] = (int)(yRatio*point_y[i]);
        }

        current_landing_point_x = new int[4];
        current_landing_point_y = new int[4];
        for (int i = 0; i < 4; ++i) {
            current_landing_point_x[i] = (int)(xRatio*landingPoint_x[i]);
            current_landing_point_y[i] = (int)(yRatio*landingPoint_y[i]);
        }

    }



    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(rocket.getImg(), rocket.getX(), rocket.getY(), this);
        // setBorder(BorderFactory.createLineBorder(Color.white));

        setPoints();
        drawArea(g, current_point_x, current_point_y);
        g.setColor(Color.red);
        drawLandingArea(g, current_landing_point_x, current_landing_point_y);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        rocket.move();
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            rocket.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            rocket.keyPressed(e);
        }
    }
}


/*aktualna pozycja statku - watek, obliczanie w petli albo timerem ze swingu i wymuszanie cykliczne repainta*/

