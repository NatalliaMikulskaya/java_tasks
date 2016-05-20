package by.epam.atl.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.epam.atl.hotel.bean.Booking;
import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.TypeRoom;
import by.epam.atl.hotel.bean.TypeUser;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.BookingDAO;
import by.epam.atl.hotel.dao.ConnectionPoolManager;
import by.epam.atl.hotel.dao.DAOUtil;
import by.epam.atl.hotel.dao.exception.DAOException;

public class BookingDAOImpl implements BookingDAO {

	private static final String SQL_FIND_BY_ID =
			"SELECT booking.*, users.name, users.login, users.password, users.email, users.ban, "
			+ "rooms.number, rooms.capacity, rooms.type, rooms.smoke, rooms.available "
			+ "FROM booking, users, rooms WHERE booking.id = ? AND booking.room_id = rooms.id "
			+ "AND booking.user_id = users.id";
	private static final String SQL_LIST_ORDERS_BY_ID = 
			"SELECT booking.*, users.name, users.login, users.password, users.email, users.ban, "
			+ "rooms.number, rooms.capacity, rooms.type, rooms.smoke, rooms.available "
			+ "FROM booking, users, rooms "
			+ "WHERE booking.room_id = rooms.id AND booking.user_id = users.id "
			+ "ORDER BY order_id";
	private static final String SQL_FIND_BY_TO_DATE =
			"SELECT booking.*, users.name, users.login, users.password, users.email, users.ban, "
			+ "rooms.number, rooms.capacity, rooms.type, rooms.smoke, rooms.available "
			+ "FROM booking, users, rooms "
			+ "WHERE date_from = ? AND date_to = ? AND booking.room_id = rooms.id AND booking.user_id = users.id "
			+ "ORDER BY order_id";
	private static final String SQL_FIND_BY_START_DATE =
			"SELECT booking.*, users.name, users.login, users.password, users.email, users.ban, "
			+ "rooms.number, rooms.capacity, rooms.type, rooms.smoke, rooms.available "
			+ "FROM booking, users, rooms "
			+ "WHERE date_from = ? AND booking.room_id = rooms.id AND booking.user_id = users.id "
			+ "ORDER BY order_id";
	private static final String SQL_FIND_BY_USER =
			"SELECT booking.*, users.name, users.login, users.password, users.email, users.ban, "
			+ "rooms.number, rooms.capacity, rooms.type, rooms.smoke, rooms.available "
			+ " FROM booking, users, rooms "
			+ " WHERE user_id = ? AND booking.room_id = rooms.id AND booking.user_id = users.id "
			+ " ORDER BY order_id";
	private static final String SQL_FIND_BY_ROOM =
			"SELECT booking.*, users.name, users.login, users.password, users.email, users.ban, "
			+ "rooms.number, rooms.capacity, rooms.type, rooms.smoke, rooms.available "
			+ " FROM booking, users, rooms "
			+ " WHERE room_id = ? AND booking.room_id = rooms.id AND booking.user_id = users.id "
			+ " ORDER BY order_id";
	private static final String SQL_FIND_BY_CUSTOMER =
			"SELECT booking.*, users.name, users.login, users.password, users.email, users.ban, "
			+ "rooms.number, rooms.capacity, rooms.type, rooms.smoke, rooms.available "
			+ " FROM booking, users, rooms "
			+ " WHERE customer_name = ? AND booking.room_id = rooms.id AND booking.user_id = users.id "
			+ " ORDER BY order_id";
	private static final String SQL_INSERT =
			"INSERT INTO booking (room_id, user_id, customer_name, date_from, date_to) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM booking WHERE id = ?";
	
	private static final String SQL_UPDATE =
			"UPDATE booking SET room_id = ?, user_id = ?, customer_name = ?, date_from = ?, date_to = ? WHERE id = ?";
	
	
	public BookingDAOImpl(){}
	
	@Override
	public Booking findOrderByID(int id) throws DAOException {
		return find(SQL_FIND_BY_ID, id);
	}

	@Override
	public List<Booking> list() throws DAOException {
		return findList(SQL_LIST_ORDERS_BY_ID);
	}

	@Override
	public List<Booking> findOrdersByPeriod(Date dateFrom, Date dateTo) throws DAOException {
		return findList(SQL_FIND_BY_TO_DATE, dateFrom, dateTo);
	}

	@Override
	public List<Booking> findOrdersByStartDate(Date dateFrom) throws DAOException {
		return findList(SQL_FIND_BY_START_DATE, dateFrom);
	}

	@Override
	public List<Booking> findOrdersByUser(User user) throws DAOException {
		if ( user.getUserID() == 0 ){
			throw new DAOException("User " + user.toString() + " isn't registered in database. Searching is impossible!");
		}
		
		return findList(SQL_FIND_BY_USER, user.getUserID());
	}

	@Override
	public List<Booking> findOrdersByRoom(Room room) throws DAOException {
		if ( room.getId() == 0 ){
			throw new DAOException("Room " + room.toString() + " isn't registered in database. Searching is impossible!");
		}
		return findList(SQL_FIND_BY_ROOM, room.getId());
	}

	@Override
	public List<Booking> findOrdersByCustomer(String customerName) throws DAOException {
		return findList(SQL_FIND_BY_CUSTOMER, customerName);
	}

	@Override
	public void addOrder(Booking order) throws DAOException {
		if (order.getOrderID() != 0) {
			throw new IllegalArgumentException("Order is already created, the order ID is not null.");
		}

		Object[] values = {
				order.getRoom().getId(),
				order.getUser().getUserID(),
				order.getCustomerName(),
				order.getDateFrom(),
				order.getDateTo()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values) )
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Creating order failed, no rows affected.");
			}

			//set new id for room
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					order.setOrderID(generatedKeys.getInt(1));
				} else {
					throw new DAOException("Creating order failed, no generated key obtained.");
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
	public void deleteOrder(Booking order) throws DAOException {
		if (order.getOrderID() == 0) {
			throw new IllegalArgumentException("Order is not exists in database.");
		}
		
		Object[] values = { 
				order.getOrderID()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_DELETE, false, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Deleting order failed, no rows affected.");
			} else {
				order.setOrderID(0);
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
	public void updateOrder(Booking order) throws DAOException {
		if (order.getOrderID() == 0) {
			throw new IllegalArgumentException("Order is not exists in database.");
		}
		
		Object[] values = {
				order.getRoom().getId(),
				order.getUser().getUserID(),
				order.getCustomerName(),
				order.getDateFrom(),
				order.getDateTo()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_UPDATE, true, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Updating order failed, no rows affected.");
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

	/* 
	 * Execute query with set of values. Return one order
	 */
	private Booking find(String sql, Object... values) throws DAOException {
		Booking order = null;

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( 
			PreparedStatement statement = DAOUtil.prepareStatement(connection, sql, false, values);
			ResultSet resultSet = statement.executeQuery();
			) 
		{
			if (resultSet.next()) {
				order = map(resultSet);
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return order;
	}
	
	private List<Booking> findList(String sql) throws DAOException {

		List<Booking> orders = new ArrayList<Booking>();

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( 
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			)
		{
			while ( resultSet.next() ) {
				orders.add( map(resultSet) );
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return orders;
	}
	
	/* 
	 * Execute query with set of values. Return list of orders
	 */
	private List<Booking> findList(String sql, Object... values) throws DAOException {
		List<Booking> orders = new ArrayList<Booking>();

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( 
			PreparedStatement statement = DAOUtil.prepareStatement(connection, sql, true, values);
			ResultSet resultSet = statement.executeQuery();
			) 
		{
			while (resultSet.next()) {
				orders.add(map(resultSet));
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return orders;
	}
	
	
	private static Booking map(ResultSet resultSet) throws SQLException {
		Booking order = new Booking();
		User user = new User();
		Room room = new Room();

		order.setOrderID(resultSet.getInt("order_id"));
		
		//create room
		room.setNumber(resultSet.getInt("number"));
		room.setId(resultSet.getInt("room_id"));
		room.setCapacity(resultSet.getInt("capacity"));
		TypeRoom typeRoom = TypeRoom.valueOf(resultSet.getString("type"));
		room.setType(typeRoom);
		room.setSmoke(resultSet.getBoolean("smoke"));
		room.setAvailable(resultSet.getBoolean("available"));
		
		//set room into order
		order.setRoom(room);
		
		//create user
		user.setUserID(resultSet.getInt("user_id"));
		user.setUserName(resultSet.getString("name"));
		user.setUserLogin(resultSet.getString("login"));
		user.setUserPassword(resultSet.getString("password"));
		user.setEmail(resultSet.getString("email"));
		user.setIsBanned(resultSet.getBoolean("ban"));
		TypeUser typeUser = TypeUser.valueOf(resultSet.getString("access"));
		user.setType(typeUser);
		
		//set user into order
		order.setUser(user);
		
		order.setDateFrom(resultSet.getDate("date_from"));
		order.setDateTo(resultSet.getDate("date_to"));
		order.setCustomerName(resultSet.getString("customer_name"));

		return order;
	}
}
