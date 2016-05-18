package by.epam.atl.hotel.dao.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.hotel.dao.DatabaseDAO;
import by.epam.atl.hotel.dao.exception.DAOException;

public class DatabaseDAOImpl implements DatabaseDAO {
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final String SQL_CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS hotel DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;";
	private static final String SQL_CREATE_USERS = "CREATE TABLE users (id  INT NOT NULL AUTO_INCREMENT,"
													+ " name VARCHAR(300) NOT NULL,"
													+ " login CHAR(20) NOT NULL,"
													+ " password VARCHAR(16) NOT NULL,"
													+ " email VARCHAR(100),"
													+ " ban BOOL,"
													+ " access VARCHAR(100),"
													+ " PRIMARY KEY (id));";
	
	private static final String SQL_CREATE_ROOMS = "CREATE TABLE rooms (id  INT NOT NULL AUTO_INCREMENT,"
													+ " capacity INT NOT NULL,"
													+ " type CHAR(40) NOT NULL,"
													+ " smoke BOOL,"
													+ " available  BOOL,"
													+ " PRIMARY KEY (id));";
	
	private static final String SQL_CREATE_BOOKING = "CREATE TABLE booking (order_id  INT NOT NULL AUTO_INCREMENT,"
			+ " room_id INT NOT NULL,"
			+ " user_id INT NOT NULL,"
			+ " date_from DATE NOT NULL,"
			+ " date_to DATE NOT NULL,"
			+ " PRIMARY KEY (order_id),"
			+ " FOREIGN KEY (room_id) REFERENCES rooms(id),"
			+ " FOREIGN KEY (user_id) REFERENCES users(id));";
	
	private static String DB_NAME;
	
	private Connection connection;
	
	public DatabaseDAOImpl(Connection con, String dbName) {
		connection = con;
		DB_NAME = dbName;
	}
		
	/*public DatabaseDAOImpl(ConnectionFactory factory, String databaseName) {
		this(factory);
		
		DB_NAME = databaseName;
	}*/
	 
	@Override
	public void createDatabase() throws DAOException {
		
		/*if (DB_NAME.isEmpty()){
			throw new DAOException("Database name not specified.");
		}*/
		
		try (Statement statement = connection.createStatement())
		{
			statement.executeUpdate(SQL_CREATE_DATABASE);
			
            LOG.info("Database was created... ");
            
            // set database as default
            connection.setCatalog(DB_NAME);
            
            LOG.info("Database set as default... ");
		    
		    //create table {users}
		    if (!createUsers()){
		    	throw new DAOException("Error occurred while table {Users} was created");
		    }
		    
		    //create table {rooms}
		    if (!createRooms()){
		    	throw new DAOException("Error occurred while table {Rooms} was created");
		    }
		    
		    //create table {booking}
		    if (!createBooking()){
		    	throw new DAOException("Error occurred while table {booking} was created");
		    }
            
		}
		catch (SQLException e){
			throw new DAOException("Can't create statement.", e);
		}

	}

	/*
	 * Create table {Users}
	 * @return true if table was created successfully
	 */
	private boolean createUsers() throws DAOException{
		
		try (Statement statement = connection.createStatement())
		{
			statement.executeUpdate(SQL_CREATE_USERS);
			
			SQLWarning warning = statement.getWarnings();
            if (warning != null) {
                throw new DAOException(warning.getMessage());
            }

            LOG.info("Table {users} was created... ");
            
            return true;
		}
		catch (SQLException e){
			throw new DAOException("Can't create statement.", e);
		}
		
		
	}
	
	/*
	 * Create table {Rooms}
	 * @return true if table was created successfully
	 */
	private boolean createRooms() throws DAOException{
		
		try (Statement statement = connection.createStatement())
		{
			statement.executeUpdate(SQL_CREATE_ROOMS);
			
			SQLWarning warning = statement.getWarnings();
            if (warning != null) {
                throw new DAOException(warning.getMessage());
            }

            LOG.info("Table {rooms} was created... ");
            
            return true;
		}
		catch (SQLException e){
			throw new DAOException("Can't create statement.", e);
		}
		
		
	}
	
	/*
	 * Create table {Booking}
	 * @return true if table was created successfully
	 */
	private boolean createBooking() throws DAOException{
		
		try (Statement statement = connection.createStatement())
		{
			statement.executeUpdate(SQL_CREATE_BOOKING);
			
			SQLWarning warning = statement.getWarnings();
            if (warning != null) {
                throw new DAOException(warning.getMessage());
            }

            LOG.info("Table {Booking} was created... ");
            
            return true;
		}
		catch (SQLException e){
			throw new DAOException("Can't create statement.", e);
		}
		
		
	}
	
}
