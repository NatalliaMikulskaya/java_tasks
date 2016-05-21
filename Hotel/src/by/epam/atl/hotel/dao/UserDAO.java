package by.epam.atl.hotel.dao;

import java.util.List;

import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.exception.DAOException;

public interface UserDAO {
	
	public User find(int id) throws DAOException;
	public User find(String login, String password) throws DAOException;
	public User find(String login) throws DAOException;
	
	public List<User> list() throws DAOException;
	public void create(User user) throws IllegalArgumentException, DAOException;
	public void update(User user) throws IllegalArgumentException, DAOException;
	public void delete(User user) throws DAOException;
	
	public boolean existEmail(String email) throws DAOException;
	public boolean existLogin(String email) throws DAOException;
	
	public void changePassword(User user) throws DAOException;
	
	public void banUser(User user) throws DAOException;
	public void unbanUser(User user) throws DAOException;
	public void setUserAccess(User user) throws DAOException;
}
