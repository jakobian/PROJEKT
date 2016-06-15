package ui.results;

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

    public BestResults() throws IOException {
        loadResults();
        /*File file = new File("resources/results.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();*/

        fromPropToArray(properties);

    }

    private void loadResults() throws IOException{
        File file = new File("resources/results.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
    }

    private void fromPropToArray(Properties properties){
        userName = new String[size];
        result = new int[size];

        for (int i = 0; i < size; ++i) {
            userName[i] = properties.getProperty("User_" + Integer.toString(i));
            result[i] = Integer.parseInt(properties.getProperty("Result_" + Integer.toString(i)));
        }
    }

    private void saveResults() throws IOException{
        File file = new File("resources/results.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Properties properties = new Properties();

        fileOutputStream.close();
    }


}
