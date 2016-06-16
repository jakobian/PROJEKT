package ui.menu.mainmenu;

import ui.results.BestResults;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.jar.Attributes;
import ui.results.BestResults;


/**
 * Klasa wyswietlajaca w oknie tablice wynikow
 */
public class ResultsMenu extends JDialog {

    /**
     * Pole przechowujace maksymalny rozmiar tabeli z wynikami graczy
     */
    private int size = 10;
    /**
     * Pole przechowujace tytul "Name" wyswietlany w oknie rezultatow
     */
    public final static String Title1 = "Name";
    /**
     * Pole przechowujace tytul "Score" wyswietlany w oknie rezultatow
     */
    public final static String Title2 = "Score";
    /**
     * Pole przechowujace nazwy graczy wyswietlane w oknie rezultatow
     */
    public String[] Names;
    /**
     * Pole przechowujace rezultaty wyswietlane w oknie rezultatow
     */
    public int[] Results;
    /**
     * Pole inicjujace panel w ktorym umieszczone sa napisy menu rezultatow
     */
    private JPanel panel;
    /**
     * Pole inicjujace naglowek rezultatow
     */
    private JLabel title1;
    /**
     * Pole inicjujace naglowek rezultatow
     */
    private JLabel title2;
    /**
     * Pole inicjujace nazwy najlepszych graczy
     */
    private JLabel[] nameLabels;
    /**
     * Pole inicjujace rezultaty najlepszych graczy
     */
    private JLabel[] resultsLabels;

    /**
     * Konstruktor klasy Result Menu
     * @throws IOException
     */
    public ResultsMenu() throws IOException{
        File file = new File("resources/result.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        fromPropToArray(properties);

        createPanel();
        drawView();
    }

    /**
     * Metoda pobierajaca dane - wynik i nick z pliku konfiguracyjnego
     * @param properties
     */
    private void fromPropToArray(Properties properties){
        Names = new String[size];
        Results = new int[size];

        for (int i = 0; i < size; ++i) {
            Names[i] = properties.getProperty("User_" + Integer.toString(i));
            Results[i] = Integer.parseInt(properties.getProperty("Result_" + Integer.toString(i)));
        }
    }

    /**
     * Metoda tworzaca etykiety i przypisujaca im nazwy gracza
     * @return tablica z nickami graczy
     */
    private JLabel[] createNameLabels() {
        nameLabels = new JLabel[size];
        for (int i = 0; i < size; i++) {
            nameLabels[i] = new JLabel(Names[i]);
        }
        return nameLabels;
    }

    /**
     * Metoda tworzaca etykiety i przypisujaca im wartosci wyniku
     * @return
     */
    private JLabel[] createResultsLabels() {
        resultsLabels = new JLabel[size];
        for (int i = 0; i < size; i++) {
            resultsLabels[i] = new JLabel(String.valueOf(Results[i]));
        }
        return resultsLabels;
    }

    /**
     * Metoda tworzaca wnetrze okna dialogowego menu pauzy
     */
    private void createPanel(){
        panel = new JPanel();
        panel.setLayout(new GridLayout(size+1, 2)); // pionowo;
        title1 = new JLabel(Title1);
        title2 = new JLabel(Title2);
        JLabel[] nameLabels = createNameLabels();
        JLabel[] resultsLabels = createResultsLabels();

        panel.add(title1);
        panel.add(title2);

        for (int i = 0; i < size; i++){
            panel.add(nameLabels[i]);
            panel.add(resultsLabels[i]);
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
