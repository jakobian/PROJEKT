package ui.results;

/**
 * Created by Jakub on 12.05.2016.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** Klasa zapisujaca dane i osiagniecia uzytkownika */
public class UserResult {

    /**
     * Pole przechowujace nazwe gracza
     */
    public String user_name ;
    /**
     * Pole przechowujace wynik gracza
     */
    public long user_result;

    /**
     * Konstruktor wyniku gracza
     */
    private long maxPoints;

    private long coefficient;

    public UserResult() throws IOException {
        File file = new File("resources/area1.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        getMaxPoints(properties);
    }

    private void getMaxPoints(Properties properties){

        maxPoints = Long.parseLong(properties.getProperty("maxPoints"));
        coefficient = Long.parseLong(properties.getProperty("coefficient"));
    }

    public void setTotalPoints(long estimatedTime){
        user_result = (maxPoints - estimatedTime)/(maxPoints/coefficient);
    }

    /**
     * Metoda wczytujaca wynik gracza
     */
    public long getUserResult(){
        if (user_result >0)
            return user_result;
        else
            return 0;
    }

    /**
     * Metoda zapisujaca wynik gracza
     */
    public void writeUserResult(Integer user_result) {
        this.user_result = user_result;
    }
    /**
     * Metoda zapisujaca nazwe gracza
     */
    public void writeUserName(String user_name) {
        this.user_name = user_name;
    }
    /**
     * Metoda wczytujaca nazwe gracza
     */
    public String getUserName() {
        return user_name;
    }
}
