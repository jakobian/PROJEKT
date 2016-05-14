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

    private int x;
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

   // private Image img;

    public Rocket() {
        init();
        load();
        checkDim();
    }

    private void init() {
        resetRocket();
    }

    public void resetRocket() {
        Random generator = new Random();
        x = generator.nextInt(400)+20;
        y = generator.nextInt(100)+20;

    }

    /**
     * Metoda wczytujaca obrazek statu z pliku
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
        x += dx;
        y += dy;
    }


    public int getDimH(){
        return h;
    }


    public int getDimW(){
        return w;
    }

    private void checkDim(){
        h = img.getHeight();
        w = img.getWidth();
    }

    public Image getImg() {
        return img;
    }

    public int getX() {
        return x;
    }



    public int getY() {
        return y;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }
        else if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        else if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
