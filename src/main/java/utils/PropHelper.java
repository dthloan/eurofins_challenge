package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropHelper {
    public static Properties loadPropertiesByFilePath(String filePath) {
        Properties prop = new Properties();
        try (InputStream inputStream = new FileInputStream(new File(filePath))) {
            prop.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException("Sorry, unable to find property file: " + filePath);
        }
        return prop;
    }
}
