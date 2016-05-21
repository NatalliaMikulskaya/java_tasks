package by.epam.atl.hotel.service;


import java.util.Date;
import java.util.List;

import by.epam.atl.hotel.bean.Booking;
import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.service.exception.ServiceException;


public interface BookingService {

	
	boolean doBooking(User currentUser, Room room, String customer, Date dateFrom, Date dateTo) throws ServiceException;
	boolean cancelBooking(User currentUser, Booking order) throws ServiceException;
	
	List<Booking> getBookingByCustomer(User currentUser, String customer) throws ServiceException;
	List<Booking> getBookingByUser(User currentUser, User user) throws ServiceException;
	List<Booking> getAllBookingForPeriod(User currentUser, Date dateFrom, Date dateTo) throws ServiceException;
}
