package ui.gamefield;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Michał on 2016-05-13.
 */
public class Rocket {

    private int x;
    private int y;
    private int dx;
    private int dy;

    private Image img;

    public Rocket() {
        init();
        load();
    }

    private void init() {
        resetRocket();

    }

    private void load() {
        ImageIcon i = new ImageIcon("/PROJEKT/images/space_ship.png");
        img = i.getImage();
    }

    public void resetRocket() {
        //x = (int)(0.5*GameField.gameWidth);
        //y = (int)(0.1*GameField.gameHeight);
        x = 300;
        y = 200;
    }

    public void move() {
        x += dx;
        y += dy;
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
