package by.epam.atl.hotel.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.TypeRoom;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.service.exception.ServiceException;

public interface RoomService {

	List<Room> getListRooms(User user) throws ServiceException;
	List<Room> getListRoomsByAvailable(User user) throws ServiceException;
	List<Room> getListRoomsByParameters(User user, HashMap<String, Object> parameters) throws ServiceException;
	
	boolean addRoom(User currentUser, int number, int capacity, boolean smoke, boolean available, TypeRoom type) throws ServiceException;
	boolean deleteRoom(User user, Room room) throws ServiceException;
	void deleteRooms(User user, List<Room> rooms) throws ServiceException;
	void changeRooms(User user, List<Room> rooms) throws ServiceException;
	
	void closeRooms(User user, List<Room> rooms, Date dateFrom, Date dateTo) throws ServiceException;
	void openRooms(User user, List<Room> rooms) throws ServiceException;
	
}
