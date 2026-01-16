package eu.senla.management.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
    public static Properties property;

    static {
        try {
            property = new Properties();
            FileInputStream propertyFile = new FileInputStream("src/test/resources/conf/conf.properties");
            property.load(propertyFile);
        } catch (IOException e) {
            System.err.println("Error: Properties file is absent!");
        }
    }

    public static String getProperty(String key) {
        return property.getProperty(key);
    }
}
