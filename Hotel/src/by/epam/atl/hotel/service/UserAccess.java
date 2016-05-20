package by.epam.atl.hotel.service;

import by.epam.atl.hotel.bean.User;

public interface UserAccess {
	
	boolean isUserAllowedSeeAllRooms(User user);
	boolean isUserAllowedSeeCloseRooms(User user);
	boolean isUserAllowedSeeOpenRooms(User user);
	boolean isUserAllowedDoBooking(User user);
	boolean isUserAllowedSeeAllBooking(User user);
	boolean isUserAllowedCloseRooms(User user);
	boolean isUserAllowedOpenRooms(User user);
	boolean isUserAllowedAddRooms(User user);
	boolean isUserAllowedDeleteRooms(User user);
	boolean isUserAllowedUpdateRooms(User user);
	
}
