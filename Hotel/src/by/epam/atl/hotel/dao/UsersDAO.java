package by.epam.atl.hotel.dao;

import java.util.List;

import by.epam.atl.hotel.bean.User;

public interface UsersDAO {
	void addUser(User usr);
	List<User> selectUsers();
	
}
