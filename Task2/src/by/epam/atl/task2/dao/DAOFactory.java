package by.epam.atl.task2.dao;

import by.epam.atl.task2.dao.impl.NoteBookDaoImpl;

public class DAOFactory {
	private static final DAOFactory factory = new DAOFactory();
	private final NoteBookDao noteBookDao = new NoteBookDaoImpl();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return factory;
	}
	
	public NoteBookDao getNoteBookDao() {
		return noteBookDao;
	}
}
