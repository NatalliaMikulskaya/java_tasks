package by.epam.atl.hotel.dao.impl;

import java.sql.Connection;
import java.util.concurrent.ArrayBlockingQueue;

import by.epam.atl.hotel.dao.exception.DAOException;

public class ConnectionPoolManager {

	private static final int MAX_POOL_SIZE = 5; //setup size of pool connection
	private static String NAME_PROPERTY = "hotel.jdbc";
	private static ConnectionPoolManager instance;

	private ArrayBlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<Connection>(MAX_POOL_SIZE); 

	private ConnectionPoolManager() throws DAOException{
		
		initialise();
		
	}
	
	public static ConnectionPoolManager getInstance(){
		
		if (instance == null){
			try{
				instance = new ConnectionPoolManager();
			}
			catch (DAOException e){
				throw new RuntimeException("Can't create poll connections",e);
			}
		}
		
		return instance;

	}

	/*
	 * generating maximum connections
	 */
	private void initialise() throws DAOException{

		while(!isConnectionPullFull()){

			if(!connectionPool.offer(createNewConnection())){
			
				throw new DAOException("Can't add connection in pool.");

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
		catch (DAOException e){
			throw new RuntimeException("Can't create connection.",e);
		}

	}

	/*
	 * get the first free connection from poll
	 */
	public synchronized Connection getConnectionFromPool() throws DAOException
	{
		Connection connection = null;

		//Check if there is a connection available. There are times when all the connections in the pool may be used up
		if(connectionPool.size() > 0){
			connection = (Connection) connectionPool.poll();
		} else {
			throw new DAOException("Pool connection is empy. ");
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
		connectionPool.offer(connection);
	}

}
