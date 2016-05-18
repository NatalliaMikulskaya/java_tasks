package by.epam.atl.hotel.main;

import java.sql.Connection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.hotel.bean.TypeUser;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.DatabaseDAO;
import by.epam.atl.hotel.dao.UserDAO;
import by.epam.atl.hotel.dao.exception.DAOException;
import by.epam.atl.hotel.dao.impl.ConnectionFactory;
import by.epam.atl.hotel.dao.impl.ConnectionPoolManager;
import by.epam.atl.hotel.dao.impl.DatabaseDAOImpl;
import by.epam.atl.hotel.dao.impl.UserDAOImpl;
import by.epam.atl.hotel.view.UsersConsoleView;

public class HotelApp {
	public static final Logger LOG = LogManager.getRootLogger();
		
	public static void main(String[] args) {
		
		try {
			
			ConnectionPoolManager connectionPool = new ConnectionPoolManager();
			Connection connection = connectionPool.getConnectionFromPool();
			
			//ConnectionFactory factory = ConnectionFactory.getInstance("hotel.jdbc");
			
			//DatabaseDAO db = new DatabaseDAOImpl(connection, "hotel");
			
			//db.createDatabase();
			//LOG.info("Database was created successfully.");

			UserDAO userDao = new UserDAOImpl(connection, "hotel");
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
			
			UsersConsoleView userView = new UsersConsoleView();
			userView.print(usr);
			
			//check if user exists
			
			connectionPool.returnConnectionToPool(connection);
			
		}
		
		catch (DAOException e){
			LOG.error("EXCEPTION: " + e + " ; \n"+ e.getCause());
		}

		
	}

}
