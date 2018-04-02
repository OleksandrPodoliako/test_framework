package utils;

import java.net.URL;
import java.util.Properties;

public class PropertiesReader {

    private static Properties properties;

    private PropertiesReader() {
    }

    public static String getProperty(final String propertyName) {
        return properties.getProperty(propertyName);
    }

    static {
        properties = new Properties();
        URL systemResource = ClassLoader.getSystemResource("config.properties");
        try {
            properties.load(systemResource.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
