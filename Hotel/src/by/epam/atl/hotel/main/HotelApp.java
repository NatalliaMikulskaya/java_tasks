package by.epam.atl.hotel.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.hotel.dao.DatabaseDAO;
import by.epam.atl.hotel.dao.UserDAO;
import by.epam.atl.hotel.dao.exception.DAOException;
import by.epam.atl.hotel.dao.impl.ConnectionFactory;

public class HotelApp {
	public static final Logger LOG = LogManager.getRootLogger();
	private static final String DB_NAME = "hotel"; 
	
	public static void main(String[] args) {
		
		try {
			ConnectionFactory factory = ConnectionFactory.getInstance("hotel.jdbc");
			
			DatabaseDAO db = factory.getDatabaseDAO(DB_NAME);
			
			db.createDatabase();
			LOG.info("Database was created successfully.");

			UserDAO userDao = factory.getUserDAO();
		}
		
		catch (DAOException e){
			LOG.error("EXCEPTION: " + e + " ; \n"+ e.getCause());
		}

		
	}

}
