package by.epam.atl.hotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import by.epam.atl.hotel.dao.exception.DAOException;

public class ConnectionFactory {

	// Constants ----------------------------------------------------------------------------------
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USERNAME = "username";
	private static final String PROPERTY_PASSWORD = "password";

	private static String driverClassName;
	private static String url;
	private static String username;
	private static String password;

	private static ConnectionFactory instance;

	private ConnectionFactory(String name) throws DAOException{
		
		if (name == null) {
			throw new DAOException("Database name is not specified.");
		}
		prepareDataForConnection(name);
	}

	public static ConnectionFactory getInstance(String name) throws DAOException  {

		if ( instance == null){
			instance = new ConnectionFactory(name); 
		}
		
		return instance;
	}

	private static void prepareDataForConnection(String name){
		try {
			DAOProperty properties = new DAOProperty(name);
			url = properties.getProperty(PROPERTY_URL, true);
			driverClassName = properties.getProperty(PROPERTY_DRIVER, true);
			password = properties.getProperty(PROPERTY_PASSWORD, true);
			username = properties.getProperty(PROPERTY_USERNAME, true);
		}
		catch (DAOException e){
			throw new RuntimeException(e);
		}
	}


	public Connection getConnection() throws DAOException{

		// If driver is specified, then load it to let it register itself with DriverManager.
		if (driverClassName != null) {
			Connection con;

			try {
				Class.forName(driverClassName);
			} catch (ClassNotFoundException e) {
				throw new DAOException("Driver class '" + driverClassName + "' is missing in classpath.", e);
			}

			try{
				con = DriverManager.getConnection(url, username, password);
			}
			catch (SQLException e){
				throw new DAOException("Can't create connection.", e);
			}
			return con;

		}// Else assume URL as DataSource URL and lookup it in the JNDI.
		else {
			throw new DAOException("Driver class '" + driverClassName + "' is missing in classpath.");
		}
	}
}








