package by.epam.atl.hotel.dao.impl;

import java.sql.Connection;
import java.util.List;

import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.ConnectionFactory;
import by.epam.atl.hotel.dao.UsersDAO;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public void addUser(User usr) {
		Connection con = ConnectionFactory.connection;
		
		String query = "INSERT ";

	}

	@Override
	public List<User> selectUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
