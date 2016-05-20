package by.epam.atl.hotel.service.impl;

import java.util.List;

import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.exception.DAOException;
import by.epam.atl.hotel.dao.impl.RoomDAOImpl;
import by.epam.atl.hotel.service.RoomService;
import by.epam.atl.hotel.service.ServiceFactory;
import by.epam.atl.hotel.service.exception.ServiceException;

public class RoomServiceImpl implements RoomService {

	@Override
	public List<Room> getListRooms(User currentUser) throws ServiceException {
		List<Room> rooms = null;
		
		UserAccessImpl checkAccess = (UserAccessImpl) ServiceFactory.getInstance().getUserAccessService();
		
		if (! checkAccess.isUserAllowedSeeAllRooms(currentUser) ){
			throw new ServiceException("User not allowed to see full list room.");
		}
		
		RoomDAOImpl roomDao = new RoomDAOImpl();
		try {
			rooms = roomDao.list();
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
		
		return rooms;
	}

	@Override
	public List<Room> getListRoomsByAvailable() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getListRoomsBySmoke() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
