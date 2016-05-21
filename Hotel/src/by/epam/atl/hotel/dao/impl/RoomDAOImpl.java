package by.epam.atl.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.epam.atl.hotel.bean.Room;
import by.epam.atl.hotel.bean.TypeRoom;
import by.epam.atl.hotel.dao.ConnectionPoolManager;
import by.epam.atl.hotel.dao.DAOUtil;
import by.epam.atl.hotel.dao.RoomDAO;
import by.epam.atl.hotel.dao.exception.DAOException;

public class RoomDAOImpl implements RoomDAO {

	// Constants ----------------------------------------------------------------------------------
	private static final String SQL_FIND_BY_ID =
			"SELECT *  FROM rooms WHERE id = ?";
	
	private static final String SQL_FIND_BY_NUMBER =
			"SELECT *  FROM rooms WHERE number = ?";

	private static final String SQL_LIST_ROOMS_BY_ID = "SELECT * FROM rooms ORDER BY id";
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
	private static final String SQL_DELETE = 
			"DELETE FROM rooms WHERE id = ? "
			+ " AND id NOT IN (SELECT room_id FROM booking)";
	private static final String SQL_CLOSE_ROOM =
			"UPDATE rooms SET available = false WHERE id = ?";
	private static final String SQL_OPEN_ROOM =
			"UPDATE rooms SET available = true WHERE id = ?";
	private static final String SQL_LIST_NOT_BOOKED_ROOMS_IN_PERIOD =
			"SELECT * FROM rooms WHERE id NOT IN (SELECT room_id FROM hotel.booking "+ 
			" WHERE booking.date_from >= ? AND booking.date_to <= ?)";
	
	private static final String SQL_CHECK_ROOM_NOT_BOOKED_IN_PERIOD =
			"SELECT * FROM rooms WHERE id = ? AND available = 1 AND id NOT IN "
			+ " (SELECT room_id FROM hotel.booking "+ 
			" WHERE booking.date_from >= ? AND booking.date_to <= ?)";
	
	private static final String SQL_CLOSE_ROOM_IF_NOT_BOOKED = ""
			+ "UPDATE rooms SET rooms.available = 0 WHERE (rooms.id = ? "
			+ "AND rooms.id NOT IN " 
			+ " (SELECT room_id FROM booking  WHERE booking.date_from >= ? AND hotel.booking.date_to <= ? )) ";

	public RoomDAOImpl(){}

	@Override
	public Room findByID(int id) throws DAOException {
		
		return find(SQL_FIND_BY_ID, id);
	}
	
	@Override
	public Room findByNumber(int number) throws DAOException {
		
		return find(SQL_FIND_BY_NUMBER, number);
	}

	@Override
	public List<Room> list() throws DAOException {
	
		return findList(SQL_LIST_ROOMS_BY_ID);
	}

	@Override
	public List<Room> findAvailableRooms(boolean available) throws DAOException {
		
		return findList(SQL_LIST_AVAILABLE_ROOMS, available);
	}

	@Override
	public List<Room> findAllAvailableRooms(boolean canSmoke) throws DAOException {
	
		return findList(SQL_LIST_AVAILABLE_ROOMS_BY_SMOKE, true, canSmoke);
	}

	@Override
	public List<Room> findAllAvailableRooms(TypeRoom type, boolean canSmoke) throws DAOException {

		return findList(SQL_LIST_AVAILABLE_ROOMS_BY_TYPE_AND_SMOKE, true, type.toString(), canSmoke);
	}

	@Override
	public List<Room> findAllAvailableRooms(TypeRoom type, int capacity, boolean canSmoke) throws DAOException {
		
		return findList(SQL_LIST_AVAILABLE_ROOMS_BY_TYPE_AND_SMOKE_AND_CAPACITY, true, type.toString(), capacity, canSmoke);
	}

	@Override
	public List<Room> findAllAvailableRooms(TypeRoom type, int capacity) throws DAOException {

		return findList(SQL_LIST_AVAILABLE_ROOMS_BY_TYPE_AND_CAPACITY, true, type.toString(), capacity);
	}

	@Override
	public List<Room> findAllAvailableRooms(int capacity) throws DAOException {
		
		return findList(SQL_LIST_AVAILABLE_ROOMS_BY_CAPACITY, true, capacity);
	}

	@Override
	public List<Room> findAllAvailableRooms(TypeRoom type) throws DAOException {
		
		return findList(SQL_LIST_AVAILABLE_ROOMS_BY_TYPE, true, type.toString());
	}

	@Override
	public List<Room> findAllAvailableRooms(int capacity, boolean canSmoke ) throws DAOException {
		
		return findList(SQL_LIST_AVAILABLE_ROOMS_BY_CAPACITY_AND_SMOKE, true, capacity, canSmoke);
	}


	@Override
	public boolean isRoomAvailableInPeriod(Room room, Date dateFrom, Date dateTo) throws DAOException {
		if (room.getId() == 0){
			throw new DAOException("Room does not exist in database");
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		 
		String sqlDateFrom = df.format(dateFrom);
		String sqlDateTo = df.format(dateTo);
		
		Object[] values = {
				room.getId(),
				sqlDateFrom,
				sqlDateTo
		};

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( 
				PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_CHECK_ROOM_NOT_BOOKED_IN_PERIOD, true, values); 
				ResultSet resultSet = statement.executeQuery()
			)
		{
			if (resultSet.next()) {
				return true;
			}
			
			return false;
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
		
		//To Do
		
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

		Object[] values = {
				room.getId()	
			};
		
		try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_CLOSE_ROOM, true, values))
		{
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Closing room failed, no rows affected.");
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
	public void openRoom(Room room) throws DAOException {
		if (room.isAvailable()){
			throw new DAOException("Room is already open.");
		}

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		Object[] values = {
			room.getId()	
		};
		
		try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_OPEN_ROOM, true, values))
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
	
	@Override
	public int openRoom(List<Room> rooms) throws DAOException {
		int openedRooms = 0;
		
		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try{
			connection.setAutoCommit(false);
			for (Room room : rooms){
				if (room.isAvailable()) {
					continue;
				}
				Object[] values = {
					room.getId()	
				};
				
				PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_OPEN_ROOM, true, values); 
				openedRooms += statement.executeUpdate();

			}
			connection.commit();
			
		} 
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			try{
				connection.setAutoCommit(true);
			}
			catch (SQLException e){
				
			}
			finally{
				//free connection
				poolManager.returnConnectionToPool(connection);
			}
		}
		
		return openedRooms;
	}

	@Override
	public List<Room> findAllNotBookedRoomsInPeriod(Date dateFrom, Date dateTo) throws DAOException {
		
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		 
		String sqlDateFrom = df.format(dateFrom);
		String sqlDateTo = df.format(dateTo);
		
		return findList(SQL_LIST_NOT_BOOKED_ROOMS_IN_PERIOD, sqlDateFrom, sqlDateTo);
	}
	
	@Override
	public int closeRoomsIfNotBookedInPeriod(List<Room> rooms, Date dateFrom, Date dateTo) throws DAOException {
		int closed = 0;
		
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		 
		String sqlDateFrom = df.format(dateFrom);
		String sqlDateTo = df.format(dateTo);
		
		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();
		
		try {
			connection.setAutoCommit(false);

			for (Room room: rooms){
				if (closeRoom (connection, room, sqlDateFrom, sqlDateTo )) {
					closed ++;
				}

			}
			try {
				connection.commit();
			} 
			catch (SQLException e){
				closed = 0;
			} 
		}
		catch (SQLException e ) {
			throw new DAOException(e);
		}
		finally{
			try {
				connection.setAutoCommit(true);
			}
			catch (SQLException e ) {
				throw new DAOException(e);
			}
			finally{
				poolManager.returnConnectionToPool(connection);
			}
		}
		
		return closed;
	}
	
	private boolean closeRoom(Connection connection, Room room, String from, String to) throws DAOException{
		if (room.isAvailable()){
			Object[] values = {
					room.getId(),
					from,
					to
			};

			try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_CLOSE_ROOM_IF_NOT_BOOKED, true, values))
			{
				int affectedRows = statement.executeUpdate();
				if (affectedRows != 0) {
					return true;
				}
			} 
			catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return true;
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
	
	private List<Room> findList(String sql) throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( 
				PreparedStatement statement = connection.prepareStatement(sql);
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
	
	private List<Room> findList(String sql, Object... values) throws DAOException {
		List<Room> rooms = new ArrayList<Room>();

		ConnectionPoolManager poolManager = ConnectionPoolManager.getInstance();
		//take free connection from pool
		Connection connection = poolManager.getConnectionFromPool();

		try ( 
				PreparedStatement statement = DAOUtil.prepareStatement(connection, sql, true, values);
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

	private static Room map(ResultSet resultSet) throws SQLException {
		Room room = new Room();

		room.setId(resultSet.getInt("id"));
		room.setNumber(resultSet.getInt("number"));
		room.setCapacity(resultSet.getInt("capacity"));
		room.setSmoke(resultSet.getBoolean("smoke"));
		room.setAvailable(resultSet.getBoolean("available"));

		TypeRoom t = TypeRoom.valueOf(resultSet.getString("type"));
		room.setType(t);

		return room;
	}
	
}
