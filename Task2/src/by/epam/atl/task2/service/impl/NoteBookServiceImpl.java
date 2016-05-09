package by.epam.atl.task2.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.dao.DAOFactory;
import by.epam.atl.task2.dao.NoteBookDao;
import by.epam.atl.task2.dao.exception.DAOException;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.exception.ServiceException;

public class NoteBookServiceImpl implements NoteBookService {

	@Override
	public List<Note> getListNotes() {
		return NoteBookProvider.getInstance().getListNotes();
	}

	@Override
	public NoteBook createNoteBook() {
		
		//create empty list of notes
		List<Note> newNoteList = new ArrayList<Note>();
		
		//set list of notes into notebook
		NoteBookProvider.getInstance().setNoteBook(newNoteList);
		
		return NoteBookProvider.getInstance();
		
	}

	@Override
	public List<Note> findNotesByDate(Date data) throws ServiceException {
		
		if (data == null) {
			throw new ServiceException("Data is equal null. Searching is impossible!");
		}
		
		List<Note> resultNotes = new ArrayList<Note>();
		
		//get notes from notebook
		List<Note> notebook = getListNotes();
		
		//iterate notes
		for(Note note: notebook){
			if (note.getDate().equals(data)){
				resultNotes.add(note);
				
			}
		}
		
		return resultNotes;
	}

	@Override
	public List<Note> findNotesByContent(String searchString) throws ServiceException {
		
		if (searchString == null){
			throw new ServiceException("String for searching is not initialised. Searching is impossible!");
		}
		
		if (searchString.length() == 0){
			throw new ServiceException("String for searching is empty. Searching is impossible!");
		}
		List<Note> resultNotes = new ArrayList<Note>();
		
		//get notes from notebook
		List<Note> notebook = getListNotes();
		
		//iterate notes
		for(Note note: notebook){
			if (note.getNote().contains(searchString)){
				resultNotes.add(note);
				
			}
		}
		
		return resultNotes;
	}

	@Override
	public void addNoteToNoteBook(Note nt) throws ServiceException {
		
		if (nt == null){
			throw new ServiceException("Note is not initialized! ");
		}
		
		//set list of notes into notebook
		NoteBookProvider.getInstance().addNote(nt); 
				
	}

	@Override
	public NoteBook loadNoteBookFromFile(String fileName) throws ServiceException {
		NoteBook ntb = null;
		
		NoteBookDao ntbDao = DAOFactory.getInstance().getNoteBookDao();

		try {
			ntb = ntbDao.loadNoteBookFromFile(fileName);
		
			NoteBookProvider.setInstance(ntb);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
		
		return ntb;
	}

	@Override
	public void unloadBookIntoFile(String fileName) throws ServiceException {
		
		NoteBookDao ntbDao = DAOFactory.getInstance().getNoteBookDao();
		try{
		
			NoteBook ntb = NoteBookProvider.getInstance();
		
			ntbDao.saveNoteBookIntoFile(ntb, fileName);
		}
		catch(DAOException e){
			throw new ServiceException(e);
		}
		
	}

	@Override
	public Note createNote(Date date, String noteContent) throws ServiceException {
		Note newNote = null;
		
		if (date == null){
			throw new ServiceException("Can't create note with null date");
		}
		//create empty note
		
		newNote = new Note(date, noteContent);
				
		return newNote;
	}
	
	
}
