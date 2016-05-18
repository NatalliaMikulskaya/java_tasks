package by.epam.atl.hotel.service;

public class ServiceFactory {

	private static ServiceFactory factory = new ServiceFactory();
	private final UserService userService = new UserServiceImpl();
	private final RoomService roomService = new RoomServiceImpl();
	
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
	
}
