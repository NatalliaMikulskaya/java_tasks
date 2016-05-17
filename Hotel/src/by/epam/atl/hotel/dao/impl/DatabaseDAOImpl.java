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
	private static String DB_NAME="";
	
	private ConnectionFactory conFactory;
	
	public DatabaseDAOImpl(ConnectionFactory factory) {
		conFactory = factory;
	}
		
	public DatabaseDAOImpl(ConnectionFactory factory, String databaseName) {
		this(factory);
		
		DB_NAME = databaseName;
	}
	 
	@Override
	public void createDatabase() throws DAOException {
		
		if (DB_NAME.isEmpty()){
			throw new DAOException("Database name not specified.");
		}
		
		try (Connection connection = conFactory.getConnection();
			Statement statement = connection.createStatement())
		{
			statement.executeUpdate(SQL_CREATE_DATABASE);
			
            LOG.info("Database was created... ");
            
            // set database as default
            connection.setCatalog(DB_NAME);
            
            LOG.info("Database set as default... ");
		    
		    //create table {users}
		    if (!createUsers(connection)){
		    	throw new DAOException("Error occurred while table User was created");
		    }
            
		}
		catch (SQLException e){
			throw new DAOException("Can't create connection to database.", e);
		}

	}

	/*
	 * Create table {Users}
	 * @return true if table was created successfully
	 */
	private boolean createUsers(Connection connection) throws DAOException{
		
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
			throw new DAOException("Can't create connection to database.", e);
		}
		
		
	}
	
}
