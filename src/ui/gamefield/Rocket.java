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
     * Pole przechowujace wspolrzedana x pozycji statku
     */
    private int x;
    /**
     * Pole przechowujace wspolrzedana y pozycji statku
     */
    private int y;
    private int dx;
    private int dy;
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
     *
     */
    private void init() {
        resetRocket();
    }

    /**
     * Metoda ustalajaca poczatkowa pozycje statku
     */
    public void resetRocket() {
        Random generator = new Random();
        x = generator.nextInt(400)+20;
        y = generator.nextInt(150)+20;

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
        if (GameField.keyboardKeyState(KeyEvent.VK_UP))
            dy = -1;
        else if (GameField.keyboardKeyState(KeyEvent.VK_DOWN))
            dy = 1;
        else if (GameField.keyboardKeyState(KeyEvent.VK_RIGHT))
            dx = 1;
        else if (GameField.keyboardKeyState(KeyEvent.VK_LEFT))
            dx = -1;


        x += dx;
        y += dy;
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
