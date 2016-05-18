package by.epam.atl.hotel.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import by.epam.atl.hotel.dao.exception.DAOException;

public class DAOProperty {
	
	
	// Constants ----------------------------------------------------------------------------------
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final Properties PROPERTIES = new Properties();
    
    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
        	throw new RuntimeException("Properties file '" + PROPERTIES_FILE + "' is missing in classpath.");

        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            
			throw new RuntimeException("Cannot load properties file '" + PROPERTIES_FILE + "'.", e);
			
        }
    }
    
    // Vars ---------------------------------------------------------------------------------------
    private String specificKey;
    
    public DAOProperty(String specificKey) throws DAOException {
        this.specificKey = specificKey;
    }
    
    public String getProperty(String key, boolean mandatory) throws DAOException {
        String fullKey = specificKey + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property == null || property.trim().length() == 0) {
            if (mandatory) {
                throw new DAOException("Required property '" + fullKey + "'"
                    + " is missing in properties file '" + PROPERTIES_FILE + "'.");
            } else {
                // Make empty value null. Empty Strings are evil.
                property = null;
            }
        }

        return property;
    }
}
