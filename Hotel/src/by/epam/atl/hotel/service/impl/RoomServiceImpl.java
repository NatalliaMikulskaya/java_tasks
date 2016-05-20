package by.epam.atl.hotel.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.TypeRoom;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.DAOFactory;
import by.epam.atl.hotel.dao.RoomDAO;
import by.epam.atl.hotel.dao.exception.DAOException;
import by.epam.atl.hotel.service.RoomService;
import by.epam.atl.hotel.service.ServiceFactory;
import by.epam.atl.hotel.service.UserAccess;
import by.epam.atl.hotel.service.exception.ServiceException;

public class RoomServiceImpl implements RoomService {
	private final UserAccess checkAccess = ServiceFactory.getInstance().getUserAccessService();
	private final RoomDAO roomDao = DAOFactory.getInstance().getRoomDAO();

	@Override
	public List<Room> getListRooms(User currentUser) throws ServiceException {
		List<Room> rooms = null;
		
		if (! checkAccess.isUserAllowedSeeAllRooms(currentUser) ){
			throw new ServiceException("User not allowed to see full list room.");
		}
		
		try {
			rooms = roomDao.list();
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
		
		return rooms;
	}

	@Override
	public List<Room> getListRoomsByAvailable(User currentUser) throws ServiceException {
		List<Room> rooms = null;
		
		if (! checkAccess.isUserAllowedSeeOpenRooms(currentUser) ){
			throw new ServiceException("User not allowed to see list of accesible rooms.");
		}
		
		try {
			rooms = roomDao.findAvailableRooms(true);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
		
		return rooms;
	}

	@Override
	public List<Room> getListRoomsByParameters(User currentUser, HashMap<String, Object> parameters) throws ServiceException {
		List<Room> rooms = null;
		
		if (! checkAccess.isUserAllowedSeeOpenRooms(currentUser) ){
			throw new ServiceException("User not allowed to see list of accesible rooms.");
		}

		switch (parameters.size()){
		case 3 : {
			if (parameters.containsKey("smoke") &&
					parameters.containsKey("type") &&
					parameters.containsKey("capacity")){

				try {
					rooms = roomDao.findAllAvailableRooms((TypeRoom)parameters.get("type"),
							(int) parameters.get("capacity"),
							(boolean) parameters.get("smoke"));
				}
				catch (DAOException e){
					throw new ServiceException(e);
				}
			}
			break;
		}
		case 2: {
			if (parameters.containsKey("smoke") &&
					parameters.containsKey("type") ){

				try {
					rooms = roomDao.findAllAvailableRooms((TypeRoom)parameters.get("type"),
							(boolean) parameters.get("smoke"));
				}
				catch (DAOException e){
					throw new ServiceException(e);
				}
			} else if (parameters.containsKey("smoke") &&
					parameters.containsKey("capacity")){
				try {
					rooms = roomDao.findAllAvailableRooms((int) parameters.get("capacity"),
							(boolean) parameters.get("smoke"));
				}
				catch (DAOException e){
					throw new ServiceException(e);
				}
			} else if (parameters.containsKey("type") &&
					parameters.containsKey("capacity")){
				try {
					rooms = roomDao.findAllAvailableRooms((TypeRoom)parameters.get("type"),
							(int) parameters.get("capacity"));
				}
				catch (DAOException e){
					throw new ServiceException(e);
				}
			}

			break;
		}
		case 1: {
			if (parameters.containsKey("smoke") ){

				try {
					rooms = roomDao.findAllAvailableRooms((boolean) parameters.get("smoke"));
				}
				catch (DAOException e){
					throw new ServiceException(e);
				}
			} else if (	parameters.containsKey("capacity")){
				try {
					rooms = roomDao.findAllAvailableRooms((int) parameters.get("capacity"));
				}
				catch (DAOException e){
					throw new ServiceException(e);
				}
			} else if (parameters.containsKey("type")) {
				try {
					rooms = roomDao.findAllAvailableRooms((TypeRoom)parameters.get("type"));
				}
				catch (DAOException e){
					throw new ServiceException(e);
				}
			}
			break;
		}
		}
		
		return rooms;
	}

	@Override
	public void addRooms(List<Room> rooms) throws ServiceException {
		if (! checkAccess.isUserAllowedCloseRooms(currentUser) ){
			throw new ServiceException("User not allowed to see list of accesible rooms.");
		}
		
	}

	@Override
	public void deleteRooms(List<Room> rooms) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeRooms(List<Room> rooms) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeRooms(List<Room> rooms, Date dateFrom, Date dateTo) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openRooms(List<Room> rooms) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}
