package by.epam.atl.task2.service;

import java.util.GregorianCalendar;
import java.util.List;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.service.impl.NoteBookServiceImpl;

public class ServiceFactory {
	private static ServiceFactory factory = new ServiceFactory();
	private final NoteBookService noteBookService = new NoteBookServiceImpl();
		
	private ServiceFactory(){}
	
	public static ServiceFactory getInstance(){
		return factory;
	}
	
	public NoteBookService getNoteBookService(){
		return noteBookService;
	}
}
