package by.epam.atl.hotel.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import by.epam.atl.hotel.bean.DbProperty;
import by.epam.atl.hotel.dao.exception.DAOException;

public class ConnectionFactory {
	private static ConnectionFactory instance = new ConnectionFactory();
	
	public static final String PROPERTY_FILE = "C:/git_repository/java_tasks/Hotel/parameters";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
	public static DbProperty DP = new DbProperty();
	public static Connection connection;
	

	private ConnectionFactory() {
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
		
	} 
    
    
}
