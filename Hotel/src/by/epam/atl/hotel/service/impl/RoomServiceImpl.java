package by.epam.atl.hotel.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass()); 

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
	public boolean addRoom(User currentUser, int number, int capacity, boolean smoke, boolean available, TypeRoom type) 
			throws ServiceException {
		
		if (! checkAccess.isUserAllowedAddRooms(currentUser) ){
			throw new ServiceException("User not allowed to add rooms.");
		}
		
		if (number <= 0 ){
			throw new ServiceException("Room number can't be less or equal 0.");
		}
		
		if (capacity <= 0) {
			throw new ServiceException("Room capacity can't be less or equal 0.");
		}
		
		Room room = new Room();
		room.setAvailable(available);
		room.setCapacity(capacity);
		room.setNumber(number);
		room.setSmoke(smoke);
		room.setType(type);
		
		try {
			roomDao.create(room);
			if (room.getId() != 0){
				return true;
			}else {
				return false;
			}
		}
		catch (DAOException e){
			throw new ServiceException("Error occurred while room was added to database", e);
		}
		
	}
	
	@Override
	public boolean deleteRoom(User currentUser, Room room) throws ServiceException {
		if (! checkAccess.isUserAllowedDeleteRooms(currentUser) ){
			throw new ServiceException("User not allowed to delete rooms.");
		}
		
		if (room == null) {
			throw new ServiceException("Error occurred while room was deleted to database");
		}
			
		if (room.getId() == 0 ){
			throw new ServiceException("Room does not exist in database");
		} else {
			try {
				roomDao.delete(room);
				if (room.getId() == 0){
					return true;
				}else {
					return false;
				}
				
			}catch (DAOException e){
				throw new ServiceException("Error occurred while room was deleted to database", e);
			}
		}
	}
	
	@Override
	public void deleteRooms(User currentUser, List<Room> rooms) throws ServiceException {
		if (! checkAccess.isUserAllowedDeleteRooms(currentUser) ){
			throw new ServiceException("User not allowed to delete rooms.");
		}
		
		int processed = 0;
		int skipped = 0;
		
		for (Room room : rooms){
			if (room == null) {
				skipped ++;
				continue;
			}
			
			if (room.getId() == 0 ){
				skipped ++;
			} else {
				try {
					roomDao.delete(room);
					processed ++;
				}
				catch (DAOException e){
					throw new ServiceException("Error occurred while room was deleted to database", e);
				}
			}
		}
		LOG.info("Operation 'delete rooms': were deleted "+processed+" rooms, were skipped "+ skipped+
				" rooms (because null or not exists)");
		
	}

	@Override
	public void changeRooms(User currentUser, List<Room> rooms) throws ServiceException {
		if (! checkAccess.isUserAllowedUpdateRooms(currentUser) ){
			throw new ServiceException("User not allowed to change rooms.");
		}
		
		int processed = 0;
		int skipped = 0;
		
		for (Room room : rooms){
			if (room == null) {
				skipped ++;
				continue;
			}
			
			if (room.getId() == 0 ){
				skipped ++;
			} else {
				try {
					roomDao.update(room);
					processed ++;
				}
				catch (DAOException e){
					throw new ServiceException("Error occurred while room was updated into database", e);
				}
			}
		}
		LOG.info("Operation 'update rooms': were updated "+processed+" rooms, were skipped "+ skipped+
				" rooms (because null or not exists)");
		
		
	}

	@Override
	public void closeRooms(User user, List<Room> rooms, Date dateFrom, Date dateTo) throws ServiceException {
		if (! checkAccess.isUserAllowedCloseRooms(user) ){
			throw new ServiceException("User not allowed to close rooms.");
		}
				
		try{
			
			int numberClosedRooms = roomDao.closeRoomsIfNotBookedInPeriod(rooms, dateFrom, dateTo);
			LOG.info("Where closed " + numberClosedRooms + " from " + rooms.size());
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}

	}

	@Override
	public void openRooms(User user, List<Room> rooms) throws ServiceException {
		if (! checkAccess.isUserAllowedOpenRooms(user) ){
			throw new ServiceException("User not allowed to open rooms.");
		}
		
		try{
			//get free from booking rooms in period
			int numberOpenedRooms = roomDao.openRoom(rooms);
			LOG.info("Where opened " + numberOpenedRooms + " from " + rooms.size());
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
		
	}

}
