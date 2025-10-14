package co.com.client.project.utils;

import co.com.client.project.exceptions.TestFailure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    private PropertiesManager() { throw new IllegalStateException("Utility class"); }

    private static final String RUTA_PROPERTIES = "src/test/resources/properties/environment.properties";

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream stream = new FileInputStream(RUTA_PROPERTIES)){
            properties.load(stream);
            return properties;
        } catch (IOException e) {
            throw new TestFailure("Error al abrir el archivo properties configuracion.properties");
        }
    }

    public static String getValue(String key)
    {
        Properties properties = loadProperties();
        return properties.getProperty(key);
    }
}
