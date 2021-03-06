package by.epam.atl.hotel.dao;

import java.util.Date;
import java.util.List;

import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.TypeRoom;
import by.epam.atl.hotel.dao.exception.DAOException;

public interface RoomDAO {
	
	public Room findByID(int number) throws DAOException;
	public Room findByNumber(int number) throws DAOException;
	public List<Room> list() throws DAOException;
	
	public List<Room> findAvailableRooms(boolean available) throws DAOException;
	public List<Room> findAllAvailableRooms(boolean canSmoke) throws DAOException;
	public List<Room> findAllAvailableRooms(TypeRoom type, boolean canSmoke) throws DAOException;
	public List<Room> findAllAvailableRooms(TypeRoom type, int capacity, boolean canSmoke ) throws DAOException;
	public List<Room> findAllAvailableRooms(TypeRoom type, int capacity) throws DAOException;
	public List<Room> findAllAvailableRooms(int capacity) throws DAOException;
	public List<Room> findAllAvailableRooms(TypeRoom type) throws DAOException;
	public List<Room> findAllAvailableRooms(int capacity, boolean canSmoke ) throws DAOException;
	
	public List<Room> findAllNotBookedRoomsInPeriod(Date dateFrom, Date dateTo ) throws DAOException;
	public boolean isRoomAvailableInPeriod(Room room, Date dateFrom, Date dateTo ) throws DAOException;
	
	public void create(Room room) throws IllegalArgumentException, DAOException;
	public void update(Room room) throws IllegalArgumentException, DAOException;
	public void delete(Room room) throws DAOException;
	
	public void closeRoom(Room room) throws DAOException;
	public int closeRoomsIfNotBookedInPeriod(List<Room> rooms, Date dateFrom, Date dateTo) throws DAOException;
	
	public void openRoom(Room room) throws DAOException;
	public int openRoom(List<Room> rooms) throws DAOException;
}
