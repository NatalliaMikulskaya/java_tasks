package by.epam.atl.hotel.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.hotel.bean.TypeUser;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.DatabaseDAO;
import by.epam.atl.hotel.dao.UserDAO;
import by.epam.atl.hotel.dao.exception.DAOException;
import by.epam.atl.hotel.dao.impl.ConnectionFactory;

public class HotelApp {
	public static final Logger LOG = LogManager.getRootLogger();
		
	public static void main(String[] args) {
		
		try {
			ConnectionFactory factory = ConnectionFactory.getInstance("hotel.jdbc");
			
			DatabaseDAO db = factory.getDatabaseDAO();
			
			//db.createDatabase();
			//LOG.info("Database was created successfully.");

			UserDAO userDao = factory.getUserDAO();
			//create user
			User usr = new User();
			usr.setUserName("Administrator");
			usr.setEmail("admin@hotel.com");
			usr.setIsBanned(false);
			usr.setType(TypeUser.ADMINISTRATOR);
			usr.setUserLogin("Administrator");
			usr.setUserPassword("111");
			
			if (userDao.existLogin(usr.getUserLogin())){
				LOG.error("Can't add user because login {"+usr.getUserLogin()+"} exists. ");
			}else {
				userDao.create(usr);
			}
			
			//check if user exists
			
			
			
		}
		
		catch (DAOException e){
			LOG.error("EXCEPTION: " + e + " ; \n"+ e.getCause());
		}

		
	}

}
