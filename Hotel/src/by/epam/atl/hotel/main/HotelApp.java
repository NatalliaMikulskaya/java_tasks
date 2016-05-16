package by.epam.atl.hotel.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.hotel.dao.exception.DAOException;
import by.epam.atl.hotel.dao.impl.HotelDAOImpl;

public class HotelApp {
	public static final Logger LOG = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		String fileName = "C:/git_repository/java_tasks/Hotel/parameters";
		
		
		HotelDAOImpl hd = new HotelDAOImpl();
		try{
		 if (hd.createDatabase(fileName) ){
			 LOG.info("Database was created successfully.");
		 }
		 else {
			 LOG.error("Error occurred while database was created.");
		 }
		}
		
		catch (DAOException e){
			LOG.error("EXCEPTION: " + e + " ; "+ e.getCause());
		}

	}

}
