package by.epam.atl.hotel.dao;

public class DAOFactory {
	private static final DAOFactory factory = new DAOFactory();

	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return factory;
	}
}
