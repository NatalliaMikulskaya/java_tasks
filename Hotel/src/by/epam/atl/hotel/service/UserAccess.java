package by.epam.atl.hotel.service;

import by.epam.atl.hotel.bean.User;

public interface UserAccess {
	
	boolean isUserAllowedSeeAllRooms(User user);
	boolean isUserAllowedSeeCloseRooms(User user);
	boolean isUserAllowedSeeOpenRooms(User user);
	boolean isUserAllowedDoBooking(User user);
	boolean isUserAllowedSeeAllBooking(User user);
	
}
