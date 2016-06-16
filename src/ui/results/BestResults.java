package ui.results;

import javax.swing.*;
import java.io.*;
import java.util.Properties;
import ui.results.UserResult;



/**
 * Created by Jakub on 12.05.2016.
 */
public class BestResults {

    private int size = 10;
    private int position;

    public String[] userName;
    public int[] result;

    private int actualResult;
    private String actualName;

    private UserResult userResult;

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

    private void initUserResult(){
        userResult = UserResult.getInstance();
    }

    private void fromPropToArray(Properties properties){
        userName = new String[size];
        result = new int[size];

        for (int i = 0; i < size; ++i) {
            userName[i] = properties.getProperty("User_" + Integer.toString(i));
            result[i] = Integer.parseInt(properties.getProperty("Result_" + Integer.toString(i)));
        }
    }

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

    public String[] getUserNames(){
        return userName;
    }

    public int[] getResults(){
        return result;
    }

    private int findSlot(){
        actualResult = (int)userResult.getUserResult();
        position = -1;

        for (int i = size-1; i > 0; i-- ) {
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

    private void replaceName() {
        actualName = userResult.getUserName();

        if (position > -1) {
            for (int i = size-1; i > position; --i) {
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