package by.epam.atl.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.TypeRoom;
import by.epam.atl.hotel.dao.DAOUtil;
import by.epam.atl.hotel.dao.RoomDAO;
import by.epam.atl.hotel.dao.exception.DAOException;

public class RoomDAOImpl implements RoomDAO {

	// Constants ----------------------------------------------------------------------------------
	private static final String SQL_FIND_BY_ID =
			"SELECT *  FROM rooms WHERE id = ?";

	private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM rooms ORDER BY id";
	private static final String SQL_LIST_AVAILABLE_ROOMS = "SELECT * FROM rooms WHERE available = ? ORDER BY id";
	private static final String SQL_LIST_AVAILABLE_ROOMS_BY_SMOKE =
			"SELECT * FROM rooms WHERE available = ? AND smoke = ? ORDER BY id";
	private static final String SQL_LIST_AVAILABLE_ROOMS_BY_TYPE =
			"SELECT * FROM rooms WHERE available = ? AND type = ? ORDER BY id";
	private static final String SQL_LIST_AVAILABLE_ROOMS_BY_TYPE_AND_SMOKE =
			"SELECT * FROM rooms WHERE available = ? AND type = ? AND smoke = ? ORDER BY id";
	private static final String SQL_LIST_AVAILABLE_ROOMS_BY_TYPE_AND_SMOKE_AND_CAPACITY =
			"SELECT * FROM rooms WHERE available = ? AND type = ? AND capacity = ? AND smoke = ?  ORDER BY id";
	private static final String SQL_LIST_AVAILABLE_ROOMS_BY_TYPE_AND_CAPACITY =
			"SELECT * FROM rooms WHERE available = ? AND type = ? AND capacity = ? ORDER BY id";    
	private static final String SQL_LIST_AVAILABLE_ROOMS_BY_CAPACITY =
			"SELECT * FROM rooms WHERE available = ? AND capacity = ? ORDER BY id";   
	private static final String SQL_LIST_AVAILABLE_ROOMS_BY_CAPACITY_AND_SMOKE =
			"SELECT * FROM rooms WHERE available = ? AND capacity = ? AND smoke = ?  ORDER BY id";
	private static final String SQL_INSERT =
			"INSERT INTO rooms (capacity, type, smoke, available) VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE =
			"UPDATE rooms SET capacity = ?, type = ?, smoke = ?, available = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM rooms WHERE id = ?";
	private static final String SQL_CLOSE_ROOM =
			"UPDATE rooms SET available = false WHERE id = ?";
	private static final String SQL_OPEN_ROOM =
			"UPDATE rooms SET available = true WHERE id = ?";


	public RoomDAOImpl(){}

	@Override
	public Room find(int id) throws DAOException {
		return find(SQL_FIND_BY_ID, id);
	}

	@Override
	public List<Room> list() throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( 
				PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
				ResultSet resultSet = statement.executeQuery();
				)
		{
			while ( resultSet.next() ) {
				rooms.add( map(resultSet) );
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return rooms;
	}

	@Override
	public List<Room> findAvailableRooms(boolean available) throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		Object[] values ={
				available	
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (
				PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_LIST_AVAILABLE_ROOMS, true, values);
				ResultSet resultSet = statement.executeQuery();
				)
		{
			while (resultSet.next()) {
				rooms.add(map(resultSet));
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}
		return rooms;
	}

	@Override
	public List<Room> findAllAvailableRooms(boolean canSmoke) throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		Object[] values ={
				true,
				canSmoke
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (
				PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_LIST_AVAILABLE_ROOMS_BY_SMOKE, true, values);
				ResultSet resultSet = statement.executeQuery();
				)
		{
			while (resultSet.next()) {
				rooms.add(map(resultSet));
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}
		return rooms;
	}

	@Override
	public List<Room> findAllAvailableRooms(TypeRoom type, boolean canSmoke) throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		Object[] values ={
				true,
				type,
				canSmoke
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (
				PreparedStatement statement = 
				DAOUtil.prepareStatement(connection,SQL_LIST_AVAILABLE_ROOMS_BY_TYPE_AND_SMOKE, true, values);
				ResultSet resultSet = statement.executeQuery()
				)
		{
			while (resultSet.next()) {
				rooms.add(map(resultSet));
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return rooms;
	}

	@Override
	public List<Room> findAllAvailableRooms(TypeRoom type, int capacity, boolean canSmoke) throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		Object[] values ={
				true,
				type,
				capacity,
				canSmoke
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( 
				PreparedStatement statement = 
				DAOUtil.prepareStatement(connection,SQL_LIST_AVAILABLE_ROOMS_BY_TYPE_AND_SMOKE_AND_CAPACITY, true, values);
				ResultSet resultSet = statement.executeQuery()
				)
		{
			while (resultSet.next()) {
				rooms.add(map(resultSet));
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return rooms;
	}

	@Override
	public List<Room> findAllAvailableRooms(TypeRoom type, int capacity) throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		Object[] values ={
				true,
				type,
				capacity
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (
				PreparedStatement statement = 
				DAOUtil.prepareStatement(connection,SQL_LIST_AVAILABLE_ROOMS_BY_TYPE_AND_CAPACITY, true, values);
				ResultSet resultSet = statement.executeQuery()
				)
		{
			while (resultSet.next()) {
				rooms.add(map(resultSet));
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return rooms;
	}

	@Override
	public List<Room> findAllAvailableRooms(int capacity) throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		Object[] values ={
				true,
				capacity
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (
				PreparedStatement statement = 
				DAOUtil.prepareStatement(connection,SQL_LIST_AVAILABLE_ROOMS_BY_CAPACITY, true, values);
				ResultSet resultSet = statement.executeQuery()
				)
		{
			while (resultSet.next()) {
				rooms.add(map(resultSet));
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return rooms;
	}

	@Override
	public List<Room> findAllAvailableRooms(TypeRoom type) throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		Object[] values ={
				true,
				type
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (
				PreparedStatement statement = 
				DAOUtil.prepareStatement(connection,SQL_LIST_AVAILABLE_ROOMS_BY_TYPE, true, values);
				ResultSet resultSet = statement.executeQuery()
				)
		{
			while (resultSet.next()) {
				rooms.add(map(resultSet));
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return rooms;
	}

	@Override
	public List<Room> findAllAvailableRooms(int capacity, boolean canSmoke ) throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		Object[] values ={
				true,
				capacity,
				canSmoke
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (
				PreparedStatement statement = 
				DAOUtil.prepareStatement(connection,SQL_LIST_AVAILABLE_ROOMS_BY_CAPACITY_AND_SMOKE, true, values);
				ResultSet resultSet = statement.executeQuery()
				)
		{
			while (resultSet.next()) {
				rooms.add(map(resultSet));
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return rooms;
	}

	@Override
	public void create(Room room) throws IllegalArgumentException, DAOException {
		if (room.getId() != 0) {
			throw new IllegalArgumentException("Room is already created, the room ID is not null.");
		}

		Object[] values = {
				room.getCapacity(),
				room.getType().toString(),
				room.isSmoke(),
				room.isAvailable()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values) )
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Creating room failed, no rows affected.");
			}

			//set new id for room
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					room.setId(generatedKeys.getInt(1));
				} else {
					throw new DAOException("Creating room failed, no generated key obtained.");
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
	public void update(Room room) throws IllegalArgumentException, DAOException {
		if (room.getId() == 0) {
			throw new IllegalArgumentException("Room is not created yet, the room ID is null.");
		}

		Object[] values = {
				room.getCapacity(),
				room.getType().toString(),
				room.isSmoke(),
				room.isAvailable(),
				room.getId()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_UPDATE, true, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Updating room failed, no rows affected.");
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
	public void delete(Room room) throws DAOException {
		Object[] values = { 
				room.getId()
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_DELETE, false, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Deleting room failed, no rows affected.");
			} else {
				room.setId(0);
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
	public void closeRoom(Room room) throws DAOException {
		if (!room.isAvailable()){
			throw new DAOException("Room is already closed.");
		}

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (PreparedStatement statement = connection.prepareStatement(SQL_CLOSE_ROOM))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Closing room failed, no rows affected.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}
	}

	@Override
	public void openRoom(Room room) throws DAOException {
		if (room.isAvailable()){
			throw new DAOException("Room is already open.");
		}

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try (PreparedStatement statement = connection.prepareStatement(SQL_OPEN_ROOM))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Opening room failed, no rows affected.");
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

	private Room find(String sql, Object... values) throws DAOException {
		Room room = null;

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( 
			PreparedStatement statement = DAOUtil.prepareStatement(connection, sql, false, values);
			ResultSet resultSet = statement.executeQuery();
			) 
		{
			if (resultSet.next()) {
				room = map(resultSet);
			}
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			//free connection
			poolManager.returnConnectionToPool(connection);
		}

		return room;
	}

	private static Room map(ResultSet resultSet) throws SQLException {
		Room room = new Room();

		room.setId(resultSet.getInt("id"));
		room.setCapacity(resultSet.getInt("capacity"));
		room.setSmoke(resultSet.getBoolean("smoke"));
		room.setAvailable(resultSet.getBoolean("available"));

		TypeRoom t = TypeRoom.valueOf(resultSet.getString("type"));
		room.setType(t);

		return room;
	}
}
