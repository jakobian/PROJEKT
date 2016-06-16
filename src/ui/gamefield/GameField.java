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
import ui.results.BestResults;
import ui.results.UserResult;


/**
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
    /**
     * Inicjacja obiektu rakiety
     */
    private Rocket rocket;
    /**
     * Inicjacja obiektu podloza
     */
    private Area area;
    /**
     * Inicjacja obiektu ladowiska
     */
    private LandingArea landingArea;
    /**
     * Inicjacja obiektu menadzera uzytkownika
     */
    private UserResult userResult;
    /**
     * Tablica mozliwych stanow gry
     */
    public enum  statesOfGame{START_MENU, PLAY, END_GAME, NEXT_LEVEL, FINISH_GAME, PAUSE_GAME}
    /**
     * Pole przechowujace aktualny stan gry
     */
    public static statesOfGame state;
    /**
     * Pole przechowujace obraz dla stanu gry state = START_MENU
     */
    private BufferedImage menuImg;
    /**
     * Pole przechowujace aktualna szerokosc okna gry
     */
    public static int gameWidth;
    /**
     * Pole przechowujace aktualna wysokosc okna gry
     */
    public static int gameHeight;
    /**
     * Pole przechowujace czas rozpoczecia rozgrywki
     */
    private long startTime;
    /**
     * Pole przechowujace calkowity czas rozgrywki
     */
    public long estimatedTime;
    /**
     * Pole przechowujace numer planszy
     */
    private int mapNr = 1;

    BestResults bestResults;

    
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
                resetResult();
                break;

            case NEXT_LEVEL:
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    initBestResult();
                    resetResult();
                    state = statesOfGame.START_MENU;
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(mapNr<2){
                        ++mapNr;
                        restartGame();
                    }
                    else
                    state = statesOfGame.FINISH_GAME;
                }
                break;

            case END_GAME:
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    state = statesOfGame.START_MENU;
                    initBestResult();
                    resetResult();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    resetResult();
                    restartGame();
                }
                break;

            case FINISH_GAME:
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    state = statesOfGame.START_MENU;
                    initBestResult();
                    resetResult();
                }
                break;

            case PAUSE_GAME:
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    state = statesOfGame.PLAY;
                }
                break;

            case PLAY:
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    state = statesOfGame.PAUSE_GAME;
                }
                break;
        }
    }

    /**
     * Metoda ustawiajaca parametry rezultatu do wartosci poczatkowych
     */
    private void resetResult(){
        mapNr = 1;
        estimatedTime = 0L;
        userResult.user_result=0;
    }

    /**
     * Konstruktor klasy GameField
     * @throws IOException
     */
    public GameField () {
        state = statesOfGame.START_MENU;
        initPointsCounter();
        try {
            initArea(mapNr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Thread gameThread = new Thread() {
            @Override
            public void run() {
                try {
                    initField();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                gameLoop();
                state = statesOfGame.PLAY;
            }
        };
        gameThread.start();
    }

    /**
     * Meotda inicjujaca rakiete
     */
    private void initField() throws IOException{
        rocket = new Rocket();
    }

    /**
     * Metoda inicjujaca teren ksiezyca
     * @param mapId
     * @throws IOException
     */
    private void initArea(int mapId) throws IOException{
        area = new Area(mapId);
        landingArea = new LandingArea(mapId);
    }


    public void initPointsCounter() {

        userResult = UserResult.getInstance();
    }

    private void initBestResult(){
        try {
            bestResults = new BestResults();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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

                case NEXT_LEVEL:
                    break;

                case FINISH_GAME:
                    break;

                case PAUSE_GAME:
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

    private void pointsManager(boolean rocketState){
            estimatedTime = (System.nanoTime() - startTime);
            userResult.setTotalPoints(estimatedTime, rocketState, area.getLevelPoint(), area.getLevelCoefficient());
    }

    /**
     * Metoda odswiezania gry
     */
    public void updateGame() {
        rocket.move();

        try {
            initArea(mapNr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        checkLanding();
    }

    /**
     * Metoda restarutujaca gre podczas przejscia z jednego stanu do innego
     */
    public void restartGame() {
        startTime = System.nanoTime();
        rocket.resetRocket();
        state = statesOfGame.PLAY;
    }

    /**
     * Metoda wykrywajaca kolizje miedzy statkiem a podlozem, ladowiskiem
     */
    private void checkLanding() {
        rocket.setDimension(gameWidth,gameHeight);
        rocket.setLocation(gameWidth,gameHeight);
        landingArea.setPoints(gameWidth,gameHeight);
        area.setPoints(gameWidth,gameHeight);

        if((landingArea.intersects(rocket.getActualLocationX(), rocket.getActualLocationY(),
                rocket.getActualSizeWidth(), rocket.getActualSizeHeight()))
                && (rocket.getActualLocationX()>= landingArea.current_landing_point_x[1] &&
                    rocket.actualLocationX+ rocket.getActualSizeWidth()<=landingArea.current_landing_point_x[2]))
        {

            if(rocket.getDy() <= rocket.maxLandingVerticalSpeed
                    && rocket.getDx() <= rocket.maxLandingHorizontalSpeed
                    && rocket.getDx() >= (-1)*rocket.maxLandingHorizontalSpeed) {

                rocket.landed = true;
                rocket.crashed = false;
                state = statesOfGame.NEXT_LEVEL;
                pointsManager(true);
            }
            else if(rocket.getDy() > rocket.maxLandingVerticalSpeed
                    || rocket.getDx() >= rocket.maxLandingHorizontalSpeed
                    || rocket.getDx() <= (-1)*rocket.maxLandingHorizontalSpeed){
                rocket.crashed = true;
                rocket.landed = false;
                state = statesOfGame.END_GAME;
                pointsManager(false);
            }
        }

        else if(area.intersects(rocket.getActualLocationX(), rocket.getActualLocationY(),
                rocket.getActualSizeWidth(), rocket.getActualSizeHeight()))
        {
            rocket.crashed = true;
            rocket.landed = false;
            state = statesOfGame.END_GAME;
            pointsManager(false);
        }
    }



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
                g.drawString("Nacisnij dowolny klawisz, aby rozpoczac gre", gameWidth/2 - 120, gameHeight/2);
                g.drawString("Wybierz START, aby wprowadzic swoje imie", gameWidth/2 - 120, gameHeight/2 + 20);
                break;

            case PLAY:

                rocket.setDimension(gameWidth,gameHeight);
                rocket.setLocation(gameWidth,gameHeight);
                rocket.drawRocket(g);
                area.setPoints(gameWidth,gameHeight);
                area.drawArea(g);
                landingArea.setPoints(gameWidth,gameHeight);
                landingArea.drawLandingArea(g);

                break;

            case END_GAME:

                rocket.setDimension(gameWidth,gameHeight);
                rocket.setLocation(gameWidth,gameHeight);

                rocket.drawExplosion(g);
                g.drawString("Liczba zdobtych punktow: " + userResult.getUserResult(), gameWidth/2 - 50, gameHeight/8 + 80);

                area.setPoints(gameWidth,gameHeight);
                area.drawArea(g);
                landingArea.setPoints(gameWidth,gameHeight);
                landingArea.drawLandingArea(g);
                rocket.Draw(g,gameWidth,gameHeight);

                break;

            case NEXT_LEVEL:

                rocket.drawRocket(g);
                g.drawString("Liczba zdobtych punktow: " + userResult.getUserResult(), gameWidth/2 - 50, gameHeight/8 + 80);

                rocket.setDimension(gameWidth,gameHeight);
                rocket.setLocation(gameWidth,gameHeight);
                area.setPoints(gameWidth,gameHeight);
                area.drawArea(g);
                landingArea.setPoints(gameWidth,gameHeight);
                landingArea.drawLandingArea(g);
                rocket.Draw(g,gameWidth,gameHeight);

                break;

            case FINISH_GAME:

                rocket.setDimension(gameWidth,gameHeight);
                rocket.setLocation(gameWidth,gameHeight);
                area.setPoints(gameWidth,gameHeight);
                area.drawArea(g);
                landingArea.setPoints(gameWidth,gameHeight);
                landingArea.drawLandingArea(g);

                g.drawString("KONIEC" , gameWidth/2 - 15, gameHeight/9);

                break;

            case PAUSE_GAME:

                rocket.drawRocket(g);
                rocket.setDimension(gameWidth,gameHeight);
                rocket.setLocation(gameWidth,gameHeight);
                area.setPoints(gameWidth,gameHeight);
                area.drawArea(g);
                landingArea.setPoints(gameWidth,gameHeight);
                landingArea.drawLandingArea(g);

                break;

        }
    }
}