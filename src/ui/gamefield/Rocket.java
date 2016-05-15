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
 * Created by Michał on 2016-05-13.
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
    public int maxLandingSpeed = 5;
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

    public boolean landed;
    public boolean crashed;

    public void Draw(Graphics g, int width, int height)
    {
        if(landed)
        {
            g.drawString("Brawo, wyladowales!!!", width/2, height/2);
        }
        else if(crashed)
        {
            g.drawString("Niestety rozbiles sie.", width/2, height/2);

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
}
