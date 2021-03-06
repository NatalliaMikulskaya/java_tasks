package by.epam.atl.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.atl.hotel.bean.TypeUser;
//import by.epam.atl.hotel.dao.DAOUtil.*;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.ConnectionPoolManager;
import by.epam.atl.hotel.dao.DAOUtil;
import by.epam.atl.hotel.dao.UserDAO;
import by.epam.atl.hotel.dao.exception.DAOException;

public class UserDAOImpl implements UserDAO{

	// Constants ----------------------------------------------------------------------------------
	private static final String SQL_FIND_BY_ID = "SELECT *  FROM users WHERE id = ?";
	private static final String SQL_FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?";
	private static final String SQL_FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
	private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM users ORDER BY id";
	private static final String SQL_INSERT =
			"INSERT INTO users (name, login, password, email, access, ban) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE =
			"UPDATE users SET email = ?, name = ?, login = ?, password = ?, access = ?, ban = ? WHERE id = ?";
	private static final String SQL_DELETE = 
			"DELETE FROM users WHERE id = ?"
			+ " AND id NOT IN (SELECT user_id FROM booking)";
	private static final String SQL_EXIST_EMAIL = "SELECT id FROM users WHERE email = ?";
	private static final String SQL_EXIST_LOGIN = "SELECT id FROM users WHERE login = ?";
	private static final String SQL_CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE id = ?";
	private static final String SQL_BAN_UNBAN_USER = "UPDATE users SET ban = ? WHERE id = ?";
	private static final String SQL_GET_ACCESS_USER = "UPDATE users SET type = ? WHERE id = ?";


	public UserDAOImpl(){}

	@Override
	public User find(int id) throws DAOException {
		return find(SQL_FIND_BY_ID, id);
	}

	@Override
	public User find(String login, String password) throws DAOException {
		return find(SQL_FIND_BY_LOGIN_AND_PASSWORD, login, password);
	}
	
	@Override
	public User find(String login) throws DAOException {
		return find(SQL_FIND_BY_LOGIN, login);
	}

	private User find(String sql, Object... values) throws DAOException {
		User user = null;

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (
			PreparedStatement statement = DAOUtil.prepareStatement(connection, sql, false, values);
			ResultSet resultSet = statement.executeQuery();
			) 
		{
			if (resultSet.next()) {
				user = map(resultSet);
			}

		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return user;
	}

	@Override
	public List<User> list() throws DAOException {
		List<User> users = new ArrayList<User>();

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (
			PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
			ResultSet resultSet = statement.executeQuery();
			)
		{
			while (resultSet.next()) {
				users.add(map(resultSet));
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return users;
	}

	@Override
	public void create(User user) throws IllegalArgumentException, DAOException {
		if (user.getUserID() != 0) {
			throw new IllegalArgumentException("User is already created, the user ID is not null.");
		}

		Object[] values = {
				user.getUserName(),
				user.getUserLogin(),
				user.getUserPassword(),
				user.getEmail(),
				user.getType().toString(),
				false
		};
		
		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Creating user failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setUserID(generatedKeys.getInt(1));
				} else {
					throw new DAOException("Creating user failed, no generated key obtained.");
				}
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

	}

	@Override
	public void update(User user) throws IllegalArgumentException, DAOException {
		if (user.getUserID() == 0) {
			throw new IllegalArgumentException("User is not created yet, the user ID is null.");
		}

		Object[] values = {
				user.getEmail(),
				user.getUserName(),
				user.getUserID()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_UPDATE, false, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Updating user failed, no rows affected.");
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}
	}

	@Override
	public void delete(User user) throws DAOException {
		Object[] values = { 
				user.getUserID()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_DELETE, false, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Deleting user failed, no rows affected.");
			} else {
				user.setUserID(0);
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

	}

	@Override
	public boolean existEmail(String email) throws DAOException {
		Object[] values = { 
				email
		};

		boolean exist = false;

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_EXIST_EMAIL, false, values);
			ResultSet resultSet = statement.executeQuery()
			)
		{
			exist = resultSet.next();
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return exist;
	}

	@Override
	public void changePassword(User user) throws DAOException {
		if (user.getUserID() == 0) {
			throw new IllegalArgumentException("User is not created yet, the user ID is null.");
		}

		Object[] values = {
				user.getUserPassword(),
				user.getUserID()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_CHANGE_PASSWORD, false, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Changing password failed, no rows affected.");
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

	}

	@Override
	public boolean existLogin(String login) throws DAOException {
		Object[] values = { 
				login
		};

		boolean exist = false;

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( 
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_EXIST_LOGIN, false, values);
			ResultSet resultSet = statement.executeQuery();
			)
		{
			exist = resultSet.next();
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return exist;
	}

	@Override
	public void banUser(User user) throws DAOException {
		Object[] values = { 
				true,
				user.getUserID()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_BAN_UNBAN_USER, false, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Ban/unban user failed, no rows affected.");
			} else {
				user.setIsBanned(true);
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

	}
	
	@Override
	public void unbanUser(User user) throws DAOException {
		Object[] values = { 
				false,
				user.getUserID()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_BAN_UNBAN_USER, false, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Ban/unban user failed, no rows affected.");
			} else {
				user.setIsBanned(false);
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}
		
	}

	@Override
	public void setUserAccess(User user) throws DAOException {

		Object[] values = { 
				user.getType().toString(),
				user.getUserID()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_ACCESS_USER, false, values)) 
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Set user access failed, no rows affected.");
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

	}

	private static User map(ResultSet resultSet) throws SQLException {
		User user = new User();

		user.setUserID(resultSet.getInt("id"));
		user.setEmail(resultSet.getString("email"));
		user.setUserName(resultSet.getString("name"));
		user.setUserPassword(resultSet.getString("password"));
		user.setUserLogin(resultSet.getString("login"));
		TypeUser t = TypeUser.valueOf(resultSet.getString("access"));
		user.setType(t);
		user.setIsBanned(resultSet.getBoolean("ban"));

		return user;
	}


}
