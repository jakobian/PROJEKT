import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

public class Game_Field extends JPanel {

    /**Pole do wyswietlania czasu gry paliwa wyniku*/
    private static final String Time = "Time";
    private static final String Score = "Score";
    private static final String Fuel = "Fuel";

    /**Pole do przechowania sciezki obrazka*/
    private BufferedImage image;


    public Game_Field(){

    }
/*
    private void showImage() {

        File imageFile = new File("..\\PROJEKT\\images\\makieta.jpg");
        try{
            image = ImageIO.read(imageFile);
        }
        catch (IOException ex){
            System.err.println("Cannot read a file");
            ex.printStackTrace();
        }
    }

    private void
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,0,0,this);
    }

*/

    /*public void paint (Graphics g){
        g.drawRect(10,10,50,20);
    }*/

}
