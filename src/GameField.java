import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jakub on 12.04.2016.
 */


public class GameField extends JPanel{
    private int[] point_x;
    private int[] point_y;


    public GameField() throws IOException{
        File file = new File("resources/area.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        createAreaPoints(properties);

    }


    private void createAreaPoints(Properties properties) {
        int total_number_points = Integer.parseInt(properties.getProperty("total_number_points"));
        point_x = new int[total_number_points];
        point_y = new int[total_number_points];
        for (int i = 0; i < total_number_points; ++i) {
            point_x[i] = Integer.parseInt(properties.getProperty("point_x_" + Integer.toString(i)));
            point_y[i] = Integer.parseInt(properties.getProperty("point_y_" + Integer.toString(i)));
        }
    }

    private void drawArea(Graphics g, int[] point_x, int[] point_y){
        g.fillPolygon(point_x, point_y, point_x.length);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.gray);
        drawArea(g, point_x, point_y);


    }
}

