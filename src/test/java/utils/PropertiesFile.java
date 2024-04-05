package utils;

import constants.GlobalConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

    private static FileInputStream inputStream;
    private static Properties prop = null;
    private static final String CONFIG_FILE = GlobalConstants.CONFIG_PROPERTIES_FILE;

    public static String getProperty(String property) {

        try {
            inputStream = new FileInputStream(CONFIG_FILE);
            prop = new Properties();
            prop.load(inputStream);
        } catch(FileNotFoundException e) {
            System.out.println("Properties File Not Found");
        } catch(IOException e) {
            System.out.println("IO Exception while loading Properties File");
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                System.out.println("IO Exception while closing file input stream");
            }
        }
        return prop.getProperty(property).trim();
    }
}
