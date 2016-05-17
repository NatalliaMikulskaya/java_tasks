package by.epam.atl.hotel.dao;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.hotel.dao.exception.DAOException;

public class DAOProperties {
	
	
	// Constants ----------------------------------------------------------------------------------
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final Properties PROPERTIES = new Properties();
    public static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    
    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
        	try{
        		throw new DAOException("Properties file '" + PROPERTIES_FILE + "' is missing in classpath.");
        	} catch(DAOException e1){
        		LOG.error("Exception: "+e1);
        	}
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            try {
				throw new DAOException("Cannot load properties file '" + PROPERTIES_FILE + "'.", e);
			} catch (DAOException e1) {
				LOG.error("Exception: "+e1);
			}
        }
    }
    
    // Vars ---------------------------------------------------------------------------------------
    private String specificKey;
    
    public DAOProperties(String specificKey) throws DAOException {
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
