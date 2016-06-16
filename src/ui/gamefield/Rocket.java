package ui.gamefield;

import org.omg.PortableServer.POAHelper;

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
public class Rocket extends Polygon {

    /**
     * Pole przechowujace wspolrzedna x pozycji statku
     */
    private int x;
    /**
     * Pole przechowujace wspolrzedna y pozycji statku
     */
    private int y;
    /**
     * Pole przechowujace zmiany wspolrzednej x statku
     */
    private int dx;
    /**
     * Pole przechowujace zmiany wspolrzednej y statku
     */
    public int dy;
    /**
     * Pole przechowujace wartosc przyspieszenia statku
     */
    private int accSpeed;
    /**
     * Pole przechowujace wartosc maksymalnej dopuszczalnej pionowej predkosci ladowania
     */
    public int maxLandingHorizontalSpeed;
    /**
     * Pole przechowujace wartosc maksymalnej dopuszczalnej poziomej predkosci ladowania
     */
    public int maxLandingVerticalSpeed;
    /**
     * Pole przechowujace obiekt subklasy BufferedImage - opisuje obrazek i jego dane
     */
    public BufferedImage img;
    /**
     * Pole przechowujace obiekt subklasy BufferedImage - opisuje obrazek i jego dane
     */
    public BufferedImage exp;
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
    public double actualLocationX;
    /**
     * Pole przechowujace aktualna wspolrzedna Y pozycji statku
     */
    public double actualLocationY;
    /**
     * Zmienna logiczna ladowania
     */
    public boolean landed;
    /**
     * Zmienna logiczna wypadku statku
     */
    public boolean crashed;

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
        maxLandingVerticalSpeed = 2;
        maxLandingHorizontalSpeed = 3;
    }

    /**
     * Metoda ustalajaca poczatkowa pozycje statku
     */
    public void resetRocket() {
        Random generator = new Random();
        x = generator.nextInt(400)+20;
        y = generator.nextInt(50)+20;
        dx = 0;
        dy = 0;
    }

    /**
     * Metoda wczytujaca obrazek statku z pliku
     */
    private void load() {
        File imageFile = new File("../PROJEKT/images/space_ship.png");
        File imageFile2 = new File("../PROJEKT/images/wybuch.png");
        try {
            img = ImageIO.read(imageFile);
            exp = ImageIO.read(imageFile2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda opisujaca ruch statku
     */
    public void move() {
        if (GameField.keyboardKeyState(KeyEvent.VK_UP)) {
            if (dy <= 3)
                dy += -1;
            else
                dy = 1;
        }
        else {
            if(dy <= 2)
                dy += accSpeed;
            else
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

    public int getDy() {return dy;}

    public int getDx() {return dx;}



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
            g.drawString("Brawo, wyladowales!!!", width/2 - 30, height/8);
            g.drawString("Nasisnij ENTER aby przejsc do nastepnego poziomu", width/2 - 120, height/8 + 20);
            g.drawString("Nasisnij SPACJE aby powrocic do menu poczatkowego", width/2 - 120, height/8 + 40);
        }
        else if (crashed) {
            g.setColor(Color.blue);
            g.drawString("Niestety rozbiles sie.", width / 2 - 30, height / 8);
            g.drawString("Nasisnij ENTER aby zagrac jeszcze raz", width / 2 - 120, height / 8 + 20);
            g.drawString("Nasisnij SPACJE aby powrocic do menu poczatkowego", width / 2 - 120, height / 8 + 40);
            }
    }

    /**
     * Metoda zwaracajaca wysokosc statku
     * @return
     */
    public double getDimH(){
        return h;
    }

    /**
     * Metoda zwracajaca szerkosc statku
     * @return
     */
    public double getDimW(){
        return w;
    }

    public double getActualLocationX(){
        return actualLocationX;
    }
    public double getActualLocationY(){
        return actualLocationY;
    }

    public double getActualSizeWidth(){
        return actualSizeWidth;
    }
    public double getActualSizeHeight(){
        return actualSizeHeight;
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

    public Image getExplosion() {return exp;}

    /**
     * Metoda zwracajaca wspolrzedna x pozycji statku
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * Metoda zwracajaca wspolrzedna y pozycji statku
     * @return
     */
    public double getY() {
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
        addPoint((int)actualSizeWidth, (int)actualSizeHeight);
    }

    /**
     * Metoda ustawiająca aktualna pozycje statku dopasowujac ja do zmiany rozmiaru okna
     */
    public void setLocation(int gameWidth, int gameHeight){
        double xRatio = gameWidth/(double)500;
        double yRatio = gameHeight/(double)500;

        actualLocationX = xRatio*getX();
        actualLocationY = yRatio*getY();
    }

    /**
     * Metoda rysujaca statek powietrzny
     * @param g
     */
    public void drawRocket(Graphics g){
        g.drawImage(getImg(),(int)actualLocationX, (int)actualLocationY ,(int)actualSizeWidth,(int)actualSizeHeight, null);
    }

    /**
     * Metoda rysujaca wybuch statku po nieudanym ladowaniu
     * @param g
     */
    public void drawExplosion(Graphics g){
        g.drawImage(getExplosion(),(int)actualLocationX, (int)actualLocationY ,(int)actualSizeWidth,(int)actualSizeHeight, null);
    }

}

