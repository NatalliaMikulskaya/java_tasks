package by.epam.atl.hotel.dao;

import by.epam.atl.hotel.dao.exception.DAOException;

public interface HotelDAO {
	boolean createDatabase(String filePreferencies) throws DAOException;
	
}
