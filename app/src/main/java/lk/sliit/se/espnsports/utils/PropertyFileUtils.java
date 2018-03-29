package lk.sliit.se.espnsports.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Handles operations related to property files
 *
 * Created by dinukshakandasamanage on 3/29/18.
 */

public class PropertyFileUtils {

    /**
     * Retrieves the given property from the given file
     *
     * @param fileName
     * @param property
     * @return
     */
    public static String getPropertyValue(String fileName, String property){

        try (InputStream inputStream = new FileInputStream(fileName)) {

            Properties properties = new Properties();

            properties.load(inputStream);

            return properties.getProperty(property);

        } catch (IOException e) {
            System.out.println("Failed to retrieve property: " + e);
        }
        return null;
    }
}
