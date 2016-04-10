import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JPanel;

public class Game_Field extends JPanel {

    /**Pole do wyswietlania czasu gry paliwa wyniku*/
    private static final String Time = "Time";
    private static final String Score = "Score";
    private static final String Fuel = "Fuel";

    private String canvasPath;
    private String platformPath;

    /**Pole do przechowania sciezki obrazka*/
    private BufferedImage canvas;
    private BufferedImage platform;


    public Game_Field() throws IOException{
        File file = new File("resources/field.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        showImage(properties);
    }

    private void showImage(Properties properties) {
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

    public void setDimension(int width, int height){
        Dimension dim = new Dimension(width,height);
        setPreferredSize(dim);

    }

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
