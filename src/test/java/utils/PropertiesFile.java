package utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

    private static FileInputStream fis;
    private static Properties prop = null;

    public static String getProperty(String property) {

        try {
            fis = new FileInputStream("config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException e) {
            System.out.println("Properties File Not Found");
        } catch(IOException e) {
            System.out.println("IO Exception while loading Properties File");
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println("IO Exception while closing file input stream");
            }
        }
        return prop.getProperty(property).trim();
    }
}
