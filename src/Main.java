import java.awt.*;
import java.io.IOException;
import ui.gamewindow.GameWindow;

import javax.swing.*;

/** Klasa glowna programu */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow gmw = null;
                try {
                    gmw = new GameWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gmw.setVisible(true);
            }
        });
    }
}
