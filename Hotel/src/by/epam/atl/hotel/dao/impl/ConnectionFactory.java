package by.epam.atl.hotel.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import by.epam.atl.hotel.dao.DAOProperties;
import by.epam.atl.hotel.dao.DatabaseDAO;
import by.epam.atl.hotel.dao.RoomDAO;
import by.epam.atl.hotel.dao.UserDAO;
import by.epam.atl.hotel.dao.exception.DAOException;

public abstract class ConnectionFactory {
	
	// Constants ----------------------------------------------------------------------------------
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    
    private static String DB_NAME = "hotel";
    
    
    //private static ConnectionFactory instance = new ConnectionFactory();
    
	//public static final String PROPERTY_FILE = "C:/git_repository/java_tasks/Hotel/parameters";

	/*public static DbProperty DP = new DbProperty();
	public static Connection connection;*/
	

	 public static ConnectionFactory getInstance(String name) throws DAOException {
		 if (name == null) {
			 throw new DAOException("Database name is null.");
		 }
	
	     DAOProperties properties = new DAOProperties(name);
	     String url = properties.getProperty(PROPERTY_URL, true);
	     String driverClassName = properties.getProperty(PROPERTY_DRIVER, false);
	     String password = properties.getProperty(PROPERTY_PASSWORD, false);
	     String username = properties.getProperty(PROPERTY_USERNAME, password != null);
	     ConnectionFactory instance;
	     
	     // If driver is specified, then load it to let it register itself with DriverManager.
	     if (driverClassName != null) {
	    	 try {
	         	Class.forName(driverClassName);
	         } catch (ClassNotFoundException e) {
	            throw new DAOException("Driver class '" + driverClassName + "' is missing in classpath.", e);
	         }
	         instance = new DriverManagerDAOFactory(url, username, password);
	     }// Else assume URL as DataSource URL and lookup it in the JNDI.
	     else {
	    	 DataSource dataSource;
	         try {
	        	 dataSource = (DataSource) new InitialContext().lookup(url);
	         } catch (NamingException e) {
	             throw new DAOException("DataSource '" + url + "' is missing in JNDI.", e);
	         }
	         
	         if (username != null) {
	             instance = new DataSourceWithLoginDAOFactory(dataSource, username, password);
	         } else {
	             instance = new DataSourceDAOFactory(dataSource);
	         }
	     }

	     return instance;
	}
	 
	 
	abstract Connection getConnection() throws SQLException;
	 
	/*public UserDAO getUserDAO() {
        return new UserDAOImpl(this, DB_NAME);
    }
	
	public DatabaseDAO getDatabaseDAO() {
        return new DatabaseDAOImpl(this, DB_NAME);
    }
	
	public RoomDAO getRommDAO() {
        return new RoomDAOImpl(this, DB_NAME);
    }*/
	
}


// Default DAOFactory implementations -------------------------------------------------------------

/**
 * The DriverManager based DAOFactory.
 */
class DriverManagerDAOFactory extends ConnectionFactory {
    private String url;
    private String username;
    private String password;

    DriverManagerDAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
	Connection getConnection() throws SQLException {
	    return DriverManager.getConnection(url, username, password);
	}
}

/**
 * The DataSource based DAOFactory.
 */
class DataSourceDAOFactory extends ConnectionFactory {
    private DataSource dataSource;

    DataSourceDAOFactory(DataSource dataSource) {
    	this.dataSource = dataSource;
	}

    @Override
    Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}


/**
 * The DataSource-with-Login based DAOFactory.
 */
class DataSourceWithLoginDAOFactory extends ConnectionFactory {
    private DataSource dataSource;
    private String username;
    private String password;

    DataSourceWithLoginDAOFactory(DataSource dataSource, String username, String password) {
        this.dataSource = dataSource;
        this.username = username;
        this.password = password;
    }

    @Override
    Connection getConnection() throws SQLException {
        return dataSource.getConnection(username, password);
    }
}

/*	private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
		
	public static Connection getConnection() throws DAOException {
        return instance.createConnection();
    }
	
	public static void closeConnection() throws DAOException {
        try { 
        	connection.close();
        } catch (SQLException e) {
            throw new DAOException("Error occurred while close connection", e);
        }
    }
	
	private Connection createConnection() throws DAOException {
        
        try {
        	parseProperties();
            connection = DriverManager.getConnection(DP.getLocation(), DP.getLoin(), DP.getPassword());
        } catch (SQLException e) {
            throw new DAOException("ERROR: Unable to Connect to Database.", e);
        }
        return connection;
    }   
	
	private void parseProperties() throws DAOException{
		DP = new DbProperty();
		
		File fl = new File(PROPERTY_FILE);
		
		if (fl.exists() && fl.isFile() && fl.canRead()){
			try (Scanner sc = new Scanner(fl)) {
				
				 while (sc.hasNextLine()){
					 String line = sc.nextLine();
					 
					 int position = line.indexOf("=");
					 if (position == -1){
						 continue;
					 }
					 
					 if (line.startsWith("location")) {
						 DP.setLocation(line.substring(position+1).trim()); 
					 }
					 
					 if (line.startsWith("login")) {
						 DP.setLogin(line.substring(position+1).trim()); 
					 }
					 
					 if (line.startsWith("password")) {
						 DP.setPassword(line.substring(position+1).trim()); 
					 }
					 
					 if (line.startsWith("databasename")) {
						 DP.setDbName(line.substring(position+1).trim()); 
					 }
				 }
				
			}
			catch (FileNotFoundException e){
				throw new DAOException("File "+PROPERTY_FILE+" not found.", e);
			}
			
		}
		else {
			throw new DAOException("Can't read file whith properties.");
		}
		
	} */

    

