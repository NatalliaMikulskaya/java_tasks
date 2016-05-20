package by.epam.atl.hotel.service;

import java.util.List;

import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.service.exception.ServiceException;

public interface UserService {
	
	User findUserByLogin(String login);
	boolean addUser(User currentUser, User deleteUser) throws ServiceException;
	int addUser(User currentUser, List<User> users) throws ServiceException;
	
	boolean deleteUser(User currentUser, User deleteUser) throws ServiceException;
	int deleteUser(User currentUser, List<User> users) throws ServiceException;
	
	void changeUser(User currentUser, User changeUser) throws ServiceException;
	
	int banUsers(User currentUser, List<User> users) throws ServiceException;

}
