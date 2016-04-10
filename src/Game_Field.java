import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JPanel;

/** Klasa glownego pola gry */

public class Game_Field extends JPanel {

    /*
    private static final String Time = "Time";
    private static final String Score = "Score";
    private static final String Fuel = "Fuel";
*/
    /** Pole do przechowania sciezki obrazu tla*/
    private String canvasPath;
    /** Pole do przechowania sciezki obrazu platformy*/
    private String platformPath;

    /** Bufor do przechowania obrazu tla */
    private BufferedImage canvas;
    /** Bufor do przechowania obrazu platformy */
    private BufferedImage platform;


    /** Konstruktor pola gry */
    public Game_Field() throws IOException{
        File file = new File("resources/field.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        readImage(properties);
    }

    /** Metoda czytajaca obrazy z pliku */
    private void readImage(Properties properties) {
        canvasPath = properties.getProperty("canvas");
        platformPath = properties.getProperty("platformPath");

        File canvasFile = new File(canvasPath);
        File platformFile = new File(platformPath);

        try{
            canvas = ImageIO.read(canvasFile);
            platform = ImageIO.read(platformFile);
        }
        catch (IOException ex){
            System.err.println("Cannot read a file");
            ex.printStackTrace();
        }
    }

    /**
     * Metoda ustawiajaca wielkosc obrazka
     * @param width Poczatkowa szerokosc obrazka w pikselach
     * @param height Poczatkowa wysokosc obrazka w pikselach
     */
    public void setDimension(int width, int height){
        Dimension dim = new Dimension(width,height);
        setPreferredSize(dim);

    }

    /** Metoda wyswietlajaca obrazki w panelu */
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(canvas,0,0,this);
        g2d.drawImage(platform, 200,350,this);
    }


/*
    public void paint (Graphics g){
        g.drawRect(10,10,50,20);
    }*/

}
