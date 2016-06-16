package ui.results;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** Klasa zapisujaca dane i osiagniecia uzytkownika, klasa typu singleton */
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
     * Pole przechowujace wynik po mapie
     */
    public long mapPoints;


    /**
     * Konstruktor klasy
     */
    private UserResult(){
        user_name = "user";
        user_result = 0;

    }

    private static class UserResultHolder{
        private static final UserResult instance = new UserResult();
    }


    public static UserResult getInstance(){
        return UserResultHolder.instance;
    }


    /**
     * Metoda ustalajaca i zapisujaca wynik gracza
     */
    public void setTotalPoints(long estimatedTime, boolean rocketState, long maxPoints, long coefficient){
        if(rocketState == true) {
            mapPoints = coefficient*(maxPoints - estimatedTime)/(maxPoints);
        }
        else {
            mapPoints = 0;
        }
        this.user_result += mapPoints;
    }

    /**
     * Metoda wczytujaca wynik gracza
     */
    public long getUserResult(){
        if (user_result >0) {
            return user_result;
        }
        else
            return 0;
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
