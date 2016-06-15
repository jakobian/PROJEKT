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

    UserResult[] list;

    public BestResults() throws IOException {
        loadResults();

    }

    private void loadResults() throws IOException{
        File file = new File("resources/results.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
    }

    private void saveResults() throws IOException{
        File file = new File("resources/results.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Properties properties = new Properties();

        fileOutputStream.close();
    }


}
