package by.epam.atl.hotel.service;

import java.util.List;

import by.epam.atl.hotel.bean.TypeUser;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.service.exception.ServiceException;

public interface UserService {
	
	User findUserByLogin(String login)throws ServiceException;
	User findUserByLoginPassword(String login, String password) throws ServiceException;
	User findUserByID(int id)throws ServiceException;
	
	boolean isUserLoginExists(String login) throws ServiceException;
	boolean isUserEmailExists(String email) throws ServiceException;
	
	boolean addUser(String login, String password, String name, TypeUser type, String email) throws ServiceException;
		
	boolean deleteUser(User currentUser, User deleteUser) throws ServiceException;
	int deleteUser(User currentUser, List<User> users) throws ServiceException;
	
	void changeUserAccess(User currentUser, User userForChange, TypeUser type)throws ServiceException;
	void changeUserName(User currentUser, User userForChange, String name)throws ServiceException;
	void changeUserEmail(User currentUser, User userForChange, String email)throws ServiceException;
		
	int banUsers(User currentUser, List<User> users) throws ServiceException;
	int unbanUsers(User currentUser, List<User> users) throws ServiceException;

}
