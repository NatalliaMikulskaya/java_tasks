package by.epam.atl.hotel.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import by.epam.atl.hotel.dao.ConnectionFactory;
import by.epam.atl.hotel.dao.DatabaseDAO;
import by.epam.atl.hotel.dao.exception.DAOException;

public class DatabaseDAOImpl implements DatabaseDAO {
	 private Connection connection;
	 private Statement statement;
	
	 public DatabaseDAOImpl() {
		
	}
	 
	@Override
	public boolean createDatabase() throws DAOException {
		 String query = "CREATE DATABASE IF NOT EXISTS " + ConnectionFactory.DP.getDbName();
		 
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
			SQLWarning warning = statement.getWarnings();
            if (warning != null)
                throw new DAOException(warning.getMessage());
		}
		catch (DAOException e){
			throw new DAOException(e);
		}
		catch (SQLException e){
			throw new DAOException("Can't create connection to database.", e);
		}

		return false;
	}

}
