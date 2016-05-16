package by.epam.atl.hotel.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.hotel.bean.DbProperty;
import by.epam.atl.hotel.dao.HotelDAO;
import by.epam.atl.hotel.dao.exception.DAOException;

public class HotelDAOImpl implements HotelDAO {

	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	//private static String driverClassName = "com.mysql.jdbc.Driver";
	private static DbProperty dbProperties = null;
	
	private static Connection conn;
    private static Statement stmnt;
    private static PreparedStatement pStmnt;
    private static ResultSet rs;
	
	public HotelDAOImpl(){
		
	}
	
	@Override
	public boolean createDatabase(String filePreferencies) throws DAOException  {
		
		try{

		    // Create BD
		    String query = "CREATE DATABASE IF NOT EXISTS " + dbProperties.getDbName();
		    stmnt = conn.createStatement();
		    
		    stmnt.executeUpdate(query);
		       
		    LOG.info("Query was executed... ");
		    
		    // choose database
		    conn.setCatalog(dbProperties.getDbName());
			
		    //create table with permissions
		    if (!createPermissions()){
		    	throw new DAOException("Error occurred while table Permissions was created");
		    }
		    
		    //create table with users
		    if (!createUsers()){
		    	throw new DAOException("Error occurred while table Permissions was created");
		    }
		    

			return true;
		}
		catch (SQLException e){
			throw new DAOException("Can't create connection to database.", e);
		}
		
		
	}
	
	
	private boolean createPermissions() throws DAOException{
		
		try {
			
			stmnt = conn.createStatement();
			
			
			//create table 
			String query = "CREATE TABLE permissions (id  INT NOT NULL AUTO_INCREMENT,"
			 		+ " permission VARCHAR(100) NOT NULL,"
			 		+ " PRIMARY KEY (id));" ;
			
			    
			stmnt.executeUpdate(query);
			 
			query = "INSERT INTO permissions (permission) VALUES (?);" ;
			
			pStmnt = conn.prepareStatement(query);
			pStmnt.setString(1, "Administrator");
			pStmnt.executeUpdate();
			
			pStmnt.setString(1, "Look for rooms");
			pStmnt.executeUpdate();
			
			pStmnt.setString(1, "Do booking");
			pStmnt.executeUpdate();

			
			stmnt.close();
			pStmnt.close();
		}
		catch (SQLException e){
			throw new DAOException("Error occurred while table Permissions was created", e);
		}
		return true;
	}

	
private boolean createUsers() throws DAOException{
		
		try {
			
			stmnt = conn.createStatement();
			
			
			//create table 
			String query = "CREATE TABLE users (id  INT NOT NULL AUTO_INCREMENT,"
					+ " username VARCHAR(300) NOT NULL,"
					+ " userlogin CHAR(20) NOT NULL,"
					+ " userpassword VARCHAR(16) NOT NULL,"
					+ " isBanned BOOL,"
			 		+ " permissions INT,"
			 		+ " PRIMARY KEY (id));" ;
			
			    
			stmnt.executeUpdate(query);
			 
			query = "INSERT INTO users (username, userlogin, userpassword, isBanned) VALUES (?,?,?,?);" ;
			
			pStmnt = conn.prepareStatement(query);
			pStmnt.setString(1, "'Administrator'");
			pStmnt.setString(2, "'Admin'");
			pStmnt.setString(3, "'111'");
			pStmnt.setString(4, "0");
			pStmnt.executeUpdate();
			
			//get id for added string from {users}
			query = "SELECT ID FROM users WHERE username = 'Administrator'";
			
			rs = stmnt.executeQuery(query);
			rs.next();
			
			int adminID = rs.getInt("id"); // id for Administrator
				
			//add permission for administrator
							
			
			
			stmnt.close();
			pStmnt.close();
		}
		catch (SQLException e){
			throw new DAOException("Error occurred while table Permissions was created", e);
		}
		return true;
	}
	
	
}
