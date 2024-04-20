package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * To select the base path based on environment so that tests can run on all env
 */
public class ConfigManager {

    private Properties properties;

    /**
     * To change the env based on systen varibale 'env' and fetch all the details from the properties file
     * env must be either dev, qa, staging or prod
     */
    public ConfigManager() {
        properties = new Properties();
        try {
            String environment = System.getProperty("env") == null ? "qa" : System.getProperty("env");
            String propertyFileName = "config/config-" + environment + ".properties";
            InputStream is = getClass().getClassLoader().getResourceAsStream(propertyFileName);
            properties.load(is);
            Objects.requireNonNull(is).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * To fetch base url from properties files
     *
     * @return
     */
    public String getBaseUrl() {
        return properties.getProperty("base_url");
    }


}