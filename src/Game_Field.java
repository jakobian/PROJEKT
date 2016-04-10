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

    /**Pole do przechowania sciezki obrazka*/
    private BufferedImage image;


    public Game_Field() throws IOException{
        File file = new File("resources/field.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();


        showImage(properties);
        setDimension();
    }

    private void showImage(Properties properties) {
        canvasPath = properties.getProperty("path");

        File imageFile = new File(canvasPath);
        try{
            image = ImageIO.read(imageFile);
        }
        catch (IOException ex){
            System.err.println("Cannot read a file");
            ex.printStackTrace();
        }
    }

    private void setDimension(){
        Dimension dim = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dim);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,0,0,this);
    }



    /*public void paint (Graphics g){
        g.drawRect(10,10,50,20);
    }*/

}
