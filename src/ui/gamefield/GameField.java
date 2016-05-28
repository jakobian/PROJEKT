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
    //private long gameTime;
    //private long lastTime;
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
     * Pole przechowujace aktualna szerokosc okna gry
     */
    public static int gameWidth;
    /**
     * Pole przechowujace aktualna wysokosc okna gry
     */
    public static int gameHeight;


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
    public GameField () {
        state = statesOfGame.START_MENU;

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
        area = new Area();
        landingArea = new LandingArea();
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
                && (rocket.getActualLocationX()> landingArea.current_landing_point_x[1] &&
                    rocket.actualLocationX+ rocket.getActualSizeWidth()<landingArea.current_landing_point_x[2]))
        {
            rocket.landed = true;
            rocket.crashed = false;
            state = statesOfGame.END_GAME;
        }

        else if(area.intersects(rocket.getActualLocationX(), rocket.getActualLocationY(),
                rocket.getActualSizeWidth(), rocket.getActualSizeHeight()))
        {
            rocket.crashed = true;
            rocket.landed = false;
            state = statesOfGame.END_GAME;
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
                g.drawString("Nasisnij dowolny klawisz aby rozpoczac gre", gameWidth/2 - 120, gameHeight/2);
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

                if(rocket.landed) {
                    rocket.drawRocket(g);
                }
                else if (rocket.crashed){
                    rocket.drawExplosion(g);
                }

                area.setPoints(gameWidth,gameHeight);
                area.drawArea(g);
                landingArea.setPoints(gameWidth,gameHeight);
                landingArea.drawLandingArea(g);
                rocket.Draw(g,gameWidth,gameHeight);

        }
    }
}