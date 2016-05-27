package ui.gamefield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * Klasa implementujaca obiekt rakiety
 */
public class Rocket {

    /**
     * Pole przechowujace wspolrzedna x pozycji statku
     */
    private int x;
    /**
     * Pole przechowujace wspolrzedna y pozycji statku
     */
    private int y;
    private int dx;
    public int dy;
    private int accSpeed;
    public int maxLandingSpeed = 2;
    /**
     * Pole przechowujace obiekt subklasy BufferedImage - opisuje obrazek i jego dane
     */
    public BufferedImage img;
    /**
     * Pole przechowujace wysokosc obrazka statku
     */
    public int h;
    /**
     * Pole przechowujace szerokosc obrazka statku
     */
    public int w;
    /**
     * Pole przechowujace aktualna dlugosc statku
     */
    private double actualSizeWidth;
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
     * Konstruktor klasy Rocket
     */
    public Rocket() {
        init();
        load();
        checkDim();
    }

    /**
     *Metoda inicjujaca rakiete
     */
    private void init() {
        resetRocket();
        accSpeed = 1;
    }

    /**
     * Metoda ustalajaca poczatkowa pozycje statku
     */
    public void resetRocket() {
        Random generator = new Random();
        x = generator.nextInt(400)+20;
        y = generator.nextInt(150)+20;
        dx = 0;
        dy = 0;
    }

    /**
     * Metoda wczytujaca obrazek statku z pliku
     */
    private void load() {
        File imageFile = new File("../PROJEKT/images/space_ship.png");
        try {
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda opisujaca ruch statku
     */
    public void move() {
        if (GameField.keyboardKeyState(KeyEvent.VK_UP)) {
            dy += -1;
        }
        else {
            dy = accSpeed;
        }
        if (GameField.keyboardKeyState(KeyEvent.VK_RIGHT)) {
            dx += 1;
        }
        if (GameField.keyboardKeyState(KeyEvent.VK_LEFT)) {
            dx += -1;
        }

        x += dx;
        y += accSpeed + dy;
    }

    /**
     * Zmienna logiczna ladowania
     */
    public boolean landed;
    /**
     * Zmienna logiczna wypadku statku
     */
    public boolean crashed;

    /**
     * Meotda rysujaca reakcje na ladowanie/katastrofe statku
     * @param g
     * @param width
     * @param height
     */
    public void Draw(Graphics g, int width, int height)
    {
        if (landed) {
            g.setColor(Color.blue);
            g.drawString("Brawo, wyladowales!!!", width/2 - 20, height/2);
            g.drawString("Nasisnij ENTER aby zagrac jeszcze raz", width/2 - 60, height/2 + 20);
            g.drawString("Nasisnij SPACJE aby powrocic do menu poczatkowego", width/2 - 120, height/2 + 40);
        }
        else {
            if (crashed) {
                g.setColor(Color.blue);
                g.drawString("Niestety rozbiles sie.", width / 2 - 20, height / 2);
                g.drawString("Nasisnij ENTER aby zagrac jeszcze raz", width / 2 - 60, height / 2 + 20);
                g.drawString("Nasisnij SPACJE aby powrocic do menu poczatkowego", width / 2 - 120, height / 2 + 40);
            }
        }
    }

    /**
     * Metoda zwaracajaca wysokosc statku
     * @return
     */
    public int getDimH(){
        return h;
    }

    /**
     * Metoda zwracajaca szerkosc statku
     * @return
     */
    public int getDimW(){
        return w;
    }

    /**
     * Metoda pobierajaca aktualny rozmiar obrazka ilustrujacego statek
     */
    private void checkDim(){
        h = img.getHeight();
        w = img.getWidth();
    }

    public Image getImg() {
        return img;
    }

    /**
     * Metoda zwracajaca wspolrzedna x pozycji statku
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Metoda zwracajaca wspolrzedna y pozycji statku
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Metoda pobierajaca i ustawiajaca akturalny rozmiar statku skalowanego na podstawie obecnego rozmiaru okna
     */
    public void setDimension(int gameWidth, int gameHeight){
        double xRatio = gameWidth/(double)500;
        double yRatio = gameHeight/(double)500;

        actualSizeWidth = xRatio*getDimW();
        actualSizeHeight = yRatio*getDimH();
    }

    /**
     * Metoda ustawiająca aktualna pozycje statku dopasowujac ja do zmiany rozmiaru okna
     */
    public void setLocation(int gameWidth, int gameHeight){
        double xRatio = gameWidth/(double)500;
        double yRatio = gameHeight/(double)500;

        actualLocationX = xRatio*(double)getX();
        actualLocationY = yRatio*(double)getY();
    }

    /**
     * Metoda rysujaca statek powietrzny
     * @param g
     */
    public void drawRocket(Graphics g){
        /*
        Pytanie czy bedzie dzialac z nullem jako osatnim parametrem ponizej
         */
        g.drawImage(getImg(),(int)actualLocationX, (int)actualLocationY ,(int)actualSizeWidth,(int)actualSizeHeight, null);
    }

}

