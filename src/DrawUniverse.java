import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by Jakub on 11.04.2016.
 */


/**
 * Klasa definujaca wszechswiat - podloze, grawitacje...
 */
public class DrawUniverse {


    private double gravity;
    private Point[] area;


    /**
     * Konstruktor klasy DrawUniverse
     */
    DrawUniverse() throws IOException{
        File file = new File("resources/Area.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
    }

    private void createArea(){
        area = new Point[Integer.parseInt(properties.getProperty(""))];
        for(int i = 0; i<area.length;++i){
            area[i] = new Point(Integer.parseInt(properties.getProperty("")),
                    Integer.parseInt(properties.getProperty("")));
        }

    }


}
