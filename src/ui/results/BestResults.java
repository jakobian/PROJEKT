package ui.results;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;



/**
 * Created by Jakub on 12.05.2016.
 */
public class BestResults {

    private int size = 10;

    public String[] userName;
    public int[] result;

    private int actualResult;

    private UserResult userResult;

    public BestResults() throws IOException {
        File file = new File("resources/results.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        fromPropToArray(properties);

    }

    private void fromPropToArray(Properties properties){
        userName = new String[size];
        result = new int[size];

        for (int i = 0; i < size; ++i) {
            userName[i] = properties.getProperty("User_" + Integer.toString(i));
            result[i] = Integer.parseInt(properties.getProperty("Result_" + Integer.toString(i)));
        }
    }

    public String[] getUserNames(){
        return userName;
    }

    public int[] getUserResults(){
        return result;
    }

    private void saveResults() throws IOException{
        File file = new File("resources/results.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Properties properties = new Properties();

        fileOutputStream.close();
    }

    private void findSlot(){
        actualResult = (int)userResult.getUserResult();


        for(int i=0; i < size; ++i ){
            if(actualResult<result[i]){

            }
        }
    }



}
