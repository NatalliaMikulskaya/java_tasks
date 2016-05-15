package by.epam.atl.hotel.dao.impl;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.hotel.bean.DbProperties;
import by.epam.atl.hotel.dao.HotelDAO;
import by.epam.atl.hotel.dao.exception.DAOException;

public class HotelDAOImpl implements HotelDAO {

	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public HotelDAOImpl(){
		
	}
	
	@Override
	public void createDatabase(String filePreferencies) throws DAOException  {
		
		DbProperties properties = parseProperties(filePreferencies);
		
		try{
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testphones",
				"root", "pasword");
			
		}catch (SQLException e){
			throw new DAOException("Can't create connection to database", e);
		}
		
		
	}
	
	private DbProperties parseProperties(String fileName) throws DAOException{
		DbProperties dp = new DbProperties();
		
		File fl = new File(fileName);
		
		if (fl.exists() && fl.isFile() && fl.canRead()){
			
		}
		else {
			throw new DAOException("Can't read file whith properties.");
		}
		
		return dp;
	}

	
}
