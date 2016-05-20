package by.epam.atl.hotel.service.impl;

import java.util.List;

import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.service.UserService;
import by.epam.atl.hotel.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	@Override
	public User findUserByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(User currentUser, User deleteUser) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addUser(User currentUser, List<User> users) throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteUser(User currentUser, User deleteUser) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deleteUser(User currentUser, List<User> users) throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changeUser(User currentUser, User changeUser) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int banUsers(User currentUser, List<User> users) throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
