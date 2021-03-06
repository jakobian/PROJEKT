package ui.results;

import javax.swing.*;
import java.io.*;
import java.util.Properties;
import ui.results.UserResult;


/**
 * Klasa pobierajaca wynik gracza i zapsujaca go w tablicy wynikow
 */
public class BestResults {

    /**
     * Pole przechowujace i okreslajace rozmiar tabeli wynikow
     */
    private int size = 10;
    /**
     * Pole przechowujace pozycje na ktora zostal wstawiony rezultat/nazwa uzytkownika
     */
    private int position;
    /**
     * Pole przechowujace tablice z nazwami uzytkownikow
     */
    public String[] userName;
    /**
     * Pole przechowujace tablice z rezultatami
     */
    public int[] result;
    /**
     * Pole przechowujace aktualny rezultat uzytkownika
     */
    private int actualResult;
    /**
     * Pole przechowujace aktualna nazwe uzytkownika
     */
    private String actualName;
    /**
     * Pole inicjujace obiekt klasy User Result
     */
    private UserResult userResult;

    /**
     * Konstruktor klasy Best Result
     * @throws IOException
     */
    public BestResults() throws IOException {
        initUserResult();

        File file = new File("resources/result.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        fromPropToArray(properties);
        findSlot();
        replaceName();
        fromArrayToProp();
    }

    /**
     * Metoda odwolujaca sie do obiektu klasy UserResult
     */
    private void initUserResult(){
        userResult = UserResult.getInstance();
    }

    /**
     * Metoda pobierajaca dane - wynik i nick z pliku konfiguracyjnego
     * @param properties
     */
    private void fromPropToArray(Properties properties){
        userName = new String[size];
        result = new int[size];

        for (int i = 0; i < size; ++i) {
            userName[i] = properties.getProperty("User_" + Integer.toString(i));
            result[i] = Integer.parseInt(properties.getProperty("Result_" + Integer.toString(i)));
        }
    }

    /**
     * Metoda zapisujaca dane - wynik i nick z pliku konfiguracyjnego
     */
    private void fromArrayToProp() throws IOException {
        Properties props = new Properties();
        for (int i = 0; i < size; ++i) {
            props.setProperty("User_" + Integer.toString(i), userName[i]);
            props.setProperty("Result_" + Integer.toString(i), Integer.toString(result[i]));
        }
        File file = new File("resources/result.properties");
        OutputStream out = new FileOutputStream(file);
        props.store(out, "Best Results");
    }

    /**
     * Metoda wstawiajaca wynik uzytkownika do tablcy wynikow
     * @return zwraca pozycje na ktora zostal wstawiony wynik do tablicy
     */
    private int findSlot(){
        actualResult = (int)userResult.getUserResult();
        position = -1;

        for (int i = size-1; i >= 0; --i ) {
            if (actualResult > result[i]) {
                if (i == size-1) {
                    result[i] = actualResult;
                }
                else {
                    result[i+1] = result[i];
                    result[i] = actualResult;
                }
                position = i;
            }
        }
        return position;
    }

    /**
     * Metoda wstawiajaca nazwe uzytkownika do tabeli wynikow
     */
    private void replaceName() {
        actualName = userResult.getUserName();

        if (position > -1) {
            for (int i = size-1; i >= position; --i) {
                if (i == size-1) {
                    userName[i] = actualName;
                }
                else {
                    userName[i+1] = userName[i];
                    userName[i] = actualName;
                }
            }
        }
    }
}