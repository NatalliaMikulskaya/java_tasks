package by.epam.atl.hotel.dao;

import by.epam.atl.hotel.dao.impl.BookingDAOImpl;
import by.epam.atl.hotel.dao.impl.RoomDAOImpl;
import by.epam.atl.hotel.dao.impl.UserDAOImpl;

public class DAOFactory {
	private static final DAOFactory factory = new DAOFactory();
	private final BookingDAO bookingDAO = new BookingDAOImpl();
	private final RoomDAO roomDAO = new RoomDAOImpl();
	private final UserDAO userDAO = new UserDAOImpl();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return factory;
	}
	
	public BookingDAO getBookingDAO(){
		return bookingDAO;
	}
	
	public RoomDAO getRoomDAO(){
		return roomDAO;
	}
	
	public UserDAO getUserDAO(){
		return userDAO;
	}
}
