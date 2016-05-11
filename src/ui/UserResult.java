package ui;

/**
 * Created by Jakub on 12.05.2016.
 */
/** Klasa zapisujaca dane i osiagniecia uzytkownika */
public class UserResult {

    private String user_name;
    private Integer user_result;

    public void writeUserresult(Integer user_result) {this.user_result = user_result;}
    public void writeUsername(String user_name) {this.user_name = user_name;}

    public String getUsername() {return user_name;}
    public Integer getUserresult() {return user_result;}


    public UserResult(String user_name,Integer user_result){
        this.user_name = user_name;
        this.user_result = user_result;
    }
}
