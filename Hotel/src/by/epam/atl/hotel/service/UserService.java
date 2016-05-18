package by.epam.atl.hotel.service;

import java.util.List;

import by.epam.atl.hotel.bean.User;

public interface UserService {
	
	User findUserByLogin(String login);
	

}
