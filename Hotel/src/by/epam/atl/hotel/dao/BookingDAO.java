package by.epam.atl.hotel.dao;

import java.util.Date;
import java.util.List;

import by.epam.atl.hotel.bean.Booking;
import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.exception.DAOException;

public interface BookingDAO {
	
	public Booking findOrderByID(int id) throws DAOException;
	public List<Booking> list() throws DAOException;
	
	public List<Booking> findOrdersByPeriod(Date dateFrom, Date dateTo) throws DAOException;
	public List<Booking> findOrdersByStartDate(Date dateFrom) throws DAOException;
	public List<Booking> findOrdersByUser(User user) throws DAOException;
	public List<Booking> findOrdersByRoom(Room room) throws DAOException;
	public List<Booking> findOrdersByCustomer(String customerName) throws DAOException;
	
	public void addOrder(Booking order) throws DAOException;
	public void deleteOrder(Booking order) throws DAOException;
	public void updateOrder(Booking order) throws DAOException;
	
}
