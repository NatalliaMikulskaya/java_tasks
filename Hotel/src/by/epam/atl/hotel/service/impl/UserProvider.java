package by.epam.atl.hotel.service.impl;

import by.epam.atl.hotel.bean.User;

public class UserProvider {
	
	private static User instance;
	
	private UserProvider(){
		instance = null;
	}
	
	public static User getInstance(){
		if (instance == null){
			instance = new User();
		}
		return instance;
	}
	
	public static void setInstance(User usr){
		instance = usr;
	}	
	

}
