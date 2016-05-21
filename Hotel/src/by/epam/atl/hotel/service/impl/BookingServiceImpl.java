package by.epam.atl.hotel.service.impl;

import java.util.Date;
import java.util.List;

import by.epam.atl.hotel.bean.Booking;
import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.BookingDAO;
import by.epam.atl.hotel.dao.DAOFactory;
import by.epam.atl.hotel.dao.RoomDAO;
import by.epam.atl.hotel.dao.exception.DAOException;
import by.epam.atl.hotel.service.BookingService;
import by.epam.atl.hotel.service.ServiceFactory;
import by.epam.atl.hotel.service.UserAccess;
import by.epam.atl.hotel.service.exception.ServiceException;

public class BookingServiceImpl implements BookingService {
	
	private final UserAccess checkAccess = ServiceFactory.getInstance().getUserAccessService();
	private final BookingDAO bookingDao = DAOFactory.getInstance().getBookingDAO();
	private final RoomDAO roomDao = DAOFactory.getInstance().getRoomDAO();
		
	@Override
	public boolean doBooking(User currentUser, Room room, String customer, Date dateFrom, Date dateTo) throws ServiceException {
		if (currentUser.isBanned()){
			throw new ServiceException("User is banned!");
		}
		
		//check user permission
		if (!checkAccess.isUserAllowedDoBooking(currentUser)){
			throw new ServiceException("User has not permission to book rooms!");
		}

		//check if room exists
		if (room.getId() == 0){
			throw new ServiceException("Room does not exists in database!");
		}
		
		if (dateFrom.after(dateTo)){
			throw new ServiceException("Illegal period. Start date bigger than end date.");
		}
		
		Booking order = new Booking();
		order.setCustomerName(customer);
		order.setDateFrom(dateFrom);
		order.setDateTo(dateTo);
		order.setRoom(room);
		order.setUser(currentUser);
		
		
		if (bookRoom(order)){
			return true;
		}
	 
		return false;
	}

	private boolean bookRoom(Booking order) throws ServiceException {

		try {
			//check if room free in period
			if (roomDao.isRoomAvailableInPeriod(order.getRoom(), order.getDateFrom(), order.getDateTo())){
				bookingDao.addOrder(order);
				return true;
			} else {
				return false;
			}
			
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}

	}
	
	@Override
	public boolean cancelBooking(User currentUser, Booking order) throws ServiceException {
		//check if room exists in database
		if (order.getOrderID() == 0){
			throw new ServiceException("Order does not exists in database!");
		}
		
		//check if room exists
		if (order.getRoom().getId() == 0){
			throw new ServiceException("Room does not exists in database!");
		}
		
		if ( currentUser.isBanned()){
			throw new ServiceException("Room does not exists in database!");
		}
		
		boolean canCancel = false;
		if (order.getUser().equals(currentUser)) {
			canCancel = true;
		} else if ( checkAccess.isUserAllowedCancelBooking(currentUser)){
			canCancel = true;
		}
		
		if (canCancel){
			try {
				bookingDao.deleteOrder(order);
				return true;
			}
			catch(DAOException e){
				throw new ServiceException(e);
			}
		}

		return false;
	}

	@Override
	public List<Booking> getBookingByCustomer(User currentUser, String customer) throws ServiceException {
		
		if (customer.isEmpty()){
			throw new ServiceException("Customer not specified!");
		}
		
		if (! checkAccess.isUserAllowedSeeAllBooking(currentUser)){
			throw new ServiceException("User is not allowed see all booking!");
		}
		
		try{
			return bookingDao.findOrdersByCustomer(customer);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Booking> getBookingByUser(User currentUser, User user) throws ServiceException {
		if (user.getUserID() == 0){
			throw new ServiceException("User does not exist in database.");
		}
		
		if (currentUser.equals(user) || 
			checkAccess.isUserAllowedSeeAllBooking(user)){
			
			try {
				return bookingDao.findOrdersByUser(user);
			}
			catch (DAOException e){
				throw new ServiceException(e);
			}
		}
		else {
			throw new ServiceException("User is not allowed see booking by user!");
		}

	}

	@Override
	public List<Booking> getAllBookingForPeriod(User currentUser, Date dateFrom, Date dateTo) throws ServiceException {
		if (dateFrom.after(dateTo)){
			throw new ServiceException("Illegal period. Start date bigger than end date.");
		}
		
		if (!checkAccess.isUserAllowedSeeAllBooking(currentUser)){
			throw new ServiceException("User is not allowed see booking by user!");
		}
		
		try {
			return bookingDao.findOrdersByPeriod(dateFrom, dateTo);
		}
		catch(DAOException e){
			throw new ServiceException(e);
			
		}

	}
}
