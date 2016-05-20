package by.epam.atl.hotel.service;

import by.epam.atl.hotel.service.impl.RoomServiceImpl;
import by.epam.atl.hotel.service.impl.UserAccessImpl;
import by.epam.atl.hotel.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static ServiceFactory factory = new ServiceFactory();
	private final UserService userService = new UserServiceImpl();
	private final RoomService roomService = new RoomServiceImpl();
	private final UserAccess userAccessService = new UserAccessImpl();
	
	private ServiceFactory(){}
	
	public static ServiceFactory getInstance(){
		return factory;
	}
	
	public UserService getUserService(){
		return userService;
	}
	
	public RoomService getRoomService(){
		return roomService;
	}
	
	public UserAccess getUserAccessService(){
		return userAccessService;
	}
	
}
