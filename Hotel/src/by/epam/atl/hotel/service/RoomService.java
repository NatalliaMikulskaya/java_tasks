package by.epam.atl.hotel.service;

import java.util.List;

import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.service.exception.ServiceException;

public interface RoomService {

	List<Room> getListRooms(User user) throws ServiceException;
	List<Room> getListRoomsByAvailable() throws ServiceException;
	List<Room> getListRoomsBySmoke() throws ServiceException;
}
