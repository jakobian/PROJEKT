package ui.gamefield;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import ui.gamefield.Rocket;

/*
 * Created by Jakub on 12.04.2016.
 */

/**
 * Klasa tworzaca okno rozgrywki
 */
public class GameField extends JPanel {

    /**
     * Pole przechowujace tablice stanow klawiszy klawiatury
     */
    private static boolean[] keyboardState = new boolean[525];
    /**
     * Pole przechowujace czas odswiezania w milisekundach
     */
    private final long updatePeriod = 25;
    //private long gameTime;
    //private long lastTime;
    /**
     * Inicjacja obiektu rakiety
     */
    private Rocket rocket;
    /**
     * Tablica mozliwych stanow gry
     */
    public enum  statesOfGame{START_MENU, PLAY, END_GAME}
    /**
     * Pole przechowujace aktualny stan gry
     */
    public static statesOfGame state;
    /**
     * Pole przechowujace obraz dla stanu gry state = START_MENU
     */
    private BufferedImage menuImg;
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
     * Pole przechowujace aktualna dlugosc statku
     */
    private double actualSizeWeidht;
    /**
     * Pole przechowujace aktualna szerokosc statku
     */
    private double actualSizeHeight;
    /**
     * Pole przechowujace aktualna wspolrzedna X pozycji statku
     */
    private double actualLocationX;
    /**
     * Pole przechowujace aktualna wspolrzedna Y pozycji statku
     */
    private double actualLocationY;

    /**
     * Metoda
     * @param key
     * @return
     */
    public static boolean keyboardKeyState(int key) {
        return keyboardState[key];
    }

    /**
     * Metoda przypsiujaca wartosc logiczna true gdy klawisz zostaje wcisniety
     * @param e
     */
   public void keyPressed(KeyEvent e) {
        keyboardState[e.getKeyCode()] = true;
    }

    /**
     * Meotda przypisujaca wartosc false w momencie puszczenia klawisza
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        keyboardState[e.getKeyCode()] = false;
    }

    /**
     * Metoda obslugujaca klawisze
     * @param e
     */
    public void keyReleasedGame(KeyEvent e) {
        switch (state) {
            case START_MENU:
                restartGame();
                break;

            case END_GAME:
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    state = statesOfGame.START_MENU;
                }
                else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    restartGame();
                }
        }
    }


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

        state = statesOfGame.START_MENU;

        //this.setFocusable(true);
        //this.requestFocusInWindow();

        //addKeyListener(this);


        Thread gameThread = new Thread() {
            @Override
            public void run() {
                initField();
                createAreaPoints(properties);
                createLandingPoints(properties);
                gameLoop();

                state = statesOfGame.PLAY;
            }
        };
        gameThread.start();
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

    /**
     * Meotda inicjujaca rakiete
     */
    private void initField() {
        //addKeyListener(new TAdapter());

        rocket = new Rocket();

        //timer = new Timer(DELAY, this);
        //timer.start();
    }

    /**
     * Metoda petli gry
     */
    private void gameLoop() {
        long beginTime, timeTaken, timeLeft;

        while (true) {
            beginTime = System.currentTimeMillis();

            switch (state) {
                case START_MENU:
                    loadMenu();
                    break;

                case PLAY:
                    updateGame();
                    break;

                case END_GAME:
                    break;
            }

            repaint();

            timeTaken = System.currentTimeMillis() - beginTime;
            timeLeft = updatePeriod - timeTaken;
            if (timeLeft < 5) timeLeft = 5;
            try {
                Thread.sleep(timeLeft);
            } catch (InterruptedException ex) { }
        }
    }

    /**
     * Metoda ladujaca menu gry
     */
    private void loadMenu() {
        File imageFile = new File("../PROJEKT/images/makieta.jpg");
        try {
            menuImg = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda odswiezania gry
     */
    public void updateGame() {
        rocket.move();
        checkLanding();
    }

    /**
     * Metoda restarutujaca gre podczas przejscia z jednego stanu do innego
     */
    public void restartGame() {
        rocket.resetRocket();
        state = statesOfGame.PLAY;
    }

    //private Dimension  initSize = new Dimension(500,500);

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Graphics2D g2d = (Graphics2D) g;
        gameWidth = this.getWidth();
        gameHeight = this.getHeight();


        switch (state) {
            case START_MENU:

                g.drawImage(menuImg, 0, 0, gameWidth, gameHeight , null);
                g.setColor(Color.white);
                g.drawString("Nasisnij dowolny klawisz aby rozpoczac gre", gameWidth/2 - 120, gameHeight/2);
                break;

            case PLAY:
                setDimension();
                setLocation();
                drawRocket(g, (int)actualLocationX, (int)actualLocationY ,(int)actualSizeWeidht,(int)actualSizeHeight);
                //setBorder(BorderFactory.createLineBorder(Color.white));

                setPoints();
                drawArea(g, current_point_x, current_point_y);
                g.setColor(Color.red);
                drawLandingArea(g, current_landing_point_x, current_landing_point_y);


                break;

            case END_GAME:
                setDimension();
                setLocation();
                drawRocket(g, (int)actualLocationX, (int)actualLocationY ,(int)actualSizeWeidht,(int)actualSizeHeight);
                setPoints();
                drawArea(g, current_point_x, current_point_y);
                g.setColor(Color.red);
                drawLandingArea(g, current_landing_point_x, current_landing_point_y);

                rocket.Draw(g,gameWidth,gameHeight);

        }

    }

    /**
     * Metoda pobierajaca i ustawiajaca akturalny rozmiar statku skalowanego na podstawie obecnego rozmiaru okna
     */
    private void setDimension(){
        double xRatio = gameWidth/(double)500;
        double yRatio = gameHeight/(double)500;

        actualSizeWeidht = xRatio*rocket.getDimW();
        actualSizeHeight = yRatio*rocket.getDimH();
    }

    /**
     * Metoda ustawiająca aktualna pozycje statku dopasowujac ja do zmiany rozmiaru okna
     */
    private void setLocation(){
        double xRatio = gameWidth/(double)500;
        double yRatio = gameHeight/(double)500;

        actualLocationX = xRatio*(double)rocket.getX();
        actualLocationY = yRatio*(double)rocket.getY();
    }

    /**
     * Metoda rysujaca statek powietrzny
     * @param g
     * @param Width
     * @param Height
     */
    private void drawRocket(Graphics g, int CooX, int CooY, int Width, int Height){
        g.drawImage(rocket.getImg(), CooX, CooY, Width, Height, this);
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
       /* gameWidth = this.getWidth();
        gameHeight = this.getHeight();*/
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

    /**
     * Metoda sprawdzajaca pozycje statku wzgledem ladowiska
     */
    private void checkLanding() {
        setDimension();
        setPoints();
        setLocation();


        if (((int)actualLocationY+(int)actualSizeHeight >= current_landing_point_y[1]) && (int)actualLocationY+(int)actualSizeHeight <= current_landing_point_y[1]+2){
           if((int)actualLocationX > current_landing_point_x[1] && (int)actualLocationX+(int)actualSizeWeidht<current_landing_point_x[2])
            {
                if(rocket.dy < rocket.maxLandingSpeed){
                    rocket.landed = true;
                    state = statesOfGame.END_GAME;
                }
                else
                    rocket.crashed = true;
                    state = statesOfGame.END_GAME;
            }
        }
    }
/*
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
    }*/
}


/*aktualna pozycja statku - watek, obliczanie w petli albo timerem ze swingu i wymuszanie cykliczne repainta*/

