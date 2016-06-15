package ui.menu.mainmenu;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Michał on 2016-06-15.
 */
public class ResultsMenu extends JDialog {

    private int size = 10;

    /**
     * Pole przechowujace tutyl wyswietlany w oknie rezultatow
     */
    public final static String Title = "Name     Score";
    /**
     * Pole przechowujace razultaty wyswietlane w oknie rezultatow
     */
    public String[] Results;

    /**
     * Pole inicjujace panel w ktorym umieszczone sa napisy menu rezultatow
     */
    private JPanel panel;
    /**
     * Pole inicjujace naglowek rezultatow
     */
    private JLabel title;
    /**
     * Pole inicjujace rezultaty
     */
    private JLabel[] results;


    public ResultsMenu() throws IOException{
        File file = new File("resources/result.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        createResults(properties);
        createPanel();
        drawView();
    }

    private void createResults(Properties properties) {
        String[] user = new String[size];
        String[] res = new String[size];
        Results = new String[size];

        for (int i = 0; i < size; i++) {
            user[i] = properties.getProperty("User_" + Integer.toString(i+1));
            res[i] = properties.getProperty("Result_" + Integer.toString(i+1));
        }

        for (int j = 0; j < size; j++) {
            Results[j] = user[j] + "    " + res[j];
        }
    }

    private JLabel[] createLabels() {
        JLabel[] labels = new JLabel[size];
        for (int i = 0; i < size; i++) {
            labels[i] = new JLabel(Results[i]);
        }
        return labels;
    }

    /**
     * Metoda tworzaca wnetrze okna dialogowego menu pauzy
     */
    private void createPanel(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); // pionowo;
        title = new JLabel(Title);
        JLabel[] labels = createLabels();

        panel.add(title);

        for (int i = 0; i < size; i++) {
            panel.add(labels[i]);
        }
    }

    /**
     * Metoda wyswitlajaca okno menu pauzy
     */
    private void drawView(){
        this.add(panel);
        this.setSize(180, 210);
        setResizable(false);
        setTitle("Best Results");
        this.setLocationRelativeTo(null);
    }

}
