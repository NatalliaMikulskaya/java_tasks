package by.epam.atl.hotel.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import by.epam.atl.hotel.dao.exception.DAOException;

public class ConnectionPoolManager {
	
	private static final int MAX_POOL_SIZE = 5;
	private static String NAME_PROPERTY = "hotel.jdbc";
	
	private Vector<Connection> connectionPool = new Vector<Connection>(); 
	
	public ConnectionPoolManager() throws DAOException{
		try{
			initialise();
		}
		catch(DAOException e){
			throw new DAOException(e);
			
		}
	}
	
	/*
	 * generating maximum connections
	 */
	private void initialise() throws DAOException{
		
		while(!isConnectionPullFull()){
			try{
				connectionPool.addElement(createNewConnection());
			}
			catch(DAOException e){
				throw new DAOException(e);
				
			}
		}
	}
	
	/*
	 * Checking is connection poll is full
	 */
	private synchronized boolean isConnectionPullFull(){
		
		if (connectionPool.size() < MAX_POOL_SIZE){
			return false;
		}
			
		return true;
	}
	
	/*
	 * Create new single connection to database
	 */
	private  Connection createNewConnection() throws DAOException{
		Connection con = null;
		
		try {
			ConnectionFactory factory = ConnectionFactory.getInstance(NAME_PROPERTY);
			con = factory.getConnection();
			return con;
		}
		catch (SQLException e){
			throw new DAOException("Can't create connection.",e);
		}

	}
	
	/*
	 * get the first free connection from poll
	 */
	public synchronized Connection getConnectionFromPool()
	{
		Connection connection = null;

		//Check if there is a connection available. There are times when all the connections in the pool may be used up
		if(connectionPool.size() > 0)
		{
			connection = (Connection) connectionPool.firstElement();
			connectionPool.removeElementAt(0);
		}
		//Giving away the connection from the connection pool
		return connection;
	}
	
	/*
	 * free connection
	 */
	public synchronized void returnConnectionToPool(Connection connection)
	{
		//Adding the connection from the client back to the connection pool
		connectionPool.addElement(connection);
	}
	
}
