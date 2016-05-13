package ui.results;

/**
 * Created by Jakub on 12.05.2016.
 */
/** Klasa zapisujaca dane i osiagniecia uzytkownika */
public class UserResult {

    /**
     * Pole przechowujace nazwe gracza
     */
    private String user_name;
    /**
     * Pole przechowujace wynik gracza
     */
    private Integer user_result;

    /**
     * Konstruktor wyniku gracza
     */
    public UserResult(String user_name,Integer user_result){
        this.user_name = user_name;
        this.user_result = user_result;
    }

    /**
     * Metoda zapisujaca nazwe gracza
     */
    public void writeUserresult(Integer user_result) {this.user_result = user_result;}
    /**
     * Metoda zapisujaca wynik gracza
     */
    public void writeUsername(String user_name) {this.user_name = user_name;}
    /**
     * Metoda wczytujaca nazwe gracza
     */
    public String getUsername() {return user_name;}
    /**
     * Metoda wczytujaca wynik gracza
     */
    public Integer getUserresult() {return user_result;}
}
