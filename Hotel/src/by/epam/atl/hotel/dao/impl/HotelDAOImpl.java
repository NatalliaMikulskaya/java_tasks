package by.epam.atl.hotel.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.hotel.bean.DbProperties;
import by.epam.atl.hotel.dao.HotelDAO;
import by.epam.atl.hotel.dao.exception.DAOException;

public class HotelDAOImpl implements HotelDAO {

	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public HotelDAOImpl(){
		
	}
	
	@Override
	public boolean createDatabase(String filePreferencies) throws DAOException  {
		String driverClassName = "com.mysql.jdbc.Driver";
		
		DbProperties properties = parseProperties(filePreferencies);
		
		try{
			
			Driver driver = (Driver)Class.forName(driverClassName).newInstance();
			LOG.info("Driver was created... ");
		    DriverManager.registerDriver(driver);
		    LOG.info("Driver was registered... ");
		    
		    Connection conn = DriverManager.getConnection(properties.getLocation(), properties.getLoin(), properties.getPassword());
		    
		    LOG.info("Connection was created... ");
		    
		    // Create BD
		    String query = "CREATE DATABASE IF NOT EXISTS " + properties.getDbName();
		    Statement stmnt = conn.createStatement();
		    
		    stmnt.executeUpdate(query);
		       
		    LOG.info("Query was executed... ");
		    
		    // choose database
		    conn.setCatalog(properties.getDbName());
			
		    //create table with permissions
		    if (!createPermissions(conn)){
		    	throw new DAOException("Error occurred while table Permissions was created");
		    }
		    
		    stmnt.close();
		    conn.close();
			return true;
		}
		catch(InstantiationException |ClassNotFoundException e){
			throw new DAOException("Can't create connection.", e);
		}
		catch(IllegalAccessException e){
			throw new DAOException("Can't create access to MySQL.", e);
		}
		catch (SQLException e){
			throw new DAOException("Can't create connection to database.", e);
		}
		
		
	}
	
	private DbProperties parseProperties(String fileName) throws DAOException{
		DbProperties dp = new DbProperties();
		
		File fl = new File(fileName);
		
		if (fl.exists() && fl.isFile() && fl.canRead()){
			try (Scanner sc = new Scanner(fl)) {
				
				 while (sc.hasNextLine()){
					 String line = sc.nextLine();
					 
					 int position = line.indexOf("=");
					 if (position == -1){
						 continue;
					 }
					 
					 if (line.startsWith("location")) {
						dp.setLocation(line.substring(position+1).trim()); 
					 }
					 
					 if (line.startsWith("login")) {
						dp.setLogin(line.substring(position+1).trim()); 
					 }
					 
					 if (line.startsWith("password")) {
						dp.setPassword(line.substring(position+1).trim()); 
					 }
					 
					 if (line.startsWith("databasename")) {
						dp.setDbName(line.substring(position+1).trim()); 
					 }
				 }
				
			}
			catch (FileNotFoundException e){
				throw new DAOException("File "+fileName+" not found.", e);
			}
			
		}
		else {
			throw new DAOException("Can't read file whith properties.");
		}
		
		return dp;
	}

	
	private boolean createPermissions(Connection con) throws DAOException{
		
		try {
			
			Statement stmnt = con.createStatement();
			
			//create table 
			String query = "CREATE TABLE permissions (id  INT NOT NULL AUTO_INCREMENT,"
			 		+ " permission VARCHAR(100) NOT NULL,"
			 		+ " PRIMARY KEY (id));" ;
			
			    
			stmnt.executeUpdate(query);
			 
			//add data into table
			query = "INSERT INTO permissions (permission) VALUES ('Administrator');" ;

			stmnt.executeUpdate(query); 
			 
			query = "INSERT INTO permissions (permission) VALUES ('Look for rooms');" ;

			stmnt.executeUpdate(query); 
			
			query = "INSERT INTO permissions (permission) VALUES ('Do booking');" ;

			stmnt.executeUpdate(query);
			
			stmnt.close();
		}
		catch (SQLException e){
			throw new DAOException("Error occurred while table Permissions was created", e);
		}
		return true;
	}
	
}
